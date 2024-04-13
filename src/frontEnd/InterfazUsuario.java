package frontEnd;

import backEnd.usuarios.Administrador;
import backEnd.usuarios.Usuario;

public class InterfazUsuario {

	//Atributo
	private static Usuario usuario;
	
	public static void menu(Usuario usuario) {
		usuario=usuario;
		MenuUsuario menu = new MenuUsuario("Que desea hacer?");
		menu.addOpcion("Ver informacion del usuario");
		menu.addOpcion("Buscador de peliculas.");
		menu.addOpcion("Buscador de series.");
	
		menu.elegirOpcion();
	}
}
