package usuarios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Administrador extends Persona {
	//Att
	private int idAdmin;//Att unico, no modificable e incremental
	private static int contadorIdAdmin=0;
	
	//Meth
		//Const
	public Administrador(String nombre, String contrasenia) {
		super(nombre, contrasenia);
		this.idAdmin=contadorIdAdmin;
		contadorIdAdmin++;
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