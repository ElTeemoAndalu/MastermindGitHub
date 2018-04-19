package mastermind;

public class Partida {

	private Jugador jugador1, jugador2;
	private static int num_partida = 0;
	private Dificultad dificultad;

	public Partida(Dificultad dificultad) {
		this.dificultad = dificultad;
		++num_partida;
		if (dificultad == Dificultad.DIFICIL) {
			jugador1 = new IA(dificultad);
			jugador2 = new IA(dificultad);
		} else {
			jugador1 = new Usuario(dificultad);
			jugador2 = new IA(dificultad);
		}
	}

	public Partida(Dificultad dificultad, boolean cifrando) {
		this.dificultad = dificultad;
		++num_partida;
		if (dificultad == Dificultad.DIFICIL) {
			jugador1 = new IA(dificultad);
			jugador2 = new IA(dificultad);
		} else {
			jugador1 = new Usuario(dificultad, cifrando);
			jugador2 = new IA(dificultad, cifrando);
		}
	}

	public int opciones_jugador() {

		final int CANT_CASILLAS;
		int cont = 0, cont2 = 0, seleccion = 0, opcion_salir = 3;
		boolean comb_llena = false, cifr_lleno = false;

		CANT_CASILLAS = jugador1.getTablero().numero_de_casillas();

		jugador1.getTablero().dibujar_elemento();

		System.out.println("1.- Introducir bola");
		
		for (int j = 0; j < CANT_CASILLAS; j++) {

			if (jugador1.getTablero().coger_ultima_combinacion()[j] != null && jugador1.getTurno() > 0) {
				cont++;
			}

			if (jugador1.getTablero().coger_cifrado()[j] != null && jugador1.getTurno() == 0) {
				cont2++;
			}
		}

		if (cont == CANT_CASILLAS) {
			comb_llena = true;
		}

		if (cont2 == CANT_CASILLAS) {
			cifr_lleno = true;
		}

		if (cifr_lleno || comb_llena) {
			System.out.println("2.- Confirmar fila");
		} else {
			opcion_salir = 2;
		}

		if (dificultad == Dificultad.FACIL) {
			System.out.printf("%d.- Salir al menú principal\n", opcion_salir);
		} else {
			System.out.printf("%d.- Rendirse\n", opcion_salir);
		}

		if (cifr_lleno || comb_llena) {
			seleccion = Teclado.lecturaconlimites(1, 3, Teclado.LimiteInfySup.INCLUIDOS, "");
		} else {
			seleccion = Teclado.lecturaconlimites(1, 2, Teclado.LimiteInfySup.INCLUIDOS, "");
		}

		return seleccion;
	}

	public void partida_jugador(boolean cifrando) {

	}

	// Métodos que dictaminan como funcionan las partidas
	public void partida_facil_cifrando() {
		boolean ganador = false,fila_valida = false;
		int seleccion;

		while (!ganador) {

			if (jugador1.getTurno() == 0) {
				
				if(!jugador1.getTablero().getCifrado().es_comb_llena()) {
					do {
						seleccion = opciones_jugador();
						if (seleccion == 1) {
							jugador1.introducir_cifrado(jugador2.getTablero());
						} else{
							new Mastermind().menu_principal();
						}
					} while (!jugador1.getTablero().getCifrado().es_comb_llena());
				}else {
					do {
						seleccion = opciones_jugador();
						if (seleccion == 1) {
							jugador1.introducir_cifrado(jugador2.getTablero());
						} else if (seleccion == 2) {
							fila_valida = jugador1.confirmar_fila(jugador2.getTablero());
						} else {
							new Mastermind().menu_principal();
						} 
						if(!fila_valida) {
							System.out.println("La fila no es válida.");
						}
					} while (!fila_valida);
				}
				

			} else {
				//METER INTRODUCIR FILA DE IA E INTRODUCIR ACIERTOS DE USUARIO
				
				jugador1.introducir_aciertos(jugador2.getTablero());
				jugador2.
				
			}

			if (jugador1.getTurno() >= 1 && jugador2.getTablero().getComb_y_result().get(jugador2.getTablero().ultima_combinacion_y_result()).comprobar_respuesta()) {
				ganador();
				ganador = true;
			}

			if (dificultad.getIntentos() + 1 == jugador2.getTurno()) {
				perdedor();
			}
		}
	}

	public void partida_facil_descifrando() {
		if (partida.getJugador1().getTurno() == 0) {

		} else {

		}
	}
	
	public void partida_medio() {
		
	}
	
	public void partida_dificil() {
		
	}

	public void ganador() {

	}

	public void perdedor() {

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
