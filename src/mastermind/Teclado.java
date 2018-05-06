package mastermind;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta clase sirve para la lectura de lo que introduce el usuario.
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */

public class Teclado {

	public enum Comparacion {
		MAYOR, MENOR, MAYORIGUAL, MENORIGUAL
	}

	public enum LimiteInfySup {
		INCLUIDOS, EXCLUIDOS, INFINCLUIDO, SUPINCLUIDO
	}

	static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		// (para cuando lo vaya a usar) import static utilidades[paquete en el que este].Teclado.*;

		// enum de mayor que, menor que, mayor igual que, menor igual que
		// no se puede hacer lo del ejercicio 1y2 (la lectura numero, no se puede pasar un parametro que va
		// a quedarse sin usar, que se machaca) corregir 1y2
		
	}

	public static void cerrarteclado() {
		teclado.close();
	}

	public static char leerchar() {
		return teclado.next().charAt(0);
	}

	public static String leercadena() {
		return teclado.nextLine();

	}

	public static boolean leerboolmenu(String mensaje_menu, String mensajeop1, String mensajeop2) {

		byte opcion = 1;
		boolean vof = false, error;
		do {
			try {
				System.out.println(mensaje_menu);

				System.out.println("\t1.-" + mensajeop1);
				System.out.println("\t2.-" + mensajeop2);

				opcion = leerbyte();
				error = false;

			} catch (InputMismatchException e) {
				System.out.println("Error, valor incorrecto");
				teclado.nextLine();
				error = true;
			}
		} while (error || (opcion != 1 && opcion != 2));

		switch (opcion) {
			case 1:
				vof = true;
				break;
			case 2:
				vof = false;
				break;

			default:
				System.out.println("Error en la selección de las opciones");
				break;
		}

		return vof;
	}

	public static boolean leerboolpregsino(String pregunta) {
		char siono;
		boolean vof = false;

		do {
			System.out.println(pregunta + "(s/n)");
			siono = Character.toLowerCase(leerchar());
			if (siono!='s' && siono!='n') {
				System.out.println("Error. Caracter no válido");
			}
		} while (siono != 's' && siono != 'n');

		switch (siono) {
			case 's':
				vof = true;
				break;
			case 'n':
				vof = false;
				break;
			default:
				System.out.println("Error en la selección de las opciones");
				break;
		}
		return vof;
	}

	public static byte leerbyte() {
		byte bit8 = 0;
		boolean error;
		do {
			try {
				bit8 = teclado.nextByte();
				error = false;

			} catch (InputMismatchException e) {
				System.out.println("Error, valor incorrecto");
				error = true;

			} finally {
				teclado.nextLine();
			}
		} while (error);
		return bit8;
	}

	public static short leershort() {
		short cortito = 0;
		boolean error;
		do {
			try {
				cortito = teclado.nextShort();
				error = false;
			} catch (InputMismatchException e) {
				System.out.println("Error, valor incorrecto");
				error = true;
			} finally {
				teclado.nextLine();
			}
		} while (error);
		return cortito;
	}

	public static int leerint() {
		int entero = 0;
		boolean error;
		do {
			try {
				entero = teclado.nextInt();
				error = false;
			} catch (InputMismatchException e) {
				System.out.println("Error, valor incorrecto");
				error = true;
			} finally {
				teclado.nextLine();
			}
		} while (error);
		return entero;
	}

	public static long leerlong() {
		long largo = 0;
		boolean error;
		do {
			try {
				largo = teclado.nextLong();
				error = false;
			} catch (InputMismatchException e) {
				System.out.println("Error, valor incorrecto");
				error = true;
			} finally {
				teclado.nextLine();
			}
		} while (error);
		System.out.printf(teclado.nextLine());
		return largo;
	}

	public static float leerfloat() {
		float flota = 0;
		boolean error;
		do {
			try {
				flota = teclado.nextFloat();
				error = false;
			} catch (InputMismatchException e) {
				System.out.println("Error, valor incorrecto");
				teclado.nextLine();
				error = true;
			} finally {
				teclado.nextLine();
			}
		} while (error);

		return flota;
	}

	public static double leerdouble() {
		double doble = 0;
		boolean error;
		do {
			try {
				doble = teclado.nextDouble();
				error = false;
			} catch (InputMismatchException e) {
				System.out.println("Error, valor incorrecto, introduzca otro.");
				error = true;
			} finally {
				teclado.nextLine();
			}
		} while (error);

		return doble;
	}

	public static byte lecturacomparativa(byte numcomparado, Comparacion comparacion) {

		byte numleido = 0;
		boolean error = false;
		do {
			numleido = leerbyte();
			switch (comparacion) {
				case MAYOR:
					if (numleido > numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser mayor que " + numcomparado + ": ");
						error = true;
					}
					break;

				case MENOR:
					if (numleido < numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser menor que " + numcomparado + ": ");
						error = true;
					}

					break;

				case MAYORIGUAL:

					if (numleido >= numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser mayor o igual que " + numcomparado + ": ");
						error = true;
					}
					break;

				case MENORIGUAL:
					if (numleido <= numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser menor o igual que " + numcomparado + ": ");
						error = true;
					}
					break;

				default:
					System.out.println("Error en la lectura");
					break;
			}
		} while (error);

		return numleido;

	}

	public static short lecturacomparativa(short numcomparado, Comparacion comparacion) {

		short numleido = 0;
		boolean error = false;
		do {
			numleido = leershort();
			switch (comparacion) {
				case MAYOR:
					if (numleido > numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser mayor que " + numcomparado + ": ");
						error = true;
					}
					break;

				case MENOR:
					if (numleido < numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser menor que " + numcomparado + ": ");
						error = true;
					}

					break;

				case MAYORIGUAL:

					if (numleido >= numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser mayor o igual que " + numcomparado + ": ");
						error = true;
					}
					break;

				case MENORIGUAL:
					if (numleido <= numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser menor o igual que " + numcomparado + ": ");
						error = true;
					}
					break;

				default:
					System.out.println("Error en la lectura");
					break;
			}
		} while (error);

		return numleido;

	}

	public static int lecturacomparativa(int numcomparado, Comparacion comparacion) {

		int numleido = 0;
		boolean error = false;
		do {
			numleido = leerint();
			switch (comparacion) {
				case MAYOR:
					if (numleido > numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser mayor que " + numcomparado + ": ");
						error = true;
					}
					break;

				case MENOR:
					if (numleido < numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser menor que " + numcomparado + ": ");
						error = true;
					}

					break;

				case MAYORIGUAL:

					if (numleido >= numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser mayor o igual que " + numcomparado + ": ");
						error = true;
					}
					break;

				case MENORIGUAL:
					if (numleido <= numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser menor o igual que " + numcomparado + ": ");
						error = true;
					}
					break;

				default:
					System.out.println("Error en la lectura");
					break;
			}
		} while (error);

		return numleido;

	}

	public static long lecturacomparativa(long numcomparado, Comparacion comparacion) {

		long numleido = 0;
		boolean error = false;
		do {
			numleido = leerlong();
			switch (comparacion) {
				case MAYOR:
					if (numleido > numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser mayor que " + numcomparado + ": ");
						error = true;
					}
					break;

				case MENOR:
					if (numleido < numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser menor que " + numcomparado + ": ");
						error = true;
					}

					break;

				case MAYORIGUAL:

					if (numleido >= numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser mayor o igual que " + numcomparado + ": ");
						error = true;
					}
					break;

				case MENORIGUAL:
					if (numleido <= numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser menor o igual que " + numcomparado + ": ");
						error = true;
					}
					break;

				default:
					System.out.println("Error en la lectura");
					break;
			}
		} while (error);

		return numleido;

	}

	public static float lecturacomparativa(float numcomparado, Comparacion comparacion) {

		float numleido = 0;
		boolean error = false;
		do {
			numleido = leerfloat();
			switch (comparacion) {
				case MAYOR:
					if (numleido > numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser mayor que " + numcomparado + ": ");
						error = true;
					}
					break;

				case MENOR:
					if (numleido < numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser menor que " + numcomparado + ": ");
						error = true;
					}

					break;

				case MAYORIGUAL:

					if (numleido >= numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser mayor o igual que " + numcomparado + ": ");
						error = true;
					}
					break;

				case MENORIGUAL:
					if (numleido <= numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser menor o igual que " + numcomparado + ": ");
						error = true;
					}
					break;

				default:
					System.out.println("Error en la lectura");
					break;
			}
		} while (error);

		return numleido;

	}

	public static double lecturacomparativa(double numcomparado, Comparacion comparacion) {

		double numleido = 0;
		boolean error = false;
		do {
			numleido = leerdouble();
			switch (comparacion) {
				case MAYOR:
					if (numleido > numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser mayor que " + numcomparado + ": ");
						error = true;
					}
					break;

				case MENOR:
					if (numleido < numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser menor que " + numcomparado + ": ");
						error = true;
					}

					break;

				case MAYORIGUAL:

					if (numleido >= numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser mayor o igual que " + numcomparado + ": ");
						error = true;
					}
					break;

				case MENORIGUAL:
					if (numleido <= numcomparado) {
						error = false;
					} else {
						System.out.println("El numero tiene que ser menor o igual que " + numcomparado + ": ");
						error = true;
					}
					break;

				default:
					System.out.println("Error en la lectura");
					break;
			}
		} while (error);

		return numleido;

	}

	public static byte lecturaconlimites(byte limiteinferior, byte limitesuperior, LimiteInfySup limites,
			String mensajedelectura) {

		byte numentrelimites = 0;
		boolean error = false;

		if (limiteinferior > limitesuperior) {
			throw new IllegalArgumentException("Error, el límite inferior es mayor que el límite superior");
		}

		do {

			System.out.println(mensajedelectura);
			numentrelimites = leerbyte();

			switch (limites) {
				case INCLUIDOS:
					if (numentrelimites >= limiteinferior && numentrelimites <= limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + " y " + limitesuperior
								+ " (incluidos) ");
						error = true;
					}
					break;

				case EXCLUIDOS:
					if (numentrelimites > limiteinferior && numentrelimites < limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + " y " + limitesuperior
								+ " (sin incluir ambos) ");
						error = true;
					}

					break;

				case INFINCLUIDO:

					if (numentrelimites >= limiteinferior && numentrelimites < limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + "(incluido) y "
								+ limitesuperior + " (sin incluir) ");
						error = true;
					}

					break;

				case SUPINCLUIDO:
					if (numentrelimites > limiteinferior && numentrelimites <= limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + "(sin incluir) y "
								+ limitesuperior + " (incluido) ");
						error = true;
					}

					break;

				default:
					System.out.println("Error en la lectura");
					break;
			}

		} while (error);

		return numentrelimites;
	}
	
	public static short lecturaconlimites(short limiteinferior, short limitesuperior, LimiteInfySup limites,
			String mensajedelectura) {

		short numentrelimites = 0;
		boolean error = false;

		if (limiteinferior > limitesuperior) {
			throw new IllegalArgumentException("Error, el límite inferior es mayor que el límite superior");
		}

		do {

			System.out.println(mensajedelectura);
			numentrelimites = leershort();

			switch (limites) {
				case INCLUIDOS:
					if (numentrelimites >= limiteinferior && numentrelimites <= limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + " y " + limitesuperior
								+ " (incluidos) ");
						error = true;
					}
					break;

				case EXCLUIDOS:
					if (numentrelimites > limiteinferior && numentrelimites < limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + " y " + limitesuperior
								+ " (sin incluir ambos) ");
						error = true;
					}

					break;

				case INFINCLUIDO:

					if (numentrelimites >= limiteinferior && numentrelimites < limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + "(incluido) y "
								+ limitesuperior + " (sin incluir) ");
						error = true;
					}

					break;

				case SUPINCLUIDO:
					if (numentrelimites > limiteinferior && numentrelimites <= limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + "(sin incluir) y "
								+ limitesuperior + " (incluido) ");
						error = true;
					}

					break;

				default:
					System.out.println("Error en la lectura");
					break;
			}

		} while (error);

		return numentrelimites;
	}
	
	public static int lecturaconlimites(int limiteinferior, int limitesuperior, LimiteInfySup limites,
			String mensajedelectura) {

		int numentrelimites = 0;
		boolean error = false;

		if (limiteinferior > limitesuperior) {
			throw new IllegalArgumentException("Error, el límite inferior es mayor que el límite superior");
		}

		do {

			System.out.println(mensajedelectura);
			numentrelimites = leerint();

			switch (limites) {
				case INCLUIDOS:
					if (numentrelimites >= limiteinferior && numentrelimites <= limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + " y " + limitesuperior
								+ " (incluidos) ");
						error = true;
					}
					break;

				case EXCLUIDOS:
					if (numentrelimites > limiteinferior && numentrelimites < limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + " y " + limitesuperior
								+ " (sin incluir ambos) ");
						error = true;
					}

					break;

				case INFINCLUIDO:

					if (numentrelimites >= limiteinferior && numentrelimites < limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + "(incluido) y "
								+ limitesuperior + " (sin incluir) ");
						error = true;
					}

					break;

				case SUPINCLUIDO:
					if (numentrelimites > limiteinferior && numentrelimites <= limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + "(sin incluir) y "
								+ limitesuperior + " (incluido) ");
						error = true;
					}

					break;

				default:
					System.out.println("Error en la lectura");
					break;
			}

		} while (error);

		return numentrelimites;
	}
	
	public static long lecturaconlimites(long limiteinferior, long limitesuperior, LimiteInfySup limites,
			String mensajedelectura) {

		long numentrelimites = 0;
		boolean error = false;

		if (limiteinferior > limitesuperior) {
			throw new IllegalArgumentException("Error, el límite inferior es mayor que el límite superior");
		}

		do {

			System.out.println(mensajedelectura);
			numentrelimites = leerlong();

			switch (limites) {
				case INCLUIDOS:
					if (numentrelimites >= limiteinferior && numentrelimites <= limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + " y " + limitesuperior
								+ " (incluidos) ");
						error = true;
					}
					break;

				case EXCLUIDOS:
					if (numentrelimites > limiteinferior && numentrelimites < limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + " y " + limitesuperior
								+ " (sin incluir ambos) ");
						error = true;
					}

					break;

				case INFINCLUIDO:

					if (numentrelimites >= limiteinferior && numentrelimites < limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + "(incluido) y "
								+ limitesuperior + " (sin incluir) ");
						error = true;
					}

					break;

				case SUPINCLUIDO:
					if (numentrelimites > limiteinferior && numentrelimites <= limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + "(sin incluir) y "
								+ limitesuperior + " (incluido) ");
						error = true;
					}

					break;

				default:
					System.out.println("Error en la lectura");
					break;
			}

		} while (error);

		return numentrelimites;
	}
	
	public static float lecturaconlimites(float limiteinferior, float limitesuperior, LimiteInfySup limites,
			String mensajedelectura) {

		float numentrelimites = 0;
		boolean error = false;

		if (limiteinferior > limitesuperior) {
			throw new IllegalArgumentException("Error, el límite inferior es mayor que el límite superior");
		}

		do {

			System.out.println(mensajedelectura);
			numentrelimites = leerfloat();

			switch (limites) {
				case INCLUIDOS:
					if (numentrelimites >= limiteinferior && numentrelimites <= limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + " y " + limitesuperior
								+ " (incluidos) ");
						error = true;
					}
					break;

				case EXCLUIDOS:
					if (numentrelimites > limiteinferior && numentrelimites < limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + " y " + limitesuperior
								+ " (sin incluir ambos) ");
						error = true;
					}

					break;

				case INFINCLUIDO:

					if (numentrelimites >= limiteinferior && numentrelimites < limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + "(incluido) y "
								+ limitesuperior + " (sin incluir) ");
						error = true;
					}

					break;

				case SUPINCLUIDO:
					if (numentrelimites > limiteinferior && numentrelimites <= limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + "(sin incluir) y "
								+ limitesuperior + " (incluido) ");
						error = true;
					}

					break;

				default:
					System.out.println("Error en la lectura");
					break;
			}

		} while (error);

		return numentrelimites;
	}
	
	public static double lecturaconlimites(double limiteinferior, double limitesuperior, LimiteInfySup limites,
			String mensajedelectura) {

		double numentrelimites = 0;
		boolean error = false;

		if (limiteinferior > limitesuperior) {
			throw new IllegalArgumentException("Error, el límite inferior es mayor que el límite superior");
		}

		do {

			System.out.println(mensajedelectura);
			numentrelimites = leerdouble();

			switch (limites) {
				case INCLUIDOS:
					if (numentrelimites >= limiteinferior && numentrelimites <= limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + " y " + limitesuperior
								+ " (incluidos) ");
						error = true;
					}
					break;

				case EXCLUIDOS:
					if (numentrelimites > limiteinferior && numentrelimites < limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + " y " + limitesuperior
								+ " (sin incluir ambos) ");
						error = true;
					}

					break;

				case INFINCLUIDO:

					if (numentrelimites >= limiteinferior && numentrelimites < limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + "(incluido) y "
								+ limitesuperior + " (sin incluir) ");
						error = true;
					}

					break;

				case SUPINCLUIDO:
					if (numentrelimites > limiteinferior && numentrelimites <= limitesuperior) {
						error = false;
					} else {
						System.out.println("El numero tiene que estar entre " + limiteinferior + "(sin incluir) y "
								+ limitesuperior + " (incluido) ");
						error = true;
					}

					break;

				default:
					System.out.println("Error en la lectura");
					break;
			}

		} while (error);

		return numentrelimites;
	}

		// *llenar_array_numenteros: Devuelve un lista de numeros enteros de un tamaño especificado por el usuario y rellenado también por el
			
	public static int[] llenar_array_numenteros() {

		int array[];

		// 1.- Pedimos el tamaño de la lista al usuario
		System.out.println("Introduzca el tamaño del array: ");
		array = new int[Teclado.lecturacomparativa(0, Teclado.Comparacion.MAYOR)];

		// 2.- Vamos recorriendo la lista
		for (int j = 0; j < array.length; j++) {
			// 2.1.-Mostramos por pantalla la posición del elemento y se lo pedimos para guardarlo en la lista
			System.out.println("Número de la posición nº" + (j) + " :");
			array[j] = Teclado.leerint();
		}

		// 3.-Devolemos la lista
		return array;

	}
	
	
	//
	public static int[][] leer_matrizarray_enteros() {
		
		int matriz[][];
		
		System.out.println("¿Cuantas filas tendrá la matriz?");
		matriz = new int[Teclado.lecturacomparativa(0, Teclado.Comparacion.MAYOR)][];
		
		
		for(int i=0;i<matriz.length;i++){
			System.out.println("Introduzca el tamaño de la fila " + i + ": ");
			matriz[i] = new int[Teclado.lecturacomparativa(0, Teclado.Comparacion.MAYOR)];
			
			System.out.printf("\nIntroduzca los números de la fila " + i + ": ");
			
			for(int j=0;j<matriz[i].length;j++) {
				System.out.printf("\n[%d][%d]: ",i,j);
				matriz[i][j] = Teclado.leerint();
			}
			
			System.out.println();
			
		}
		
		return matriz;
	}
	
	

}