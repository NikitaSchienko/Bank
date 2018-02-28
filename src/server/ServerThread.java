package server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.SourceTree;
import database.SynchonizedMap;
import processing.ProcessingRequest;
import processing.ProcessingRequestScore;
import requests.RequestScore;
import response.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.Socket;

public class ServerThread implements Runnable
{

    private Socket socket;
    private SynchonizedMap synchonizedMap;

    public ServerThread(Socket socket, SynchonizedMap synchonizedMap)
    {
        this.synchonizedMap = synchonizedMap;
        this.socket = socket;
    }

    public void run()
    {
        System.out.println(socket.toString() + " - Сессия запущена");
        try
        {
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();


            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String jsonLine = null;
            while(true)
            {
                System.out.println("Ожидание информации от клиента");
                jsonLine = in.readUTF();

                System.out.println("Клиент прислал строку:  " + jsonLine);
                JSONObject requestJSON = JSON.parseObject(jsonLine);
                Response response = identifyTypeRequest(requestJSON);

                out.writeUTF(JSON.toJSONString(response));
                out.flush();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
            System.out.println(socket.toString() + " - Сессия завершена");
        }
    }


    private Response identifyTypeRequest(JSONObject requestJSON)
    {
        System.out.println("Обработка типа");
        ProcessingRequest processingRequest = null;

        int type = requestJSON.getInteger("type");

        switch (type)
        {
            case 1001:
            {

            }
            break;
            case 1002:
            {
                System.out.println("Тип запроса - проверка счета");
                BigInteger id = requestJSON.getBigInteger("id");
                int code = requestJSON.getInteger("code");
                RequestScore requestScore = new RequestScore(id,code,type);
                processingRequest = new ProcessingRequestScore(requestScore, synchonizedMap);
            }
            break;
            case 1003:
            {

            }
            break;
            case 1004:
            {

            }
            break;
            default:
            {
                throw new NullPointerException("Неизвестная операция");
            }
        }
        return processingRequest.createResponse();
    }

}
