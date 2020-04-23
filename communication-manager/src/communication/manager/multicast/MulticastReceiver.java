package communication.manager.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Keshan De Silva
 */
public class MulticastReceiver
{
    private MulticastSocket socket = null;
    private DatagramPacket inputPacket = null;
    private boolean running;
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private ArrayList<MulticastMessageReceiveListener> listenerList = new ArrayList<>();
    
    public boolean startMulticastCommunication(int portNumber)
    {
        try
        {
            socket = new MulticastSocket(portNumber);
            
            InetAddress address = InetAddress.getByName("224.2.2.3");
            socket.joinGroup(address);
            running = true;
            executorService.submit(new MulticastReceiverThread());
        }
        catch (IOException ex)
        {
            return false;
        }

        return true;
    }

    public void stopCommunication()
    {
        this.running = false;
        this.socket.close();
    }
        
    public void addMulticastMessageReceiveListener(MulticastMessageReceiveListener listener)
    {
        listenerList.add(listener);
    }
    
    public void removeMulticastMessageReceiveListener(MulticastMessageReceiveListener listener)
    {
        listenerList.remove(listener);
    }
    
    public void clearMessageReceiveListeners()
    {
        listenerList.clear();
    }
    
    private class MulticastReceiverThread implements Runnable
    {
        @Override
        public void run()
        {
            byte[] inputBufffer = new byte[256];
            
            try
            {
                while (running)
                {
                    inputPacket = new DatagramPacket(inputBufffer, inputBufffer.length);
                    socket.receive(inputPacket);
                
                    String message = new String(inputBufffer, 0, inputPacket.getLength());
                    for (MulticastMessageReceiveListener messageReceiveListener : listenerList)
                    {
                        messageReceiveListener.onMessageReceived(message, inputPacket.getAddress());
                    }
                }
            }
            catch (IOException exception) {}
        }
    }
}
