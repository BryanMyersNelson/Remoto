package fifa;

public class Liga {
	private int cod_liga;
	private String nombre;
	private String pais;

	public Liga() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Liga(int cod_liga, String nombre, String pais) {
		super();
		this.cod_liga = cod_liga;
		this.nombre = nombre;
		this.pais = pais;
	}

	public int getCod_jugador() {
		return cod_liga;
	}

	public void setCod_jugador(int cod_liga) {
		this.cod_liga = cod_liga;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Liga [cod_liga=" + cod_liga + ", nombre=" + nombre + ", pais=" + pais + "]";
	}
}
