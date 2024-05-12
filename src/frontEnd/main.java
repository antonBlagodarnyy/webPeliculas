package frontEnd;


import java.util.HashMap;

import backEnd.dao.DAOAudiovisual;
import backEnd.dao.DAOPersona;
import backEnd.usuarios.Administrador;
import backEnd.usuarios.Persona;
import backEnd.usuarios.Usuario;
import frontEnd.InterfazAdministrador;
import frontEnd.InterfazUsuario;
import frontEnd.MenuUsuario;

public class main {
	private static boolean exit=false;

	public static void main(String[] args) {
		crearTabla();
		login();

	}

	private static void crearTabla() {
		DAOAudiovisual.crearTablaAudiovisual();
		DAOPersona.inicializarPersonas();
		DAOPersona.crearTablaHistorial();
		DAOPersona.crearTablaFavoritos();
	}

	/**
	 * Gestiona el proceso de inicio de sesión de un usuario en el sistema.
	 * Actualiza la lista de personas en la base de datos y solicita al usuario que inicie sesión.
	 * Después de la autenticación, redirige al usuario al menú correspondiente (usuario o administrador)
	 * dependiendo del tipo de persona autenticada.
	 * Si el usuario está baneado, se le notifica antes de acceder al menú.
	 */
	private static void login() {
		DAOPersona.actualizarListaPersonas();
		
		while(!exit) {
			Persona personaAutenticada = inicioSesion(); // Variable para almacenar la persona autenticada

			if(personaAutenticada!=null) {

				if (personaAutenticada instanceof Usuario) {// Se comprueba el baneo antes de acceder al menu
					Usuario usuarioAux = (Usuario) personaAutenticada;
					
					if (usuarioAux.isBaneado())
						System.out.println("Usted esta baneado!");
					else
						InterfazUsuario.menuUsuario(usuarioAux);

				} else {// Se accede al menu directamente
					Administrador adminAux = (Administrador) personaAutenticada;
					InterfazAdministrador.menu(adminAux);
				}
			}
		}
	}

	/**
	 * Gestiona el proceso de inicio de sesión o registro de un usuario en el sistema.
	 * Muestra un menú con opciones para iniciar sesión, registrarse o salir.
	 * Después de que el usuario elija una opción, lo redirige al proceso correspondiente.
	 * Si el usuario elige salir, establece el indicador de salida en verdadero.
	 * 
	 * @return La persona autenticada (si se inició sesión correctamente o se registró), o null si se elige salir.
	 */
	private static Persona inicioSesion() {
		Persona personaAutenticada = null;

		MenuUsuario menu = new MenuUsuario("Escoja una opcion:");
		menu.addOpcion("Login");
		menu.addOpcion("Registrarse");
		menu.addOpcion("Salir");

		int opcionElegida = menu.elegirOpcion();

		if(opcionElegida == 3)
			exit=true;

		// Bucle del login
		if(!exit) {		
			if (opcionElegida == 1) {// Inicio sesion
				personaAutenticada = autentificarse();
				if(personaAutenticada!=null)
					System.out.println("Bienvenido "+personaAutenticada);

			} else {// Registro
				personaAutenticada = registrarUsuario();
				if(personaAutenticada!=null)
					System.out.println("Bienvenido "+personaAutenticada);

			}
		}
		return personaAutenticada;
	}

	/**
	 * Busca ese login y esa contraseña en la bd. Si no se encuentra, devuelve nulo
	 * 
	 * @return
	 */
	private static Persona autentificarse() {
		String login = MenuUsuario.leerCadena("Introduzca su nombre de usuario");
		String contrasenia = MenuUsuario.leerCadena("Introduzca su contraseña");

		// Busca la persona en el HashMap de usuarios y administradores
		Persona personaAutenticada = Persona.buscarPersona(login, contrasenia);

		if (personaAutenticada != null) {
			if (personaAutenticada instanceof Usuario) {
				Usuario usuarioAutenticado = (Usuario) personaAutenticada;
				personaAutenticada = usuarioAutenticado;

			} else if (personaAutenticada instanceof Administrador) {
				Administrador adminAutenticado = (Administrador) personaAutenticada;
				personaAutenticada = adminAutenticado;

			}
		} else {
			System.out.println("Nombre de usuario o contraseña incorrectos. Por favor, inténtelo de nuevo.");
		}
		return personaAutenticada;
	}

	/**
	 * Registra un nuevo usuario en el sistema solicitando al usuario que ingrese su nombre y contraseña.
	 * Después de registrar al usuario, muestra un mensaje de éxito o error según corresponda.
	 * 
	 * @return El objeto Usuario registrado si el proceso de registro es exitoso, o null si el nombre de usuario o la contraseña ya están en uso.
	 */
	private static Usuario registrarUsuario() {
		String nombre = MenuUsuario.leerCadena("Introduzca su nombre");
		String contrasenia = MenuUsuario.leerCadena("Introduzca su contrasenia.");

		Usuario nuevoUsuario = new Usuario(Persona.idDisponible(), nombre, contrasenia, false); // Obtener los datos del
		// nuevo usuario
		Usuario usuarioRegistrado = Usuario.registrarse(nuevoUsuario); // Registrar al nuevo usuario

		if (usuarioRegistrado != null) {
			System.out.println("¡Registro exitoso! Bienvenido, " + usuarioRegistrado.getNombre() + ".");
		} else {
			System.out.println("El nombre de usuario o la contraseña ya están registrados. Por favor, elija otro.");
		}
		return usuarioRegistrado;
	}

}
