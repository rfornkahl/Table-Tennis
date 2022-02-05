package tableTennis;

public class AIController {
    public PlayerController playerController;
    public Rectangle ball;

    public AIController(PlayerController plrController, Rectangle ball ){
        this.playerController = plrController;
        this.ball = ball;
    }

    public void update(double deltaTime){
        playerController.update(deltaTime);

        if (ball.y < playerController.rectangle.y){
            playerController.moveUp(deltaTime);
        } else if (ball.y + ball.height > playerController.rectangle.y + playerController.rectangle.height){
            playerController.moveDown(deltaTime);
        }
    }
}
