package audiovisual;

import dao.DAOPersona;
import usuarios.Usuario;
public class main {

	public static void main(String[] args) {
		
		Usuario user1 = new Usuario("user1","pass1");
		System.out.println(DAOPersona.registrarse(user1));
		//System.out.println(DAOPersona.eliminarUsuario(0));
		Usuario user2 = new Usuario("user1","pass1");
		System.out.println(DAOPersona.registrarse(user2));
		System.out.println(DAOPersona.registrarse(user1));
	}

}
