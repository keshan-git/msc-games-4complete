package four.complete.ui.model;

import four.complete.model.GameBoardModel;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Keshan De Silva
 */
public class GameServersListModel extends AbstractListModel<String>
{
    private ArrayList<String> dataList = new ArrayList<>();
    
    @Override
    public int getSize()
    {
        return dataList.size();
    }

    @Override
    public String getElementAt(int i)
    {
        return dataList.get(i);
    }

    public void addServerIP(String serevrIP)
    {
        if (!dataList.contains(serevrIP))
        {
            dataList.add(serevrIP);
            fireContentsChanged(this, 0, dataList.size());
        }
    }
        
    public void clear()
    {
        dataList.clear();
        fireContentsChanged(this, 0, dataList.size());
    }
    
}
