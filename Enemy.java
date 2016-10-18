import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public class Enemy {
    private int health;
    private int damage;
    private double xPos;
    private double yPos;
    private Image enemy;
    private Shape enemyHB; 
    
    public Enemy(String path) throws SlickException {
        health = 10;
        damage = 10;
        xPos = 0.0;
        yPos = 0.0;
        enemy = new Image(path);
        enemyHB = new Ellipse((int)xPos, (int)yPos, 16, 16);
    }
    
    public Shape getHB() {
        return enemyHB;
    }
    
    public void setHB(Shape hitbox) {
        enemyHB = hitbox;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int newHP) {
        health = newHP;//changed for setting health normal
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    public Image getImage() {
        return enemy;
    }
    
    public double getY() {
        return yPos;
    }
    
    public double getX() { 
        return xPos;
    }
    
    public void setY(double y) {
        this.yPos = y;
    }
    
    public void setX(double x) {
        this.xPos = x;
    }
}
