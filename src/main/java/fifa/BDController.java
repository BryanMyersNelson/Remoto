package fifa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BDController {

	private Connection conexion;

	public BDController() {
		super();
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/fifa23", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error en Constructor BDController: " + e.getMessage());
		}
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public ArrayList<Equipo> todosEquipos() {
		ArrayList<Equipo> equipos = new ArrayList<Equipo>();
		String sql = "SELECT * FROM equipos";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while (rs.next()) {
				Equipo equipo = new Equipo(rs.getInt("cod_equipo"), rs.getString("nombre"), rs.getInt("cod_liga"));
				equipos.add(equipo);
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipos;
	}

	public ArrayList<Jugador> todosJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        String sql = "SELECT * FROM jugadores";
        try {
            Statement myStatement = this.conexion.createStatement();
            ResultSet rs = myStatement.executeQuery(sql);
            while (rs.next()) {
                Jugador jugador = new Jugador(rs.getInt("cod_jugador"), rs.getString("nombre"), rs.getInt("cod_equipo"),
                        rs.getString("pierna"), rs.getInt("altura"), rs.getString("pais"));
                jugadores.add(jugador);
            }
            myStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jugadores;
    }

	public ArrayList<Liga> todasLigas() {
		ArrayList<Liga> ligas = new ArrayList<Liga>();
		String sql = "SELECT * FROM ligas";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while (rs.next()) {
				Liga liga = new Liga(rs.getInt("cod_liga"), rs.getString("nombre"), rs.getString("pais"));
				ligas.add(liga);
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ligas;
	}

	public ArrayList<Carta> todasCartas() {
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		String sql = "SELECT * FROM cartas";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			while (rs.next()) {
				Carta carta = new Carta(rs.getString("nombre"), rs.getInt("cod_jugador"), rs.getInt("rat"),
						rs.getString("pos"), rs.getInt("precio"), rs.getInt("pac"), rs.getInt("sho"), rs.getInt("pas"),
						rs.getInt("dri"), rs.getInt("def"), rs.getInt("phy"), rs.getInt("pierna_mala"),
						rs.getInt("filigranas")); 
				cartas.add(carta);
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartas;
	}

	public Jugador dameJugador(int cod) {
		Jugador jugador = new Jugador();
		String sql = "SELECT * FROM jugadores WHERE cod_jugador = '" + cod + "'";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			if (rs.next()) {
				jugador = new Jugador(rs.getInt("cod_jugador"), rs.getString("nombre"), rs.getInt("cod_equipo"),
						rs.getString("pierna"), rs.getInt("altura"), rs.getString("pais"));
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jugador;
	}

	public Equipo dameEquipo(int cod) {
		Equipo equipo = new Equipo();
		String sql = "SELECT * FROM equipos WHERE cod_equipo = '" + cod + "'";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			if (rs.next()) {
				equipo = new Equipo(rs.getInt("cod_equipo"), rs.getString("nombre"), rs.getInt("cod_liga"));
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipo;
	}

	public Liga dameLiga(int cod) {
		Liga liga = new Liga();
		String sql = "SELECT * FROM ligas WHERE cod_liga = '" + cod + "'";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			if (rs.next()) {
				liga = new Liga(rs.getInt("cod_liga"), rs.getString("nombre"), rs.getString("pais"));
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liga;
	}

	public Carta dameCarta(String nombre, int codigo) {
        Carta carta = new Carta();
        String sql = "SELECT * FROM cartas WHERE cod_jugador = '" + codigo + "' and nombre LIKE '" + nombre + "'";
        try {
            Statement myStatement = this.conexion.createStatement();
            ResultSet rs = myStatement.executeQuery(sql);
            while (rs.next()) {
                carta = new Carta(rs.getString("nombre"), rs.getInt("cod_jugador"), rs.getInt("rat"),
                        rs.getString("pos"), rs.getInt("precio"), rs.getInt("pac"), rs.getInt("sho"), rs.getInt("pas"),
                        rs.getInt("dri"), rs.getInt("def"), rs.getInt("phy"), rs.getInt("pierna_mala"),
                        rs.getInt("filigranas"));
            }
            myStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carta;
    }
	
	public boolean existeEquipo(int codEquipo) {
	    boolean existe = false;
	    String sql = "SELECT COUNT(*) AS count FROM equipos WHERE cod_equipo = ?";
	    try {
	        PreparedStatement statement = this.conexion.prepareStatement(sql);
	        statement.setInt(1, codEquipo);
	        ResultSet rs = statement.executeQuery();
	        if(rs.next()) {
	            int count = rs.getInt("count");
	            existe = count > 0;
	        }
	        statement.close();
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return existe;
	}

	public boolean existeJugador(int codJugador) {
	    boolean existe = false;
	    String sql = "SELECT COUNT(*) AS count FROM jugadores WHERE cod_jugador = ?";
	    try {
	        PreparedStatement statement = this.conexion.prepareStatement(sql);
	        statement.setInt(1, codJugador);
	        ResultSet rs = statement.executeQuery();
	        if(rs.next()) {
	            int count = rs.getInt("count");
	            existe = count > 0;
	        }
	        statement.close();
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return existe;
	}

	public boolean existeLiga(int codLiga) {
	    boolean existe = false;
	    String sql = "SELECT COUNT(*) AS count FROM ligas WHERE cod_liga = ?";
	    try {
	        PreparedStatement statement = this.conexion.prepareStatement(sql);
	        statement.setInt(1, codLiga);
	        ResultSet rs = statement.executeQuery();
	        if(rs.next()) {
	            int count = rs.getInt("count");
	            existe = count > 0;
	        }
	        statement.close();
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return existe;
	}

	public boolean existeCarta(int codJugador) {
	    boolean existe = false;
	    String sql = "SELECT COUNT(*) AS count FROM cartas WHERE cod_jugador = ?";
	    try {
	        PreparedStatement statement = this.conexion.prepareStatement(sql);
	        statement.setInt(1, codJugador);
	        ResultSet rs = statement.executeQuery();
	        if(rs.next()) {
	            int count = rs.getInt("count");
	            existe = count > 0;
	        }
	        statement.close();
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return existe;
	}

	public String dameColor(int puntos) {
		String color="";
		if(puntos<49 && puntos>0) {
			color ="#F44336";
		}else if(puntos<59){
			color ="#FB8C00";
		}else if (puntos<69) {
			color ="#E9BC0B";
		}else if(puntos<89) {
			color ="#4CAF50";
		}else if (puntos<=99) {
			color="#317533";
		}
		
		return color;
	}
	
	
	public Liga dameLigaEquipos(String pais ) {
		Liga liga = new Liga();
		String sql = "SELECT * FROM ligas WHERE pais = '" + pais + "'";
		try {
			Statement myStatement = this.conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			if (rs.next()) {
				liga = new Liga(rs.getInt("cod_jugador"), rs.getString("nombre"), rs.getString("pais"));
			}
			myStatement.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liga;
	}
	
	public ArrayList<Equipo> dameEquiposSegunLiga(int cod) {
        ArrayList<Equipo> equipos = new ArrayList<Equipo>();

        Equipo equipo = new Equipo();
        String sql = "SELECT * FROM equipos WHERE cod_liga = '" + cod + "'";
        try {
            Statement myStatement = this.conexion.createStatement();
            ResultSet rs = myStatement.executeQuery(sql);
            while (rs.next()) {
                equipo = new Equipo(rs.getInt("cod_equipo"), rs.getString("nombre"), rs.getInt("cod_liga"));
                equipos.add(equipo);
            }
            myStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    public ArrayList<Jugador> dameJugadoresSegunEquipo(int cod) {
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        String sql = "SELECT * FROM jugadores WHERE cod_equipo = '" + cod + "'";
        try {
            Statement myStatement = this.conexion.createStatement();
            ResultSet rs = myStatement.executeQuery(sql);
            while (rs.next()) {
                Jugador jugador = new Jugador(rs.getInt("cod_jugador"), rs.getString("nombre"), rs.getInt("cod_equipo"),
                        rs.getString("pierna"), rs.getInt("altura"), rs.getString("pais"));
                jugadores.add(jugador);
            }
            myStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jugadores;
    }

    public Liga dameLigaSegunEquipo(int cod) {
        Liga liga = new Liga();
        String sql = "SELECT * FROM ligas WHERE cod_liga = '" + cod + "'";
        try {
            Statement myStatement = this.conexion.createStatement();
            ResultSet rs = myStatement.executeQuery(sql);
            if (rs.next()) {
                liga = new Liga(rs.getInt("cod_liga"), rs.getString("nombre"), rs.getString("pais"));
            }
            myStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liga;
    }

    public String dameNombreLiga(int cod) {
        String nombreLiga = "";
        String sql = "SELECT nombre FROM ligas WHERE cod_liga = '" + cod + "'";
        try {
            Statement myStatement = this.conexion.createStatement();
            ResultSet rs = myStatement.executeQuery(sql);
            if (rs.next()) {
                nombreLiga = rs.getString("nombre");
            }
            myStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreLiga;
    }

    public String dameNombreEquipo(int cod) {
        String nombreEquipo = "";
        String sql = "SELECT * FROM equipos WHERE cod_equipo = '" + cod + "'";
        try {
            Statement myStatement = this.conexion.createStatement();
            ResultSet rs = myStatement.executeQuery(sql);
            if (rs.next()) {
                nombreEquipo = rs.getString("nombre");
            }
            myStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombreEquipo;
    }
    
    public ArrayList<Carta> dameCartaTodas(int codigo) {
		ArrayList<Carta> cartas = new ArrayList<Carta>();
		Carta carta = new Carta();
        String sql = "SELECT * FROM cartas WHERE cod_jugador = '" + codigo + "'";
        try {
            Statement myStatement = this.conexion.createStatement();
            ResultSet rs = myStatement.executeQuery(sql);
            while (rs.next()) {
                carta = new Carta(rs.getString("nombre"), rs.getInt("cod_jugador"), rs.getInt("rat"),
                        rs.getString("pos"), rs.getInt("precio"), rs.getInt("pac"), rs.getInt("sho"), rs.getInt("pas"),
                        rs.getInt("dri"), rs.getInt("def"), rs.getInt("phy"), rs.getInt("pierna_mala"),
                        rs.getInt("filigranas"));
                cartas.add(carta);
            }
            myStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartas;
    }
    
    public boolean darDeAltaJugador(String nombre, int cod_equipo, String pierna, int altura, String pais) {
        String sqlInsert = "INSERT INTO JUGADORES (cod_jugador, Nombre, cod_equipo, pierna, altura, pais) VALUES ('"
                + ultimaPosicionCodJugador() + "','" + nombre + "','" + cod_equipo + "','"
                + pierna + "','" + altura + "','" + pais + "')";
        System.out.println(sqlInsert);
        System.out.println("SQL LANZADA" + sqlInsert);
        try {
            Statement myStatement = this.conexion.createStatement();
            myStatement.executeUpdate(sqlInsert);
            myStatement.close();
            return true; // La inserción se realizó con éxito
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Ocurrió un error durante la inserción
        }
    }
    
    public boolean darDeAltaEquipos(String nombre, int cod_liga) {
        String sqlInsert = "INSERT INTO EQUIPOS (nombre, cod_equipo, cod_liga) VALUES ('"
                + nombre + "','" + ultimaPosicionCodEquipo() + "','"
                + cod_liga + "')";
        System.out.println(sqlInsert);
        System.out.println("SQL LANZADA" + sqlInsert);
        try {
            Statement myStatement = this.conexion.createStatement();
            myStatement.executeUpdate(sqlInsert);
            myStatement.close();
            return true; // La inserción se realizó con éxito
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Ocurrió un error durante la inserción
        }
    }

    public boolean darDeBajaJugador(int cod) {
        String sql = "DELETE FROM JUGADORES WHERE cod_jugador LIKE '" + String.valueOf(cod) + "'";
        try {
            Statement myStatement = this.conexion.createStatement();
            myStatement.executeUpdate(sql);
            myStatement.close();
            return true; // La inserción se realizó con éxito
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Ocurrió un error durante la inserción
        }
    }

    public boolean darDeBajaEquipo(int cod) {
        String sql = "DELETE FROM EQUIPOS WHERE cod_equipo LIKE '" + String.valueOf(cod) + "'";
        try {
            Statement myStatement = this.conexion.createStatement();
            myStatement.executeUpdate(sql);
            myStatement.close();
            return true; // La inserción se realizó con éxito
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Ocurrió un error durante la inserción
        }
    }
    
    public boolean darDeBajaLiga(int cod) {
        String sql = "DELETE FROM LIGAS WHERE cod_liga LIKE '" + String.valueOf(cod) + "'";
        try {
            Statement myStatement = this.conexion.createStatement();
            myStatement.executeUpdate(sql);
            myStatement.close();
            return true; // La inserción se realizó con éxito
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Ocurrió un error durante la inserción
        }
    }

    public int ultimaPosicionCodJugador() {
        int pos = 0;
        String sql = "SELECT cod_jugador FROM jugadores ORDER BY cod_jugador DESC LIMIT 1;";
        try {
            Statement myStatement = this.conexion.createStatement();
            ResultSet rs = myStatement.executeQuery(sql);
            if (rs.next()) {
                pos = rs.getInt("cod_jugador");
            }
            myStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pos = pos + 1;
        System.out.println(pos);
        return pos;
    }
    
    public int ultimaPosicionCodLiga() {
        int pos = 0;
        String sql = "SELECT cod_liga FROM ligas ORDER BY cod_liga DESC LIMIT 1;";
        try {
            Statement myStatement = this.conexion.createStatement();
            ResultSet rs = myStatement.executeQuery(sql);
            if (rs.next()) {
                pos = rs.getInt("cod_liga");
            }
            myStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pos = pos + 1;
        System.out.println(pos);
        return pos;
    }
    
    public int ultimaPosicionCodEquipo() {
        int pos = 0;
        String sql = "SELECT cod_equipo FROM equipos ORDER BY cod_equipo DESC LIMIT 1;";
        try {
            Statement myStatement = this.conexion.createStatement();
            ResultSet rs = myStatement.executeQuery(sql);
            if (rs.next()) {
                pos = rs.getInt("cod_liga");
            }
            myStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pos = pos + 1;
        System.out.println(pos);
        return pos;
    }
    
    
    
   
    public boolean darDeAltaLiga(String nombre_liga, String pais_liga) {
        String sqlInsert = "INSERT INTO LIGAS (cod_liga, nombre, pais) VALUES ('" + ultimaPosicionCodLiga() + "','" + nombre_liga
                + "','" + pais_liga + "')";
        System.out.println(sqlInsert);
        System.out.println("SQL LANZADA" + sqlInsert);
        try {
            Statement myStatement = this.conexion.createStatement();
            myStatement.executeUpdate(sqlInsert);
            myStatement.close();
            return true; // La inserción se realizó con éxito
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Ocurrió un error durante la inserción
        }
    }
    
    public boolean modificarJugador(int cod_jugador, String nombre, int cod_equipo, String pierna, int altura, String pais) {
        String sqlUpdate = "UPDATE JUGADORES SET Nombre = '" + nombre + "', cod_equipo = '" + cod_equipo + "', pierna = '" + pierna +
                "', altura = '" + altura + "', pais = '" + pais + "' WHERE cod_jugador = '" + cod_jugador + "'";
        System.out.println(sqlUpdate);
        System.out.println("SQL LANZADA: " + sqlUpdate);
        try {
            Statement myStatement = this.conexion.createStatement();
            myStatement.executeUpdate(sqlUpdate);
            myStatement.close();
            return true; // La actualización se realizó con éxito
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Ocurrió un error durante la actualización
        }
    }

    
	

}