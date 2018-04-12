package KiwiIsland;

/**
 * @author AS
 * @version 1.0
 * 
 */
public enum Terrain
{
    SAND(".", 1.0),
    GRASS("*", 2.0),
    ICE("#", 2.5),
    WATER("~", 4.0),
    LAVA("$", 8.0),
    ROCK("%", 3.5);
    
    private final double difficulty;
    private final String stringRep;
    
    /**
     * 
     * @param stringRep
     * @param difficulty 
     */
    private Terrain(String stringRep, double difficulty) {
        this.stringRep  = stringRep;
        this.difficulty = difficulty;
    }
    
    /**
     * 
     * @return 
     */
    public double getDifficulty() {
        return difficulty;
    }
    
    /**
     * 
     * @return 
     */
    public String getStringRepresentation() {
        return stringRep;
    }
    
    /**
     * 
     * @param terrainString
     * @return 
     */
    public static Terrain getTerrainFromStringRepresentation(String terrainString) {
        // Get a terrain enum type from a string representation.
        // For example, '~' = WATER.
        Terrain terrain = null;
        for (Terrain item : values()) {
            if (item.getStringRepresentation().equals(terrainString)) {
                terrain = item;
            }
        }
        
        return terrain;
    }
}

