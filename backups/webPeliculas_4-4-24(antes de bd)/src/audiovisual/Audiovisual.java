package audiovisual;

import java.time.LocalDate; 
import java.time.LocalTime;
import java.util.ArrayList;

import enums.Genero;



public abstract class Audiovisual {
	public static final int VALORACION_MINIMA=0, VALORACION_MAXIMA=5;
	
	private int id;
	private String titulo;
	private Genero genero;
	private ArrayList<Double> valoraciones;
    private LocalDate fecha;
    private LocalTime duracion;
    private static int numeroDeContenidos=1;
    
	public Audiovisual(String titulo, Genero genero, ArrayList<Double> valoraciones, LocalDate fecha, LocalTime duracion) {
		super();
		this.id =  numeroDeContenidos;
		this.titulo = titulo;
		this.genero = genero;
		this.valoraciones = valoraciones;
		this.fecha = fecha;
		this.duracion = duracion;
		numeroDeContenidos++;
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
	public int getId() {
		return id;
	}
	
	//Att derivado
	
	public double getMediaValoraciones() {
		double media=0;
		int sumatorio=0;
		for (Double double1 : valoraciones) {
			sumatorio+=double1;
			
		}
		media=sumatorio/valoraciones.size();
		return media;
	}
    
}
