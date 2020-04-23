package communication.manager.multicast;

import java.net.InetAddress;

/**
 * @author Keshan De Silva
 * Created on Oct 7, 2014
 */
public interface MulticastMessageReceiveListener
{
    public void onMessageReceived(String message, InetAddress address);
}
