package KiwiIsland;

import java.util.HashMap;
/**
 * @author Team Taniwha
 * @version 1.0
 * 
 */
public class GameListenerMap {
    HashMap<String, GameListener> listeners;
    
    /**
     * 
     */
    public GameListenerMap() {
        this.listeners = new HashMap<String, GameListener>();
    }
    
    /**
     * 
     * @param key
     * @param listener 
     */
    public void addListener(String key, GameListener listener) {
        this.listeners.put(key, listener);
    }
    
    /**
     * 
     * @param key
     * @return 
     */
    public GameListener getListener(String key) {
        return this.listeners.get((String) key);
    }
}
