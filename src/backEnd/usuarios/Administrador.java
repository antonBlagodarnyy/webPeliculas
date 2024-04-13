package backEnd.usuarios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import backEnd.dao.DAOPersona;

public class Administrador extends Persona {
	//Att
	private int idAdmin;//Att unico, no modificable e incremental
	
	//Meth
		//Const
	public Administrador(int idAdmin, String nombre, String contrasenia) {
		super(nombre, contrasenia);
		this.idAdmin=idAdmin;
	}
	public Administrador() {
		super();
	}
		//GetnSet
	public int getIdAdmin() {
		return idAdmin;
	}
	
	/**
 * Metodo utilizado para banear o quitar baneos a usuarios
 * @param usuario
 * @return estado del boolean baneado
 */
public static boolean switchDeBaneoUsuario(int idUsuario) {
    Usuario usuarioAux = DAOPersona.usuarios.get(idUsuario); // Get the user from the HashMap by ID
    if (usuarioAux != null) {
        // Toggle the 'baneado' status
        boolean nuevoEstado = !usuarioAux.isBaneado();
        usuarioAux.setBaneado(nuevoEstado);
        return nuevoEstado; // Return the new 'baneado' status
    } else {
        // User with the given ID does not exist
        System.out.println("Usuario no encontrado.");
        return false; // Return false indicating failure
    }
}	
	
	@Override
	public String toString() {
		String output =super.toString();
		return output+=" +idAdmin: "+idAdmin;
	}

}