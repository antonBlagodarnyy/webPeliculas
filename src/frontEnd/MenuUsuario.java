package frontEnd;

import java.util.Scanner;


/**
 * Clase utiliada para crear menus de los usuarios.
 * Elmenu consistir� en una poregunta, y varias opciones.
 * El objeto se encargara de mostar el resultado, y validar
 * que la respuesta es correcta.
 * Adicionalmente, la clase contiene un conjuntoi de metodos estaticos
 * cuyo objeto es facilitar la lectura de informacion del usuario validada
 * 
 * @author Raul SG
 *
 */
public class MenuUsuario {

	//ATRIBUTOS
	private String pregunta;
	private String[] opciones;//TODO seria mejor hacerlo con un List, pero no lo utilizo porque 
	//no lo hemos dado aun. Con List, seria mas simple.

	private int i; //Indice por donde vamos a�adiendo las opciones en el array de opciones. Inicialmente vale 0.
	public static final  int TAM_MAX=20;//Hemos considerado que el numero maximo de opciones en un menu va a ser 20
	//Usando List, no existiria un limite.


	//METODOS

	//Constructor
	/**
	 * Constructor de la clase MenuUsuario. Es utilizado para crear un objeto menu de usuario
	 * indicandole la pregunta principal del menu
	 * 
	 * @param pregunta principal del menu
	 */
	public MenuUsuario(String pregunta) {
		this.pregunta = pregunta;
		this.opciones=new String[TAM_MAX];
		this.i=0;
	}

	/**
	 * Metodo utilizado para a�adir las posibles opciones que se van a presentar en el menu
	 * 
	 * @param opcionNueva que se quiere agregar a las opciones ya existentes
	 * @return devuelve true si se ha podiso agregar, devuelve false, si se ha llegado al
	 * limite de opciones posibles, y por tanto no se pueden agregar mas opciones 
	 */
	public  boolean addOpcion(String opcionNueva) {
		boolean agregado=false;
		if(i<TAM_MAX) {//Si no se ha llegado al limite
			opciones[i]=opcionNueva;//guardamos la nueva opcion
			i++;//Incrementamos el indice, para que apunte al siguiente hueco de la array.
			agregado=true; //Se indica que se ha a�adido la nueva opcion
		}
		return agregado;//Devolvenos si hemos agregado la opcion o no
	}

	/**
	 * Metodo utilizado para mostrar el menu
	 */
	public String toString() {
		String resultado="";
		resultado+=pregunta+"\n";//Mostramos la pregunta
		for(int i=0;i<this.i;i++) {//Mostramos todas las opciones, numeradas
			resultado+="\n\t"+(i+1)+".- "+opciones[i];//TODO STring builder concatena mejor.
		}
		return resultado;
	}

	/**
	 * Metodo utilizado para leer la opcion elegida por el usuario desde teclado.
	 * Se controla que la eleccion este dentro del numero de opciones disponibles, en caso contrario se le avisara al usuario y 
	 * tendra que introducir un numero de nuevo.
	 * @return devuelve la eleccion seleccionada por el usuario, la cual siempre estara entre 1 , y el numero de opciones disponibles
	 */
	public int elegirOpcion() {
		int inicio=1;//Las opciones posibles estan entre 1
		int fin=i;//Y el numero de opciones a�adidas
		System.out.println(this.toString());
		return leerNumeroEntre(inicio, fin);
	}

	//Metodo utilizado para mandar un mensaje de error, cuando no se ha seleccionado
	//un numero entre un rango determinado.
	private static void mensajeError(int inicio, int fin) {
		System.out.println("Eleccion incorrecta. Se debe seleccionar un numero entre "+inicio+" y "+fin);
	}

	/**
	 * Metodo utilizado para leer por teclado un numero situado entre
	 * los paramnetros inicio, y fin.
	 * 
	 * @param inicio, marca el comienzo del rango. Esta incluido. Para que funcione el metodo debe ser
	 * menor que el parametro fin
	 * @param fin, marca el final del rango. Esta incluido. Para que funcione el metodo debe ser
	 * mayor que el parametro inicio
	 * @return numero introducido por teclado situado entre el rango [inicio, fin]
	 */
	public static int leerNumeroEntre(int inicio, int fin) {
		int eleccion=1;//Variable que guarda el numero tecleado por el usuario
		boolean opcionIncorrecta=true;//Inicialmente consideramos que el numero es incorrecto
		while(opcionIncorrecta) {//Hasta que no se tecle un numero correcto
			try {
				Scanner sc=new Scanner(System.in);
				eleccion=sc.nextInt();//Leemos un entero por teclado
				if(eleccion<inicio || eleccion>fin) {//Si elegimos un valor fuera del rango posible
					mensajeError(inicio,fin);
				}
				else {//Si el numero esta dentro del rango permitido
					opcionIncorrecta=false;//La opcion pasa a ser correcta, y el bucle se detiene
				}
			}catch (Exception e) {//En caso de teclear texto, en lugar de un numero
				mensajeError(inicio,fin);
			}
		}
		return eleccion;//Se devuelve el numnero tecleado por el usuario
	}

	/**
	 * Metodo utilizado para leer un numero desde teclado
	 * 
	 * @return devuelve el numero seleccionado por el usuario
	 */
	public static int leerNumero() {
		int eleccion=1;//Variable que guarda el numero tecleado por el usuario
		boolean opcionIncorrecta=true;//Inicialmente consideramos que el numero es incorrecto
		while(opcionIncorrecta) {//Hasta que no se tecle un numero correcto
			try {
				Scanner sc=new Scanner(System.in);
				eleccion=sc.nextInt();//Leemos un entero por teclado
				opcionIncorrecta=false;//Si llegamos a este punto, la opcion es correcta, y el bucle se detendra.
			}catch (Exception e) {//En caso de teclear texto, en lugar de un numero
				System.out.println("Se debe elegir un numero");
			}
		}
		return eleccion;//Se devuelve el numnero tecleado por el usuario
	}

	/**
	 * Metodo utilizado para leer un numero desde el teclado, mostrando al
	 * usuario una pregunta
	 * @param pregunta. Pregunta que se quiere mostrar al usuario antes de leer un valor del teclado.
	 * @return Numero tecleado por el usuario
	 */
	public static int leerNumero(String pregunta) {
		System.out.println(pregunta);//Mostramos la pregunta
		return leerNumero();//Leemos un numero
	}

	/**
	 * Metodo utilizado para leer un numero desde el teclado, mostrando al
	 * usuario una pregunta
	 * @param pregunta. Pregunta que se quiere mostrar al usuario antes de leer un valor del teclado.
	 * @return Numero tecleado por el usuario
	 */
	public static double leerDouble(String pregunta) {
		System.out.println(pregunta);//Mostramos la pregunta
		return leerNumeroDouble();//Leemos un numero
	}

	/**
	 * Metodo utilizado para leer un numero desde teclado
	 * 
	 * @return devuelve el numero seleccionado por el usuario
	 */
	public static double leerNumeroDouble() {
		double eleccion=1;//Variable que guarda el numero tecleado por el usuario
		boolean opcionIncorrecta=true;//Inicialmente consideramos que el numero es incorrecto
		while(opcionIncorrecta) {//Hasta que no se tecle un numero correcto
			try {
				Scanner sc=new Scanner(System.in);
				eleccion=sc.nextDouble();//Leemos un entero por teclado
				opcionIncorrecta=false;//Si llegamos a este punto, la opcion es correcta, y el bucle se detendra.
			}catch (Exception e) {//En caso de teclear texto, en lugar de un numero
				System.out.println("Se debe elegir un numero");
			}
		}
		return eleccion;//Se devuelve el numnero tecleado por el usuario
	}

	/**
	 * Metodo utilizado para leer una cadena de texto, mostrando al
	 * usuario una pregunta
	 * @param pregunta. Pregunta que se quiere mostrar al usuario antes de leer un valor del teclado.
	 * @return String tecleado por el usuario
	 */
	public static String leerCadena(String pregunta) {
		Scanner sc=new Scanner(System.in);
		System.out.println(pregunta);//Mostramos la pregunta
		return sc.nextLine();//Leemos una cadena por teclado
	}

	/**
	 * Metodo utilizado para leer por teclado un numero situado entre
	 * los paramnetros inicio, y fin.
	 * 
	 * @param inicio, marca el comienzo del rango. Esta incluido. Para que funcione el metodo debe ser
	 * menor que el parametro fin
	 * @param fin, marca el final del rango. Esta incluido. Para que funcione el metodo debe ser
	 * mayor que el parametro inicio
	 * @return numero introducido por teclado situado entre el rango [inicio, fin]
	 */
	public static double leerDoubleEntre(double inicio, double fin) {
		double eleccion=1;//Variable que guarda el numero tecleado por el usuario
		boolean opcionIncorrecta=true;//Inicialmente consideramos que el numero es incorrecto
		while(opcionIncorrecta) {//Hasta que no se tecle un numero correcto
			try {
				Scanner sc=new Scanner(System.in);
				eleccion=sc.nextDouble();//Leemos un entero por teclado
				if(eleccion<inicio || eleccion>fin) {//Si elegimos un valor fuera del rango posible
					mensajeError(inicio,fin);
				}
				else {
					opcionIncorrecta=false;
				}
			}catch (Exception e) {//En caso de teclear texto, en lugar de un numero
				mensajeError(inicio,fin);
			}
		}
		return eleccion;//Se devuelve el numero tecleado por el usuario
	}

	//Metodo utilizado para mandar un mensaje de error, cuando no se ha seleccionado
	//un numero entre un rango determinado.
	private static void mensajeError(double inicio, double fin) {
		System.out.println("Eleccion incorrecta. Se debe seleccionar un numero entre "+inicio+" y "+fin);
		//TODO fusionar con el otro metodo que muestra un mensaje de error
	}

	
	/**
	 * Metodo utilizado para seleccionar un booleano. Muestra una pregunta por
	 * pantalla, y luego a continuacion muestra las  opciones para elegir si o no.
	 * 
	 * @param pregunta. Texto que se le muestra al usuario
	 * @return booleano. True, si el usuario elige si, y false si el usuario elige no.
	 */
	public static boolean leerBooleano(String pregunta) {
		MenuUsuario menuAgregarProducto=new MenuUsuario(pregunta);//Creamos un menu, con las opciones si y no
		menuAgregarProducto.addOpcion("Si");//Agregamos la opcion si
		menuAgregarProducto.addOpcion("No");//Agregamos la opcion no
		int opcion =menuAgregarProducto.elegirOpcion();//Se muestra las opciones al usuario, para elegir
		boolean resultado=false;//Falso por defecto
		if(opcion==1) {//Si elige el uno
			resultado=true;//El resultado sera true
		}
		return resultado;//Devolvemos el resultado.
	}
}