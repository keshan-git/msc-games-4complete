package four.complete.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Keshan De Silva
 */
public class BoxModel
{
    private ArrayList<LineSegmentModel> lineSet = new ArrayList<>();
    
    private int topRowIndex;
    private int topColumnIndex;
    private int boxIndex;
    private int boardSize;
    private boolean magicBox = false;
    
    private PlayerModel playerModel;
        
    public BoxModel(int boardSize, LineSegmentModel... lineSegments)
    {
        this.boardSize = boardSize;
        lineSet.addAll(Arrays.asList(lineSegments));
        generateData();
    }

    public void addLineSegments(LineSegmentModel lineSegmentModel)
    {
        lineSet.add(lineSegmentModel);
        generateData();
    }

    public int getTopRowIndex()
    {
        return topRowIndex;
    }

    public int getTopColumnIndex()
    {
        return topColumnIndex;
    }

    public int getBoxIndex()
    {
        return boxIndex;
    }

    public PlayerModel getPlayerModel()
    {
        return playerModel;
    }

    public void setPlayerModel(PlayerModel playerModel)
    {
        this.playerModel = playerModel;
    }

    public void setMagicBox(boolean magicBox)
    {
        this.magicBox = magicBox;
    }

    public boolean isMagicBox()
    {
        return magicBox;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }

        BoxModel boxModel = (BoxModel) obj;
        if (this.topRowIndex != boxModel.topRowIndex)
        {
            return false;
        }
        if (this.topColumnIndex != boxModel.topColumnIndex)
        {
            return false;
        }
        if (this.boxIndex != boxModel.boxIndex)
        {
            return false;
        }
        return true;
    }
    
    
    private void generateData()
    {
        ArrayList<Integer> rowIndexList = new ArrayList<>();
        ArrayList<Integer> columnIndexList = new ArrayList<>();
        
        for (LineSegmentModel lineSegmentModel : lineSet)
        {
            rowIndexList.add(lineSegmentModel.getStartRowIndex());
            rowIndexList.add(lineSegmentModel.getEndRowIndex());
            columnIndexList.add(lineSegmentModel.getStartColumnIndex());
            columnIndexList.add(lineSegmentModel.getEndColumnIndex());
        }

        Collections.sort(rowIndexList);
        Collections.sort(columnIndexList);
        
        topRowIndex = rowIndexList.get(0);
        topColumnIndex = columnIndexList.get(0);
        boxIndex = (boardSize * topRowIndex ) + topColumnIndex;
    }
}
