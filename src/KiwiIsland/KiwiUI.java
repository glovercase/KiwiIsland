package KiwiIsland;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;

public class KiwiUI extends javax.swing.JFrame {
    private Game game;
    
    public KiwiUI() {
        // Added an amazing comment here that needs its own branch
        // because of how cool it isssss.
        
        GameListenerMap listeners = new GameListenerMap();
        initListeners(listeners);
        this.game = new Game(listeners);
        
        initComponents();
        txtPlayerName.setText(game.world.player.getName());       
        progPlayerStamina.setMaximum(100);
        progPlayerStamina.setValue(100);
        
        progBackpackSize.setMaximum(game.world.player.getMaximumBackpackSize());
        progBackpackWeight.setMaximum(game.world.player.getMaximumBackpackWeight());
        
        txtPredatorsLeft.setText("0");
        
        jBtnHome.setFocusable(false);
        
        pnlIsland.addKeyListener(game.world);
        pnlIsland.setLayout(null);
        game.world.setLocation(0,0);
        game.world.setSize(Game.WIDTH, Game.HEIGHT);
        pnlIsland.setFocusable(true);
        pnlIsland.add(game.world);
       
        jTableBackPack.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                       int selectedRow = jTableBackPack.getSelectedRow();
                       int selectedCol = jTableBackPack.getSelectedColumn();  
                       if(selectedRow >= 0){
                            String selectedItem = jTableBackPack.getValueAt(selectedRow, selectedCol).toString();
                            if(selectedItem.equals("Food")){
                                btnCollect.setText("Use");
                                btnCollect.setEnabled(true);
                                btnCount.setText("Drop");
                                btnCount.setEnabled(true);
                            }else if(selectedItem.equals("Trap")){
                                if(Game.state == GameState.Battle){
                                    btnCollect.setText("Catch");
                                    btnCollect.setEnabled(true);
                                }else{
                                    infoBox("You can not use a trap yet, find a predatotor", "Warning!");
                                    btnCount.setText("Drop");
                                    btnCount.setEnabled(true);
                                    btnCollect.setEnabled(false);
                                }
                            }else{
                                //handle tool
                                btnCollect.setText("Fix");
                                btnCollect.setEnabled(true);
                            }
                        }
                    }
                }
        );
        
        pack();
    }
    
    public void clear() {
    	objectsList.setListData(new String[0]);
        objectsList.clearSelection();
        game.world.player.getCurrentTile().clearObject();
    }
    
    public void continuePlay() {
         objectsList.clearSelection();
    }
    
    public void handleKiwi(Kiwi kiwi) {
        infoBox(kiwi.getDescription(),kiwi.getName());
    	btnCount.setEnabled(true);
        btnCount.setText("Count");
        btnCollect.setEnabled(false);
    	objectsList.setListData(new String[]{kiwi.getName()});
    }
    
    public void handleNative(Native nat) {
        infoBox(nat.getDescription(),nat.getName());
        btnCount.setEnabled(true);
        btnCount.setText("Log");
        btnCollect.setEnabled(false);
    	objectsList.setListData(new String[]{nat.getName()});
    }
    
    public void handleFood(Food food) {
        infoBox(food.getDescription(),food.getName());
    	btnCount.setEnabled(false);
    	btnCollect.setEnabled(true);
    	objectsList.setListData(new String[]{food.getName()});
    }
    
    public void handleTool(Tool tool) {
        infoBox(tool.getDescription(),tool.getName());
    	btnCount.setEnabled(false);
    	btnCollect.setEnabled(true);
    	objectsList.setListData(new String[]{tool.getName()});
    }
    
    public void handlePredator(Predator predator){
        infoBox(predator.getDescription(),predator.getName());
        btnCount.setEnabled(true);
        btnCount.setText("Battle");
        btnCollect.setEnabled(false);
        objectsList.setListData(new String[]{predator.getName()});
    }
    
    public void handleHazard(Hazard hazard){
        infoBox(hazard.getDescription(),hazard.getName());
        game.world.player.kill();
    }
    
    public void handleBackpack(boolean drop, int selectedRow){
        DefaultTableModel model = (DefaultTableModel) jTableBackPack.getModel();
        String itemKey = (String)model.getValueAt(selectedRow, 0);
        if(drop){
            handleDrop(itemKey);
        }else{
           handleUse(itemKey);
        }
        game.world.player.drop(itemKey);   
        model.removeRow(selectedRow);  
    }
    
    public void handleDrop(String itemKey){
        
        Item item = game.world.player.getItem(itemKey);
        boolean success = game.world.player.drop(itemKey);;
        if ( success )
        {
            // player has dropped an what: try to add to grid square
             int x = game.world.player.getGridX();
            int y = game.world.player.getGridY();
            success = game.world.getLevel().addOccupant(x, y, item);
            
            if ( success )
            {
                // drop successful: everybody has to know that
               progBackpackSize.setValue(game.world.player.getCurrentBackpackSize());
               progBackpackWeight.setValue(game.world.player.getCurrentBackpackWeight());
            }
            else
            {
                // grid square is full: player has to take what back
                game.world.player.collect(item);
            }
        }
        
    }
    
    public void handleUse(String itemKey) {
        Item item = game.world.player.getItem(itemKey);
           if(item instanceof Food)
                {
                    Food food = (Food) item;
                    game.world.player.increaseStamina(food.getEnergy());
                    game.world.player.drop(itemKey);
                    progBackpackSize.setValue(game.world.player.getCurrentBackpackSize());
                    progBackpackWeight.setValue(game.world.player.getCurrentBackpackWeight());
                }else if (item instanceof Tool){
                    Tool tool = (Tool) item;
                    
                        if(game.world.player.hasTrap() && ((Tool) item).isBroken())
                            {
                                Tool trap = game.world.player.getTrap();
                                trap.fix();
                                infoBox("Trap is fixed","Success");
                                game.world.player.drop(itemKey);
                                progBackpackSize.setValue(game.world.player.getCurrentBackpackSize());
                                progBackpackWeight.setValue(game.world.player.getCurrentBackpackWeight());
                            }
                    
                    
                }    
    }
    
    public boolean battleScene(){
        if(game.world.player.hasItem("Trap")){
                Tool item = (Tool)game.world.player.getItem("Trap");
                    if(!item.isBroken()){
                        Game.state = GameState.Battle;
                        btnCount.setEnabled(false);
                        return true;
                    }else{
                        infoBox("Your Trap is broken find a tool to fix it", "Warning!");
                        continuePlay();
                        pnlIsland.grabFocus();
                        return false;
                    }
            }else{
                   infoBox("You need a trap to capture a predator", "Warning!");
                    continuePlay();
                    pnlIsland.grabFocus();
                    return false;
               }
    }
    
    public void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "Encounter: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void reset() {
        game.world.player.reset();
        progPlayerStamina.setValue((int)game.world.player.getStaminaLevel());
        progBackpackWeight.setValue(0);
        progBackpackSize.setValue(0);
        txtPredatorsLeft.setText("0");
        
        // Reset everything here.
        clear();
        txtKiwisCounted.setText("0");
        DefaultTableModel model = (DefaultTableModel) jTableBackPack.getModel();
        DefaultTableModel model2 = (DefaultTableModel) jTableKiwiLog.getModel();
        model.setRowCount(0);
        model2.setRowCount(0);
    }
    
    
    public void initListeners(GameListenerMap listeners) {
        listeners.addListener("stamina", new GameListener() {
            public void gameEvent() { 
                progPlayerStamina.setValue((int)game.world.player.getStaminaLevel()); 
            }
        });
        
        listeners.addListener("walk", new GameListener() {
            public void gameEvent(){
                jTableBackPack.getSelectionModel().clearSelection();
                btnCollect.setText("Collect");
                btnCount.setText("Count");
            }
        });
        
        listeners.addListener("changePlayerName", new GameListener() {
            public void gameEvent() {
                txtPlayerName.setText(game.world.player.getName());
            }
        });
        
        
        listeners.addListener("changePredatorCount", new GameListener() {
            public void gameEvent() {
                txtPredatorsLeft.setText(Integer.toString(game.world.player.currentLevel.getPredatorCount()));
            }
        });
        
        listeners.addListener("endGame", new GameListener() {
            public void gameEvent() { 
                infoBox(game.world.player.getMessage(), "Game Over");
                reset();
                m.stop();
                jBtnMusicOff.setEnabled(false);
                jBtnMusicOff.setSelected(false);
                jBtnMusicOn.setEnabled(true);
            }
        });
        
        listeners.addListener("addGameObject", new GameListener() {
        	public void gameEvent() {
        		// Get the current tile that the user is standing on.
        		Tile t = game.world.player.getCurrentTile();
        		
        		GameObject object = t.getObject();
                        if (object == null) {
                            btnCount.setEnabled(false);
                            btnCollect.setEnabled(false);
                
                            // Clear the objects list.
                            objectsList.setListData(new String[0]);
                            return;
                        }
                        
                        // Handle all possible game objects.
                        // TODO: simplify this and all the handle methods into one.
        		if (object.getClass() == Kiwi.class) {
        			Kiwi kiwi = (Kiwi) object;
        			handleKiwi(kiwi);
        		}
                        if (object.getClass() == Native.class) {
        			Native nat = (Native) object;
        			handleNative(nat);
        		}
        		if (object.getClass() == Food.class) {
        			Food food = (Food) object;
        			handleFood(food);
        		}
        		if (object.getClass() == Tool.class) {
        			Tool tool = (Tool) object;
                                int rand = (int) Math.ceil(Math.random() * 4);           
                                if(rand < 1){
                                tool.setBroken();
                                }
        			handleTool(tool);
        		}
                        if (object.getClass() == Hazard.class){
                                Hazard hazard = (Hazard) object;
                                handleHazard(hazard);
                        }
                        if (object.getClass() == Predator.class){
                                Predator pred = (Predator) object;
                                handlePredator(pred);
                        }
                      
        	}
        });
        
      
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        javax.swing.JPanel pnlContent = new javax.swing.JPanel();
        pnlIsland = new javax.swing.JPanel();
        javax.swing.JPanel pnlControls = new javax.swing.JPanel();
        javax.swing.JPanel pnlPlayer = new javax.swing.JPanel();
        javax.swing.JPanel pnlPlayerData = new javax.swing.JPanel();
        javax.swing.JLabel lblPlayerName = new javax.swing.JLabel();
        txtPlayerName = new javax.swing.JLabel();
        javax.swing.JLabel lblPlayerStamina = new javax.swing.JLabel();
        progPlayerStamina = new javax.swing.JProgressBar();
        javax.swing.JLabel lblBackpackWeight = new javax.swing.JLabel();
        progBackpackWeight = new javax.swing.JProgressBar();
        javax.swing.JLabel lblBackpackSize = new javax.swing.JLabel();
        progBackpackSize = new javax.swing.JProgressBar();
        lblPredators = new javax.swing.JLabel();
        lblKiwisCounted = new javax.swing.JLabel();
        txtKiwisCounted = new javax.swing.JLabel();
        txtPredatorsLeft = new javax.swing.JLabel();
        javax.swing.JPanel pnlOptions = new javax.swing.JPanel();
        jTabbedPaneOptions = new javax.swing.JTabbedPane();
        jScrollPaneBackPack = new javax.swing.JScrollPane();
        jTableBackPack = new javax.swing.JTable();
        jScrollPaneKiwiLog = new javax.swing.JScrollPane();
        jTableKiwiLog = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jBtnMusicOn = new javax.swing.JToggleButton();
        jBtnMusicOff = new javax.swing.JToggleButton();
        javax.swing.JPanel pnlObjects = new javax.swing.JPanel();
        btnCollect = new javax.swing.JButton();
        btnCount = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        objectsList = new JList();
        jBtnHelp = new javax.swing.JToggleButton();
        jBtnHome = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kiwi Island");

        pnlContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlContent.setLayout(new java.awt.BorderLayout(10, 0));

        pnlIsland.setMaximumSize(new java.awt.Dimension(640, 640));
        pnlIsland.setPreferredSize(new java.awt.Dimension(640, 640));

        javax.swing.GroupLayout pnlIslandLayout = new javax.swing.GroupLayout(pnlIsland);
        pnlIsland.setLayout(pnlIslandLayout);
        pnlIslandLayout.setHorizontalGroup(
            pnlIslandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        pnlIslandLayout.setVerticalGroup(
            pnlIslandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );

        pnlContent.add(pnlIsland, java.awt.BorderLayout.CENTER);
        pnlIsland.getAccessibleContext().setAccessibleName("Back Pack");

        pnlControls.setLayout(new java.awt.GridBagLayout());

        pnlPlayer.setBorder(javax.swing.BorderFactory.createTitledBorder("Player"));
        pnlPlayer.setLayout(new java.awt.BorderLayout());

        pnlPlayerData.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlPlayerData.setLayout(new java.awt.GridBagLayout());

        lblPlayerName.setText("Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        pnlPlayerData.add(lblPlayerName, gridBagConstraints);

        txtPlayerName.setText("Player Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pnlPlayerData.add(txtPlayerName, gridBagConstraints);

        lblPlayerStamina.setText("Stamina:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlPlayerData.add(lblPlayerStamina, gridBagConstraints);

        progPlayerStamina.setOpaque(true);
        progPlayerStamina.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        pnlPlayerData.add(progPlayerStamina, gridBagConstraints);
        progPlayerStamina.getAccessibleContext().setAccessibleName("0");

        lblBackpackWeight.setText("Backpack Weight:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlPlayerData.add(lblBackpackWeight, gridBagConstraints);

        progBackpackWeight.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        pnlPlayerData.add(progBackpackWeight, gridBagConstraints);

        lblBackpackSize.setText("Backpack Size:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        pnlPlayerData.add(lblBackpackSize, gridBagConstraints);

        progBackpackSize.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        pnlPlayerData.add(progBackpackSize, gridBagConstraints);

        lblPredators.setText("Predators Left:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlPlayerData.add(lblPredators, gridBagConstraints);

        lblKiwisCounted.setText("Kiwis Counted :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlPlayerData.add(lblKiwisCounted, gridBagConstraints);

        txtKiwisCounted.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlPlayerData.add(txtKiwisCounted, gridBagConstraints);

        txtPredatorsLeft.setText("P");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnlPlayerData.add(txtPredatorsLeft, gridBagConstraints);

        pnlPlayer.add(pnlPlayerData, java.awt.BorderLayout.PAGE_START);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        pnlControls.add(pnlPlayer, gridBagConstraints);

        pnlOptions.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));
        pnlOptions.setLayout(new java.awt.GridBagLayout());

        jTabbedPaneOptions.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPaneOptions.setFocusTraversalKeysEnabled(false);
        jTabbedPaneOptions.setFocusable(false);
        jTabbedPaneOptions.setPreferredSize(new java.awt.Dimension(280, 250));

        jTableBackPack.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Items"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBackPack.setFocusable(false);
        jScrollPaneBackPack.setViewportView(jTableBackPack);

        jTabbedPaneOptions.addTab("Back Pack", jScrollPaneBackPack);

        jTableKiwiLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Log"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableKiwiLog.setFocusable(false);
        jScrollPaneKiwiLog.setViewportView(jTableKiwiLog);

        jTabbedPaneOptions.addTab("Kiwi Log", jScrollPaneKiwiLog);

        jBtnMusicOn.setText("Music On");
        jBtnMusicOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnMusicOnActionPerformed(evt);
            }
        });

        jBtnMusicOff.setText("Music Off");
        jBtnMusicOff.setEnabled(false);
        jBtnMusicOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnMusicOffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBtnMusicOff)
                    .addComponent(jBtnMusicOn))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jBtnMusicOn)
                .addGap(26, 26, 26)
                .addComponent(jBtnMusicOff)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jTabbedPaneOptions.addTab("Settings", jPanel1);

        pnlOptions.add(jTabbedPaneOptions, new java.awt.GridBagConstraints());
        jTabbedPaneOptions.getAccessibleContext().setAccessibleName("Kiwi Log");
        jTabbedPaneOptions.getAccessibleContext().setAccessibleDescription("");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        pnlControls.add(pnlOptions, gridBagConstraints);

        pnlObjects.setBorder(javax.swing.BorderFactory.createTitledBorder("Objects"));

        btnCollect.setText("Collect");
        btnCollect.setToolTipText("");
        btnCollect.setFocusable(false);
        btnCollect.setMaximumSize(new java.awt.Dimension(81, 23));
        btnCollect.setMinimumSize(new java.awt.Dimension(81, 20));
        btnCollect.setPreferredSize(new java.awt.Dimension(81, 20));
        btnCollect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCollectActionPerformed(evt);
            }
        });

        btnCount.setText("Count");
        btnCount.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCount.setFocusable(false);
        btnCount.setMinimumSize(new java.awt.Dimension(82, 20));
        btnCount.setPreferredSize(new java.awt.Dimension(82, 20));
        btnCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCountActionPerformed(evt);
            }
        });

        objectsList.setModel(new DefaultListModel());
        jScrollPane2.setViewportView(objectsList);

        javax.swing.GroupLayout pnlObjectsLayout = new javax.swing.GroupLayout(pnlObjects);
        pnlObjects.setLayout(pnlObjectsLayout);
        pnlObjectsLayout.setHorizontalGroup(
            pnlObjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlObjectsLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btnCount, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnCollect, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlObjectsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pnlObjectsLayout.setVerticalGroup(
            pnlObjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlObjectsLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(pnlObjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCollect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlControls.add(pnlObjects, gridBagConstraints);

        pnlContent.add(pnlControls, java.awt.BorderLayout.EAST);

        jBtnHelp.setText("Help");
        jBtnHelp.setFocusable(false);
        jBtnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHelpActionPerformed(evt);
            }
        });

        jBtnHome.setText("Home");
        jBtnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 73, Short.MAX_VALUE)
                .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnHelp)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnHelp)
                    .addComponent(jBtnHome))
                .addGap(12, 12, 12)
                .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 239, Short.MAX_VALUE))
        );

        jBtnHelp.getAccessibleContext().setAccessibleParent(jTabbedPaneOptions);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCollectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCollectActionPerformed
        
    	String selected = objectsList.getSelectedValue();
    	Tile t = game.world.player.getCurrentTile();
    	if(game.world.player.getCurrentBackpackSize() > 0){
                int selectedRow = jTableBackPack.getSelectedRow(); 
                if(selectedRow >= 0 ){
                DefaultTableModel model = (DefaultTableModel) jTableBackPack.getModel();
                
                String itemKey = (String)model.getValueAt(selectedRow, 0);
                handleUse(itemKey);   
                model.removeRow(selectedRow);  
                }
        }
        
        if(Game.state == GameState.Battle){
              game.world.player.drop("Trap");
              progBackpackSize.setValue(game.world.player.getCurrentBackpackSize());
              progBackpackWeight.setValue(game.world.player.getCurrentBackpackWeight());
              game.world.getLevel().incrPredCount();
              int rPred = game.world.getLevel().getPredatorsInLevel() - game.world.getLevel().getPredatorCount();
              txtPredatorsLeft.setText(Integer.toString(rPred));
              game.world.battleScene();
        }
        
       
        // We are trying to collect nothing, release the focus and return.
    	if (selected == null || selected.equals("")) {
    		pnlIsland.grabFocus();
    		return;
    	}
    	
        // Add the object to the player's backpack.
    	switch (selected) {
            case "Trap": 
                if(!game.world.player.collect((Item) t.getObject())){
                    infoBox("Could not add the Trap to backpack!", "Warning");
                    continuePlay();
                    pnlIsland.grabFocus();
                    return;
                };
                break;
            case "Tool":
                if(!game.world.player.collect((Item) t.getObject())){
                    infoBox("Could not add the Tool to backpack!", "Warning");
                    continuePlay();
                    pnlIsland.grabFocus();
                    return;
                };
                break;
            case "Food":
                if(!game.world.player.collect((Item) t.getObject())){
                    infoBox("Not enough space in the back pack!", "Warning");
                    continuePlay();
                    pnlIsland.grabFocus();
                    return;
                };
    		
                break;
    	}
        clear();
        
        // Add the item to the backpack list.
        DefaultTableModel model = (DefaultTableModel) jTableBackPack.getModel();
        model.addRow(new Object[]{selected});

        // Update the player's backpack weight and size etc here.
        progBackpackSize.setValue(game.world.player.getCurrentBackpackSize());
        progBackpackWeight.setValue(game.world.player.getCurrentBackpackWeight());
        
        // Set the focus back to the game.
    	pnlIsland.grabFocus();
    }//GEN-LAST:event_btnCollectActionPerformed

    private void btnCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCountActionPerformed
       if(game.world.player.getCurrentBackpackSize() > 0){
            int selectedRow = jTableBackPack.getSelectedRow(); 
            if(selectedRow >= 0 ){
                handleBackpack(true, selectedRow);
            }
       }
       String selected = objectsList.getSelectedValue();
       DefaultTableModel model = (DefaultTableModel) jTableKiwiLog.getModel();
       
       if (selected == null || selected.equals("")) {
    		pnlIsland.grabFocus();
    		return;
    	}
       // Add the object that we are counting to the Kiwi log.
       
       switch (selected) {
           case "Tuatara":
               model.addRow(new Object[]{"Tuatara"});
               break;
           case "Tui":
               model.addRow(new Object[]{"Tui"});
               break;
           case "Dolphin":
               model.addRow(new Object[]{"Dolphin"});
               break;
           case "Fantail":
               model.addRow(new Object[]{"Fantail"});
               break;
           case "Kakeru":
               model.addRow(new Object[]{"Kakeru"});
               break;
           case "Moa":
               model.addRow(new Object[]{"Moa"});
               break;
           case "Morepork":
               model.addRow(new Object[]{"Morepork"});
               break;
           case "Taniwha":
               model.addRow(new Object[]{"Taniwha"});
               break;
           case "Weta":
               model.addRow(new Object[]{"Weta"});
               break;
           case "Kiwi":
               // Increment kiwi count.
               this.game.world.getLevel().incrKiwiCount();
               txtKiwisCounted.setText(Integer.toString(this.game.world.getLevel().getKiwiCount()));
               model.addRow(new Object[]{"Kiwi"}); 
               break;
           case "Possum":
               //switch to battle scene
               if(!battleScene()) return;
               break; 
           case "Cat":
               //switch to battle scene
               if(!battleScene()) return;
               break; 
           case "Rat":
               //switch to battle scene
               if(!battleScene()) return;
               break;
           case "Stoat":
               //switch to battle scene
               if(!battleScene()) return;
               break;
            case "Ferret":
               //switch to battle scene
               if(!battleScene()) return;
               break;
            case "Weasel":
               //switch to battle scene
               if(!battleScene()) return;
               break;
       }
       
       
       
       clear();
       
       pnlIsland.grabFocus();
    }//GEN-LAST:event_btnCountActionPerformed

    
    Music m = new Music();
    
    //Play music when the Music On Button is pressed
    private void jBtnMusicOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnMusicOnActionPerformed
        m.run();
        jBtnMusicOn.setEnabled(false);
        jBtnMusicOn.setSelected(false);
        jBtnMusicOff.setEnabled(true);
      
        pnlIsland.grabFocus();      
    }//GEN-LAST:event_jBtnMusicOnActionPerformed

    //Pause music when the Music Off Button is pressed
    private void jBtnMusicOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnMusicOffActionPerformed
       
        m.stop();
        jBtnMusicOff.setEnabled(false);
        jBtnMusicOff.setSelected(false);
        jBtnMusicOn.setEnabled(true);
        
        pnlIsland.grabFocus();
    }//GEN-LAST:event_jBtnMusicOffActionPerformed


    private void jBtnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHelpActionPerformed

        jBtnHelp.setSelected(false);
        
        File file = new File("Help.txt");
        JTextArea textArea = new JTextArea();
         String contents = null;
         try {
              contents = new Scanner(file).useDelimiter("\\Z").next();
         } catch (FileNotFoundException ex) {
             Logger.getLogger(KiwiUI.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         //Setting auto scrolling of Jtext Area
         DefaultCaret caret = (DefaultCaret)textArea.getCaret();
         caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
         
         //Sets the text of the text component to specified Text
         textArea.setText(contents); 
        
        //set Text Area non-editable
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);   
        this.add(scrollPane, BorderLayout.CENTER);
        
        //Set the size of the JScrollPane as per the ScreenSize
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() /2);
        int height = (int) (screenSize.getHeight()/1.5);
        scrollPane.setPreferredSize( new Dimension( width, height));
        
        // Sets JTextArea font and color.
        Font font = new Font("Comic Sans MS", Font.BOLD, 14);
        textArea.setFont(font);
        textArea.setForeground(Color.BLUE);
            
        JOptionPane.showMessageDialog(this,
        scrollPane, "Help Menu",
        JOptionPane.INFORMATION_MESSAGE);
        jBtnHelp.setFocusable(false);
         
         pnlIsland.grabFocus();
    }//GEN-LAST:event_jBtnHelpActionPerformed

    private void jBtnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHomeActionPerformed
        reset();
        Game.state = GameState.LevelSelect;
        pnlIsland.grabFocus();  
    }//GEN-LAST:event_jBtnHomeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KiwiUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KiwiUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KiwiUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KiwiUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        final KiwiUI kiwiUI = new KiwiUI();
        /* Create and display the form */
            
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                kiwiUI.setVisible(true);
            }
        });   
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCollect;
    private javax.swing.JButton btnCount;
    private javax.swing.JToggleButton jBtnHelp;
    private javax.swing.JButton jBtnHome;
    private javax.swing.JToggleButton jBtnMusicOff;
    private javax.swing.JToggleButton jBtnMusicOn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPaneBackPack;
    private javax.swing.JScrollPane jScrollPaneKiwiLog;
    private javax.swing.JTabbedPane jTabbedPaneOptions;
    private javax.swing.JTable jTableBackPack;
    private javax.swing.JTable jTableKiwiLog;
    private javax.swing.JLabel lblKiwisCounted;
    private javax.swing.JLabel lblPredators;
    private javax.swing.JList<String> objectsList;
    private javax.swing.JPanel pnlIsland;
    private javax.swing.JProgressBar progBackpackSize;
    private javax.swing.JProgressBar progBackpackWeight;
    private javax.swing.JProgressBar progPlayerStamina;
    private javax.swing.JLabel txtKiwisCounted;
    private javax.swing.JLabel txtPlayerName;
    private javax.swing.JLabel txtPredatorsLeft;
    // End of variables declaration//GEN-END:variables
}
