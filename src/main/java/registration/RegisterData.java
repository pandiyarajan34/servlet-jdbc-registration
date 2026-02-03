package registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//end point - register
public class RegisterData extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//url - data fetch for store values in database
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String username = req.getParameter("uname");
		String password = req.getParameter("pass");
		
		//init-param
		ServletConfig src = super.getServletConfig();
		String url = src.getInitParameter("url");
		String uname = src.getInitParameter("uname");
		String pass = src.getInitParameter("pass");
		
		//Db class use pannaporom
		try {
			//Connection con = Db.connect(url, uname, pass);
			Connection con = Db.connect(url, uname, pass);//db clas sload - static execute - driver object register entry
			
			String query = "insert into student values(?,?,?,?,?);";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, mobile);
			pst.setString(4, username);
			pst.setString(5, password);
			
			int rows = pst.executeUpdate();//dml - update,insert,delete
			System.out.println("Rows Are Affected "+rows);
			
			res.sendRedirect("reg-success.html");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
