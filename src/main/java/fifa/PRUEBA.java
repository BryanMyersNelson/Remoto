<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="fifa.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Jugador</title>
</head>
<body>

<%
    BDController controladorBD = new BDController();
    String mensaje = "";
    if (request.getParameter("mensaje") != null) {
        mensaje = request.getParameter("mensaje");
    }
    
    // Obtener el código del jugador a modificar
    int codJugador = Integer.parseInt(request.getParameter("CodModificarJugador"));
    
    // Obtener el jugador a partir de su código
    Jugador jugador = controladorBD.dameJugador(codJugador);
%>

<h1>Modificar Jugador</h1>

<form action="operaciones.jsp" method="post">
    <input type="hidden" name="cod_jugador" value="<%=jugador.getCod_jugador()%>">
    Nombre: <input type="text" name="nombre" value="<%=jugador.getNombre()%>"><br>
    Pierna buena: <input type="text" name="pierna" value="<%=jugador.getPierna()%>"><br>
    Altura: <input type="text" name="altura" value="<%=jugador.getAltura()%>"><br>
    País: <input type="text" name="pais" value="<%=jugador.getPais()%>"><br>
    <!-- Aquí podrías agregar un campo para la imagen del jugador -->
    <input type="submit" value="Actualizar">
</form>

<%=mensaje%>

</body>
</html>
