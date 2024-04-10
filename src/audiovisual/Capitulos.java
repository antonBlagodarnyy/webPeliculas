package audiovisual;

import java.time.LocalTime;

public class Capitulos {
    private String tituloCapitulo;
    private LocalTime duracionCapitulo;
	public Capitulos(String tituloCapitulo, LocalTime duracionCapitulo) {
		super();
		this.tituloCapitulo = tituloCapitulo;
		this.duracionCapitulo = duracionCapitulo;
	}
	public String getTituloCapitulo() {
		return tituloCapitulo;
	}
	public void setTituloCapitulo(String tituloCapitulo) {
		this.tituloCapitulo = tituloCapitulo;
	}
	public LocalTime getDuracionCapitulo() {
		return duracionCapitulo;
	}
	public void setDuracionCapitulo(LocalTime duracionCapitulo) {
		this.duracionCapitulo = duracionCapitulo;
	}
    
}
