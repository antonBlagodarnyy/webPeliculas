package backEnd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import backEnd.usuarios.Administrador;
import backEnd.usuarios.Persona;
import backEnd.usuarios.Usuario;

/**
 * Clase utilizada para obtener informacion de los Usuarios de la base de datos.
 */
public class DAOPersona {

	public static final String BOOLEAN_ADMIN = "A", BOOLEAN_USER = "U";// La bd no admite boolean

	// Configuración de la conexión a la base de datos Oracle XE
	static String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL de conexión
	static String usuario = "SYSTEM"; // Usuario de la base de datos
	static String contraseña = "Admin2023"; // Contraseña de la base de datos

	
	//Listas
	public static HashMap<Integer, Usuario> usuarios = new HashMap<>();
	public static HashMap<Integer, Administrador> admins = new HashMap<>();

	
	//Personas
	public static List<Integer> getIdsPersonas() {
		List<Integer> idPersonas = new ArrayList<>(usuarios.keySet());
		idPersonas.addAll(admins.keySet());
		return idPersonas;
	}

	/**
	 * Conecta a la base de datos y obtiene la lista actualizada de personas.
	 * Esta lista se divide en usuarios y administradores, los cuales son almacenados
	 * en HashMaps separados, con las claves como el ID de la persona.
	 * 
	 * @return No devuelve ningún valor, pero actualiza los HashMaps 'usuarios' y 'admins'
	 *         con la información obtenida de la base de datos.
	 *         
	 * @throws SQLException Si ocurre un error durante la conexión a la base de datos
	 *                      o durante la ejecución de la consulta SQL.
	 *                     
	 * @author antonBlagodarnyy
	 */
	public static void getListasPersonas() {

		try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
				Statement stmt = conn.createStatement()) {
			// Declaración de la sentencia SQL
			String selectSql = "SELECT * FROM PERSONA";
			ResultSet rs = stmt.executeQuery(selectSql);

			// Recorre el ResultSet e imprime los datos
			while (rs.next()) {
				int idUsuario = rs.getInt("id_persona");
				String nombre = rs.getString("nombre");
				String contrasenia = rs.getString("contrasenia");
				String tipo = rs.getString("tipo");// Tipo es o "U" o "A"
				int baneado = rs.getInt("baneado");// Baneado es o "1" o "0"
				boolean ban;
				if (baneado == 1)
					ban = true;
				else
					ban = false;

				if (tipo.equals(BOOLEAN_USER))
					usuarios.put(idUsuario, Usuario.crearUsuario(idUsuario, nombre, contrasenia, ban));

				else
					admins.put(idUsuario, Administrador.crearAdministrador(idUsuario, nombre, contrasenia));
			}
		} catch (SQLException e) {
			System.out.println("Error en getListaUsuarios");
		}

	}
	/**
	 * Actualiza las listas de usuarios y administradores eliminando los datos actuales
	 * y volviendo a obtener la información de la base de datos.
	 * 
	 * @author antonBlagodarnyy
	 */
	public static void actualizarListaPersonas() {
		usuarios.clear();
		admins.clear();
		getListasPersonas();
	}

	
	//Usuarios
	/**
	 * Inserta un nuevo usuario en la base de datos y actualiza la lista de usuarios.
	 * 
	 * @param usuario El usuario a insertar en la base de datos.
	 * @throws SQLException Si hay un error al insertar el usuario en la base de datos.
	 *
	 * @author antonBlagodarnyy
	 */
	public static void insertUsuario(Usuario usuario) {
		int baneado;

		// Intenta establecer la conexión
		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				// Indicamos usuario y contraseña
				Statement stmt = conn.createStatement()) {
			// Declaración de la sentencia SQL
			if (usuario.isBaneado())
				baneado = 1;
			else
				baneado = 0;

			String sql = "INSERT INTO PERSONA (id_persona,nombre, contrasenia, tipo,baneado) " + "VALUES ('"
					+ usuario.getId() + "','" + usuario.getNombre() + "', '" + usuario.getContrasenia() + "', 'U',"
					+ baneado + ")";
			// Ejecutar la sentencia SQL
			stmt.execute(sql);
			System.out.println("Sentencia ejecutada correctamente.");
		} catch (SQLException e) {

		}
		actualizarListaPersonas();
	}
	/**
	 * Elimina un usuario de la base de datos y actualiza la lista de usuarios.
	 * 
	 * @param usuario El usuario a eliminar de la base de datos.
	 * @throws SQLException Si hay un error al eliminar el usuario de la base de datos.
	 *
	 *@author antonBlagodarnyy
	 */
	public static void deleteUsuario(Usuario usuario) {

		// Intenta establecer la conexión
		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				// Indicamos usuario y contraseña

				Statement stmt = conn.createStatement()) {
			// Declaración de la sentencia SQL
			String sql = "DELETE from persona where id_persona=" + usuario.getId();
			// Ejecutar la sentencia SQL
			stmt.execute(sql);
			System.out.println("Sentencia ejecutada correctamente.");
		} catch (SQLException e) {

		}
		actualizarListaPersonas();
	}

	/**
	 * Actualiza el nombre de inicio de sesión de un usuario en la base de datos y
	 * actualiza la lista de usuarios.
	 * 
	 * @param usuario El usuario cuyo nombre de inicio de sesión se actualizará.
	 * @param login   El nuevo nombre de inicio de sesión para el usuario.
	 * @throws SQLException Si hay un error al actualizar el nombre de inicio de sesión en la base de datos.
	 *
	 *@author antonBlagodarnyy
	 */
	public static void updateUsuarioLogin(Usuario usuario, String login) {

		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				Statement stmt = conn.createStatement()) {

			// Construct the SQL UPDATE statement
			String sql = "UPDATE persona SET nombre = '" + login + "' WHERE id_persona = " + usuario.getId();

			// Execute the update statement
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected > 0) {
				System.out.println("Usuario actualizado correctamente.");
			} else {
				System.out.println("No se pudo actualizar el usuario.");
			}
		} catch (SQLException e) {

		}
		actualizarListaPersonas();
	}
	/**
	 * Actualiza el estado de baneo de un usuario en la base de datos y
	 * actualiza la lista de usuarios.
	 * 
	 * @param usuario El usuario cuyo estado de baneo se actualizará.
	 * @param ban     El nuevo estado de baneo para el usuario (0 para no baneado, 1 para baneado).
	 * @throws SQLException Si hay un error al actualizar el estado de baneo en la base de datos.
	 * @author antonBlagodarnyy
	 */
	public static void updateUsuarioBan(Usuario usuario, int ban) {

		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				Statement stmt = conn.createStatement()) {

			// Construct the SQL UPDATE statement
			String sql = "UPDATE persona SET baneado = " + ban + " WHERE id_persona =" + usuario.getId();

			// Execute the update statement
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected > 0) {
				System.out.println("Usuario actualizado correctamente.");
			} else {
				System.out.println("No se pudo actualizar el usuario.");
			}
		} catch (SQLException e) {

		}
		actualizarListaPersonas();
	}
	/**
	 * Actualiza la contraseña de un usuario en la base de datos y
	 * actualiza la lista de usuarios.
	 * 
	 * @param usuario  El usuario cuya contraseña se actualizará.
	 * @param password La nueva contraseña para el usuario.
	 * @throws SQLException Si hay un error al actualizar la contraseña en la base de datos.
	 * @author antonBlagodarnyy
	 */
	public static void updateUsuarioPassword(Usuario usuario, String password) {

		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				Statement stmt = conn.createStatement()) {

			String sql = "UPDATE persona SET contrasenia = '" + password + "' WHERE id_persona =" + usuario.getId();

			// Execute the update statement
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected > 0) {
				System.out.println("Usuario actualizado correctamente.");
			} else {
				System.out.println("No se pudo actualizar el usuario.");
			}
		} catch (SQLException e) {

		}
		actualizarListaPersonas();
	}

	
	//Historial
	/**
	 * Obtiene el historial de un usuario a partir de su ID en la base de datos.
	 * 
	 * @param idPersona El ID del usuario del cual se desea obtener el historial.
	 * @return Una lista de enteros que representa el historial del usuario, donde cada
	 *         entero es el ID de un elemento del historial.
	 * @throws SQLException Si hay un error al acceder a la base de datos para obtener el historial.
	 * @author antonBlagodarnyy
	 */
	public static ArrayList <Integer> getHistorial(int idPersona){
		ArrayList<Integer> listaHistorial = new ArrayList<Integer>();	

		try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
				Statement stmt = conn.createStatement()) {
			// Declaración de la sentencia SQL
			String selectSql = "SELECT * FROM HISTORIAL WHERE id_persona =" + idPersona ;
			ResultSet rs = stmt.executeQuery(selectSql);

			// Comprueba que el result set no este vacio
			if (rs.isBeforeFirst()) {
				// Recorre el ResultSet e imprime los datos
				while (rs.next()) {
					int idAudiovisual = rs.getInt("IDAudiovisual");
					listaHistorial.add(idAudiovisual);
				}
			}

		} catch (SQLException e) {

		}
		return listaHistorial;

	}

	/**
	 * Inserta una entrada en el historial de visualización de una persona en la base de datos.
	 * 
	 * @param persona       La persona cuyo historial se actualizará.
	 * @param idAudiovisual El ID del elemento audiovisual que se agregará al historial.
	 * @throws SQLException Si hay un error al insertar la entrada en el historial en la base de datos.
	 * @author antonBlagodarnyy
	 */
	public static void insertHistorial(Persona persona, int idAudiovisual) {
		// Intenta establecer la conexión
		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				// Indicamos usuario y contraseña
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO HISTORIAL (id_persona, IDAudiovisual) VALUES (?, ?)")) {

			// Set the values for the parameters
			pstmt.setInt(1, persona.getId());
			pstmt.setInt(2, idAudiovisual);

			// Ejecutar la sentencia SQL
			pstmt.executeUpdate();
			actualizarListaPersonas();
		} catch (SQLException e) {

		}

	}
	/**
	 * Elimina el historial de visualización de una persona de la base de datos.
	 * 
	 * @param persona La persona cuyo historial se eliminará.
	 * @throws SQLException Si hay un error al eliminar el historial de la persona de la base de datos.
	 * @author antonBlagodarnyy
	 */
	public static void deleteHistorial(Persona persona) {
		// Intenta establecer la conexión
		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				// Indicamos usuario y contraseña
				PreparedStatement pstmt = conn.prepareStatement(
						"DELETE from HISTORIAL where id_persona=?")) {

			// Set the values for the parameters
			pstmt.setInt(1, persona.getId());

			// Ejecutar la sentencia SQL
			pstmt.executeUpdate();
		} catch (SQLException e) {

		}
		actualizarListaPersonas();
	}

	
	//Favoritos
	/**
	 * Obtiene la lista de elementos favoritos de una persona a partir de su ID en la base de datos.
	 * 
	 * @param idPersona El ID de la persona de la cual se desea obtener la lista de favoritos.
	 * @return Una lista de enteros que representa los elementos favoritos de la persona, donde cada
	 *         entero es el ID de un elemento favorito.
	 * @throws SQLException Si hay un error al acceder a la base de datos para obtener la lista de favoritos.
	 * @author antonBlagodarnyy
	 */
	public static ArrayList <Integer> getFavoritos(int idPersona){
		ArrayList<Integer> listaFav = new ArrayList<Integer>();	

		try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
				Statement stmt = conn.createStatement()) {
			// Declaración de la sentencia SQL
			String selectSql = "SELECT * FROM FAVORITOS WHERE id_persona ='" + idPersona + "'";
			ResultSet rs = stmt.executeQuery(selectSql);

			// Comprueba que el result set no este vacio
			if (rs.isBeforeFirst()) {
				// Recorre el ResultSet e imprime los datos
				while (rs.next()) {
					int idAudiovisual = rs.getInt("IDAudiovisual");

					listaFav.add(idAudiovisual);
				}
			}

		} catch (SQLException e) {

		}
		return listaFav;

	}

	/**
	 * Inserta un elemento como favorito para una persona en la base de datos.
	 * 
	 * @param persona       La persona para la cual se agregará el elemento como favorito.
	 * @param idAudiovisual El ID del elemento audiovisual que se agregará como favorito.
	 * @throws SQLException Si hay un error al insertar el elemento como favorito en la base de datos.
	 * @author antonBlagodarnyy
	 */
	public static void insertFavorito(Persona persona, int idAudiovisual) {
		// Intenta establecer la conexión
		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				// Indicamos usuario y contraseña
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO FAVORITOS (id_persona, IDAudiovisual) VALUES (?, ?)")) {

			// Set the values for the parameters
			pstmt.setInt(1, persona.getId());
			pstmt.setInt(2, idAudiovisual);

			// Ejecutar la sentencia SQL
			pstmt.executeUpdate();
			actualizarListaPersonas();

		} catch (SQLException e) {

		}

	}
	/**
	 * Elimina un elemento de la lista de favoritos de una persona en la base de datos.
	 * 
	 * @param persona       La persona de la cual se eliminará el elemento de la lista de favoritos.
	 * @param idAudiovisual El ID del elemento audiovisual que se eliminará de la lista de favoritos.
	 * @throws SQLException Si hay un error al eliminar el elemento de la lista de favoritos en la base de datos.
	 * @author antonBlagodarnyy
	 */
	public static void deleteFavorito(Persona persona, int idAudiovisual) {
		// Intenta establecer la conexión
		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				// Indicamos usuario y contraseña
				PreparedStatement pstmt = conn.prepareStatement(
						"DELETE from FAVORITOS where id_persona=? AND IDAudiovisual=?")) {

			// Set the values for the parameters
			pstmt.setInt(1, persona.getId());
			pstmt.setInt(2, idAudiovisual);

			// Ejecutar la sentencia SQL
			pstmt.executeUpdate();
			actualizarListaPersonas();

		} catch (SQLException e) {

		}

	}
	/**
	 * Elimina todos los elementos de la lista de favoritos de una persona en la base de datos.
	 * 
	 * @param persona La persona de la cual se eliminarán todos los elementos de la lista de favoritos.
	 * @throws SQLException Si hay un error al eliminar los elementos de la lista de favoritos en la base de datos.
	 * @author antonBlagodarnyy
	 */
	public static void deleteFavoritos(Persona persona) {
		// Intenta establecer la conexión
		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				// Indicamos usuario y contraseña
				PreparedStatement pstmt = conn.prepareStatement(
						"DELETE from FAVORITOS where id_persona=?")) {

			// Set the values for the parameters
			pstmt.setInt(1, persona.getId());

			// Ejecutar la sentencia SQL
			pstmt.executeUpdate();
			actualizarListaPersonas();
			System.out.println("Sentencia ejecutada correctamente.");
		} catch (SQLException e) {

		}

	}

	
	//DDL
	/**
	 * Crea la tabla PERSONA en la base de datos con las columnas correspondientes.
	 * La tabla PERSONA contiene información sobre los usuarios, incluyendo su ID, nombre,
	 * contraseña, tipo (administrador o usuario) y estado de baneo.
	 * 
	 * @throws SQLException Si hay un error al ejecutar la sentencia SQL para crear la tabla en la base de datos.
	 * @author antonBlagodarnyy
	 */
	public static void crearTablaPersona() {
		String sql = "CREATE TABLE PERSONA ("+
				" id_persona NUMBER PRIMARY KEY,"+
				"  nombre VARCHAR(100),"+
				"  contrasenia VARCHAR(100),"+
				"  tipo VARCHAR(1) CHECK (tipo IN ('A', 'U')),"+
				" baneado NUMBER(1,0)"+
				")";

		try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
				// Ejecuto el create
				Statement stmt = conn.createStatement()) {

			stmt.execute(sql);

		} catch (SQLException e) {

		}
	}
	/**
	 * Inserta un administrador predeterminado en la base de datos.
	 * Este método se utiliza para inicializar la base de datos con un administrador
	 * por defecto al crearla.
	 * 
	 * @throws SQLException Si hay un error al insertar el administrador en la base de datos.
	 * @author antonBlagodarnyy
	 */
	public static void insertarAdmin() {
		// Intenta establecer la conexión
		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				// Indicamos usuario y contraseña
				Statement stmt = conn.createStatement()) {
			// Declaración de la sentencia SQL

			String sql = "INSERT INTO persona (id_persona, nombre, contrasenia,tipo,baneado) VALUES (1, 'Admin1', '1234','A',0)";
			// Ejecutar la sentencia SQL
			stmt.execute(sql);

		} catch (SQLException e) {


		}
	}
	/**
	 * Inicializa la tabla de personas en la base de datos creando la tabla y 
	 * agregando un administrador predeterminado.
	 * 
	 * @throws SQLException Si hay un error al crear la tabla de personas o al insertar el administrador en la base de datos.
	 * @see #crearTablaPersona()
	 * @see #insertarAdmin()
	 * @author antonBlagodarnyy
	 */
	public static void inicializarPersonas() {
		crearTablaPersona();
		insertarAdmin();
	}

	/**
	 * Crea la tabla HISTORIAL en la base de datos con las columnas correspondientes.
	 * La tabla HISTORIAL registra la actividad de visualización de los usuarios,
	 * incluyendo el ID de la entrada de historial (generado automáticamente), el ID del usuario
	 * que realizó la visualización y el ID del elemento audiovisual visto.
	 * 
	 * @throws SQLException Si hay un error al ejecutar la sentencia SQL para crear la tabla en la base de datos.
	 * @author antonBlagodarnyy
	 */
	public static void crearTablaHistorial() {

		String sql = "CREATE TABLE historial("
				+	  "IDFavorito NUMBER GENERATED BY DEFAULT AS IDENTITY,"
				+   " id_persona NUMBER,"
				+  "IDAudiovisual NUMBER,"
				+"FOREIGN KEY (id_persona) REFERENCES PERSONA (id_persona),"
				+"FOREIGN KEY (IDAudiovisual) REFERENCES ContenidoAudiovisual (IDAudiovisual)"
				+ ")";

		try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
				// Ejecuto el create
				Statement stmt = conn.createStatement()) {

			stmt.execute(sql);

		} catch (SQLException e) {

		}
	}
	/**
	 * Crea la tabla FAVORITOS en la base de datos con las columnas correspondientes.
	 * La tabla FAVORITOS registra los elementos marcados como favoritos por los usuarios,
	 * incluyendo el ID de la entrada de favorito (generado automáticamente), el ID del usuario
	 * que marcó el elemento como favorito y el ID del elemento audiovisual marcado como favorito.
	 * 
	 * @throws SQLException Si hay un error al ejecutar la sentencia SQL para crear la tabla en la base de datos.
	 * @author antonBlagodarnyy
	 */
	public static void crearTablaFavoritos() {
		String sql = "CREATE TABLE favoritos("
				+ "    IDFavorito NUMBER GENERATED BY DEFAULT AS IDENTITY,"
				+ "    id_persona NUMBER,"
				+ "    IDAudiovisual NUMBER,"
				+ "    FOREIGN KEY (id_persona) REFERENCES PERSONA (id_persona),"
				+ "    FOREIGN KEY (IDAudiovisual) REFERENCES ContenidoAudiovisual (IDAudiovisual)"
				+ ")";

		try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
				// Ejecuto el create
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.execute();

		} catch (SQLException e) {

		}
	}

}