package mastermind;

import java.util.ArrayList;
import java.util.ListIterator;

public class Tablero implements Dibujable {
	
	private ArrayList<Comb_y_result> comb_y_resultados;
	private Combinacion cifrado;
	
	public Tablero(Dificultad dificultad){
		cifrado = new Combinacion(dificultad.casillas); // Fila del cifrado
		comb_y_resultados = new ArrayList<>(); // Combinaciones y sus respectivos resultados
	}

	
	public void dibujar_elemento() {
		final int NUM_CASILLAS,NUM_BORDES,SEPARACION = 2,BORDE_x_CASILLA=3,BORDE_x_ACIERTO=2;
		ListIterator<Comb_y_result> itComb; //Iterador para las combinaciones
		
		NUM_CASILLAS = cifrado.tamanio();
		NUM_BORDES = 2*SEPARACION + NUM_CASILLAS * BORDE_x_CASILLA + NUM_CASILLAS * BORDE_x_ACIERTO;
		
		for (int i = 0; i < NUM_BORDES; i++) {
			System.out.printf("_");//Tantos _ como caracteres ocupe el ancho del tablero (cada casilla son tres _ y cada acierto dos, el borde izquierdo son otros dos y las separacion dos mas
		}
		
		System.out.println();
		
		for (itComb = comb_y_resultados.listIterator(comb_y_resultados.size()); itComb.hasPrevious();) {
			
			Comb_y_result comb_y_resultado = itComb.previous();
			
			comb_y_resultado.dibujar_elemento();
			
			System.out.printf("|");
			if (itComb.hasPrevious()) {
				for (int i = 0; i < (NUM_BORDES - SEPARACION - NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf(" "); //Numero de espacios desde el borde izquierdo hasta la primera separación
				}
				System.out.printf("|");
				for (int i = 0; i < (SEPARACION + NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf(" "); //Numero de espacios desde la separación hasta el borde derecho
				}
				System.out.println("|");
			}else {
				for (int i = 0; i < (NUM_BORDES - SEPARACION - NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf("_"); //Numero de guiones bajos(borde inferior) desde el borde izquierdo hasta la primera separación
				}
				System.out.printf("|");
				for (int i = 0; i < (SEPARACION + NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf("_"); //Numero de guiones bajos(borde inferior) desde la separación hasta el borde derecho
				}
				System.out.println("|");
			}
		}

		cifrado.dibujar_elemento();
		System.out.println("\n\n");
	}
	
	public void dibujar_con_cifr_oculto() {
		final int NUM_CASILLAS,NUM_BORDES,SEPARACION = 2,BORDE_x_CASILLA=3,BORDE_x_ACIERTO=2;
		ListIterator<Comb_y_result> itComb; //Iterador para las combinaciones
		
		NUM_CASILLAS = cifrado.tamanio();
		NUM_BORDES = 2*SEPARACION + NUM_CASILLAS * BORDE_x_CASILLA + NUM_CASILLAS * BORDE_x_ACIERTO;
		
		for (int i = 0; i < NUM_BORDES; i++) {
			System.out.printf("_");//Tantos _ como caracteres ocupe el ancho del tablero (cada casilla son tres _ y cada acierto dos, el borde izquierdo son otros dos y las separacion dos mas
		}
		
		System.out.println();
		
		for (itComb = comb_y_resultados.listIterator(comb_y_resultados.size()); itComb.hasPrevious();) {
			
			Comb_y_result comb_y_resultado = itComb.previous();
			
			comb_y_resultado.dibujar_elemento();
			
			System.out.printf("|");
			if (itComb.hasPrevious()) {
				for (int i = 0; i < (NUM_BORDES - SEPARACION - NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf(" "); //Numero de espacios desde el borde izquierdo hasta la primera separación
				}
				System.out.printf("|");
				for (int i = 0; i < (SEPARACION + NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf(" "); //Numero de espacios desde la separación hasta el borde derecho
				}
				System.out.println("|");
			}else {
				for (int i = 0; i < (NUM_BORDES - SEPARACION - NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf("_"); //Numero de guiones bajos(borde inferior) desde el borde izquierdo hasta la primera separación
				}
				System.out.printf("|");
				for (int i = 0; i < (SEPARACION + NUM_CASILLAS * BORDE_x_ACIERTO) - 1; i++) {
					System.out.printf("_"); //Numero de guiones bajos(borde inferior) desde la separación hasta el borde derecho
				}
				System.out.println("|");
			}
		}

		cifrado.dibujar_elemento_oculto();;
		System.out.println("\n\n");
	}
	
	public void dibujar_comb_actual() {
		comb_y_resultados.get(ultima_combinacion_y_result()).dibujar_solo_combinacion();
	}
	
	public void dibujar_cifrado() {
		cifrado.dibujar_elemento();
	}
	
	
	public void aniadir_combinacion() {
		comb_y_resultados.add(new Comb_y_result(cifrado.tamanio()));
	}
	
	public int ultima_combinacion_y_result() {
		return (comb_y_resultados.size() - 1);
	}
	
	public int numero_de_casillas() {
		return cifrado.tamanio();
	}
	
	public Casilla[] coger_ultima_combinacion() {
		return comb_y_resultados.get(ultima_combinacion_y_result()).getCombinacion();
	}
	
	public Comb_y_result coger_ultima_combinacion_y_result() {
		return comb_y_resultados.get(ultima_combinacion_y_result());
	}
	
	public Byte calcular_resultado(int posicion) {
		int ind_negros,ind_grises;
		
		ind_negros = ind_grises = 0;
		
		for (int i = 0; i < comb_y_resultados.get(posicion).getResultados().length; i++) {
			if(comb_y_resultados.get(posicion).getResultados()[i].getColor()==Color.IND_NEGRO) {
				ind_negros++;
			}else if(comb_y_resultados.get(posicion).getResultados()[i].getColor()==Color.IND_GRIS) {
				ind_grises++;
			}
		}
		
		return (byte)((ind_negros * 10) + ind_grises);
	}
	
	public Combinacion getCifrado() {
		return cifrado;
	}
	
	public ArrayList<Comb_y_result> getComb_y_result(){
		return comb_y_resultados;
	}

}
