package four.complete.ui.frames;

import communication.manager.core.CommunicationClient;
import communication.manager.core.CommunicationManagerListener;
import communication.manager.core.MessageReceiveListener;
import communication.manager.multicast.MulticastMessageReceiveListener;
import communication.manager.multicast.MulticastReceiver;
import four.complete.core.coms.packets.CurrentPlayerPacket;
import four.complete.core.coms.packets.GameStartPacket;
import four.complete.core.coms.packets.GameSummaryPacket;
import four.complete.core.coms.packets.LineSegmentPacket;
import four.complete.core.coms.packets.PlayerTurnPacket;
import four.complete.core.coms.packets.ScorePacket;
import four.complete.core.engine.GameEngine;
import four.complete.core.events.GameEventListener;
import four.complete.model.ScoreDataModel;
import four.complete.ui.model.GameServersListModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;

/**
 *
 * @author Keshan De Silva
 */
public class GameClientUI extends javax.swing.JFrame
{
    private ActionListener backButtonActionListener;

    private GameEngine gameEngine;
    private FourCompleteUI fourCompleteUI;
    private GameServersListModel gameServersListModel;
    
    private MulticastReceiver multicastReceiver;
    
    public GameClientUI()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex){}
        initComponents();
        gameServersListModel = new GameServersListModel();
        listServers.setModel(gameServersListModel);
        
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                gameEngine.getCommunicationManager().stopCommunication();
                System.exit(0);
            }    
        });
    }

    public void setBackButtonActionListener(ActionListener backButtonActionListener)
    {
        this.backButtonActionListener = backButtonActionListener;
    }
    
    public void clearData()
    {
        lblNote.setText("Please Select a Game Server and 'Connet'");
        txtIP.setEnabled(true);
        listServers.setEnabled(true);
        lblConnect.setEnabled(true);
        gameServersListModel.clear();
        
        multicastReceiver = new MulticastReceiver();
        multicastReceiver.startMulticastCommunication(8888);
        multicastReceiver.addMulticastMessageReceiveListener(new MulticastMessageReceiveListener()
        {
            @Override
            public void onMessageReceived(String message, InetAddress address)
            {
                gameServersListModel.addServerIP(address.getHostAddress());
                System.out.println("A");
            }
        });
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lblPositionLabel2 = new javax.swing.JLabel();
        lblConnect = new javax.swing.JLabel();
        lblPlayers = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listServers = new javax.swing.JList();
        lblBack = new javax.swing.JLabel();
        txtIP = new javax.swing.JTextField();
        lblNote = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Four-Complete [Player]");
        setMinimumSize(new java.awt.Dimension(414, 367));
        setPreferredSize(new java.awt.Dimension(414, 367));
        setResizable(false);

        lblPositionLabel2.setBackground(new java.awt.Color(51, 153, 0));
        lblPositionLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPositionLabel2.setForeground(new java.awt.Color(255, 255, 255));
        lblPositionLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPositionLabel2.setText("Server IP");
        lblPositionLabel2.setMaximumSize(new java.awt.Dimension(76, 25));
        lblPositionLabel2.setMinimumSize(new java.awt.Dimension(76, 25));
        lblPositionLabel2.setOpaque(true);
        lblPositionLabel2.setPreferredSize(new java.awt.Dimension(76, 25));

        lblConnect.setBackground(new java.awt.Color(255, 0, 0));
        lblConnect.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblConnect.setForeground(new java.awt.Color(255, 255, 255));
        lblConnect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConnect.setText("Connect");
        lblConnect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblConnect.setMaximumSize(new java.awt.Dimension(130, 25));
        lblConnect.setMinimumSize(new java.awt.Dimension(130, 25));
        lblConnect.setOpaque(true);
        lblConnect.setPreferredSize(new java.awt.Dimension(130, 25));
        lblConnect.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lblConnectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                lblConnectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                lblConnectMouseExited(evt);
            }
        });

        lblPlayers.setBackground(new java.awt.Color(51, 153, 0));
        lblPlayers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPlayers.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayers.setText("Servers");
        lblPlayers.setMaximumSize(new java.awt.Dimension(76, 25));
        lblPlayers.setMinimumSize(new java.awt.Dimension(76, 25));
        lblPlayers.setOpaque(true);
        lblPlayers.setPreferredSize(new java.awt.Dimension(76, 25));

        listServers.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        listServers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        listServers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listServers.setSelectionBackground(new java.awt.Color(51, 153, 0));
        listServers.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                listServersValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listServers);

        lblBack.setBackground(new java.awt.Color(51, 153, 0));
        lblBack.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblBack.setForeground(new java.awt.Color(255, 255, 255));
        lblBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBack.setText("Back");
        lblBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBack.setMaximumSize(new java.awt.Dimension(130, 25));
        lblBack.setMinimumSize(new java.awt.Dimension(130, 25));
        lblBack.setOpaque(true);
        lblBack.setPreferredSize(new java.awt.Dimension(130, 25));
        lblBack.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lblBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                lblBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                lblBackMouseExited(evt);
            }
        });

        txtIP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtIP.setText("0.0.0.0");
        txtIP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)));

        lblNote.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNote.setForeground(new java.awt.Color(255, 0, 0));
        lblNote.setText("Please Select a Game Server and 'Connet'");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblPositionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblBack, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lblNote)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPositionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBack, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblConnectMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblConnectMouseEntered
    {//GEN-HEADEREND:event_lblConnectMouseEntered
        if (lblConnect.isEnabled())
        {
            lblConnect.setBackground(lblConnect.getBackground().darker());
        }
    }//GEN-LAST:event_lblConnectMouseEntered

    private void lblConnectMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblConnectMouseExited
    {//GEN-HEADEREND:event_lblConnectMouseExited
        if (lblConnect.isEnabled())
        {
            lblConnect.setBackground(lblConnect.getBackground().brighter());
        }
    }//GEN-LAST:event_lblConnectMouseExited

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblBackMouseClicked
    {//GEN-HEADEREND:event_lblBackMouseClicked
        if (backButtonActionListener != null)
        {
            backButtonActionListener.actionPerformed(null);
        }
        
        this.setVisible(false);
    }//GEN-LAST:event_lblBackMouseClicked

    private void lblBackMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblBackMouseEntered
    {//GEN-HEADEREND:event_lblBackMouseEntered
        lblBack.setBackground(lblBack.getBackground().darker());
    }//GEN-LAST:event_lblBackMouseEntered

    private void lblBackMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblBackMouseExited
    {//GEN-HEADEREND:event_lblBackMouseExited
        lblBack.setBackground(lblBack.getBackground().brighter());
    }//GEN-LAST:event_lblBackMouseExited

    private void lblConnectMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblConnectMouseClicked
    {//GEN-HEADEREND:event_lblConnectMouseClicked
        multicastReceiver.stopCommunication();
        connectToGameServer();
        
        txtIP.setEnabled(false);
        lblConnect.setEnabled(false);
        listServers.setEnabled(false);
        lblNote.setText("Please wait... Game will start when Server is ready");
    }//GEN-LAST:event_lblConnectMouseClicked

    private void listServersValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_listServersValueChanged
    {//GEN-HEADEREND:event_listServersValueChanged
        txtIP.setText(listServers.getSelectedValue().toString());
    }//GEN-LAST:event_listServersValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblConnect;
    private javax.swing.JLabel lblNote;
    private javax.swing.JLabel lblPlayers;
    private javax.swing.JLabel lblPositionLabel2;
    private javax.swing.JList listServers;
    private javax.swing.JTextField txtIP;
    // End of variables declaration//GEN-END:variables

    private void connectToGameServer()
    {
        CommunicationClient communicationClient = new CommunicationClient();
        communicationClient.startCommunication(txtIP.getText(), 1234);
        
        communicationClient.addMessageReceiveListener(new MessageReceiveListener()
        {
            @Override
            public void onMessageReceived(String message, int clientID)
            {
                System.out.println("Received " + clientID + " : " + message);
            }
        });
        
        communicationClient.addCommunicationManagerListener(new CommunicationManagerListener()
        {

            @Override
            public void onConnected(int clientID, Socket communicationSocket)
            {
                System.out.println("Connected : " + clientID + " : " + communicationSocket.getInetAddress().getHostName());
            }

            @Override
            public void onDisconnected(int clientID, Socket communicationSocket)
            {
                System.out.println("Disconnected : " + clientID + " : " + communicationSocket.getInetAddress().getHostName());
            }
        });
        
        // Generate Game Details
        gameEngine = new GameEngine(GameEngine.GameSetupType.GAME_CLIENT, communicationClient);
        gameEngine.addGameEventListener(new GameEventListener()
        {
            @Override
            public void onGameStart(GameStartPacket gameStartPacket)
            {
                gameEngine.setGameBoardModel(gameStartPacket.getGameBoardModel());
                gameEngine.setPlayerModelList(gameStartPacket.getPlayerList());
                gameEngine.setCurrentPlayer(gameStartPacket.getCurrentPlayer());
                fourCompleteUI = new FourCompleteUI(gameEngine);
                fourCompleteUI.setSize(getSize());
                fourCompleteUI.setLocation(getLocation());
                fourCompleteUI.setVisible(true);
                setVisible(false);
            }

            @Override
            public void onLineSegmentAdded(LineSegmentPacket lineSegmentPacket)
            {
                gameEngine.getGameBoardModel().addLineSegment(lineSegmentPacket.getLineSegmentModel());
                gameEngine.processBoxComplete(lineSegmentPacket.getLineSegmentModel());
                fourCompleteUI.getGameCanvas().repaint();
                
                // Check for game complete
                if (gameEngine.isGameComplete())
                {
                    // Server Game Complete UI update
                    fourCompleteUI.showGameCompleteScreen(gameEngine.calculateScoreData());
                }
            }  

            @Override
            public void onPlayerTurnStart(PlayerTurnPacket playerTurnPacket)
            {
                gameEngine.setPlayerTurn(true);
            }   

            @Override
            public void onGameComplete(GameSummaryPacket gameSummaryPacket)
            {
                ScoreDataModel scoreDataModel = new ScoreDataModel();
                scoreDataModel.setScoreMap(gameSummaryPacket.getScoreMap());
                scoreDataModel.setPositionList(gameSummaryPacket.getPositionList());
                fourCompleteUI.showGameCompleteScreen(scoreDataModel);
            }  

            @Override
            public void onPlayerTurnUpdate(CurrentPlayerPacket currentPlayerPacket)
            {
                fourCompleteUI.getGameStatusPanel().setCurrentPlayer(currentPlayerPacket.getCurrentPlayer());
                fourCompleteUI.getGameStatusPanel().setProgress(currentPlayerPacket.getGameProgress());
            }

            @Override
            public void onScoreUpdate(ScorePacket scorePacket)
            {
                fourCompleteUI.getGameStatusPanel().setScore(scorePacket.getScore());
                fourCompleteUI.getGameStatusPanel().setPosition(scorePacket.getPosition());
            } 

            @Override
            public void onGameSummaryUpdate(GameSummaryPacket gameSummaryPacket)
            {
                fourCompleteUI.getPlayersDetailsPanel().updateGameSummary(gameSummaryPacket);
            }
        });
    }

}
