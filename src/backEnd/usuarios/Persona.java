package backEnd.usuarios;

import java.util.ArrayList;
import java.util.Objects;

import backEnd.audiovisual.Audiovisual;
import backEnd.dao.DAOPersona;
import frontEnd.MenuUsuario;

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

	public void aniadirFavorito(Audiovisual audiovisual) {
		boolean repetido=false;
		for (Audiovisual favorito : getFavoritos()) {
			if(favorito.getId()==audiovisual.getId()) {
				repetido=true;
			}
			if(!repetido) {
				getFavoritos().add(audiovisual);
			}
		}
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
	


	/**
	 * Busca una persona segun su nombre y contrasenia
	 * @param nombreUsuario
	 * @param contrasenia
	 * @return
	 */
	public static Persona buscarPersona(String nombreUsuario, String contrasenia) {
		//TODO corregir returns, solo 1
		// Busca en el HashMap de usuarios
		for (Usuario usuario : DAOPersona.usuarios.values()) {
			if (usuario.getNombre().equals(nombreUsuario) && usuario.getContrasenia().equals(contrasenia)) {
				return usuario; // Devuelve el usuario si se encuentra
			}
		}

		// Busca en el HashMap de administradores
		for (Administrador admin : DAOPersona.admins.values()) {
			if (admin.getNombre().equals(nombreUsuario) && admin.getContrasenia().equals(contrasenia)) {
				return admin; // Devuelve el administrador si se encuentra
			}
		}

		return null; // Si no se encuentra ninguna persona con las credenciales proporcionadas
	}

	
	/**
	 * Busca un id que no se encuentre en la bd
	 * @return int disponible
	 */
	public static int idDisponible() {
		  int number = 0;
	        while (DAOPersona.getIdsPersonas().contains(number)) {
	            number++;
	        }

	        return number;
	}
}