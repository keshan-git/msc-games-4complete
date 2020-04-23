package communication.manager.core;

/**
 * @author Keshan De Silva
 * Created on Oct 7, 2014
 */
public interface MessageReceiveListener
{
    public void onMessageReceived(String message, int clientID);
}
