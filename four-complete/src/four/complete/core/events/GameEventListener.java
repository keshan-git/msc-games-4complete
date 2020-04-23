package four.complete.core.events;

import four.complete.core.coms.packets.CurrentPlayerPacket;
import four.complete.core.coms.packets.GameStartPacket;
import four.complete.core.coms.packets.GameSummaryPacket;
import four.complete.core.coms.packets.LineSegmentPacket;
import four.complete.core.coms.packets.PlayerTurnCompletePacket;
import four.complete.core.coms.packets.PlayerTurnPacket;
import four.complete.core.coms.packets.ScorePacket;

/**
 *
 * @author Keshan De Silva
 */
public abstract class GameEventListener
{
    public void onGameStart(GameStartPacket gameStartPacket){}
    public void onLineSegmentAdded(LineSegmentPacket lineSegmentPacket){}
    public void onPlayerTurnStart(PlayerTurnPacket playerTurnPacket){}
    public void onPlayerTurnComplete(PlayerTurnCompletePacket playerTurnCompletePacket){}
    public void onGameComplete(GameSummaryPacket gameSummaryPacket){}
    public void onPlayerTurnUpdate(CurrentPlayerPacket currentPlayerPacket){}
    public void onScoreUpdate(ScorePacket scorePacket){}
    public void onGameSummaryUpdate(GameSummaryPacket gameSummaryPacket){ }
}
