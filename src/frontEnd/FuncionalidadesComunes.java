package frontEnd;

import backEnd.dao.DAOAudiovisual;
import backEnd.dao.DAOPersona;
import backEnd.usuarios.Persona;

public class FuncionalidadesComunes {
	/**
	 * Muestra la información del historial de reproducciones de una persona.
	 * 
	 * @param persona La persona cuyo historial se mostrará.
	 */
	public static void mostrarHistorialReproducciones(Persona persona) {
		String historial = persona.toStringHistorial();
		System.out.println(historial);
	}

	/**
	 * Elimina el historial de reproducciones de una persona de la base de datos.
	 * 
	 * @param persona La persona cuyo historial se eliminará.
	 */
	public static void eliminarHistorialReproducciones(Persona persona) {
		DAOPersona.deleteHistorial(persona);
	}

	/**
	 * Muestra la información de un usuario, incluyendo su nombre, historial de reproducciones y lista de favoritos.
	 * 
	 * @param persona La persona de la cual se mostrará la información.
	 */
	public static void informacionUsuario(Persona persona) {
		System.out.println(persona.informacionUsuario());
	}

	/**
	 * Solicita al usuario que introduzca el título de un elemento audiovisual y devuelve su ID.
	 * 
	 * @return El ID del elemento audiovisual introducido por el usuario.
	 */
	public static int buscadorAV() {
		String titulo = MenuUsuario.leerCadena("Introduzca el título");
		return DAOAudiovisual.selectAudiovisualId(titulo);
	}

}

