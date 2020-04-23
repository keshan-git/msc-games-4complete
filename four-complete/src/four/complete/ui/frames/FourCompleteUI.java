package four.complete.ui.frames;

import four.complete.core.engine.GameEngine;
import four.complete.model.ScoreDataModel;
import four.complete.ui.panels.GameCanvas;
import four.complete.ui.panels.GameStatusPanel;
import four.complete.ui.panels.PlayersDetailsPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;

/**
 *
 * @author Keshan De Silva
 */
public class FourCompleteUI extends javax.swing.JFrame
{
    private GameEngine gameEngine;
    
    public FourCompleteUI(GameEngine gameEngine)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex){}
                
        initComponents();
        
        this.gameEngine = gameEngine;
        gameCanvas.setGameBoardModel(gameEngine);
        playersDetailsPanel.setPlayerModelList(gameEngine.getPlayerModelList());
        
        gameStatusPanel.setPlayerColor(gameEngine.getCurrentPlayer().getPlayerColor());
        gameStatusPanel.setPlayerName(gameEngine.getCurrentPlayer().getPlayerName());
        gameStatusPanel.setProgress(0);
        gameStatusPanel.setScore(0);
        gameStatusPanel.setPosition(-1);
        
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                //gameEngine.getCommunicationManager().stopCommunication();
                System.exit(0);
            }    
        });
    }

    public GameStatusPanel getGameStatusPanel()
    {
        return gameStatusPanel;
    }

    public GameCanvas getGameCanvas()
    {
        return gameCanvas;
    }

    public PlayersDetailsPanel getPlayersDetailsPanel()
    {
        return playersDetailsPanel;
    }

    public void showGameCompleteScreen(ScoreDataModel scoreDataModel)
    {
        GameComplete gameComplete = new GameComplete();
        gameComplete.setSize(this.getSize());
        gameComplete.setLocation(this.getLocation());
        gameComplete.displayGameResut(gameEngine, scoreDataModel);
        this.setVisible(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        gameCanvas = new four.complete.ui.panels.GameCanvas();
        playersDetailsPanel = new four.complete.ui.panels.PlayersDetailsPanel();
        gameStatusPanel = new four.complete.ui.panels.GameStatusPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Four-Complete");

        gameCanvas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        gameCanvas.setPreferredSize(new java.awt.Dimension(295, 295));

        javax.swing.GroupLayout gameCanvasLayout = new javax.swing.GroupLayout(gameCanvas);
        gameCanvas.setLayout(gameCanvasLayout);
        gameCanvasLayout.setHorizontalGroup(
            gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        gameCanvasLayout.setVerticalGroup(
            gameCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );

        playersDetailsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        playersDetailsPanel.setLayout(new java.awt.GridLayout(0, 1));

        gameStatusPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gameStatusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gameCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playersDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(gameStatusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gameCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playersDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private four.complete.ui.panels.GameCanvas gameCanvas;
    private four.complete.ui.panels.GameStatusPanel gameStatusPanel;
    private four.complete.ui.panels.PlayersDetailsPanel playersDetailsPanel;
    // End of variables declaration//GEN-END:variables
}
