package tableTennis;

import com.sun.tools.jconsole.JConsoleContext;

public class Ball {
    public Rectangle rectangle;
    public Rectangle leftPaddle, rightPaddle;
    public Text rightScoreText, leftScoreText;

    //velocity in x,y
    private double vy = 75;
    private double vx = -200;

    public Ball(Rectangle rectangle, Rectangle leftPaddle, Rectangle rightPaddle, Text leftScoreText, Text rightScoreText) {
        this.rectangle = rectangle;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
    }


    public double calculateNewVelocityAngle(Rectangle paddle) {
        double relativeIntersectY = (paddle.y + (paddle.height / 2.0)) - (this.rectangle.y + (this.rectangle.height / 2.0));
        double normalIntersectY = relativeIntersectY / (paddle.height / 2.0);
        double theta = normalIntersectY * Constants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

    public void update(double deltaTime) {

        if (vx < 0) {
            if (this.rectangle.x <= this.leftPaddle.x + this.leftPaddle.width && this.rectangle.x >= this.leftPaddle.x &&
                    this.rectangle.y >= this.leftPaddle.y && this.rectangle.y <= this.leftPaddle.y + this.leftPaddle.height) {

                double theta = calculateNewVelocityAngle(leftPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta)) * Constants.BALL_SPEED;

                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;

            } else if (this.rectangle.x + this.rectangle.width < this.leftPaddle.x) {
                System.out.println("You have lost a point");
            }
        } else if (vx > 0) {
            if (this.rectangle.x + this.rectangle.width >= this.rightPaddle.x && this.rectangle.x <= this.rightPaddle.x + this.rightPaddle.width &&
                    this.rectangle.y >= this.rightPaddle.y && this.rectangle.y <= this.rightPaddle.y + this.rightPaddle.height) {

                double theta = calculateNewVelocityAngle(rightPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (Math.sin(theta)) * Constants.BALL_SPEED;

                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;


            } else if (this.rectangle.x + this.rectangle.width > this.rightPaddle.x + this.rightPaddle.width) {
                System.out.println("The computer has lost a point");
            }
        }
        if (vy > 0) {
            if (this.rectangle.y + this.rectangle.height > Constants.SCREEN_HEIGHT) {
                this.vy *= -1;
            }
        } else if (vy < 0) {
            if (this.rectangle.y < Constants.TOOLBAR_HEIGHT) {
                this.vy *= -1;
            }

        }
        this.rectangle.x += vx * deltaTime;
        this.rectangle.y += vy * deltaTime;

        if (this.rectangle.x + this.rectangle.width < leftPaddle.x) {
            int rightScore = Integer.parseInt(rightScoreText.text);
            rightScore++;
            rightScoreText.text = "" + rightScore;
            this.rectangle.x = Constants.SCREEN_WIDTH / 2.0;
            this.rectangle.y = Constants.SCREEN_HEIGHT / 2.0;
            this.vx = 75;
            this.vy = -150;
            if (rightScore >= Constants.WIN_SCORE) {
                tableTennis.changeState(2);
            }

        } else if (this.rectangle.x > rightPaddle.x + rightPaddle.width) {
            int leftScore = Integer.parseInt(leftScoreText.text);
            leftScore++;
            leftScoreText.text = "" + leftScore;
            this.rectangle.x = Constants.SCREEN_WIDTH / 2.0;
            this.rectangle.y = Constants.SCREEN_HEIGHT / 2.0;
            this.vx = 75;
            this.vy = -150;
            if (leftScore >= Constants.WIN_SCORE) {
                tableTennis.changeState(2);
            }
        }
    }
}

