package communication.manager.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author Keshan De Silva
 * Created on Oct 7, 2014
 */
public class CommunicationServer extends CommunicationManager
{
    private ServerSocket serverSocket;
    private int clientID = 0;
    private HashMap<Integer, Socket> socketMap = new HashMap<>();
    private HashMap<Integer, DataOutputStream> outputStreamMap = new HashMap<>();
        
    public boolean startCommunication(int portNumber)
    {
        try
        {
            serverSocket = new ServerSocket(portNumber);
            executorService.submit(new ServerStartThread());
        }
        catch (IOException ex)
        {
            return false;
        }

        return true;
    }
    
    public String getServerSocketInfo()
    {
        return serverSocket.getInetAddress().getHostAddress();
    }
    
    @Override
    public void stopCommunication()
    {
        this.running = false;
        
        try
        {
            this.serverSocket.close();

            serverSocket = null;
            clientID = 0;
            socketMap.clear();
            
            for (DataOutputStream dataOutputStream : outputStreamMap.values())
            {
                dataOutputStream.close();
            }
            
            outputStreamMap.clear();
        }
        catch (IOException e)
        {

        }
    }

    @Override
    public boolean sendString(int clientID, String message)
    {
        try
        {
            if ((socketMap.containsKey(clientID)) && (socketMap.get(clientID).isConnected()) && (outputStreamMap.containsKey(clientID)))
            {
                outputStreamMap.get(clientID).writeUTF(message);
                return true;
            }
        }
        catch (IOException ex) 
        {
  
        }
        return false;
    }
    
    public HashMap<Integer, Socket> getClientSocketSet()
    {
        return socketMap;
    }

    public ServerSocket getServerSocket()
    {
        return serverSocket;
    }

    private void processRequest(Socket communicationSocket) throws IOException
    {
        DataOutputStream dataOutputStream = new DataOutputStream(communicationSocket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(communicationSocket.getInputStream());
        outputStreamMap.put(clientID, dataOutputStream);
        fireEventHostConnect(clientID, communicationSocket);
        
        executorService.submit(new InputStreamThread(dataInputStream, clientID)); 
        executorService.submit(new DisconnectListenerThread(communicationSocket, clientID));    
    }

    @Override
    public boolean isConnectionAvailable(int clientID)
    {
        try
        {
            outputStreamMap.get(clientID).writeUTF(ARE_YOU_THERE);
            return true;
        }
        catch (IOException ex)
        {
            return false;
        }
    }

    public int getClientCount()
    {
        return clientID;
    }
        
    private class ServerStartThread implements Runnable
    {
        @Override
        public void run()
        {
            do
            {
                try
                {
                    Thread.sleep(200);
                    Socket communicationSocket = serverSocket.accept();
                    
                    clientID++;
                    socketMap.put(clientID, communicationSocket);
                    
                    processRequest(communicationSocket);
                    running = true;
                }
                catch (InterruptedException | IOException ex)
                {

                }
            }while (running);
        }            
    }  
}
