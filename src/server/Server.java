package server;

import database.Card;
import database.FileCardDAO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server
{
    private final static Integer PORT = 6666;



    public static void main(String[] ar)
    {
        FileCardDAO fileCardDAO = new FileCardDAO();

        Map<Long, Card> cardMap = fileCardDAO.getMapCards();

        ServerSocket serverSocket;
        System.out.println("Server - start");
        try
        {
            serverSocket = new ServerSocket(PORT);
            while (true)
            {
                new Thread(new ServerThread(serverSocket.accept(), cardMap)).start();
            }
        }
        catch(Exception x)
        {
            x.printStackTrace();
        }
    }
}
