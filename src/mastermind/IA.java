package mastermind;

import java.util.Random;
import java.util.TreeMap;

public class IA extends Jugador {

	private TreeMap<Byte, Comb_y_result> tableroAux;
	private boolean colores_encontrados, color_encontrado[], color_en_cifr[];
	private byte posicion_actual, color_actual, cant_colores[];

	protected IA(Dificultad dificultad) {
		tablero = new Tablero(dificultad);
		turno = color_actual = posicion_actual = 0;
		filaCreada = false;
		colores_encontrados = false;
		cant_colores = new byte[10];
		color_en_cifr = new boolean[8];
		color_encontrado = new boolean[10];

		for (int i = 0; i < cant_colores.length; i++) {
			cant_colores[i] = 0;
			color_encontrado[i] = false;
			if (i < 8) {
				color_en_cifr[i] = false;
			}
		}
	}

	protected IA(Dificultad dificultad, boolean cifrando) {
		if (dificultad == Dificultad.FACIL && cifrando) {
			tablero = new Tablero(dificultad);
		}

		if (cifrando) {
			turno = 1;
		} else {
			turno = 0;
		}

		filaCreada = false;
	}

	public Tablero getTablero() {
		return tablero;
	}

	protected void introducir_bola(int posicion, Color color, Tablero tablero) {
		if (turno > 0) {
			tablero.coger_ultima_combinacion()[posicion].setColor(color);
		} else {
			tablero.getCifrado().getCombinacion()[posicion].setColor(color);
		}
	}

	public void introducir_fila(Combinacion combinacion) {
		if (!filaCreada) {
			tablero.aniadir_combinacion();
			filaCreada = true;
		}
		for (int i = 0; i < tablero.getCifrado().tamanio(); i++) {
			introducir_bola(i, combinacion.getCombinacion()[i].getColor(), tablero);
		}
		confirmar_fila(tablero);

	}

	public void introducir_cifrado(Combinacion combinacion, Tablero tablero) {
		for (int i = 0; i < tablero.getCifrado().tamanio(); i++) {
			introducir_bola(i, combinacion.getCombinacion()[i].getColor(), tablero);
		}
		confirmar_fila(tablero);

	}

	public Combinacion comb_random(Tablero tablero) {
		Random rnd = new Random();
		Combinacion combinacion = new Combinacion(tablero.numero_de_casillas());
		Color color = null;
		int i;

		do {
			// Devuelve colores del modo fácil
			for (i = 0; i < tablero.numero_de_casillas(); i++) {

				switch (rnd.nextInt(8)) {
					case 0:
						color = Color.NEGRO;
						break;
					case 1:
						color = Color.ROJO;
						break;
					case 2:
						color = Color.VERDE;
						break;
					case 3:
						color = Color.AMARILLO;
						break;
					case 4:
						color = Color.AZUL;
						break;
					case 5:
						color = Color.MORADO;
						break;
					case 6:
						color = Color.CELESTE;
						break;
					case 7:
						color = Color.MORADO_CLARO;
						break;
					case 8:
						color = Color.GRIS;
						break;
					default:
						break;
				}

				combinacion.cambiar_color_casilla(i, color);
			}
		} while (combinacion.comprobar_colores_repes());
		return combinacion;
	}

	@Override
	protected void introducir_aciertos(Tablero tablero) {
		int ind_Negr_Blan[];

		ind_Negr_Blan = tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result())
				.calcular_respuesta(tablero.getCifrado());

		tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result()).colocar_respuesta(ind_Negr_Blan[0],
				ind_Negr_Blan[1]);
	}

	// Planteamiento
	/*
	 * 1.- Si se ha introducido una fila previamente 
	 * 	1.1.- Se guarda la combinación y el resultado (resultado = (ind_Negros x 10) + ind_Blancos)
	 * 
	 * 
	 * 2.- Si no se han encontrado los colores
	 * 
	 * 	2.1.- Si se ha introducido una fila previamente 
	 * 		2.1.1.- Se guarda la cantidad de aciertos del color introducido, sin importar si el indicador es negro o gris
	 * 
	 * 	2.2.- Si era el último color o se han encontrado todos los colores 2.2.1.- Se
	 * 	guarda esa información
	 * 
	 * 	2.3.- Si no 
	 * 		2.3.1.- Se guarda una combinación compuesta por un solo color
	 * 
	 * 
	 * 3.- Si se han encontrado todos los colores
	 * 
	 * 	3.1.- Si es el primer turno después de encontrar los colores 
	 * 		3.1.1.- Se rellena la combinación de uno de los colores que no esté
	 * 
	 * 	3.2.- Si no se ha hecho ya, se coge un color,de los encontrados,, que tenga una sola casilla en el cifrado y no se haya encontrado su posición 
	 * 		3.2.1.- Si en el intento anterior no se encontró, se tacha la posición y se pasa a la siguiente 
	 * 		3.2.2.- Se coloca en la primera posición disponible 
	 * 		3.2.3.- Se guarda la combinación
	 * 
	 * 	3.3.- Si se han acabado los colores con una sola casilla en el cifrado, se 
	 * 	van cogiendo el resto colores, que tengan más de una casilla en el cifrado y
	 * 	no se haya encontrado su posición 
	 * 		3.3.1.- Si quedan en el cifrado el mismo número de casillas sin color encontrado que en la cantidad del color que se
	 * 		esta probando se colocan 
	 * 		3.3.2.- Si no 
	 * 			3.3.2.1.- Se va provando uno a uno al igual que en el paso 3.2 
	 * 		3.3.3.- Se guarda la combinación
	 * 
	 * 3.4.- Se devuelve la combinación
	 */
	public Combinacion analisis_intento() {
		Combinacion nueva_comb = new Combinacion(tablero.numero_de_casillas());
		Color color_no_encontrado, color_a_encontrar;
		int i, j, cantidad_color_actual, total_colores_encontrados, turno_colores_encontrados;

		cantidad_color_actual = turno_colores_encontrados = total_colores_encontrados = 0;
		color_no_encontrado = color_a_encontrar = null;

		if (!tablero.getComb_y_result().isEmpty()) {
			tableroAux.put(tablero.calcular_resultado(tablero.ultima_combinacion_y_result()),
					tablero.coger_ultima_combinacion_y_result());
		}

		if (!colores_encontrados) {
			if (!tablero.getComb_y_result().isEmpty()) {
				cantidad_color_actual = tableroAux.lastKey() / 10 + (tableroAux.lastKey() % 10);
				cant_colores[tablero.getComb_y_result().size()] = (byte) cantidad_color_actual;
			}

			for (i = 0; i < cant_colores.length; i++) {
				total_colores_encontrados++;
				turno_colores_encontrados = tablero.getComb_y_result().size();
			}

			if (tablero.getComb_y_result().size() == 10 || total_colores_encontrados == 8) {
				colores_encontrados = true;
				ordenar_colores_por_cantidad();
			} else {
				color_a_encontrar = coger_color_dificil(tablero.getComb_y_result().size());
				for (i = 0; i < cant_colores.length; i++) {
					nueva_comb.cambiar_color_casilla(i, color_a_encontrar);
				}
			}

		} else {

			if (turno == turno_colores_encontrados + 1) { // REPASAR
				for (i = 0; i < cant_colores.length; i++) {
					if (cant_colores[i] == 0) {
						color_no_encontrado = coger_color_dificil(i);

						for (j = 0; j < cant_colores.length; j++) {
							nueva_comb.cambiar_color_casilla(j, color_no_encontrado);
						}

					}
				}

			} else {
				while (cant_colores[color_actual] == 0 || !color_encontrado[color_actual]) {
					color_actual++;
				}
				while (color_en_cifr[posicion_actual]) {
					posicion_actual++;
				}

				if (cant_colores[color_actual] == 1) {
					if(tableroAux.containsKey((byte)(tableroAux.lastKey()+10))) {
						color_en_cifr[posicion_actual] = true;
						color_encontrado[color_actual] = true;
					}else {
						nueva_comb.cambiar_color_casilla(posicion_actual, coger_color_dificil(color_actual));
					}
					

				} else if (cant_colores[color_actual] > 1 && (cant_colores.length - color_actual) == 0) {

				} else if (cant_colores[color_actual] > 1) {

				}

			}

		}

		return nueva_comb;
	}

	private void ordenar_colores_por_cantidad() {
		byte i, j, aux;

		for (i = 0; i < cant_colores.length; i++) {
			for (j = 0; j < cant_colores.length; j++) {
				if (cant_colores[i] > cant_colores[j]) {
					aux = cant_colores[i + 1];
					cant_colores[i + 1] = cant_colores[i];
					cant_colores[i] = aux;
				}
			}

		}
	}

	private Color coger_color_dificil(int numero) {
		Color color = null;

		switch (numero) {
			case 1:
				color = Color.ROJO;
				break;
			case 2:
				color = Color.AMARILLO;
				break;
			case 3:
				color = Color.AZUL;
				break;
			case 4:
				color = Color.NEGRO;
				break;
			case 5:
				color = Color.CELESTE;
				break;
			case 6:
				color = Color.GRIS;
				break;
			case 7:
				color = Color.MORADO;
				break;
			case 8:
				color = Color.MORADO_CLARO;
				break;
			case 9:
				color = Color.VERDE;
				break;
			case 10:
				color = Color.VERDE_CLARO;
				break;

			default:
				break;
		}

		return color;
	}

}
