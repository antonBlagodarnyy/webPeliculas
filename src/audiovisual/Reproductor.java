package audiovisual;

import java.util.ArrayList;

import enums.Idioma; 



public class Reproductor {
    ArrayList<Idioma>idiomasDisponibles=new ArrayList<>();
    ArrayList<Idioma>subtitulosDisponibles=new ArrayList<>();
	public Reproductor(ArrayList<Idioma> idiomasDisponibles, ArrayList<Idioma> subtitulosDisponibles) {
		super();
		this.idiomasDisponibles = idiomasDisponibles;
		this.subtitulosDisponibles = subtitulosDisponibles;
	}
	public ArrayList<Idioma> getIdiomasDisponibles() {
		return idiomasDisponibles;
	}
	public void setIdiomasDisponibles(ArrayList<Idioma> idiomasDisponibles) {
		this.idiomasDisponibles = idiomasDisponibles;
	}
	public ArrayList<Idioma> getSubtitulosDisponibles() {
		return subtitulosDisponibles;
	}
	public void setSubtitulosDisponibles(ArrayList<Idioma> subtitulosDisponibles) {
		this.subtitulosDisponibles = subtitulosDisponibles;
	}
    
    
}
