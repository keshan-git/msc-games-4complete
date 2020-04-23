package four.complete.ui.frames;

import four.complete.core.engine.GameEngine;
import four.complete.model.ScoreDataModel;
import four.complete.ui.model.GameResultTableModel;
import javax.swing.UIManager;

/**
 *
 * @author Keshan De Silva
 */
public class GameComplete extends javax.swing.JFrame
{
    private GameResultTableModel gameResultTableModel = new GameResultTableModel();
    
    public GameComplete()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex){}
        initComponents();
        
        tableResult.setModel(gameResultTableModel);
    }

    public void displayGameResut(GameEngine gameEngine, ScoreDataModel scoreDataModel)
    {
        gameEngine.getCommunicationManager().stopCommunication();
        gameResultTableModel.updateData(scoreDataModel);
        this.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lblMainMenu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableResult = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(414, 367));
        setPreferredSize(new java.awt.Dimension(414, 367));

        lblMainMenu.setBackground(new java.awt.Color(51, 153, 0));
        lblMainMenu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMainMenu.setForeground(new java.awt.Color(255, 255, 255));
        lblMainMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMainMenu.setText("Main Menu");
        lblMainMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMainMenu.setMaximumSize(new java.awt.Dimension(130, 25));
        lblMainMenu.setMinimumSize(new java.awt.Dimension(130, 25));
        lblMainMenu.setOpaque(true);
        lblMainMenu.setPreferredSize(new java.awt.Dimension(130, 25));
        lblMainMenu.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lblMainMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                lblMainMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                lblMainMenuMouseExited(evt);
            }
        });

        tableResult.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tableResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Place", "Player Name", "Score"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableResult);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(75, 75, 75))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addComponent(lblMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblMainMenuMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblMainMenuMouseClicked
    {//GEN-HEADEREND:event_lblMainMenuMouseClicked
        this.setVisible(false);
        new MainMenu().setVisible(true);
    }//GEN-LAST:event_lblMainMenuMouseClicked

    private void lblMainMenuMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblMainMenuMouseEntered
    {//GEN-HEADEREND:event_lblMainMenuMouseEntered
        lblMainMenu.setBackground(lblMainMenu.getBackground().darker());
    }//GEN-LAST:event_lblMainMenuMouseEntered

    private void lblMainMenuMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblMainMenuMouseExited
    {//GEN-HEADEREND:event_lblMainMenuMouseExited
        lblMainMenu.setBackground(lblMainMenu.getBackground().brighter());
    }//GEN-LAST:event_lblMainMenuMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMainMenu;
    private javax.swing.JTable tableResult;
    // End of variables declaration//GEN-END:variables
}
