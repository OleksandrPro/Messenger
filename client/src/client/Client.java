package client;

import java.io.*;
import java.net.Socket;

public class Client
{
    private static String UserName;
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private static BufferedReader inputUser; // поток чтения с консоли
    private static Socket socket;

    public static void main(String[] args)
    {
        inputUser = new BufferedReader(new InputStreamReader(System.in));

        String ip = "";
        int port = 0;
        try
        {
            System.out.println("Enter Username:");
            UserName = inputUser.readLine();
            System.out.println("Enter ip:");
            ip = inputUser.readLine();
            System.out.println("Enter port:");
            port = Integer.parseInt(inputUser.readLine());
        }
        catch (IOException e)
        {
            System.err.println("Error reading input: " + e.getMessage());
            return;
        }

        System.out.println("ip: " + ip);
        System.out.println("port: " + port);

        try
        {
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.println("Enter message:");
            String message = inputUser.readLine();
            out.write("Message from " + UserName + ": " + message);
            out.newLine();  // Необходимо для отправки строки
            out.flush();

            String serverAnswer = in.readLine();
            System.out.println("Server answer: " + serverAnswer);
        }
        catch (IOException e)
        {
            System.err.println("Connection error: " + e.getMessage());
        }
        finally
        {
            try
            {
                if (in != null) in.close();
                if (out != null) out.close();
                System.out.println("IO-Streams are closed");
                socket.close();
                System.out.println("Socket is closed");
            }
            catch (IOException e)
            {
                System.err.println("Error closing streams: " + e.getMessage());
            }
            System.out.println("Closing client");
        }
    }
}
