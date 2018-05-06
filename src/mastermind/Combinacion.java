package mastermind;


/**
 * Esta clase guarda una combinación, que es un conjunto de objetos tipo Casilla y puede hacer comprobaciones y calculos de sobre esta además de dibujarla.
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */

import java.util.Arrays;

public class Combinacion implements CombinacionDibujable, Cloneable {
	/**
	 * Almacena un array de Casilla.
	 * @see Casilla
	 */
	protected Casilla[] combinacion;

	// Constructor
	/**
	 * Construye un nuevo objeto con todos las posiciones con las casillas por defecto.
	 */
	public Combinacion(int num_casillas) {
		combinacion = new Casilla[num_casillas];
		for (int i = 0; i < combinacion.length; i++) {
			combinacion[i] = new Casilla();
		}
	}

	// Getter
	/**
	 * Devuelve la combinacion,el array de casillas.
	 * @return Casilla[]
	 */
	public Casilla[] getCombinacion() {
		return combinacion;
	}

	// Equals
	/**
	 * Compara esta combinacion con el objeto que se le pasa.
	 * @param obj El objeto con el que se va a comparar.
	 * @return true: si son iguales.
	 * 		   false: si son diferentes.
	 */
	public boolean equals(Object obj) {
		boolean resultado = false;
		if (obj instanceof Combinacion && Arrays.equals(((Combinacion) obj).combinacion, combinacion)) {
			resultado = true;
		}
		return resultado;
	}

	// Metodos aparte
	/**
	 * Dibuja la combinación, llamando al dibujar de cada casilla dentro de esta.
	 * @see Casilla
	 */
	@Override
	public void dibujar_elemento() {
		System.out.printf("| ");
		for (int i = 0; i < tamanio(); i++) {
			combinacion[i].dibujar_elemento();
		}
		System.out.printf("| ");

	}
	
	/**
	 * Dibuja la combinación pero con todas las casillas en negro (Oculto).
	 */
	public void dibujar_elemento_oculto() {
		System.out.printf("| ");
		for (int i = 0; i < tamanio(); i++) {
			System.out.printf(Color.NEGRO.getCod_Color() + "  " + Color.RESETEAR.getCod_Color() + " ");
		}
		System.out.printf("| ");

	}
	/**
	 * Devuelve el tamanio de esta combinacion.
	 * @return El tamanio de la combinacion
	 */
	public int tamanio() {
		return combinacion.length;
	}
	
	/**
	 * Cambia el color de una de las casillas de esta combinación según el color y la posición que reciba.
	 * @param posicion El numero que indica la posicion 
	 * @param color El color que se pondra en la casilla de esa posicion
	 * @see Casilla
	 */
	public void cambiar_color_casilla(int posicion, Color color) {
		combinacion[posicion].setColor(color);
	}
	
	/**
	 * Comprueba si hay colores repetidos en esta combinacion.
	 * @return true: si hay colores repetidos
	 * 		   false: si no hay colores repetidos
	 * @see Casilla
	 */
	public boolean comprobar_colores_repes() {
		boolean color_repetido = false;
		int i, j;

		for (i = 0; i < combinacion.length; i++) {
			for (j = i + 1; j < combinacion.length; j++) {
				if (combinacion[i].equals(combinacion[j])) {
					color_repetido = true;
				}
			}
		}

		return color_repetido;
	}

	/**
	 * Comprueba si los indicadores que ha introducido el usuario son correctos, corresponden con lo que hay en el cifrado.
	 * @param cifrado La combinacion que actua como cifrado
	 * @param num_ind_Negros	Los indicadores negros(mismo color y posicion)
	 * @param num_ind_Grises	Los indicadores grises (solo mismo color)
	 * @return true: si ha introducido bien los indicadores
	 * 		   false: si los ha introducido mal
	 */
	protected boolean comprobar_respuesta(Combinacion cifrado, int num_ind_Negros, int num_ind_Grises) {
		int i, j, ind_Negros, ind_Blancos;
		final int NUM_CASILLAS = combinacion.length;
		boolean en_resultado[] = new boolean[NUM_CASILLAS], resultado = false;

		ind_Negros = ind_Blancos = 0;

		for (i = 0; i < en_resultado.length; i++) {
			en_resultado[i] = false;
		}

		for (i = 0; i < NUM_CASILLAS; i++) {
			if (combinacion[i].color.equals(cifrado.getCombinacion()[i].getColor())) {
				ind_Negros++;
				en_resultado[i] = true;
			}
		}

		for (i = 0; i < NUM_CASILLAS; i++) {

			for (j = 0; j < NUM_CASILLAS; j++) {
				if (!en_resultado[j]) {
					if (combinacion[i].getColor().equals(cifrado.getCombinacion()[j].getColor())) {
						ind_Blancos++;
						en_resultado[j] = true;
					}
				}
			}
		}
		
		if (num_ind_Negros == ind_Negros && num_ind_Grises == ind_Blancos) {
			resultado = true;
		}
		return resultado;

	}
	
	
	/**
	 * Devuelve la cantidad de indicadores negros y grises que daría como resultado esta combinacion.
	 * @param cifrado	Combinacion que actua como cifrado y que se usara para comparar colores.
	 * @return Array de dos numeros que contiene tras los indicadores negros en la primera posicion y los grises en la segunda posicion.
	 * @see Casilla
	 */
	protected int[] calcular_respuesta(Combinacion cifrado) {
		int i, j,ind_Negr_Blan[] = {0,0};
		final int NUM_CASILLAS = combinacion.length;
		boolean en_resultado[] = new boolean[NUM_CASILLAS];
		

		for (i = 0; i < en_resultado.length; i++) {
			en_resultado[i] = false;
		}

		for (i = 0; i < NUM_CASILLAS; i++) {
			if (combinacion[i].color.equals(cifrado.getCombinacion()[i].getColor())) {
				ind_Negr_Blan[0]++;
				en_resultado[i] = true;
			}
		}

		for (i = 0; i < NUM_CASILLAS; i++) {

			for (j = 0; j < NUM_CASILLAS; j++) {
				if (!en_resultado[j]) {
					if (combinacion[i].getColor().equals(cifrado.getCombinacion()[j].getColor())) {
						ind_Negr_Blan[1]++;
						en_resultado[j] = true;
					}
				}
			}
		}
			
		return ind_Negr_Blan;

	}


}
