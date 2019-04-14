package chat.server;


import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;


public class ClientHandlerWithExecutorService implements Runnable
{
  private Socket clientSocket;
  private ServerWithExecutorService server;
  private PrintWriter outMsg;
  private Scanner inMsg;
  private static int clientCount = 0;
  
  private static final Logger log = Logger.getLogger(ClientHandlerWithExecutorService.class.getName());


  public ClientHandlerWithExecutorService(Socket clientSocket, ServerWithExecutorService server)
  {
    try
    {
      clientCount++;
      this.clientSocket = clientSocket;
      this.server = server;
      this.outMsg = new PrintWriter(clientSocket.getOutputStream());
      this.inMsg = new Scanner(clientSocket.getInputStream());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  
  @Override
  public void run()
  {
    try
    {  	
      server.notificationAllClientWithNewMessage("New client in our chat");
      server.notificationAllClientWithNewMessage("In our chat client count = " + clientCount);
      log.info("New client in our chat");

      while (true)
      {
        if (inMsg.hasNext())
        {
          String clientMsg = inMsg.nextLine();
          if (clientMsg.equalsIgnoreCase("QUIT"))
          {
            break;
          }
          System.out.println(clientMsg);
          server.notificationAllClientWithNewMessage(clientMsg);
          log.info("Client sent message");
        }
      }

      Thread.sleep(1000);
    }
    catch (Exception e) {e.printStackTrace();}
    finally{exitFromChat();}

  }

  
  private void exitFromChat()
  {
    clientCount--;
    server.notificationAllClientWithNewMessage("Client exited. In out chat = " + clientCount + " clients!");
    server.removeClient(this);
    log.info("Client exited");
  }

  public void sendMessage(String msg)
  {
    try
    {
      outMsg.println(msg);
      outMsg.flush();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}