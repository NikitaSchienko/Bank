package database;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class OperationCardDAO
{
    private FileCardDAO fileCardDAO;
    private Map<BigInteger, Card> mapCards = new HashMap<BigInteger, Card>();

    public OperationCardDAO()
    {
        fileCardDAO = new FileCardDAO();
        mapCards = fileCardDAO.getMapCards();
    }

    public void insert(Card newCard)
    {
        mapCards.put(newCard.getId(),newCard);
        fileCardDAO.saveCards();
    }

    public void delete(Long id)
    {
        mapCards.remove(mapCards.get(id));
        fileCardDAO.saveCards();
    }
}
