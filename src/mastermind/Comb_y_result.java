package mastermind;

import java.util.Arrays;

/**
 * Esta además de una combinacion, tiene su resultado, que es un conjunto de objetos tipo Indicador y puede hacer comprobaciones y calculos de sobre esta además de dibujarla.
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */
public class Comb_y_result extends Combinacion {
	/**
	 * Almacena un array de Indicador.
	 * @see Indicador
	 */
	private Indicador resultados[];
	
	/**
	 * Construye un nuevo objeto con todos las posiciones con los indicadores por defecto.
	 * @param num_casillas numero de casillas de la combinación que es igual que el de los indicadores, que viene determinado por la dificultad
	 * @see Indicador , Casilla
	 */
	public Comb_y_result(int num_casillas) {
		super(num_casillas);
		resultados = new Indicador[num_casillas];// El numero de casillas es igual que el numero de indicadores
		for (int i = 0; i < resultados.length; i++) {
			resultados[i] = new Indicador();
		}
	}

	// Metodo para dibujar la combinacion y su resultado
	/**
	 * Dibuja la combinación y el resultado.
	 */
	public void dibujar_elemento() {
		super.dibujar_elemento();
		for (int i = 0; i < tamanio(); i++) {
			resultados[i].dibujar_elemento();

		}
		System.out.printf("|");

	}
	
	/**
	 * Dibuja solo la combinacion.
	 */
	public void dibujar_solo_combinacion() {
		super.dibujar_elemento();
	}
	
	/**
	 * Coloca los indicadores negros y grises según los numeros que recibe.
	 * @param cant_ind_Negros cantidad de indicadores negros (mismo color y posicion)
	 * @param cant_ind_Grises cantidad de indicadores grises (solo mismo color)
	 */
	protected void colocar_respuesta(int cant_ind_Negros, int cant_ind_Grises) {
		int i;

		for (i = 0; i < cant_ind_Negros; i++) { // Coloca los indicadores negros en el resultado de la última
												// combinación
			resultados[i].setColor(Color.IND_NEGRO);
		}
		for (i = cant_ind_Negros; i < cant_ind_Negros + cant_ind_Grises; i++) { // Coloca los indicadores blancos en el
																					// resultado de la última
																					// combinación
			resultados[i].setColor(Color.IND_GRIS);
		}

	}
	
	/**
	 * Compara los resultados(indicadores negros y grises) de esta combinacion y la que se le pasa.
	 * @param comb_y_result_rival		Objeto de tipo comb_y_result del rival.
	 * @return 1: si el esta combinacion es mejor que la del rival 
	 * 		   2: si la combinacion del rival es mejor
	 * 		   0: si son iguales
	 */
	protected int comparar_respuestas(Comb_y_result comb_y_result_rival) {
		int ind_Negros_j1, ind_Negros_j2, ind_Grises_j1, ind_Grises_j2,resultado;
		
		ind_Negros_j1 = ind_Negros_j2 = ind_Grises_j1 = ind_Grises_j2 = resultado = 0;
		
		for (int i = 0; i < combinacion.length; i++) {
			if (resultados[i].getColor() == Color.IND_NEGRO) {
				ind_Negros_j1++;
			} else if (resultados[i].getColor() == Color.IND_GRIS) {
				ind_Grises_j1++;
			}

			if (comb_y_result_rival.getResultados()[i].getColor() == Color.IND_NEGRO) {
				ind_Negros_j2++;
			} else if (comb_y_result_rival.getResultados()[i].getColor() == Color.IND_GRIS) {
				ind_Grises_j2++;
			}
		}
		
		if(ind_Negros_j1 > ind_Negros_j2) {
			resultado = 1;
		}else if(ind_Negros_j1 < ind_Negros_j2) {
			resultado = 2;
		}else {
			if(ind_Grises_j1 > ind_Grises_j2) {
				resultado = 1;
			}else if(ind_Grises_j1 < ind_Grises_j2) {
				resultado = 2;
			}
		}
		return resultado;
	}

	

	// Equals
	/**
	 * Compara este combinacion y su resultado con el objeto que se le pasa.
	 * @param obj El objeto con el que se va a comparar.
	 * @return true: si son iguales.
	 * 		   false: si son diferentes.
	 */
	public boolean equals(Object obj) {
		boolean resultado = false;
		if (obj instanceof Comb_y_result && super.equals(obj)
				&& Arrays.equals(((Comb_y_result) obj).resultados, resultados)) {
			resultado = true;
		}
		return resultado;
	}

	// Getter
	public Indicador[] getResultados() {
		return resultados;
	}

}
