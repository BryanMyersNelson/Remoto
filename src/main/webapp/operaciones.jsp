<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="fifa.*"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">

<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<title>Proyecto Web FIFA23</title>
</head>

<body class="is-preload">
	<div id="page-wrapper">


		<%
		BDController controladorBD = new BDController();
		String origen = request.getParameter("tipo");

		String nombre = "";
		String pierna = "";
		int cod_equipo = 0;
		String pais = "";
		int altura = 0;
		int codigoJ = 0;
		int cod_jugador = 0;
		int cod_liga = 0;
		String modificar = "";
		String modi = "";
		ArrayList<String> preguntas = new ArrayList<String>();
		preguntas.add("Nombre del equipo");
		preguntas.add("Liga del equipo");
		preguntas.add("Nombre del equipo");
		preguntas.add("Nombre del equipo");
		preguntas.add("Nombre del equipo");

		if (origen.equalsIgnoreCase("AltaJugador")) {

			nombre = request.getParameter("nombre");
			cod_equipo = Integer.parseInt(request.getParameter("cod"));
			pais = request.getParameter("pais");
			altura = Integer.parseInt(request.getParameter("altura"));
			pierna = request.getParameter("pierna");

			boolean exito = controladorBD.darDeAltaJugador(nombre, cod_equipo, pierna, altura, pais);

			if (exito) {
				response.sendRedirect("altajugador.jsp?mensaje=Alta realizada exitosamente");
			} else {
				response.sendRedirect("altajugador.jsp?mensaje=Alta no realizada, algo fallo en el proceso");

			}
		}

		if (origen.equalsIgnoreCase("BajaJugador")) {
			cod_jugador = Integer.parseInt(request.getParameter("cod_baja"));
			boolean exito = controladorBD.darDeBajaJugador(cod_jugador);

			if (exito) {
				response.sendRedirect("bajaJugador.jsp?mensaje=Baja realizada exitosamente");
			} else {
				response.sendRedirect("bajaJugador.jsp?mensaje=Baja no realizada, algo fallo en el proceso");
			}
		}

		if (origen.equalsIgnoreCase("BajaEquipo")) {
			cod_equipo = Integer.parseInt(request.getParameter("cod_equipo"));
			boolean exite = controladorBD.existeEquipo(cod_equipo);

			if (exite) {
				boolean exito = controladorBD.darDeBajaEquipo(cod_equipo);
				if (exito) {
			response.sendRedirect("bajaequipo.jsp?mensaje=Baja realizada exitosamente");

				} else {
			response.sendRedirect("bajaequipo.jsp?mensaje=No Baja realizada exitosamente");

				}
			} else {
				response.sendRedirect("bajaequipo.jsp?mensaje=No existe");
			}
		}

		if (origen.equalsIgnoreCase("BajaLiga")) {
			cod_liga = Integer.parseInt(request.getParameter("cod_liga"));
			boolean exite = controladorBD.existeLiga(cod_liga);

			if (exite) {
				boolean exito = controladorBD.darDeBajaLiga(cod_liga);
				if (exito) {
			response.sendRedirect("bajaliga.jsp?mensaje=Baja realizada exitosamente");

				} else {
			response.sendRedirect("bajaliga.jsp?mensaje=No Baja realizada exitosamente");

				}
			} else {
				response.sendRedirect("bajaliga.jsp?mensaje=No existe");
			}
		}

		if (origen.equalsIgnoreCase("AltaEquipo")) {
			cod_liga = Integer.parseInt(request.getParameter("cod_liga"));
			nombre = request.getParameter("nombre");

			boolean exite2 = controladorBD.existeLiga(cod_liga);

			if (exite2 == true) {
				boolean exito = controladorBD.darDeAltaEquipos(nombre, cod_liga);

				if (exito) {
			response.sendRedirect("altaequipo.jsp?mensaje=Alta realizada exitosamente");

				} else {
			response.sendRedirect("altaequipo.jsp?mensaje=No Alta realizada");
				}

			} else {
				response.sendRedirect("altaequipo.jsp?mensaje=No estan bien los datos");

			}
		}

		if (origen.equalsIgnoreCase("AltaLiga")) {
			nombre = request.getParameter("nombre");
			pais = request.getParameter("pais");

			
			
			boolean exito = controladorBD.darDeAltaEquipos(nombre, cod_liga);

			if (exito) {
				response.sendRedirect("altaequipo.jsp?mensaje=Alta realizada exitosamente");

			} else {
				response.sendRedirect("altaequipo.jsp?mensaje=No Alta realizada");
			}
		}
	
		

		
		if (origen.equalsIgnoreCase("modificarJugador")) {
			 codigoJ = Integer.parseInt(request.getParameter("CodModificarJugador"));
	            boolean exitoModificar = controladorBD.existeJugador(codigoJ);
	            if (exitoModificar) {
	                response.sendRedirect("modificar2Jugador.jsp");
	            } else {
	                response.sendRedirect("modificar1Jugador.jsp?mensaje=Modificación no realizada, no existe el jugador");
	            }

	    
			}
		
		if (origen.equalsIgnoreCase("modificacionJugador")) {
			cod_jugador = Integer.parseInt(request.getParameter("cod"));
			nombre = request.getParameter("nombre");
			cod_equipo = Integer.parseInt(request.getParameter("cod_equipo"));
			pais = request.getParameter("pais");
			altura = Integer.parseInt(request.getParameter("altura"));
			pierna = request.getParameter("pierna");
			
			boolean exito = controladorBD.modificarJugador(cod_jugador, nombre, cod_equipo, pierna, altura, pais);

			if (exito) {
				response.sendRedirect("modificar1Jugador.jsp?mensaje=Modificacion realizada exitosamente");
			} else {
				response.sendRedirect("modificar1Jugador.jsp?mensaje=Modificacion no realizada, algo fallo en el proceso");
			}
		}
		%>



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
						<li><a href="ligas.jsp">FIFA 23 Ligas</a></li>
						<li><a href="equipos.jsp">FIFA 23 Equipos</a></li>
						<li><a href="jugadores.jsp">FIFA 23 Jugadores</a></li>
					</ul></li>
				<li><a href="#">Operaciones</a>
					<ul>
						<li><a href="#">Jugadores</a>
							<ul>
								<li><a href="operaciones.jsp?tipo=altajugador">Alta
										Jugador</a></li>
								<li><a href="#">Baja Jugador</a></li>
								<li><a href="#">Modificar Jugador</a></li>
							</ul></li>
						<li><a href="#">Equipos</a>
							<ul>
								<li><a href="#">Alta Equipo</a></li>
								<li><a href="#">Baja Equipo</a></li>
								<li><a href="#">Modificar Equipo</a></li>
							</ul></li>
						<li><a href="#">Ligas</a>
							<ul>
								<li><a href="#">Alta Liga</a></li>
								<li><a href="#">Baja Liga</a></li>
								<li><a href="#">Modificar Liga</a></li>
							</ul></li>
						<li><a href="#">Cartas</a>
							<ul>
								<li><a href="#">Alta Carta</a></li>
								<li><a href="#">Baja Carta</a></li>
								<li><a href="#">Modificar Carta</a></li>
							</ul></li>
					</ul></li>
				<li><a href="#">Simulador</a>
					<ul>
						<li><a href="#">Construye tu Equipo</a></li>
					</ul></li>
			</ul>
			</nav>
		</div>
		<!-- Main -->
		<section class="wrapper style1" style="height:500px;">
		<div class="container">
			<div id="content">

				<!-- Content -->

				<article> <section class="col-6 col-12-narrower">
				<h3></h3>

				</section> </article>
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
						<li><a href="jugadores.asp">Listado de Jugadores</a></li>
						<li><a href="equipos.asp">Listado de Equipos</a></li>
						<li><a href="ligas.asp">Listado de Ligas</a></li>

					</ul>
					</section>
					<section class="col-3 col-6-narrower col-12-mobilep">
					<h3>Más Opciones</h3>
					<ul class="links">
						<li><a href="altaJugador.jsp">Alta Jugador</a></li>
						<li><a href="altaEquipo.jsp">Alta Equipo</a></li>
						<li><a href="altaLiga.jsp">Alta Liga</a></li>
						<li><a href="altaCarta.jsp">Alta Carta</a></li>
						<li><a href="modifiJugador.jsp">Modificar Jugador</a></li>
						<li><a href="modifiEquipo.jsp">Modificar Equipo</a></li>
						<li><a href="modifiLiga.jsp">Modificar Liga</a></li>
						<li><a href="modifiCarta.jsp">Modificar Carta</a></li>
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
	<script src="assets/js/index.js"></script>
	<script src="assets/js/dropdown.js"></script>

</body>

</html>