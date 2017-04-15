import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 4/13/17.
 */
public class Player {
    private  int x;
    private  int y;
    private  int w;
    private  int h;
    private Image image;
    private int dx;
    private  int dy;
    private int screenW = 600;
    private int screenH = 700;
    private boolean shootEnable;
    Image loadImage(String path) {
        try {
            return ImageIO.read(new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    ArrayList<Bullet> bullets;

    public Player(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.w = 70;
        this.h = 60;
        this.image = image;
        shootEnable = true;
        bullets = new ArrayList<>();
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public  void updateBullet() {
        for (Bullet bullet: bullets) {
            bullet.update();
        }
    }
    public  void drawBullet(Graphics graphics){
        for (Bullet bullet: bullets) {
            bullet.draw(graphics);
        }
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
    }

    public  void processInput(boolean isUpPressed,
                              boolean isDownPressed,
                              boolean isLeftPressed,
                              boolean isRightPressed,
                              boolean isSpacePressed){
        dx = 0;
        dy = 0;
        if (isUpPressed) {
            if (y > 20) {
                dy -= 10;
            }
        }
        if (isDownPressed) {
            if (y <= screenH - h) {
                dy += 10;
            }
        }
        if (isLeftPressed) {
            if (x >= 0) {
                dx -= 10;
            }
        }
        if (isRightPressed) {
            if (x <= screenW - w) {
                dx += 10;
            }
        }
        if (isSpacePressed) {
            if (shootEnable) {
                Bullet bullet = new Bullet(x + w / 2, y, loadImage("res/bullet.png"));
                bullets.add(bullet);
                shootEnable = false;
                coolDownTime = 15;
            }

        }

    }
    int coolDownTime;
    public  void update() {
        this.x += dx;
        this.y += dy;
        if  (!shootEnable) {
            coolDownTime --;
            if (coolDownTime == 0) {
                shootEnable = true;
            }
        }
    }
}
