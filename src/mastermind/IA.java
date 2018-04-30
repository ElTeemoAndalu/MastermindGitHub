package mastermind;

import java.util.HashMap;
import java.util.Random;

public class IA extends Jugador {
	
	private HashMap<Combinacion, Integer[]> tableroAux;

	protected IA(Dificultad dificultad) {
		tablero = new Tablero(dificultad);
		turno = 0;
		filaCreada = false;
	}

	protected IA(Dificultad dificultad, boolean cifrando) {
		if (dificultad == Dificultad.FACIL && cifrando) {
			tablero = new Tablero(dificultad);
		}
		
		if (cifrando) {
			turno = 1;
		}else {
			turno = 0;
		}
		
		filaCreada = false;
	}

	public Tablero getTablero() {
		return tablero;
	}

	protected void introducir_bola(int posicion, Color color, Tablero tablero) {
		if (turno > 0) {
			tablero.coger_ultima_combinacion()[posicion].setColor(color);
		} else {
			tablero.coger_cifrado()[posicion].setColor(color);
		}
	}

	public void introducir_fila(Combinacion combinacion) {
		if (!filaCreada) {
			tablero.aniadir_combinacion();
			filaCreada = true;
		}
		for (int i = 0; i < tablero.getCifrado().tamanio(); i++) {
			introducir_bola(i, combinacion.getCombinacion()[i].getColor(), tablero);
		}
		confirmar_fila(tablero);

	}

	public void introducir_cifrado(Combinacion combinacion, Tablero tablero) {
		for (int i = 0; i < tablero.getCifrado().tamanio(); i++) {
			introducir_bola(i, combinacion.getCombinacion()[i].getColor(), tablero);
		}
		confirmar_fila(tablero);

	}

	public Combinacion comb_random(Tablero tablero) {
		Random rnd = new Random();
		Combinacion combinacion = new Combinacion(tablero.numero_de_casillas());
		Color color = null;
		int i;
		
		do {
			// Devuelve colores del modo fácil
			for (i = 0; i < tablero.numero_de_casillas(); i++) {

				switch (rnd.nextInt(8)) {
				case 0:
					color = Color.NEGRO;
					break;
				case 1:
					color = Color.ROJO;
					break;
				case 2:
					color = Color.VERDE;
					break;
				case 3:
					color = Color.AMARILLO;
					break;
				case 4:
					color = Color.AZUL;
					break;
				case 5:
					color = Color.MORADO;
					break;
				case 6:
					color = Color.CELESTE;
					break;
				case 7:
					color = Color.MORADO_CLARO;
					break;
				case 8:
					color = Color.GRIS;
					break;
				default:
					break;
				}

				combinacion.cambiar_color_casilla(i, color);
			} 
		} while (combinacion.comprobar_colores_repes());
		return combinacion;
	}

	@Override
	protected void introducir_aciertos(Tablero tablero) {
		int ind_Negr_Blan[];
		
		ind_Negr_Blan = tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result()).calcular_respuesta(tablero.getCifrado());

		tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result()).colocar_respuesta(ind_Negr_Blan[0],
				ind_Negr_Blan[1]);
	}
	
	public Combinacion analisis_intento() {
		Combinacion nueva_comb = new Combinacion(tablero.numero_de_casillas());
		
		return nueva_comb;
	}


}
