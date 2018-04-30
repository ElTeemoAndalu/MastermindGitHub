package mastermind;

import java.util.Arrays;

public class Partida {

	private Jugador jugador1, jugador2;
	private static int num_partida = 0;
	private Dificultad dificultad;
	private int turnoPartida;

	public Partida(Dificultad dificultad) {
		this.dificultad = dificultad;
		++num_partida;
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
		++num_partida;
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
				((IA) jugador2).introducir_fila(((IA) jugador2).comb_random(jugador2.getTablero()));
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
				((IA) jugador2).introducir_cifrado(((IA) jugador2).comb_random(jugador1.getTablero()),
						jugador1.getTablero());

			} else {

				do {
					jugador1.getTablero().dibujar_con_cifr_oculto();
					seleccion = ((Usuario) jugador1).opciones_jugador_descifrando();
					if (seleccion == 1) {
						jugador1.introducir_bola();
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
				((IA) jugador2).introducir_cifrado(((IA) jugador2).comb_random(jugador1.getTablero()),
						jugador1.getTablero());
				turnoPartida++;
			} else {

				do {
					System.out.println("T" + turnoPartida + ". Introduzca una combinación:");
					jugador1.getTablero().dibujar_con_cifr_oculto();
					seleccion = ((Usuario) jugador1).opciones_jugador_descifrando();

					if (seleccion == 1) {
						jugador1.introducir_bola();
					} else if (seleccion == 2) {
						fila_valida = jugador1.confirmar_fila(jugador1.getTablero());
						((IA) jugador2).introducir_aciertos(jugador1.getTablero());
					}

				} while (!fila_valida);

				fila_valida = false;

				// CAMBIAR ESTA LINEA CUANDO IA ESTE TERMINADA
				((IA) jugador2).introducir_fila(((IA) jugador2).comb_random(jugador2.getTablero()));
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

	}

	public int comprobar_ganador_dif_facil(boolean cifrando) {
		int resultado = 0;

		if (cifrando) {
			if (Arrays.equals(jugador2.getTablero().coger_cifrado(),
					jugador2.getTablero().coger_ultima_combinacion())) {
				resultado = 2;
			} else if ((turnoPartida + 1) > dificultad.getIntentos()) {
				resultado = 1;
			}
		} else {
			if (Arrays.equals(jugador1.getTablero().coger_cifrado(),
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

		if (Arrays.equals(jugador1.getTablero().coger_cifrado(), jugador1.getTablero().coger_ultima_combinacion())) {
			resultado = 1;
		} else if (Arrays.equals(jugador2.getTablero().coger_cifrado(),
				jugador2.getTablero().coger_ultima_combinacion())) {
			resultado = 1;
		} else if ((turnoPartida + 1) > dificultad.getIntentos()) {
			if (jugador1.getTablero().getComb_y_result().get(jugador1.getTablero().ultima_combinacion_y_result())
					.comparar_respuestas(jugador2.getTablero().getComb_y_result()
							.get(jugador2.getTablero().ultima_combinacion_y_result())) == 1) {
				resultado = 1;
			} else if (jugador1.getTablero().getComb_y_result().get(jugador1.getTablero().ultima_combinacion_y_result())
					.comparar_respuestas(jugador2.getTablero().getComb_y_result()
							.get(jugador2.getTablero().ultima_combinacion_y_result())) == 2) {
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

	// Getters
	public static int getNum_partida() {
		return num_partida;
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
