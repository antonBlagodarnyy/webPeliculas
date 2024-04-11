package usuarios;

import java.util.ArrayList;
import java.util.Objects;

import audiovisual.Audiovisual;

public abstract class Persona {
	//Atributos
	private String nombre;
	private String contrasenia;
	
	private ArrayList<Audiovisual> favoritos=new ArrayList<Audiovisual>();
	private ArrayList<Audiovisual> historialReproducciones=new ArrayList<Audiovisual>();
	private ArrayList<Double> historialValoraciones=new ArrayList<Double>();
		
	//Metodos
	public Persona(String nombre, String contrasenia) {
		super();
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}
public Persona() {
	
}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public ArrayList<Audiovisual> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(ArrayList<Audiovisual> favoritos) {
		this.favoritos = favoritos;
	}

	public ArrayList<Audiovisual> getHistorialReproducciones() {
		return historialReproducciones;
	}

	public void setHistorialReproducciones(ArrayList<Audiovisual> historialReproducciones) {
		this.historialReproducciones = historialReproducciones;
	}

	public ArrayList<Double> getHistorialValoraciones() {
		return historialValoraciones;
	}

	public void setHistorialValoraciones(ArrayList<Double> historialValoraciones) {
		this.historialValoraciones = historialValoraciones;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(contrasenia, other.contrasenia) && Objects.equals(favoritos, other.favoritos)
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", contrasenia=" + contrasenia + ", favoritos=" + favoritos
				+ ", historialReproducciones=" + historialReproducciones + ", historialValoraciones="
				+ historialValoraciones + "]";
	}
	
			
}