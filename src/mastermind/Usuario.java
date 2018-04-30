package mastermind;

public class Usuario extends Jugador {

	protected Usuario(Dificultad dificultad) {
		tablero = new Tablero(dificultad);
		turno = 0;
		filaCreada = false;
	}

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

			if (tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result())
					.comprobar_respuesta(tablero.getCifrado(), cant_Negros, cant_Blancos)
					&& (cant_Negros + cant_Blancos) < tablero.numero_de_casillas()) {

				tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result()).colocar_respuesta(cant_Negros,
						cant_Blancos);

				indic_correctos = true;

			} else {
				System.out.println("Los aciertos introducidos no son correctos.Vuelva a introducirlos: \n\n");
				tablero.dibujar_elemento();
			}

		} while (!indic_correctos);

	}

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
	
	public int opciones_jugador_descifrando() {
		String opciones = "";
			if (filaCreada) {
				opciones = "1.- Introducir bola\n2.- Confirmar fila";
			}else if (!filaCreada) {
				opciones = "1.- Introducir fila y bola\n2.- Confirmar fila\n  (Se introduce fila todo de negros)";
			}
		return Teclado.lecturaconlimites(1, 2, Teclado.LimiteInfySup.INCLUIDOS, opciones);
	}
	
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
