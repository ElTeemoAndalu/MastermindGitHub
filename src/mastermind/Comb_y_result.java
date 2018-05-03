package mastermind;

import java.util.Arrays;

public class Comb_y_result extends Combinacion {

	private Indicador resultados[];

	public Comb_y_result(int num_casillas) {
		super(num_casillas);
		resultados = new Indicador[num_casillas];// El numero de casillas es igual que el numero de indicadores
		for (int i = 0; i < resultados.length; i++) {
			resultados[i] = new Indicador();
		}
	}

	// Metodo para dibujar la combinacion y su resultado
	public void dibujar_elemento() {
		super.dibujar_elemento();
		for (int i = 0; i < tamanio(); i++) {
			resultados[i].dibujar_elemento();

		}
		System.out.println("|");

	}

	public void dibujar_solo_combinacion() {
		super.dibujar_elemento();
	}

	protected void colocar_respuesta(int cant_ind_Negros, int cant_ind_Blancos) {
		int i;

		for (i = 0; i < cant_ind_Negros; i++) { // Coloca los indicadores negros en el resultado de la última
												// combinación
			resultados[i].setColor(Color.IND_NEGRO);
		}
		for (i = cant_ind_Negros; i < cant_ind_Negros + cant_ind_Blancos; i++) { // Coloca los indicadores blancos en el
																					// resultado de la última
																					// combinación
			resultados[i].setColor(Color.IND_GRIS);
		}

	}

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
	public boolean equals(Object obj) {
		boolean resultado = false;
		if (obj instanceof Comb_y_result && super.equals(obj)
				&& Arrays.equals(((Comb_y_result) obj).resultados, resultados)) {
			resultado = true;
		}
		return resultado;
	}

	// Clone
	public Object clone() {
		Comb_y_result clonado;
		clonado = (Comb_y_result) super.clone();
		clonado.resultados = resultados.clone();
		for (int i = 0; i < resultados.length; i++) {
			clonado.resultados[i] = (Indicador) resultados[i].clone();
		}
		return clonado;
	}

	// Getter
	public Indicador[] getResultados() {
		return resultados;
	}

}
