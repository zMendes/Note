package br.edu.insper;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/register")
public class Register extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response ) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
			
		out.println("<html><body>");
		out.println("<form  method='post'>");
		out.println("Usuário: <input type='text' name='username'><br>");
		out.println("Apelido: <input type='text' name='nickname'><br>");
		out.println("Senha: <input type='password' name='senha'><br>");
		out.println("Confirme sua senha: <input type='password' name='confirma_senha'><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</form>");
		out.println("<a href='index.html'><--Voltar</a>");
		
		out.println("<body><html>");
		
	}	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response ) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		DAO dao = new DAO();
		
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setNickname(request.getParameter("nickname"));
		if (request.getParameter("senha").equals(request.getParameter("confirma_senha"))) {
			user.setPassword(request.getParameter("senha"));
			dao.adicionaUser(user);
			
			out.println("<html><body>");
			out.print("User adicionado com sucesso. <br>");
			out.print("Clique <a href='index.html'>aqui</a> para voltar à tela de login.");
			out.println("</body></html>");
			
			
		}
		else {
			out.println("<html><body>");
			out.println("As senhas não batem, por favor, tente novamente. Clique <a href='register'>aqui</a> para voltar.");
			
			
		}
		dao.close();
		
		
		

	}
}