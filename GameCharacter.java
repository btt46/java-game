import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameCharacter extends JPanel implements ActionListener, KeyListener,IFcharacter  {
    String bg;
    String cr;
    Timer tm = new Timer(5, this);
    int v;
    public String backGround() {
        return bg;
    }
    public String charac() {
        return cr;
    }
    public int vel() {
        return v;
    }


    public void paintComponent(Graphics g) {
            Image image = new ImageIcon(backGround()).getImage();
            g.drawImage(image, 0, 0, 1000, 600, this); 
            Font font1 = new Font("ＭＳ Ｐゴシック", Font.PLAIN, 20);
            g.setFont(font1);
    }

    public void keyTyped(KeyEvent e) {};
    public void keyPressed(KeyEvent e) {};
    public void keyReleased(KeyEvent e) {};
    public void actionPerformed(ActionEvent e) {};
}