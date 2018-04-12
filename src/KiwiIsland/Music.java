package KiwiIsland;

import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author suraaz
 * @version 1.0
 * 
 */
/*
This game uses these sounds from freesound:
Ascension Acid by Georgke ( http://www.freesound.org/people/Georgke/)
*/

public class Music implements Runnable{
    //DATA FIELDS
    Game game;
    JFXPanel j;
    String uri;
    MediaPlayer m;
    boolean playing;
    
    /**
     * 
     */
    public Music() {
        
        try{
            j = new JFXPanel();
            uri = new File("mmusic.mp3").toURI().toString();
            m = new MediaPlayer(new Media(uri));
            playing = false;
        }
        catch(Exception e){
            System.err.println(e);
        }
           
    }

    /**
     Plays music
     */
    @Override
    public void run() {
        
        if(!playing ){
            m.play();
            playing = true;
        }
                                              
    }
    
    /**
     Stops playing music
     */
    public void stop() {
        
      if(playing){
          m.pause();
          playing = false;
      }                
                               
    }   
}
