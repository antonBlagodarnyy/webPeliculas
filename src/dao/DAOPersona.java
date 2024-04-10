package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import usuarios.Persona;
import usuarios.Administrador;
import usuarios.Usuario;


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

	public static ArrayList<Usuario> usuarioBD= getListaUsuarios();
	public static ArrayList<Administrador> adminBD= new ArrayList<>();

	/**
	 * Conecta a la bd para obtener la lista de usuarios
	 * @return Lista de usuarios actualizada
	 */
	public static ArrayList<Usuario> getListaUsuarios(){
		ArrayList<Usuario> listaAux= new ArrayList<Usuario>();

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

				if(tipo.equals(BOOLEAN_USER)) {
					Usuario usuarioAux = new Usuario(idUsuario,nombre,contrasenia,ban);
					listaAux.add(usuarioAux);
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Error en getListaUsuarios");
		}
		return listaAux;
	}


	/**
	 * Metodo utilizado para obtener un objeto Usuario de la base de datos, a partir de su
	 * idUsuario.
	 * @param idUsuario, id del admin que se desea obtener de la BD
	 * @return objeto Usuario, cuyo username coincide con el parametro. En caso de no existir se
	 * devuelve null. 
	 */
	public static Usuario getUsuario(int idUsuario) {
		Usuario usuarioBuscado=null;
		for(int i=0;i<usuarioBD.size() && usuarioBuscado==null;i++) {
			Usuario user=(Usuario) usuarioBD.get(idUsuario);
			if(user.getIdUsuario()==(idUsuario))
				usuarioBuscado=user;
		}
		return usuarioBuscado;
	}



/**
 * Metodo utilizado para obtener un objeto Administrador de la base de datos, a partir de su
 * idAdmin.
 * @param idAdmin, id del admin que se desea obtener de la BD
 * @return objeto admin, cuyo id coincide con el parametro. En caso de no existir se
 * devuelve null. 
 */
public static Administrador getAdmin(int idAdmin) {
	Administrador adminBuscado=null;
	for(int i=0;i<usuarioBD.size() && adminBuscado==null;i++) {
		Administrador user=(Administrador) adminBD.get(idAdmin);
		if(user.getIdAdmin()==(idAdmin))
			adminBuscado=user;
	}
	return adminBuscado;
}

/**Comprueba que no haya un usuario y una constrasenia ya en el DAO
 * Si es asi, agrega el nuevo usuario
 * Si no, devuelve null
 * 
 * @param usuario
 * @param contrasenia
 * @return usuario
 */
public static Usuario registrarse(Usuario usuario) {
	String contrasenia = usuario.getContrasenia();
	String nombre = usuario.getNombre();
	boolean repe=false;
	for (Usuario usuarioAux : usuarioBD) {
		if(!repe) {
			if(contrasenia.equals(usuarioAux.getContrasenia()) ||
					nombre.equals(usuarioAux.getNombre())) {
				repe=true;

			}
		}
		if(!repe)
			usuarioBD.add(usuario);
	}

	return usuario;
}

/**
 * Metodo utilizado para banear o quitar baneos a usuarios
 * @param usuario
 * @return estado del boolean baneado
 */
public static boolean banearUsuario(int idUsuario) {
	Usuario usuarioAux =DAOPersona.getUsuario(idUsuario);
	if(usuarioAux.isBaneado())
		usuarioAux.setBaneado(false);
	else 
		usuarioAux.setBaneado(true);

	return usuarioAux.isBaneado();
}


/**Elimina la cuenta de un usuario de la bd 
 * 
 * @param idUsuario
 *@return true si se ha eliminado
 */
public static boolean eliminarUsuario(int idUsuario) {
	boolean eliminado=false;
	Usuario usuarioAux = usuarioBD.get(idUsuario);
	if(usuarioAux!=null) {
		usuarioAux=null;
		eliminado=true;
	}
	return eliminado;
}

public  static void aniadirValoracionAHistorial(double valoracion, Usuario usuario) {
	ArrayList auxiliar = usuario.getHistorialValoraciones();
	auxiliar.add(valoracion);
	usuario.setHistorialValoraciones(auxiliar);
}


}