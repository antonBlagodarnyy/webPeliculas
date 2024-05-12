package frontEnd;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import backEnd.audiovisual.Audiovisual;
import backEnd.audiovisual.Pelicula;
import backEnd.dao.DAOAudiovisual;
import backEnd.dao.DAOPersona;
import backEnd.enums.Genero;
import backEnd.usuarios.Administrador;
import backEnd.usuarios.Persona;
import backEnd.usuarios.Usuario;

public abstract class InterfazUsuario {

	// Atributo
	private static Usuario usuario;
	private static boolean exit;

	/**
	 * Muestra el menú de opciones para un usuario y gestiona las acciones correspondientes según la selección del usuario.
	 * El menú incluye opciones como ver información del usuario, buscar elementos audiovisuales, ver historial de reproducciones,
	 * eliminar historial de reproducciones, cambiar datos del usuario, y salir.
	 * 
	 * @param usuarioNuevo El objeto Usuario que accede al menú.
	 */
	public static void menuUsuario(Usuario usuarioNuevo) {
		exit= false;
		do {
			usuario =(Usuario) Persona.buscarPersona(usuarioNuevo.getId());

			MenuUsuario menu = new MenuUsuario("¿Qué desea hacer?");
			menu.addOpcion("Ver información del usuario");
			menu.addOpcion("Buscador");
			menu.addOpcion("Ver historial de reproducciones");
			menu.addOpcion("Eliminar historial de reproducciones");
			menu.addOpcion("Cambiar datos del usuario");
			menu.addOpcion("Salir");

			int opcion = menu.elegirOpcion();
			switch (opcion) {
			case 1:
				FuncionalidadesComunes.informacionUsuario(usuario);
				break;

			case 2:
				int av = FuncionalidadesComunes.buscadorAV();
				if (av < 0)
					System.out.println("Esa película no existe en la base de datos.");
				else 
					InterfazAudiovisual.menuAudiovisual(usuario, av);
				
				break;

			case 3:
				FuncionalidadesComunes.mostrarHistorialReproducciones(usuario);
				break;

			case 4:
				FuncionalidadesComunes.eliminarHistorialReproducciones(usuario);
				break;

			case 5:
				cambiarDatosUsuarios();
				DAOPersona.actualizarListaPersonas();
				usuario = DAOPersona.usuarios.get(usuarioNuevo.getId());
				break;

			case 6:
				exit=true;
				break;

			default:
				System.out.println("Error en switch");
			}

		} while (!exit);
	}

	/**
	 * Permite al usuario cambiar su nombre de usuario o contraseña según su elección.
	 * 
	 * El método muestra un menú con dos opciones: cambiar login o cambiar contraseña.
	 * Si elige cambiar login, solicita el nuevo nombre de usuario y actualiza la información en la base de datos.
	 * Si elige cambiar contraseña, solicita la nueva contraseña y actualiza la información en la base de datos.
	 */
	private static void cambiarDatosUsuarios() {
		MenuUsuario menu = new MenuUsuario("Escoja una opcion");
		menu.addOpcion("Cambiar login");
		menu.addOpcion("Cambiar contrasenia");
		int opcion = menu.elegirOpcion();

		if (opcion == 1) {
			String nuevoLogin = menu.leerCadena("Introduzca el nuevo login");
			Usuario.cambiarLogin(usuario, nuevoLogin);
			FuncionalidadesComunes.informacionUsuario(usuario);
		} else {
			String nuevaContrasenia = menu.leerCadena("Introduzca la nueva contrasenia");
			Usuario.cambiarContrasenia(usuario, nuevaContrasenia);
		}
	}




}
