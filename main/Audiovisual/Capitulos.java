package main;

import java.time.LocalTime;

public class capitulos {
    private String tituloCapitulo;
    private LocalTime duracionCapitulo;
	public capitulos(String tituloCapitulo, LocalTime duracionCapitulo) {
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
