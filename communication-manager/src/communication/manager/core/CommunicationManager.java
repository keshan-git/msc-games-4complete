package communication.manager.core;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Keshan De Silva
 * Created on Oct 7, 2014
 */
public abstract class CommunicationManager
{
    protected ArrayList<MessageReceiveListener> messageListernerList = new ArrayList<>();
    protected ArrayList<CommunicationManagerListener> connectionListenerList = new ArrayList<>();
    protected boolean running;
        
    protected final ExecutorService executorService = Executors.newCachedThreadPool();
    protected final String ARE_YOU_THERE = "*";
    protected final int SERVER_ID = 0;

    public void addMessageReceiveListener(MessageReceiveListener listener)
    {
        messageListernerList.add(listener);
    }
    
    public void removeMessageReceiveListener(MessageReceiveListener listener)
    {
        messageListernerList.remove(listener);
    }
    
    public void clearMessageReceiveListeners()
    {
        messageListernerList.clear();
    }

    public void addCommunicationManagerListener(CommunicationManagerListener listener)
    {
        connectionListenerList.add(listener);
    }
    
    public void removeCommunicationManagerListener(CommunicationManagerListener listener)
    {
        connectionListenerList.remove(listener);
    }
    
    public void clearCommunicationManagerListeners()
    {
        connectionListenerList.clear();
    }
    
    protected void onMessageReceived(String message, int clientID)
    {
        for (MessageReceiveListener listener : messageListernerList)
        {
            listener.onMessageReceived(message, clientID);
        }
    }

    protected void fireEventHostConnect(int clientID, Socket communicationSocket)
    {
        for (CommunicationManagerListener listener : connectionListenerList)
        {
            listener.onConnected(clientID, communicationSocket);
        }
    }
    
    protected void fireEventHostDisconnect(int clientID, Socket communicationSocket)
    {
        for (CommunicationManagerListener listener : connectionListenerList)
        {
            listener.onDisconnected(clientID, communicationSocket);
        }
    }
   
    public abstract void stopCommunication();
    public abstract boolean sendString(int clientID, String message);
    public abstract boolean isConnectionAvailable(int clientID);
    
    protected class InputStreamThread implements Runnable
    {
        private DataInputStream dataInputStream;
        private int clientID;

        public InputStreamThread(DataInputStream dataInputStream, int clientID)
        {
            this.dataInputStream = dataInputStream;
            this.clientID = clientID;
        }
        
        @Override
        public void run()
        {
            try
            {
                while (true)
                {
                    String receivedMessage = dataInputStream.readUTF();
                    if (!receivedMessage.equals(ARE_YOU_THERE))
                    {
                        onMessageReceived(receivedMessage, clientID);
                    }    
                }
            }
            catch (IOException ex)
            {

            }
        }    
    }
    
    protected class DisconnectListenerThread implements Runnable
    {
        private Socket communicationSocket;
        private int clientID;
        
        public DisconnectListenerThread(Socket communicationSocket, int clientID)
        {
            this.communicationSocket = communicationSocket;
            this.clientID = clientID;
        }

        @Override
        public void run()
        {
            while (running)
            {
                if ((!communicationSocket.isConnected()) || (!isConnectionAvailable(clientID)))
                {
                    fireEventHostDisconnect(clientID, communicationSocket);
                    running = false;
                }
                        
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ex)
                {

                }
            }
        }    
    }
}
