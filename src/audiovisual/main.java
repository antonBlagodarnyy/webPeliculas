package audiovisual;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import dao.DAOPersona;
import enums.Genero;
import trabajadores.Actor;
import trabajadores.Director;
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
		
		//Test2 (getListaUsuarios de la bd)
		ArrayList<Usuario> getListaUsuariosTest = DAOPersona.getListaUsuarios();

		for (Usuario usuario : getListaUsuariosTest) {
			if(usuario!=null)
			System.out.println(usuario);
		}
	}

}
