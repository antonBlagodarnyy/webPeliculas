package backEnd.audiovisual;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate; 
import java.time.LocalTime;
import java.util.ArrayList;

import backEnd.enums.Genero;




public abstract class Audiovisual {
	public static final int VALORACION_MINIMA=0, VALORACION_MAXIMA=5;
	    private static int numeroDeContenidos = 1;
	    
	   // private int id;
	    private String titulo;
	    private Genero genero;
	    private ArrayList<Double> valoraciones;
	    private LocalDate fecha;
	    private LocalTime duracion;
	    private String director;
	    private ArrayList<String> actores;
	   

	    public Audiovisual(String titulo, Genero genero, ArrayList<Double> valoraciones, LocalDate fecha, LocalTime duracion, String director, ArrayList<String> actores) {
	        // this.id = numeroDeContenidos;
	        this.titulo = titulo;
	        this.genero = genero;
	        this.valoraciones = valoraciones;
	        this.fecha = fecha;
	        this.duracion = duracion;
	        this.director = director;
	        this.actores = actores;
	       
	    }
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public ArrayList<Double> getValoracion() {
		return valoraciones;
	}
	public void setValoracion(ArrayList<Double> valoraciones) {
		this.valoraciones = valoraciones;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getDuracion() {
		return duracion;
	}
	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}
	
	public double getMediaValoraciones() {
		double media=0;
		int sumatorio=0;
		for (Double double1 : valoraciones) {
			sumatorio+=double1;

		}
		media=sumatorio/valoraciones.size();
		return media;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public ArrayList<String> getActores() {
		return actores;
	}
	public void setActores(ArrayList<String> actores) {
		this.actores = actores;
	}
	




}
