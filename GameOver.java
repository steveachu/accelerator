import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOver extends Play {
    publoc int getID() {
        return 4;
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    	Play.updatePlay();
    	
        if (gc.getInput().isKeyPressed(Input.KEY_Y)) {
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());            
        }
        
        if (gc.getInput().isKeyPressed(Input.KEY_N)) {
            sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.drawString("GAME OVER!", 50, 100);
        g.drawString("Your final score was " + finalScore, 50, 140);
        g.drawString("Try again? (Y/N)", 50, 160);
    }
}
