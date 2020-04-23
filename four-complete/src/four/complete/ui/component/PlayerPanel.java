package four.complete.ui.component;

import four.complete.model.PlayerModel;
import java.awt.Color;
import javax.swing.BorderFactory;

/**
 *
 * @author Keshan De Silva
 */
public class PlayerPanel extends javax.swing.JPanel
{
    private Color playerColor;
    private String playerName;
    private int position;
    private int score;
    
    public PlayerPanel()
    {
        initComponents();
    }

    public void setPlayerModel(PlayerModel playerModel)
    {
        setPlayerColor(playerModel.getPlayerColor());
        setPlayerName(playerModel.getPlayerName());
    }
        
    public void setPlayerColor(Color playerColor)
    {
        this.playerColor = playerColor;
        lblPlayerName.setBackground(playerColor);
        lblPlayerStatus.setBorder(BorderFactory.createLineBorder(playerColor));
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
        lblPlayerName.setText(playerName);
    }

    public void setPosition(int position)
    {
        this.position = position;
        updateScoreLabel();
    }

    public void setScore(int score)
    {
        this.score = score;
        updateScoreLabel();
    }

    private void updateScoreLabel()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        
        switch (position)
        {
            case -1 :
            {
                stringBuilder.append("-");
                break;
            }
            case 1 :
            {
                stringBuilder.append("1<sup>st</sup>");
                break;
            }
            case 2 :
            {
                stringBuilder.append("2<sup>nd</sup>");
                break;
            }
            case 3 :
            {
                stringBuilder.append("3<sup>rd</sup>");
                break;
            }
            default :
            {
                stringBuilder.append(position).append("<sup>th</sup>");
                break;
            }
        }
        
        stringBuilder.append(" : [ ").append(score).append(" ]");
        stringBuilder.append("<html>");
        
        lblPlayerStatus.setText(stringBuilder.toString());
    }

    public String getPlayerName()
    {
        return playerName;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lblPlayerName = new javax.swing.JLabel();
        lblPlayerStatus = new javax.swing.JLabel();

        lblPlayerName.setBackground(new java.awt.Color(51, 255, 0));
        lblPlayerName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPlayerName.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayerName.setText("Keshan-PC");
        lblPlayerName.setMaximumSize(new java.awt.Dimension(100, 25));
        lblPlayerName.setMinimumSize(new java.awt.Dimension(100, 25));
        lblPlayerName.setOpaque(true);
        lblPlayerName.setPreferredSize(new java.awt.Dimension(100, 25));

        lblPlayerStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPlayerStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayerStatus.setText("-");
        lblPlayerStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 0)));
        lblPlayerStatus.setMaximumSize(new java.awt.Dimension(100, 25));
        lblPlayerStatus.setMinimumSize(new java.awt.Dimension(100, 25));
        lblPlayerStatus.setOpaque(true);
        lblPlayerStatus.setPreferredSize(new java.awt.Dimension(100, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPlayerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(lblPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblPlayerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblPlayerName;
    private javax.swing.JLabel lblPlayerStatus;
    // End of variables declaration//GEN-END:variables
}
