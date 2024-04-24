<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "fifa.*"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%  
BDController controladorBD = new BDController();
ArrayList<Carta> cartas = new ArrayList<Carta>();
%>
<% for(Carta e : controladorBD.todasCartas()){%>
	<h3><%=e.getPrecio() %></h3>
	
<%}%>
</body>
</html>