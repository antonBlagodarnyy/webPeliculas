package usuarios;

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

		@Override
		public String toString() {
			String output =super.toString();
			return output+=" +idUsuario: "+idUsuario;
		}
		
}