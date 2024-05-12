package frontEnd;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import backEnd.audiovisual.Audiovisual;
import backEnd.audiovisual.Capitulos;
import backEnd.audiovisual.Pelicula;
import backEnd.audiovisual.Serie;
import backEnd.audiovisual.Temporada;
import backEnd.dao.DAOAudiovisual;
import backEnd.dao.DAOPersona;
import backEnd.enums.Genero;
import backEnd.usuarios.Administrador;
import backEnd.usuarios.Persona;
import backEnd.usuarios.Usuario;

import backEnd.utilidad.Teclado;
import backEnd.utilidad.Utilidad;

public abstract class InterfazAdministrador {
	// Atributo
	private static Administrador administrador;
	private static boolean exit;

	/**
	 * Muestra el menú de opciones para un administrador y gestiona las acciones correspondientes según la selección del administrador.
	 * El menú incluye opciones como ver información del usuario, buscar elementos audiovisuales, ver historial de reproducciones,
	 * eliminar historial de reproducciones, administrar usuarios de la plataforma, administrar elementos audiovisuales de la base de datos, y salir.
	 * 
	 * @param admin El objeto Administrador que accede al menú.
	 */
	public static void menu(Administrador admin) {
		exit=false;
		do {
			administrador = (Administrador) Persona.buscarPersona(admin.getId());
			MenuUsuario menu = new MenuUsuario("Que desea hacer?");
			menu.addOpcion("Ver informacion del usuario");
			menu.addOpcion("Buscador");
			menu.addOpcion("Ver historial de reproducciones");
			menu.addOpcion("Eliminar historial de reproducciones");
			menu.addOpcion("Administrar usuarios de la plataforma");
			menu.addOpcion("Administrar audiovisuales de la bd");
			menu.addOpcion("Salir");

			int opcion = menu.elegirOpcion();

			switch (opcion) {
			case 1:
				FuncionalidadesComunes.informacionUsuario(administrador);
				break;
				
			case 2:
				int av = FuncionalidadesComunes.buscadorAV();
				if(av<0)
					System.out.println("Esa pelicula no existe en la bd.");
				else 
					InterfazAudiovisual.menuAudiovisual(administrador, av);
				break;

			case 3:
				FuncionalidadesComunes.mostrarHistorialReproducciones(administrador);
				break;

			case 4:
				FuncionalidadesComunes.eliminarHistorialReproducciones(administrador);
				break;

			case 5:
				administrarUsuarios();
				break;
				
			case 6:
				menuAudiovisual();
				break;

			case 7:
				exit=true;
				break;

			default:
				System.out.println("Error en switch");
			}
		} while (!exit);
	}

	/**
	 * Permite al administrador buscar y administrar usuarios del sistema.
	 * El administrador puede buscar un usuario por su nombre, y luego realizar acciones como cambiar su estado de baneo o eliminarlo.
	 */
	private static void administrarUsuarios() {
		String nombre = MenuUsuario.leerCadena("Introduzca el nombre del usuario a administrar");
		Usuario usuarioAdministrado = Persona.buscarPersona(nombre);

		if(usuarioAdministrado==null) 
			System.out.println("Usuario no encontrado");
		else {
			MenuUsuario menu = new MenuUsuario("Que desea realizar?:");
			menu.addOpcion("Cambiar estado del baneo");
			menu.addOpcion("Eliminar usuario");

			int opcion = menu.elegirOpcion();
			if (opcion == 1)
				administrador.switchDeBaneoUsuario(usuarioAdministrado.getId());
			else
				administrador.eliminarUsuario(usuarioAdministrado.getId());

		}
	}

	/**
	 * Menu para preguntar el proceso que quiere hacer con la tabla ContenidoAudiovisual
	 * @author Antonio Jesus Blanco
	 */
	public static void menuAudiovisual() {

		int opcionMenu=0;

		while(opcionMenu!=6) {

			System.out.println(Texto.TEXTO_MENU);

			//Aqui controlo que no se puede meter un numero que no este entre 1 y 8

			opcionMenu=Teclado.pedirNumeroOpcionMenu();

			switch (opcionMenu) {

			case 1 ->{darCatalogo();}

			case 2 ->{darCatalogoGenero();}

			case 3 ->{AnadirContenidoAudiovisual();}

			case 4 ->{cambiarValoracionAudiovisual();}

			case 5 ->{borrarContenidoAudiovisual();}

			}

		}



	}
	/**
	 * Pide un genero al usuario y permito realizar el select
	 * @author Antonio Jesus Blanco
	 */	
	private static void darCatalogoGenero() {
		System.out.println(Texto.TEXTO_GENERO_SELECT_CONTENIDO);
		String genero=Utilidad.generoCambiarDeNumeroAString(Teclado.pedirNumeroGenero());
		String mensaje=DAOAudiovisual.selectAudiovisualGenero(genero);
		System.out.println(mensaje);
	}

	/**
	 * Este metodo mostrara la tabla de ContenidoAudiovisual en su totalidad
	 * @author Antonio Jesus Blanco
	 */
	public static void darCatalogo() {
		System.out.println(DAOAudiovisual.selectAudiovisual());
	}

	/**
	 * Este método permite cambiar la valoración de un 
	 * mediante un ID en la tabla ContenidoAudiovisual
	 * @author Antonio Jesus Blanco
	 * 
	 */
	private static void cambiarValoracionAudiovisual() {
		Scanner sc=new Scanner(System.in);
		// Solicitar al usuario el título del contenido cuya valoración desea cambiar
		System.out.println(Texto.TEXTO_TITULO_CONTENIDO);
		String titulo=sc.nextLine();
		// Obtener el ID del contenido audiovisual basado en el título proporcionado
		int id=DAOAudiovisual.selectAudiovisualId(titulo);
		// Solicitar al usuario la nueva valoración
		System.out.println(Texto.TEXTO_VALORACION_NUEVA);
		double valoracion=Teclado.pedirNumeroValoracion();
		// Si se encuentra el ID del contenido audiovisual, actualizar su valoración y mostrar el resultado
		if(id!=-1) {
			System.out.println(DAOAudiovisual.updateAudiovisual(id, valoracion));
		}else {
			System.out.println(Texto.TEXTO_ERROR_DELETE_NO_ENCONTRADO);
		}
	}

	/**
	 * Este método permite añadir un nuevo contenido en la tabla ContenidoAudiovisual
	 * @author Antonio Jesus Blanco
	 */
	public static void AnadirContenidoAudiovisual() {
		int opcionAudiovisual=0;
		Scanner sc=new Scanner(System.in);
		// Solicitar al usuario el tipo de contenido audiovisual que desea añadir
		System.out.println(Texto.TEXTO_OPCIONES_CONTENIDO);

		//Segun la opcion que elija sera pelicula o serie
		boolean valido=false;
		while (!valido) {
			opcionAudiovisual=Teclado.pedirNumero();
			switch (opcionAudiovisual) {
			case 1 -> {opcionAudiovisual=1;
			valido=true;}
			case 2 -> {opcionAudiovisual=2;
			valido=true;}
			default -> System.out.println(Texto.TEXTO_ERROR_NUMERO);
			}
		}
		// Solicito al usuario el título del contenido
		System.out.println(Texto.TEXTO_TITULO_CONTENIDO);
		String titulo=sc.nextLine();
		// Solicito al usuario el género del contenido
		System.out.println(Texto.TEXTO_GENERO_CONTENIDO);
		Genero generoElegido=Utilidad.generoCambiarDeNumeroAGenero();
		// Solicito al usuario su valoración
		System.out.println(Texto.TEXTO_VALORACION_CONTENIDO);
		double valoracion=Teclado.pedirNumeroValoracion();
		ArrayList<Double> valoraciones=new ArrayList<>();
		valoraciones.add(valoracion);

		Scanner scFecha=new Scanner(System.in);
		// Solicitar al usuario la fecha de estreno
		System.out.println(Texto.TEXTO_ANYO_ESTRENO);
		int anyo=Teclado.pedirNumeroAnyo();
		System.out.println(Texto.TEXTO_MES_ESTRENO);
		int mes=Teclado.pedirNumeroMes();
		System.out.println(Texto.TEXTO_DIA_ESTRENO);
		int dia=Teclado.pedirNumeroDia(mes);
		LocalDate fechaEstreno=LocalDate.of(anyo, mes, dia);

		// Solicitar al usuario el nombre del director
		System.out.println(Texto.TEXTO_NOMBRE_DIRECTOR);
		Scanner scDirector=new Scanner(System.in);
		String director=scDirector.nextLine();

		// Solicitar al usuario el número de actores
		System.out.println(Texto.TEXTO_NUMERO_ACTORES);
		int numeroActores=Teclado.pedirNumeroPositivo();
		ArrayList<String> actores=new ArrayList<>();
		for (int i = 0; i < numeroActores; i++) {
			// Solicitar al usuario el nombre de cada actor
			System.out.println(Texto.TEXTO_NOMBRE_ACTOR);
			Scanner scActor=new Scanner(System.in);
			String actor1=scActor.nextLine();
			actores.add(actor1);
		}

		// Si es una película
		if(opcionAudiovisual==1) {
			System.out.println(Texto.TEXTO_HORAS_PELICULA);
			int horasPelicula=Teclado.pedirNumeroHora();
			System.out.println(Texto.TEXTO_MINUTOS_PELICULA);
			int minutosPelicula=Teclado.pedirNumeroMinuto();
			LocalTime duracionPelicula=LocalTime.of(horasPelicula, minutosPelicula);

			Pelicula pelicula=new Pelicula(titulo, generoElegido, valoraciones, fechaEstreno, duracionPelicula, director, actores);
			System.out.println(DAOAudiovisual.insertAudiovisual(pelicula));
		} else if(opcionAudiovisual==2) {
			// Si el usuario elige añadir una serie
			int minutosDeLaSerie=0;
			int horasDeLaSerie=0;
			System.out.println(Texto.TEXTO_TEMPORADAS_SERIE);
			int temporadas=Teclado.pedirNumeroPositivo();
			ArrayList<Temporada> temporadasDeLaSerie=new ArrayList<>();
			for (int i = 0; i < temporadas; i++) {
				Temporada temporada=new Temporada(null, null);
				ArrayList<Capitulos> capitulosDeLaTemporada=new ArrayList<>();
				System.out.println(Texto.TEXTO_NUMERO_CAPITULOS_TEMPORADA);
				int capitulos=Teclado.pedirNumeroPositivo();

				for (int j = 0; j < capitulos; j++) {
					System.out.println(Texto.TEXTO_TITULO_CAPITULO);
					Scanner scTitulo=new Scanner(System.in);
					String tituloDelCapitulo=scTitulo.nextLine();

					System.out.println(Texto.TEXTO_DURACION_CAPITULO);
					int minutosCapitulo=Teclado.pedirNumeroPositivo();
					int horasCapitulo=0;
					//Pregunto minutos del capitulo y se lo añado a minutos de la serie
					minutosDeLaSerie+=minutosCapitulo;
					//Para dejar añadido como LocalDate la duracion del capitulo voy quitando de 60 en 60 para conseguir horas
					while(minutosCapitulo>=60) {
						minutosCapitulo-=60;
						horasCapitulo++;
					}
					LocalTime duracionCapitulo = null;
					duracionCapitulo.of(horasCapitulo, minutosCapitulo);
					Capitulos capitulo=new Capitulos(titulo, duracionCapitulo);
					capitulosDeLaTemporada.add(capitulo);
				}
				temporada.setCapitulos(capitulosDeLaTemporada);

				//TODO controlar la fecha de temporadas
				System.out.println(Texto.TEXTO_FECHA_ESTRENO_TEMPORADA);
				System.out.println(Texto.TEXTO_ANYO_ESTRENO);
				int añoTemporada=Teclado.pedirNumeroAnyo();
				System.out.println(Texto.TEXTO_MES_ESTRENO);
				int mesTemporada=Teclado.pedirNumeroMes();
				System.out.println(Texto.TEXTO_DIA_ESTRENO);
				int diaTemporada=Teclado.pedirNumeroDia(mesTemporada);
				LocalDate fechaEstrenoTemporada = null;
				fechaEstrenoTemporada.of(anyo, mes, dia);
				temporada.setFechaTemporada(fechaEstrenoTemporada);
			}
			while (minutosDeLaSerie>=60) {
				minutosDeLaSerie-=60;
				horasDeLaSerie++;
			}
			LocalTime duracionDeLaSerie=LocalTime.of(horasDeLaSerie, minutosDeLaSerie);
			Serie serie=new Serie(titulo, generoElegido, valoraciones, fechaEstreno, duracionDeLaSerie, director, actores, temporadasDeLaSerie);
			System.out.println(DAOAudiovisual.insertAudiovisual(serie));
		}
	}

	/**
	 * Este método permite borrar un contenido de la tabla ContenidoAudiovisual.
	 * @author Antonio Jesus Blanco
	 */
	public static void borrarContenidoAudiovisual() {
		Scanner sc=new Scanner(System.in);
		// Solicitar al usuario el título del contenido que desea borrar
		System.out.println(Texto.TEXTO_TITULO_BORRAR_CONTENIDO);
		String titulo=sc.nextLine();
		// Obtener el ID del contenido audiovisual basado en el título proporcionado
		int id=DAOAudiovisual.selectAudiovisualId(titulo);
		// Si se encuentra el ID del contenido audiovisual, borrarlo
		if(id!=-1) {
			System.out.println(DAOAudiovisual.deleteAudiovisual(id));
		}else {
			System.out.println(Texto.TEXTO_ERROR_DELETE_NO_ENCONTRADO);
		}
	}


}
