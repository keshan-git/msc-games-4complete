
package four.complete.ui.panels;

import four.complete.core.coms.packets.GameSummaryPacket;
import four.complete.model.PlayerModel;
import four.complete.ui.component.PlayerPanel;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

/**
 *
 * @author Keshan De Silva
 */
public class PlayersDetailsPanel extends javax.swing.JPanel
{
    private ArrayList<PlayerModel> playerModelList;
    private ArrayList<PlayerPanel> playerPanels;
    
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    
    public PlayersDetailsPanel()
    {
        initComponents();
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1;
   }

    public void setPlayerModelList(ArrayList<PlayerModel> playerModelList)
    {
        this.playerModelList = playerModelList;
        this.playerPanels = new ArrayList<>();
        this.gridBagConstraints.gridy = playerPanels.size();
            
        for (PlayerModel playerModel : playerModelList)
        {
            PlayerPanel playerPanel = new PlayerPanel();
            playerPanel.setPlayerModel(playerModel);
       
            this.gridBagConstraints.gridy = playerPanels.size();
            playerPanels.add(playerPanel);
            this.add(playerPanel, gridBagConstraints);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setLayout(new java.awt.GridBagLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void updateGameSummary(GameSummaryPacket gameSummaryPacket)
    {
        for (PlayerPanel playerPanel : playerPanels)
        {
            int score = (gameSummaryPacket.getScoreMap().containsKey(playerPanel.getPlayerName())) ?
                    gameSummaryPacket.getScoreMap().get(playerPanel.getPlayerName()) : 0;
            
            int position = (gameSummaryPacket.getPositionList().contains(playerPanel.getPlayerName())) ?
                    (gameSummaryPacket.getPositionList().indexOf(playerPanel.getPlayerName())) + 1: -1;
            
            playerPanel.setScore(score);
            playerPanel.setPosition(position);
        }
    }
}
