package backEnd.usuarios;

import java.util.ArrayList;

import backEnd.audiovisual.Audiovisual;
import backEnd.dao.DAOPersona;

public class Usuario extends Persona {
	// Att
	private int idUsuario;
	private boolean baneado;

	// Meth
	// Const
	public Usuario() {

	}
	public Usuario(int idUsuario, String nombre, String contrasenia, boolean baneado) {
		super(nombre, contrasenia);
		this.baneado = baneado;
		this.idUsuario = idUsuario;
	}
	/**
	 * Crea un nuevo objeto Usuario con la información proporcionada y carga sus favoritos y historial desde la base de datos.
	 * 
	 * @param idUsuario               El ID del usuario.
	 * @param nombre                  El nombre del usuario.
	 * @param contrasenia             La contraseña del usuario.
	 * @param ban                     El estado de baneo del usuario (true si está baneado, false si no lo está).
	 * @return Un objeto Usuario creado con la información proporcionada y sus favoritos e historial cargados desde la base de datos.
	 *@author antonBlagodarnyy
	 */
	public static Usuario crearUsuario(int idUsuario, String nombre, String contrasenia, Boolean ban) {
		Usuario usuarioAux = new Usuario(idUsuario, nombre, contrasenia, ban);// Creamos al usuario con esos atributos
		usuarioAux.setFavoritos(DAOPersona.getFavoritos(idUsuario));
		usuarioAux.setHistorialReproducciones(DAOPersona.getHistorial(idUsuario));
		return usuarioAux;
	}	
	

	// GetnSet
	@Override
	public int getId() {
		return idUsuario;
	}
	public boolean isBaneado() {
		return baneado;
	}
	public void setBaneado(boolean baneado) {
		this.baneado = baneado;
	}

	
	//toString
	@Override
	public String toString() {
		String output = super.toString();
		return output += " +idUsuario: " + idUsuario;
	}

	
	//Funcionalidades
	/**
	 * Cambia el nombre de inicio de sesión de un usuario si el nuevo nombre no está en uso.
	 * Si se cambia con éxito, actualiza la lista de usuarios en la base de datos y en el objeto usuario.
	 * 
	 * @param usuario El usuario cuyo nombre de inicio de sesión se cambiará.
	 * @param login   El nuevo nombre de inicio de sesión para el usuario.
	 * @return El nombre de inicio de sesión actualizado del usuario, o el mismo nombre si no se pudo cambiar.
	 * @author antonBlagodarnyy
	 */
	public static String cambiarLogin(Usuario usuario, String login) {
		if (Persona.loginNoEncontrado(login)) {
			DAOPersona.updateUsuarioLogin(usuario, login);
			DAOPersona.actualizarListaPersonas();
			usuario.setNombre(login);
		}
		return usuario.getNombre();
	}
	/**
	 * Cambia la contraseña de un usuario y actualiza la lista de usuarios en la base de datos.
	 * 
	 * @param usuario      El usuario cuya contraseña se cambiará.
	 * @param contrasenia  La nueva contraseña para el usuario.
	 * @return El objeto Usuario con la contraseña actualizada.
	 *@author antonBlagodarnyy
	 */
	public static Usuario cambiarContrasenia(Usuario usuario, String contrasenia) {
		DAOPersona.updateUsuarioPassword(usuario, contrasenia);
		DAOPersona.actualizarListaPersonas();
		usuario.setContrasenia(contrasenia);
		return usuario;
	}
	/**
	 * Registra un nuevo usuario en el sistema si el nombre de usuario y la contraseña no están en uso.
	 * Si el registro es exitoso, el usuario se inserta en la base de datos y se actualiza la lista de usuarios.
	 * 
	 * @param usuario El usuario que se registrará en el sistema.
	 * @return El mismo objeto Usuario si el registro es exitoso, o null si el nombre de usuario o la contraseña ya están en uso.
	 *
	 *@author antonBlagodarnyy
	 */
	public static Usuario registrarse(Usuario usuario) {
		String nombre = usuario.getNombre();
		String contrasenia = usuario.getContrasenia();
		boolean repe = false;
		// Checkea que no se encuentre un usuario con ese nombre o contrasenia
		for (Usuario usuarioAux : DAOPersona.usuarios.values()) {
			if (usuarioAux.getNombre().equals(nombre) || usuarioAux.getContrasenia().equals(contrasenia)) {
				repe = true;
				break;
			}
		}
		for (Administrador usuarioAux : DAOPersona.admins.values()) {
			if (usuarioAux.getNombre().equals(nombre) || usuarioAux.getContrasenia().equals(contrasenia)) {
				repe = true;
				break;
			}
		}
		if (repe)
			usuario = null;
		else {
			DAOPersona.insertUsuario(usuario);
			DAOPersona.actualizarListaPersonas();

		}

		return usuario;
	}
}