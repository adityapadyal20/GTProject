

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletUpdate
 */
public class ServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
      /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	  public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException

	    {
	        doPost(req,res);

	     }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		
		try {
			Class.forName("org.postgresql.Driver");
		 // loads driver
		Connection c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/users","postgres","root");
		PreparedStatement ps = c.prepareStatement("update userdetail set password = ? where username = ?");
		ps.setString(2, Username);
		ps.setString(1, Password);
		
		int i=ps.executeUpdate();
		if(i>0)
		{
			response.sendRedirect("Success.html");
		}
		else
		{
			response.sendRedirect("error.html");
		}
//		while(rs.next())
//		{
//			response.sendRedirect("Success.html");
//			return;
//		}
//		response.sendRedirect("error.html");
//		return;
		
		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
