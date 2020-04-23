package communication.manager.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Keshan De Silva
 * Created on Oct 7, 2014
 */
public class CommunicationClient extends CommunicationManager
{
    private Socket clientSocket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public boolean startCommunication(String host, int portNumber)
    {
        try
        {
            clientSocket = new Socket(host, portNumber);
            executorService.submit(new ClientStartThread());
        }
        catch (IOException ex)
        {
            return false;
        }

        return true;
    }
    
    @Override
    public void stopCommunication()
    {
        this.running = false;
        
        try
        {
            this.clientSocket.close();
            clientSocket = null;
            dataOutputStream.close();
            dataInputStream.close();
            dataInputStream = null;
            dataOutputStream = null; 
        }
        catch (IOException e){}
    }

    @Override
    public boolean sendString(int clientID, String message)
    {
        try
        {
            if (clientID == SERVER_ID)
            {
                if (running && (clientSocket.isConnected()) && (dataOutputStream != null))
                {
                    dataOutputStream.writeUTF(message);
                    return true;
                }
            }
        }
        catch (IOException ex) 
        {
  
        }
        return false;
    }
    
    @Override
    public boolean isConnectionAvailable(int clientID)
    {
        try
        {
            dataOutputStream.writeUTF(ARE_YOU_THERE);
            return true;
        }
        catch (IOException ex)
        {
            return false;
        }
    }
        
    private void processRequest(Socket clientSocket) throws IOException
    {
        dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        dataInputStream = new DataInputStream(clientSocket.getInputStream());
        fireEventHostConnect(SERVER_ID, clientSocket);
        
        executorService.submit(new InputStreamThread(dataInputStream, SERVER_ID)); 
        executorService.submit(new DisconnectListenerThread(clientSocket, SERVER_ID));    
    }
   
    private class ClientStartThread implements Runnable
    {
        @Override
        public void run()
        {
            do
            {
                try
                {
                    Thread.sleep(1000);
                    processRequest(clientSocket);
                    running = true;
                }
                catch (InterruptedException | IOException ex)
                {
                    running = false;
                }
            } while (!running);
        }
 
    }

}
