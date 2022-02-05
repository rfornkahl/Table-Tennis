package tableTennis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.renderable.RenderContext;

public class MainMenu extends JFrame implements Runnable {

    public Graphics2D g2;
    public KL keyListner = new KL();
    public Text startGame, exitGame, title, creator;
    public ML mouseListener = new ML();
    public boolean isRunning = true;






    public MainMenu() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT); //makes the window size 800px x 600px
        this.setTitle(Constants.SCREEN_TITLE); //makes the title "Table Tennis"
        this.setResizable(false); //makes it so the user can't change the size
        this.setVisible(true); //makes the window visible
        this.setIconImage(new ImageIcon("https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.sciencenewsforstudents.org%2Fwp-content%2Fuploads%2F2019%2F11%2F860-sun-yellow-dwarf.gif&imgrefurl=https%3A%2F%2Fwww.sciencenewsforstudents.org%2Farticle%2Fscientists-say-yellow-dwarf&tbnid=4QIQ1GEspTY0zM&vet=12ahUKEwipnK3a8L71AhWEE80KHQQGC3EQMygTegUIARCxAQ..i&docid=2FaCD5XLGRfXhM&w=860&h=460&itg=1&q=sunshine%20and%20star&ved=2ahUKEwipnK3a8L71AhWEE80KHQQGC3EQMygTegUIARCxAQ").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when you click the x the window closes
        this.addKeyListener(keyListner);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.title = new Text("This is PONG", new Font("SansSerif", Font.BOLD, 100), Constants.SCREEN_WIDTH/2.0 - 320, Constants.SCREEN_HEIGHT/2 - 50, Color.WHITE);

        this.startGame = new Text("Start New Game", new Font("Times New Roman", Font.PLAIN,40), Constants.SCREEN_WIDTH/2.0 - 120, Constants.SCREEN_HEIGHT/2.0+130, Color.WHITE );
        this.exitGame = new Text("Exit Game", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH/2.0-75, Constants.SCREEN_WIDTH/2.0+100, Color.WHITE );

        g2 = (Graphics2D)this.getGraphics();

    }

    public void update(double deltaTime) {
        Image dblImage = createImage((getWidth()), getHeight());
        Graphics dbg = dblImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dblImage, 0, 0, this);

        if (mouseListener.getMouseX() > startGame.x && mouseListener.getMouseX() < startGame.x + startGame.width
        && mouseListener.getMouseY() > startGame.y - startGame.height/2&& mouseListener.getMouseY() < startGame.y + startGame.height / 2){
            startGame.color = new Color(125, 206, 206);

            if(mouseListener.isPressed()){
                tableTennis.changeState(1);
            }
        } else {
            startGame.color = Color.WHITE;
        }

        if (mouseListener.getMouseX() > exitGame.x && mouseListener.getMouseX() < exitGame.x + exitGame.width
                && mouseListener.getMouseY() > exitGame.y - exitGame.height/2&& mouseListener.getMouseY() < exitGame.y + exitGame.height / 2){
            exitGame.color = new Color(183, 31, 31);
            if(mouseListener.isPressed()){
                tableTennis.changeState(2);
            }

        } else {
            exitGame.color = Color.WHITE;
        }





    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        startGame.draw(g2);
        exitGame.draw(g2);
        title.draw(g2);


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

