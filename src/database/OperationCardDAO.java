package database;

import java.util.Map;

public class OperationCardDAO
{
    private FileCardDAO fileCardDAO;
    private Map<Long, Card> mapCards;

    public OperationCardDAO()
    {
        fileCardDAO = new FileCardDAO();
        mapCards = fileCardDAO.getMapCards();
    }

    public void insert(Card newCard)
    {
        mapCards.put(newCard.getNumber(),newCard);
        fileCardDAO.saveCards();
    }

    public void delete(Long id)
    {
        mapCards.remove(mapCards.get(id));
        fileCardDAO.saveCards();
    }
}
