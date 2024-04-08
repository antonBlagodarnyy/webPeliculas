package main;

import java.util.ArrayList;

public abstract class Persona {
	//TODO Agregar toString e Equals de Persona, Usuario y Administrador
	//Atributos
	private String nombre;
	private String contrasenia;
	
	private ArrayList<Audiovisual> favoritos;
	private ArrayList<Audiovisual> historialReproducciones;
	private ArrayList<Double> historialValoraciones;
		
	//Metodos
	public Persona(String nombre, String contrasenia) {
		super();
		this.nombre = nombre;
		this.contrasenia = contrasenia;
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
	
			/**Aniade valoraciones en la bd.
			 * 
			 * @param audiovisual
			 * @param valoracion
			 * @return
			 */
			public double dejarValoracion(int id ,double valoracion) {
				//TODO Agregar DAO de AV
				// arraylist de doubles para valoraciones 
				// este metodo haga arraylist.add?
				//audiovisual.setValoracion(valoracion);
				historialValoraciones.add((Double)valoracion);
				return 0;
			}
			
			/**Aniade un AV a favoritos
			 *  
			 * @param audiovisual
			 * @return AV si se ha aniadido. Si no devuelve null
			 */
			public Audiovisual aniadirAFavoritos(int idl) {
				//TODO Agregad DAO de AV
				//Revisar si el id de la pelicula se encuentra en el DAO
				//Devolver lo requerido
				//this.favoritos.add(audiovisual);
				return null;
			}
	
}
