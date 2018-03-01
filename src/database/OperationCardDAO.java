package database;

import java.math.BigInteger;
import java.util.Map;

public class OperationCardDAO
{
    private FileCardDAO fileCardDAO;
    private Map<BigInteger,Card> mapCards;

    public OperationCardDAO()
    {
        fileCardDAO = new FileCardDAO();
        mapCards = fileCardDAO.getMapCards();
    }

    public Map<BigInteger,Card> getMapCards()
    {
        return mapCards;
    }

    public void insert(Card newCard)
    {
        mapCards.put(newCard.getId(),newCard);
        fileCardDAO.saveCards();
    }

    public void delete(BigInteger id)
    {
        mapCards.remove(id);
        fileCardDAO.saveCards();
    }
}
