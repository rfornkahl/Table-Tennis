package tableTennis;

import java.awt.event.KeyEvent;
import java.security.Key;

public class PlayerController {
    public KL keyListner;
    public Rectangle rectangle;

    public PlayerController(Rectangle rectangle, KL keyListner) {
        this.rectangle = rectangle;
        this.keyListner = keyListner;
    }

    public PlayerController(Rectangle rectangle){
        this.rectangle = rectangle;
        this.keyListner = null;
    }

    public void update(double deltaTime) {
        if (keyListner != null) {
            if (keyListner.isKeyPressed(KeyEvent.VK_DOWN)) {
                moveDown(deltaTime);
                if (keyListner.isKeyPressed(KeyEvent.VK_SPACE))
                    this.rectangle.y += Constants.PADDLE_SPEED_CHEATER * deltaTime;
            } else if (keyListner.isKeyPressed(KeyEvent.VK_UP)) {
                moveUp(deltaTime);
                if (keyListner.isKeyPressed(KeyEvent.VK_SPACE))
                    this.rectangle.y -= Constants.PADDLE_SPEED_CHEATER * deltaTime;
            }
            else if (keyListner.isKeyPressed(KeyEvent.VK_SPACE)) {
                if (keyListner.isKeyPressed(KeyEvent.VK_RIGHT))
                    this.rectangle.x = (this.rectangle.x + Constants.PADDLE_SPEED_CHEATER * deltaTime);
                if (keyListner.isKeyPressed(KeyEvent.VK_LEFT))
                    this.rectangle.x = (this.rectangle.x - Constants.PADDLE_SPEED_CHEATER * deltaTime);
            }
        }
    }

    public void moveUp(double deltaTime){
        if (rectangle.y - Constants.PADDLE_SPEED * deltaTime > Constants.TOOLBAR_HEIGHT)
            this.rectangle.y -= Constants.PADDLE_SPEED * deltaTime;
    }

    public void moveDown (double deltaTime){
        if ((rectangle.y + Constants.PADDLE_SPEED * deltaTime) + rectangle.height < Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM)
            this.rectangle.y += Constants.PADDLE_SPEED * deltaTime;
    }
    }