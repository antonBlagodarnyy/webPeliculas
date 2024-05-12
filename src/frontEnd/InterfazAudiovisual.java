package frontEnd;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import backEnd.audiovisual.Audiovisual;
import backEnd.audiovisual.Capitulos;
import backEnd.audiovisual.Pelicula;
import backEnd.audiovisual.Serie;
import backEnd.audiovisual.Temporada;
import backEnd.dao.DAOAudiovisual;
import backEnd.dao.DAOPersona;
import backEnd.enums.Genero;
import backEnd.usuarios.Administrador;
import backEnd.usuarios.Persona;
import backEnd.usuarios.Usuario;

import backEnd.utilidad.Teclado;
import backEnd.utilidad.Utilidad;

public abstract class InterfazAudiovisual {
	// Atributo
	private static Persona usuario;
	private static int  audiovisual;
	private static boolean fav;

	private static boolean exit = false;

	/**
	 * Muestra el menú de opciones para un elemento audiovisual específico y gestiona las acciones correspondientes según la selección del usuario.
	 * El menú incluye opciones como reproducir el elemento audiovisual, añadirlo o eliminarlo de favoritos, y salir.
	 * 
	 * @param persona La persona que está accediendo al menú.
	 * @param av El ID del elemento audiovisual que se está visualizando.
	 */
	public static void menuAudiovisual(Persona persona, int av) {
		do {	
		usuario = Usuario.buscarPersona(persona.getId());
		audiovisual = av;
		fav=usuario.favorito(av);

			System.out.println(audiovisual);
			
			if(fav)
			System.out.println("Favorito: Si");
			else
				System.out.println("Favorito: No");

			MenuUsuario menu = new MenuUsuario("¿Qué desea hacer?");
			menu.addOpcion("Reproducir");
			if(!fav)
				menu.addOpcion("Añadir a favoritos");
			else
				menu.addOpcion("Eliminar de favoritos");

			menu.addOpcion("Salir");

			int opcion = menu.elegirOpcion();
			
			
			switch (opcion) {
			case 1:
				reproducir();
				break;
				
			case 2:
				usuario.switchFavorito(audiovisual);
				break;
				
			case 3:
				exit = true;
				break;
				
			default:
				System.out.println("Error: Opción inválida.");
			}
		} while (!exit);
	}

	/**
	 * Reproduce el elemento audiovisual actual y registra la reproducción en el historial de la persona.
	 */
	private static void reproducir() {
		// Implementa la lógica para reproducir el audiovisual
		System.out.println("Reproduciendo: " + audiovisual);
		DAOPersona.insertHistorial(usuario, audiovisual);
		DAOPersona.actualizarListaPersonas();
	}


}
