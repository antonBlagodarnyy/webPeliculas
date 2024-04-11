package usuarios;

import audiovisual.Audiovisual;

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
		@Override
		public String toString() {
			String output =super.toString();
			return output+=" +idUsuario: "+idUsuario;
		}
		
}