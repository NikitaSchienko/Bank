package database;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


@XmlRootElement
public class MapCards
{
    private Map<BigInteger, Card> mapCards;

    public Map<BigInteger, Card> getMapCards()
    {
        return mapCards;
    }

    public void setMapCards(Map<BigInteger, Card> mapCards)
    {
        this.mapCards = mapCards;
    }
}
