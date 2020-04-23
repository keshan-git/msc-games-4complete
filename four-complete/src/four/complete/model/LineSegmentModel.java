package four.complete.model;

import four.complete.core.coms.packets.Packet;


/**
 *
 * @author Keshan De Silva
 */
public class LineSegmentModel
{  
    private int startRowIndex;
    private int startColumnIndex;
    private int endRowIndex;
    private int endColumnIndex;
    
    private PlayerModel playerModel;
    
    public LineSegmentModel(int startRowIndex, int startColumnIndex, int endRowIndex, int endColumnIndex)
    {
        this.startRowIndex = startRowIndex;
        this.startColumnIndex = startColumnIndex;
        this.endRowIndex = endRowIndex;
        this.endColumnIndex = endColumnIndex;
    }

    public LineSegmentModel(PointDistanceModel pointDistanceModelStart, PointDistanceModel pointDistanceModelEnd)
    {
        this.startRowIndex = pointDistanceModelStart.getRowIndex();
        this.startColumnIndex = pointDistanceModelStart.getColumnIndex();
        this.endRowIndex = pointDistanceModelEnd.getRowIndex();
        this.endColumnIndex = pointDistanceModelEnd.getColumnIndex();
    }

    public LineSegmentModel(String dataStream)
    {
        String[] components =  dataStream.split(Packet.PARAMETER_SEPARATOR_3);
        startRowIndex = Integer.parseInt(components[0]);
        startColumnIndex = Integer.parseInt(components[1]);
        endRowIndex = Integer.parseInt(components[2]);
        endColumnIndex = Integer.parseInt(components[3]);
        playerModel = new PlayerModel(components[4]);    
    }

    public PlayerModel getPlayerModel()
    {
        return playerModel;
    }

    public void setPlayerModel(PlayerModel playerModel)
    {
        this.playerModel = playerModel;
    }

    public int getStartRowIndex()
    {
        return startRowIndex;
    }

    public void setStartRowIndex(int startRowIndex)
    {
        this.startRowIndex = startRowIndex;
    }

    public int getStartColumnIndex()
    {
        return startColumnIndex;
    }

    public void setStartColumnIndex(int startColumnIndex)
    {
        this.startColumnIndex = startColumnIndex;
    }

    public int getEndRowIndex()
    {
        return endRowIndex;
    }

    public void setEndRowIndex(int endRowIndex)
    {
        this.endRowIndex = endRowIndex;
    }

    public int getEndColumnIndex()
    {
        return endColumnIndex;
    }

    public void setEndColumnIndex(int endColumnIndex)
    {
        this.endColumnIndex = endColumnIndex;
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof LineSegmentModel)
        {
            LineSegmentModel line = (LineSegmentModel)object;
            if (((startRowIndex == line.getStartRowIndex()) && (startColumnIndex == line.getStartColumnIndex()) &&
                 (endRowIndex == line.getEndRowIndex()) && (endColumnIndex == line.getEndColumnIndex())) ||
                ((startRowIndex == line.getEndRowIndex()) && (startColumnIndex == line.getEndColumnIndex()) &&
                 (endRowIndex == line.getStartRowIndex()) && (endColumnIndex == line.getStartColumnIndex())))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString()
    {
        return startRowIndex + Packet.PARAMETER_SEPARATOR_3 + startColumnIndex +
                Packet.PARAMETER_SEPARATOR_3  + endRowIndex + Packet.PARAMETER_SEPARATOR_3  + endColumnIndex +
                Packet.PARAMETER_SEPARATOR_3 + playerModel.toString();
    }
    
    
}
