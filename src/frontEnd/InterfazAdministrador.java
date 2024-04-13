package frontEnd;

import backEnd.usuarios.Administrador;

public abstract class InterfazAdministrador {
	//Atributo
	private static Administrador administrador;
	
	public static void menu(Administrador admin) {
		administrador=admin;
		MenuUsuario menu = new MenuUsuario("Que desea hacer?");
		menu.addOpcion("Ver informacion del usuario");
		menu.addOpcion("Buscador de peliculas.");
		menu.addOpcion("Buscador de series.");
		menu.addOpcion("Administrar usuarios de la plataforma.");
	
		menu.elegirOpcion();
	}

}
