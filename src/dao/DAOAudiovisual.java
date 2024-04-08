package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate; 
import java.time.LocalTime;
import java.util.ArrayList;

import audiovisual.Audiovisual;
import audiovisual.Pelicula;
import audiovisual.Serie;
import enums.Genero;
import trabajadores.Actor;
import trabajadores.Director;
import usuarios.Administrador;
import usuarios.Usuario;



public class DAOAudiovisual {
	private static ArrayList<Audiovisual> daoAudiovisual=new ArrayList();

	/**
	 * Constructor del objeto utilizado para acceder a los datos de la base de datos.
	 */
	public DAOAudiovisual() {
	}

	public static void añadirAudioVisual(Audiovisual audiovisualIntroducido) {
		daoAudiovisual.add(audiovisualIntroducido);
	}
	/**
	 * Introducimos el ID de una pelicula
	 * @param id
	 * @return
	 */
	public static Audiovisual getAudiovisual(int id) {
		Audiovisual contenidoAudiovisual=null;
		for (Audiovisual audiovisual : daoAudiovisual) {
			if(audiovisual.getId()==id) {
				contenidoAudiovisual=audiovisual;
				daoAudiovisual.get(id);
			}
		}	
		return contenidoAudiovisual;	
	}
	/**
	 * En este metodo obtenemos una lista con todas las peliculas del catalogo
	 * @return peliculasDelCatalogo
	 */
	public static ArrayList<Pelicula> filtrarPelicula() {
		ArrayList<Pelicula>peliculasDelCatalogo=new ArrayList<>();
		for (Audiovisual audiovisual : daoAudiovisual) {
			if (audiovisual instanceof Pelicula) {
				Pelicula pelicula = (Pelicula) audiovisual;
				peliculasDelCatalogo.add(pelicula);
			}
		}
		return peliculasDelCatalogo;
	}
	/**
	 * En este metodo obtenemos la lista de series que tenemos en nuestro catalogo
	 * @return seriesDelCatalogo
	 */
	public static ArrayList<Serie> filtrarSerie() {
		ArrayList<Serie>seriesDelCatalogo=new ArrayList<>();
		for (Audiovisual audiovisual : daoAudiovisual) {
			if (audiovisual instanceof Serie) {
				Serie serie = (Serie) audiovisual;
				seriesDelCatalogo.add(serie);
			}
		}
		return seriesDelCatalogo;
	}
	public static ArrayList<Audiovisual> filtrarValoracion(double valoracionMinima,double valoracionMaxima) {
		ArrayList<Audiovisual> audiovisualFiltrado=new ArrayList(); 
		for (Audiovisual audiovisual : daoAudiovisual) {
			if(audiovisual.getMediaValoraciones()<=valoracionMaxima&&audiovisual.getMediaValoraciones()>=valoracionMinima) {
				audiovisualFiltrado.add(audiovisual);
			}
		}
		return audiovisualFiltrado;
	}
	public static void filtrarValoracionSerie(double valoracionMinima,double valoracionMaxima) {
		ArrayList<Serie> series=filtrarSerie();
		ArrayList<Serie> seriesFiltradas=new ArrayList();
		for (Serie serie : series) {
			if(serie.getMediaValoraciones()<=valoracionMaxima&&serie.getMediaValoraciones()>=valoracionMinima) {
				seriesFiltradas.add(serie);
			}
		}
	}
	public static void filtrarValoracionPelicula(double valoracionMinima,double valoracionMaxima) {
		ArrayList<Pelicula> peliculas=filtrarPelicula();
		ArrayList<Pelicula> peliculasFiltradas=new ArrayList();
		for (Pelicula pelicula : peliculasFiltradas) {
			if(pelicula.getMediaValoraciones()<=valoracionMaxima&&pelicula.getMediaValoraciones()>=valoracionMinima) {
				peliculasFiltradas.add(pelicula);
			}
		}
	}

	/**
	 * En este metodo obtenemos una lista con todo el contenido del genero que hemos introducido
	 * @param genero
	 * @return audiovisualFiltrado
	 */
	public static ArrayList<Audiovisual> filtrarGenero(Genero genero) {
		ArrayList<Audiovisual> audiovisualFiltrado=new ArrayList(); 
		for (Audiovisual audiovisual : daoAudiovisual) {
			if(audiovisual.getGenero().equals(genero)) {
				audiovisualFiltrado.add(audiovisual);
			}
		}
		return audiovisualFiltrado;
	}
	/**
	 * En este metodo obtenemos una lista con todas las series del genero que hemos introducido
	 * @param genero
	 * @return
	 */
	public static ArrayList<Serie> filtrarGeneroSerie(Genero genero){
		ArrayList<Serie> series=filtrarSerie();
		ArrayList<Serie> seriesFiltradas=new ArrayList();
		for (Serie serie : series) {
			if(serie.getGenero().equals(genero)) {
				seriesFiltradas.add(serie);
			}
		}
		return seriesFiltradas;
	}


	/**
	 * En este metodo obtenemos una lista con todas las peliculas que son del genero 
	 * que hemos introducido
	 */
	public static ArrayList<Pelicula> filtrarGeneroPelicula(Genero genero){
		ArrayList<Pelicula> peliculas=filtrarPelicula();
		ArrayList<Pelicula> peliculasFiltradas=new ArrayList();
		for (Pelicula pelicula : peliculas) {
			if(pelicula.getGenero().equals(genero)) {
				peliculasFiltradas.add(pelicula);
			}
		}
		return peliculasFiltradas;
	}


	/**Aniade valoraciones en la bd.
	 * 
	 * @param audiovisual
	 * @param valoracion
	 * @return
	 */
	public static Audiovisual dejarValoracion(int id ,double valoracion, Usuario usuario) {
		//Tal y como eTODO Se podria hacer un arrayList con las valoraciones en audiovisual y filtrar por la media de esas valoraciones?sta solo se puede dejar una valoracion
		Audiovisual audiovisualAux =getAudiovisual(id);
		ArrayList<Double> valoracionAux = audiovisualAux.getValoracion();

		if (valoracion < Audiovisual.VALORACION_MAXIMA && valoracion > Audiovisual.VALORACION_MINIMA) {
			Double valoracionDouble=valoracion;
			valoracionAux.add(valoracionDouble);
			DAOPersona.aniadirValoracionAHistorial(valoracion,usuario);
		}
		return audiovisualAux;
	}

	/**Aniade un AV a favoritos
	 *  
	 * @param audiovisual
	 * @return AV si se ha aniadido. Si no devuelve null
	 */
	public static Audiovisual aniadirAFavoritos(int idl, Usuario usuario) {
		Audiovisual audiovisualAux =getAudiovisual(idl);
		if(audiovisualAux!=null && usuario != null) {
			ArrayList favoritosAux = usuario.getFavoritos();
			favoritosAux.add(audiovisualAux);
			usuario.setFavoritos(favoritosAux);
		} else 
			audiovisualAux=null;

		return audiovisualAux;
	}

	/**Elimina un AV de favoritos
	 *  
	 * @param audiovisual
	 * @return AV si se ha eliminado. Si no devuelve null
	 */
	public static Audiovisual eliminidaAVDeFavoritos(int idl, Usuario usuario) {
		Audiovisual audiovisualAux =getAudiovisual(idl);
		ArrayList favoritosAux = usuario.getFavoritos();
		favoritosAux.add(audiovisualAux);

		if(audiovisualAux!=null && usuario != null && favoritosAux.contains(audiovisualAux)) {
			favoritosAux.remove(audiovisualAux);
			usuario.setFavoritos(favoritosAux);
		} else 
			audiovisualAux=null;

		return audiovisualAux;
	}
	/**
	 * En este metodo obtenemos una lista con las peliculas que ha dirigido el director en cuestion.
	 * @param director
	 * @return peliculasDelDirector
	 */
	public static ArrayList<Audiovisual> filtrarPorDirector(Director director) {
		ArrayList<Audiovisual> peliculasDelDirector=new ArrayList<Audiovisual>();
		for (Audiovisual audiovisual : daoAudiovisual) {
			if(audiovisual.getDirector().equals(director)) {
				peliculasDelDirector.add(audiovisual);
			}
		}
		return peliculasDelDirector;
	}

	/**
	 * En este metodo obtenemos una lista con las peliculas en las que ha participado
	 * el actor en cuestion.
	 * @param actor
	 * @return peliculasDelActor
	 */
	public static ArrayList<Audiovisual> filtrarPorActor(Actor actor) {
		ArrayList<Audiovisual> peliculasDelActor=new ArrayList<Audiovisual>();
		for (Audiovisual audiovisual : daoAudiovisual) {
			if(audiovisual.getDirector().equals(actor)) {
				peliculasDelActor.add(audiovisual);
			}
		}
		return peliculasDelActor;
	}



}
}
