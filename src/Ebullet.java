import java.awt.*;

/**
 * Created by admin on 4/13/17.
 */
public class Ebullet {
    private int x;
    private int y;
    private Image image;

    public Ebullet(int x, int y, Image image) {
        this.x = x - image.getWidth(null)/2;
        this.y = y + image.getHeight(null);
        this.image = image;
    }
    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
    }
    public  void update(){
        this.y += 9;
    }


}
