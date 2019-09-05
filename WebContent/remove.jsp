<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Remove nota</title>
</head>
<body>
<%@ page import="java.util.*,br.edu.insper.*" %>
<% 
int noteId=  Integer.valueOf(request.getParameter("removeId"));
String username = session.getAttribute("username").toString();
String password = session.getAttribute("senha").toString();
DAO dao = new DAO();
System.out.println(noteId);
dao.removeNota(noteId);
%>Sua nota foi removida com sucesso. <br>
<a href= "yourNotes.jsp"><--Voltar</a>
<% 
session.setAttribute("username", username);
session.setAttribute("senha", password);
%>
</body>
</html>