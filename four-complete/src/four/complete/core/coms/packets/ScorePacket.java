package four.complete.core.coms.packets;

/**
 *
 * @author Keshan De Silva
 */
public class ScorePacket implements Packet
{
    private int score;
    private int position;
    
    public ScorePacket(int score, int position)
    {
        this.score = score;
        this.position = position;
    }

    public ScorePacket(String dataStream)
    {
        String[] components = dataStream.split(Packet.PARAMETER_SEPARATOR_1);
        score = Integer.parseInt(components[0]);
        position = Integer.parseInt(components[1]);
    }

    public int getScore()
    {
        return score;
    }

    public int getPosition()
    {
        return position;
    }

    @Override
    public String getDataStream()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(score).append(Packet.PARAMETER_SEPARATOR_1);
        stringBuilder.append(position).append(Packet.PARAMETER_SEPARATOR_1);
        return stringBuilder.toString();
    }
    
    
}
