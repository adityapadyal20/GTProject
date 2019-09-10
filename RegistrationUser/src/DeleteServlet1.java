

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Username=request.getParameter("username");
		
		try {
			Class.forName("org.postgresql.Driver");
		 // loads driver
		Connection c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/users","postgres","root");
		
		PreparedStatement ps = c.prepareStatement("delete from userdetail where username=?");
		ps.setString(1, Username);
		
		int i = ps.executeUpdate();
		if(i>0)
		{
			response.sendRedirect("Success.html");
		}
		else
		{
			response.sendRedirect("error.html");
		}
		
		}catch (Exception e)
		{
			System.out.println("connction faild");
		}
		
	}
}