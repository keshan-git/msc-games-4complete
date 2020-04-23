
package four.complete.model;

import four.complete.core.coms.packets.Packet;
import java.awt.Color;

/**
 *
 * @author Keshan De Silva
 */
public class PlayerModel
{
    private String playerName;
    private Color playerColor;

    public PlayerModel(String playerName, Color playerColor)
    {
        this.playerName = playerName;
        this.playerColor = playerColor;
    }

    public PlayerModel(String dataStream)
    {
        String[] components =  dataStream.split(Packet.PARAMETER_SEPARATOR_2);
        String[] colorComponents = components[1].split(Packet.PARAMETER_SEPARATOR_1);
        
        this.playerName = components[0];
        this.playerColor = new Color(
                Integer.parseInt(colorComponents[0]),
                Integer.parseInt(colorComponents[1]),
                Integer.parseInt(colorComponents[2]),
                Integer.parseInt(colorComponents[3]));
    }
    
    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public Color getPlayerColor()
    {
        return playerColor;
    }

    public void setPlayerColor(Color playerColor)
    {
        this.playerColor = playerColor;
    }

    @Override
    public String toString()
    {
        return playerName + Packet.PARAMETER_SEPARATOR_2 + playerColor.getRed() 
                + Packet.PARAMETER_SEPARATOR_1 + playerColor.getGreen() 
                + Packet.PARAMETER_SEPARATOR_1 + playerColor.getBlue()
                + Packet.PARAMETER_SEPARATOR_1 + playerColor.getAlpha();
    }   
}
