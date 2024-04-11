package audiovisual;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import dao.DAOPersona;
import enums.Genero;
import trabajadores.Actor;
import trabajadores.Director;
import usuarios.Administrador;
import usuarios.Usuario;
public class main {

	public static void main(String[] args) {
		
		//Tests1 (registrarse y eliminar usuario sin bd)
//		Usuario user1 = new Usuario("user1","pass1");
//		System.out.println(DAOPersona.registrarse(user1));
//		//System.out.println(DAOPersona.eliminarUsuario(0));
//		Usuario user2 = new Usuario("user1","pass1");
//		System.out.println(DAOPersona.registrarse(user2));
//		System.out.println(DAOPersona.registrarse(user1));
		
		//Test2 comprobamos que sacamos los usuarios de la bd;
		for (Usuario usuario : DAOPersona.usuarioBD) {
			if(usuario!=null)
			System.out.println(usuario);
		}
		saltoDeLinea();
		
		//Test3 Comprobamos que sacamos los admin
		for (Administrador admin : DAOPersona.adminBD) {
			if(admin!=null)
			System.out.println(admin);
		}
		saltoDeLinea();
		
		//Test4 getUsuario
		System.out.println(DAOPersona.getUsuario(3));//Coge usuario e
		saltoDeLinea();
		
		//Test5 getAdmin
		System.out.println(DAOPersona.getAdmin(4));
	
	}
	private static void saltoDeLinea() {
		System.out.println();
	}
}
