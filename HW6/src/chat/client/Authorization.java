package chat.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import chat.dbhandler.SQLite;



public class Authorization extends JFrame {
	private JTextField jtfLogin;
	private JTextField jtfPassword;
		  
	public Authorization() {
		
		setBounds(200, 100, 300, 105);
	    setTitle("Authorization form");
	    setResizable(false);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	   	  
	    JPanel topPanel = new JPanel(new BorderLayout());
	    add(topPanel, BorderLayout.NORTH);
	    
	    JPanel centerPanel = new JPanel(new BorderLayout());
	    add(centerPanel, BorderLayout.CENTER);
 
	    JPanel bottomPanel = new JPanel(new BorderLayout());
	    add(bottomPanel, BorderLayout.SOUTH);
	    
	    //fill top panel
	    JLabel jlblLogin = new JLabel("Login:");
	    topPanel.add(jlblLogin, BorderLayout.WEST);
	    
	    jtfLogin = new JTextField();
	    topPanel.add(jtfLogin, BorderLayout.CENTER);
	    
	    //fill central panel   
	    JLabel jlblPassword = new JLabel("Password:");
	    centerPanel.add(jlblPassword, BorderLayout.WEST);
	       	     
	    jtfPassword = new JTextField();
	    centerPanel.add(jtfPassword, BorderLayout.CENTER);
	    
	    //fill bottom panel
	    JButton jbtnLogin = new JButton("Login");
	    bottomPanel.add(jbtnLogin, BorderLayout.WEST);
	    
	    JButton jbtnSignUp = new JButton("Sign Up");
	    bottomPanel.add(jbtnSignUp, BorderLayout.EAST);
	    
	    SQLite sql = new SQLite();
	    
	    jbtnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				if (sql.checkAuthentication(jtfLogin.getText(), jtfPassword.getText())) {
					setVisible(false);
					Client c = new Client(jtfLogin.getText());
				}
				
			}
		});
	    
	    jbtnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {		
					try {
						if (!sql.checkLoginExistsInDB(jtfLogin.getText())) {
							sql.addUser(jtfLogin.getText(),jtfPassword.getText());
							setVisible(false);
							Client c = new Client(jtfLogin.getText());
						}
					} catch (ClassNotFoundException e1) {e1.printStackTrace();
					} catch (SQLException e1) {	e1.printStackTrace();}	
			}
		});
	    
	    setVisible(true);
	    	    
	    
	}
	
}
