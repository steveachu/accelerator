import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Pause extends BasicGameState {
    public int getID() {
        return 3;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_R)) {
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
        
        if (gc.getInput().isKeyPressed(Input.KEY_Q)) {
        	Play.updatePlay();
            sbg.enterState(0);
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.drawString("PAUSED", 100, 250);
        g.drawString("R TO RESUME", 100, 300);
        g.drawString("Q TO QUIT", 100, 350);
    }
}
