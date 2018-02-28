package client;
import com.alibaba.fastjson.JSON;
import requests.Constants;
import requests.RequestScore;

import java.math.BigInteger;
import java.net.*;
import java.io.*;

public class Client
{
    public static void main(String[] ar)
    {
        int serverPort = 6666; // здесь обязательно нужно указать порт к которому привязывается сервер.
        //String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.

        try
        {

            Socket socket = new Socket("localhost", serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("Подклился к серверу");

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);


            RequestScore request = new RequestScore(new BigInteger("4215729546565"), 5421, Constants.REQUEST_MONEY);
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