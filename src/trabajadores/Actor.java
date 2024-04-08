package trabajadores;

import java.util.ArrayList;

import audiovisual.Audiovisual;

public class Actor extends Trabajador{
ArrayList<Audiovisual>contenidoActuado=new ArrayList<>();

public Actor(String nombre, String apellido, int edad, double sueldo, ArrayList<Audiovisual> contenidoActuado) {
	super(nombre, apellido, edad, sueldo);
	this.contenidoActuado = contenidoActuado;
}

public ArrayList<Audiovisual> getContenidoActuado() {
	return contenidoActuado;
}

public void setContenidoActuado(ArrayList<Audiovisual> contenidoActuado) {
	this.contenidoActuado = contenidoActuado;
}



}