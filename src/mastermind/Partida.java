package mastermind;

/**
 * Esta clase almacena a los jugadores y la dificultad y determina el flujo de las partidas segun la dificultad,
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Partida {

	private Jugador jugador1, jugador2;
	private Dificultad dificultad;
	private int turnoPartida;

	public Partida(Dificultad dificultad) {
		this.dificultad = dificultad;
		turnoPartida = 0;

		if (dificultad == Dificultad.DIFICIL) {
			jugador1 = new IA(dificultad);
		} else {
			jugador1 = new Usuario(dificultad);
		}

		jugador2 = new IA(dificultad);
	}

	public Partida(Dificultad dificultad, boolean cifrando) {
		this.dificultad = dificultad;
		turnoPartida = 0;
		jugador1 = new Usuario(dificultad, cifrando);
		jugador2 = new IA(dificultad, cifrando);

	}

	public void partida_jugador(boolean cifrando) {

	}

	// Metodos que dictaminan como funcionan las partidas
	public void partida_facil_cifrando() {
		boolean ganador = false;

		while (!ganador && turnoPartida < dificultad.getIntentos()) {

			if (jugador1.getTurno() == 0) {

				((Usuario) jugador1).introducir_cifrado(jugador2.tablero);

			} else {
				// CAMBIAR ESTA LINEA CUANDO IA ESTE TERMINADA
				((IA) jugador2).introducir_fila(((IA) jugador2).comb_random(jugador2.getTablero(),true));
				jugador2.getTablero().dibujar_elemento();
				jugador1.introducir_aciertos(jugador2.getTablero());

				if (comprobar_ganador_dif_facil(true) > 0) {
					ganador = true;
				} else {
					turnoPartida++;
				}

			}
		}
		mostrar_ganador(comprobar_ganador_dif_facil(true));
	}

	public void partida_facil_descifrando() {
		int seleccion;
		boolean fila_valida, ganador;

		fila_valida = ganador = false;

		while (!ganador && turnoPartida < dificultad.getIntentos()) {

			if (jugador2.getTurno() == 0) {
				((IA) jugador2).introducir_cifrado(((IA) jugador2).comb_random(jugador1.getTablero(),true),
						jugador1.getTablero());

			} else {

				do {
					jugador1.getTablero().dibujar_con_cifr_oculto();
					seleccion = ((Usuario) jugador1).opciones_jugador_descifrando();
					if (seleccion == 1) {
						((Usuario)jugador1).introducir_bola();
					} else if (seleccion == 2) {
						fila_valida = jugador1.confirmar_fila(jugador1.getTablero());
						((IA) jugador2).introducir_aciertos(jugador1.getTablero());
					}
				} while (!fila_valida);

				fila_valida = false;

				if (comprobar_ganador_dif_facil(false) > 0) {
					ganador = true;

				} else {
					turnoPartida++;
				}

			}
		}
		mostrar_ganador(comprobar_ganador_dif_facil(false));
	}

	public void partida_medio() {

		int seleccion;
		boolean fila_valida, ganador;

		fila_valida = ganador = false;

		while (!ganador && turnoPartida < dificultad.getIntentos()) {

			if (turnoPartida == 0) {
				System.out.println("Introduzca la combinación secreta de tu rival.");
				((Usuario) jugador1).introducir_cifrado(jugador2.tablero);
				((IA) jugador2).introducir_cifrado(((IA) jugador2).comb_random(jugador1.getTablero(),true),
						jugador1.getTablero());
				turnoPartida++;
			} else {

				do {
					System.out.println("T" + turnoPartida + ". Introduzca una combinación:");
					jugador1.getTablero().dibujar_con_cifr_oculto();
					seleccion = ((Usuario) jugador1).opciones_jugador_descifrando();

					if (seleccion == 1) {
						((Usuario)jugador1).introducir_bola();
					} else if (seleccion == 2) {
						fila_valida = jugador1.confirmar_fila(jugador1.getTablero());
						((IA) jugador2).introducir_aciertos(jugador1.getTablero());
					}

				} while (!fila_valida);

				fila_valida = false;

				// CAMBIAR ESTA LINEA CUANDO IA ESTE TERMINADA
				((IA) jugador2).introducir_fila(((IA) jugador2).comb_random(jugador2.getTablero(),true));
				System.out.println("Tablero de la IA");
				jugador2.getTablero().dibujar_elemento();
				jugador1.introducir_aciertos(jugador2.getTablero());

				if (comprobar_ganador_medio_dif() > 0) {
					ganador = true;
				} else {
					turnoPartida++;
				}

			}
		}
		mostrar_ganador(comprobar_ganador_medio_dif());
	}
	
	

	public void partida_dificil() {
		
		boolean ganador;

		ganador = false;

		while (!ganador) {

			if (turnoPartida == 0) {
				((IA) jugador1).introducir_cifrado(((IA) jugador1).comb_random(jugador2.getTablero(),false),jugador2.getTablero());
				((IA) jugador2).introducir_cifrado(((IA) jugador2).comb_random(jugador1.getTablero(),false),jugador1.getTablero());
				turnoPartida++;
				jugador1.getTablero().dibujar_tableros_lado_a_lado(jugador2.getTablero());
			} else {
				((IA) jugador1).introducir_fila(((IA) jugador1).analisis_intento());
				((IA) jugador2).introducir_aciertos(jugador1.getTablero());
				((IA) jugador2).introducir_fila(((IA) jugador2).analisis_intento());
				((IA) jugador1).introducir_aciertos(jugador2.getTablero());
				
				jugador1.getTablero().dibujar_tableros_lado_a_lado(jugador2.getTablero());
				

				if (comprobar_ganador_medio_dif() > 0 && turnoPartida > 10) {
					ganador = true;
				} else {
					turnoPartida++;
				}
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					System.out.println("error en la espera");
				}
				
			}
		}
		mostrar_ganador(comprobar_ganador_medio_dif());

	}
	
	

	public int comprobar_ganador_dif_facil(boolean cifrando) {
		int resultado = 0;

		if (cifrando) {
			if (Arrays.equals(jugador2.getTablero().getCifrado().getCombinacion(),
					jugador2.getTablero().coger_ultima_combinacion())) {
				resultado = 2;
			} else if ((turnoPartida + 1) > dificultad.getIntentos()) {
				resultado = 1;
			}
		} else {
			if (Arrays.equals(jugador1.getTablero().getCifrado().getCombinacion(),
					jugador1.getTablero().coger_ultima_combinacion())) {
				resultado = 1;
			} else if ((turnoPartida + 1) > dificultad.getIntentos()) {
				resultado = 2;
			}
		}

		return resultado;
	}

	public int comprobar_ganador_medio_dif() {
		int resultado = 0;

		if (Arrays.equals(jugador1.getTablero().getCifrado().getCombinacion(), jugador1.getTablero().coger_ultima_combinacion())) {
			resultado = 1;
		} else if (Arrays.equals(jugador2.getTablero().getCifrado().getCombinacion(),
				jugador2.getTablero().coger_ultima_combinacion())) {
			resultado = 1;
		} else if ((turnoPartida + 1) > dificultad.getIntentos()) {
			if (jugador1.getTablero().getComb_y_result().get(jugador1.getTablero().ultima_comb_y_result())
					.comparar_respuestas(jugador2.getTablero().getComb_y_result()
							.get(jugador2.getTablero().ultima_comb_y_result())) == 1) {
				resultado = 1;
			} else if (jugador1.getTablero().getComb_y_result().get(jugador1.getTablero().ultima_comb_y_result())
					.comparar_respuestas(jugador2.getTablero().getComb_y_result()
							.get(jugador2.getTablero().ultima_comb_y_result())) == 2) {
				resultado = 2;
			} else {
				resultado = 0;
			}
		}

		return resultado;
	}

	public void mostrar_ganador(int ganador) {
		if (ganador > 0) {
			System.out.println("Jugador " + ganador + " gana ＼（＾∀＾）メ（＾∀＾）ノ");
		} else {
			System.out.println("Empate o(╥﹏╥)o");
		}
		System.out.println("Pulse intro para continuar");
		Teclado.leercadena();
	}

	public Dificultad getDificultad() {
		return dificultad;
	}

	public Jugador getJugador1() {
		return jugador1;
	}

	public Jugador getJugador2() {
		return jugador2;
	}

}
