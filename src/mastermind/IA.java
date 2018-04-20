package mastermind;

import java.util.HashMap;
import java.util.Random;

public class IA extends Jugador {

	private Tablero tablero;
	private int turno;
	private boolean filaCreada;
	private HashMap<Combinacion, Integer> tableroAux;

	protected IA(Dificultad dificultad) {
		tablero = new Tablero(dificultad);
		turno = 0;
		filaCreada = false;
	}

	protected IA(Dificultad dificultad, boolean cifrando) {
		if (dificultad == Dificultad.FACIL) {
			tablero = new Tablero(dificultad);
		}
		turno = 0;
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
			tablero.añadir_combinacion();
			filaCreada = true;
		}
		for (int i = 0; i < tablero.getCifrado().tamaño(); i++) {
			introducir_bola(i, combinacion.getCombinacion()[i].getColor(), tablero);
		}
		confirmar_fila(tablero);

	}

	public void introducir_cifrado(Combinacion combinacion, Tablero tablero) {
		for (int i = 0; i < tablero.getCifrado().tamaño(); i++) {
			introducir_bola(i, combinacion.getCombinacion()[i].getColor(), tablero);
		}
		confirmar_fila(tablero);

	}

	public Combinacion comb_random() {
		Random rnd = new Random();
		Combinacion combinacion = new Combinacion(tablero.numero_de_casillas());
		Color color = null;
		int i;

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
					color = Color.BLANCO;
					break;
				case 8:
					color = Color.GRIS;
					break;
				default:
					break;
			}

			combinacion.cambiar_color_casilla(i, color);
		}

		return combinacion;
	}

	@Override
	protected void introducir_aciertos(Tablero tablero) {
		int i, j, ind_Negros, ind_Blancos;
		final int num_casillas = tablero.numero_de_casillas();
		ind_Negros = ind_Blancos = 0;
		boolean en_resultado[] = new boolean[num_casillas];

		for (i = 0; i < num_casillas; i++) {
			for (j = 0; i < num_casillas; i++) {
				if (!en_resultado[j]) {
					if (tablero.getComb_y_result().get(tablero.getComb_y_result().size() - 1).getCombinacion()[i]
							.getColor().equals(tablero.getCifrado().getCombinacion()[j].getColor()) && i == j) {
						ind_Negros++;
						en_resultado[j] = true;
						j = num_casillas;
					} else if (tablero.getComb_y_result().get(tablero.getComb_y_result().size() - 1).getCombinacion()[i]
							.getColor().equals(tablero.getCifrado().getCombinacion()[j].getColor())) {
						ind_Blancos++;
						en_resultado[j] = true;
						j = num_casillas;
					}
				}
			}
		}

		tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result()).colocar_respuesta(ind_Negros,
				ind_Blancos);
	}

	@Override
	protected void introducir_cifrado(Tablero tablero) {
	}

}
