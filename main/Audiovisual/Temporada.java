package main;

import java.time.LocalDate;
import java.util.ArrayList;

public class temporada {
    private ArrayList<capitulos> capitulos=new ArrayList<>();
    private LocalDate fechaTemporada;;
    boolean enEmision;

	public temporada(ArrayList<main.capitulos> capitulos, LocalDate fechaTemporada,boolean enEmision) {
		
		this.capitulos = capitulos;
		this.fechaTemporada=fechaTemporada;
		this.enEmision=enEmision;
	}

	public LocalDate getFechaTemporada() {
		return fechaTemporada;
	}

	public void setFechaTemporada(LocalDate fechaTemporada) {
		this.fechaTemporada = fechaTemporada;
	}

	public ArrayList<capitulos> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(ArrayList<capitulos> capitulos) {
		this.capitulos = capitulos;
	}
    
    public boolean isEnEmision() {
		return enEmision;
	}

	public void setEnEmision(boolean enEmision) {
		this.enEmision = enEmision;
	}

	public int getNumeroDeCapitulos() {
    	int numeroDeCapitulos=0;
		for (capitulos capitulos : capitulos) {
			numeroDeCapitulos++;
		}
		return numeroDeCapitulos;

	}
    
}
