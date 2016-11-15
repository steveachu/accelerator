import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Options extends Play {
    static int gameMode = 700;
    static Boolean easy = false;
    static Boolean normal = false;
    static Boolean hard = false;
	
    public int getID() {
        return 2;
    }
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.drawString("How to Play:", 100, 20);
        g.drawString("Arrows to move spaceship.", 100, 40);
        g.drawString("'A' to shoot missles.", 100, 60);
        g.drawString("Destroy all alien ships!", 100, 80);
        g.drawString("Select Difficulty", 100, 200); 
        g.drawString("E FOR EASY MODE", 100, 220);
        g.drawString("N FOR NORMAL MODE", 100, 240);
        g.drawString("H FOR HARD MODE", 100, 260);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_E)) {
            System.out.println("From options easy");
            Play.setEasy();
            sbg.enterState(0);
        }
        
        else if (gc.getInput().isKeyPressed(Input.KEY_N)) {
            System.out.println("From options norm");
            Play.setNormal();
            sbg.enterState(0);
        }
        
        else if (gc.getInput().isKeyPressed(Input.KEY_H)) {
            System.out.println("From options hard");
            Play.setHard();
            sbg.enterState(0);
        }
        else {
            Play.setNormal();
            System.out.println("Default from options");
        }
    }
}
