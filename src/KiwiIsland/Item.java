package KiwiIsland;

/**
 * This class represents an item that can be found on the island.
 * 
 * @author AS
 * @version July 2011
 */
public abstract class Item extends GameObject {
    // DATA FIELDS
    private double weight;
    private double size;
    private String name;
    private String description;
    
    /**
     * Construct an item with known attributes.
     * @param pos the position of the item
     * @param name the name of the item
     * @param description a longer description of the item
     */
    public Item(String name, String description, double weight, double size) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.size = size;
    }
    
     /**
     * Gets the weight of the item
     * @return the weight of the item
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Gets the size of the item
     * @return the size of the item
     */
    public double getSize() {
        return this.size;
    }    

    /**
     * Is it OK to pick up and carry this item?
     * Items with size > 0 can be carried.
     * 
     * @return true if item can be carried, false if not
     */
    public boolean isOkToCarry()
    {
        return size > 0;
    }
    
    /**
     * Gets the occupant's name.
     * 
     * @return the name of the occupant
     */
    public String getName()
    {
        return this.name;
    } 

   /**
    * Gets the description for the item.
    * 
    * @return the description
    */
    public String getDescription() {
        return this.description;
    }
}
