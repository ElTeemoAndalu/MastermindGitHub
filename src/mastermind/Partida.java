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
		boolean ganador = false, fila_valida = false;

		while (!ganador && turnoPartida < dificultad.getIntentos()) {

			if (jugador1.getTurno() == 0) {

				((Usuario) jugador1).introducir_cifrado(jugador2.tablero);

			} else {

				do {
					//CAMBIAR ESTA LINEA CUANDO IA ESTE TERMINADA
					((IA) jugador2).introducir_fila(((IA) jugador2).comb_random(jugador2.getTablero()));
					jugador2.getTablero().dibujar_elemento();
					jugador1.introducir_aciertos(jugador2.getTablero());
				} while (!fila_valida);

				if (comprobar_ganador(jugador2.getTablero(),true) == 2) {
					ganador = true;
					mostrar_ganador(comprobar_ganador(jugador2.getTablero(),true));
				} else {
					turnoPartida++;
				}

			}
		}
		mostrar_ganador(comprobar_ganador(jugador2.getTablero(),true));
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
					seleccion = ((Usuario) jugador1).opciones_jugador();
					if (seleccion == 1) {
						jugador1.introducir_bola();
					} else if (seleccion == 2) {
						fila_valida = jugador1.confirmar_fila(jugador1.getTablero());
						((IA) jugador2).introducir_aciertos(jugador1.getTablero());
					}
				} while (!fila_valida);
				
				if (comprobar_ganador(jugador1.getTablero(),false) == 1) {
					ganador = true;
					mostrar_ganador(comprobar_ganador(jugador1.getTablero(),false));
				} else {
					turnoPartida++;
				}
				
			}
		}
	}

	public void partida_medio() {
		
		int seleccion;
		boolean fila_valida, ganador;

		fila_valida = ganador = false;

		while (!ganador && turnoPartida < dificultad.getIntentos()) {

			if (jugador2.getTurno() == 0) {
				((IA) jugador2).introducir_cifrado(((IA) jugador2).comb_random(jugador1.getTablero()),
						jugador1.getTablero());
				((Usuario) jugador1).introducir_cifrado(jugador2.tablero);

			} else {

				do {
					jugador1.getTablero().dibujar_con_cifr_oculto();
					seleccion = ((Usuario) jugador1).opciones_jugador();
					if (seleccion == 1) {
						jugador1.introducir_bola();
					} else if (seleccion == 2) {
						fila_valida = jugador1.confirmar_fila(jugador1.getTablero());
						((IA) jugador2).introducir_aciertos(jugador1.getTablero());
					}
				} while (!fila_valida);
				
				if (comprobar_ganador(jugador1.getTablero(),false) == 1) {
					ganador = true;
					mostrar_ganador(comprobar_ganador(jugador1.getTablero(),false));
				} else {
					turnoPartida++;
				}
				
			}
		}

	}

	public void partida_dificil() {

	}

	public int comprobar_ganador(Tablero tablero,boolean cifrando) {
		int resultado = 0;
		
		if(dificultad == Dificultad.FACIL) {
			if (Arrays.equals(tablero.coger_cifrado(), tablero.coger_ultima_combinacion())) {
				if (cifrando) {
					resultado = 2;
				}else {
					resultado = 1;
				}
			} else if ((turnoPartida + 1) > dificultad.getIntentos()) {
				if (cifrando) {
					resultado = 1;
				}else {
					resultado = 2;
				}
			}
		}

		return resultado;
	}

	public void mostrar_ganador(int ganador) {
		System.out.println("Jugador " + ganador + " gana ＼（＾∀＾）メ（＾∀＾）ノ");
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
