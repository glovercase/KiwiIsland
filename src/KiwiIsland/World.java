package KiwiIsland;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * @author Team Taniwha
 * @version 1.0
 * 
 */
public class World extends JPanel implements KeyListener {
    
    // DATA FIELDS
    // Update the world every 50 milliseconds (0.05 seconds).
    private final int UPDATE_TIME = 50;
    
    public Player player;
    private Level currentLevel;
    
    private BufferedImage battleScene;
    private BufferedImage map;
    private SpriteSheet terrain;
    private SpriteSheet catchPredator;
    private SpriteSheet buttons;
    private BufferedImage button;
    
    private Animation battlePred;
    
    private JButton btnLevelOne;
    private JButton btnLevelTwo;
    private JButton btnLevelThree;
    private JButton btnLevelFour;
    private JButton btnLevelFive;
    private JButton btnLevelSix;
    private JButton btnLevelSeven;
    private JButton btnLevelEight;
    private JButton btnLevelNine;
    private JButton btnLevelTen;
    private JButton btnLevelEleven;
    private JButton btnLevelTweleve;
    private JButton btnLevelThirteen;
    private JButton btnLevelFourteen;
    private JButton btnLevelFifteen;
    private JButton btnLevelSixteen;
    
    private PlayerSelect ps;

    GameListenerMap listeners;

    // Timer for rendering and updating the game world.
    private final Timer timer;
    
    /**
     * 
     * @param listeners 
     */
    public World(GameListenerMap listeners) {
        this.setLayout(null);
        
        this.listeners = listeners;
        
        try {
            this.map = ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/map.png"));
            this.battleScene = ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/Boy_Battle_Scene.png"));
            this.terrain = new SpriteSheet(ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/terrain.png")), 64, 64);
            this.catchPredator = new SpriteSheet(ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/Boy_Battle_Scene_trap.png")), 640,640);
            this.buttons = new SpriteSheet(ImageIO.read(getClass().getClassLoader().getResource("KiwiIsland/res/Button.png")), 16,16);
        } catch (Exception e) {
            e.printStackTrace();
        }
          
        this.button = buttons.getImage(0, 0);
        
        currentLevel = new Level("KiwiIsland/res/level1.txt", terrain, listeners);
        
        // Create a player for the world.
        this.player = new Player("Fast Eddie", listeners, 100, 10, 5, currentLevel);
        
        addLevelButtons();
        hideLevelButtons();
        ps = new PlayerSelect(player, this);
        this.add(ps);

        // Render and update the game world every 50 milliseconds.
        this.timer = new Timer(UPDATE_TIME, (ActionEvent e) -> {
            update(); 
            repaint();
        });      
        this.timer.start();
    }
    
    /**
     * 
     */
    public void update() {
        switch (Game.state) {
            case Playing:
                this.player.update();
                break;
            case LevelSelect:
                break;
            case Battle:
                break;
        }
    }
    
    public void battleScene() {
       battlePred = new Animation(catchPredator.getRow(0, 7), 80, true);
       this.battlePred.start();
       Timer t = new Timer(this.battlePred.getTotalTime(), (ActionEvent ae) -> {
            Game.state = GameState.Playing;
            //this.listeners.getListener("endGame").gameEvent();
        });
        t.setRepeats(false);
        t.start();
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (player.updating()) {
            return;
        }
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                player.up();
                break;
            case KeyEvent.VK_DOWN:
                player.down();
                break;
            case KeyEvent.VK_RIGHT:
                player.right();
                break;
            case KeyEvent.VK_LEFT:
                player.left();
                break;
        }
    }
    
    /**
     * 
     * @return 
     */
    public Level getLevel(){
        return this.currentLevel;
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {}
    
    /**
     * 
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e) {}
    
    /**
     * 
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        switch (Game.state) {
            case PlayerSelect:
                this.ps.setVisible(true);
                g.drawImage(map, 0, 0, null);
                break;
            case LevelSelect:
                this.ps.setVisible(false);
                showLevelButtons();
                g.drawImage(map, 0, 0, null);
                break;
            case Playing:
                hideLevelButtons();
                drawWorld(g);
                g.drawImage(player.getImg(), player.getWorldX(), player.getWorldY(), null);
                break;
            case Battle:
                g.drawImage(battleScene, 0, 0, null);
                //g.drawImage(map, 0, 0, null);
                break;
            case Won:
                //fade background
                //btnLevelTwo = new JButton(new ImageIcon(button = buttons.getImage(0, 0)));
                break;
            case Lost:
                //fade back ground
                Game.state = GameState.LevelSelect;
                break;
        }
    }
    
    /**
     * 
     */
    public void addLevelButtons() {
        btnLevelOne = new JButton(new ImageIcon(button));       
        btnLevelTwo = new JButton(new ImageIcon(button = buttons.getImage(0, 2)));
        btnLevelThree = new JButton(new ImageIcon(button));
        btnLevelFour = new JButton(new ImageIcon(button));
        btnLevelFive = new JButton(new ImageIcon(button));
        btnLevelSix = new JButton(new ImageIcon(button));
        btnLevelSeven = new JButton(new ImageIcon(button));
        btnLevelEight = new JButton(new ImageIcon(button));
        btnLevelNine = new JButton(new ImageIcon(button));
        btnLevelTen = new JButton(new ImageIcon(button));
        btnLevelEleven = new JButton(new ImageIcon(button));
        btnLevelTweleve = new JButton(new ImageIcon(button));
        btnLevelThirteen = new JButton(new ImageIcon(button));
        btnLevelFourteen = new JButton(new ImageIcon(button));
        btnLevelFifteen = new JButton(new ImageIcon(button));
        btnLevelSixteen = new JButton(new ImageIcon(button));
        
        btnLevelOne.setSize(16,16);
        btnLevelTwo.setSize(16,16);
        btnLevelThree.setSize(16,16);
        btnLevelFour.setSize(16,16);
        btnLevelFive.setSize(16,16);
        btnLevelSix.setSize(16,16);
        btnLevelSeven.setSize(16,16);
        btnLevelEight.setSize(16,16);
        btnLevelNine.setSize(16,16);
        btnLevelTen.setSize(16,16);
        btnLevelEleven.setSize(16,16);
        btnLevelTweleve.setSize(16,16);
        btnLevelThirteen.setSize(16,16);
        btnLevelFourteen.setSize(16,16);
        btnLevelFifteen.setSize(16,16);
        btnLevelSixteen.setSize(16,16);
        
        btnLevelOne.setLocation(380,130); //Auckland
        btnLevelTwo.setLocation(370,325); //Wellinton
        btnLevelThree.setLocation(290,430); //Christchurch
        btnLevelFour.setLocation(420,200); //Taupo
        btnLevelFive.setLocation(440,180); //Rotorua
        btnLevelSix.setLocation(150,500); //Queenstown
        btnLevelSeven.setLocation(300,325); //Nelson
        btnLevelEight.setLocation(400,100); //Coromandal
        btnLevelNine.setLocation(400,170); //Hamilton
        btnLevelTen.setLocation(510,220); //Gisborne
        btnLevelEleven.setLocation(350,70); //Whangarei
        btnLevelTweleve.setLocation(390,260); //Wanganui
        btnLevelThirteen.setLocation(200, 400); //Grey Town
        btnLevelFourteen.setLocation(220, 500); //Dunedin
        btnLevelFifteen.setLocation(100, 590); //Stewart Island
        btnLevelSixteen.setLocation(460, 260); //Napier
        
        this.add(btnLevelOne);
        this.add(btnLevelTwo);
        this.add(btnLevelThree);
        this.add(btnLevelFour);
        this.add(btnLevelFive);
        this.add(btnLevelSix);
        this.add(btnLevelSeven);
        this.add(btnLevelEight);
        this.add(btnLevelNine);
        this.add(btnLevelTen);
        this.add(btnLevelEleven);
        this.add(btnLevelTweleve);
        this.add(btnLevelThirteen);
        this.add(btnLevelFourteen);
        this.add(btnLevelFifteen);
        this.add(btnLevelSixteen);
        
        btnLevelOne.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level1.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelTwo.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level2.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelThree.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level3.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelFour.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level4.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelFive.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level5.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
          
        btnLevelSix.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level6.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelSeven.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level7.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelEight.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level8.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelNine.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level9.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelTen.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level10.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
                
        btnLevelEleven.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level11.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelTweleve.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level12.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelThirteen.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level13.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelFourteen.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level14.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelFifteen.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level15.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
        
        btnLevelSixteen.addActionListener((ActionEvent e) -> {
            currentLevel = new Level("KiwiIsland/res/level16.txt", terrain, listeners);
            player.currentLevel = currentLevel;
            listeners.getListener("changePredatorCount").gameEvent();
            Game.state = GameState.Playing;
        });
    }
    
    /**
     * 
     */
    public void removeLevelButtons() {
        this.remove(btnLevelOne);
        this.remove(btnLevelTwo);
        this.remove(btnLevelThree);
        this.remove(btnLevelFour);
        this.remove(btnLevelFive);
        this.remove(btnLevelSix);
        this.remove(btnLevelSeven);
        this.remove(btnLevelEight);
        this.remove(btnLevelNine);
        this.remove(btnLevelTen);
        this.remove(btnLevelEleven);
        this.remove(btnLevelTweleve);
        this.remove(btnLevelThirteen);
        this.remove(btnLevelFourteen);
        this.remove(btnLevelFifteen);
        this.remove(btnLevelSixteen);
    }
    
    /**
     * 
     */
    public void hideLevelButtons() {
        btnLevelOne.setVisible(false);
        btnLevelTwo.setVisible(false);
        btnLevelThree.setVisible(false);
        btnLevelFour.setVisible(false);
        btnLevelFive.setVisible(false);
        btnLevelSix.setVisible(false);
        btnLevelSeven.setVisible(false);
        btnLevelEight.setVisible(false);
        btnLevelNine.setVisible(false);
        btnLevelTen.setVisible(false);
        btnLevelEleven.setVisible(false);
        btnLevelTweleve.setVisible(false);
        btnLevelThirteen.setVisible(false);
        btnLevelFourteen.setVisible(false);
        btnLevelFifteen.setVisible(false);
        btnLevelSixteen.setVisible(false);
    }
    
    /**
     * 
     */
    public void showLevelButtons() {
        btnLevelOne.setVisible(true);
        btnLevelTwo.setVisible(true);
        btnLevelThree.setVisible(true);
        btnLevelFour.setVisible(true);
        btnLevelFive.setVisible(true);
        btnLevelSix.setVisible(true);
        btnLevelSeven.setVisible(true);
        btnLevelEight.setVisible(true);
        btnLevelNine.setVisible(true);
        btnLevelTen.setVisible(true);
        btnLevelEleven.setVisible(true);
        btnLevelTweleve.setVisible(true);
        btnLevelThirteen.setVisible(true);
        btnLevelFourteen.setVisible(true);
        btnLevelFifteen.setVisible(true);
        btnLevelSixteen.setVisible(true);
    }
    
    /**
     * 
     * @param g 
     */
    public void drawWorld(Graphics g) {
        Tile[][] grid = currentLevel.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int x = i * Game.TILE_SIZE;
                int y = j * Game.TILE_SIZE;

                grid[i][j].draw(g, x, y);

                // If the tile is visible, draw its game objects.
                if (grid[i][j].getVisible()) {
                	GameObject go = grid[i][j].getObject();
                	if (go != null) {
                		go.draw(g);
                	}
                }
            }
        }
    }
}