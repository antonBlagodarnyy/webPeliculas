package backEnd.dao;

import java.sql.Connection; 
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate; 
import java.time.LocalTime;
import java.util.ArrayList;

import backEnd.audiovisual.Audiovisual;
import backEnd.audiovisual.Pelicula;
import backEnd.audiovisual.Serie;
import backEnd.audiovisual.Temporada;
import backEnd.utilidad.Utilidad;
import frontEnd.Texto;

public class DAOAudiovisual {
	public static String selectAudiovisualTitulo(int id) {

		String resultado = null;

		//Realizo la conexion y el select del ID de la tabla

		try (Connection conn = DriverManager.getConnection(Texto.ENLACE_BD, Texto.USUARIO, Texto.CONTRASENA);		

				Statement stmt = conn.createStatement()) {

			String sql = "SELECT titulo FROM ContenidoAudiovisual WHERE idAudiovisual='" + id+ "'";



			// Ejecuto el Select

			ResultSet rs = stmt.executeQuery(sql);



			// Recojo el ID del titulo introducido

			if (rs.next()) {

				resultado = rs.getString("Titulo");

			}

		} catch (SQLException e) {

			//Si salta excepcion simplemente no pasa nada

		}

		/*Devuelvo el resultado, si no ha encontrado nada devuelve 

		el -1 por lo cual luego no hara nada*/

		return resultado; 



	}
	/**
	 * Reaiza un select de un genero determinado
	 * @author Antonio Jesus Blanco
	 * @param genero
	 * @return resultado
	 */
	public static String selectAudiovisualGenero(String genero) {
		String resultado="";
		//Realizo la conexion y el select de la tabla
		try (Connection conn = DriverManager.getConnection(Texto.ENLACE_BD, Texto.USUARIO, Texto.CONTRASENA);
				PreparedStatement pstmt = conn.prepareStatement(Texto.SENTENCIA_SELECT_GENERO)) {
			pstmt.setString(1, genero);

			// Ejecuto el Select
			ResultSet rs = pstmt.executeQuery();

			// Recojo los valores en un String
			while (rs.next()) {
				String tituloDevuelto = rs.getString("titulo");
				String generoDevuelto = rs.getString("genero"); // TODO Hacer el switch
				double valoracionMedia=rs.getDouble("ValoracionMedia");
				LocalDate fechaEstreno = rs.getDate("fecha").toLocalDate();

				resultado+="\nTitulo: "+tituloDevuelto+", \nGenero: "+genero+", \n"+"Valoracion media: "+valoracionMedia+"\n"+"Fecha de estreno: "+fechaEstreno+"\n";
			}

		} catch (SQLException e) {
			// Si da fallo informa del fallo
			resultado=Texto.SENTENCIA_ERROR+ e.getMessage();
		}
		return resultado;

	}

	/**
	 * Realiza un select que da un id, el cual es buscado por el titulo
	 * @author Antonio Jesus Blanco
	 * @param titulo
	 * @return resultado
	 */
	public static int selectAudiovisualId(String titulo) {
		int resultado = -1;
		//Realizo la conexion y el select del ID de la tabla
		try (Connection conn = DriverManager.getConnection(Texto.ENLACE_BD, Texto.USUARIO, Texto.CONTRASENA);		
				Statement stmt = conn.createStatement()) {
			String sql = Texto.SENTENCIA_SELECT_ID + titulo + "'";

			// Ejecuto el Select
			ResultSet rs = stmt.executeQuery(sql);

			// Recojo el ID del titulo introducido
			if (rs.next()) {
				resultado = rs.getInt("IDAudiovisual");
			}
		} catch (SQLException e) {
			//Si salta excepcion simplemente no pasa nada
		}
		/*Devuelvo el resultado, si no ha encontrado nada devuelve 
		el -1 por lo cual luego no hara nada*/
		return resultado; 

	}
	/**
	 * Realiza un select general de toda la tabla ContenidoAudiovisual
	 * @author Antonio Jesus Blanco
	 * @return resultado
	 */
	public static String selectAudiovisual() {
		int minutosDuracion=0;
		int horasDuracion=0;

		String resultado = "";
		//Realizo la conexion y el select del ID de la tabla
		try (Connection conn = DriverManager.getConnection(Texto.ENLACE_BD, Texto.USUARIO, Texto.CONTRASENA);
				Statement stmt = conn.createStatement()) {
			String sql =Texto.SENTENCIA_SELECT;

			// Ejecuto el Select 
			ResultSet rs = stmt.executeQuery(sql);

			// Recojo el resultado en un String 
			while (rs.next()) {
				String titulo=rs.getString("Titulo");
				int duracion=rs.getInt("Duracion");
				String genero=rs.getString("Genero");
				double valoracion=rs.getDouble("ValoracionMedia");
				LocalDate fechaEstreno = rs.getDate("fecha").toLocalDate();

				//Realizo un bucle para recoger la duracion en horas y minutos
				while (duracion>=60) {
					duracion-=60;
					horasDuracion++;
				}
				minutosDuracion=duracion;
				resultado+="\nTitulo: "+titulo+", \nGenero: "+genero+", \nDuracion: "+horasDuracion+":"+minutosDuracion+",\n"+"Valoracion media: "+valoracion+"\n"+"Fecha de estreno: "+fechaEstreno+"\n";
			}

		} catch (SQLException e) {
			resultado=Texto.SENTENCIA_ERROR+ e.getMessage();
		}

		return resultado; 

	}
	/**
	
 * Realiza un insert a la tabla ya sea de una pelicula o una serie
	 * @author Antonio Jesus Blanco
	 * @param audiovisual
	 * @return mensaje
	 */
	public static String insertAudiovisual(Audiovisual audiovisual) {
		String mensaje="";
		String genero = null;
		//Cambio el genero de audiovisual a un String para introducirlo en la tabla
		genero=Utilidad.generoCambiarDeGeneroAString(audiovisual.getGenero());

		//Realizo un bucle para tener todos los nombres de los actores en un String
		String nombreActores="";
		for (String actor : audiovisual.getActores()) {
			nombreActores+=actor+"\n";
		}
		int minutosAudiovisual=audiovisual.getDuracion().getHour()*60+audiovisual.getDuracion().getMinute();

		//Realizo la conexion y el insert en la tabla
		try (Connection conn = DriverManager.getConnection(Texto.ENLACE_BD, Texto.USUARIO, Texto.CONTRASENA)) {
			String sql = Texto.SENTENCIA_INSERT;;
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// Establezco los parámetros que introducire en la consulta
			pstmt.setString (1, audiovisual.getTitulo());
			pstmt.setString (2, genero); 
			pstmt.setDouble (3, audiovisual.getMediaValoraciones()); 
			pstmt.setDate (4, Date.valueOf(audiovisual.getFecha()));
			pstmt.setInt (5, minutosAudiovisual);
			pstmt.setString (6, audiovisual.getDirector()); 
			pstmt.setString (7, nombreActores); 

			// Si es una pelicula el valor de numero de temporadas y numero capitulos es 0
			if (audiovisual instanceof Pelicula) {
				pstmt.setInt (8, 0); 
				pstmt.setInt (9, 0);  
			}else {
				//Si es una serie le añado el numero de temporadas y capitulos que tenga
				pstmt.setInt (8, ((Serie) audiovisual).getNumeroDeTemporadas()); 
				pstmt.setInt (9, ((Serie) audiovisual).getCapitulosTotales());  
			}
			// Ejecuto el Insert
			pstmt.executeUpdate();
			mensaje=Texto.SENTENCIA_CREADA;     
		} catch (SQLException e) {
			// Si da fallo informa del fallo
			mensaje=Texto.SENTENCIA_ERROR + e.getMessage();
		}
		return mensaje;
	}

	/**
	 * Realiza un update de la valoracion de un determinado id
	 * @author Antonio Jesus Blanco
	 * @param id
	 * @param valoracionIntroducida
	 * @return
	 */
	public static String updateAudiovisual(int id, double valoracionIntroducida) {
		String mensaje="";
		//Realizo la conexion y el Update de la tabla
		try (Connection conn = DriverManager.getConnection(Texto.ENLACE_BD, Texto.USUARIO, Texto.CONTRASENA)) {
			String sql = Texto.SENTENCIA_UPDATE;
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// Establezco la valoracion que metere y el ID al que le quiero cambiar la valoracion
			pstmt.setDouble (1, valoracionIntroducida);
			pstmt.setInt (2, id); //TODO

			// Ejecuto el Update
			pstmt.executeUpdate();
			mensaje=Texto.SENTENCIA_CREADA;   
		} 

		catch (SQLException e) {
			// Si da fallo informa del fallo
			mensaje=Texto.SENTENCIA_ERROR + e.getMessage();

		}
		return mensaje;
	}

	/**
	 * Realiza un delete de un determinado ID
	 * @author Antonio Jesus Blanco
	 * @param id
	 * @return
	 */
	public static String deleteAudiovisual(int id) {
		String mensaje="";
		//Realizo la conexion y el Delete de un determinado ID
		try (Connection conn = DriverManager.getConnection(Texto.ENLACE_BD, Texto.USUARIO, Texto.CONTRASENA);			
				Statement stmt = conn.createStatement()) {
			String sql = Texto.SENTENCIA_DELETE + id;

			//Ejecuto el Delete
			stmt.executeUpdate(sql);
			mensaje=Texto.SENTENCIA_CREADA;  

		} catch (SQLException e) {
			// Si da fallo informa del fallo
			mensaje=Texto.SENTENCIA_ERROR + e.getMessage();
		}  
		return mensaje;
	}
	/**
	 * Borra la tabla contenidoAudiovisual
	 * @author Antonio Jesus Blanco
	 * @return
	 */
	public static String borrarTablaAudiovisual() {
		String mensaje="";
		//Realizo la conexion y el delete table
		String sql = Texto.SENTENCIA_BORRAR_TABLA;
		try (Connection conn = DriverManager.getConnection(Texto.ENLACE_BD, Texto.USUARIO, Texto.CONTRASENA);
				// Ejecuto el delete
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.execute();
			mensaje=Texto.SENTENCIA_CREADA; 

		} catch (SQLException e) {
			// Si da fallo informa del fallo
			mensaje= Texto.SENTENCIA_ERROR+ e.getMessage(); 
		}
		return mensaje;
	}


	/**
	 * Crea la tabla contenidoAudiovisual
	 * @author Antonio Jesus Blanco
	 * @return
	 */
	public static String crearTablaAudiovisual() {
		String mensaje="";
		//Realizo la conexion y el create table
		String sql = Texto.CREAR_TABLA;
		try (Connection conn = DriverManager.getConnection(Texto.ENLACE_BD, Texto.USUARIO, Texto.CONTRASENA);
				// Ejecuto el create
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.execute();
			mensaje=Texto.TABLA_CREADA;  
		} catch (SQLException e) {
			/// Si da fallo informa del fallo
			mensaje=Texto.TABLA_ERROR + e.getMessage(); 
		}
		return mensaje;
	}

}

