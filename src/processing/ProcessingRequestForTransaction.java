package processing;

import database.Card;
import database.SynchronizedMap;
import requests.Constants;
import requests.RequestForTransaction;
import response.Response;

public class ProcessingRequestForTransaction implements ProcessingRequest
{
    private RequestForTransaction request;
    private SynchronizedMap synchronizedMap;

    public ProcessingRequestForTransaction(RequestForTransaction requestForTransaction, SynchronizedMap synchronizedMap)
    {
        this.request = requestForTransaction;
        this.synchronizedMap = synchronizedMap;
    }

    public Response createResponse()
    {
        Response response = new Response();

        if(checkAccess(request))
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

    private boolean checkCard(RequestForTransaction request)
    {
        return synchronizedMap.containsCard(request.getIdRecipient());
    }

    private boolean checkMoney(RequestForTransaction request)
    {
        return (synchronizedMap.getCard(request.getId()).getMoney() - request.getMoney()) >= 0;
    }

    private void transaction(RequestForTransaction request)
    {
        Card myCard = synchronizedMap.getCard(request.getId());
        Card cardRecipient = synchronizedMap.getCard(request.getIdRecipient());

        double myMoney = myCard.getMoney() - request.getMoney();
        double moneyRecipient = cardRecipient.getMoney() + request.getMoney();

        synchronizedMap.setCardMoney(myCard,myMoney);
        synchronizedMap.setCardMoney(cardRecipient,moneyRecipient);
    }
}
