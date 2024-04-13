package backEnd.jUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import backEnd.audiovisual.Audiovisual;
import backEnd.audiovisual.Pelicula;
import backEnd.audiovisual.Serie;
import backEnd.dao.DAOPersona;
import backEnd.enums.Genero;
import backEnd.usuarios.Administrador;
import backEnd.usuarios.Usuario;

public class DAOPersonaTestJUnit {


	@Test
	public void testRegistrarse() {
		// Mock data
		DAOPersona.usuarios.clear(); // Clearing existing data
		Usuario existingUser = new Usuario(1, "John", "password1", false);
		DAOPersona.usuarios.put(1, existingUser);

		// Test case 1: User with same username already exists
		Usuario newUser1 = new Usuario(2, "John", "password2", false);
		Usuario result1 = Usuario.registrarse(newUser1);
		assertNull("User with same username should not be registered", result1);

		// Test case 2: User with same password already exists
		Usuario newUser2 = new Usuario(3, "Jane", "password1", false);
		Usuario result2 = Usuario.registrarse(newUser2);
		assertNull("User with same password should not be registered", result2);

		// Test case 3: New user registered successfully
		Usuario newUser3 = new Usuario(4, "Alice", "password3", false);
		Usuario result3 = Usuario.registrarse(newUser3);
		assertEquals("New user should be registered", newUser3, result3);
	}

	@Test
	public void testEliminarUsuario() {
		// Mock data
		DAOPersona.usuarios.clear(); // Clearing existing data
		DAOPersona.usuarios.put(1, new Usuario(1, "John", "password1", false));

		// Test case 1: Delete an existing user
		boolean result1 = DAOPersona.eliminarUsuario(1);
		assertTrue("User should be deleted", result1);
		assertNull("User should not exist after deletion", DAOPersona.usuarios.get(1));

		// Test case 2: Try to delete a non-existing user
		boolean result2 = DAOPersona.eliminarUsuario(2);
		assertFalse("Deleting non-existing user should return false", result2);
	}

	@Test
	public void testSwitchDeBaneoUsuario() {
		// Mock data
		DAOPersona.usuarios.clear(); // Clearing existing data
		Usuario user1 = new Usuario(1, "John", "password1", false);
		Usuario user2 = new Usuario(2, "Jane", "password2", true);
		DAOPersona.usuarios.put(1, user1);
		DAOPersona.usuarios.put(2, user2);

		// Test case 1: Toggle the 'baneado' status of a user who is not banned
		boolean result1 = Administrador.switchDeBaneoUsuario(1);
		assertTrue("User should be banned", result1);
		assertTrue("User should be banned after toggle", DAOPersona.usuarios.get(1).isBaneado());

		// Test case 2: Toggle the 'baneado' status of a user who is already banned
		boolean result2 = Administrador.switchDeBaneoUsuario(2);
		assertFalse("User should be unbanned now", result2);
		assertFalse("User should be unbanned after toggle", DAOPersona.usuarios.get(2).isBaneado());

		// Test case 3: Toggle the 'baneado' status of a non-existing user
		boolean result3 = Administrador.switchDeBaneoUsuario(3);
		assertFalse("Non-existing user should not be found", result3);
	}

	@Test
	public void testAniadirValoracionAHistorial() {
		// Mock data
		Usuario usuario = new Usuario(1, "John", "password1", false);
		usuario.setHistorialValoraciones(new ArrayList<Double>());

		// Test case: Add a rating to the user's history
		double valoracion = 4.5;
		DAOPersona.aniadirValoracionAHistorial(valoracion, usuario);
		assertTrue("Historial de valoraciones should contain the added rating", usuario.getHistorialValoraciones().contains(valoracion));
	}

	@Test
	public void testAniadirAudiovisualAHistorial() {
		// Mock data
		ArrayList<Double> valoraciones = new ArrayList<>();
		valoraciones.add(4.5);
		valoraciones.add(3.2);
		valoraciones.add(5.0);
		valoraciones.add(2.8);

		ArrayList<String> nombresActores = new ArrayList<>();
		nombresActores.add("Robert Downey Jr.");
		nombresActores.add("Leonardo DiCaprio");
		nombresActores.add("Tom Hanks");
		nombresActores.add("Jennifer Lawrence");
		nombresActores.add("Emma Stone");

		DAOPersona.usuarios.clear(); // Clearing existing data
		Usuario usuario = new Usuario(1, "John", "password1", false);
		DAOPersona.usuarios.put(1, usuario);

		Pelicula pelicula = new Pelicula("Titanic", Genero.DRAMA,valoraciones, LocalDate.of(1997, 12, 1), LocalTime.of(2, 32),"DirectorTitanic",nombresActores);
		//		Serie serie = new Serie("Breaking Bad", "Vince Gilligan", 2008, 5);

		// Test case 1: Add a movie to the user's history
		DAOPersona.aniadirAudiovisualAHistorial(pelicula, usuario);
		assertTrue("Historial de reproducciones should contain the added movie", usuario.getHistorialReproducciones().contains(pelicula));

		// Test case 2: Add a series to the user's history
		//		DAOPersona.aniadirAudiovisualAHistorial(serie, usuario);
		//		assertTrue("Historial de reproducciones should contain the added series", usuario.getHistorialReproducciones().contains(serie));
	}
}







