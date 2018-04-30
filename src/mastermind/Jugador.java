package mastermind;

public abstract class Jugador {

	protected Tablero tablero;
	protected int turno;
	protected boolean filaCreada;

	protected abstract void introducir_aciertos(Tablero tablero);

	protected void introducir_bola_cifrado(Tablero tablero) {
		final int NUM_CASILLAS_DIF_MEDIA = 5;
		int seleccion, num_colores;

		System.out.println(
				"¿En que posicion desea introducir la bola? (Introduzca 0 para volver a la pantalla anterior):\n");

		if (tablero.getCifrado().tamanio() == Dificultad.FACIL.getCasillas()) {
			System.out.println("  1  2  3  4");
		}else {
			System.out.println("  1  2  3  4  5");
		}

		tablero.dibujar_cifrado();

		seleccion = Teclado.lecturaconlimites(0, tablero.numero_de_casillas(), Teclado.LimiteInfySup.INCLUIDOS, "") - 1;

		if (tablero.numero_de_casillas() <= NUM_CASILLAS_DIF_MEDIA) {// Se limitan y muestran los colores disponibles
																		// segun la dificultad
			num_colores = Dificultad.FACIL.getColores();// El número de colores de la dificultad fácil y media son los
														// mismos
			System.out.printf(
					"Introduzca el color de la bola:(Introduzca 0 para volver al menu de partida)\n1.-%s  %s  2.-%s  %2$s\n3.-%s  %2$s  4.-%s  %2$s\n5.-%s  %2$s  6.-%s  %2$s\n7.-%s  %2$s  8.-%s  %2$s\n",
					Color.NEGRO.getCod_Color(), Color.RESETEAR.getCod_Color(), Color.ROJO.getCod_Color(),
					Color.VERDE.getCod_Color(), Color.AMARILLO.getCod_Color(), Color.AZUL.getCod_Color(),
					Color.MORADO.getCod_Color(), Color.CELESTE.getCod_Color(), Color.MORADO_CLARO.getCod_Color());
		} else {
			num_colores = Dificultad.DIFICIL.getColores();
			System.out.printf(
					"Introduzca el color de la bola:(Introduzca 0 para volver al menu de partida)\n1.-%s  %s  2.-%s  %2$s\n3.-%s  %2$s  4.-%s  %2$s\n5.-%s  %2$s  6.-%s  %2$s\n7.-%s  %2$s  8.-%s  %2$s\n9.-%s  %2$s 10.-%s  %2$s",
					Color.NEGRO.getCod_Color(), Color.RESETEAR.getCod_Color(), Color.ROJO.getCod_Color(),
					Color.VERDE.getCod_Color(), Color.AMARILLO.getCod_Color(), Color.AZUL.getCod_Color(),
					Color.MORADO.getCod_Color(), Color.CELESTE.getCod_Color(), Color.MORADO_CLARO.getCod_Color(),
					Color.GRIS.getCod_Color(), Color.VERDE_CLARO.getCod_Color());
		}
		switch (Teclado.lecturaconlimites(0, num_colores, Teclado.LimiteInfySup.INCLUIDOS, "")) {
		case 1:
			tablero.getCifrado().cambiar_color_casilla(seleccion, Color.NEGRO);
			break;
		case 2:
			tablero.getCifrado().cambiar_color_casilla(seleccion, Color.ROJO);
			break;
		case 3:
			tablero.getCifrado().cambiar_color_casilla(seleccion, Color.VERDE);
			break;
		case 4:
			tablero.getCifrado().cambiar_color_casilla(seleccion, Color.AMARILLO);
			break;
		case 5:
			tablero.getCifrado().cambiar_color_casilla(seleccion, Color.AZUL);
			break;
		case 6:
			tablero.getCifrado().cambiar_color_casilla(seleccion, Color.MORADO);
			break;
		case 7:
			tablero.getCifrado().cambiar_color_casilla(seleccion, Color.CELESTE);
			break;
		case 8:
			tablero.getCifrado().cambiar_color_casilla(seleccion, Color.MORADO_CLARO);
			break;
		case 9:
			tablero.getCifrado().cambiar_color_casilla(seleccion, Color.GRIS);
			break;
		case 10:
			tablero.getCifrado().cambiar_color_casilla(seleccion, Color.VERDE_CLARO);
			break;

		default:
			break;
		}

	}

	protected void introducir_bola() {
		final int NUM_CASILLAS_DIF_MEDIA = 5;
		int seleccion, num_colores;

		if (!filaCreada && turno > 0) { // Se crea un objeto combinacion y resultado cada ver que se intenta añadir la
										// primera bola de una combinacion que no sea la del cifrado
			tablero.aniadir_combinacion(); // Las
											// combinaciones ser�n del mismo tama�o que el cifrado
			filaCreada = true;
		}

		System.out.println(
				"¿En que posición desea introducir la bola? (Introduzca 0 para volver a la pantalla anterior):\n");

		if (tablero.getCifrado().tamanio() == Dificultad.FACIL.getCasillas()) {
			System.out.println("  1  2  3  4");
		} else {
			System.out.println("  1  2  3  4  5");
		}

		tablero.dibujar_comb_actual();

		seleccion = Teclado.lecturaconlimites(0, tablero.numero_de_casillas(), Teclado.LimiteInfySup.INCLUIDOS, "") - 1;

		if (tablero.numero_de_casillas() <= NUM_CASILLAS_DIF_MEDIA) {// Se limitan y muestran los colores disponibles
																		// segun la dificultad
			num_colores = Dificultad.FACIL.getColores();// El n�mero de colores de la dificultad f�cil y media son los
														// mismos
			System.out.printf(
					"Introduzca el color de la bola:(Introduzca 0 para volver al menu de partida)\n1.-%s  %s  2.-%s  %2$s\n3.-%s  %2$s  4.-%s  %2$s\n5.-%s  %2$s  6.-%s  %2$s\n7.-%s  %2$s  8.-%s  %2$s\n",
					Color.NEGRO.getCod_Color(), Color.RESETEAR.getCod_Color(), Color.ROJO.getCod_Color(),
					Color.VERDE.getCod_Color(), Color.AMARILLO.getCod_Color(), Color.AZUL.getCod_Color(),
					Color.MORADO.getCod_Color(), Color.CELESTE.getCod_Color(), Color.MORADO_CLARO.getCod_Color());
		} else {
			num_colores = Dificultad.DIFICIL.getColores();
			System.out.printf(
					"Introduzca el color de la bola:(Introduzca 0 para volver al menu de partida)\n1.-%s  %s  2.-%s  %2$s\n3.-%s  %2$s  4.-%s  %2$s\n5.-%s  %2$s  6.-%s  %2$s\n7.-%s  %2$s  8.-%s  %2$s\n9.-%s  %2$s 10.-%s  %2$s",
					Color.NEGRO.getCod_Color(), Color.RESETEAR.getCod_Color(), Color.ROJO.getCod_Color(),
					Color.VERDE.getCod_Color(), Color.AMARILLO.getCod_Color(), Color.AZUL.getCod_Color(),
					Color.MORADO.getCod_Color(), Color.CELESTE.getCod_Color(), Color.MORADO_CLARO.getCod_Color(),
					Color.GRIS.getCod_Color(), Color.VERDE_CLARO.getCod_Color());
		}
		switch (Teclado.lecturaconlimites(0, num_colores, Teclado.LimiteInfySup.INCLUIDOS, "")) {
		case 1:

			tablero.coger_ultima_combinacion()[seleccion].setColor(Color.NEGRO);
			break;
		case 2:
			tablero.coger_ultima_combinacion()[seleccion].setColor(Color.ROJO);
			break;
		case 3:
			tablero.coger_ultima_combinacion()[seleccion].setColor(Color.VERDE);
			break;
		case 4:
			tablero.coger_ultima_combinacion()[seleccion].setColor(Color.AMARILLO);
			break;
		case 5:
			tablero.coger_ultima_combinacion()[seleccion].setColor(Color.AZUL);
			break;
		case 6:
			tablero.coger_ultima_combinacion()[seleccion].setColor(Color.MORADO);
			break;
		case 7:
			tablero.coger_ultima_combinacion()[seleccion].setColor(Color.CELESTE);
			break;
		case 8:
			tablero.coger_ultima_combinacion()[seleccion].setColor(Color.MORADO_CLARO);
			break;
		case 9:
			tablero.coger_ultima_combinacion()[seleccion].setColor(Color.GRIS);
			break;
		case 10:
			tablero.coger_ultima_combinacion()[seleccion].setColor(Color.VERDE_CLARO);
			break;

		default:
			// METER LLAMADA A MENU DE PARTIDA
			break;
		}
	}

	protected boolean confirmar_fila(Tablero tablero) {
		boolean fila_valida = false;
		
		if (!filaCreada && turno > 0) { 
			tablero.aniadir_combinacion(); 
			filaCreada = true;
		}
		
		if (turno == 0) { // Si estamos cifrando, se comprueba que no se repitan colores
			if (!tablero.getCifrado().comprobar_colores_repes() && tablero.getCifrado().es_comb_llena()) {
				fila_valida = true;
			}
		} else { // Si no, solo que este llena, con que colores no importa
			if (tablero.getCifrado().es_comb_llena()) {
				fila_valida = true;
			}
		}
		if (fila_valida) {
			turno++;
		}
		filaCreada = false;

		return fila_valida;

	}

	public Tablero getTablero() {
		return tablero;
	}

	public int getTurno() {
		return turno;
	}

}
