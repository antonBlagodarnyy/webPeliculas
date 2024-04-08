package audiovisual;

import java.time.LocalDate;
import java.util.ArrayList;

public class Temporada {
    private ArrayList<Capitulos> capitulos=new ArrayList<>();
    private LocalDate fechaTemporada;;
    boolean enEmision;

	public Temporada(ArrayList<Capitulos> capitulos, LocalDate fechaTemporada,boolean enEmision) {
		
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

	public ArrayList<Capitulos> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(ArrayList<Capitulos> capitulos) {
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
		for (Capitulos capitulos : capitulos) {
			numeroDeCapitulos++;
		}
		return numeroDeCapitulos;

	}
    
}
