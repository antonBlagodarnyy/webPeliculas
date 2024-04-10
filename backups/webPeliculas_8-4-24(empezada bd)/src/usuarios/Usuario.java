package usuarios;

import audiovisual.Audiovisual;

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
	//Mandar a anton
	public void aniadirFavorito(Audiovisual audiovisual) {
		boolean repetido=false;
		for (Audiovisual favorito : getFavoritos()) {
			if(favorito.getId()==audiovisual.getId()) {
				repetido=true;
			}
			if(!repetido) {
				getFavoritos().add(audiovisual);
			}
		}

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
		
}