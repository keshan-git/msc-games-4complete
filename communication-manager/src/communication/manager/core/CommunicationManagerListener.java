package communication.manager.core;

import java.net.Socket;

/**
 * @author Keshan De Silva
 * Created on Oct 7, 2014
 */
public interface CommunicationManagerListener
{
    public void onConnected(int clientID, Socket communicationSocket);
    public void onDisconnected(int clientID, Socket communicationSocket);
}
