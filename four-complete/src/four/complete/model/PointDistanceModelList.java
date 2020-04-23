package four.complete.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Keshan De Silva
 */
public class PointDistanceModelList extends ArrayList<PointDistanceModel>
{
    public void addDistanceModel(PointDistanceModel distanceMode)
    {
        add(distanceMode);
    }
    
    public void sort()
    {
        Collections.sort(this);
    }
}
