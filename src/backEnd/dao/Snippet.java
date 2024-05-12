//package dao;
//
//import java.sql.Connection;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//
//import audiovisual.Audiovisual;
//import usuarios.Persona;
//import usuarios.Administrador;
//import usuarios.Usuario;
//
//public class Snippet {
//	
//	public static List<Usuario> selectCoche(){
//		List<Usuario> resultado=new LinkedList<Usuario>();
//		// Configuración de la conexión a la base de datos Oracle XE
//		String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL de conexión
//	// Intenta establecer la conexión
//		try (Connection conn = DriverManager.getConnection(url, "C##nuevo_usuario", "asd");
//		 //Indicamos usuario y contraseña
//	     	Statement stmt = conn.createStatement()) {
//		  // Declaración de la sentencia SQL
//	    	String sql = "SELECT * FROM coche";
//	    	// Ejecutar la sentencia SQL
//	    	ResultSet rs=stmt.executeQuery(sql);
//	    	while (rs.next()) {
//				String matricula=rs.getString("matricula");
//				String marca=rs.getString("marca");
//				String color=rs.getString("color");
//				resultado.add(new Usuario(matricula, color, marca));
//			}
//	    	System.out.println("Sentencia ejecutada correctamente.");
//		} catch (SQLException e) {
//	    	// Manejo de excepciones
//	    	System.out.println("Error al ejecutar la sentencia: " + e.getMessage());
//		}
//		return resultado;
//	}
//	
//	public static void insertCoche(Usuario Usuario) {
//		// Configuración de la conexión a la base de datos Oracle XE
//		String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL de conexión
//	// Intenta establecer la conexión
//		try (Connection conn = DriverManager.getConnection(url, "C##nuevo_usuario", "asd");
//		 //Indicamos usuario y contraseña
//	     	Statement stmt = conn.createStatement()) {
//		  // Declaración de la sentencia SQL
//	    	String sql = "INSERT INTO Usuario (matricula, marca, color) "
//	   			 + "VALUES ('"+Usuario.getMatricula()+"', '"+Usuario.getMarca()+"', '"+Usuario.getColor()+"')";
//	    	// Ejecutar la sentencia SQL
//	    	stmt.execute(sql);
//	    	System.out.println("Sentencia ejecutada correctamente.");
//		} catch (SQLException e) {
//	    	// Manejo de excepciones
//	    	System.out.println("Error al ejecutar la sentencia: " + e.getMessage());
//		}
//	}
//	
//	public static void deleteCoche(Usuario usuario) {
//		// Configuración de la conexión a la base de datos Oracle XE
//		String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL de conexión
//	// Intenta establecer la conexión
//		try (Connection conn = DriverManager.getConnection(url, "C##nuevo_usuario", "asd");
//		 //Indicamos usuario y contraseña
//	     	Statement stmt = conn.createStatement()) {
//		  // Declaración de la sentencia SQL
//	    	String sql = "DELETE from coche where matricula='"+usuario.getMatricula()+"' ";
//	    	// Ejecutar la sentencia SQL
//	    	stmt.execute(sql);
//	    	System.out.println("Sentencia ejecutada correctamente.");
//		} catch (SQLException e) {
//	    	// Manejo de excepciones
//	    	System.out.println("Error al ejecutar la sentencia: " + e.getMessage());
//		}
//	}
//	
//	public static void updateCoche(String matricula, String color) {
//		//indexOf
//	}
//}
package backEnd.dao;
