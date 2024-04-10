


package audiovisual;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;  
import java.time.LocalTime;
import java.util.ArrayList;

import enums.Genero;
import trabajadores.Actor;
import trabajadores.Director;


 
public class Serie extends Audiovisual {
    private ArrayList<Temporada> temporadas=new ArrayList<>();
 
	public Serie(String titulo, Genero genero, ArrayList<Double> valoraciones, LocalDate fecha, LocalTime duracion,Director director,ArrayList<Actor> actores,
			ArrayList<Temporada> temporadas) {
		super(titulo, genero, valoraciones, fecha, duracion, director, actores);
		this.temporadas = temporadas;
	}
	public ArrayList<Temporada> getTemporadas() {
		return temporadas;
	}
	public void setTemporadas(ArrayList<Temporada> temporadas) {
		this.temporadas = temporadas;
	}
	public int getNumeroDeTemporadas() {
		int numeroTemporadas=0;
		for (Temporada temporada : temporadas) {
			numeroTemporadas++;
		}
		return numeroTemporadas;
	}
	public int getCapitulosTotales() {
		int numeroCapitulos=0;
		for (Temporada temporada : temporadas) {
			numeroCapitulos+=temporada.getNumeroDeCapitulos();
		}
		return numeroCapitulos;
	}

	public void aniadirPeliculaBaseDatos() {
		String url = "jdbc:oracle:thin:@localhost:1521:XE"; 
		try (Connection conn = DriverManager.getConnection(url, "System", "Admin2023")) {		
			String sql = "INSERT INTO ContenidoAudiovisual(Titulo, Genero, ValoracionMedia, Fecha, Duracion, Director, Actores, NumeroDeTemporadas, CapitulosTotales) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, getTitulo());
//				pstmt.setString(2, getGenero()); 
				pstmt.setDouble(3, getMediaValoraciones()); 
//				pstmt.setDate(4, getFecha()); 
//				pstmt.setTime(5, getDuracion()); 
				pstmt.setString(6, getDirector().getNombre()+""+getDirector().getApellido());
				pstmt.setString(7, nombreActores()); 
				pstmt.setInt(8, getNumeroDeTemporadas()); 
				pstmt.setInt(9, getCapitulosTotales()); 


				pstmt.executeUpdate();
				System.out.println("Sentencia ejecutada correctamente.");
			} 
		} catch (SQLException e) {
			System.out.println("Error al ejecutar la sentencia: " + e.getMessage());
		}
	}

	
}

