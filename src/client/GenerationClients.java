package client;

import database.Card;
import database.FileCardDAO;
import processing.ProcessingRequestForAddMoney;
import processing.ProcessingRequestForTakeMoney;
import processing.ProcessingRequestForTransaction;
import processing.ProcessingRequestScore;
import requests.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;





public class GenerationClients
{

    private  Integer[] type_requests =  {
            Constants.GET_MONEY,
            Constants.ADD_MONEY,
            Constants.TRANSFER_MONEY,
            Constants.TAKE_MONEY
    };

    private Client client;
    private  Random random;

    public GenerationClients()
    {
        random  = new Random();
        execute();
    }

    public void executeClientRequest()
    {
        client.execute();
    }

    private Map<BigInteger, Card> getListCard()
    {
        FileCardDAO fileCardDAO = new FileCardDAO();
        return fileCardDAO.getMapCards();
    }

    private Card getRandomCardInMap()
    {
        Map<BigInteger, Card> map = getListCard();
        List<Card> list = new ArrayList<Card>(map.values());
        return list.get(random.nextInt(list.size()));

    }

    private void execute()
    {
        Card card = getRandomCardInMap();
        int type_request  = type_requests[random.nextInt(type_requests.length)];
        double money = random.nextInt(1000);


        switch (type_request)
        {
            case 1003:
            {
                RequestForAddMoney requestForAddMoney = new RequestForAddMoney(card.getId(),  card.getCode(),Constants.ADD_MONEY, money);
                client = new Client<RequestForAddMoney>(requestForAddMoney);
            }
            break;
            case 1001:
            {
                RequestScore requestScore = new RequestScore(card.getId(),card.getCode(),Constants.GET_MONEY);
                client = new Client<RequestScore>(requestScore);
            }
            break;
            case 1002:
            {
                RequestForTakeMoney requestForTakeMoney = new RequestForTakeMoney(card.getId(),card.getCode(), Constants.TAKE_MONEY,  money);
                client = new Client<RequestForTakeMoney>(requestForTakeMoney);
            }
            break;
            case 1004:
            {
                Map<BigInteger,Card> map = getListCard();
                map.remove(card.getId());
                List<Card> list = new ArrayList<Card>(map.values());
                Card cardRecipient = list.get(random.nextInt(list.size()));


                RequestForTransaction requestForTakeMoney = new RequestForTransaction(card.getId(),card.getCode(), Constants.TRANSFER_MONEY, money,cardRecipient.getId());
                client = new Client<RequestForTransaction>(requestForTakeMoney);
            }
            break;
            default:
            {
                throw new NullPointerException("Неизвестная операция");
            }
        }

    }

}
