package four.complete.core.engine;

import communication.manager.core.CommunicationServer;

/**
 *
 * @author Keshan De Silva
 */
public class TokenEngine
{
    private static TokenEngine tokenEngine;
    
    private GameEngine gameEngine;
    private int currentPlayerIndex;
    
    private TokenEngine(){}
    
    public static synchronized TokenEngine getInstance()
    {
        if (tokenEngine == null)
        {
            tokenEngine = new TokenEngine();
        }
        return tokenEngine;
    }
    
    public void initialized(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        this.currentPlayerIndex = 0;
        this.gameEngine.setPlayerTurn(true);
    }

    public void nextPlayer()
    {
        int clientCount = ((CommunicationServer)gameEngine.getCommunicationManager()).getClientCount();
        if (currentPlayerIndex == clientCount)
        {
            currentPlayerIndex = 0;
            gameEngine.setPlayerTurn(true);
        }
        else
        {
            currentPlayerIndex = getCurrentPlayerIndex() + 1;
        }
    }
    
    public int getCurrentPlayerIndex()
    {
        return currentPlayerIndex;
    }
    
    
    
    
}
