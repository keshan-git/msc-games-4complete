package four.complete.ui.frames;

import communication.manager.core.CommunicationManagerListener;
import communication.manager.core.CommunicationServer;
import communication.manager.core.MessageReceiveListener;
import communication.manager.multicast.MulticastSender;
import four.complete.core.coms.packets.CurrentPlayerPacket;
import four.complete.core.coms.packets.GameStartPacket;
import four.complete.core.coms.packets.GameSummaryPacket;
import four.complete.core.coms.packets.LineSegmentPacket;
import four.complete.core.coms.packets.Packet;
import four.complete.core.coms.packets.PlayerTurnCompletePacket;
import four.complete.core.coms.packets.PlayerTurnPacket;
import four.complete.core.coms.packets.ScorePacket;
import four.complete.core.engine.GameEngine;
import four.complete.core.engine.TokenEngine;
import four.complete.core.events.GameEventListener;
import four.complete.model.GameBoardModel;
import four.complete.model.PlayerModel;
import four.complete.model.ScoreDataModel;
import four.complete.ui.model.PlayerListModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.UIManager;

/**
 *
 * @author Keshan De Silva
 */
public class GameServerUI extends javax.swing.JFrame
{
    private ActionListener backButtonActionListener;
    
    private int boardSize = 3;
    private boolean magicBox = true;

    private GameEngine gameEngine;
    private TokenEngine tokenEngine;
    private ArrayList<Color> colorList = new ArrayList<>();
    private FourCompleteUI fourCompleteUI;
    
    private PlayerListModel playerListModel;
    private MulticastSender multicastSender;
    
    public GameServerUI()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex){}
        initComponents();
        generateColorList();
        playerListModel = new PlayerListModel();
        listPlayers.setModel(playerListModel);
        
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
        lblSize3.setBackground(new Color(51, 153, 0));
        lblSize6.setBackground(new Color(240, 240, 240));
        lblSize8.setBackground(new Color(240, 240, 240));
        
        lblSize3.setForeground(Color.WHITE);
        lblSize6.setForeground(Color.BLACK);
        lblSize8.setForeground(Color.BLACK);
        
        boardSize = 3;
        
        lblYes.setBackground(new Color(51, 153, 0));
        lblNo.setBackground(new Color(240, 240, 240));
        
        lblYes.setForeground(Color.WHITE);
        lblNo.setForeground(Color.BLACK);
        
        magicBox = true;
        
        lblSize3.setEnabled(true);
        lblSize6.setEnabled(true);
        lblSize8.setEnabled(true);
        lblYes.setEnabled(true);
        lblNo.setEnabled(true);
        lblStartGameServer.setEnabled(true);
        lblStartGame.setEnabled(false);
        lblNote.setText("Please Start the Game Server");
    }
    
    private void generateColorList()
    {
        colorList.add(new Color(0,0,102));
        colorList.add(new Color(51, 153, 0));
        colorList.add(new Color(102, 0, 102));
        colorList.add(new Color(255, 102, 0));
        colorList.add(new Color(204, 0, 0));
        colorList.add(new Color(153, 153, 0));
        colorList.add(new Color(0, 153, 153));
        Collections.shuffle(colorList);
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        lblPositionLabel = new javax.swing.JLabel();
        lblSize3 = new javax.swing.JLabel();
        lblSize6 = new javax.swing.JLabel();
        lblSize8 = new javax.swing.JLabel();
        lblPositionLabel1 = new javax.swing.JLabel();
        lblYes = new javax.swing.JLabel();
        lblNo = new javax.swing.JLabel();
        lblPositionLabel2 = new javax.swing.JLabel();
        lblIP = new javax.swing.JLabel();
        lblStartGameServer = new javax.swing.JLabel();
        lblPlayers = new javax.swing.JLabel();
        lblStartGame = new javax.swing.JLabel();
        lblBack = new javax.swing.JLabel();
        lblNote = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPlayers = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Four-Complete [Server]");
        setMinimumSize(new java.awt.Dimension(414, 367));
        setPreferredSize(new java.awt.Dimension(414, 367));
        setResizable(false);

        lblPositionLabel.setBackground(new java.awt.Color(51, 153, 0));
        lblPositionLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPositionLabel.setForeground(new java.awt.Color(255, 255, 255));
        lblPositionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPositionLabel.setText("Board Size");
        lblPositionLabel.setMaximumSize(new java.awt.Dimension(76, 25));
        lblPositionLabel.setMinimumSize(new java.awt.Dimension(76, 25));
        lblPositionLabel.setOpaque(true);
        lblPositionLabel.setPreferredSize(new java.awt.Dimension(76, 25));

        lblSize3.setBackground(new java.awt.Color(51, 153, 0));
        lblSize3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSize3.setForeground(new java.awt.Color(255, 255, 255));
        lblSize3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSize3.setText("3 X 3");
        lblSize3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)));
        lblSize3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSize3.setMaximumSize(new java.awt.Dimension(54, 25));
        lblSize3.setMinimumSize(new java.awt.Dimension(54, 25));
        lblSize3.setOpaque(true);
        lblSize3.setPreferredSize(new java.awt.Dimension(54, 25));
        lblSize3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lblSize3MouseClicked(evt);
            }
        });

        lblSize6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSize6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSize6.setText("6 X 6");
        lblSize6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)));
        lblSize6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSize6.setMaximumSize(new java.awt.Dimension(54, 25));
        lblSize6.setMinimumSize(new java.awt.Dimension(54, 25));
        lblSize6.setOpaque(true);
        lblSize6.setPreferredSize(new java.awt.Dimension(54, 25));
        lblSize6.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lblSize6MouseClicked(evt);
            }
        });

        lblSize8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSize8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSize8.setText("8 X 8");
        lblSize8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)));
        lblSize8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSize8.setMaximumSize(new java.awt.Dimension(54, 25));
        lblSize8.setMinimumSize(new java.awt.Dimension(54, 25));
        lblSize8.setOpaque(true);
        lblSize8.setPreferredSize(new java.awt.Dimension(54, 25));
        lblSize8.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lblSize8MouseClicked(evt);
            }
        });

        lblPositionLabel1.setBackground(new java.awt.Color(51, 153, 0));
        lblPositionLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPositionLabel1.setForeground(new java.awt.Color(255, 255, 255));
        lblPositionLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPositionLabel1.setText("Magic Box");
        lblPositionLabel1.setMaximumSize(new java.awt.Dimension(76, 25));
        lblPositionLabel1.setMinimumSize(new java.awt.Dimension(76, 25));
        lblPositionLabel1.setOpaque(true);
        lblPositionLabel1.setPreferredSize(new java.awt.Dimension(76, 25));

        lblYes.setBackground(new java.awt.Color(51, 153, 0));
        lblYes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblYes.setForeground(new java.awt.Color(255, 255, 255));
        lblYes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblYes.setText("Yes");
        lblYes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)));
        lblYes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblYes.setMaximumSize(new java.awt.Dimension(54, 25));
        lblYes.setMinimumSize(new java.awt.Dimension(54, 25));
        lblYes.setOpaque(true);
        lblYes.setPreferredSize(new java.awt.Dimension(54, 25));
        lblYes.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lblYesMouseClicked(evt);
            }
        });

        lblNo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNo.setText("No");
        lblNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)));
        lblNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNo.setMaximumSize(new java.awt.Dimension(54, 25));
        lblNo.setMinimumSize(new java.awt.Dimension(54, 25));
        lblNo.setOpaque(true);
        lblNo.setPreferredSize(new java.awt.Dimension(54, 25));
        lblNo.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lblNoMouseClicked(evt);
            }
        });

        lblPositionLabel2.setBackground(new java.awt.Color(51, 153, 0));
        lblPositionLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPositionLabel2.setForeground(new java.awt.Color(255, 255, 255));
        lblPositionLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPositionLabel2.setText("Server IP");
        lblPositionLabel2.setMaximumSize(new java.awt.Dimension(76, 25));
        lblPositionLabel2.setMinimumSize(new java.awt.Dimension(76, 25));
        lblPositionLabel2.setOpaque(true);
        lblPositionLabel2.setPreferredSize(new java.awt.Dimension(76, 25));

        lblIP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIP.setToolTipText("");
        lblIP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)));
        lblIP.setMaximumSize(new java.awt.Dimension(54, 25));
        lblIP.setMinimumSize(new java.awt.Dimension(54, 25));
        lblIP.setOpaque(true);
        lblIP.setPreferredSize(new java.awt.Dimension(54, 25));

        lblStartGameServer.setBackground(new java.awt.Color(255, 0, 0));
        lblStartGameServer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStartGameServer.setForeground(new java.awt.Color(255, 255, 255));
        lblStartGameServer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStartGameServer.setText("Start Server");
        lblStartGameServer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblStartGameServer.setMaximumSize(new java.awt.Dimension(130, 25));
        lblStartGameServer.setMinimumSize(new java.awt.Dimension(130, 25));
        lblStartGameServer.setOpaque(true);
        lblStartGameServer.setPreferredSize(new java.awt.Dimension(130, 25));
        lblStartGameServer.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lblStartGameServerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                lblStartGameServerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                lblStartGameServerMouseExited(evt);
            }
        });

        lblPlayers.setBackground(new java.awt.Color(51, 153, 0));
        lblPlayers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPlayers.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayers.setText("Players");
        lblPlayers.setMaximumSize(new java.awt.Dimension(76, 25));
        lblPlayers.setMinimumSize(new java.awt.Dimension(76, 25));
        lblPlayers.setOpaque(true);
        lblPlayers.setPreferredSize(new java.awt.Dimension(76, 25));

        lblStartGame.setBackground(new java.awt.Color(255, 0, 0));
        lblStartGame.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblStartGame.setForeground(new java.awt.Color(255, 255, 255));
        lblStartGame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStartGame.setText("Start Game");
        lblStartGame.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblStartGame.setEnabled(false);
        lblStartGame.setMaximumSize(new java.awt.Dimension(130, 25));
        lblStartGame.setMinimumSize(new java.awt.Dimension(130, 25));
        lblStartGame.setOpaque(true);
        lblStartGame.setPreferredSize(new java.awt.Dimension(130, 25));
        lblStartGame.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lblStartGameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                lblStartGameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                lblStartGameMouseExited(evt);
            }
        });

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

        lblNote.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNote.setForeground(new java.awt.Color(255, 0, 0));
        lblNote.setText("Please Start the Game Server");

        listPlayers.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        listPlayers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        listPlayers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listPlayers.setSelectionBackground(new java.awt.Color(51, 153, 0));
        jScrollPane1.setViewportView(listPlayers);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNote, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPositionLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPositionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPositionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblYes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblNo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lblStartGameServer, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblIP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                        .addComponent(lblSize3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lblSize6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(lblSize8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 61, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lblNote)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblPositionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSize3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSize6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSize8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPositionLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblYes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPositionLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStartGameServer, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPlayers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(8, 8, 8)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblStartGameServerMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblStartGameServerMouseEntered
    {//GEN-HEADEREND:event_lblStartGameServerMouseEntered
        if (lblStartGameServer.isEnabled())
        {
            lblStartGameServer.setBackground(lblStartGameServer.getBackground().darker());
        }
    }//GEN-LAST:event_lblStartGameServerMouseEntered

    private void lblStartGameServerMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblStartGameServerMouseExited
    {//GEN-HEADEREND:event_lblStartGameServerMouseExited
        if (lblStartGameServer.isEnabled())
        {
            lblStartGameServer.setBackground(lblStartGameServer.getBackground().brighter());
        }
    }//GEN-LAST:event_lblStartGameServerMouseExited

    private void lblStartGameMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblStartGameMouseEntered
    {//GEN-HEADEREND:event_lblStartGameMouseEntered
        if (lblStartGame.isEnabled())
        {
            lblStartGame.setBackground(lblStartGameServer.getBackground().darker());
        }
    }//GEN-LAST:event_lblStartGameMouseEntered

    private void lblStartGameMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblStartGameMouseExited
    {//GEN-HEADEREND:event_lblStartGameMouseExited
        if (lblStartGame.isEnabled())
        {
            lblStartGame.setBackground(lblStartGameServer.getBackground().brighter());
        }
    }//GEN-LAST:event_lblStartGameMouseExited

    private void lblBackMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblBackMouseEntered
    {//GEN-HEADEREND:event_lblBackMouseEntered
        if (lblBack.isEnabled())
        {
            lblBack.setBackground(lblBack.getBackground().darker());
        }
    }//GEN-LAST:event_lblBackMouseEntered

    private void lblBackMouseExited(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblBackMouseExited
    {//GEN-HEADEREND:event_lblBackMouseExited
        if (lblBack.isEnabled())
        {
            lblBack.setBackground(lblBack.getBackground().brighter());
        }
    }//GEN-LAST:event_lblBackMouseExited

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblBackMouseClicked
    {//GEN-HEADEREND:event_lblBackMouseClicked
        if (backButtonActionListener != null)
        {
            backButtonActionListener.actionPerformed(null);
        }
        
        this.setVisible(false);
    }//GEN-LAST:event_lblBackMouseClicked

    private void lblSize3MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblSize3MouseClicked
    {//GEN-HEADEREND:event_lblSize3MouseClicked
        if (lblSize3.isEnabled())
        {
            lblSize3.setBackground(new Color(51, 153, 0));
            lblSize6.setBackground(new Color(240, 240, 240));
            lblSize8.setBackground(new Color(240, 240, 240));

            lblSize3.setForeground(Color.WHITE);
            lblSize6.setForeground(Color.BLACK);
            lblSize8.setForeground(Color.BLACK);

            boardSize = 3;
        }
    }//GEN-LAST:event_lblSize3MouseClicked

    private void lblSize6MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblSize6MouseClicked
    {//GEN-HEADEREND:event_lblSize6MouseClicked
        if (lblSize6.isEnabled())
        {
            lblSize6.setBackground(new Color(51, 153, 0));
            lblSize3.setBackground(new Color(240, 240, 240));
            lblSize8.setBackground(new Color(240, 240, 240));

            lblSize6.setForeground(Color.WHITE);
            lblSize3.setForeground(Color.BLACK);
            lblSize8.setForeground(Color.BLACK);

            boardSize = 6;
        }
    }//GEN-LAST:event_lblSize6MouseClicked

    private void lblSize8MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblSize8MouseClicked
    {//GEN-HEADEREND:event_lblSize8MouseClicked
        if (lblSize8.isEnabled())
        {
            lblSize8.setBackground(new Color(51, 153, 0));
            lblSize6.setBackground(new Color(240, 240, 240));
            lblSize3.setBackground(new Color(240, 240, 240));

            lblSize8.setForeground(Color.WHITE);
            lblSize6.setForeground(Color.BLACK);
            lblSize3.setForeground(Color.BLACK);

            boardSize = 8;
        }
    }//GEN-LAST:event_lblSize8MouseClicked

    private void lblYesMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblYesMouseClicked
    {//GEN-HEADEREND:event_lblYesMouseClicked
        if (lblSize8.isEnabled())
        {
            lblYes.setBackground(new Color(51, 153, 0));
            lblNo.setBackground(new Color(240, 240, 240));

            lblYes.setForeground(Color.WHITE);
            lblNo.setForeground(Color.BLACK);

            magicBox = true;
        }
    }//GEN-LAST:event_lblYesMouseClicked

    private void lblNoMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblNoMouseClicked
    {//GEN-HEADEREND:event_lblNoMouseClicked
        if (lblSize8.isEnabled())
        {
            lblNo.setBackground(new Color(51, 153, 0));
            lblYes.setBackground(new Color(240, 240, 240));

            lblNo.setForeground(Color.WHITE);
            lblYes.setForeground(Color.BLACK);

            magicBox = false;
        }
    }//GEN-LAST:event_lblNoMouseClicked

    private void lblStartGameServerMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblStartGameServerMouseClicked
    {//GEN-HEADEREND:event_lblStartGameServerMouseClicked
        startGameServer();
        startMulticast();
        
        lblSize3.setEnabled(false);
        lblSize6.setEnabled(false);
        lblSize8.setEnabled(false);
        lblYes.setEnabled(false);
        lblNo.setEnabled(false);
        lblStartGameServer.setEnabled(false);
        lblStartGame.setEnabled(true);
        
        lblNote.setText("Please Start the Game after all Players are connected");
    }//GEN-LAST:event_lblStartGameServerMouseClicked

    private void lblStartGameMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lblStartGameMouseClicked
    {//GEN-HEADEREND:event_lblStartGameMouseClicked
        startGame();
        stopMulticast();
    }//GEN-LAST:event_lblStartGameMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblIP;
    private javax.swing.JLabel lblNo;
    private javax.swing.JLabel lblNote;
    private javax.swing.JLabel lblPlayers;
    private javax.swing.JLabel lblPositionLabel;
    private javax.swing.JLabel lblPositionLabel1;
    private javax.swing.JLabel lblPositionLabel2;
    private javax.swing.JLabel lblSize3;
    private javax.swing.JLabel lblSize6;
    private javax.swing.JLabel lblSize8;
    private javax.swing.JLabel lblStartGame;
    private javax.swing.JLabel lblStartGameServer;
    private javax.swing.JLabel lblYes;
    private javax.swing.JList listPlayers;
    // End of variables declaration//GEN-END:variables

    private void startGameServer()
    {
        CommunicationServer communicationServer = new CommunicationServer();
        communicationServer.startCommunication(1234);
        lblIP.setText(communicationServer.getServerSocketInfo());
        
        communicationServer.addMessageReceiveListener(new MessageReceiveListener()
        {
            @Override
            public void onMessageReceived(String message, int clientID)
            {
                System.out.println("Received : " + clientID + " : " + message);
            }
        });
        
        communicationServer.addCommunicationManagerListener(new CommunicationManagerListener()
        {
            @Override
            public void onConnected(int clientID, Socket communicationSocket)
            {
                System.out.println("Connected : " + clientID + " : " + communicationSocket.getInetAddress().getHostName() + " : " 
                                            + communicationSocket.getRemoteSocketAddress().toString());
                playerListModel.addPlayer(communicationSocket);
                listPlayers.updateUI();
            }

            @Override
            public void onDisconnected(int clientID, Socket communicationSocket)
            {
                System.out.println("Disconnected : " + clientID + " : " + communicationSocket.getInetAddress().getHostName());
                playerListModel.removePlayer(communicationSocket);
            }
        });
        
        // Generate Game Details
        gameEngine = new GameEngine(GameEngine.GameSetupType.GAME_SERVER, communicationServer);
        gameEngine.addGameEventListener(new GameEventListener()
        {
            @Override
            public void onLineSegmentAdded(LineSegmentPacket lineSegmentPacket)
            {
                gameEngine.getGameBoardModel().addLineSegment(lineSegmentPacket.getLineSegmentModel());
                gameEngine.processBoxComplete(lineSegmentPacket.getLineSegmentModel());
                fourCompleteUI.getGameCanvas().repaint();
                
                ScoreDataModel scoreDataModel = gameEngine.calculateScoreData();
                for (int i = 0; i < ((CommunicationServer)gameEngine.getCommunicationManager()).getClientCount(); i++)
                {
                    PlayerModel player = gameEngine.getPlayerModelList().get(i + 1);
                    ScorePacket scorePacket = new ScorePacket(
                            scoreDataModel.getPlayerScore(player), scoreDataModel.getPlayerPosition(player));
                    gameEngine.sendToPlayer(i + 1, Packet.SCORE_PACKET, scorePacket);  
                }

                // Check for game complete
                if (gameEngine.isGameComplete())
                {
                    GameSummaryPacket gameSummaryPacket = new GameSummaryPacket(gameEngine.calculateScoreData());
                    gameEngine.sendToAllPlayers(Packet.GAME_COMPLETE_PACKET, gameSummaryPacket);
                    
                    // Server Game Complete UI update
                    fourCompleteUI.showGameCompleteScreen(gameEngine.calculateScoreData());
                }
            }   

            @Override
            public void onPlayerTurnComplete(PlayerTurnCompletePacket playerTurnCompletePacket)
            {
                tokenEngine.nextPlayer();
                gameEngine.sendToPlayer(tokenEngine.getCurrentPlayerIndex(), Packet.PLAYER_TURN_PACKET, new PlayerTurnPacket());
                
                                
                int gameProgress = gameEngine.calculateScoreData().getGameProgress();
                CurrentPlayerPacket currentPlayerPacket = new CurrentPlayerPacket(
                        gameEngine.getPlayerModelList().get(tokenEngine.getCurrentPlayerIndex()), gameProgress);
                gameEngine.sendToAllPlayers(Packet.CURRENT_PLAYER_PACKET, currentPlayerPacket);
                
                GameSummaryPacket gameSummaryPacket = new GameSummaryPacket(gameEngine.calculateScoreData());
                gameEngine.sendToAllPlayers(Packet.GAME_SUMMARY_PACKET, gameSummaryPacket);
                
                for (GameEventListener gameEventListener : gameEngine.getGameEventListeners())
                {
                    gameEventListener.onPlayerTurnUpdate(currentPlayerPacket);
                    gameEventListener.onGameSummaryUpdate(gameSummaryPacket);
                }
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

    private void startMulticast()
    {
        multicastSender = new MulticastSender();
        multicastSender.startMulticastCommunication(8888);
    }
        
    private void startGame()
    {
        ArrayList<PlayerModel> playerModelList = generatePlayerDetails();
        
        GameBoardModel gameBoardModel = new GameBoardModel();
        gameBoardModel.setBoardSize(boardSize);
        gameBoardModel.setMagicBoxEnabled(magicBox);
        
        gameEngine.setGameBoardModel(gameBoardModel);
        gameEngine.setPlayerModelList(playerModelList);
        gameEngine.setCurrentPlayer(playerModelList.get(0));
        gameEngine.setPlayerTurn(true);
        
        tokenEngine = TokenEngine.getInstance();
        tokenEngine.initialized(gameEngine);
        
        fourCompleteUI = new FourCompleteUI(gameEngine);
        fourCompleteUI.setSize(getSize());
        fourCompleteUI.setLocation(getLocation());
        fourCompleteUI.setVisible(true);
        this.setVisible(false);
        
        fourCompleteUI.getGameStatusPanel().setCurrentPlayer(playerModelList.get(0));
        
        // Send to other players
        GameStartPacket gameStartPacket = new GameStartPacket(playerModelList, gameBoardModel);
        CurrentPlayerPacket currentPlayerPacket = new CurrentPlayerPacket(playerModelList.get(0), 0);
        GameSummaryPacket gameSummaryPacket = new GameSummaryPacket(gameEngine.calculateScoreData());

        for (int i = 0; i < ((CommunicationServer)gameEngine.getCommunicationManager()).getClientCount(); i++)
        {
            gameStartPacket.setCurrentPlayer(playerModelList.get(i + 1));
            gameEngine.sendToPlayer(i + 1, Packet.GAME_START_PACKET, gameStartPacket);  
            gameEngine.sendToPlayer(i + 1, Packet.CURRENT_PLAYER_PACKET, currentPlayerPacket);  
            gameEngine.sendToPlayer(i + 1, Packet.GAME_SUMMARY_PACKET, gameSummaryPacket);  
        }
    }
    
    private void stopMulticast()
    {
        multicastSender.stopCommunication();
    }
        
    private ArrayList<PlayerModel> generatePlayerDetails()
    {
        ArrayList<PlayerModel> playerModelList = new ArrayList<>();

        if (gameEngine.getGameSetupType().equals(GameEngine.GameSetupType.GAME_SERVER))
        {
            HashMap<Integer, Socket> clientSocketList = 
                    ((CommunicationServer)gameEngine.getCommunicationManager()).getClientSocketSet();
            
            // Add Server Player
            int colorIndex = 0;
            playerModelList.add(new PlayerModel(((CommunicationServer)gameEngine.getCommunicationManager()).
                getServerSocket().getInetAddress().getHostName(),colorList.get(0)));
            
            for (Socket socket : clientSocketList.values())
            {
                playerModelList.add(new PlayerModel(socket.getInetAddress().getHostName(),
                        colorList.get(++colorIndex % colorList.size())));
            }    
        }
        
        return playerModelList;
    }



}
