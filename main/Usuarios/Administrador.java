package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Administrador extends Persona {
	//Att
	private int idAdmin;//Att unico, no modificable e incremental
	private static int contadorIdAdmin=0;
	
	//Meth
		//Const
	public Administrador(String nombre, String contrasenia) {
		super(nombre, contrasenia);
		this.idAdmin=contadorIdAdmin;
		contadorIdAdmin++;
	}
		//GetnSet
	public int getIdAdmin() {
		return idAdmin;
	}
	
	//Deriv
	/**Elimina la cuenta de un usuario de la bd 
	 * 
	 * @param idUsuario
	 *@return true si se ha eliminado
	 */ 
	public static boolean eliminarUsuario(int idUsuario ) {
		//TODO Agregar DAOUsuario
		//buscar user segun su id y eliminarlo del dao
		return false;
	}

	/**
	 * 
	 * @param titulo
	 * @param genero
	 * @param valoracion
	 * @param fecha
	 * @param duracion
	 * @param temporadas
	 * @return
	 */
	public  Serie aniadirSerie(String titulo, String genero, double valoracion, LocalDate fecha, LocalTime duracion,ArrayList<Temporada> temporadas) {
		//TODO Agregar DAO
		//Revisar si es mejor juntar con aniadirPelicula
		//Si es el caso, se podria usar un instanceOf para aceptar temporadas en caso de que sea serie?
		Serie serie = new Serie(titulo,  genero,  valoracion,  fecha,  duracion,temporadas);
		return serie;
	}

	/**Aniade pelicula al DAO de AV
	 * 
	 * @param titulo
	 * @param genero
	 * @param valoracion
	 * @param fecha
	 * @param duracion
	 * @return pelicula aniadida
	 */
	public  Pelicula aniadirPelicula(String titulo, String genero, double valoracion, LocalDate fecha, LocalTime duracion) {
		//TODO Agregar DAO AV
		//Revisar que no haya un AV con el mismo titulo
		//Revisar si es mejor juntar el metodo con aniadirSerie
		//Revisar si es mejor no aniadir valoraciones
		Pelicula pelicula = new Pelicula(titulo,  genero,  valoracion,  fecha,  duracion);
		return pelicula;
	}

	/**
	 * banea un usuario, controla que se encuentre en el DAO
	 * @param usuario
	 * @return true si esta baneado
	 */
	public static boolean banearUsuario(int idUsuario) {
		//TODO Agregar DAOUsuario 
		//Buscar y setBaneado segun su id
		//Si ya se encuentra baneado, notificarlo
		//Devolver true si se banea

		//if(usuario!=null)
		//usuario.setBaneado(true);
		return false;
	}

}