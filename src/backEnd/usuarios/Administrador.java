package backEnd.usuarios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import backEnd.dao.DAOPersona;

public class Administrador extends Persona {
	// Att
	private int idAdmin;// Att unico, no modificable e incremental

	// Meth
	// Const
	public Administrador(int idAdmin, String nombre, String contrasenia) {
		super(nombre, contrasenia);
		this.idAdmin = idAdmin;
	}
	public Administrador() {
		super();
	}
	
	/**
	 * Crea un nuevo objeto Administrador con la información proporcionada y carga su historial y favoritos desde la base de datos.
	 * 
	 * @param idUsuario    El ID del administrador.
	 * @param nombre       El nombre del administrador.
	 * @param contrasenia  La contraseña del administrador.
	 * @return Un objeto Administrador creado con la información proporcionada y su historial y favoritos cargados desde la base de datos.
	 */
	public static Administrador crearAdministrador(int idUsuario, String nombre, String contrasenia) {
		Administrador adminAux = new Administrador(idUsuario, nombre, contrasenia);
		adminAux.setHistorialReproducciones(DAOPersona.getHistorial(idUsuario));
		adminAux.setFavoritos(DAOPersona.getFavoritos(idUsuario));
		return adminAux;
	}
	
	
	// GetnSet
	@Override
    public int getId() {
        return idAdmin;
    }

	
	//toString
	@Override
	public String toString() {
		String output = super.toString();
		return output += "idAdmin: " + idAdmin;
	}

	
	//Funcionalidades
		/**
		 * Metodo utilizado para banear o quitar baneos a usuarios
		 * 
		 * @param usuario
		 * @return estado del boolean baneado
		 *@author antonBlagodarnyy
		 */
		public boolean switchDeBaneoUsuario(int idUsuario) {
			boolean toggle = false;
			int ban;
			Usuario usuarioAux = DAOPersona.usuarios.get(idUsuario); // Get the user from the HashMap by ID
			if (usuarioAux != null) {
				// Toggle the 'baneado' status
				toggle = !usuarioAux.isBaneado();
				if (toggle)
					ban = 1;
				else
					ban = 0;
				DAOPersona.updateUsuarioBan(usuarioAux, ban);
				usuarioAux.setBaneado(toggle);
			} else {
				// User with the given ID does not exist
				System.out.println("Usuario no encontrado.");
			}
			return toggle;
		}
		/**
		 * Elimina la cuenta de un usuario de la bd
		 * 
		 * @param idUsuario
		 * @return true si se ha eliminado
		 *@author antonBlagodarnyy
		 */
		public boolean eliminarUsuario(int idUsuario) {
			Usuario usuarioAux = DAOPersona.usuarios.remove(idUsuario); // Remove the user from the HashMap by ID
			boolean borrar;
			if (usuarioAux != null) {
				DAOPersona.deleteHistorial(usuarioAux);
				DAOPersona.deleteFavoritos(usuarioAux);	
				DAOPersona.deleteUsuario(usuarioAux);
				
				borrar = true;
			} else {

				System.out.println("Usuario no encontrado.");
				borrar = false;
			}
			DAOPersona.actualizarListaPersonas();
			return borrar;
		}
}