/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package four.complete.ui.model;

import four.complete.model.ScoreDataModel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Keshan De Silva
 */
public class GameResultTableModel extends AbstractTableModel
{
    private ScoreDataModel scoreDataModel;
    private final String[] columNames = new String[]{"Place", "Player Name", "Score"};
    private final Class[] columClassNames = new Class[]{String.class, String.class, Integer.class};
    
    public void updateData(ScoreDataModel scoreDataModel)
    {
        this.scoreDataModel = scoreDataModel;
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int i, int i1)
    {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int i)
    {
        return columClassNames[i];
    }

    @Override
    public String getColumnName(int i)
    {
        return columNames[i];
    }
    
    
    @Override
    public int getRowCount()
    {
        if (scoreDataModel != null)
        {
            return scoreDataModel.getPositionList().size();
        }
        
        return 0;
    }

    @Override
    public int getColumnCount()
    {
        return columNames.length;
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        if (scoreDataModel != null)
        {
            String player = scoreDataModel.getPositionList().get(row);
            switch (column)
            {
                case 0 : return scoreDataModel.getPositionList().indexOf(player) + 1;
                case 1 : return player;
                case 2 : return scoreDataModel.getScoreMap().get(player);
            }
        }
        
        return "";
    }
    
}
