package processing;

import database.Card;
import database.SynchonizedMap;
import requests.Constants;
import requests.RequestScore;
import response.Response;

public class ProcessingRequestScore implements ProcessingRequest
{
    private RequestScore request;
    private SynchonizedMap synchonizedMap;

    public ProcessingRequestScore(RequestScore request, SynchonizedMap synchonizedMap)
    {
        this.request = request;
        this.synchonizedMap = synchonizedMap;
    }

    public Response createResponse()
    {
        Response response = new Response();

        if(checkAccess(request))
        {
            Card card = synchonizedMap.getCard(request.getId());
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


}
