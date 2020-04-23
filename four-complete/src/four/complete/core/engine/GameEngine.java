package four.complete.core.engine;

import communication.manager.core.CommunicationClient;
import communication.manager.core.CommunicationManager;
import communication.manager.core.CommunicationServer;
import communication.manager.core.MessageReceiveListener;
import four.complete.core.coms.packets.CurrentPlayerPacket;
import four.complete.core.coms.packets.GameStartPacket;
import four.complete.core.coms.packets.GameSummaryPacket;
import four.complete.core.coms.packets.LineSegmentPacket;
import four.complete.core.coms.packets.Packet;
import static four.complete.core.coms.packets.Packet.CURRENT_PLAYER_PACKET;
import static four.complete.core.coms.packets.Packet.GAME_COMPLETE_PACKET;
import static four.complete.core.coms.packets.Packet.GAME_START_PACKET;
import static four.complete.core.coms.packets.Packet.GAME_SUMMARY_PACKET;
import static four.complete.core.coms.packets.Packet.LINE_SEGMENT_PACKET;
import static four.complete.core.coms.packets.Packet.PLAYER_TURN_COMPLETE_PACKET;
import static four.complete.core.coms.packets.Packet.PLAYER_TURN_PACKET;
import static four.complete.core.coms.packets.Packet.SCORE_PACKET;
import four.complete.core.coms.packets.PlayerTurnCompletePacket;
import four.complete.core.coms.packets.PlayerTurnPacket;
import four.complete.core.coms.packets.ScorePacket;
import four.complete.core.events.GameEventListener;
import four.complete.model.BoxModel;
import four.complete.model.GameBoardModel;
import four.complete.model.LineSegmentModel;
import four.complete.model.PlayerModel;
import four.complete.model.ScoreDataModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author Keshan De Silva
 */
public class GameEngine
{
    public enum GameSetupType {GAME_SERVER, GAME_CLIENT};
    
    private CommunicationManager communicationManager;
    private GameSetupType gameSetupType;
    private GameBoardModel gameBoardModel;
    private ArrayList<PlayerModel> playerModelList;
    private PlayerModel currentPlayer;
    private boolean playerTurn;
        
    private ArrayList<GameEventListener> gameEventListeners = new ArrayList<>();
    
    public GameEngine(GameSetupType gameSetupType, CommunicationManager communicationManager)
    {
        this.communicationManager = communicationManager;
        this.gameSetupType = gameSetupType;
        
        communicationManager.addMessageReceiveListener(new MessageReceiveListener()
        {
            @Override
            public void onMessageReceived(String message, int clientID)
            {
                processPacket(message, clientID);
            }
        });
    }

    public void addGameEventListener(GameEventListener listener)
    {
        gameEventListeners.add(listener);
    }
    
    public ArrayList<GameEventListener> getGameEventListeners()
    {
        return gameEventListeners;
    }
    
    public GameBoardModel getGameBoardModel()
    {
        return gameBoardModel;
    }

    public void setGameBoardModel(GameBoardModel gameBoardModel)
    {
        this.gameBoardModel = gameBoardModel;
    }  

    public PlayerModel getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerModel currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<PlayerModel> getPlayerModelList()
    {
        return playerModelList;
    }

    public void setPlayerModelList(ArrayList<PlayerModel> playerModelList)
    {
        this.playerModelList = playerModelList;
    }

    public CommunicationManager getCommunicationManager()
    {
        return communicationManager;
    }

    public GameSetupType getGameSetupType()
    {
        return gameSetupType;
    }

    public boolean isPlayerTurn()
    {
        return playerTurn;
    }
    
    public void setPlayerTurn(boolean playerTurn)
    {
        this.playerTurn = playerTurn;
    }

    public boolean sendToAllPlayers(String packetType, Packet packet)
    {
        if (gameSetupType.equals(GameSetupType.GAME_SERVER))
        {
            CommunicationServer communicationServer = (CommunicationServer)communicationManager;
            for (int i = 0; i < communicationServer.getClientCount(); i++)
            {
                communicationServer.sendString(i + 1, packetType + packet.getDataStream());
            }
            return true;
        }
        return false;
    }
    
    public boolean sendToPlayer(int clientID, String packetType, Packet packet)
    {
        if (gameSetupType.equals(GameSetupType.GAME_SERVER))
        {
            CommunicationServer communicationServer = (CommunicationServer)communicationManager;
            communicationServer.sendString(clientID, packetType + packet.getDataStream());
            
            return true;
        }
        return false;
    }
    
    public boolean sendToServer(String packetType, Packet packet)
    {
        if (gameSetupType.equals(GameSetupType.GAME_CLIENT))
        {
            CommunicationClient communicationClient = (CommunicationClient)communicationManager;
            communicationClient.sendString(0, packetType + packet.getDataStream());
            
            return true;
        }
        return false;
    }
        
    public void processLineSegment(LineSegmentModel lineSegmentModel, boolean isBoxComplete)
    {
        // Send Infrmation to other Players
        LineSegmentPacket lineSegmentPacket = new LineSegmentPacket(lineSegmentModel);
        if (gameSetupType.equals(GameSetupType.GAME_SERVER))
        {
            sendToAllPlayers(Packet.LINE_SEGMENT_PACKET, lineSegmentPacket);
            
            TokenEngine tokenEngine = TokenEngine.getInstance();
            if (!isBoxComplete)
            {
                tokenEngine.nextPlayer();
                sendToPlayer(tokenEngine.getCurrentPlayerIndex(), Packet.PLAYER_TURN_PACKET, new PlayerTurnPacket()); 
            }
            
            int gameProgress = calculateScoreData().getGameProgress();
            CurrentPlayerPacket currentPlayerPacket = new CurrentPlayerPacket(
                    getPlayerModelList().get(tokenEngine.getCurrentPlayerIndex()), gameProgress);
            sendToAllPlayers(Packet.CURRENT_PLAYER_PACKET, currentPlayerPacket);

            for (GameEventListener gameEventListener : gameEventListeners)
            {
                gameEventListener.onPlayerTurnUpdate(currentPlayerPacket);

            }
                
            ScoreDataModel scoreDataModel = calculateScoreData();
            int playerScore = scoreDataModel.getPlayerScore(getPlayerModelList().get(0));
            int playerPosition = scoreDataModel.getPlayerPosition(getPlayerModelList().get(0));
            
            GameSummaryPacket gameSummaryPacket = new GameSummaryPacket(scoreDataModel);
            sendToAllPlayers(Packet.GAME_SUMMARY_PACKET, gameSummaryPacket);
            
            if (isGameComplete())
            {
                sendToAllPlayers(Packet.GAME_COMPLETE_PACKET, gameSummaryPacket);
                for (GameEventListener gameEventListener : getGameEventListeners())
                {
                    gameEventListener.onGameComplete(gameSummaryPacket);
                }
            }
            
            for (GameEventListener gameEventListener : getGameEventListeners())
            {
                gameEventListener.onScoreUpdate(new ScorePacket(playerScore, playerPosition));
                gameEventListener.onGameSummaryUpdate(gameSummaryPacket);
            }
        }
        else
        {
            GameSummaryPacket gameSummaryPacket = new GameSummaryPacket(calculateScoreData());
            sendToServer(Packet.LINE_SEGMENT_PACKET, lineSegmentPacket);
            sendToServer(Packet.GAME_SUMMARY_PACKET, gameSummaryPacket);
            
            if (!isBoxComplete)
            {
                sendToServer(Packet.PLAYER_TURN_COMPLETE_PACKET, new PlayerTurnCompletePacket());
            }
        } 
    }
      
    public boolean processBoxComplete(LineSegmentModel lineSegmentModel)
    {
        ArrayList<LineSegmentModel> adjLineSegmentModelsPrimary = new ArrayList<>();
        ArrayList<LineSegmentModel> adjLineSegmentModelsSecondary = new ArrayList<>();
        int startRowIndex = lineSegmentModel.getStartRowIndex();
        int startColumnIndex = lineSegmentModel.getStartColumnIndex();
        int endRowIndex = lineSegmentModel.getEndRowIndex();
        int endColumnIndex = lineSegmentModel.getEndColumnIndex();
        boolean isComplete = false;
        
        // Vertical
        if (startColumnIndex == endColumnIndex)
        {
            adjLineSegmentModelsPrimary.add(new LineSegmentModel(startRowIndex, startColumnIndex - 1, startRowIndex, startColumnIndex));
            adjLineSegmentModelsPrimary.add(new LineSegmentModel(startRowIndex, startColumnIndex - 1, endRowIndex, endColumnIndex - 1));
            adjLineSegmentModelsPrimary.add(new LineSegmentModel(endRowIndex, endColumnIndex - 1, endRowIndex, endColumnIndex));
            
            adjLineSegmentModelsSecondary.add(new LineSegmentModel(startRowIndex, startColumnIndex + 1, startRowIndex, startColumnIndex));
            adjLineSegmentModelsSecondary.add(new LineSegmentModel(startRowIndex, startColumnIndex + 1, endRowIndex, endColumnIndex + 1));
            adjLineSegmentModelsSecondary.add(new LineSegmentModel(endRowIndex, endColumnIndex + 1, endRowIndex, endColumnIndex));
        }
        // Horizontal
        else if (startRowIndex == lineSegmentModel.getEndRowIndex())
        {
            adjLineSegmentModelsPrimary.add(new LineSegmentModel(startRowIndex - 1, startColumnIndex, startRowIndex, startColumnIndex));
            adjLineSegmentModelsPrimary.add(new LineSegmentModel(startRowIndex - 1, startColumnIndex, endRowIndex - 1, endColumnIndex));
            adjLineSegmentModelsPrimary.add(new LineSegmentModel(endRowIndex - 1, endColumnIndex, endRowIndex, endColumnIndex));
            
            adjLineSegmentModelsSecondary.add(new LineSegmentModel(startRowIndex + 1, startColumnIndex, startRowIndex, startColumnIndex));
            adjLineSegmentModelsSecondary.add(new LineSegmentModel(startRowIndex + 1, startColumnIndex, endRowIndex + 1, endColumnIndex));
            adjLineSegmentModelsSecondary.add(new LineSegmentModel(endRowIndex + 1, endColumnIndex, endRowIndex, endColumnIndex));
        }

        // Fourth line segment
        if (gameBoardModel.getLineSegmentSet().containsAll(adjLineSegmentModelsPrimary))
        {
            BoxModel boxModel = new BoxModel(gameBoardModel.getBoardSize(),
                    adjLineSegmentModelsPrimary.get(0), adjLineSegmentModelsPrimary.get(1), adjLineSegmentModelsPrimary.get(2));
            boxModel.setPlayerModel(lineSegmentModel.getPlayerModel());
            boxModel.setMagicBox(boxModel.getBoxIndex() == gameBoardModel.getMagicBoxPosition());
            gameBoardModel.getBoxModelSet().add(boxModel);
            isComplete = true;
        }       
        
        if(gameBoardModel.getLineSegmentSet().containsAll(adjLineSegmentModelsSecondary))
        {
            BoxModel boxModel = new BoxModel(gameBoardModel.getBoardSize(),
                    adjLineSegmentModelsSecondary.get(0), adjLineSegmentModelsSecondary.get(1), adjLineSegmentModelsSecondary.get(2));
            boxModel.setPlayerModel(lineSegmentModel.getPlayerModel());
            boxModel.setMagicBox(boxModel.getBoxIndex() == gameBoardModel.getMagicBoxPosition());
            gameBoardModel.getBoxModelSet().add(boxModel);
            isComplete = true;
        }
        
        return isComplete;
    }
       
    public boolean isGameComplete()
    {
        int totalBoxCount = gameBoardModel.getBoardSize() * gameBoardModel.getBoardSize();
        int currentBoxCount = gameBoardModel.getBoxModelSet().size();
        System.out.println("T : " + totalBoxCount + " : Current : " + currentBoxCount);
        return (currentBoxCount == totalBoxCount);
    }
    
    public ScoreDataModel calculateScoreData()
    {
        ScoreDataModel scoreDataModel = new ScoreDataModel();
        
        // calculate scores
        final HashMap<String, Integer> scoreMap = new HashMap<>();
        for (BoxModel boxModel : gameBoardModel.getBoxModelSet())
        {
            String playerName = boxModel.getPlayerModel().getPlayerName();
            
            if (!scoreMap.containsKey(boxModel.getPlayerModel().getPlayerName()))
            {
                scoreMap.put(playerName, 0);
            }
            
            int currentScore = scoreMap.get(playerName);
            int scoreFactor = boxModel.isMagicBox() ? 20 : 10;
            scoreMap.put(playerName, currentScore + scoreFactor);
        }
        scoreDataModel.setScoreMap(scoreMap);
        
        // calculate game progress
        // if grid has n number of rows (or colums) is has 2n(n + 1)  line segments
        int n = gameBoardModel.getBoardSize();
        int totalLineCount = 2 * n * (n + 1);
        int currentLineCount = gameBoardModel.getLineSegmentSet().size();
        int progress = (int)Math.round((currentLineCount * 100.0 ) / totalLineCount);
        scoreDataModel.setGameProgress(progress);
        
        // calculate positions
        ArrayList<String> players = new ArrayList<>();
        for (String player : scoreMap.keySet())
        {
            players.add(player);
        }
        
        Collections.sort(players, new Comparator<String>()
        {
            @Override
            public int compare(String player1, String player2)
            {
                return scoreMap.get(player2) - scoreMap.get(player1);
            }
        });
        scoreDataModel.setPositionList(players);
        
        return scoreDataModel;
    }
  
    private void processPacket(String message, int clientID)
    {
        String packetType = message.substring(0, 1);
        Packet packet = null;

        switch (packetType)
        {
            case GAME_START_PACKET : 
            {
                packet = new GameStartPacket(message.substring(1, message.length()));
                break;
            }
            case LINE_SEGMENT_PACKET:
            {
                packet = new LineSegmentPacket(message.substring(1, message.length()));
                break;
            }
            case PLAYER_TURN_PACKET:
            {
                packet = new PlayerTurnPacket(message.substring(1, message.length()));
                break;
            }
            case PLAYER_TURN_COMPLETE_PACKET:
            {
                packet = new PlayerTurnCompletePacket(message.substring(1, message.length()));
                break;
            }
            case GAME_COMPLETE_PACKET:
            {
                packet = new GameSummaryPacket(message.substring(1, message.length()));
                break;
            }
            case CURRENT_PLAYER_PACKET:
            {
                packet = new CurrentPlayerPacket(message.substring(1, message.length()));
                break;
            }
            case SCORE_PACKET:
            {
                packet = new ScorePacket(message.substring(1, message.length()));
                break;
            }
            case GAME_SUMMARY_PACKET:
            {
                packet = new GameSummaryPacket(message.substring(1, message.length()));
                break;
            }
        }
        processPacket(packetType, packet);   
    }
    
    private void processPacket(String packetType, Packet packet)
    {
        if (gameSetupType.equals(GameSetupType.GAME_SERVER))
        {
            switch (packetType)
            {
                case LINE_SEGMENT_PACKET : 
                {
                    for (GameEventListener gameEventListener : gameEventListeners)
                    {
                        gameEventListener.onLineSegmentAdded((LineSegmentPacket)packet);
                    }
                    
                    sendToAllPlayers(LINE_SEGMENT_PACKET, packet);
                   
                    GameSummaryPacket gameSummaryPacket = new GameSummaryPacket(calculateScoreData());
                    sendToAllPlayers(GAME_SUMMARY_PACKET, gameSummaryPacket);
                    
                    break;
                }
                
                case PLAYER_TURN_PACKET :
                {
                    for (GameEventListener gameEventListener : gameEventListeners)
                    {
                        gameEventListener.onPlayerTurnStart((PlayerTurnPacket)packet);
                    }
                    break;
                }
                
                case PLAYER_TURN_COMPLETE_PACKET :
                {
                    for (GameEventListener gameEventListener : gameEventListeners)
                    {
                        gameEventListener.onPlayerTurnComplete((PlayerTurnCompletePacket)packet);
                    }
                    break;
                }
                
                case CURRENT_PLAYER_PACKET :
                {
                    for (GameEventListener gameEventListener : gameEventListeners)
                    {
                        gameEventListener.onPlayerTurnUpdate((CurrentPlayerPacket)packet);
                    }
                    break;
                }
                
                case GAME_SUMMARY_PACKET:
                {
                    for (GameEventListener gameEventListener : gameEventListeners)
                    {
                        gameEventListener.onGameSummaryUpdate((GameSummaryPacket)packet);
                    }
                    break;
                }
            }
        }
        else if (gameSetupType.equals(GameSetupType.GAME_CLIENT))
        {
            switch (packetType)
            {
                case GAME_START_PACKET : 
                {
                    for (GameEventListener gameEventListener : gameEventListeners)
                    {
                        gameEventListener.onGameStart((GameStartPacket)packet);
                    }
                    break;
                }
                
                case LINE_SEGMENT_PACKET : 
                {
                    for (GameEventListener gameEventListener : gameEventListeners)
                    {
                        gameEventListener.onLineSegmentAdded((LineSegmentPacket)packet);
                    }
                    break;
                }
                
                case GAME_COMPLETE_PACKET : 
                {
                    for (GameEventListener gameEventListener : gameEventListeners)
                    {
                        gameEventListener.onGameComplete((GameSummaryPacket)packet);
                    }
                    break;
                }
                
                case PLAYER_TURN_PACKET :
                {
                    for (GameEventListener gameEventListener : gameEventListeners)
                    {
                        gameEventListener.onPlayerTurnStart((PlayerTurnPacket)packet);
                    }
                    break;
                }
                
                case CURRENT_PLAYER_PACKET :
                {
                    for (GameEventListener gameEventListener : gameEventListeners)
                    {
                        gameEventListener.onPlayerTurnUpdate((CurrentPlayerPacket)packet);
                    }
                    break;
                }
                
                case SCORE_PACKET:
                {
                    for (GameEventListener gameEventListener : gameEventListeners)
                    {
                        gameEventListener.onScoreUpdate((ScorePacket)packet);
                    }
                    break;
                }
                
                case GAME_SUMMARY_PACKET:
                {
                    for (GameEventListener gameEventListener : gameEventListeners)
                    {
                        gameEventListener.onGameSummaryUpdate((GameSummaryPacket)packet);
                    }
                    break;
                }
            }
        }
    }
}
