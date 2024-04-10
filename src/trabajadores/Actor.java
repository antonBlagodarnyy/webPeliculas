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


//
//// Declaración de la sentencia SQL para selección
//String selectSql = "SELECT ID, Nombre, Salario FROM Empleados";
//
//// Ejecutar la sentencia SQL para selección
//try (ResultSet rs = stmt.executeQuery(selectSql)) {
//    // Recorre el ResultSet e imprime los datos
//    while (rs.next()) {
//        int id = rs.getInt("ID");
//        String nombre = rs.getString("Nombre");
//        double salario = rs.getDouble("Salario");
//        System.out.println("ID: " + id + ", Nombre: " + nombre + ", Salario: " + salario);
//    }
}