package mastermind;

import java.util.HashMap;
import java.util.Random;

public class IA extends Jugador {

	private HashMap<Byte, Comb_y_result> tableroAux;
	private boolean colores_encontrados, color_encontrado[], color_en_cifr[];
	private byte min_cantidad,turno_colores_encontrados,total_colores_encontrados,posicion_actual, posicion_anterior,color_actual, cont_color_restantes,cant_colores[];
	
	protected IA(Dificultad dificultad) {
		tablero = new Tablero(dificultad);
		turno = 0;
		filaCreada = false;
		if (dificultad == Dificultad.DIFICIL) {
			colores_encontrados = false;
			cant_colores = new byte[dificultad.getColores()];
			color_en_cifr = new boolean[dificultad.getCasillas()];
			color_encontrado = new boolean[dificultad.getColores()];
			for (int i = 0; i < cant_colores.length; i++) {
				cant_colores[i] = 0;
				color_encontrado[i] = false;
				if (i < 8) {
					color_en_cifr[i] = false;
				}
				tableroAux = new HashMap<>();
				color_actual = posicion_anterior = posicion_actual =  cont_color_restantes = total_colores_encontrados = turno_colores_encontrados = 0;
				min_cantidad = 1;
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
		filaCreada = false;
		turno++;
	}

	public void introducir_cifrado(Combinacion combinacion, Tablero tablero) {
		for (int i = 0; i < tablero.getCifrado().tamanio(); i++) {
			introducir_bola(i, combinacion.getCombinacion()[i].getColor(), tablero);
		}
		filaCreada = false;
		turno++;

	}

	public Combinacion comb_random(Tablero tablero,boolean no_repetir_color) {
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
		} while (combinacion.comprobar_colores_repes() && no_repetir_color);
		return combinacion;
	}

	@Override
	protected void introducir_aciertos(Tablero tablero) {
		int ind_Negr_Blan[];

		ind_Negr_Blan = tablero.getComb_y_result().get(tablero.ultima_comb_y_result())
				.calcular_respuesta(tablero.getCifrado());

		tablero.getComb_y_result().get(tablero.ultima_comb_y_result()).colocar_respuesta(ind_Negr_Blan[0],
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
		byte ultimo_pincho_negro,i, j,casillas_restantes;
		boolean siguiente_cant = true;
		
		ultimo_pincho_negro = casillas_restantes = 0;
		color_no_encontrado = color_a_encontrar = null;
		
		
		
		
		//Si no se han encontrado todos los colores
		if (!colores_encontrados) {
			//Y no esta vacío, se guarda el resultado de la última combinación
			if (!tablero.getComb_y_result().isEmpty()) {
				cant_colores[tablero.getComb_y_result().size()-1] = (byte) (tablero.calcular_resultado(tablero.ultima_comb_y_result())/10);
			}
			
			if (tablero.getComb_y_result().size() > 0) {
				//Sumamos al total la cantidad del color que se ha colocado para saber cuando llegamos al máximo
				total_colores_encontrados += cant_colores[tablero.getComb_y_result().size() - 1];
			}
			
			
			//Si se han intentado los 10 colores o se han encontrado los colores de todas las casillas
			if (total_colores_encontrados == 8) {
				//Pasamos a probar posiciones y ordenamos los colores por cantidad en el cifrado
				colores_encontrados = true;
				turno_colores_encontrados = (byte)tablero.getComb_y_result().size();
				//ordenar_colores_por_cantidad();
				nueva_comb = analisis_intento();	
			//Si no
			} else {
				//Cogemos uno de los colores que no hayamos usado
				color_a_encontrar = coger_color(tablero.getComb_y_result().size());
				for (i = 0; i < nueva_comb.tamanio(); i++) {
					//Y rellenamos de él la combinación que pondremos en el tablero
					nueva_comb.cambiar_color_casilla(i, color_a_encontrar);
				}
			}
		
			//Si se han encontrado todos los colores
		} else {
			//Y es el turno justo después de encontrarlos
			if (turno == turno_colores_encontrados + 1) { 
				for (i = 0; i < cant_colores.length; i++) {
					if (cant_colores[i] == 0) {
						//Cogemos un color que no este en el cifrado
						color_no_encontrado = coger_color(i);
					}
				}
				for (j = 0; j < color_en_cifr.length; j++) {
					// Y lo usamos para rellenar la combinación que irá al tablero
					nueva_comb.cambiar_color_casilla(j, color_no_encontrado);
				}
				
				
				//El resto de turnos hacemos lo siguiente
			} else {//Si no esta vacío, se guarda la cantidad de pinchos negros y grises en un tablero auxiliar
				
				if (!tablero.getComb_y_result().isEmpty()) {
					tableroAux.put(Byte.valueOf(tablero.calcular_resultado(tablero.ultima_comb_y_result())),tablero.coger_ultima_comb_y_result());
				}
				
				//Guardamos la cantidad(x 10) de pinchos negros de la mejor combinación 
				for (j = 0; j < color_en_cifr.length; j++) {
					if (color_en_cifr[j]) {
						ultimo_pincho_negro++;
					}
				}
				
				ultimo_pincho_negro *=10;
				
				if(tableroAux.containsKey(Byte.valueOf((byte)(ultimo_pincho_negro+10)))) {
					//Indicamos que se ha encontrado la posición de un color y el propio color
					color_en_cifr[posicion_anterior] = true;
					cont_color_restantes--;
					if (cont_color_restantes == 0) {
						color_encontrado[color_actual] = true;
						color_actual = 0;
					} else {
						color_encontrado[color_actual] = false;
					}
					posicion_actual = 0;

				}
				
				for (j = 0; j < color_en_cifr.length; j++) {
					if(!color_en_cifr[j]) {
						casillas_restantes++;
					}
				}
			
				
				for (j = 0; j < cant_colores.length; j++) {
					if(cant_colores[j] == min_cantidad) {
						if(!color_encontrado[j]) {
							siguiente_cant = false;
						}
					}
					
				}

				if(siguiente_cant) {
					min_cantidad++;
				}
				
				//Cogemos uno de los colores que estan en el cifrado, que todavía no hayamos ubicado en este
				while (color_actual < cant_colores.length-1 && (cant_colores[color_actual] != min_cantidad || color_encontrado[color_actual])) {
					color_actual++;
				}
				
				
				//Nos vamos a una posición que no tenga ya un color defitivo
					while (posicion_actual < color_en_cifr.length-1 && color_en_cifr[posicion_actual]) {
						posicion_actual++;
					}

				
				//Buscamos en nuestro tablero auxiliar una cantidad igual a la que acabamos de calcular y copiamos su combinación
				for (j = 0; j < nueva_comb.tamanio(); j++) {
					nueva_comb.cambiar_color_casilla(j, tableroAux.get(Byte.valueOf(ultimo_pincho_negro)).getCombinacion()[j].getColor());
				}
				
				
				//Si la cantidad del color que hemos escogido es igual a 1
				if (cant_colores[color_actual] == 1) {
					//Y la combinación anterior ha dado como resultado un picho negro mas
					if(tableroAux.containsKey(Byte.valueOf((byte)(ultimo_pincho_negro+10)))) {
						//Y rehacemos los pasos anteriores con la información obtenida
						nueva_comb = analisis_intento();
						
						//Si no ha dado un pincho negro más
					}else {
						//Probamos el color en la siguiente casilla disponible
						if (cont_color_restantes == 0) {
							cont_color_restantes = cant_colores[color_actual];
						}
						posicion_anterior = posicion_actual;
						nueva_comb.cambiar_color_casilla(posicion_actual, coger_color(color_actual));
						posicion_actual++;
					}
					
					//Si la catidad de colores es superior a 1
				} else if (cant_colores[color_actual] > 1  ) {
					
					//Y la cantidad de casillas que quedan es igual a la cantidad del color que hemos escogido
					if((cant_colores[color_actual] - casillas_restantes) == 0) { // PONER COLORES RESTANTES
						if (cont_color_restantes == 0) {
							cont_color_restantes = cant_colores[color_actual];
						}
						
						//Los colocamos en la combinación que pondremos en el tablero
						while (cont_color_restantes > 0) {
							if(color_en_cifr[posicion_actual] == false) {
								color_en_cifr[posicion_actual] = true;
								nueva_comb.cambiar_color_casilla(posicion_actual, coger_color(color_actual));
								cont_color_restantes--;
							}
								posicion_actual++;
							
						}
						
					//Si no queda la misma cantidad de casillas que la cantidad del color escogido
					}else {
							//Hacemos lo mismo que cuando teníamos una sola casilla para el color pero tantas veces como el color aparezca
							if(tableroAux.containsKey(Byte.valueOf((byte)(ultimo_pincho_negro + 10)))) {
								nueva_comb = analisis_intento();
							}else {
								posicion_anterior = posicion_actual;
								nueva_comb.cambiar_color_casilla(posicion_actual, coger_color(color_actual));
								posicion_actual++;
							}
							
						
						
					}

				} 

			}

		}

		return nueva_comb;
	}

	/*private void ordenar_colores_por_cantidad() {
		byte i, j, aux;

		for (i = 0; i < cant_colores.length; i++) {
			for (j = (byte) (i + 1); j < cant_colores.length; j++) {
				if(cant_colores[i] == 0) {
					
				}
				if (cant_colores[i] > cant_colores[j]) {
					aux = cant_colores[i];
					cant_colores[i] = cant_colores[j];
					cant_colores[j] = aux;
				}
			}

		}
	}*/

	private Color coger_color(int numero) {
		Color color = null;

		switch (numero) {
			case 0:
				color = Color.ROJO;
				break;
			case 1:
				color = Color.AMARILLO;
				break;
			case 2:
				color = Color.AZUL;
				break;
			case 3:
				color = Color.NEGRO;
				break;
			case 4:
				color = Color.CELESTE;
				break;
			case 5:
				color = Color.GRIS;
				break;
			case 6:
				color = Color.MORADO;
				break;
			case 7:
				color = Color.MORADO_CLARO;
				break;
			case 8:
				color = Color.VERDE;
				break;
			case 9:
				color = Color.VERDE_CLARO;
				break;

			default:
				color = Color.NEGRO;
				break;
		}

		return color;
	}

}
