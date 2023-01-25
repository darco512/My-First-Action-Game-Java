import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GamePanel extends JPanel implements Runnable {
    final int SCREEN_WIGHT = 815;
    final int SCREEN_HEIGHT = 730;
    final int MAP_WIGHT = 11200;
    final int MAP_HEIGHT = 700;
    final int FLOOR_SIZE = 50;
    int marioX = 50;
    int marionY = -50;
    int speed = 5;
    int cameraPosition = 0;
    Mario mario;
    Thread gameThread;
    Image backgroundImage;
    Image floorImg;
    Rectangle[] floors = new Rectangle[MAP_WIGHT/FLOOR_SIZE];

    GamePanel() {
        newMario();
        backgroundImage = new ImageIcon("background.png").getImage();
        floorImg = new ImageIcon("floor.png").getImage();
       // mario = new ImageIcon("mario.png");
        this.setFocusable(true);
        this.addKeyListener(new AL());
        gameThread = new Thread(this);
        gameThread.start();
        
    }
    public void newMario(){
        marionY=0;
        marioX = 50;
        mario = new Mario(marioX,marionY,35,50);
    }
    public int camera(){

        if (marioX>=(-1*cameraPosition)+(SCREEN_WIGHT/2)){
        cameraPosition = (-1*marioX)+(SCREEN_WIGHT/2);
        }
        else
            cameraPosition=cameraPosition;

        return cameraPosition;
    }
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(backgroundImage, camera(), 0,MAP_WIGHT,MAP_HEIGHT,null);
        g2D.translate(camera(),0);
        //g2D.drawImage(mario, marioX, marionY, null);
        for (int i = 0; i < floors.length; i ++) {
            if(i==7|| i==8)
                continue;
            else
                g2D.drawImage(floorImg, floors[i].x, floors[i].y, null);
        }
        for (int i = 0; i < floors.length; i ++) {
            if(i==7|| i==8)
                continue;
            else
            g2D.drawImage(floorImg, floors[i].x, MAP_HEIGHT-30, null);
        }
        draw(g);
    }
    public void floor() {
        for (int i = 0; i < floors.length; i++) {
            floors[i] = new Rectangle((i * FLOOR_SIZE), (MAP_HEIGHT - FLOOR_SIZE)-30, FLOOR_SIZE, FLOOR_SIZE);
        }
    }
    public void collision(){
        if(marioX <= (-1*cameraPosition))
            mario.x=(-1*cameraPosition);


    }
    public void draw(Graphics g) {
        mario.draw(g);
    }
    public void move(){
        mario.move();
    }
    public void jump(){
            mario.jump();
    }
    public void fall(){
    mario.fall();
    }
    public boolean checkGround(){
        for (int i = 0; i <floors.length ; i++) {
            if (mario.intersects(floors[i])&& i!=7&& i!=8) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1_000_000_000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                collision();
                if (marionY<0||marionY>MAP_HEIGHT)
                    newMario();
                floor();
                move();
                marionY=mario.y;
                marioX=mario.x;
                if (!checkGround()){
                  fall();
                }
                else {
                    mario.y = marionY;
                }
                //checkCollision();
                repaint();
                delta--;
            }
        }

    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
             if(e.getKeyCode()== KeyEvent.VK_A) {
                mario.setXDerection(-speed);
                move();
                System.out.println("KEY PRESSED");
            }
            if(e.getKeyCode()== KeyEvent.VK_D) {
                mario.setXDerection(speed);
                move();
                System.out.println("KEY Released");
            }
            if(e.getKeyCode()== KeyEvent.VK_W) {
                if (checkGround()) {
                    jump();
                }
            }
        }
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode()== KeyEvent.VK_A) {
                mario.setXDerection(0);
                move();
                System.out.println("KEY RELEASED");
            }
            if(e.getKeyCode()== KeyEvent.VK_D) {
                mario.setXDerection(0);
                move();
                System.out.println("KEY RELEASED");
            }

        }
    }
}


