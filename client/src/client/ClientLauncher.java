package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientLauncher
{
    public static void main(String[] args)
    {
        BufferedReader inputUser = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            System.out.println("Enter Username:");
            String userName = inputUser.readLine();
            System.out.println("Enter ip:");
            String ip = inputUser.readLine();
            System.out.println("Enter port:");
            int port = Integer.parseInt(inputUser.readLine());

            Client client = new Client(userName, ip, port);
            client.start();
        }
        catch (IOException e)
        {
            System.err.println("Error reading input: " + e.getMessage());
            return;
        }

    }
}
