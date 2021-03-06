package processing;

import database.Card;
import database.SynchronizedMap;
import requests.Constants;
import requests.RequestScore;
import response.Response;

public class ProcessingRequestScore implements ProcessingRequest
{
    private RequestScore request;
    private SynchronizedMap synchronizedMap;

    public ProcessingRequestScore(RequestScore request, SynchronizedMap synchronizedMap)
    {
        this.request = request;
        this.synchronizedMap = synchronizedMap;
    }

    public Response createResponse()
    {
        Response response = new Response();

        if(checkAccess(request))
        {
            Card card = synchronizedMap.getCard(request.getId());
            response.setCodeException(Constants.CORRECT_CODE);
            response.setId(request.getId());
            response.setMoney(card.getMoney());
            response.setType(request.getType());
        }
        else
        {
            response.setCodeException(Constants.INCORRECT_CODE);
        }

        return response;
    }

    private boolean checkAccess(RequestScore request)
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


}
