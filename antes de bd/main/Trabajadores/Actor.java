package main;

import java.util.ArrayList;

public class Actor extends Trabajador{
ArrayList<audiovisual>contenidoActuado=new ArrayList<>();

public Actor(String nombre, String apellido, int edad, double sueldo, ArrayList<audiovisual> contenidoActuado) {
	super(nombre, apellido, edad, sueldo);
	this.contenidoActuado = contenidoActuado;
}

public ArrayList<audiovisual> getContenidoActuado() {
	return contenidoActuado;
}

public void setContenidoActuado(ArrayList<audiovisual> contenidoActuado) {
	this.contenidoActuado = contenidoActuado;
}



}
