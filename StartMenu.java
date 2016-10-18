import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StartMenu extends BasicGameState {
    private Image[] bgArray;
    private float countX = 0;
    
    public int getID() 
    {
        return 0;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        bgArray = new Image[3];
        bgArray[0] = new Image("start_background.png");
        bgArray[1] = new Image("start_background.png");
        bgArray[2] = new Image("start_background.png");
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        // Enter the game state (1)
        if (gc.getInput().isKeyPressed(Input.KEY_1)) {
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }

        // Enter the option state (2)
        if (gc.getInput().isKeyPressed(Input.KEY_2)) {
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        
        // Parallax scrolling for the main menu background
        if (countX < 350) {
            countX += .05;
        }
        else {
            countX = -350;
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        // For loop to scroll backgrounds at different speeds
        int bgCounter = -1;
        for (Image bg : bgArray) {
            g.drawImage(bg, bgCounter * 350 + countX, 0);
            bgCounter++;
        }
        
        g.setColor(Color.black);
        g.fillRect(25, 95, 140, 90);
        g.setColor(Color.blue);
        g.fillRect(35, 85, 140, 90);
        g.setColor(Color.white);
        g.drawString("Accelerator", 50, 100);
        g.drawString("1. Play", 50, 120);
        g.drawString("2. Options", 50, 140);
    } 
}
