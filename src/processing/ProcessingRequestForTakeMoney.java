package processing;

import database.Card;
import database.SynchronizedMap;
import requests.Constants;
import requests.RequestForTakeMoney;
import response.Response;

public class ProcessingRequestForTakeMoney implements ProcessingRequest
{
    private RequestForTakeMoney request;
    private SynchronizedMap synchronizedMap;

    public ProcessingRequestForTakeMoney(RequestForTakeMoney request, SynchronizedMap synchronizedMap)
    {
        this.request = request;
        this.synchronizedMap = synchronizedMap;
    }

    public Response createResponse()
    {
        Response response = new Response();

        if(checkAccess(request))
        {
            if(checkMoney(request))
            {
                transaction(request);
                response.setCodeException(Constants.CORRECT_CODE);
                response.setMoney(request.getMoney());
            }
            else
            {
                response.setCodeException(Constants.NOT_ENOUGH_MONEY);
            }

        }
        else
        {
            response.setCodeException(Constants.INCORRECT_CODE);
        }

        response.setType(request.getType());

        return response;
    }

    private boolean checkAccess(RequestForTakeMoney request)
    {
        Boolean access = false;

        if(synchronizedMap.containsCard(request.getId()))
        {
            Card card = synchronizedMap.getCard(request.getId());
            if(card.getCode() == request.getCode())
            {
                access = true;
            }
        }
        else
        {
            throw new NullPointerException("Такой карты не существует!");
        }
        return access;
    }



    private boolean checkMoney(RequestForTakeMoney request)
    {
        return (synchronizedMap.getCard(request.getId()).getMoney() - request.getMoney()) >= 0;
    }

    private void transaction(RequestForTakeMoney request)
    {
        Card myCard = synchronizedMap.getCard(request.getId());

        double myMoney = myCard.getMoney() - request.getMoney();

        synchronizedMap.getCard(myCard.getId()).setMoney(myMoney);

    }
}
