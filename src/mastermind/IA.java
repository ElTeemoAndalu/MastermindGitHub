package mastermind;

import java.util.HashMap;

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
		if (dificultad != Dificultad.FACIL) {
			tablero = new Tablero(dificultad);
		}
		turno = 0;
		filaCreada = false;
	}
	
	@Override
	protected void introducir_bola() {
		
	}


	public void introducir_fila() {
		for (int i = 0; i < tablero.getCifrado().tamaño(); i++) {
			introducir_bola();
		}

		confirmar_fila(tablero);
	}

	@Override
	protected void introducir_aciertos(Tablero tablero) {
		int i, j, ind_Negros, ind_Blancos;
		final int num_casillas = tablero.getCifrado().getCombinacion().length;
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

		tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result()).colocar_respuesta(ind_Negros,ind_Blancos);
	}

	@Override
	protected void introducir_cifrado(Tablero tablero) {
	}

	

}
