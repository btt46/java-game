import java.awt.*;
import javax.swing.*;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter; 
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.net.*;
 
public class Game implements IFGame  {
   JFrame frame = new JFrame();
   static int x = 420, velX = 0;
   static int score = 0;
   JLabel l;
   String str = "bomb.png";
   static int x1 = (int) (Math.random() * 400 - 50);
   static int y1;
   int i = 0;
   static boolean gOver = false;
   PlayMIDI ms = new PlayMIDI();
   public static void main(String[] args) {
       Game gui = new Game();
       gui.go();
   }
 
   public void go() {
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       JButton sb = new JButton(new ImageIcon("startButton.jpg"));
       sb.setBounds(400, 250, 200, 90);
       sb.addActionListener(new startButton());
       BackGround bgrd = new BackGround();
       frame.getContentPane().add(BorderLayout.CENTER, sb);
       frame.getContentPane().add(BorderLayout.CENTER, bgrd);
       frame.setSize(1000, 600);
       frame.setVisible(true);
   }
 
   public void play() {
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // play music after user starts game
       ms.setup();
       Character chr = new Character("45884.jpg","chr.jpg", 6);
       frame.getContentPane().add(BorderLayout.CENTER, chr);
       frame.setSize(1000, 600);
       frame.setVisible(true);
 
   }
 
   class startButton implements ActionListener {
       public void actionPerformed(ActionEvent event) {
           frame = new JFrame();
           play();
       }
   }
 
   public class Character extends GameCharacter {
       public Character(String a, String b, int c) {
           bg = a;
           cr = b;
           v = c;
           tm.start();
           addKeyListener(this);
           setFocusable(true);
           setFocusTraversalKeysEnabled(false);
       }
 
       public void paintComponent(Graphics g) {
           super.paintComponent(g);
           Image image1 = new ImageIcon(charac()).getImage();
           g.drawImage(image1, x, 380, 150, 150, this);
           Image image2 = new ImageIcon(str).getImage();
           g.drawImage(image2, x1, y1, 50, 50, this);
           g.drawString("Score: " + String.valueOf(score), 450, 20);
           if (gOver == true) {
               Font font2 = new Font("ＭＳ Ｐゴシック", Font.PLAIN, 80);
               g.setFont(font2);
               g.drawString("Game Over", 300, 150);
           }
       }
 
       public void actionPerformed(ActionEvent event) {
           if (gOver == false) {
               x += velX;
               if (y1 <= 450)
                   y1 += 10;
               if (y1 == 430)
                   str = "explosion.png";
               if (y1 == 440) {
                   str = "";
               }
               if (y1 == 450) {
                   x1 = (int) (Math.random() * 950 - 50);
                   str = "bomb.png";
                   y1 = 0;
                   score += 1;
               }
               System.out.println(x);
               System.out.println(x1);
               System.out.println("-----");
               if (x1 >= x + 18 && y1 > 380 && x1 <= x + 65) {
                   gOver = true;
                   Thread readerThread = new Thread(new ScoreSave());
                   readerThread.start();
                   tm.stop();
               }
               if (x < -40)
                   x = -40;
               if (x > 890)
                   x = 890;
               frame.repaint();
           }
       }
 
       public void keyPressed(KeyEvent e) {
           int c = e.getKeyCode();
           if (c == KeyEvent.VK_LEFT && gOver == false) {
               velX = - vel();
           }
           if (c == KeyEvent.VK_RIGHT && gOver == false) {
               velX = vel();
           }
       }
   }
 
   class BackGround extends JPanel {
 
       public void paintComponent(Graphics g) {
           Image image = new ImageIcon("45884.jpg").getImage();
           g.drawImage(image, 0, 0, 1000, 600, this);
       }
   }
 
   class ScoreSave implements Runnable {
       public void run() {
           try {
               /*
               Socket s = new Socket("localhost", 1234);
               DataOutputStream data = new DataOutputStream(s.getOutputStream());
               */
               LocalDateTime now = LocalDateTime.now();
               DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
               String formatDateTime = now.format(format);
               File file = new File("Score.txt");
               FileWriter fr = new FileWriter(file, true);
               BufferedWriter br = new BufferedWriter(fr);
 
               br.write(String.valueOf("Score: " + score + " at " + formatDateTime + "\n"));
               /*
               data.writeUTF(String.valueOf("Score: " + score + " at " + formatDateTime + "\n"));
               data.flush();
               data.close();*/
 
               br.close();
               fr.close();
           } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
           }
       }
   }  
 
 
 
}
