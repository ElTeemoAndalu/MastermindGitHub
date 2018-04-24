package mastermind;

public class Usuario extends Jugador {

	protected Usuario(Dificultad dificultad) {
		tablero = new Tablero(dificultad);
		turno = 0;
		filaCreada = false;
	}

	protected Usuario(Dificultad dificultad, boolean cifrando) {
		if (!cifrando) {
			tablero = new Tablero(dificultad);
		}
		turno = 0;
		filaCreada = false;
	}

	@Override
	public void introducir_bola() {
		

	}

	@Override
	protected void introducir_cifrado(Tablero tablero) {
		final int NUM_CASILLAS_DIF_MEDIA = 5;
		int seleccion, num_colores;

		System.out.println(
				"¿En que posicion desea introducir la bola? (Introduzca 0 para volver a la pantalla anterior):\n");

		System.out.println("  1  2  3  4");
		
		tablero.dibujar_cifrado();

		seleccion = Teclado.lecturaconlimites(0, tablero.numero_de_casillas(), Teclado.LimiteInfySup.INCLUIDOS, "") - 1;

		if (tablero.numero_de_casillas() <= NUM_CASILLAS_DIF_MEDIA) {// Se limitan y muestran los colores disponibles
																		// segun la dificultad
			num_colores = Dificultad.FACIL.getColores();// El número de colores de la dificultad fácil y media son los
														// mismos
			System.out.printf(
					"Introduzca el color de la bola:(Introduzca 0 para volver al menu de partida)\n1.-%s  %s  2.-%s  %2$s\n3.-%s  %2$s  4.-%s  %2$s\n5.-%s  %2$s  6.-%s  %2$s\n7.-%s  %2$s  8.-%s  %2$s\n",
					Color.NEGRO.getCod_Color(), Color.RESETEAR.getCod_Color(), Color.ROJO.getCod_Color(), Color.VERDE.getCod_Color(),
					Color.AMARILLO.getCod_Color(), Color.AZUL.getCod_Color(), Color.MORADO.getCod_Color(), Color.CELESTE.getCod_Color(),
					Color.MORADO_CLARO.getCod_Color());
		} else {
			num_colores = Dificultad.DIFICIL.getColores();
			System.out.printf(
					"Introduzca el color de la bola:(Introduzca 0 para volver al menu de partida)\n1.-%s  %s  2.-%s  %2$s\n3.-%s  %2$s  4.-%s  %2$s\n5.-%s  %2$s  6.-%s  %2$s\n7.-%s  %2$s  8.-%s  %2$s\n9.-%s  %2$s 10.-%s  %2$s",
					Color.NEGRO.getCod_Color(), Color.RESETEAR.getCod_Color(), Color.ROJO.getCod_Color(), Color.VERDE.getCod_Color(),
					Color.AMARILLO.getCod_Color(), Color.AZUL.getCod_Color(), Color.MORADO.getCod_Color(), Color.CELESTE.getCod_Color(),
					Color.MORADO_CLARO.getCod_Color(), Color.GRIS.getCod_Color(), Color.VERDE_CLARO.getCod_Color());
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
				// METER LLAMADA A MENU DE PARTIDA
				break;
		}

	}
	

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
			
			if (tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result()).comprobar_respuesta(tablero.getCifrado(), cant_Negros, cant_Blancos)
					&& (cant_Negros + cant_Blancos) < tablero.numero_de_casillas()) {

				tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result()).colocar_respuesta(cant_Negros, cant_Blancos);
				
				indic_correctos = true;
				
			} else {
				System.out.println("Los aciertos introducidos no son correctos.Vuelva a introducirlos: \n\n");
			}

		} while (!indic_correctos);

	}

}
