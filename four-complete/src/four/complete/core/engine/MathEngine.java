
package four.complete.core.engine;

import four.complete.model.PointDistanceModel;
import four.complete.model.PointDistanceModelList;
import java.awt.Point;

/**
 *
 * @author Keshan De Silva
 */
public final class MathEngine
{
    private MathEngine(){}
    
    public static double getDistance(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2));
    }
    
    public static Point getMidPoint(Point p1, Point p2)
    {
        return new Point((int)Math.round(Math.abs(p1.getX() - p2.getX()) / 2), (int)Math.round(Math.abs(p1.getY() - p2.getY()) / 2));
    }
    
    public static PointDistanceModel[] getClosedPoints(Point p1, Point[][] pointSet)
    {
        Point point;
        Double distance;
        
        PointDistanceModelList distanceModelList = new PointDistanceModelList();
        for (int i = 0; i < pointSet.length; i++)
        {
            for (int j = 0; j < pointSet.length; j++)
            {
                point = pointSet[j][i];
                distance = MathEngine.getDistance(point, p1); 
                distanceModelList.addDistanceModel(new PointDistanceModel(distance, point, j, i));
            }
        }
        
        distanceModelList.sort();
        if ((distanceModelList.get(0).getRowIndex() + distanceModelList.get(0).getColumnIndex())
             < (distanceModelList.get(1).getRowIndex() + distanceModelList.get(1).getColumnIndex()))
        {
            return new PointDistanceModel[] {distanceModelList.get(0), distanceModelList.get(1)};
        }
        else
        {
            return new PointDistanceModel[] {distanceModelList.get(1), distanceModelList.get(0)}; 
        }
    }

}
