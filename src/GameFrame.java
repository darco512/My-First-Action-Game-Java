import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class GameFrame extends JFrame {
    GamePanel panel;
    GameFrame(){
        this.setTitle("Mario Game");
        panel = new GamePanel();
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(panel.SCREEN_WIGHT,panel.SCREEN_HEIGHT));
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
}
