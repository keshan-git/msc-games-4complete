/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package four.complete.ui.model;

import java.net.Socket;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Keshan De Silva
 */
public class PlayerListModel extends AbstractListModel<String>
{
    private final ArrayList<Socket> playersSocketList = new ArrayList<>();

    public void addPlayer(Socket socket)
    {
        playersSocketList.add(socket);
        fireContentsChanged(this, 0, playersSocketList.size());
    }
    
    public void removePlayer(Socket socket)
    {
        playersSocketList.remove(socket);
    }
    
    @Override
    public int getSize()
    {
        return playersSocketList.size();
    }

    @Override
    public String getElementAt(int i)
    {
        Socket communicationSocket = playersSocketList.get(i);
        return communicationSocket.getInetAddress().getHostName();
    } 
}
