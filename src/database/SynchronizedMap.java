package database;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Map;

public class SynchronizedMap
{
    private Map<BigInteger,Card> mapCard;
    private OperationCardDAO operationCardDAO;

    public SynchronizedMap()
    {
        operationCardDAO = new OperationCardDAO();
        mapCard = operationCardDAO.getMapCards();
    }

    public synchronized Map<BigInteger, Card> getMapCard()
    {
        return mapCard;
    }

    public synchronized void remoteCardInMap(BigInteger id)
    {
        mapCard.remove(id);
        operationCardDAO.delete(id);
    }

    public synchronized boolean containsCard(BigInteger id)
    {
        return mapCard.containsKey(id);
    }

    public synchronized Card getCard(BigInteger id)
    {
        return mapCard.get(id);
    }

    public synchronized void setCard(Card card)
    {
        mapCard.remove(card.getId());
        operationCardDAO.delete(card.getId());

        mapCard.put(card.getId(),card);
        operationCardDAO.insert(card);
    }

    public synchronized void setCardMoney(Card card, double money)
    {
        card.setMoney(money);
        operationCardDAO.delete(card.getId());
        operationCardDAO.insert(card);
    }

    public synchronized void putCard(BigInteger id, Card card)
    {
        mapCard.put(id,card);
        operationCardDAO.insert(card);
    }

}
