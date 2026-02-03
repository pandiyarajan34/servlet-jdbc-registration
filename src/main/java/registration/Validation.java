package registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//end - point validate
public class Validation extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		String username = req.getParameter("uname");
		String password = req.getParameter("pass");
		
		//init - parameter
		
		ServletConfig src = super.getServletConfig();
		String url = src.getInitParameter("url");
		String uname = src.getInitParameter("uname");
		String pass = src.getInitParameter("pass");
		//boolean validate = false;
		int check = 0;
		try {
			Connection con = Db.connect(url, uname, pass);
			String query = "select * from student where username = ? and password = ?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			
			ResultSet rst = pst.executeQuery();
			
			while(rst.next()) {
				//validate = true;//registered users
				check = 1;
			}
			
			pst.close();
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		//validation
		if (check == 1) {
			res.sendRedirect("home.html");
		}
		
		else {
			res.sendRedirect("invalid-page.html");
		}
		
		
	}
	
}
