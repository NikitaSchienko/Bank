package database;

import java.math.BigInteger;
import java.util.Map;

public class SynchonizedMap
{
    private Map<BigInteger,Card> mapCard;

    public SynchonizedMap(Map<BigInteger, Card> mapCard)
    {
        this.mapCard = mapCard;
    }

    public synchronized Map<BigInteger, Card> getMapCard()
    {
        return mapCard;
    }

    public synchronized void setMapCard(Map<BigInteger, Card> mapCard)
    {
        this.mapCard = mapCard;
    }

    public synchronized void remoteCardInMap(BigInteger id)
    {
        mapCard.remove(id);
    }

    public synchronized boolean containsCard(BigInteger id)
    {
        return mapCard.containsKey(id);
    }

    public synchronized Card getCard(BigInteger id)
    {
        return mapCard.get(id);
    }

    public synchronized void putCard(Card card)
    {
        mapCard.put(card.getId(),card);
    }

}
