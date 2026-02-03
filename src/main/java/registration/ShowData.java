package registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//purpose - show json data
// end point /show
public class ShowData  extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		//init - param
		
		ServletConfig src = super.getServletConfig();
		String url = src.getInitParameter("url");
		String username = src.getInitParameter("uname");
		String password = src.getInitParameter("pass");
		StringBuffer sb = new StringBuffer();
		boolean comma = true;
		try {
			Connection con = Db.connect(url, username, password);
			String query = "select * from student;";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rst = pst.executeQuery();
			
			sb.append("[");
			while(rst.next()) {
				
				//comma logic
				if(!comma) {
					sb.append(",");
				}
				
				sb.append("  {");
				sb.append("\"name\":"+"\""+rst.getString("name")+"\",");
				sb.append("\"email\":"+"\""+rst.getString("email")+"\",");
				sb.append("\"mobile\":"+"\""+rst.getString("mobile")+"\",");
				sb.append("\"username\":"+"\""+rst.getString("username")+"\",");
				sb.append("\"password\":"+"\""+rst.getString("password")+"\"");
				sb.append("  }");
				
				//comma logic
				comma = false;
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		sb.append("]");
		
		String json = sb.toString();
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		
		out.println(json);
	}
}
