

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Name = request.getParameter("fullname");
		String Email = request.getParameter("email");
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("Hello" + Name);
		System.out.println(Name);
		System.out.println(Email);
		System.out.println(Username);
		System.out.println(Password);
		
		try {
			Class.forName("org.postgresql.Driver");
			try (Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/users", "postgres",
					"root")) {

				if (conn != null) {
					System.out.println("Connected to the database!");
					Statement stmnt = null;
					stmnt = conn.createStatement();
		
					java.sql.PreparedStatement pst = null;
					String sql = "INSERT INTO public.\"userdetail\"(\"fullname\",\"email\",\"username\",\"password\") VALUES (?, ?, ?, ?)";
					pst = conn.prepareStatement(sql);
					pst.setString(1,Name);
					pst.setString(2,Email);
					pst.setString(3,Username);
					pst.setString(4,Password);
					ResultSet rs = pst.executeQuery();
										
//					 ResultSet rs1= stmnt.executeQuery("select * from public.\"userdetail\"");
//					 while(rs1.next())
//					 {
//						 System.out.println(rs1.getString(1));
//						 System.out.println(rs1.getString(2));
//						 System.out.println(rs1.getString(3));
//						 System.out.println(rs1.getString(4));
//
//						 out.print(rs1.getString(1));
//						 out.print("<br>");
//						 out.print(rs1.getString(2));
//						 out.print("<br>");
//						 out.print(rs1.getString(3));
//						 out.print("<br>");
//						 out.print(rs1.getString(4));
//						 out.print("<br>");
	//				 }
					 
				} else {
					System.out.println("Failed to make connection!");
				}

			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
