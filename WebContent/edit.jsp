<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar nota</title>
</head>
<% if (request.getParameter("notes")==null){%>
<% int noteId = Integer.valueOf(request.getParameter("noteId").toString()); 
System.out.println(noteId);%>
<% int userId =Integer.parseInt(session.getAttribute("userId").toString()); %>
<% String username = session.getAttribute("username").toString();
String password = session.getAttribute("senha").toString();%>
<%@ page import="java.util.*,br.edu.insper.*" %>

<%
String text = "Não foi possível carregar sua nota";
DAO dao = new DAO();
List<User> notes = dao.getNotes(username);
for (User note:notes){
	if (note.getNoteId() == noteId){
		text = note.getNote();
		System.out.println(text);	
	}
}%>

<body>
<form action ="edit.jsp" method='post'>
 Notas: <input  value="<%=text %>"  type="text" name='notes' />

 <br> 
<input type="submit" value='Submit'/>
</form>


<% dao.close();
session.setAttribute("noteId", noteId);
session.setAttribute("username", username);%>
	 	
<%} else{
	int noteId = Integer.valueOf(session.getAttribute("noteId").toString());
	String username = session.getAttribute("username").toString();
	DAO dao = new DAO();
	User user = new User();;
	System.out.println(request.getParameter("notes"));
	dao.editaNota(request.getParameter("notes"), noteId);
	
	String password = "";
	List<User> users = dao.getLista();
	for (User user2 :users){
		if (user2.getUsername().equals(username)){
			password = user2.getPassword();
			System.out.println(password);
		}
		
	}
	dao.close();
	session.setAttribute("username",username);
	session.setAttribute("senha", password);
	
%>	
Nota editada com sucesso. <br>
<%} %>
	



Clique <a href="yourNotes.jsp">aqui</a> para voltar para suas notas.
</body>
</html>