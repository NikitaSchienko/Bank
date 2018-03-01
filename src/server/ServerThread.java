package server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import database.SynchronizedMap;
import processing.*;
import requests.RequestForAddMoney;
import requests.RequestForTakeMoney;
import requests.RequestForTransaction;
import requests.RequestScore;
import response.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class ServerThread implements Runnable
{

    private Socket socket;
    private SynchronizedMap synchronizedMap;
    private Semaphore semaphore;

    public ServerThread(Socket socket, SynchronizedMap synchronizedMap, Semaphore semaphore)
    {
        this.synchronizedMap = synchronizedMap;
        this.socket = socket;
        this.semaphore = semaphore;
    }

    public void run()
    {

        System.out.println(socket.toString() + " - Сессия запущена");
        try
        {
            semaphore.acquire();
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();


            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String jsonLine = null;
            while(true)
            {
                System.out.println("Ожидание информации от клиента - "+ socket.toString());
                jsonLine = in.readUTF();

                System.out.println(socket.toString() +" - Клиент прислал строку:  " + jsonLine);
                JSONObject requestJSON = JSON.parseObject(jsonLine);
                Response response = identifyTypeRequest(requestJSON);

                out.writeUTF(JSON.toJSONString(response));
                out.flush();
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(socket.toString() + " - Произошла ошибка");
            System.out.println(socket.toString() + " - Сессия завершена");
        }
        semaphore.release();
    }


    private Response identifyTypeRequest(JSONObject requestJSON)
    {
        System.out.println("Обработка типа запроса от клиента - " + socket.toString());
        ProcessingRequest processingRequest = null;

        BigInteger id = requestJSON.getBigInteger("id");
        int code = requestJSON.getInteger("code");
        int type = requestJSON.getInteger("type");

        switch (type)
        {
            case 1003:
            {
                System.out.println(socket.toString()+ " Тип запроса - внести деньги");
                double money = requestJSON.getDouble("money");
                RequestForAddMoney requestForAddMoney = new RequestForAddMoney(id,  code, type, money);
                processingRequest = new ProcessingRequestForAddMoney(requestForAddMoney, synchronizedMap);
            }
            break;
            case 1001:
            {
                System.out.println(socket.toString()+ " Тип запроса - проверка счета");
                RequestScore requestScore = new RequestScore(id,code,type);
                processingRequest = new ProcessingRequestScore(requestScore, synchronizedMap);
            }
            break;
            case 1002:
            {
                System.out.println(socket.toString()+ " Тип запроса - получить деньги");
                double money = requestJSON.getDouble("money");

                RequestForTakeMoney requestForTakeMoney = new RequestForTakeMoney(id, code, type,  money);
                processingRequest = new ProcessingRequestForTakeMoney(requestForTakeMoney, synchronizedMap);
            }
            break;
            case 1004:
            {
                System.out.println(socket.toString()+ " Тип запроса - перевод");
                BigInteger idRecipient = requestJSON.getBigInteger("idRecipient");
                double money = requestJSON.getDouble("money");

                RequestForTransaction requestForTakeMoney = new RequestForTransaction(id,  code, type, money,idRecipient);
                processingRequest = new ProcessingRequestForTransaction(requestForTakeMoney, synchronizedMap);
            }
            break;
            default:
            {
                throw new NullPointerException(socket.toString()+ " Неизвестная операция");
            }
        }
        return processingRequest.createResponse();
    }

}
