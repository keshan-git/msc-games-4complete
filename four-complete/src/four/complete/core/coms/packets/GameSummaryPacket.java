package four.complete.core.coms.packets;

import four.complete.model.ScoreDataModel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Keshan De Silva
 */
public class GameSummaryPacket implements Packet
{
    private HashMap<String, Integer> scoreMap = new HashMap<>();
    private ArrayList<String> positionList = new ArrayList<>();
    
    public GameSummaryPacket(ScoreDataModel scoreDataModel)
    {
        this.scoreMap = scoreDataModel.getScoreMap();
        this.positionList = scoreDataModel.getPositionList();
    }
    
    public GameSummaryPacket(String dataStream)
    {
        String[] components =  dataStream.split(Packet.PARAMETER_SEPARATOR_3);
        if (components.length == 0) return;
        
        String[] scoreComponents =  components[0].split(Packet.PARAMETER_SEPARATOR_2);
        for (String comp : scoreComponents)
        {
            String[] scoreComponent = comp.split(Packet.PARAMETER_SEPARATOR_1);
            scoreMap.put(scoreComponent[0], Integer.parseInt(scoreComponent[1]));
        }
        
        String[] positionComponents = components[1].split(PARAMETER_SEPARATOR_1);
        for (String comp : positionComponents)
        {
            positionList.add(comp);
        }
    }

    public HashMap<String, Integer> getScoreMap()
    {
        return scoreMap;
    }

    public ArrayList<String> getPositionList()
    {
        return positionList;
    }

    @Override
    public String getDataStream()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : scoreMap.keySet())
        {
            stringBuilder.append(key).append(Packet.PARAMETER_SEPARATOR_1);
            stringBuilder.append(scoreMap.get(key)).append(Packet.PARAMETER_SEPARATOR_2);
        }
        
        stringBuilder.append(PARAMETER_SEPARATOR_3);
        for (String position : positionList)
        {
            stringBuilder.append(position).append(Packet.PARAMETER_SEPARATOR_1);
        }
        
        return stringBuilder.toString();
    }
    
    
}
