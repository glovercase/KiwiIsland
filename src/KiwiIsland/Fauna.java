package KiwiIsland;

/**
 * @author Team Taniwha
 * @version 1.0
 * 
 */
public abstract class Fauna extends GameObject{
    
    // DATA FIELDS
    private String name;
    private String description;
    
    /**
     * Constructor to create a Fauna with given name and description.
     * 
     * @param name
     * @param description 
     */
    public Fauna(String name, String description){
        this.name = name;
        this.description = description;
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
