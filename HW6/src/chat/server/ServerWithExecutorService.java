package chat.server;



import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerWithExecutorService
{
  private List<ClientHandlerWithExecutorService> clientHandlers = new ArrayList<>();

  public ServerWithExecutorService()
  {
    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    try
    {
      serverSocket = new ServerSocket(8888);
      System.out.println("Server launched");
      ExecutorService executorService = Executors.newFixedThreadPool(10);

      while (true)
      {
        clientSocket = serverSocket.accept();
        ClientHandlerWithExecutorService client = new ClientHandlerWithExecutorService(clientSocket, this);
        clientHandlers.add(client);
        //new Thread(client).start();
        executorService.execute(client);
        
        
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        serverSocket.close();
        clientSocket.close();
        System.out.println("Server stoped");
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }


  }

  public void notificationAllClientWithNewMessage(String msg)
  {
    for (ClientHandlerWithExecutorService clientHandler : clientHandlers)
    {
      clientHandler.sendMessage(msg);    
    }

  }

  public void removeClient(ClientHandlerWithExecutorService clientHandler)
  {
    clientHandlers.remove(clientHandler);
  } 

  
}
