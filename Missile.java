import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public class Missile {
    private Image missile;
    private double xPos;
    private double yPos;
    private Shape missileHB;
    
    Missile(String path) throws SlickException {
        missile = new Image(path);
        xPos = 0;
        yPos = 0;
        missileHB = new Ellipse((int)xPos, (int)yPos, 7, 15);
    }
    
    Missile(Missile copy) {
        xPos = copy.getX();
        yPos = copy.getY();
        missile = copy.getImage();
        missileHB = copy.getHB();
    }
    
    public Shape getHB() {
        return missileHB;
    }
    
    public void setHB(Shape hitbox) {
        missileHB = hitbox;
    }
    
    public double getX() {
        return xPos;
    }
    
    public double getY() {
        return yPos;
    }
    
    public void setX(double x) {
        this.xPos = x;
    }
    
    public void setY(double y) {
        this.yPos = y;
    }
    
    public Image getImage() {
        return missile;
    }
}
