package four.complete.ui.panels;

import four.complete.model.PlayerModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Timer;

/**
 *
 * @author Keshan De Silva
 */
public class GameStatusPanel extends javax.swing.JPanel
{
    private Color playerColor;
    private String playerName;
    private String currentPlayerName;
    private int score;
    private int position;
    private int progress;
    
    private Timer animationTimer;
    
    public GameStatusPanel()
    {
        initComponents();
        animationTimer = new Timer(500, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (lblCurrentTurn.getForeground().equals(Color.WHITE))
                {
                    lblCurrentTurn.setBackground(new Color(240, 240, 240));
                    lblCurrentTurn.setForeground(playerColor);
                }
                else
                {
                    lblCurrentTurn.setBackground(playerColor);
                    lblCurrentTurn.setForeground(Color.WHITE);
                }
            }
        });
    }

    public void setPlayerColor(Color playerColor)
    {
        this.playerColor = playerColor;
        lblPlayerName.setBackground(playerColor);
        lblScoreLabel.setBackground(playerColor);
        lblScore.setBorder(BorderFactory.createLineBorder(playerColor));
        lblPositionLabel.setBackground(playerColor);
        lblPosition.setBorder(BorderFactory.createLineBorder(playerColor));
        lblProgressLabel.setBackground(playerColor);
        lblProgress.setBorder(BorderFactory.createLineBorder(playerColor));
        lblCurrentTurn.setBackground(playerColor);
        lblCurrentTurn.setBorder(BorderFactory.createLineBorder(playerColor));
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
        lblPlayerName.setText(playerName);
    }

    public void setCurrentPlayer(PlayerModel playerModel)
    {
        this.currentPlayerName = playerModel.getPlayerName();
        lblCurrentTurn.setBorder(BorderFactory.createLineBorder(playerModel.getPlayerColor()));
        lblCurrentTurn.setBackground(playerModel.getPlayerColor());
        
        if (currentPlayerName.equals(playerName))
        {
            lblCurrentTurn.setText("Your Turn");
            animationTimer.start();
        }
        else
        {
            lblCurrentTurn.setText(currentPlayerName);
            animationTimer.stop();
            lblCurrentTurn.setForeground(Color.WHITE);
        }
    }

    public void setScore(int score)
    {
        this.score = score;
        lblScore.setText(score + "");
    }

    public void setPosition(int position)
    {
        this.position = position;
        switch (position)
        {
            case -1 :
            {
                lblPosition.setText("-");
                break;
            }
            case 1 :
            {
                lblPosition.setText("<html>1<sup>st</sup></html>");
                break;
            }
            case 2 :
            {
                lblPosition.setText("<html>2<sup>nd</sup></html>");
                break;
            }
            case 3 :
            {
                lblPosition.setText("<html>3<sup>rd</sup></html>");
                break;
            }
            default :
            {
                lblPosition.setText("<html>" + position + "<sup>th</sup></html>");
                break;
            }
        }
    }

    public void setProgress(int progress)
    {
        this.progress = progress;
        this.lblProgress.setText(progress + "%");  
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lblPlayerName = new javax.swing.JLabel();
        lblScoreLabel = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        lblProgressLabel = new javax.swing.JLabel();
        lblProgress = new javax.swing.JLabel();
        lblCurrentTurn = new javax.swing.JLabel();
        lblPositionLabel = new javax.swing.JLabel();
        lblPosition = new javax.swing.JLabel();

        lblPlayerName.setBackground(new java.awt.Color(51, 255, 0));
        lblPlayerName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPlayerName.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayerName.setText("Keshan-PC");
        lblPlayerName.setMaximumSize(new java.awt.Dimension(130, 25));
        lblPlayerName.setMinimumSize(new java.awt.Dimension(130, 25));
        lblPlayerName.setOpaque(true);
        lblPlayerName.setPreferredSize(new java.awt.Dimension(130, 25));

        lblScoreLabel.setBackground(new java.awt.Color(51, 255, 0));
        lblScoreLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblScoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        lblScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScoreLabel.setText("Score");
        lblScoreLabel.setMaximumSize(new java.awt.Dimension(76, 25));
        lblScoreLabel.setMinimumSize(new java.awt.Dimension(76, 25));
        lblScoreLabel.setOpaque(true);
        lblScoreLabel.setPreferredSize(new java.awt.Dimension(55, 25));

        lblScore.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScore.setText("Score");
        lblScore.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        lblScore.setMaximumSize(new java.awt.Dimension(80, 25));
        lblScore.setMinimumSize(new java.awt.Dimension(80, 25));
        lblScore.setOpaque(true);
        lblScore.setPreferredSize(new java.awt.Dimension(80, 25));

        lblProgressLabel.setBackground(new java.awt.Color(51, 255, 0));
        lblProgressLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblProgressLabel.setForeground(new java.awt.Color(255, 255, 255));
        lblProgressLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProgressLabel.setText("Progress");
        lblProgressLabel.setMaximumSize(new java.awt.Dimension(76, 25));
        lblProgressLabel.setMinimumSize(new java.awt.Dimension(76, 25));
        lblProgressLabel.setOpaque(true);
        lblProgressLabel.setPreferredSize(new java.awt.Dimension(76, 25));

        lblProgress.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblProgress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProgress.setText("100%");
        lblProgress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        lblProgress.setMaximumSize(new java.awt.Dimension(80, 19));
        lblProgress.setMinimumSize(new java.awt.Dimension(80, 19));
        lblProgress.setOpaque(true);
        lblProgress.setPreferredSize(new java.awt.Dimension(80, 19));

        lblCurrentTurn.setBackground(new java.awt.Color(51, 255, 0));
        lblCurrentTurn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCurrentTurn.setForeground(new java.awt.Color(255, 255, 255));
        lblCurrentTurn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurrentTurn.setText("-");
        lblCurrentTurn.setMaximumSize(new java.awt.Dimension(100, 25));
        lblCurrentTurn.setMinimumSize(new java.awt.Dimension(100, 25));
        lblCurrentTurn.setOpaque(true);
        lblCurrentTurn.setPreferredSize(new java.awt.Dimension(100, 25));

        lblPositionLabel.setBackground(new java.awt.Color(51, 255, 0));
        lblPositionLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPositionLabel.setForeground(new java.awt.Color(255, 255, 255));
        lblPositionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPositionLabel.setText("Place");
        lblPositionLabel.setMaximumSize(new java.awt.Dimension(76, 25));
        lblPositionLabel.setMinimumSize(new java.awt.Dimension(76, 25));
        lblPositionLabel.setOpaque(true);
        lblPositionLabel.setPreferredSize(new java.awt.Dimension(76, 25));

        lblPosition.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPosition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPosition.setText("<html>1<sup>st<sup></html>");
        lblPosition.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)));
        lblPosition.setMaximumSize(new java.awt.Dimension(54, 25));
        lblPosition.setMinimumSize(new java.awt.Dimension(54, 25));
        lblPosition.setOpaque(true);
        lblPosition.setPreferredSize(new java.awt.Dimension(54, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPositionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPlayerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblScoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblProgressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCurrentTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCurrentTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblProgressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPositionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPosition, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCurrentTurn;
    private javax.swing.JLabel lblPlayerName;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel lblPositionLabel;
    private javax.swing.JLabel lblProgress;
    private javax.swing.JLabel lblProgressLabel;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblScoreLabel;
    // End of variables declaration//GEN-END:variables
}
