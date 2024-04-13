package backEnd.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import backEnd.audiovisual.Audiovisual;
import backEnd.usuarios.Administrador;
import backEnd.usuarios.Persona;
import backEnd.usuarios.Usuario;


//https://developer.themoviedb.org/docs/getting-started
/**
 * Clase utilizada para obtener informacion de los Usuarios de la base de datos.
 */
public class DAOPersona {

	public static final String BOOLEAN_ADMIN="A", BOOLEAN_USER="U";//La bd no admite boolean

	// Configuración de la conexión a la base de datos Oracle XE
	static String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL de conexión
	static String usuario = "SYSTEM"; // Usuario de la base de datos
	static String contraseña = "Admin2023"; // Contraseña de la base de datos

	public static HashMap<Integer, Usuario> usuarios = new HashMap<>();
	public static HashMap<Integer, Administrador> admins = new HashMap<>();
	public static HashMap[] listaPersonas = getListaPersonas();

	public static List<Integer> getIdsPersonas() {
		List<Integer>     idPersonas = new ArrayList<>(usuarios.keySet());
        idPersonas.addAll(admins.keySet());
        return idPersonas;
    }

	/**
	 * Conecta a la bd para obtener la lista de personas que contiene
	 * @return Lista de usuarios actualizada
	 */
	public static HashMap [] getListaPersonas(){
		HashMap [] listaPersonas = new HashMap [2];
		ArrayList<Usuario> listaUsuarios= new ArrayList<Usuario>();
		ArrayList<Administrador> listaAdmin= new ArrayList<Administrador>();

		try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
				Statement stmt = conn.createStatement()) {
			// Declaración de la sentencia SQL
			String selectSql = "SELECT * FROM PERSONA";
			ResultSet rs = stmt.executeQuery (selectSql);

			//Recorre el ResultSet e imprime los datos
			while(rs.next()) {
				int idUsuario =rs.getInt("id_persona");
				String nombre= rs.getString("nombre");
				String contrasenia= rs.getString("contrasenia");
				String tipo= rs.getString("tipo");//Tipo es o "U" o "A"
				int baneado= rs.getInt("baneado");//Baneado es o "1" o "0"
				boolean ban;
				if(baneado==1)
					ban=true;
				else
					ban=false;

				if (tipo.equals(BOOLEAN_USER)) {
					Usuario usuarioAux = new Usuario(idUsuario, nombre, contrasenia, ban);
					usuarios.put(idUsuario, usuarioAux);
				} else {
					Administrador adminAux = new Administrador(idUsuario, nombre, contrasenia);
					admins.put(idUsuario, adminAux);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error en getListaUsuarios");
		}

		listaPersonas[0]=usuarios;
		listaPersonas[1]=admins;
		return listaPersonas;
	}

	public static void actualizarListaPersonas() {
		listaPersonas=getListaPersonas();
	}

	public static void insertUsuario(Usuario usuario) {
		int baneado;
		// Configuración de la conexión a la base de datos Oracle XE
		String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL de conexión
		// Intenta establecer la conexión
		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				//Indicamos usuario y contraseña
				Statement stmt = conn.createStatement()) {
			// Declaración de la sentencia SQL
			if(usuario.isBaneado())
				baneado=1;
			else
				baneado=0;

			String sql = "INSERT INTO PERSONA (id_persona,nombre, contrasenia, tipo,baneado) "
					+ "VALUES ('"+usuario.getIdUsuario()+"','"+usuario.getNombre()+"', '"+usuario.getContrasenia()+"', 'U',"+baneado+")";
			// Ejecutar la sentencia SQL
			stmt.execute(sql);
			System.out.println("Sentencia ejecutada correctamente.");
		} catch (SQLException e) {
			// Manejo de excepciones
			System.out.println("Error al ejecutar la sentencia: " + e.getMessage());
		}
	}

	public static void deleteUsuario(Usuario usuario) {
		// Configuración de la conexión a la base de datos Oracle XE
		String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL de conexión
		// Intenta establecer la conexión
		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				//Indicamos usuario y contraseña
				Statement stmt = conn.createStatement()) {
			// Declaración de la sentencia SQL
			String sql = "DELETE from persona where id_persona='"+usuario.getIdUsuario()+"' ";
			// Ejecutar la sentencia SQL
			stmt.execute(sql);
			System.out.println("Sentencia ejecutada correctamente.");
		} catch (SQLException e) {
			// Manejo de excepciones
			System.out.println("Error al ejecutar la sentencia: " + e.getMessage());
		}
	}

	public static void updateUsuarioLogin(Usuario usuario, String login) {
		// Configuración de la conexión a la base de datos Oracle XE
		String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL de conexión

		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				Statement stmt = conn.createStatement()) {

			// Construct the SQL UPDATE statement
			String sql = "UPDATE persona SET nombre = '"+ login +"' WHERE id_persona = '" + usuario.getIdUsuario() + "'";

			// Execute the update statement
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected > 0) {
				System.out.println("Usuario actualizado correctamente.");
			} else {
				System.out.println("No se pudo actualizar el usuario.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateUsuarioBan(Usuario usuario, int ban) {
		// Configuración de la conexión a la base de datos Oracle XE
		String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL de conexión

		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				Statement stmt = conn.createStatement()) {

			// Construct the SQL UPDATE statement
			String sql = "UPDATE persona SET baneado = "+ ban +" WHERE id_persona = '" + usuario.getIdUsuario() + "'";

			// Execute the update statement
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected > 0) {
				System.out.println("Usuario actualizado correctamente.");
			} else {
				System.out.println("No se pudo actualizar el usuario.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateUsuarioPassword(Usuario usuario, String password) {
		// Configuración de la conexión a la base de datos Oracle XE
		String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL de conexión

		try (Connection conn = DriverManager.getConnection(url, "SYSTEM", "Admin2023");
				Statement stmt = conn.createStatement()) {

			// Construct the SQL UPDATE statement
			String sql = "UPDATE persona SET contrasenia = '"+ password +"' WHERE id_persona = '" + usuario.getIdUsuario() + "'";

			// Execute the update statement
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected > 0) {
				System.out.println("Usuario actualizado correctamente.");
			} else {
				System.out.println("No se pudo actualizar el usuario.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**Elimina la cuenta de un usuario de la bd 
	 * 
	 * @param idUsuario
	 *@return true si se ha eliminado
	 */
	public static boolean eliminarUsuario(int idUsuario) {
		Usuario usuarioAux = usuarios.remove(idUsuario); // Remove the user from the HashMap by ID
		if (usuarioAux != null) {
			// User was successfully removed
			return true;
		} else {
			// User with the given ID does not exist
			System.out.println("Usuario no encontrado.");
			return false; // Return false indicating failure
		}
	}

	public  static void aniadirValoracionAHistorial(double valoracion, Usuario usuario) {
		ArrayList auxiliar = usuario.getHistorialValoraciones();
		auxiliar.add(valoracion);
		usuario.setHistorialValoraciones(auxiliar);
	}

	public static void aniadirAudiovisualAHistorial(Audiovisual audiovisual, Usuario usuario) {
		usuario.getHistorialReproducciones().add(audiovisual);
	}


}