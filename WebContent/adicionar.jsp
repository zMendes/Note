<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,br.edu.insper.*" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adicionar nota</title>
</head>
<%String name = session.getAttribute("username").toString();
String password = session.getAttribute("senha").toString();
String userId = session.getAttribute("userId").toString(); %>
<body>
	
	<form action ="adicionar.jsp" method='post'>
	Notas: <input value='Digite aqui'type="text" name='notes' /><br>
	<input type="submit" value='Submit'/>
	</form>
	
<%if (request.getParameter("notes")!=null){
	DAO dao = new DAO();
	User user = new User();
	user.setUsername(name);
	user.setNote(request.getParameter("notes"));
	user.setId(Integer.valueOf(userId));
	dao.adicionaNota(user);
	dao.close();
	}

	session.setAttribute("senha", password);
	session.setAttribute("username",name);%>

Clique <a href="yourNotes.jsp">aqui</a> para voltar para suas notas.
</body>
</html>