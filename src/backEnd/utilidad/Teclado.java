package backEnd.utilidad;

import java.util.Scanner;

public class Teclado {

	/**
	 * Método que pide una cadena por teclado
	 * 
	 * @return devuelve la cadena introducida
	 * @author Budau_Andrei
	 * @version 1.0
	 */
	public static String pedirCadena() {
		Scanner teclado = new Scanner(System.in);

		return teclado.nextLine();
	}

	/**
	 * Método que pide un caracter por teclado
	 * 
	 * @return devuelve el caracter introducido
	 * @author Budau_Andrei
	 * @version 1.0
	 */
	public static char pedirChar() {
		Scanner teclado = new Scanner(System.in);

		return teclado.nextLine().charAt(0);
	}

	/**
	 * Método que se encarga de rellenar un array
	 * 
	 * @param array    es el array que se le pasa como parámetro para ser rellenado
	 * @param contador es el parámetro que se le pasa para definir el tamaño del
	 *                 array <br>
	 *                 pedirCadena() se encarga de recoger el nombre
	 * @author Budau_Andrei
	 * @version 1.0
	 */
	public static void rellenarArray(String array[], int contador) {
		for (int i = 0; i < contador; i++) {

			array[i] = pedirCadena();// añado el nombre introducido en la posición de la i en el array.
		}
	}

	/**
	 * Método que muestra el contenido de un array de Strings
	 * 
	 * @param array es el array a mostrar
	 * @author Budau_Andrei
	 * @version 1.0
	 */
	public static void mostrarArray(String array[]) {
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}

	}

	/**
	 * Método que muestra el contenido de un array de enteros
	 * 
	 * @param array es el array a mostrar
	 * @author Budau_Andrei
	 * @version 1.0
	 */
	public static void mostrarArray(int array[]) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}

	}




	/**
	 * Método que genera un número aleatorio entre dos números
	 * 
	 * @param min es el número minimo
	 * @param max es el número máximo
	 * @return devuelve el número aleatorio generado
	 */
	public static int genearNumeroAleatorio(int min, int max) {// metodo que genera un número aleatrio entre un mínimo y
		// un máximo
		int numAux;
		if (min > max) {// si el numero minimo es mayor que el numero mayor
			numAux = min;// guardo el valor de min en la variable aux
			min = max;// guardo el valor de max en la variable min
			max = numAux;// guardo el valor de numAux en max es decir intercambio los valores
		}
		int numero = (int) (Math.random() * (max - min + 1) + min);// declaro una variable y almaceno el valor de las
		// operaciones
		return numero;// devuelvo el número generado
	}

	/**
	 * Método que comprueba la existencia de un número entero dentro de un array
	 * 
	 * @param numero es el número a buscar dentro del array
	 * @param array  es el array que le pasamos para buscar dentro el número
	 * @return devuelve true si encuentra el número y devuelve false si no lo
	 *         encuentra
	 * @author Budau
	 * @version 1.0
	 */
	public static boolean comprobarNumeroDentroDeArray(int numero, int array[]) {

		boolean contains = false;// declaro una variable
		for (int i = 0; i < array.length && contains != true; i++) {// declaro un bucle y le pongo como condición que se
			// ejecute
			// hasta que llegue al final de la extensión del
			// array y
			// que se ejecute mientras que contains tenga un
			// valor false
			if (array[i] == numero) {// si la posicion i dentro del array coincide con el número que busco
				contains = true;// contains vale true
			} else {// si la posicion i dentro del array no coincide con el número que busco
				contains = false;// contains vale false
			}
		}
		return contains;// devuelvo el valor de contains
	}

	/**
	 * Método que comprueba la existencia de una cadena dentro de un array
	 * 
	 * @param valor es la cadena a buscar dentro del array
	 * @param array es el array que le pasamos para buscar dentro el número
	 * @return devuelve true si encuentra la cadena y devuelve false si no la
	 *         encuentra
	 * @author Budau
	 * @version 1.0
	 */
	public static boolean comprobarCadenaDentroDeArray(String valor, String array[]) {

		boolean contains = false;// declaro una variable
		for (int i = 0; i < array.length && contains != true; i++) {// declaro un bucle y le pongo como condición que se
			// ejecute
			// hasta que llegue al final de la extensión del
			// array y
			// que se ejecute mientras que contains tenga un
			// valor false
			if (array[i].equals(valor)) {// si la posicion i dentro del array coincide con el número que busco
				contains = true;// contains vale true
			} else {// si la posicion i dentro del array no coincide con el número que busco
				contains = false;// contains vale false
			}
		}
		return contains;// devuelvo el valor de contains
	}

	/**
	 * Método que comprueba la existencia de una cadena dentro de un array y 
	 * devuelve la posición que ocupa dentro del array
	 * 
	 * @param valor es la cadena a buscar dentro del array
	 * @param array es el array que le pasamos para buscar dentro el número
	 * @return devuelve la posicion si la encuentra y null si no la encuentra
	 * @author Budau
	 * @version 1.0
	 */
	public static Integer obtenerPosicionCadenaDentroDeArray(String valor, String array[]) {
		Integer posicion=null;
		boolean encontrado=false;
		if (comprobarCadenaDentroDeArray(valor, array)) {
			for (int i = 0; i < array.length && !encontrado; i++) {
				if (array[i].equals(valor)) {// si la posicion i dentro del array coincide con el número que busco
					posicion=i;
					encontrado = true;// contains vale true
				}
			}
		}

		return posicion;// devuelvo el valor de contains
	}

	/**
	 * Método que rellena un array con números enteros aleatrorios
	 * 
	 * @param tamanio      es el tamaño del array a rellenar
	 * @param numeroMinimo es el número minimo del rango de números aleatorios a
	 *                     generar
	 * @param numeroMaximo es el número máximo del rango de números aleatorios a
	 *                     generar
	 * @return devuelve un array rellenado de números aleatorios
	 * @author Budau
	 * @version 1.0
	 */
	public static int[] rellenarArrayConNumerosAleatorios(int tamanio, int numeroMinimo, int numeroMaximo) {
		int array[] = new int[tamanio];// declaro un nuevo array con el tamaño indicado
		for (int i = 0; i < array.length; i++) {
			array[i] = Teclado.genearNumeroAleatorio(numeroMinimo, numeroMaximo);// almaceno en la posicion de array[i]
			// el número aleatorio generado
		}
		return array;// devuelvo el array relleno
	}

	/**
	 * Método que genera un número aleatorio y lo almacena dentro de un array y
	 * comprueba que ese número no esté almacenado en ninguna posición, si está
	 * almacenado genera otro número aleatorio de forma que el ciclo se repite hasta
	 * que se genera un número que no esté almacenado
	 * 
	 * @param numerosAgenerar son la cantidad de numeros que queremos que se generen
	 * @param min             es el número minimo desde el que parte el rango de
	 *                        números a generar
	 * @param max             es el número máximo hasta el que llega el rango de
	 *                        números a generar
	 * @return devuelve el número generado
	 * @author Budau
	 * @version 1.0
	 */
	public static int generarNumeroNoRepetido(int numerosAgenerar, int min, int max) {
		int numerosAleatorios[] = new int[numerosAgenerar];
		int numero;// declaro las variables
		boolean contains;
		do {// ejecuto el do hasta que contains tenga un valor false
			numero = Teclado.genearNumeroAleatorio(min, max);// genero el número aleatorio y almaceno el valor en la
			// variable
			contains = comprobarNumeroDentroDeArray(numero, numerosAleatorios);// compruebo si el número que se ha
			// generado
		} while (contains != false); // está almacenado en el array
		return numero;
	}

	/**
	 * Método que se le pasa una cadena de texto y devuelve true si la cadena de
	 * texto se compone de letras o espacios. Si encuentra un número u otro caracter
	 * devuelve false
	 * 
	 * @param nombre es la cadena de texto a examinar
	 * @return devuelve true si todo son letras o espacios, devuelve false si no es
	 *         lo comentado anteriormente
	 * @author Budau
	 */

	public static boolean comprobarNombre(String nombre) {
		boolean valido = true;

		for (int i = 0; i < nombre.length() && valido; i++) {

			if (!Character.isLetter(nombre.charAt(i)) && nombre.charAt(i) != ' ') {
				valido = false;
			}
		}
		return valido;
	}

	/**
	 * Método que pide un nombre(Cadena de texto) solo con letras y espacios
	 * 
	 * @return devuelve una cadena de texto solo con letras y espacios
	 */
	public static String pedirNombre() {
		String nombre = null;
		do {

			System.out.println("Introduce nombre:");
			nombre = Teclado.pedirCadena();
			if (!comprobarNombre(nombre)) {
				System.out.println("ERROR. EL NOMBRE NO PUEDE CONTENER NUMEROS O CARACTERES");
			}
		} while (!comprobarNombre(nombre));
		return nombre;
	}

	private static int pedirNumeroAux() {
		Scanner teclado = new Scanner(System.in);
		return teclado.nextInt();
	}

	private static double pedirNumeroAuxDecimal() {
		Scanner teclado = new Scanner(System.in);
		return teclado.nextDouble();
	}

	/**
	 * Método que recoge y controla que el valor que se ha introducido es un número
	 * y devuelve el número introducido por teclado
	 * 
	 * @return devuelve el número introducido por teclado
	 */
	public static double pedirNumeroValoracion() {
		double numero = 0;
		boolean valido=false;
		do {// se repite hasta que el sueldo sea correcto/sea un número entre 0 y 10
			try {
				numero = pedirNumeroAuxDecimal();
				if(numero>=0&&numero<=10) {
					valido = true;
				}
				else {
					System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
				}
			} catch (Exception e) {// si el usuario introduce algun dato que no sean números, salta la excepcion
				System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
			}
		} while (!valido);
		return numero;// devuelvo el sueldo correcto
	}


	public static int pedirNumeroPositivo(){
		int numero = 0;
		boolean valido=false;
		do {// se repite hasta que el sueldo sea correcto/sea un número entre 0 y 10
			try {
				numero = pedirNumeroAux();
				if(numero>=0) {
					valido = true;
				}
				else {
					System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
				}
			} catch (Exception e) {// si el usuario introduce algun dato que no sean números, salta la excepcion
				System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
			}
		} while (!valido);
		return numero;// devuelvo el sueldo correcto
	}
	public static int pedirNumeroOpcionMenu(){
		int numero = 0;
		boolean valido=false;
		do {// se repite hasta que el sueldo sea correcto/sea un número entre 0 y 10
			try {
				numero = pedirNumeroAux();
				if(numero>=1&&numero<=8) {
					valido = true;
				}
				else {
					System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
				}
			} catch (Exception e) {// si el usuario introduce algun dato que no sean números, salta la excepcion
				System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
			}
		} while (!valido);
		return numero;// devuelvo el sueldo correcto
	}
	public static int pedirNumeroGenero() {
		int numero = 0;
		boolean valido=false;
		do {// se repite hasta que el sueldo sea correcto/sea un número entre 0 y 10
			try {
				numero = pedirNumeroAux();
				if(numero>=1&&numero<=7) {
					valido = true;
				}else {
					System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
				}

			} catch (Exception e) {// si el usuario introduce algun dato que no sean números, salta la excepcion
				System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
			}
		} while (!valido);
		return numero;// devuelvo el sueldo correcto
	}

	public static int pedirNumero() {
		int numero = 0;
		boolean valido=false;
		do {// se repite hasta que el sueldo sea correcto/sea un número entre 0 y 10
			try {
				numero = pedirNumeroAux();
				valido = true;
			} catch (Exception e) {// si el usuario introduce algun dato que no sean números, salta la excepcion
				System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
			}
		} while (!valido);
		return numero;// devuelvo el sueldo correcto
	}


	public static int pedirNumeroOpcionSelect() {
		int numero = 0;
		boolean valido=false;
		do {// se repite hasta que el sueldo sea correcto/sea un número entre 0 y 10
			try {
				numero = pedirNumeroAux();
				if(numero>=1&&numero<=2) {
					valido = true;}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}
			} catch (Exception e) {// si el usuario introduce algun dato que no sean números, salta la excepcion
				System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
			}
		} while (!valido);
		return numero;// devuelvo el sueldo correcto
	}

	public static int pedirNumeroHora() {
		int numero = 0;
		boolean valido=false;
		do {// se repite hasta que el sueldo sea correcto/sea un número entre 0 y 10
			try {
				numero = pedirNumeroAux();
				if(numero>=0&&numero<=23) {
					valido = true;
				}else {
					System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
				}
			} catch (Exception e) {// si el usuario introduce algun dato que no sean números, salta la excepcion
				System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
			}
		} while (!valido);
		return numero;// devuelvo el sueldo correcto
	}public static int pedirNumeroMinuto() {
		int numero = 0;
		boolean valido=false;
		do {// se repite hasta que el sueldo sea correcto/sea un número entre 0 y 10
			try {
				numero = pedirNumeroAux();
				if(numero>=0&&numero<=59) {
					valido = true;
				}
				else {
					System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
				}
			} catch (Exception e) {// si el usuario introduce algun dato que no sean números, salta la excepcion
				System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
			}
		} while (!valido);
		return numero;// devuelvo el sueldo correcto
	}


	public static int pedirNumeroAnyo() {
		int numero = 0;
		boolean valido=false;
		do {// se repite hasta que el sueldo sea correcto/sea un número entre 0 y 10
			try {
				numero = pedirNumeroAux();
				if(numero>=1900&&numero<=2024) {
					valido = true;
				}
				else 
				{
					System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
				}
			} catch (Exception e) {// si el usuario introduce algun dato que no sean números, salta la excepcion
				System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
			}
		} while (!valido);
		return numero;// devuelvo el sueldo correcto
	}
	public static int pedirNumeroMes() {
		int numero = 0;
		boolean valido=false;
		do {// se repite hasta que el sueldo sea correcto/sea un número entre 0 y 10
			try {
				numero = pedirNumeroAux();
				if(numero>=1&&numero<=12) {
					valido = true;
				}
				else {
					System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
				}
			} catch (Exception e) {// si el usuario introduce algun dato que no sean números, salta la excepcion
				System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
			}
		} while (!valido);
		return numero;// devuelvo el sueldo correcto
	}
	public static int pedirNumeroDia(int mes) {
		int numero = 0;
		boolean valido=false;
		do {// se repite hasta que el sueldo sea correcto/sea un número entre 0 y 10
			try {
				numero = pedirNumeroAux();
				if(mes==1) {
					if(numero>=1&&numero<=31) {
						valido = true;
					}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}
				}else if(mes==2) {

					if(numero>=1&&numero<=29) {
						valido = true;
					}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}
				}else if(mes==3) {

					if(numero>=1&&numero<=31) {
						valido = true;
					}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}
				}else if(mes==4) {

					if(numero>=1&&numero<=30) {
						valido = true;
					}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}	
				}else if(mes==5) {

					if(numero>=1&&numero<=31) {
						valido = true;
					}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}	
				}else if(mes==6) {

					if(numero>=1&&numero<=30) {
						valido = true;
					}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}	
				}else if(mes==7) {

					if(numero>=1&&numero<=31) {
						valido = true;
					}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}	
				}else if(mes==8) {

					if(numero>=1&&numero<=31) {
						valido = true;
					}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}	
				}else if(mes==9) {

					if(numero>=1&&numero<=30) {
						valido = true;
					}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}	
				}else if(mes==10) {

					if(numero>=1&&numero<=31) {
						valido = true;
					}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}	
				}else if(mes==11) {

					if(numero>=1&&numero<=30) {
						valido = true;
					}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}	
				}else if(mes==12) {

					if(numero>=1&&numero<=31) {
						valido = true;
					}else {
						System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
					}
				}


			} catch (Exception e) {// si el usuario introduce algun dato que no sean números, salta la excepcion
				System.out.println("Error: INTRODUZCA UN NÚMERO VALIDO");
			}
		} while (!valido);
		return numero;// devuelvo el sueldo correcto
	}


	/**
	 * Método que recoge y controla que el valor que se ha introducido es un número
	 * y devuelve el número introducido por teclado
	 * 
	 * @return devuelve el número introducido por teclado
	 */
	public static double pedirNumeroDecimal() {
		double numero = 0;
		boolean valido;
		do {// se repite hasta que el sueldo sea correcto/sea un número positivo
			try {
				numero = pedirNumeroAuxDecimal();
				valido = false;
			} catch (Exception e) {// si el usuario introduce algun dato que no sean números, salta la excepcion
				System.out.println("Error: INTRODUZCA UN NÚMERO");
				valido = true;
			}
		} while (valido);
		return numero;// devuelvo el sueldo correcto
	}








}
