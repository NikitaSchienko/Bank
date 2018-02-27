package server;

import com.alibaba.fastjson.JSON;
import com.sun.corba.se.spi.legacy.interceptor.RequestInfoExt;
import database.Card;
import jdk.nashorn.internal.ir.RuntimeNode;
import requests.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class ServerThread implements Runnable
{

    private Socket socket;
    private Map<Long, Card> cardMap;

    public ServerThread(Socket socket, Map<Long,Card> cardMap)
    {
        this.cardMap = cardMap;
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
                Request request = JSON.parseObject(jsonLine, Request.class);

                String response = checkAccess(request)? "Доступ разрешен": "Запрет на доступ";

                out.writeUTF(response);
                out.flush();
            }
        }
        catch (Exception e)
        {
            System.out.println(socket.toString() + " - Сессия завершена");
        }
    }

    private boolean checkAccess(Request request)
    {
        Boolean access = false;

        if(cardMap.containsKey(request.getId()))
        {
            Card card = cardMap.get(request.getId());
            if(card.getCode() == request.getCode())
            {
                access = true;
            }
        }
        else
        {
            //throw new NullPointerException("Такой карты не существует!");
        }
        return access;
    }

    private String checkRequest(int numberRequest)
    {
        String response;

        switch (numberRequest)
        {
            case 1001:
            {
                response = ""
            }
            break;
            case 1002:
            {

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

            }

        }
    }
}
