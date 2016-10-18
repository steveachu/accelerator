import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Play extends BasicGameState {
	protected static ArrayList<Enemy> enemies;
	protected static ArrayList<Missile> shots;
	protected static Player player;
	protected static Options options;
	protected int timePassed = 0;
	protected int shotDelay = 0;
	protected Random rng = new Random();
	public static int score = 0;
	public static int spawnTime = Options.gameMode;
	static double missleSpeed = 2;
	static double enemySpeed = 1;
	static int timedShotDelay = 400;
	
	static int finalScore;

	private Music music;

	protected Image playerRight, playerLeft, playerUp;
	protected Shape enemyHB;
	protected Shape playerHB;
	protected Shape missileHB;
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		enemies = new ArrayList<Enemy>();
		shots = new  ArrayList<Missile>();
		player = new Player("player.png");
		playerRight = new Image("player_right.png");
		playerLeft = new Image("player_left.png");
		playerUp = new Image("player.png");
		//music = new Music("music.ogg");
		bgArray = new Image[3];
		bgArray[0] = new Image("background.jpg");
		bgArray[1] = new Image("background.jpg");
		bgArray[2] = new Image("background.jpg");

		playerHB = new Ellipse((int)player.getX(), (int)player.getY(), 16, 16);

		player.setX(175);
		player.setY(400);
		music.play();
	}

	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

		// Game menu states    

		if (gc.getInput().isKeyPressed(Input.KEY_P)) {
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
		}

		// Player control detection and movement
		if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
			player.setImage(playerUp);
			player.setX(player.getX() + player.getxVel());
		}

		if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
			player.setImage(playerUp);
			player.setX(player.getX() - player.getxVel());
		}

		if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
			player.setImage(playerUp);
			player.setY(player.getY() + player.getyVel());
		}

		if (gc.getInput().isKeyDown(Input.KEY_UP)) {
			player.setImage(playerUp);
			player.setY(player.getY() - player.getyVel());
		}

		// Note: i is a parameter of the method. It is a time counter.
		shotDelay += i;
		if (gc.getInput().isKeyPressed(Input.KEY_A)) {            
                    if (shotDelay > timedShotDelay) {
                        shotDelay = 0;

                        shootEnemy();
                    }
		}

		// Set the player's hitbox.
		playerHB.setLocation((float)player.getX(), (float)player.getY());

		// Increment the timer, used to add time between enemy spawns
		timePassed += i;
		
		
		
		if (timePassed > spawnTime) {
			timePassed = 0;
			addEnemy();
		}
                   
                // ArrayList containing the enemy ships 
		for (Enemy e : enemies) {
			e.setY(e.getY() + enemySpeed);
		}
                
                // ArrayList containing the missiles from the player
		for (Missile m : shots) {
			m.setY(m.getY() - missleSpeed);
		}
                
                // While there are enemies in the ArrayList, slowly dro pthem
                // from the top of the screen.
                
                // Update the enemy location and detect for collision between
                // hitboxes
                
                // TODO: Reimplement this using a quadtree for better efficiency
		for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext();) {
                    Enemy curEnemy = enemyIterator.next();
                    enemyHB.setLocation((float)curEnemy.getX(), (float)curEnemy.getY());

                    if (enemyHB.intersects(playerHB)) 
                    {
                        score -= 200;

                        player.setHealth(player.getHealth() - 20);
                        if (player.getHealth() <= 0) 
                        {
                                sbg.enterState(4);//4
                                finalScore = score;
                        }

                        enemyIterator.remove();
                    }

                    for (Iterator<Missile> missileIterator = shots.iterator(); missileIterator.hasNext();) {

                        Missile curMissile = missileIterator.next();

                        // Set hitbox for the enemy and player
                        missileHB.setLocation((float)curMissile.getX(), (float)curMissile.getY());

                        // Remove missile if it leaves the top of the screen
                        if (curMissile.getY() < 0) {
                                missileIterator.remove();
                        }          

                        // Player missile hit the enemy, remove both objects
                        if (missileHB.intersects(enemyHB)) {
                                enemyIterator.remove();
                                missileIterator.remove();
                                score += 100;
                        }
                    }
		}

		// Delete enemies if they hit the bottom
		for (int count = enemies.size() - 1; count >= 0; count--) {
                    Enemy e = enemies.get(count);
                    if (e.getY() > 500 || e.getHealth() < 0) {
                            enemies.remove(count);
                    }
		}

		// Check the background scrolling image
                // This is for infinite background scrolling
                // TODO: Remove magic numbers
		if (countX < 350) {
                    countX += .2;
		}
		else {
                    countX = -350;
		}

		if (countY < 500) {
                    countY += .2;
		}
		else {
                    countY = -500;
		}
	}

	protected Image[] bgArray;
	private float countX = 0, countY = 0;
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		int bgCounter = -1;
		for (Image bg : bgArray) {
                    g.drawImage(bg, 0, bgCounter * 500 + countY);
                    bgCounter++;
		}

		player.getImage().draw((int)player.getX(), (int)player.getY());

		for (Missile m : shots) {
                    m.getImage().draw((int)m.getX(), (int)m.getY());
		}

		for (Enemy e: enemies) {
                    e.getImage().draw((int)e.getX(), (int)e.getY());
		}

		g.drawString("Score: " + score, 30, 30);
		g.drawString("Health: " + (int)player.getHealth(), 50, 50);
	}

	public void addEnemy() throws SlickException {
            Enemy e = new Enemy("alien.png");
            e.setX(rng.nextInt(400));
            enemyHB = new Ellipse((int)e.getX() + 10, (int)e.getY(), 16, 16);
            enemies.add(e);
	}

	public void shootEnemy() throws SlickException {
            Missile m = new Missile("missile.png");
            m.setX(player.getX());
            m.setY(player.getY());
            missileHB = new Ellipse((int)m.getX(), (int)m.getY(), 5, 5);;
            shots.add(m);
	}

	public int getID() {
            return 1;
	}

	public static void updatePlay(){
            player.setX(175);
            player.setY(400);
            enemies = new ArrayList<Enemy>();
            shots = new  ArrayList<Missile>();
            player.setHealth(100);
            score = 0;
		
	}
	public static void updateDifficulty(){
            if(Options.easy = true){
                    spawnTime = 2000;
                    System.out.println("spawnTimer in easy mode.");
            }

            else if(Options.normal = true){
                    spawnTime = 700;
                    System.out.println("spawnTimer in normal mode.");
            }

            else if(Options.hard = true){
                    spawnTime = 1;
                    System.out.println("spawnTimer in hard mode.");
            }

            else{
                    spawnTime = 700;
                    System.out.println("spawnTimer in normal mode.");
            }
	}
	
        // Difficulty setting changes
        // TODO: Remove magic numbers
	public static void setEasy(){
            spawnTime = 1600;
            missleSpeed = 1.5;
            enemySpeed = .5;
            timedShotDelay = 500;
		
	}
	
	public static void setNormal(){
            spawnTime = 700;
            missleSpeed = 2.0;
            enemySpeed = 1;
            timedShotDelay = 400;
	}
        
	public static void setHard(){
            spawnTime = 300;
            missleSpeed = 2.0;
            enemySpeed = 1.25;
            timedShotDelay = 300;
	}
}