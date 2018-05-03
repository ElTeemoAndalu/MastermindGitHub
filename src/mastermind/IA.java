package mastermind;

import java.util.Random;
import java.util.TreeMap;

public class IA extends Jugador {
	
	private TreeMap<Byte, Combinacion> tableroAux;
	private boolean colores_encontrados;
	private byte[][] cant_colores;
	
	protected IA(Dificultad dificultad) {
		tablero = new Tablero(dificultad);
		turno = 0;
		filaCreada = false;
		colores_encontrados = false;
		cant_colores = new byte[10][10];
	}

	protected IA(Dificultad dificultad, boolean cifrando) {
		if (dificultad == Dificultad.FACIL && cifrando) {
			tablero = new Tablero(dificultad);
		}
		
		if (cifrando) {
			turno = 1;
		}else {
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
		
		ind_Negr_Blan = tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result()).calcular_respuesta(tablero.getCifrado());

		tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result()).colocar_respuesta(ind_Negr_Blan[0],ind_Negr_Blan[1]);
	}
	
	//Planteamiento
		/*
		 * 1.- Si se ha introducido una fila previamente
		 * 	1.1.- Se guarda la combinación y el resultado (resultado = (ind_Negros x 10) + ind_Blancos)
		 * 
		 * 
		 * 2.- Si no se han encontrado los colores
		 * 
		 * 	2.1.- Si se ha introducido una fila previamente
		 * 		2.1.1.- Se guarda la cantidad de aciertos del color introducido
		 * 
		 * 	2.2.- Si era el último color o se han encontrado todos los colores
		 * 		2.2.1.- Se guarda esa información
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
		 * 	3.3.- Si se han acabado los colores con una sola casilla en el cifrado, se van cogiendo el resto colores, 
		 *	que tengan más de una casilla en el cifrado y no se haya encontrado su posición
		 *		3.3.1.- Si quedan en el cifrado el mismo número de casillas sin color encontrado que en la cantidad del color que se esta probando
		 *		se colocan
		 *		3.3.2.- Si no
		 *			3.3.2.1.- Se va provando uno a uno al igual que en el paso 3.2
		 *		3.3.3.- Se guarda la combinación
		 * 	
		 * 	3.4.- Se devuelve la combinación
		 */
	public Combinacion analisis_intento() {
		Combinacion nueva_comb = new Combinacion(tablero.numero_de_casillas());
		Combinacion comb_auxiliar = new Combinacion(num_casillas); // Constructor nuevo o mapa de Comb_y_result
		
		if (!tablero.getComb_y_result().isEmpty()) {
			//metodos intermedios?
			tableroAux.put(tablero.getComb_y_result().get(tablero.ultima_combinacion_y_result()).getResultados()[tablero.ultima_combinacion_y_result()], tablero)
		}
		tableroAux.
		return nueva_comb;
	}


}
