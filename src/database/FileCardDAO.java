package database;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileCardDAO
{
    private final static String PATH = "C:\\Users\\nish0817\\Desktop\\Портфолио\\Bank\\file\\cards.xml";

    private Map<Long, Card> mapCards;

    public FileCardDAO()
    {
         mapCards = new HashMap<Long, Card>();
         loadCards();
    }

    public Map<Long, Card> getMapCards()
    {
        return mapCards;
    }


    public void saveCards()
    {
        try
        {
            File file = new File(PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(MapCards.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            MapCards map = new MapCards();
            map.setMapCards(mapCards);

            marshaller.marshal(map, file);
            marshaller.marshal(map, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void loadCards()
    {
        try
        {
            File file = new File(PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(MapCards.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            MapCards map = (MapCards) jaxbUnmarshaller.unmarshal(file);
            mapCards = map.getMapCards();

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }

}