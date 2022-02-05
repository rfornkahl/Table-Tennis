package tableTennis;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class ML extends MouseAdapter implements MouseMotionListener {

    public boolean isPressed = false;
    public double x = 0.0, y= 0.0;

    @Override
    public void mousePressed(MouseEvent event){
        isPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent event){
        isPressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent event){
        this.x = event.getX();
        this.y = event.getY();
    }

    public double getMouseX(){
        return this.x;
    }

    public double getMouseY(){
        return this.y;
    }

    public boolean isPressed(){
        return this.isPressed;
    }
}
