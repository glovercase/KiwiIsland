package KiwiIsland;

/**
 * @author Team Taniwha
 * @version 1.0
 * 
 */
public class Game {
    // DATA FIELDS
    public static final int WIDTH     = 640;
    public static final int HEIGHT    = 640;
    public static final int GRID_SIZE = 10;
    public static final int TILE_SIZE = WIDTH/GRID_SIZE;

    public static GameState state;
    public World  world;
    
    
    /**
     * 
     * @param listeners 
     */
    public Game(GameListenerMap listeners) {
        this.world = new World(listeners);
        this.state = GameState.PlayerSelect;
    }
    
    /**
     * 
     * @return 
     */
    public GameState getGameState(){
        return state;
    }
}