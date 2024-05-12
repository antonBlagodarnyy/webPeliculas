package backEnd.audiovisual;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;  
import java.time.LocalTime;
import java.util.ArrayList;

import backEnd.enums.Genero;



public class Pelicula extends Audiovisual {

	public Pelicula(String titulo, Genero genero, ArrayList<Double> valoraciones, LocalDate fecha, LocalTime duracion,String director,ArrayList<String> actores) {
		super(titulo, genero, valoraciones, fecha, duracion, director, actores);

	}
}
