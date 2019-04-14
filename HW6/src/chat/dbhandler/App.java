package chat.dbhandler;
import java.sql.ResultSet;
import java.sql.SQLException;


public class App {

	public static void main(String[] args) {
		SQLite test = new SQLite();
	
		ResultSet rs;
		try {
			rs = test.displayUsers();
			while(rs.next()) {
				System.out.println(rs.getString("id")+" "+rs.getString("login")+" "+ rs.getString("password"));
			}
		} catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
		
	
	}

}
