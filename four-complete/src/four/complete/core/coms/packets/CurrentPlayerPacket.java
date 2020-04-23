package four.complete.core.coms.packets;

import four.complete.model.PlayerModel;

/**
 *
 * @author Keshan De Silva
 */
public class CurrentPlayerPacket implements Packet
{
    private PlayerModel currentPlayer;
    private int gameProgress;
        
    public CurrentPlayerPacket(PlayerModel currentPlayer, int gameProgress)
    {
        this.currentPlayer = currentPlayer;   
        this.gameProgress = gameProgress;   
    }

    public CurrentPlayerPacket(String dataStream)
    {
        String[] components = dataStream.split(Packet.PARAMETER_SEPARATOR_3);
        this.currentPlayer = new PlayerModel(components[0]);
        this.gameProgress = Integer.parseInt(components[1]);
    }

    public PlayerModel getCurrentPlayer()
    {
        return currentPlayer;
    }

    public int getGameProgress()
    {
        return gameProgress;
    }
    
    @Override
    public String getDataStream()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentPlayer.toString()).append(Packet.PARAMETER_SEPARATOR_3);
        stringBuilder.append(gameProgress);
        
        return stringBuilder.toString();
    }
    
}
