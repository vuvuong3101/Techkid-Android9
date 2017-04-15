import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 4/13/17.
 */
public class Enemy {
    private int x;
    private int y;
    private int w;
    private int h;
    private int dx;
    private int dx2;
    private Image image;
    private boolean isStatusLeft;
    private boolean isStatusRight;
    private boolean shootEnable;
    Image loadImage(String path) {
        try {
            return ImageIO.read(new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    ArrayList<Ebullet> Ebullets;
    public Enemy(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.w = 50;
        this.h = 50;
        this.image = image;
        isStatusLeft = true;
        shootEnable = true;
        Ebullets = new ArrayList<>();
    }
    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, w, h, null);
    }
    public  void automove(){
        dx = 5;
        dx2 = -5;

        if (isStatusLeft) {
            this.x += dx2;
            if (this.x == 0) {
                isStatusLeft = false;
                isStatusRight = true;
            }
        }
        if (isStatusRight) {
            this.x += dx;
            if (this.x == 550) {
                isStatusRight = false;
                isStatusLeft = true;
            }
        }
    }
    int coolDownTime;
    public  void autoshoot() {
        coolDownTime --;
        if (shootEnable) {
            Ebullet enemy_bullet = new Ebullet(x + w / 2, y, loadImage("res/enemy_bullet.png"));
            Ebullets.add(enemy_bullet);
            shootEnable = false;
            coolDownTime = 25;
        }
        if (coolDownTime == 0){
            shootEnable = true;
        }
    }
    public void draw_Ebullet(Graphics graphics) {
        for ( Ebullet ebullet: Ebullets) {
            ebullet.draw(graphics);
        }
    }
    public  void updateEbullet() {
        for ( Ebullet ebullet: Ebullets) {
            ebullet.update();
        }
    }

}
