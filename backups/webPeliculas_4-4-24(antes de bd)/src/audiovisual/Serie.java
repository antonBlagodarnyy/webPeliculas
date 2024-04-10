


package audiovisual;

import java.time.LocalDate;  
import java.time.LocalTime;
import java.util.ArrayList;

import enums.Genero;


 
public class Serie extends Audiovisual {
    private ArrayList<Temporada> temporadas=new ArrayList<>();
 
	public Serie(String titulo, Genero genero, ArrayList<Double> valoraciones, LocalDate fecha, LocalTime duracion,
			ArrayList<Temporada> temporadas) {
		super(titulo, genero, valoraciones, fecha, duracion);
		this.temporadas = temporadas;
	}
	public ArrayList<Temporada> getTemporadas() {
		return temporadas;
	}
	public void setTemporadas(ArrayList<Temporada> temporadas) {
		this.temporadas = temporadas;
	}
	public int getNumeroDeTemporadas() {
		int numeroTemporadas=0;
		for (Temporada temporada : temporadas) {
			numeroTemporadas++;
		}
		return numeroTemporadas;
	}
	

}
