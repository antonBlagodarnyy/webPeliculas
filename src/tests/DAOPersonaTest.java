package tests;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

import dao.DAOPersona;
import usuarios.Administrador;
import usuarios.Usuario;

public class DAOPersonaTest {

    @Test
    public void testGetUsuario() {
        // Datos de prueba
        DAOPersona.usuarioBD.clear(); // Limpia datos anteriores
        DAOPersona.usuarioBD.add(new Usuario(1, "John", "password1", false));
        DAOPersona.usuarioBD.add(new Usuario(2, "Jane", "password2", true));

        // Test case 1: ID Valida
        Usuario expectedUser1 = new Usuario(1, "John", "password1", false);
        Usuario actualUser1 = DAOPersona.getUsuario(1);
        assertEquals(expectedUser1, actualUser1);

        // Test case 2: ID No existente
        Usuario actualUser2 = DAOPersona.getUsuario(3);
        assertNull(actualUser2);
    }
    
    @Test
    public void testGetAdmin() {
        // Mock data
        DAOPersona.adminBD.clear(); // Clearing existing data
        DAOPersona.adminBD.add(new Administrador(1, "John", "password1"));
        DAOPersona.adminBD.add(new Administrador(2, "Jane", "password2"));

        // Test case 1: Valid admin ID
        Administrador expectedAdmin1 = new Administrador(1, "John", "password1");
        Administrador actualAdmin1 = DAOPersona.getAdmin(1);
        assertEquals(expectedAdmin1, actualAdmin1);

        // Test case 2: Non-existent admin ID
        Administrador actualAdmin2 = DAOPersona.getAdmin(3);
        assertNull(actualAdmin2);
    }

        @Test
        public void testRegistrarse() {
            // Mock data
            DAOPersona.usuarioBD.clear(); // Clearing existing data
            Usuario existingUser = new Usuario(1, "John", "password1", false);
            DAOPersona.usuarioBD.add(existingUser);

            // Test case 1: User with same username already exists
            Usuario newUser1 = new Usuario(2, "John", "password2", false);
            Usuario result1 = DAOPersona.registrarse(newUser1);
            assertNull("User with same username should not be registered", result1);

            // Test case 2: User with same password already exists
            Usuario newUser2 = new Usuario(3, "Jane", "password1", false);
            Usuario result2 = DAOPersona.registrarse(newUser2);
            assertNull("User with same password should not be registered", result2);

            // Test case 3: New user registered successfully
            Usuario newUser3 = new Usuario(4, "Alice", "password3", false);
            Usuario result3 = DAOPersona.registrarse(newUser3);
            assertEquals("New user should be registered", newUser3, result3);
        }


}

    




