package mastermind;


/**
 * Esta clase define lo que puede hacer un usuario en una partida.
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */

public class Usuario extends Jugador {
	
	// Constructor
		/**
		 * Pensado para dificultades media o dificil.Construye un nuevo de tipo usuario creando ademas el tablero en funcion de la dificultad de la partida.
		 * @param dificultad determina el tamaño del tablero y cuantos colores podra usar el usuario
		 * @see Tablero
		 * @see Dificultad
		 */
	protected Usuario(Dificultad dificultad) {
		tablero = new Tablero(dificultad);
		turno = 0;
		filaCreada = false;
	}
	
	/**
	 * Pensado para la dificultad facil.Construye un nuevo de tipo usuario. Establece el turno por el que empieza y si se crea el table en funcion del modo escogido.
	 * @param dificultad determina el tamaño del tablero y cuantos colores podra usar el usuario
	 * @param cifrando especifica si el usuario creara el cifrado o no
	 * @see Tablero
	 * @see Dificultad
	 */
	protected Usuario(Dificultad dificultad, boolean cifrando) {
		if (dificultad == Dificultad.FACIL && !cifrando) {
			tablero = new Tablero(dificultad);
		}

		if (!cifrando) {
			turno = 1;
		} else {
			turno = 0;
		}

		filaCreada = false;
	}
	
	/**
	 * Permite al usuario introducir una bola en la ultima combinacion de su tablero
	 * @see Tablero
	 */
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
				"¿En que posición desea introducir la bola?:\n");

		if (tablero.getCifrado().tamanio() == Dificultad.FACIL.getCasillas()) {
			System.out.println("  1  2  3  4");
		} else {
			System.out.println("  1  2  3  4  5");
		}

		tablero.dibujar_comb_actual();

		seleccion = Teclado.lecturaconlimites(1, tablero.numero_de_casillas(), Teclado.LimiteInfySup.INCLUIDOS, "") - 1;

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
					"Introduzca el color de la bola:\n1.-%s  %s  2.-%s  %2$s\n3.-%s  %2$s  4.-%s  %2$s\n5.-%s  %2$s  6.-%s  %2$s\n7.-%s  %2$s  8.-%s  %2$s\n9.-%s  %2$s 10.-%s  %2$s",
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

			break;
		}
	}
	
	/**
	 * Permite al usuario introducir una bola en el cifrado del tablero de su rival.
	 * @param tablero Tablero del rival
	 * @see Tablero
	 */
	protected void introducir_bola_cifrado(Tablero tablero) {
		final int NUM_CASILLAS_DIF_MEDIA = 5;
		int seleccion, num_colores;

		System.out.println(
				"¿En que posicion desea introducir la bola?:\n");

		if (tablero.getCifrado().tamanio() == Dificultad.FACIL.getCasillas()) {
			System.out.println("  1  2  3  4");
		}else {
			System.out.println("  1  2  3  4  5");
		}

		tablero.dibujar_cifrado();

		seleccion = Teclado.lecturaconlimites(1, tablero.numero_de_casillas(), Teclado.LimiteInfySup.INCLUIDOS, "") - 1;

		if (tablero.numero_de_casillas() <= NUM_CASILLAS_DIF_MEDIA) {// Se limitan y muestran los colores disponibles
																		// segun la dificultad
			num_colores = Dificultad.FACIL.getColores();// El número de colores de la dificultad fácil y media son los
														// mismos
			System.out.printf(
					"Introduzca el color de la bola:\n1.-%s  %s  2.-%s  %2$s\n3.-%s  %2$s  4.-%s  %2$s\n5.-%s  %2$s  6.-%s  %2$s\n7.-%s  %2$s  8.-%s  %2$s\n",
					Color.NEGRO.getCod_Color(), Color.RESETEAR.getCod_Color(), Color.ROJO.getCod_Color(),
					Color.VERDE.getCod_Color(), Color.AMARILLO.getCod_Color(), Color.AZUL.getCod_Color(),
					Color.MORADO.getCod_Color(), Color.CELESTE.getCod_Color(), Color.MORADO_CLARO.getCod_Color());
		} else {
			num_colores = Dificultad.DIFICIL.getColores();
			System.out.printf(
					"Introduzca el color de la bola:\n1.-%s  %s  2.-%s  %2$s\n3.-%s  %2$s  4.-%s  %2$s\n5.-%s  %2$s  6.-%s  %2$s\n7.-%s  %2$s  8.-%s  %2$s\n9.-%s  %2$s 10.-%s  %2$s",
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
	
	/**
	 * Permite al usuario introducir los aciertos de su rival en la ultima combinacion.
	 * @param tablero Tablero del rival
	 * @see Tablero
	 */
	@Override
	protected void introducir_aciertos(Tablero tablero) {

		int cant_Blancos, cant_Negros;
		final int NUM_CASILLAS;
		boolean indic_correctos = false;

		NUM_CASILLAS = tablero.numero_de_casillas();

		do {
			cant_Negros = Teclado.lecturaconlimites(0, NUM_CASILLAS, Teclado.LimiteInfySup.INCLUIDOS,
					"¿Bolas con el mismo color y posición?");
			cant_Blancos = Teclado.lecturaconlimites(0, NUM_CASILLAS, Teclado.LimiteInfySup.INCLUIDOS,
					"\n¿Bolas en diferente posición pero con el mismo color?");

			if (tablero.getComb_y_result().get(tablero.ultima_comb_y_result())
					.calcular_respuesta(tablero.getCifrado(), cant_Negros, cant_Blancos)
					&& (cant_Negros + cant_Blancos) < tablero.numero_de_casillas()) {

				tablero.getComb_y_result().get(tablero.ultima_comb_y_result()).colocar_respuesta(cant_Negros,
						cant_Blancos);

				indic_correctos = true;

			} else {
				System.out.println("Los aciertos introducidos no son correctos.Vuelva a introducirlos: \n\n");
				tablero.dibujar_elemento();
			}

		} while (!indic_correctos);

	}
	
	/**
	 * Permite al usuario introducir el cifrado del tablero de su rival.
	 * @param tablero Tablero del rival
	 * @see #introducir_bola_cifrado(Tablero)
	 */
	public void introducir_cifrado(Tablero tablero) {
		int seleccion;
		boolean fila_valida = false;
		do {
			tablero.dibujar_elemento();
			seleccion = opciones_jugador_cifrando(tablero);
			if (seleccion == 1) {
				introducir_bola_cifrado(tablero);
			} else{
				fila_valida = confirmar_fila(tablero);
				if (!fila_valida) {
					System.out.println("La fila no es valida.");
				}
			}
			
		} while (!fila_valida);
	}
	
	/**
	 * Le muestra al jugador las acciones que puede hacer ahora mientras esta descifrando.
	 * @return Numero representa la opcion escogida por el usuario
	 */
	public int opciones_jugador_descifrando() {
		String opciones = "";
			if (filaCreada) {
				opciones = "1.- Introducir bola\n2.- Confirmar fila";
			}else if (!filaCreada) {
				opciones = "1.- Introducir fila y bola\n2.- Confirmar fila\n  (Se introduce fila todo de negros)";
			}
		return Teclado.lecturaconlimites(1, 2, Teclado.LimiteInfySup.INCLUIDOS, opciones);
	}
	
	/**
	 * Le muestra al jugador las acciones que puede hacer mientras esta cifrando. Dependen de si ha introducido colores repetidos o no.
	 * @param tablero Se usa para saber si el usuario ha introducido colores repetidos 
	 * @return Numero representa la opcion escogida por el usuario
	 */
	public int opciones_jugador_cifrando(Tablero tablero) {
		String opciones = "";
			if (tablero.getCifrado().comprobar_colores_repes()) {
				opciones = "1.- Introducir bola en cifrado\n2.- Confirmar fila(No valen repetidos)";
			}else {
				opciones = "1.- Cambiar bola en cifrado\n2.- Confirmar fila";
			}
		
		return Teclado.lecturaconlimites(1, 2, Teclado.LimiteInfySup.INCLUIDOS, opciones);
	}

}
