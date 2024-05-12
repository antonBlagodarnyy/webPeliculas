package backEnd.utilidad;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import backEnd.audiovisual.Capitulos;
import backEnd.enums.Genero;

public class Utilidad {

	public static Genero generoCambiarDeNumeroAGenero() {
		Scanner sc=new Scanner(System.in);
		int opcionGenero;

		opcionGenero=Teclado.pedirNumeroGenero();
		boolean generoValido=false;
		Genero generoElegido = null;
		while (!generoValido) {
			Scanner scGenero=new Scanner(System.in);
			

			switch (opcionGenero) {
			case 1->{generoElegido=Genero.ACCION;
			generoValido=true;
			}
			case 2->{generoElegido=Genero.AVENTURA;
			generoValido=true;
			}
			case 3->{generoElegido=Genero.COMEDIA;
			generoValido=true;
			}
			case 4->{generoElegido=Genero.DRAMA;
			generoValido=true;
			}
			case 5->{generoElegido=Genero.MUSICAL;
			generoValido=true;
			}
			case 6->{generoElegido=Genero.TERROR;
			generoValido=true;
			}
			case 7->{generoElegido=Genero.THRILLER;
			generoValido=true;
			}
			

			}
		}
		return generoElegido;
	}


	public static String generoCambiarDeNumeroAString(int numero) {
		Scanner sc=new Scanner(System.in);
		
		boolean generoValido=false;
		String generoElegido = null;
		while (!generoValido) {
			Scanner scGenero=new Scanner(System.in);
		

			switch (numero) {
			case 1->{generoElegido="Accion";
			generoValido=true;
			}
			case 2->{generoElegido="Aventura";
			generoValido=true;
			}
			case 3->{generoElegido="Comedia";
			generoValido=true;
			}
			case 4->{generoElegido="Drama";
			generoValido=true;
			}
			case 5->{generoElegido="Musical";
			generoValido=true;
			}
			case 6->{generoElegido="Terror";
			generoValido=true;
			}
			case 7->{generoElegido="Thriller";
			generoValido=true;
			}
			default->{System.out.println("Por favor elija un valor valido");
			}

			}
		}
		return generoElegido;
	}


	public static String generoCambiarDeGeneroAString(Genero genero) {
		boolean generoValido=false;
		String generoElegido = null;
		while (!generoValido) {	switch (genero) {
			case ACCION->{generoElegido="Accion";
			generoValido=true;
			}
			case AVENTURA->{generoElegido="Aventura";
			generoValido=true;
			}
			case COMEDIA->{generoElegido="Comedia";
			generoValido=true;
			}
			case DRAMA->{generoElegido="Drama";
			generoValido=true;
			}
			case MUSICAL->{generoElegido="Musical";
			generoValido=true;
			}
			case TERROR->{generoElegido="Terror";
			generoValido=true;
			}
			case THRILLER->{generoElegido="Thriller";
			generoValido=true;
			}
			}
		}
		return generoElegido;
	}
}
