package dbhandler;

import java.sql.*;


public class SQLite {
	private static Connection con;
	private static boolean hasData = false;
	private static final String DB_NAME = "users";
	private static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM "+DB_NAME+" WHERE login=?";
	private static final String SQL_SELECT_BY_LOGIN_PASS = "SELECT * FROM "+ DB_NAME+" WHERE login=? AND password =?";
	public Statement getStatement() throws ClassNotFoundException, SQLException {
		if (con == null) {
			getConnection();
		}
		return con.createStatement();
	}
	

	public boolean checkLoginExistsInDB(String login) {
		boolean result = false;
		try {
        	if (con == null) {
    			getConnection();
    		}
            PreparedStatement pstmt = con.prepareStatement(SQL_SELECT_BY_LOGIN);    
            pstmt.setString(1, login);
            // returns ResultSet object generated by the query
            ResultSet rs = pstmt.executeQuery();
            // process rows from the query result
            if (rs.next()) {
            	result = true;
            } else {
            	result = false;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return result;
		
	}

    public boolean checkAuthentication(String login, String pass) {
        boolean result = false;
        try {
        	if (con == null) {
    			getConnection();
    		}
            PreparedStatement pstmt = con.prepareStatement(SQL_SELECT_BY_LOGIN);    
            pstmt.setString(1, login);
            // returns ResultSet object generated by the query
            ResultSet rs = pstmt.executeQuery();
            // process rows from the query result
            while (rs.next()) {
                result = rs.getString("password").equals(pass);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
        
        return result;
    }
	
    
	public ResultSet displayUsers() throws SQLException, ClassNotFoundException {
		Statement state = getStatement();
		ResultSet res = state.executeQuery("SELECT id,login,password FROM "+ DB_NAME);
		return res;
	}

	private void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:chat.db");
		initialise();
	}

	private void initialise() throws SQLException {
		if (!hasData) {
			hasData = true;
			
			Statement state = con.createStatement();
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name = '"+DB_NAME+"'");
			if (!res.next()) {
				System.out.println("DB is successfully created.");
				Statement state1 = con.createStatement();
				state1.execute("CREATE TABLE "+DB_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT,"
								+ "login VARCHAR(15), password VARCHAR(15))");
			}
		}
	}
	
	public void addUser(String login,String pass) throws ClassNotFoundException, SQLException {
		if (!checkLoginExistsInDB(login)) {
			Statement state = getStatement();
			state.execute("INSERT INTO "+DB_NAME+"(login,password) VALUES('"+login+"','"+pass+"')");
		} else {
			System.out.println("User already exists");
		}
		
	}

}
