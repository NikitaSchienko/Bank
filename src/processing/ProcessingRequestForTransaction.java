package processing;

import database.Card;
import database.SynchonizedMap;
import requests.Constants;
import requests.RequestForTransaction;
import requests.RequestScore;
import response.Response;

public class ProcessingRequestForTransaction implements ProcessingRequest
{
    private RequestForTransaction request;
    private SynchonizedMap synchonizedMap;

    public ProcessingRequestForTransaction(RequestForTransaction requestForTransaction, SynchonizedMap synchonizedMap)
    {
        this.request = requestForTransaction;
        this.synchonizedMap = synchonizedMap;
    }

    public Response createResponse()
    {
        Response response = new Response();

        if(checkAccess(request) && checkCard(request) && checkMoney(request))
        {
            if(checkCard(request))
            {
                if(checkMoney(request))
                {
                    transaction(request);
                    response.setCodeException(Constants.CORRECT_CODE);
                    response.setId(request.getIdRecipient());
                    response.setMoney(request.getMoney());
                }
                else
                {
                    response.setCodeException(Constants.NOT_ENOUGH_MONEY);
                }
            }
            else
            {
                response.setCodeException(Constants.INCORRECT_CARD_RECIPIENT);
            }

        }
        else
        {
            response.setCodeException(Constants.INCORRECT_CODE);
        }

        response.setType(request.getType());

        return response;
    }

    private boolean checkAccess(RequestForTransaction request)
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

    private boolean checkCard(RequestForTransaction request)
    {
        return synchonizedMap.containsCard(request.getIdRecipient());
    }

    private boolean checkMoney(RequestForTransaction request)
    {
        return (synchonizedMap.getCard(request.getId()).getMoney() - request.getMoney()) >= 0;
    }

    private void transaction(RequestForTransaction request)
    {
        Card myCard = synchonizedMap.getCard(request.getId());
        Card cardRecipient = synchonizedMap.getCard(request.getIdRecipient());

        double myMoney = myCard.getMoney() - request.getMoney();
        double moneyRecipient = cardRecipient.getMoney() + request.getMoney();

        synchonizedMap.getCard(myCard.getId()).setMoney(myMoney);
        synchonizedMap.getCard(cardRecipient.getId()).setMoney(moneyRecipient);
    }
}
