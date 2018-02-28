package processing;

import database.Card;
import database.SynchonizedMap;
import requests.Constants;
import requests.RequestForMoney;
import response.Response;

public class ProcessingRequestForMoney
{
    private RequestForMoney request;
    private SynchonizedMap synchonizedMap;

    public ProcessingRequestForMoney(RequestForMoney request, SynchonizedMap synchonizedMap)
    {
        this.request = request;
        this.synchonizedMap = synchonizedMap;
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

    private boolean checkAccess(RequestForMoney request)
    {
        Boolean access = false;

        if(synchonizedMap.containsCard(request.getId()))
        {
            Card card = synchonizedMap.getCard(request.getId());
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



    private boolean checkMoney(RequestForMoney request)
    {
        return (synchonizedMap.getCard(request.getId()).getMoney() - request.getMoney()) >= 0;
    }

    private void transaction(RequestForMoney request)
    {
        Card myCard = synchonizedMap.getCard(request.getId());

        double myMoney = myCard.getMoney() - request.getMoney();

        synchonizedMap.getCard(myCard.getId()).setMoney(myMoney);

    }
}
