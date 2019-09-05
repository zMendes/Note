package br.edu.insper;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/lista")	
public class Lista extends HttpServlet {
	
	
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		DAO dao = new DAO();
		
		List<User> users = dao.getLista();
		PrintWriter out = response.getWriter();
		out.println("<html><body><table border='1'");
		out.println("<tr><td>ID</td><td>Username</td>" + 
		"<td> Senha</td><td>Notaaa</td></tr> ");
		
		for (User user: users) {
			out.println("<tr><td>" + user.getId() + "</td>");
			out.println("<td>" + user.getUsername() + "</td>");
			out.println("<td>" + user.getPassword()+ "</td>");
			out.println("<td>" + user.getNote()+ "</td></tr>");
		}
		out.println("</table></body></html");
		
		dao.close();
	}
		
}