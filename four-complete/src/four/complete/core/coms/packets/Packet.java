package four.complete.core.coms.packets;

/**
 *
 * @author Keshan De Silva
 */
public interface Packet
{
    public static final String LIST_SEPARATOR = "`";
    public static final String PARAMETER_SEPARATOR_1 = "~";
    public static final String PARAMETER_SEPARATOR_2 = "!";
    public static final String PARAMETER_SEPARATOR_3 = "@";
    public static final String PARAMETER_SEPARATOR_4= "=";
    
    public static final String GAME_START_PACKET = "#";
    public static final String LINE_SEGMENT_PACKET = "$";
    public static final String PLAYER_TURN_PACKET = "%";
    public static final String PLAYER_TURN_COMPLETE_PACKET = "^";
    public static final String GAME_COMPLETE_PACKET = "&";
    public static final String CURRENT_PLAYER_PACKET = "*";
    public static final String SCORE_PACKET = "(";
    public static final String GAME_SUMMARY_PACKET = ")";
    
    public abstract String getDataStream();
}
