package database;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;


@XmlRootElement
public class MapCards
{
    private Map<Long, Card> mapCards;

    public Map<Long, Card> getMapCards()
    {
        return mapCards;
    }

    public void setMapCards(Map<Long, Card> mapCards)
    {
        this.mapCards = mapCards;
    }
}
