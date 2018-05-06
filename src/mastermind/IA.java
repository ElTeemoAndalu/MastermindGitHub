package mastermind;

import java.util.HashMap;
import java.util.Random;

/**
 * Esta clase define lo que es una IA en una partida.
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */
public class IA extends Jugador {
	
	/**
	 * Almacena un tablero auxiliar en el que se guardan las combinaciones que se usaran para analizar e introducir combinaciones en el tablero principal.
	 */
	private HashMap<Byte, Comb_y_result> tableroAux;
	/**
	 * Determina si se han encontrado o no todos los colores del cifrado del tablero de la IA.
	 */
	private boolean colores_encontrados;
	/**
	 * Determina por cada color si se ha encontrado o no el color en el cifrado del tablero de la IA.
	 */
	private boolean color_encontrado[];
	/**
	 * Determina por cada posicion si ya tiene un color definitivo que coincide con el cifrado del tablero de la IA actual.
	 */
	private boolean color_en_cifr[];
	/**
	 * Almacena entre las cantidades de los colores encontrados cual es la minima. Esta cifra cambiara si todos los colores con la misma cantidad en el cifrado han sido encotrados.
	 */
	private byte min_cantidad;
	/**
	 * Guarda el turno en el que se ha encotrado todos los colores.
	 */
	private byte turno_colores_encontrados;
	/**
	 * Guarda la cantidad de colores encotrados en el cifrado hasta el momento.
	 */
	private byte total_colores_encontrados;
	/**
	 * Guarda la posicion en la que se estan haciendo las comprobaciones actualmente. Cambia si en la posicion actual ya se ha encotrado el color.
	 */
	private byte posicion_actual;
	/**
	 * Guarda la posicion anterior en la que estaba la pasicion actual.
	 */
	private byte posicion_anterior;
	/**
	 * Almacena el color que se esta intentando encontrar en el cifrado.
	 */
	private byte color_actual;
	/**
	 * Almacena la cantidad de veces que se repite un color en un cifrado.Además se va reduciendo cuando se encuentran, a modo de contador.
	 */
	private byte cont_color_restantes;
	/**
	 * Lista que almacena las cantidades de colores. Cada posicion corresponde a un color y almacena la cantidad de veces que se ha encontrado este en el cifrado.
	 */
	private byte cant_colores[];
	
	// Constructor
		/**
		 * Pensado para la dificultad media y dificil. Construye un nuevo objeto en base a la dificultad, creando el tablero cogiendo el numero de casillas de la dificultad e inicializando determinadas variables de esta clase en funcion tambien de si se ha escogido medio o dificil.
		 * @param dificultad Determina la longitud del tablero, el numero de colores que usará la IA y tambien las variables que usara la IA.
		 * @see Tablero
		 */
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
	/**
	 * Pensada para la dificultad facil. Crea el tablero dependiendo de si el usuario esta cifrando o no y detremina el turno en el que se encuentra la IA.
	 * @param dificultad Determina el tamaño del tablero y la cantidad de colores, si se crea.
	 * @param cifrando	Indica si el usuario esta ha elegido el modo cifrar o descifrar
	 */
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
	
	// Constructor
		/**
		 * Devuelve el tablero de la IA escogida.
		 * @return tablero de la IA 
		 */
	public Tablero getTablero() {
		return tablero;
	}
	
	/**
	 * Cambia el color de una de las bolas/casillas del cifrado o una combinacion normal, depende del turno y del tablero que se le pase
	 * @param posicion Determina que bola/casilla se cambiara de color
	 * @param color	Establece por que color sera cambiada la bola/casilla
	 * @param tablero Determina en que tablero se cambiara el color de la bola
	 */
	protected void introducir_bola(int posicion, Color color, Tablero tablero) {
		if (turno > 0) {
			tablero.coger_ultima_combinacion()[posicion].setColor(color);
		} else {
			tablero.getCifrado().getCombinacion()[posicion].setColor(color);
		}
	}

	/**
	 * Añade una combinacion al tablero si no se había hecho ya, y cambia los colores de la combinacion actual segun la combinacion que se le pase
	 * @param combinacion Combinacion que sirve de referencia para saber que colores poner en la combinacion actual
	 * @see #introducir_bola(int, Color, Tablero)
	 */
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
	
	/**
	 * Permite a la IA cambiar los colores del cifrado del contricante. Solo se usa en el turno 0 cuando toca establecer los cifrados.
	 * @param combinacion combinacion Combinacion que sirve de referencia para saber que colores poner en el cifrado
	 * @param tablero Tablero del rival
	 */
	public void introducir_cifrado(Combinacion combinacion, Tablero tablero) {
		for (int i = 0; i < tablero.getCifrado().tamanio(); i++) {
			introducir_bola(i, combinacion.getCombinacion()[i].getColor(), tablero);
		}
		filaCreada = false;
		turno++;

	}
	
	/**
	 * Devuelve a la IA una combinacion con colores aleatorios, que estaran repetidos o no dependiendo de la dificultad
	 * @param tablero	tablero que sirve como referencia para saber el tamaño de la combinacion
	 * @param no_repetir_color Determina si devolvera una combinacion con colores repetidos o no
	 * @return Una combinacion con colores aleatorios
	 */
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
	/**
	 * Permite a la IA introducir los aciertos de la ultima combinacion en el tablero de su rival
	 * @param tablero Tablero del rival
	 */
	protected void introducir_aciertos(Tablero tablero) {
		int ind_Negr_Blan[];

		ind_Negr_Blan = tablero.getComb_y_result().get(tablero.ultima_comb_y_result())
				.calcular_resultado(tablero.getCifrado());

		tablero.getComb_y_result().get(tablero.ultima_comb_y_result()).colocar_respuesta(ind_Negr_Blan[0],
				ind_Negr_Blan[1]);
	}

	/**
	 * Analiza la situacion de la IA en la partida actual y devuelve un combinacion en base a eso. Empieza por descubrir todos los colores en su cifrado y luego va averiguando sus posiciones.
	 * @return Combinacion con colores que dependen del turno y de los colores y posiciones encontradas
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

	/**
	 * Devuelve un color en base al numero introducido.
	 * @param numero Determina que color se devolvera
	 * @return	Color Color resultante del numero introducido
	 */
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
