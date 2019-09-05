<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*,br.edu.insper.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Suas notas</title>
</head>
<body>
	<% String username= request.getParameter("username"); 
	String password= request.getParameter("senha");
	if (username == null){
		username =session.getAttribute("username").toString();
		password = session.getAttribute("senha").toString();	
	}
	Boolean passwordMatch = false;
	DAO dao = new DAO(); 
	List<User> users = dao.getLista();


	String nickname = "";
	for (User user: users){
		if (user.getUsername().equals(username)){
			if (user.getPassword().equals(password)){
				passwordMatch = true;
				nickname = user.getNickname();
				session.setAttribute("userId", user.getId());
			}
		}
	}
	
	if (passwordMatch){
		List<User> notes = dao.getNotes(username);
		
		if (!notes.isEmpty()){ %>
	Olá <%=nickname %>, seja bem vindx. <br>
	Suas notas:
	<table border='2'>
	<tr>
		<td><b>Nota</b></td>
		<td><b>Editar</b></td>
		<td><b>Remover</b></td>
	</tr>
	<% 
	for (User user : notes){ %>	
	<tr>
	<td><%= user.getNote() %></td>
	<td>
	<form action ="edit.jsp" method='post'>
	<input type="hidden" value=<%=user.getNoteId()%> name="noteId">
	<input type="submit" value="                 "></form></td>
	<td>
	<form action="remove.jsp" method='post'>
	<input type="hidden" value=<%=user.getNoteId()%> name="removeId">
	<input type="submit" value="                 ">	</form>	</td>
	</tr>

<%}
	session.setAttribute("username", username);
	session.setAttribute("senha", password);
	}
	else{ %>
		<p> Você não tem nenhuma nota :(  .</p>
	<% } %>
	</table>
Clique <a href="adicionar.jsp">aqui</a> para adicionar uma nota.

<%	} 	
	else { %>
	Usuário ou senha errado. Clique <a href='index.html'>aqui</a> para voltar. 
<%	} %>	
<br>	
<a href='index.html'>Logout</a> 
    
</body>
</html>