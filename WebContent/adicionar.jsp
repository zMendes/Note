<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <%String name = session.getAttribute("username").toString(); %>
    <%String password = session.getAttribute("senha").toString(); %>
    <%String userId = session.getAttribute("userId").toString(); %>
<title>Adicionar nota</title>
</head>
<body>

	<form action ="adicionar.jsp" method='post'>
	Notas: <input value='Digite aqui'type="text" name='notes' /><br>
	<input type="submit" value='Submit'/>
	</form>
<%@ page import="java.util.*,br.edu.insper.*" %>	
<% if (request.getParameter("notes")!=null){
	DAO dao = new DAO();
	User user = new User();
	user.setUsername(name);
	System.out.println(request.getParameter("notes"));
	user.setNote(request.getParameter("notes"));
	user.setId(Integer.valueOf(userId));
	dao.adicionaNota(user);
	dao.close();
	}

	session.setAttribute("senha", password);
	session.setAttribute("username",name);
%>

Clique <a href="yourNotes.jsp">aqui</a> para voltar para suas notas.
</body>
</html>