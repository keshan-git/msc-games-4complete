package four.complete.core.coms.packets;

import four.complete.model.GameBoardModel;
import four.complete.model.PlayerModel;
import java.util.ArrayList;

/**
 *
 * @author Keshan De Silva
 */
public class GameStartPacket implements Packet
{
    private ArrayList<PlayerModel> playerList;
    private GameBoardModel gameBoardModel;
    private PlayerModel currentPlayer;
    
    public GameStartPacket(ArrayList<PlayerModel> playerList, GameBoardModel gameBoardModel)
    {
        this.playerList = playerList;
        this.gameBoardModel = gameBoardModel;
    }

    public GameStartPacket(String dataStream)
    {
        String[] components =  dataStream.split(Packet.PARAMETER_SEPARATOR_3);
        String[] playerComponents = components[0].split(Packet.LIST_SEPARATOR);
        
        this.playerList = new ArrayList<>();
        for (String playerString : playerComponents)
        {
            playerList.add(new PlayerModel(playerString));
        }
        
        this.gameBoardModel = new GameBoardModel(components[1]);
        this.currentPlayer = new PlayerModel(components[2]);
    }

    public ArrayList<PlayerModel> getPlayerList()
    {
        return playerList;
    }

    public GameBoardModel getGameBoardModel()
    {
        return gameBoardModel;
    }

    public PlayerModel getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerModel currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }
    
    @Override
    public String getDataStream()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (PlayerModel playerModel : playerList)
        {
            stringBuilder.append(playerModel.toString()).append(Packet.LIST_SEPARATOR);
        }
        stringBuilder.append(Packet.PARAMETER_SEPARATOR_3);
        stringBuilder.append(gameBoardModel.toString());
        stringBuilder.append(Packet.PARAMETER_SEPARATOR_3);
        stringBuilder.append(currentPlayer.toString());
        
        return stringBuilder.toString();
    }
    
}
