package KiwiIsland;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Team Taniwha
 * @version 1.0
 * 
 */
public class Level {
    // DATA FIELDS
    private SpriteSheet terrain;
    
    private GameListenerMap listeners;
    
    private int kiwiCount;
    private int kiwisInLevel;
    private int predatorCount;
    private int predatorsInLevel;
    
    
    // Each level creates its own game grid.
    private Tile[][] grid = new Tile[Game.GRID_SIZE][Game.GRID_SIZE];
    
    /**
     * 
     * @param worldFile
     * @param terrain
     * @param listeners 
     */
    public Level(String worldFile, SpriteSheet terrain, GameListenerMap listeners) {
        this.listeners = listeners;
        this.terrain = terrain;
        
        this.kiwiCount = 0;
        this.kiwisInLevel = 0;
        
        parseLevel(worldFile);
        
        grid[0][0].setVisited(true);
        grid[0][0].setVisible(true);
        
        clearNearbyFog(0, 0);
    }
    
    /**
     * 
     * @param worldFile 
     */
    public void parseLevel(String worldFile) {
        // parseLevel reads a worldFile that is found in the resource package of the project.
        // Each level file is named as such: level1.txt, level2.txt etc.
        //
        // Parsing a level file can be done by firstly reading each character to determine what 
        // terrain can be found at a certain location on the grid.
        //
        // Sand  = '.'
        // Grass = '*'
        // Ice   = '#'
        // Water = '~'
        // Lava  = '$'
        // Rock  = '%'
        //
        // Following the terrain description will be a '?' character which signals 
        // that the lines to come will determine the Fauna that are in the level.
        // These game objects are then parsed and placed into the tiles they belong to.
        try {
            Scanner input = new Scanner(new File(getClass().getClassLoader().getResource(worldFile).toURI()));
            boolean readGrid = false;
            int x = 0;
            
            while (input.hasNext()) {
                String line = input.nextLine();
                
                if (line.contains("?")) {
                    readGrid = true;
                    line = input.nextLine();
                }
                
                if (readGrid == false) {
                    for (int y = 0; y < line.length(); y++) {
                        switch (Terrain.getTerrainFromStringRepresentation(Character.toString(line.charAt(y)))) {
                            case GRASS:
                                grid[x][y] = new Tile(terrain.getImage(11, 5),Terrain.GRASS);
                                break;
                            case ICE:
                                grid[x][y] = new Tile(terrain.getImage(15, 15),Terrain.ICE);
                                break;
                            case SAND:
                                grid[x][y] = new Tile(terrain.getImage(9, 19),Terrain.SAND);
                                break;
                            case WATER:
                                grid[x][y] = new Tile(terrain.getImage(5, 29),Terrain.WATER);
                                break;
                            case LAVA:
                                Tile lava = new Tile(terrain.getImage(5, 15), Terrain.LAVA);
                                lava.setDeadly(true);
                                grid[x][y] = lava;
                                break;
                            case ROCK:
                                grid[x][y] = new Tile(terrain.getImage(5, 12),Terrain.ROCK);
                        }
                    }
                    
                    x++;
                } else {
                    String[] data = line.split(",");
                    
                    if (data.length < 5) {
                        System.out.println("Level: Error reading occupant data.");
                    }
                    
                    int i = Integer.parseInt(data[3]);
                    int j = Integer.parseInt(data[4]);
                    
                    GameObject go = null;
                    
                    switch (data[1]) {
                        case "Kiwi":
                            go = new Kiwi(i, j, data[1], data[2]);
                            kiwisInLevel++;
                            break;
                        case "Tui":
                            go = new Native(i, j, data[1], data[2]);         
                            break;
                        case "Tuatara":
                            go = new Native(i, j, data[1], data[2]); 
                            break;
                        case "Dolphin":
                            go = new Native(i, j, data[1], data[2]);
                            break;
                        case "Fantail":
                            go = new Native(i, j, data[1], data[2]);
                            break;
                        case "Morepork":
                            go = new Native(i, j, data[1], data[2]);
                            break;
                        case "Kakeru":
                            go = new Native(i, j, data[1], data[2]);
                            break;
                        case "Taniwha":
                            go = new Native(i, j, data[1], data[2]);
                            break;
                        case "Weta":
                            go = new Native(i, j, data[1], data[2]);
                            break;
                        case "Possum":
                            go = new Predator(i, j, data[1], data[2]);
                            predatorsInLevel++;
                            break;
                        case "Cat":
                            go = new Predator(i, j, data[1], data[2]);
                            predatorsInLevel++;
                            break;
                        case "Rat":
                            go = new Predator(i, j, data[1], data[2]);
                            predatorsInLevel++;
                            break;
                        case "Ferret":
                            go = new Predator(i,j,data[1],data[2]);
                            predatorsInLevel++;
                            break;
                        case "Weasel":
                            go = new Predator(i,j,data[1],data[2]);
                            predatorsInLevel++;
                            break;
                        case "Stoat":
                            go = new Predator(i,j,data[1],data[2]);
                            predatorsInLevel++;
                            break;
                        case "Trap":
                            go = new Tool(i,j,data[1],data[2], 2, 2);
                            break;
                        case "Tool":
                            go = new Tool(i, j, data[1], data[2], 2, 2);
                            break;
                        case "Food":
                            go = new Food(i,j, data[1], data[2], 1, 1, 7);
                            break;
                        case "Hazard":
                            go = new Hazard(i,j, data[1], data[2], 6);
                            break;
                        default:
                            System.out.println("Level: Unknown Game Object.");
                    }
  
                    // Add the game object to a tile on the grid.
                    grid[i][j].addObject(go);
                }
            }
            
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @return 
     */
    public Tile[][] getGrid() {
        return this.grid;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    public Tile getTileAt(int x, int y) {
        if (x < 0 || x > this.grid.length && y < 0 || y > this.grid.length) {
            System.out.println("Level: getTileAt: out of bounds.");
            return null;
        }
        
        return this.grid[x][y];
    }
    
    /**
     * 
     */
    public void incrKiwiCount() {
        this.kiwiCount++;
    }
    
    /**
     * 
     * @return 
     */

    public void incrPredCount() {
        this.predatorCount++;
    }
    
    public int getKiwiCount() {
        return this.kiwiCount;
    }
    
    /**
     * 
     * @param playerX
     * @param playerY 
     */
    public void clearNearbyFog(int playerX, int playerY) {
        // Find the tiles that are in all four directions of the player and if we 
        // can access them (they are in bounds) then set their visibility to true.
        
        if (playerX+1 < this.grid.length)
            this.grid[playerX+1][playerY].setVisible(true);
        
        if (playerX-1 >= 0)
            this.grid[playerX-1][playerY].setVisible(true);
        
        if (playerY+1 < this.grid.length)
            this.grid[playerX][playerY+1].setVisible(true);
        
        if (playerY-1 >= 0)
            this.grid[playerX][playerY-1].setVisible(true);
    }
    
    /**
     * 
     * @return 
     */
    public int getPredatorCount() {
        return this.predatorCount;
    }
    
    public int getPredatorsInLevel() {
        return this.predatorsInLevel;
    }
    
    /**
     * Attempts to add an occupant to a specified position on the island.
     * @param position the position to add the occupant
     * @param occupant the occupant to add
     * @return true if occupant was successfully added, false if not
     */
    public boolean addOccupant( int itemX, int itemY, Item item)
    {
        GameObject go;
            if(item instanceof Food){
                go = new Food(itemX,itemY, item.getName(), item.getDescription(), 1, 1, 7);
            }else{
                go = new Tool(itemX,itemY, item.getName(), item.getDescription(), 1, 1); 
            }
            grid[itemX][itemY].addObject(go);   
            return true;
    }    
    
    /**
     * 
     * @return 
     */
     public int getKiwisInLevel() {
        return this.kiwisInLevel;
    }
}