package main;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Audiovisual {
	private int id;
	private String titulo;
	private String genero;
	private double valoracion;
    private LocalDate fecha;
    private LocalTime duracion;
    private static int numeroDeContenidos=1;
	public Audiovisual(String titulo, String genero, double valoracion, LocalDate fecha, LocalTime duracion) {
		super();
		this.id =  numeroDeContenidos;
		this.titulo = titulo;
		this.genero = genero;
		this.valoracion = valoracion;
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
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public double getValoracion() {
		return valoracion;
	}
	public void setValoracion(double valoracion) {
		if(valoracion>=0&&valoracion<=10)
		this.valoracion = valoracion;
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
    
}
