package communication.manager.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Keshan De Silva
 */
public class MulticastSender
{   
    private int portNumber;
    private DatagramSocket socket = null;
    private DatagramPacket outputPacket = null;
    private boolean running;
    
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    
    public boolean startMulticastCommunication(int portNumber)
    {
        try
        {
            this.portNumber = portNumber;
            socket = new DatagramSocket();
            running = true;
            executorService.submit(new MulticastServerThread());
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

    private class MulticastServerThread implements Runnable
    {
        @Override
        public void run()
        {
            String msg;
            byte[] outputBuffer;
            
            while (running)
            {
                try
                {
                    msg = "FC";
                    outputBuffer = msg.getBytes();

                    InetAddress address = InetAddress.getByName("224.2.2.3");
                    outputPacket = new DatagramPacket(outputBuffer, outputBuffer.length, address, portNumber);

                    socket.send(outputPacket);

                    try
                    {
                      Thread.sleep(500);
                    }
                    catch (InterruptedException exception) {}
                }
                catch (Exception ex){}
            }
        }
    }
}
