package usuarios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
	
	@Override
	public String toString() {
		String output =super.toString();
		return output+=" +idAdmin: "+idAdmin;
	}

}