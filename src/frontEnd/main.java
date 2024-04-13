package frontEnd;

import java.util.HashMap;

import backEnd.dao.DAOPersona;
import backEnd.usuarios.Administrador;
import backEnd.usuarios.Persona;
import backEnd.usuarios.Usuario;

public class main {
	public static void main(String[] args) {

		Persona personaAutenticada = inicioSesion(); // Variable para almacenar la persona autenticada
		
		if (personaAutenticada instanceof Usuario) {//Se comprueba el baneo antes de acceder al menu
			Usuario usuarioAux = (Usuario) personaAutenticada;
			if(usuarioAux.isBaneado())
				System.out.println("Usted esta baneado!");
			else InterfazUsuario.menu(usuarioAux);
			
		} else {//Se accede al menu directamente
			Administrador adminAux = (Administrador) personaAutenticada;
			InterfazAdministrador.menu(adminAux);
		
		}
	}

	private static Persona inicioSesion() {
		Persona personaAutenticada = null;
		
		MenuUsuario menu = new MenuUsuario("Escoja una opcion:");
		menu.addOpcion("Login");
		menu.addOpcion("Registrarse");
		boolean salir = false;

		//Bucle del login
		while(personaAutenticada==null) {
			int opcionElegida= menu.elegirOpcion();
			
			if(opcionElegida==1) {//Inicio sesion
				personaAutenticada=autentificarse();
				System.out.println(personaAutenticada);
				
			} else {//Registro
				personaAutenticada=registrarUsuario();
				System.out.println(personaAutenticada);
			
		}
	}
		return  personaAutenticada;
	}

	/**
	 * Busca ese login y esa contraseña en la bd. Si no se encuentra, devuelve nulo
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
				personaAutenticada= adminAutenticado;

			}
		} else {
			System.out.println("Nombre de usuario o contraseña incorrectos. Por favor, inténtelo de nuevo.");
		}
		return personaAutenticada;
	}

	private static Usuario registrarUsuario() {
		String nombre = MenuUsuario.leerCadena("Introduzca su nombre");
		String contrasenia = MenuUsuario.leerCadena("Introduzca su contrasenia.");

		Usuario nuevoUsuario = new Usuario(Persona.idDisponible(),nombre, contrasenia,false); // Obtener los datos del nuevo usuario
		Usuario usuarioRegistrado = Usuario.registrarse(nuevoUsuario); // Registrar al nuevo usuario
	
		if (usuarioRegistrado != null) {
			System.out.println("¡Registro exitoso! Bienvenido, " + usuarioRegistrado.getNombre() + ".");
		} else {
			System.out.println("El nombre de usuario o la contraseña ya están registrados. Por favor, elija otro.");
		}
		return usuarioRegistrado;
	}
	
	}

