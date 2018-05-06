package mastermind;

/**
 * Esta clase almacena las combinaciones y resultados ademas del cifrado que forma un tablero, y permite dibujarlo de varias formas además de ser modificado.
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */

import java.util.ArrayList;
import java.util.ListIterator;

public class Tablero implements TableroDibujable {
	/**
	 * Almacena un lista de Combinaciones y resultados.
	 * @see Comb_y_result
	 */
	private ArrayList<Comb_y_result> comb_y_resultados;
	
	/**
	 * Almacena una combinacion que sirve como cifrado del tablero.
	 * @see Combinacion
	 */
	private Combinacion cifrado;

	
	/**
	 * Construye un nuevo objeto con el cifrado por defecto (todas las casillas en negro) y con la lista de combinaciones y resultados vacia.
	 * @param dificultad 	dificultad con el numero de casillas deseado numero de casillas de la combinación que es igual que el de los indicadores
	 * @see Combinacion
	 * @see Comb_y_result
	 */
	public Tablero(Dificultad dificultad) {
		cifrado = new Combinacion(dificultad.casillas); // Fila del cifrado
		comb_y_resultados = new ArrayList<>(); // Combinaciones y sus respectivos resultados
	}
	
	/**
	 * Dibuja el tablero llamando a los metodos dibujar de los elementos en su interior además de aportando una estructura de tablero.
	 */
	public void dibujar_elemento() {
		final int NUM_CASILLAS, NUM_BORDES, SEPARACION = 2, BORDE_x_CASILLA = 3, BORDE_x_ACIERTO = 2;
		ListIterator<Comb_y_result> itComb; // Iterador para las combinaciones

		NUM_CASILLAS = cifrado.tamanio();
		NUM_BORDES = 2 * SEPARACION + NUM_CASILLAS * BORDE_x_CASILLA + NUM_CASILLAS * BORDE_x_ACIERTO;

		for (int i = 0; i < NUM_BORDES; i++) {
			System.out.printf("_");// Tantos _ como caracteres ocupe el ancho del tablero (cada casilla son tres _
									// y cada acierto dos, el borde izquierdo son otros dos y las separacion dos mas
		}

		System.out.println();

		for (itComb = comb_y_resultados.listIterator(comb_y_resultados.size()); itComb.hasPrevious();) {

			Comb_y_result comb_y_resultado = itComb.previous();

			comb_y_resultado.dibujar_elemento();

			System.out.printf("|");
			if (itComb.hasPrevious()) {
				for (int i = 0; i < (NUM_BORDES - SEPARACION - NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf(" "); // Numero de espacios desde el borde izquierdo hasta la primera separación
				}
				System.out.printf("|");
				for (int i = 0; i < (SEPARACION + NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf(" "); // Numero de espacios desde la separación hasta el borde derecho
				}
				System.out.println("|");
			} else {
				for (int i = 0; i < (NUM_BORDES - SEPARACION - NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf("_"); // Numero de guiones bajos(borde inferior) desde el borde izquierdo hasta la
											// primera separación
				}
				System.out.printf("|");
				for (int i = 0; i < (SEPARACION + NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf("_"); // Numero de guiones bajos(borde inferior) desde la separación hasta el
											// borde derecho
				}
				System.out.println("|");
			}
		}

		cifrado.dibujar_elemento();
		System.out.println("\n\n");
	}
	
	/**
	 * Dibuja el tablero igual que el metodo dibujar_elemento() pero con la combinación oculta.
	 * @see #dibujar_elemento()
	 */
	public void dibujar_con_cifr_oculto() {
		final int NUM_CASILLAS, NUM_BORDES, SEPARACION = 2, BORDE_x_CASILLA = 3, BORDE_x_ACIERTO = 2;
		ListIterator<Comb_y_result> itComb; // Iterador para las combinaciones

		NUM_CASILLAS = cifrado.tamanio();
		NUM_BORDES = 2 * SEPARACION + NUM_CASILLAS * BORDE_x_CASILLA + NUM_CASILLAS * BORDE_x_ACIERTO;

		for (int i = 0; i < NUM_BORDES; i++) {
			System.out.printf("_");// Tantos _ como caracteres ocupe el ancho del tablero (cada casilla son tres _
									// y cada acierto dos, el borde izquierdo son otros dos y las separacion dos mas
		}

		System.out.println();

		for (itComb = comb_y_resultados.listIterator(comb_y_resultados.size()); itComb.hasPrevious();) {

			Comb_y_result comb_y_resultado = itComb.previous();

			comb_y_resultado.dibujar_elemento();

			System.out.printf("|");
			if (itComb.hasPrevious()) {
				for (int i = 0; i < (NUM_BORDES - SEPARACION - NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf(" "); // Numero de espacios desde el borde izquierdo hasta la primera separación
				}
				System.out.printf("|");
				for (int i = 0; i < (SEPARACION + NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf(" "); // Numero de espacios desde la separación hasta el borde derecho
				}
				System.out.println("|");
			} else {
				for (int i = 0; i < (NUM_BORDES - SEPARACION - NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf("_"); // Numero de guiones bajos(borde inferior) desde el borde izquierdo hasta la
											// primera separación
				}
				System.out.printf("|");
				for (int i = 0; i < (SEPARACION + NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf("_"); // Numero de guiones bajos(borde inferior) desde la separación hasta el
											// borde derecho
				}
				System.out.println("|");
			}
		}

		cifrado.dibujar_elemento_oculto();
		;
		System.out.println("\n\n");
	}
	
	/**
	 * Dibuja el tablero actual y el que se le pasa, normalmente el del rival, uno al lado del otro.
	 * @param tableroRival Tablero del rival.
	 */
	public void dibujar_tableros_lado_a_lado(Tablero tableroRival) {
		final int SEPARACION_TABLEROS = 13,NUM_ESPACIOS, NUM_CASILLAS, NUM_BORDES, SEPARACION = 2, BORDE_x_CASILLA = 3, BORDE_x_ACIERTO = 2;
		ListIterator<Comb_y_result> itTabl1, itTabl2; // Iterador para las combinaciones
		Comb_y_result comb_y_resultado_tabl1, comb_y_resultado_tabl2;

		itTabl1 = itTabl2 = null;

		NUM_CASILLAS = cifrado.tamanio();
		NUM_BORDES = SEPARACION + NUM_CASILLAS * BORDE_x_CASILLA;
		NUM_ESPACIOS = NUM_CASILLAS * BORDE_x_CASILLA + NUM_CASILLAS;

		if (comb_y_resultados.size() == 0 && cifrado.tamanio() > 0) {
			for (int i = 0; i < NUM_BORDES / 2; i++) {
				System.out.printf(" ");// Tantos _ como caracteres ocupe el ancho del tablero (cada casilla son tres _
										// y cada acierto dos, el borde izquierdo son otros dos y las separacion dos mas
			}
			System.out.print("IA 1");

			for (int i = 0; i < NUM_BORDES + NUM_ESPACIOS - 5; i++) {
				System.out.printf(" ");// Tantos _ como caracteres ocupe el ancho del tablero (cada casilla son tres _
										// y cada acierto dos, el borde izquierdo son otros dos y las separacion dos mas
			}
			System.out.println("IA 2");

			for (int i = 0; i < NUM_BORDES; i++) {
				System.out.printf("_");// Tantos _ como caracteres ocupe el ancho del tablero (cada casilla son tres _
										// y cada acierto dos, el borde izquierdo son otros dos y las separacion dos mas
			}

			for (int i = 0; i < NUM_ESPACIOS; i++) {
				System.out.printf(" ");// Tantos espacios lo que ocupa un tablero para la separación entre tableros
			}

			for (int i = 0; i < NUM_BORDES; i++) {
				System.out.printf("_");// Tantos _ como caracteres ocupe el ancho del tablero (cada casilla son tres _
										// y cada acierto dos, el borde izquierdo son otros dos y las separacion dos mas
			}
			System.out.println();

			cifrado.dibujar_elemento();

			System.out.printf(Color.ROJO.getCod_Color() + "Cifrado" + Color.RESETEAR.getCod_Color());

			for (int i = 0; i < NUM_ESPACIOS - 9; i++) {
				System.out.printf(" ");// Tantos espacios lo que ocupa un tablero para la separación entre tableros
			}

			tableroRival.getCifrado().dibujar_elemento();
			System.out.printf(Color.ROJO.getCod_Color() + "Cifrado" + Color.RESETEAR.getCod_Color());
			System.out.println();
			System.out.printf("|");
			for (int i = 0; i < SEPARACION - 1 + NUM_CASILLAS * BORDE_x_CASILLA; i++) {
				System.out.printf("-"); // Numero de espacios desde el borde izquierdo hasta la primera separación
			}
			System.out.printf("|");
			for (int i = 0; i < SEPARACION - 1 + NUM_CASILLAS * BORDE_x_ACIERTO; i++) {
				System.out.printf(" "); // Numero de espacios desde la separación hasta el borde derecho
			}
			System.out.printf("|");

			for (int i = 0; i < SEPARACION_TABLEROS ; i++) {
				System.out.printf(" ");// Tantos espacios lo que ocupa un tablero para la separación entre tableros
			}

			System.out.printf("|");
			for (int i = 0; i < SEPARACION - 1 + NUM_CASILLAS * BORDE_x_CASILLA; i++) {
				System.out.printf("-"); // Numero de espacios desde el borde izquierdo hasta la primera separación
			}
			System.out.printf("|");
			for (int i = 0; i < SEPARACION - 1 + NUM_CASILLAS * BORDE_x_ACIERTO; i++) {
				System.out.printf(" "); // Numero de espacios desde la separación hasta el borde derecho
			}
			System.out.printf("|");
			
			System.out.println();

		} else {
			itTabl1 = comb_y_resultados.listIterator(comb_y_resultados.size() - 1);
			itTabl2 = tableroRival.getComb_y_result().listIterator(comb_y_resultados.size() - 1);
			
			if (itTabl1.hasNext() && itTabl2.hasNext()) {
				
				comb_y_resultado_tabl1 = itTabl1.next();
				comb_y_resultado_tabl2 = itTabl2.next();
				
				// Dibuja las combinaciones
				comb_y_resultado_tabl1.dibujar_elemento();
				for (int i = 0; i < SEPARACION_TABLEROS ; i++) {
					System.out.printf(" ");
				}
				
				comb_y_resultado_tabl2.dibujar_elemento();
				System.out.printf("Nº" + (itTabl1.nextIndex() - 1));
				System.out.println();

				// Dibuja una linea con la estructura del tablero pero sin una combinación, para
				// separar combinaciones
				//Para el tablero 1
				System.out.printf("|");
				for (int i = 0; i < SEPARACION - 1 + NUM_CASILLAS * BORDE_x_CASILLA; i++) {
					System.out.printf(" "); // Numero de espacios desde el borde izquierdo hasta la primera separación
				}
				System.out.printf("|");
				for (int i = 0; i < SEPARACION - 1 + NUM_CASILLAS * BORDE_x_ACIERTO; i++) {
					System.out.printf(" "); // Numero de espacios desde la separación hasta el borde derecho
				}
				System.out.printf("|");

				for (int i = 0; i < SEPARACION_TABLEROS ; i++) {
					System.out.printf(" ");// Tantos espacios lo que ocupa un tablero para la separación entre tableros
				}

				System.out.printf("|");
				for (int i = 0; i < SEPARACION - 1 + NUM_CASILLAS * BORDE_x_CASILLA; i++) {
					System.out.printf(" "); // Numero de espacios desde el borde izquierdo hasta la primera separación
				}
				System.out.printf("|");
				for (int i = 0; i < SEPARACION - 1 + NUM_CASILLAS * BORDE_x_ACIERTO; i++) {
					System.out.printf(" "); // Numero de espacios desde la separación hasta el borde derecho
				}
				System.out.printf("|");
				
				System.out.println();

				
			}
		}

	}
	
	/**
	 * Dibuja la ultima combinación introducida en el tablero sin su resultado.
	 * @see Comb_y_result
	 */
	public void dibujar_comb_actual() {
		comb_y_resultados.get(ultima_comb_y_result()).dibujar_solo_combinacion();
	}
	
	/**
	 * Dibuja el cifrado del tablero igual que digujaria una combinacion.
	 * @see Combinacion
	 */
	public void dibujar_cifrado() {
		cifrado.dibujar_elemento();
	}
	
	/**
	 * Añade una combinacion y su resultado correspondiente al tablero según el tamaño del cifrado que habrá sido determinado previamente por la dificultad.
	 */
	public void aniadir_combinacion() {
		comb_y_resultados.add(new Comb_y_result(cifrado.tamanio()));
	}

	/**
	 * Devuelve el numero correspondiente al la ultima combinacion añadida al tablero.
	 * @return Indice de la ultima combinacion
	 */
	public int ultima_comb_y_result() {
		return (comb_y_resultados.size() - 1);
	}
	
	/**
	 * Devuelve el numero correspondiente al la ultima combinacion añadida al tablero.
	 * @return Indice de la ultima combinacion
	 */
	public int numero_de_casillas() {
		return cifrado.tamanio();
	}
	
	/**
	 * Devuelve la ultima combinacion del tablero, sin su resultado.
	 * @return Lista de tipo casilla que tiene la ultima combinacion
	 */
	public Casilla[] coger_ultima_combinacion() {
		return comb_y_resultados.get(ultima_comb_y_result()).getCombinacion();
	}
	
	/**
	 * Devuelve la ultima combinacion del tablero, incluyendo el resultado correspondiente.
	 * @return Ultima combinacion y resultado del tablero
	 */
	public Comb_y_result coger_ultima_comb_y_result() {
		return comb_y_resultados.get(ultima_comb_y_result());
	}
	
	/**
	 *	Devuelve un número correpondiente al valor del resultado de una combinacion, calculo = indicadores negros x 10 + indicadores grises.
	 * @param posicion indice de la combinacion de la que se quiere calcular el resultado
	 * @return Numero que representa el valor de una combinacion
	 */
	public Byte calcular_resultado(int posicion) {
		int ind_negros, ind_grises;

		ind_negros = ind_grises = 0;

		for (int i = 0; i < comb_y_resultados.get(posicion).getResultados().length; i++) {
			if (comb_y_resultados.get(posicion).getResultados()[i].getColor() == Color.IND_NEGRO) {
				ind_negros++;
			} else if (comb_y_resultados.get(posicion).getResultados()[i].getColor() == Color.IND_GRIS) {
				ind_grises++;
			}
		}

		return (byte) ((ind_negros * 10) + ind_grises);
	}
	
	//Getter
	/**
	 * Devuelve el cifrado.
	 * @return Combinacion correspondiente al cifrado del tablero actual.
	 */
	public Combinacion getCifrado() {
		return cifrado;
	}

	/**
	 * Devuelve la lista de combinaciones y resultados.
	 * @return lista de combinaciones y resultados del tablero actual
	 */
	public ArrayList<Comb_y_result> getComb_y_result() {
		return comb_y_resultados;
	}

}
