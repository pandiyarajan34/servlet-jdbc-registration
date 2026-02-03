package registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//utility class - helper class always static
public class Db {
	//Deva
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//driver object create aagidum
			System.out.println("Driver loaded");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

	
	public static Connection connect(String url, String uname, String pass) throws SQLException {
		//url,uname,password
				return DriverManager.getConnection(url,uname,pass);
				//connection object return pannum
	}
}
