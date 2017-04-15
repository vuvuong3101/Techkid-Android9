import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by admin on 4/13/17.
 */
public class GameWindow extends Frame{
    Image backgroundImage;

    private int screenW = 600;
    private int screenH = 700;
    boolean isUpPressed;
    boolean isDownPressed;
    boolean isLeftPressed;
    boolean isRightPressed;
    boolean isSpacePressed;

    Player player;
    Enemy enemy;
    Image loadImage(String path) {
        try {
            return ImageIO.read(new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    BufferedImage backbufferedImage;
    Graphics backBufferedGapphics;

    public GameWindow() {
        // Set Game Window
        setVisible(true);
        setSize(screenW, screenH);
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
                }if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isSpacePressed = true;
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
                }if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isSpacePressed = false;
                }

            }
        });
        backbufferedImage = new BufferedImage(screenW, screenH, BufferedImage.TYPE_INT_ARGB);
        backBufferedGapphics = backbufferedImage.getGraphics();

        player = new Player(370, 530, loadImage("res/plane2.png") );
        enemy = new Enemy(370, 30, loadImage("res/enemy_plane_white_3.png"));

        // load image
        backgroundImage = loadImage("res/background.png");

        // Game loop
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //// LOGIC
                    player.updateBullet();
                    enemy.automove();
                    enemy.autoshoot();
                    // update vi tri player
                    player.processInput(isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isSpacePressed);
                    enemy.updateEbullet();
                    player.update();
                    repaint();
                }
            }
        });

        // repaint
        thread.start();
    }

    @Override
    public void update(Graphics g) {
        backBufferedGapphics.drawImage(backgroundImage, 0, 0 , screenW, screenH, null);
        player.draw(backBufferedGapphics);
        player.drawBullet(backBufferedGapphics);
        enemy.draw(backBufferedGapphics);
        enemy.draw_Ebullet(backBufferedGapphics);


        g.drawImage(backbufferedImage, 0, 0, null);

    }
}
