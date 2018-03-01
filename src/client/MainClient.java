package client;

import java.util.ArrayList;

public class MainClient
{
    public static  void main(String[] args)
    {
        ArrayList<GenerationClients> clientsArrayList = new ArrayList<GenerationClients>();


        for(int i = 0; i < 100; i++)
        {
            clientsArrayList.add(new GenerationClients());
        }

        for (GenerationClients generationClient: clientsArrayList)
        {
            generationClient.executeClientRequest();
        }
    }
}
