import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 4/11/17.
 */
public class GameWindow  extends Frame{
    Image backgroundImage;



//    private int planeW = 70;
//    private int planeH = 60;
    private int screenW = 600;
    private int screenH = 700;

//    private int planeX = screenW/2 - planeW/2;
//    private int planeY = screenH - planeH;


    boolean isUpPressed;
    boolean isDownPressed;
    boolean isLeftPressed;
    boolean isRightPressed;
    boolean isSpacepressed;

    // bullet
    ArrayList<Bullet> bullets;
    Player plane ;


    //
    BufferedImage backBufferImage;
    Graphics backBufferGraphics;


    // create Game Window

    public GameWindow() {

        setVisible(true);
        setSize(screenW, screenH);
        backBufferImage = new BufferedImage(screenW, screenH, BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics = backBufferImage.getGraphics();

        plane.setBullets(bullets);

        plane = new Player(370, 530,loadImage("res/plane3.png") );
        // listener
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    isLeftPressed = true;
                }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    isRightPressed = true;
                }else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    isUpPressed = true;
                }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    isDownPressed = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isSpacepressed = true;
                }




            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    isLeftPressed = false;
                }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    isRightPressed =false;
                }else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    isUpPressed = false;
                }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    isDownPressed = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isSpacepressed = false;
                }
            }
        });

        // load image
        backgroundImage = loadImage("res/background.png");

        // GAme loop
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //logic
                    plane.processInput(isUpPressed, isDownPressed, isLeftPressed, isRightPressed,isSpacepressed);

//                    if (isSpacepressed) {
//                        Bullet bullet = new Bullet(plane.getX() + plane.getW()/2, plane.getY(), loadImage("res/bullet.png"));
//                        bullets.add(bullet);
//                    }
                    for (Bullet bullet : bullets) {
                        bullet.update();
                    }


                    plane.update();
                    repaint();
                }
            }
        });
        thread.start();
    }
     Image loadImage(String path) {
         try {
             return ImageIO.read(new File(path));

         } catch (IOException e) {
             e.printStackTrace();
         }
         return null;
     }
    @Override
    public void update(Graphics g) {
        // draw on backbuffer
        backBufferGraphics.drawImage(backgroundImage,0 , 0,screenW , screenH,null);
        plane.draw(backBufferGraphics);
        for (Bullet bullet : bullets) {
            bullet.draw(backBufferGraphics);
        }

        // draw on Game window
        g.drawImage(backBufferImage, 0 , 0, null);
    }
}
