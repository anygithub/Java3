package client;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.*;



public class Client extends JFrame
{
  private String SERVER_HOST = "localhost"; 
  private int SERVER_PORT = 8888;
  private Socket clientSocket;
  private Scanner inMsg;
  private PrintWriter outMsg;
  private JTextField jtfMsg;
  private JTextField jtfName;
  private JTextArea jTextAreaMsg;

  public Client(String login) throws HeadlessException 
  {
    try
    {
      clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
      inMsg = new Scanner(clientSocket.getInputStream());
      outMsg = new PrintWriter(clientSocket.getOutputStream());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    setBounds(500, 300, 500, 400);
    setTitle("Client of chat");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jTextAreaMsg = new JTextArea();
    jTextAreaMsg.setEditable(false);
    jTextAreaMsg.setLineWrap(true);

    JScrollPane jScrollPane = new JScrollPane(jTextAreaMsg);
    add(jScrollPane, BorderLayout.CENTER);

    JLabel labelCountOfClient = new JLabel("Count of client in chat : ");
    add(labelCountOfClient, BorderLayout.NORTH);

    JPanel bottomPanel = new JPanel(new BorderLayout());
    add(bottomPanel, BorderLayout.SOUTH);

    JLabel countsOfClientLabel = new JLabel("Counts of clients in chat :");
    add(countsOfClientLabel, BorderLayout.NORTH);

    JButton sendButton = new JButton("SEND");
    bottomPanel.add(sendButton, BorderLayout.EAST);

    jtfMsg = new JTextField("Please input your msg");
    bottomPanel.add(jtfMsg, BorderLayout.CENTER);

    JLabel nameLabel = new JLabel(login);
    //jtfName = new JTextField("Your name");
    bottomPanel.add(nameLabel, BorderLayout.WEST);

    sendButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        String msg = jtfMsg.getText().trim();
        //String name = jtfName.getText().trim();

        if (!msg.isEmpty() && !login.isEmpty())
        {
          sendMsg(login);
          jtfMsg.grabFocus();

        }
      }
    });

    jtfMsg.addFocusListener(new FocusAdapter()
    {
      @Override
      public void focusGained(FocusEvent e)
      {
        jtfMsg.setText("");
      }
    });

    new Thread(new Runnable()
    {
      @Override
      public void run()
      {

        while (true)
        {
          if (inMsg.hasNext())
          {
            String msg = inMsg.nextLine();
            String clientInChat = "Counts of clients in chat : ";
            if (msg.indexOf(clientInChat) == 0)
            {
              countsOfClientLabel.setText(clientInChat);
            }
            else
            {
              jTextAreaMsg.append(msg);
              jTextAreaMsg.append("\n");
            }

          }
        }
      }
    }).start();

    addWindowListener(new WindowAdapter()
    {
      @Override
      public void windowClosing(WindowEvent e)
      {
        super.windowClosing(e);
        if (!login.isEmpty() && !login.equalsIgnoreCase("Your name"))
        {
          outMsg.println(login + " exited from chat.");
        }
        else
        {
          outMsg.println("Anonnym client exited from our chat");
        }

        outMsg.println("QUIT");
        outMsg.flush();
        outMsg.close();
        inMsg.close();
        try
        {
          clientSocket.close();
        }
        catch (IOException e1)
        {
          e1.printStackTrace();
        }


      }
    });
    setVisible(true);
  }

  private void sendMsg(String login)
  {
    String msg = login + ":" + jtfMsg.getText();
    outMsg.println(msg);
    outMsg.flush();
    jtfMsg.setText("");
  }
}
