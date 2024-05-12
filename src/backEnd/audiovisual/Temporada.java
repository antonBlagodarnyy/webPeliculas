package backEnd.audiovisual;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Temporada {
	private ArrayList<Capitulos> capitulos=new ArrayList<>();
	private LocalDate fechaTemporada;
	

	public Temporada(ArrayList<Capitulos> capitulos, LocalDate fechaTemporada) {

		this.capitulos = capitulos;
		this.fechaTemporada=fechaTemporada;
		
	}

	public LocalDate getFechaTemporada() {
		return fechaTemporada;
	}

	public void setFechaTemporada(LocalDate fechaTemporada) {
		this.fechaTemporada = fechaTemporada;
	}

	public ArrayList<Capitulos> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(ArrayList<Capitulos> capitulos) {
		this.capitulos = capitulos;
	}

	public int getNumeroDeCapitulos() {
		int numeroDeCapitulos=0;
		for (Capitulos capitulos : capitulos) {
			numeroDeCapitulos++;
		}
		return numeroDeCapitulos;

	}
	
	public LocalTime duracionTemporada() {
		LocalTime duracionTemporada = null;
		for (Capitulos capitulo : capitulos) {
			duracionTemporada.plusHours(capitulo.getDuracionCapitulo().getHour());
			duracionTemporada.plusMinutes(capitulo.getDuracionCapitulo().getMinute());	
		}
		return duracionTemporada;

	}

}
