import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public class Player extends Play {
    private double health;
    private int damage;
    private double xPos, yPos;
    private double xVel, yVel;
    private Image player;
    private Shape playerHB;

    Player(String path) throws SlickException {
        health = 100;
        damage = 10;
        xPos = 0;
        yPos = 0;
        xVel = 1;
        yVel = 1;  
        player = new Image(path);
        playerHB = new Ellipse((int)xPos, (int)yPos, 16, 16);
    }
    
    public void setImage(Image image) {
        player = image;
    }
    
    public Shape getHB() {
        return playerHB;
    }
    
    public void setHB(Shape hitbox) {
        playerHB = hitbox;
    }
    
    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getX() {
        return xPos;
    }

    public void setX(double xPos) {
        this.xPos = xPos;
    }

    public double getY() {
        return yPos;
    }

    public void setY (double yPos) {
        this.yPos = yPos;
    }

    public double getxVel() {
        return xVel;
    }

    public Image getImage() {
        return player;
    }
    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public double getyVel() {
        return yVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }
}
