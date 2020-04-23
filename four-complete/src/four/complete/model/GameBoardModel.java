package four.complete.model;

import four.complete.core.coms.packets.Packet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Keshan De Silva
 */
public class GameBoardModel
{
    private int boardSize = 5;
    private int magicBoxPosition = -1;
    
    private ArrayList<LineSegmentModel> lineSegmentSet = new ArrayList<>();
    private Set<BoxModel> boxSet = new HashSet<>();

    public GameBoardModel()
    {
    }

    public GameBoardModel(String dataStream)
    {
        String[] components = dataStream.split(Packet.PARAMETER_SEPARATOR_4);
        this.boardSize = Integer.parseInt(components[0]);
        this.magicBoxPosition = Integer.parseInt(components[1]);
    }
    
    public int getBoardSize()
    {
        return boardSize;
    }

    public void setBoardSize(int boardSize)
    {
        this.boardSize = boardSize;
    }

    public void setMagicBoxEnabled(boolean magicBox)
    {   
        if (magicBox)
        {
            magicBoxPosition = (int)(Math.random() * boardSize * boardSize);
        }
        else
        {
            magicBoxPosition = -1;
        }
    }

    public int getMagicBoxPosition()
    {
        return magicBoxPosition;
    }

    public boolean isMagicBoxAvailable()
    {
        return magicBoxPosition != -1;
    }
    
    public void addLineSegment(LineSegmentModel lineSegment)
    {
        if (!lineSegmentSet.contains(lineSegment))
        {
            lineSegmentSet.add(lineSegment);
        }
    }
    
    public boolean isLineSegmentAvailable(LineSegmentModel lineSegmentModel)
    {
        return lineSegmentSet.contains(lineSegmentModel);
    }

    public ArrayList<LineSegmentModel> getLineSegmentSet()
    {
        return lineSegmentSet;
    }
    
    public void addBoxModel(BoxModel boxModel)
    {
        boxSet.add(boxModel);
    }

    public Set<BoxModel> getBoxModelSet()
    {
        return boxSet;
    }

    @Override
    public String toString()
    {
        return boardSize + Packet.PARAMETER_SEPARATOR_4 + magicBoxPosition;
    }   
}
