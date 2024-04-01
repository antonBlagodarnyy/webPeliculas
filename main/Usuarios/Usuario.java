package main;

public class Usuario extends Persona{
	//Att
	private int idUsuario;//Att unico, no modificable e incremental
	private static int contadorIdUsuario=0;
	private boolean baneado;
	
	//Meth
		//Const
	public Usuario(String nombre, String contrasenia) {
		super(nombre, contrasenia);
		baneado=false;
		this.idUsuario=contadorIdUsuario;
		contadorIdUsuario++;
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

	//Deriv
	/**Comprueba que no haya un usuario y una constrasenia ya en el DAO
	 * Si es asi, agrega el nuevo usuario
	 * Si no, devuelve null
	 * 
	 * @param usuario
	 * @param contrasenia
	 * @return usuario
	 */
	public static Usuario registrarse(String usuario, String contrasenia) {
		//TODO Agregar DAOUsuario
		//Hace las comprobaciones
		//Aniade un usuario a la bd
		return null;
	}
	
}