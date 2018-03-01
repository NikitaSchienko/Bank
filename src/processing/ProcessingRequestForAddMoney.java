package processing;

import database.Card;
import database.SynchronizedMap;
import requests.Constants;
import requests.RequestForAddMoney;
import response.Response;

public class ProcessingRequestForAddMoney implements ProcessingRequest
{

    private RequestForAddMoney request;
    private SynchronizedMap synchronizedMap;

    public ProcessingRequestForAddMoney(RequestForAddMoney request, SynchronizedMap synchronizedMap)
    {
        this.request = request;
        this.synchronizedMap = synchronizedMap;
    }

    public Response createResponse()
    {
        Response response = new Response();

        if(checkAccess(request))
        {
            transaction(request);
            response.setCodeException(Constants.CORRECT_CODE);
            response.setMoney(request.getMoney());
        }
        else
        {
            response.setCodeException(Constants.INCORRECT_CODE);
        }

        response.setType(request.getType());

        return response;
    }

    private boolean checkAccess(RequestForAddMoney request)
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



    private void transaction(RequestForAddMoney request)
    {
        Card myCard = synchronizedMap.getCard(request.getId());

        double myMoney = myCard.getMoney() + request.getMoney();

        synchronizedMap.getCard(myCard.getId()).setMoney(myMoney);

    }
}
