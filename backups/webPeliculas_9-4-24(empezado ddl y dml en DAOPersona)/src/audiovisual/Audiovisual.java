package audiovisual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate; 
import java.time.LocalTime;
import java.util.ArrayList;

import enums.Genero;
import trabajadores.Actor;
import trabajadores.Director;



public abstract class Audiovisual {
	public static final int VALORACION_MINIMA=0, VALORACION_MAXIMA=5;
	
	private int id;
	private String titulo;
	private Genero genero;
	private ArrayList<Double> valoraciones;
    private LocalDate fecha;
    private LocalTime duracion;
    private Director director;
    private ArrayList<Actor> actores= new ArrayList<Actor>();
    private static int numeroDeContenidos=1;
    private static boolean tablaCreada=false;
    
	public Audiovisual(String titulo, Genero genero, ArrayList<Double> valoraciones, LocalDate fecha, LocalTime duracion,Director director,ArrayList<Actor> actores) {
		super();
		this.id =  numeroDeContenidos;
		this.titulo = titulo;
		this.genero = genero;
		this.valoraciones = valoraciones;
		this.fecha = fecha;
		this.duracion = duracion;
		this.director=director;
		this.actores=actores;
		numeroDeContenidos++;
		if(!tablaCreada) {
			crearTabla();
		}
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public ArrayList<Double> getValoracion() {
		return valoraciones;
	}
	public void setValoracion(ArrayList<Double> valoraciones) {
		this.valoraciones = valoraciones;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getDuracion() {
		return duracion;
	}
	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}
	public int getId() {
		return id;
	}
	
	//Att derivado
	
	public Director getDirector() {
		return director;
	}
	public void setDirector(Director director) {
		this.director = director;
	}
	public ArrayList<Actor> getActores() {
		return actores;
	}
	public void setActores(ArrayList<Actor> actores) {
		this.actores = actores;
	}
	public double getMediaValoraciones() {
		double media=0;
		int sumatorio=0;
		for (Double double1 : valoraciones) {
			sumatorio+=double1;
			
		}
		media=sumatorio/valoraciones.size();
		return media;
	}
    private void crearTabla() {
    	// Configuración de la conexión a la base de datos Oracle XE
    	String url = "jdbc:oracle:thin:@localhost:1521:XE"; // URL de conexión
   	// Intenta establecer la conexión
    	try (Connection conn = DriverManager.getConnection(url, "System", "Admin2023");
   		 //Indicamos usuario y contraseña
         	Statement stmt = conn.createStatement()) {
   		  // Declaración de la sentencia SQL
        	String sql = "CREATE TABLE ContenidoAudiovisual (ID integer by default on null as IDENTITY,"
        			+ "Titulo,"
        			+ "Genero,"
        			+ "ValoracionMedia,"
        			+ "Fecha,"
        			+ "Duracion,"
        			+ "Director,"
        			+ "Actores,"
        			+ "NumeroDeTemporadas,"
        			+ "CapitulosTotales);";
        	// Ejecutar la sentencia SQL
        	stmt.execute(sql);
        	System.out.println("Sentencia ejecutada correctamente.");
    	} catch (SQLException e) {
        	// Manejo de excepciones
        	System.out.println("Error al ejecutar la sentencia: " + e.getMessage());
    	}

    	
	}
   public String nombreActores() {
	String nombre="";
	   for (Actor actor : actores) {
		nombre+=actor.getNombre()+"";
	}
	   return nombre;
}
}
