package mastermind;

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

	public int opciones_jugador(Tablero tablero) {

		int seleccion = 0;
		
		System.out.println("1.- Introducir bola");
		System.out.println("2.- Confirmar fila");

		if (dificultad == Dificultad.FACIL) {
			System.out.println("3.- Salir al menu principal\n");
		} else {
			System.out.println("3.- Rendirse\n");
		}

		seleccion = Teclado.lecturaconlimites(1, 3, Teclado.LimiteInfySup.INCLUIDOS, "");

		return seleccion;
	}

	public void partida_jugador(boolean cifrando) {

	}

	// Metodos que dictaminan como funcionan las partidas
	public void partida_facil_cifrando() {
		boolean ganador = false, fila_valida = false;
		int seleccion;

		while (!ganador && turnoPartida < dificultad.getIntentos()) {

			if (jugador1.getTurno() == 0) {

				if (!jugador2.getTablero().getCifrado().es_comb_llena()) {
					do {
						seleccion = opciones_jugador(jugador2.getTablero());
						if (seleccion == 1) {
							jugador1.introducir_bola_cifrado(jugador2.getTablero());
						} else {
							new Mastermind().menu_principal(); // CAMBIAR ESTO
						}
					} while (!jugador1.getTablero().getCifrado().es_comb_llena());

				} else {
					do {
						jugador2.getTablero().dibujar_elemento();
						seleccion = opciones_jugador(jugador2.getTablero());
						if (seleccion == 1) {
							jugador1.introducir_bola_cifrado(jugador2.getTablero());
						} else if (seleccion == 2) {
							fila_valida = jugador1.confirmar_fila(jugador2.getTablero());
							if (!fila_valida) {
								System.out.println("La fila no es valida.");
							}
						} else {
							
							new Mastermind().menu_principal();// CAMBIAR ESTO
						}
						
					} while (!fila_valida);
				}

			} else {

				do {
					((IA) jugador2).introducir_fila(((IA) jugador2).comb_random(jugador2.getTablero()));
					jugador2.getTablero().dibujar_elemento();
					jugador1.introducir_aciertos(jugador2.getTablero());
				} while (!fila_valida);

				if (comprobar_ganador(jugador2.getTablero()) == 2) {
					ganador = true;
				} else {
					turnoPartida++;
				}

			}
		}
		mostrar_ganador(comprobar_ganador(jugador2.getTablero()));
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
					seleccion = opciones_jugador(jugador1.getTablero());
					if (seleccion == 1) {
						jugador1.introducir_bola();
					} else if (seleccion == 2) {
						fila_valida = jugador1.confirmar_fila(jugador1.getTablero());
						((IA) jugador2).introducir_aciertos(jugador1.getTablero());
					} else {
						new Mastermind().menu_principal();// CAMBIAR ESTO
					}
					if (!fila_valida) {
						System.out.println("La fila no es valida.");
					}
				} while (!fila_valida);
				
			}
		}
	}

	public void partida_medio() {

	}

	public void partida_dificil() {

	}

	// 1 -> Jugador1 gana | 2 -> Jugador2 gana
	public int comprobar_ganador(Tablero tablero) {
		int resultado = 0;
		if (dificultad == Dificultad.FACIL) {
			if (tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result()).getCombinacion()
					.equals(tablero.coger_cifrado())) {
				resultado = 2;
			} else if (turnoPartida + 1 > dificultad.getIntentos()) {
				resultado = 1;
			}
		}

		return resultado;
	}

	public void mostrar_ganador(int ganador) {
		System.out.println("Jugador " + ganador + " gana ＼（＾∀＾）メ（＾∀＾）ノ");
		System.out.println("Pulse intro para continuar");
		Teclado.leercadena();
		new Mastermind().menu_principal();
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
