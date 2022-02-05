package tableTennis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.renderable.RenderContext;

public class Window extends JFrame implements Runnable {

   public Graphics2D g2;
    public KL keyListner = new KL();
    public Rectangle playerOne, AI, ballRect;
    public PlayerController playerController;
    public AIController aiController;
    public Ball ball;
    public Text leftScoreText, rightScoreText, winner;
    public Boolean isRunning = true;




    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT); //makes the window size 800px x 600px
        this.setTitle(Constants.SCREEN_TITLE); //makes the title "Table Tennis"
        this.setResizable(false); //makes it so the user can't change the size
        this.setVisible(true); //makes the window visible
        this.setIconImage(new ImageIcon("https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.sciencenewsforstudents.org%2Fwp-content%2Fuploads%2F2019%2F11%2F860-sun-yellow-dwarf.gif&imgrefurl=https%3A%2F%2Fwww.sciencenewsforstudents.org%2Farticle%2Fscientists-say-yellow-dwarf&tbnid=4QIQ1GEspTY0zM&vet=12ahUKEwipnK3a8L71AhWEE80KHQQGC3EQMygTegUIARCxAQ..i&docid=2FaCD5XLGRfXhM&w=860&h=460&itg=1&q=sunshine%20and%20star&ved=2ahUKEwipnK3a8L71AhWEE80KHQQGC3EQMygTegUIARCxAQ").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when you click the x the window closes
        this.addKeyListener(keyListner);
        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.INSETS_BOTTOM = this.getInsets().bottom;
        Constants.INSETS_LEFT = this.getInsets().left;
        Constants.INSETS_RIGHT = this.getInsets().right;

        g2 = (Graphics2D)this.getGraphics();

        playerOne = new Rectangle(Constants.PADDING, 40,Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Color.BLUE);
        playerController = new PlayerController(playerOne, keyListner);
        leftScoreText = new Text (0, new Font("Arial", Font.PLAIN, Constants.TEXT_SIZE), Constants.TEXT_X_POS,Constants.TEXT_Y_POS);
        rightScoreText = new Text (0, new Font("Arial", Font.PLAIN, Constants.TEXT_SIZE), Constants.SCREEN_WIDTH-Constants.TEXT_X_POS-Constants.TEXT_SIZE,Constants.TEXT_Y_POS);

        AI = new Rectangle(Constants.SCREEN_WIDTH-Constants.PADDLE_WIDTH - Constants.PADDING, 40,
                Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Color.RED);
        ballRect = new Rectangle(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/2, Constants.BALL_WIDTH,
                Constants.BALL_WIDTH, Color.WHITE);
        ball = new Ball(ballRect, playerOne, AI, leftScoreText, rightScoreText);


        aiController = new AIController(new PlayerController(AI), ballRect);




    }

    public void update(double deltaTime) {
        Image dblImage = createImage((getWidth()), getHeight());
        Graphics dbg = dblImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dblImage, 0, 0, this);

        playerController.update(deltaTime);
        aiController.update(deltaTime);
        ball.update(deltaTime);




    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);


        leftScoreText.draw(g2);
        rightScoreText.draw(g2);
        playerOne.draw(g2);
        AI.draw(g2);
        ballRect.draw(g2);
    }

    public void stop(){
        isRunning = false;

    }

    public void run() {
        double lastFrameTime = 0.0;
        while (isRunning) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);
        }
        this.dispose();
        return;
    }
}
