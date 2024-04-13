package backEnd.usuarios;

import backEnd.audiovisual.Audiovisual;
import backEnd.dao.DAOPersona;

public class Usuario extends Persona{
	//Att
	private int idUsuario;
	private boolean baneado;
	
	//Meth
		//Const
	public Usuario(int idUsuario, String nombre, String contrasenia,boolean baneado) {
		super(nombre, contrasenia);
		this.baneado=baneado;
		this.idUsuario=idUsuario;

	}
	public Usuario() {
		
	}

	public static Usuario registrarse(Usuario usuario) {
    String nombre = usuario.getNombre();
    String contrasenia = usuario.getContrasenia();
    boolean repe=false;
    // Check if the username or password is already registered
    for (Usuario usuarioAux : DAOPersona.usuarios.values()) {
        if (usuarioAux.getNombre().equals(nombre) || usuarioAux.getContrasenia().equals(contrasenia)) {
        repe=true;
        break;
        }
    }
    if(repe)
    	usuario=null;
    else {
    	DAOPersona.insertUsuario(usuario);
    	DAOPersona.actualizarListaPersonas();;
    }

    return usuario;
}


		//GetnSet
	public boolean isBaneado() {
		return baneado;
	}
	public void setBaneado(boolean baneado) {
		this.baneado = baneado;
	}
	

		public int getIdUsuario() {
		return idUsuario;
	}
		@Override
		public String toString() {
			String output =super.toString();
			return output+=" +idUsuario: "+idUsuario;
		}
		
}