package client;
import com.alibaba.fastjson.JSON;
import requests.*;

import java.math.BigInteger;
import java.net.*;
import java.io.*;

public class Client<T>
{

    private T request;
    private Socket socket;

    public Client(T request)
    {
        this.request = request;

        try
        {
            socket = new Socket("localhost", Constants.PORT);
            System.out.println(socket.toString() +" - Подключился к серверу");
        }
        catch (IOException e)
        {
            System.out.println(socket.toString() + " - Не удалось подключиться к серверу");
            e.printStackTrace();
        }
    }


    public void execute()
    {
        try
        {

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);


            String jsonRequest = JSON.toJSONString(request);

            System.out.println("Отправляем строку: " + jsonRequest);
            out.writeUTF(jsonRequest);
            out.flush();
            System.out.println("Получили строку: " + in.readUTF());

        }
        catch (Exception x)
        {
            x.printStackTrace();
        }
    }


}