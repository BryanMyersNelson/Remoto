<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="fifa.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<title>Proyecto Web FIFA23</title>


</head>

<%
BDController controladorBD = new BDController();
%>

<body class="is-preload">
	<div id="page-wrapper">
		<!-- Header -->
		<div id="header">

			<!-- Logo -->
			<h1>
				<a href="index.jsp" id="logo">FIFA23</a></a>
			</h1>

			<!-- Nav -->
			<nav id="nav">
			<ul>
				<li class="current"><a href="index.jsp">Inicio</a></li>
				<li><a href="#">Listados</a>
					<ul>
						<li><a href="ligas.jsp">Opción 1</a></li>

					</ul></li>
				<li><a href="#">Operaciones</a>
					<ul>
						<li><a href="#">Opción 1</a>
							<ul>
								<li><a href="altajugador.jsp">Opción 1</a></li>

							</ul></li>
						<li><a href="#">Opción 2</a>
							<ul>
								<li><a href="#">Opción 1</a></li>

							</ul></li>
						<li><a href="#">Opción 3</a>
							<ul>
								<li><a href="#">Opción 1</a></li>

							</ul></li>
						<li><a href="#">Opción 4</a>
							<ul>
								<li><a href="#">Opción 1</a></li>

							</ul></li>
					</ul></li>
				<li><a href="#">Simulador</a>
					<ul>
						<li><a href="creador.jsp">Opción 1</a></li>
					</ul></li>
			</ul>
			</nav>

		</div>

		<!-- Main -->
		<section class="wrapper style1">
		<div class="container">
			<div id="content">

				<!-- Content -->

				<article> <header>
				<h2>Ligas</h2>
				</header>

				<div class="table-wrapper">
    <table class="alt">
        <thead>
            <tr>
                <th>Nombre</th>
                <th width='195' colspan="3"></th>
                <th colspan="19">Equipos</th>
            </tr>
        </thead>
        <tbody>
            <% for (Liga ligas : controladorBD.todasLigas()) { %>
            <tr>
                <td width="50px" height="50px" rowspan="2">
                    <a href="liga.jsp?cod_liga=<%=ligas.getCod_jugador() %>"> <img src="images/ligas/<%=ligas.getCod_jugador()%>.png" width="60px" height="60px" title="<%=ligas.getNombre()%>"></a>
                </td>
                <td width="50px" colspan="3" style="padding-left: 15px;">
                    <u><a href="liga.jsp?cod_liga=<%=ligas.getCod_jugador()%>"><b><%=ligas.getNombre()%></b></a></u>
                </td>
                <% for (Equipo equipo : controladorBD.dameEquiposSegunLiga(ligas.getCod_jugador())) { %>
                <td rowspan="2" width='60' style="text-align: center;">
                    <u><a href="equipo.jsp?cod_equipo=<%=equipo.getCod_equipo()%>"><img src="images/equipos/<%=equipo.getCod_equipo()%>.png" width="45px" height="45px" title="<%=equipo.getNombre()%>"></a></u>
                </td>
                <% } %>
            </tr>
            <tr>
                <td width='50' style="text-align: left; padding-left: 14px;">
                    <img src="images/paises/<%=ligas.getPais()%>.png" width="50px" height="35px" title="<%=ligas.getPais()%>">
                </td>
                <td width='205'></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>



				</article>

			</div>
		</div>
		</section>


		<!-- Footer -->
		<div id="footer">
			<div class="container">
				<div class="row">
					<section class="col-3 col-6-narrower col-12-mobilep">
					<h3>Listados</h3>
					<ul class="links">
						<li><a href="jugadores.asp">Listado de Ligas</a></li>


					</ul>
					</section>
					<section class="col-3 col-6-narrower col-12-mobilep">
					<h3>Más Opciones</h3>
					<ul class="links">
						<li><a href="operaciones.jsp?tipo=altaJugador">Opción 1</a></li>

					</ul>
					</section>
					<section class="col-6 col-12-narrower">
					<h3>Solicita Información</h3>
					<form>
						<div class="row gtr-50">
							<div class="col-6 col-12-mobilep">
								<input type="text" name="name" id="name" placeholder="Name" />
							</div>
							<div class="col-6 col-12-mobilep">
								<input type="email" name="email" id="email" placeholder="Email" />
							</div>
							<div class="col-12">
								<textarea name="message" id="message" placeholder="Message"
									rows="5"></textarea>
							</div>
							<div class="col-12">
								<ul class="actions">
									<li><input type="submit" class="button alt"
										value="Enviar Email" /></li>
								</ul>
							</div>
						</div>
					</form>
					</section>
				</div>
			</div>

			<!-- Icons -->
			<ul class="icons">
				<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
				<li><a href="#" class="icon fa-facebook"><span
						class="label">Facebook</span></a></li>
				<li><a href="#" class="icon fa-github"><span class="label">GitHub</span></a></li>
				<li><a href="#" class="icon fa-linkedin"><span
						class="label">LinkedIn</span></a></li>
				<li><a href="#" class="icon fa-google-plus"><span
						class="label">Google+</span></a></li>
			</ul>

			<!-- Copyright -->
			<div class="copyright">
				<ul class="menu">
					<li>&copy; FIFA 23. Todos los derechos reservados</li>
					<li>Design: <a href="https://www.centronelson.org">Centro
							Nelson</a></li>
				</ul>
			</div>

		</div>

	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.dropotron.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>
</body>
</html>