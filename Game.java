import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import java.awt.Window;

public class Game extends StateBasedGame {
    public static int lives = 3;

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game("Accelerando"));
        app.setDisplayMode(350, 500, false);
        app.start();
    }
    
    public Game(String name) {
        super(name);
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new StartMenu());
        addState(new GameOver());
        addState(new Options());
        addState(new Play());
        addState(new Pause());  
    }
}
