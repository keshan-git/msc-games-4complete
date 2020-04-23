package four.complete.core.coms.packets;

import four.complete.model.LineSegmentModel;

/**
 *
 * @author Keshan De Silva
 */
public class LineSegmentPacket implements Packet
{
    private LineSegmentModel lineSegmentModel;

    public LineSegmentPacket(LineSegmentModel lineSegmentModel)
    {
        this.lineSegmentModel = lineSegmentModel;
    }

    public LineSegmentPacket(String dataStream)
    {
        this.lineSegmentModel = new LineSegmentModel(dataStream);
    }
        
    public LineSegmentModel getLineSegmentModel()
    {
        return lineSegmentModel;
    }

    @Override
    public String getDataStream()
    {
        return lineSegmentModel.toString();
    }
 
}
