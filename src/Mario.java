import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Mario extends Rectangle {
    int xVelocity;
    int speed = 5;
    double fallenSpeed=0;
    double gravity = 0.5;
    double jumpPower = -10;
    Image mario;
    GamePanel panel;
    Mario(int x, int y, int WIDHT, int HIGHT){
        super(x,y,WIDHT,HIGHT);
        mario = new ImageIcon("mario.png").getImage();
    }
    /*public void keyPressed(KeyEvent e) {

                if(e.getKeyCode()== KeyEvent.VK_A) {
                   setXDerection(-speed);
                    move();
                    System.out.println("KEY PRESSED");
                }
                if(e.getKeyCode()== KeyEvent.VK_D) {
                    setXDerection(speed);
                    move();
                    System.out.println("KEY Released");
                }



    }
    public void keyReleased(KeyEvent e){
                if(e.getKeyCode()== KeyEvent.VK_A) {
                    setXDerection(0);
                    move();
                    System.out.println("KEY RELEASED");
                }
                if(e.getKeyCode()== KeyEvent.VK_D) {
                    setXDerection(0);
                    move();
                    System.out.println("KEY RELEASED");
                }
    }*/
    public void setXDerection(int xDirection) {
        xVelocity = xDirection;
    }
    public void jump(){
    fallenSpeed = jumpPower;
    fall();
    }
    public void move() {
        x= x + xVelocity;
    }
    public void fall() {
        y = y +(int)fallenSpeed;
        fallenSpeed +=gravity;
    }
    public void draw(Graphics g) {
        g.drawImage(mario, x, y, null);
    }



}
