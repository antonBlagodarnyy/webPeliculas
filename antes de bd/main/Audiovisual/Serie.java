package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class serie extends audiovisual {
    private ArrayList<temporada> temporadas=new ArrayList<>();
 
    
	public serie(String titulo, String genero, double valoracion, LocalDate fecha, LocalTime duracion,
			ArrayList<temporada> temporadas) {
		super(titulo, genero, valoracion, fecha, duracion);
		this.temporadas = temporadas;
	}
	public ArrayList<temporada> getTemporadas() {
		return temporadas;
	}
	public void setTemporadas(ArrayList<temporada> temporadas) {
		this.temporadas = temporadas;
	}
	public int getNumeroDeTemporadas() {
		int numeroTemporadas=0;
		for (temporada temporada : temporadas) {
			numeroTemporadas++;
		}
		return numeroTemporadas;
	}
	

}
