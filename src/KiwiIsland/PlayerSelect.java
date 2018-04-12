package KiwiIsland;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerSelect extends JPanel {
    
    private BufferedImage boySelect;
    private BufferedImage girlSelect;
    
    public PlayerSelect(Player player, World world) {
        // Load the images.
        try {
            boySelect = ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/boySelect.png"));
            girlSelect = ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/girlSelect.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Set JPanel attributes.
        this.setLayout(new BorderLayout());
        setBackground(new Color(255, 255, 255, 150));
        setBounds(50, 50, Game.WIDTH-100, Game.HEIGHT-100);
        
        // Create the labels and add event listeners.
        JLabel boy = new JLabel(new ImageIcon(boySelect));
        JLabel girl = new JLabel(new ImageIcon(girlSelect));
        
        boy.addMouseListener((new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e) {
                try {
                    player.setName("Fast Eddie");
                    player.getListeners().getListener("changePlayerName").gameEvent();
                    player.setSpriteSheet(ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/boy_sprite.png")));
                    
                    SpriteSheet death = new SpriteSheet(ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/die_boy.png")), 64, 64);
                    player.setDeathAnimation(new Animation(death.getRow(0, 11), 80, true));
                    
                    Game.state = GameState.LevelSelect;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }));
        
        girl.addMouseListener((new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e) {
                try {
                    player.setName("Speedy Suzy");
                    player.getListeners().getListener("changePlayerName").gameEvent();
                    player.setSpriteSheet(ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/GirlSprite.png")));
                    
                    SpriteSheet death = new SpriteSheet(ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/die_girl.png")), 64, 64);
                    player.setDeathAnimation(new Animation(death.getRow(0, 11), 80, true));
                    
                    Game.state = GameState.LevelSelect;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }));
        
        this.add(boy, BorderLayout.WEST);
        this.add(girl, BorderLayout.EAST);
    }
}
