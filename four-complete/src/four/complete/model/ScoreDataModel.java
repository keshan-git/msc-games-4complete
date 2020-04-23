package four.complete.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Keshan De Silva
 */
public class ScoreDataModel
{
    private HashMap<String, Integer> scoreMap = new HashMap<>();
    private int gameProgress;
    private ArrayList<String> positionList = new ArrayList<>();
    
    public HashMap<String, Integer> getScoreMap()
    {
        return scoreMap;
    }

    public void setScoreMap(HashMap<String, Integer> scoreMap)
    {
        this.scoreMap = scoreMap;
    }

    public int getPlayerScore(PlayerModel player)
    {
        if (scoreMap.containsKey(player.getPlayerName()))
        {
            return scoreMap.get(player.getPlayerName());
        }
        
        return 0;
    } 

    public int getGameProgress()
    {
        return gameProgress;
    }

    public void setGameProgress(int gameProgress)
    {
        this.gameProgress = gameProgress;
    }

    public void setPositionList(ArrayList<String> positionList)
    {
        this.positionList = positionList;
    }

    public int getPlayerPosition(PlayerModel player)
    {
        if (positionList.contains(player.getPlayerName()))
        {
            return positionList.indexOf(player.getPlayerName()) + 1;
        }
        
        return -1;
    }

    public ArrayList<String> getPositionList()
    {
        return positionList;
    }
}
