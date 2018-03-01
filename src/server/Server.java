package server;

import database.FileCardDAO;
import database.SynchronizedMap;
import requests.Constants;

import java.net.ServerSocket;
import java.util.concurrent.Semaphore;

public class Server
{



    public static void main(String[] ar)
    {
        Semaphore semaphore = new Semaphore(Constants.COUNT_CLIENT);

        SynchronizedMap synchronizedMap = new SynchronizedMap();
        ServerSocket serverSocket;
        System.out.println("Server - start");
        try
        {
            serverSocket = new ServerSocket(Constants.PORT);
            while (true)
            {
                new Thread(new ServerThread(serverSocket.accept(), synchronizedMap, semaphore)).start();
            }
        }
        catch(Exception x)
        {
            System.out.println("Server - Произошла ошибка на сервере");
            System.out.println("Server - Упал :(");
            x.printStackTrace();
        }
    }
}
