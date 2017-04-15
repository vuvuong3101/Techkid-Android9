import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 4/12/17.
 */

public class Player {

    private int w;
    private int h;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private Image image;
    private boolean shootEnable;
    ArrayList<Bullet> bullets;
    GameWindow gameWindow = new GameWindow();


    public Player(int x, int y, Image image){
        this.x = x;
        this.y = y;
        this.w = 70;
        this.h = 60;
        this.dx = 0;
        this.dy = 0;
        this.image = image;
        this.shootEnable = true;
        bullets = new ArrayList<>();
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    Image loadImage(String path) {
        try {
            return ImageIO.read(new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getX() {
        return x;
    }
    public  int getY() {
        return y;

    }
    public  int getW() {
        return w;
    }
    public  int getH() {
        return  h;
    }
    public  Image getImage() {
        return image;
    }
    public void draw(Graphics graphics){
        graphics.drawImage(this.image, this.x, this.y, null);
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public  void processInput(boolean isUpPressed,
                              boolean isDownPressed,
                              boolean isLeftPressed,
                              boolean isRightPressed,
                              boolean isSpacePressed) {
        dx = 0;
        dy = 0;
        if (isUpPressed) {
            if (this.y > 20) {
                dy -= 8;
            }
        }
        if (isDownPressed) {
            if (this.y <= 700 - this.h){
                dy += 8;
            }
        }
        if (isLeftPressed) {
            if (this.x >= 0){
                dx -= 8;
            }
        }
        if (isRightPressed) {
            if (this.x <= 600 - this.w) {
                dx += 8;
            }
        }
        if (isSpacePressed) {
            if (shootEnable){
                Bullet bullet = new Bullet(x + w/2, y, loadImage("res/bullet.png"));
                bullets.add(bullet);
                shootEnable = false;
                cooltime = 10;
            }
        }
    }
    int cooltime;
    public  void update() {
//        for (Bullet bullet : bullets) {
//            bullet.update();
//        }
        this.x += dx;
        this.y += dy;
        if (!shootEnable) {
            cooltime --;
            if (cooltime <= 0){
                shootEnable =true;
            }
        }
    }
//    public void drawbuleet() {
//        for (Bullet bullet : bullets) {
//            bullet.draw(gameWindow.backBufferGraphics);
//        }
//    }
}
