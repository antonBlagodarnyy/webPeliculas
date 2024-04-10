package audiovisual;

import java.time.LocalDate;  
import java.time.LocalTime;
import java.util.ArrayList;

import enums.Genero;



public class Pelicula extends Audiovisual {

	public Pelicula(String titulo, Genero genero, ArrayList<Double> valoraciones, LocalDate fecha, LocalTime duracion) {
		super(titulo, genero, valoraciones, fecha, duracion);
		
	}
    
}
