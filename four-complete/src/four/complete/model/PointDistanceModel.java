package four.complete.model;

import java.awt.Point;

/**
 *
 * @author Keshan De Silva
 */
public class PointDistanceModel implements Comparable<PointDistanceModel>
{
    private double distance;
    private Point point;
    private int rowIndex;
    private int columnIndex;

    public PointDistanceModel(double distance, Point point, int rowIndex, int columnIndex)
    {
        this.distance = distance;
        this.point = point;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex()
    {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex)
    {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex()
    {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex)
    {
        this.columnIndex = columnIndex;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    public Point getPoint()
    {
        return point;
    }

    public void setPoint(Point point)
    {
        this.point = point;
    }

    @Override
    public int compareTo(PointDistanceModel distanceModel)
    {
        return (int)(distance - distanceModel.getDistance());
    }

    @Override
    public String toString()
    {
        return "[" + rowIndex + "," + columnIndex + "]";
    }
    
    
}
