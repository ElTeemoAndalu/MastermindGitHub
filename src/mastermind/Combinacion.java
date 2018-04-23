package mastermind;

import java.util.Arrays;

public class Combinacion implements Dibujable, Cloneable {

	protected Casilla[] combinacion;
	
	// Constructor
	public Combinacion(int num_casillas) {
		combinacion = new Casilla[num_casillas];
		for (int i = 0; i < combinacion.length; i++) {
			combinacion[i] = new Casilla();
		}
	}
	// Getter
	public Casilla[] getCombinacion() {
		return combinacion;
	}
	

	// Equals
	public boolean equals(Object obj) {
		boolean resultado = false;
		if (obj instanceof Combinacion && Arrays.equals(((Combinacion) obj).combinacion, combinacion)) {
			resultado = true;
		}
		return resultado;
	}

	


	// Metodos aparte
	@Override
	public void dibujar_elemento() {
		System.out.printf("| ");
		for (int i = 0; i < tamanio(); i++) {
			combinacion[i].dibujar_elemento();
		}
		System.out.printf("| ");

	}
	
	public int tamanio() {
		return combinacion.length;
	}
	
	public boolean es_comb_llena() {
		boolean esta_llena = false;
		int cont = 0;
		
		for (int i = 0; i < combinacion.length; i++) {
			if (combinacion[i] == null) {
				cont++;
			}
		}
		
		if (cont==0) {
			esta_llena = true;
		}
		
		return esta_llena;
	}
	
	public void cambiar_color_casilla(int posicion,Color color) {
		combinacion[posicion].setColor(color);
	}
	
	public boolean comprobar_colores_repes() {
		boolean color_repetido = false;
		int i,j;
		
		for (i = 0; i < combinacion.length; i++) {
			for (j = i + 1; j < combinacion.length; j++) {
				if (combinacion[i].equals(combinacion[j])) {
					color_repetido = true;
				}
			}
		}
		
		return color_repetido;
	}
	
	protected boolean comprobar_respuesta(Combinacion cifrado,int num_ind_Negros,int num_ind_Blancos) {
		int j,ind_Negros,ind_Blancos;
		final int NUM_CASILLAS = combinacion.length;
		boolean en_resultado[] = new boolean[NUM_CASILLAS],resultado = false;
		
		ind_Negros = ind_Blancos = 0;
		
		for (int i = 0; i < NUM_CASILLAS; i++) {
			for (j = 0; j < NUM_CASILLAS; j++) {
				if (!en_resultado[i]) {
					if (combinacion[i].color.equals(cifrado.getCombinacion()[j].getColor()) && i == j ) {
						ind_Negros++;
						en_resultado[j] = true;
						j = NUM_CASILLAS;
					} else if (combinacion[i].getColor().equals(cifrado.getCombinacion()[j].getColor())) {
						ind_Blancos++;
						en_resultado[j] = true;
						j = NUM_CASILLAS;
					} 
				}
			}
		}
		if(num_ind_Negros == ind_Negros && num_ind_Blancos == ind_Blancos) {
			resultado = true;
		}
		return resultado;
		
	}
	
	
	
	/*protected boolean comprobar_respuesta() {
		int i,j,ind_Negros;
		final int NUM_CASILLAS = cifrado.combinacion.length;
		ind_Negros = 0;
		boolean en_resultado[] = new boolean[NUM_CASILLAS],resultado = false;
		
		for (i = 0; i < NUM_CASILLAS; i++) {
			for (j = 0; i < NUM_CASILLAS; i++) {
				if (!en_resultado[j]) {
					if (comb_y_resultados.get(comb_y_resultados.size() - 1).getCombinacion()[i].getColor().equals(cifrado.getCombinacion()[j].getColor()) && i == j ) {
						ind_Negros++;
						en_resultado[j] = true;
						j = NUM_CASILLAS;
					}
				}
			}
		}
		
		if(ind_Negros == NUM_CASILLAS) {
			resultado = true;
		}
		
		return resultado;
		
	}*/

	// Clone
	public Object clone() {
		Combinacion clonado;
		try {
			clonado = (Combinacion) super.clone();
			clonado.combinacion = combinacion.clone();
			for (int i = 0; i < combinacion.length; i++) {
				clonado.combinacion[i] = (Casilla) combinacion[i].clone();
			}
		} catch (CloneNotSupportedException e) {
			clonado = null;
		}
		return clonado;
	}

}
