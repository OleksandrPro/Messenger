package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server
{
    public static final int PORT = 10100;
    private static boolean isWorking = true;
    private static ServerSocket serverSocket;
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private static LinkedList<Socket> clientSockets;

    public static void main(String[] args) throws IOException
    {
        System.out.println("Starting server");
        try
        {
            serverSocket = new ServerSocket(PORT);
            clientSockets = new LinkedList<>();
        }
        catch (IOException e)
        {
            System.err.println("Socket failed");
        }

        while(isWorking)
        {
            Socket clientSocket = serverSocket.accept();
            try
            {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String clientMessage = in.readLine();
                System.out.println("Message: " + clientMessage);

                out.write("Message received :" + clientMessage.toUpperCase());
                out.flush();
            }
            catch (IOException e)
            {

            }
            finally
            {
                try
                {
                    if (in != null) in.close();
                    if (out != null) out.close();
                    System.out.println("IO-Streams are closed");
                    clientSocket.close();
                    System.out.println("Client Socket is closed");
                    serverSocket.close();
                    System.out.println("Server Socket is closed");
                }
                catch (IOException e)
                {
                    System.err.println("Error closing streams: " + e.getMessage());
                }
                System.out.println("Closing server");
            }
        }

    }
}