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
		
		Usuario user1 = new Usuario("user1","pass1");
		System.out.println(DAOPersona.registrarse(user1));
		//System.out.println(DAOPersona.eliminarUsuario(0));
		Usuario user2 = new Usuario("user1","pass1");
		System.out.println(DAOPersona.registrarse(user2));
		System.out.println(DAOPersona.registrarse(user1));
		
		ArrayList<Actor> actores = new ArrayList<>();
		actores.add(new Actor("John", "Travolta", 67, 1000000.00, new ArrayList<>()));
		actores.add(new Actor("Uma", "Thurman", 51, 1500000.00, new ArrayList<>()));

		Pelicula pulpFiction = new Pelicula("Pulp Fiction", 
				Genero.ACCION,
				new ArrayList<>(Arrays.asList(8.9)), 
				LocalDate.of(1994, 10, 14),
				LocalTime.of(2, 34),
				new Director("Quentin", "Tarantino",39,100000), 
				actores);

		

	
	}

}
