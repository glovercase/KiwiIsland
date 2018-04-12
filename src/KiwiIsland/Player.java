package KiwiIsland;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 * @author Team Taniwha
 * @version 1.0
 * 
 */
public class Player extends GameObject {
    
    // How fast the player will move in the x or y direction.
    private final int velocity = 4;
    
    // gridX and gridY correspond to indices in the world grid.
    private int gridX, gridY;
    
    // worldX and worldY correspond to absolute x and y positions on the frame.
    private int worldX, worldY;
    
    private String name;
    private final double maxStamina;
    private double stamina;
    private boolean alive;
    
    private String message;
    
    private HashMap<String,Item> backpack;
    
    private final double maxBackpackWeight;
    private final double maxBackpackSize;
    
    public static final double MOVE_STAMINA = 1.0;
    
    private Animation rightAnimation;
    private Animation leftAnimation;
    private Animation upAnimation;
    private Animation downAnimation;
    
    private Animation curAnimation;
    
    private Animation deathAnimation;
    
    private Direction direction;
    private boolean   moving;
    private Tile      currentTile;
    
    private BufferedImage spriteSheet;
    private GameListenerMap listeners;
    public Level currentLevel;
    
    /**
     * 
     * @param name
     * @param listeners
     * @param maxStamina
     * @param maxBackpackWeight
     * @param maxBackpackSize
     * @param currentLevel 
     */
    public Player(String name, GameListenerMap listeners, double maxStamina, double maxBackpackWeight, double maxBackpackSize, Level currentLevel) {
        try {
            this.spriteSheet = ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/boy_sprite.png"));
            
            SpriteSheet death = new SpriteSheet(ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/die_boy.png")), 64, 64);
            this.deathAnimation = new Animation(death.getRow(0, 11), 80, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        this.name = name;
        this.maxStamina = maxStamina;
        this.stamina = maxStamina;
        this.maxBackpackSize = maxBackpackSize;
        this.maxBackpackWeight = maxBackpackWeight;
        this.alive = true;
        this.backpack = new HashMap<String,Item>();
        
        resetAnimations();
        
        // Set the current position to the middle of the first box.
        this.worldX = getMiddle(gridX);
        this.worldY = getMiddle(gridY);
        
        this.listeners = listeners;
        this.currentLevel = currentLevel;
        this.currentTile = currentLevel.getTileAt(0, 0);
    }

    Player(String name, double maxStamina, double maxBackpackWeight, double maxBackpackSize) {
        this.name = name;
        this.maxStamina = maxStamina;
        this.stamina = maxStamina;
        this.maxBackpackSize = maxBackpackSize;
        this.maxBackpackWeight = maxBackpackWeight;
    }
    
    /**
     * 
     */
    public void update() {
        if (!moving) {
            return;
        }
        
        switch (direction) {
            case UP:
                curAnimation = upAnimation;
                curAnimation.start();
                
                // Push the character upwards.
                worldY -= velocity;
                
                if (worldY <= getMiddle(gridY-1)) {
                    moving = false;
                    gridY--;
                    curAnimation.stop();
                    
                    eachStep();
                }
                break;
            case DOWN:
                curAnimation = downAnimation;
                curAnimation.start();
                
                // Push the character downwards.
                worldY += velocity;
                
                if (worldY >= getMiddle(gridY+1)) {
                    moving = false;
                    gridY++;
                    curAnimation.stop();
                    
                    eachStep();
                }
                break;
            case LEFT:
                curAnimation = leftAnimation;
                curAnimation.start();
                
                // Push the character to the left.
                worldX -= velocity;
                
                if (worldX <= getMiddle(gridX-1)) {
                    moving = false;
                    gridX--;
                    curAnimation.stop();
                    
                    eachStep();
                }
                break;
            case RIGHT:
                curAnimation = rightAnimation;
                curAnimation.start();
                
                // Push the character to the right.
                worldX += velocity;
                
                if (worldX >= getMiddle(gridX+1)) {
                    moving = false;
                    gridX++;
                    curAnimation.stop();
                    
                    eachStep();
                }
                break;
        }
    }
    
    /**
     * 
     * @return 
     */
    public Tile getCurrentTile() {
    	return this.currentTile;
    }
    
    /**
     * 
     */
    public void eachStep() {
        this.listeners.getListener("walk").gameEvent();
        this.currentTile = this.currentLevel.getTileAt(this.gridX, this.gridY);
        
        // Clear the fog around the player.
        currentLevel.clearNearbyFog(this.gridX, this.gridY);
        
        // Reduce the player's stamina depending on the terrain they are on.
        reduceStamina(currentTile.getTerrainDifficulty());
        this.listeners.getListener("stamina").gameEvent();
        
        GameObject go = currentTile.getObject();
        this.listeners.getListener("addGameObject").gameEvent();
        
        //check game state
        updatePlayerState();
    }
    

    
    /**
     * Finds the middle of the tile on the ground.
     * 
     * @param x
     * @return 
     */

    // Find the middle of a tile on the grid.

    public int getMiddle(int x) {
        int middle = Game.TILE_SIZE / 2 - curAnimation.getCurFrame().getWidth() / 2;
        return middle + x*Game.TILE_SIZE;
    }
    

    /**
     * 
     */

    public void up() {
        if (gridY == 0)
            return;
        
        this.moving = true;
        this.direction = Direction.UP;
    }
    
    /**
     * 
     */
    public void down() {
        if (gridY == Game.GRID_SIZE-1)
            return;
        
        this.moving = true;
        this.direction = Direction.DOWN;
    }
    
    /**
     * 
     */
    public void left() {
        if (gridX == 0)
            return;
        
        this.moving = true;
        this.direction = Direction.LEFT;
    }
    
    /**
     * 
     */
    public void right() {
        if (gridX == Game.GRID_SIZE-1)
            return;
        
        this.moving = true;
        this.direction = Direction.RIGHT;
    }
    
    /**
     * 
     * @return 
     */
    public String getName() {
       return this.name;
    }
    
    /**
     * 
     * @return 
     */
    public boolean isAlive() {
        return this.alive;
    }
    
    /**
     * 
     * @return 
     */
    public double getMaxStamina(){
        return this.maxStamina;
    }
    
    /**
     * 
     * @return 
     */
    public double getStaminaLevel(){
        return this.stamina;
    }
    
    /**
     * 
     * @param terrain
     * @return 
     */
    public double getStaminaNeededToMove(Terrain terrain){
         double staminaNeeded = MOVE_STAMINA;
        double load = getCurrentBackpackWeight() / maxBackpackWeight;
        // Twice as much is needed when the backpack is full
        staminaNeeded *= (1.0 + load);
        // and even more when the terrain is difficult
        staminaNeeded *= terrain.getDifficulty();
        return staminaNeeded;
    }
    
    /**
     * 
     * @param terrain
     * @return 
     */
    public boolean hasStaminaToMove(Terrain terrain){
        return (this.stamina >= getStaminaNeededToMove(terrain));
    }
    
    /**
     * 
     * @return 
     */
    public boolean hasStamina(){
        return this.stamina > 0;
    }
    
    /**
     * 
     * @return 
     */
    public int getCurrentBackpackSize(){
        double totalSize = 0.0;
        for(Item item : backpack.values()){
            totalSize = totalSize + item.getSize();
         
        }
        return (int)totalSize;
    }
    
    /**
     * 
     * @return 
     */
    public int getMaximumBackpackSize(){
        return (int)maxBackpackSize;
    }
    
    /**
     * 
     * @return 
     */
    public int getCurrentBackpackWeight(){
        double totalWeight = 0.0;
        for(Item item : backpack.values()){
            totalWeight += item.getWeight();   
        }
       
        return (int)totalWeight;
    } 
    
    /**
     * 
     * @return 
     */
    public int getMaximumBackpackWeight(){
        return (int)maxBackpackWeight;
    }
    

    /**
     * 
     * @param item
     * @return 
     */


     public boolean hasItem(String item){
        return backpack.containsKey(item);
    }
     
     /**
     * Checks if the player has a tool.
     * 
     * @return true if tool in backpack, false if not
     */
    public boolean hasTrap()
    {
        boolean found = false;
        for ( Item item : backpack.values() ) 
        {
            if(item instanceof Tool)
            {
                Tool tool = (Tool) item;
                if (tool.isTrap())
                {
                    found = true;
                }
            }
        }
        return found;

    }
    
     /**
      * 
      * @return 
      */
    public int getWorldX() {
        return this.worldX;
    }
    
    /**
     * 
     * @return 
     */
    public int getWorldY() {
        return this.worldY;
    }
    
    /**
     * 
     * @return 
     */
    public int getGridX() {
        return this.gridX;
    }
    
    /**
     * 
     * @return 
     */
    public int getGridY() {
        return this.gridY;
    }
    
    /**
     * 
     * @return 
     */
    public String getMessage(){
        return this.message;
    }
    
     /**
     * Returns a collection of all items in the player's backpack.
     * 
     * @return the items in the player's backpack
     */
    public Collection<Item> getInventory()
    {
        return backpack.values();
    }
    
      /*************************************************************************************************************
     * Mutator methods
     ****************************************************************************************************************/
    
    /**
     * Kills the Player
     */
    public void kill()
    {
        this.alive = false;
    } 
    
    /**
     * Decrease the stamina level by reduction.
     * 
     * @param reduction the amount of stamina to reduce
     */
    public void reduceStamina(double reduction)
    {
       if ( reduction > 0 )
       { 
          this.stamina -= reduction;
          if ( this.stamina < 0.0 )
          {
             this.stamina = 0.0; 
          }
       }    
    }    
    
    /**
     * Increase the stamina level of the player.
     * 
     * @param increase the amount of stamina increase
     */
    public void increaseStamina(double increase)
    {
       if ( increase > 0 && isAlive() )
       {         
          this.stamina += increase;    
       }
       if ( stamina > maxStamina )
       {
           stamina = (int)maxStamina;
       }
    }
    
    /**
     * Collect an item if it will fit in player's backpack.
     * 
     * @param item to collect
     * @return true if item is placed in backpack.
     */
    public boolean collect(Item item)
    {
        boolean success = false;
        
        if ( item != null && item.isOkToCarry() )
        {      
            
            double  addedSize   = getCurrentBackpackSize() + item.getSize();     
            boolean enoughRoom  = (addedSize <= this.maxBackpackSize);
            double  addedWeight = getCurrentBackpackWeight() + item.getWeight();
            //Will weight fit in backpack?
            boolean notTooHeavy = (addedWeight <= this.maxBackpackWeight);
            //Player can only carry one trap at a time.
            //Is this an additional trap?
            boolean additionalTrap = false;
            if(item instanceof Tool)
            {
                Tool tool = (Tool) item;
                additionalTrap = (tool.isTrap() && this.hasTrap());
            }   
            if ( enoughRoom && notTooHeavy && !additionalTrap)
            {     
                backpack.put(item.getName(), item);
                success = true;
            }
            
        }
        return success;
    }
    
    /**
     * get a trap from player's backpack
     * @return trap or null if player has no trap
     */
    public Tool getTrap()
    {
        Tool tool = null;
        Tool trap = null;
        for ( Item item : backpack.values() ) 
        {
            if(item instanceof Tool)
            {
                tool = (Tool) item;
                if (tool.isTrap())
                {
                    trap = tool;
                }
            }
        }
        return trap;
    }
    
    /**
     * Drops an item if player is carrying it.
     * 
     * @param item to drop
     * @return true if item dropped, false if not
     */
    public boolean drop(String key)
    {
        Item item = backpack.get(key);
        return backpack.remove(key, item);
        
    }
    
    public Item getItem(String key)
    {
        return backpack.get(key);
    }
    
    /**
     * 
     * @return 
     */
    public boolean updating() {
        return this.moving;
    }
    
    /**
     * 
     * @return 
     */
    public Image getImg() {
        return (Image) this.curAnimation.getCurFrame();
    }
    
    /**
     * 
     * @param g 
     */
    public void draw(Graphics g) {}
    
    /**
     * 
     * @param message 
     */
    public void setMessage(String message){
        this.message = message;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public GameListenerMap getListeners() {
        return this.listeners;
    }
    
    public void setSpriteSheet(BufferedImage s) {
        this.spriteSheet = s;
        resetAnimations();
    }
    
    public void resetAnimations() {
        int size = 64;
        
        leftAnimation  = new Animation(new SpriteSheet(spriteSheet, size, size).getRow(0, 11), Animation.DEFAULT_UPDATE_TIME, false);
        rightAnimation = new Animation(new SpriteSheet(spriteSheet, size, size).getRow(1, 11), Animation.DEFAULT_UPDATE_TIME, false);
        upAnimation    = new Animation(new SpriteSheet(spriteSheet, size, size).getRow(2, 8), Animation.DEFAULT_UPDATE_TIME, false);
        downAnimation  = new Animation(new SpriteSheet(spriteSheet, size, size).getRow(3, 8), Animation.DEFAULT_UPDATE_TIME, false);
        
        // Set the character's default direction to down.
        this.curAnimation = downAnimation;
    }
    
 
    
    public void die() {
        this.curAnimation = deathAnimation;
        this.curAnimation.start();
        
        Timer t = new Timer(this.curAnimation.getTotalTime(), (ActionEvent ae) -> {
            Game.state = GameState.LevelSelect;
            this.listeners.getListener("endGame").gameEvent();
        });
        t.setRepeats(false);
        t.start();
    }
    
    /**
     * 
     */
    public void updatePlayerState(){
         String message = "";
        if ( !this.isAlive() )
        {
            message = "Sorry, you have lost the game. ";
            setMessage(message);
            die();
            
        }
        else if (!hasStamina())
        {
            message = "Sorry, you have lost the game. You do not have sufficient stamina to move.";
            setMessage(message);
            die();
        }    
        else if (currentLevel.getKiwiCount() == currentLevel.getKiwisInLevel() && currentLevel.getPredatorCount() == currentLevel.getPredatorsInLevel())
        {
            Game.state = GameState.LevelSelect;
            message = "Good job you found " + currentLevel.getKiwiCount() + " Kiwi, and you caught "+currentLevel.getPredatorCount()+" predators, you win!";
            setMessage(message);
            this.listeners.getListener("endGame").gameEvent();
        }    
    }
    
    /**
     * 
     */
    public void reset() {
        this.backpack = new HashMap<String, Item>();
        this.alive = true;
        this.gridX = 0;
        this.gridY = 0;
        this.worldX = this.getMiddle(0);
        this.worldY = this.getMiddle(0);
        this.currentTile = this.currentLevel.getTileAt(0, 0);
        this.stamina = 100;   
    }
    
    public void setDeathAnimation(Animation death) {
        this.deathAnimation = death;
    }
}