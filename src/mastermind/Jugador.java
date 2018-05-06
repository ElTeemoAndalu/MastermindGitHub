package mastermind;

/**
 * Esta clase sirve como plano para definir lo que es un jugador y definir unas pocas acciones que este puede hacer.
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */

public abstract class Jugador {
	
	/**
	 * Almacena el tablero del jugador
	 */
	protected Tablero tablero;
	/**
	 * Guarda el turno en el que se encuentra el jugador actualmente
	 */
	protected int turno;
	/**
	 * Guarda verdadero o falso dependiendo de si se ha creado un fila en el turno actual
	 */
	protected boolean filaCreada;
	
	/**
	 * Metodo que permite al jugador especificado introducir en el tablero de su rival los indicadores correspondientes a la ultima combinacion de este
	 * @param tablero Tablero del rival
	 * @see Tablero
	 */
	protected abstract void introducir_aciertos(Tablero tablero);
	
	/**
	 * Declara un turno finalizado, sumandole 1 al turno actual, comprobando si la fila es valida y declarando que en el siguiente turno se puede volver ha añadir una fila.
	 * @param tablero Tablero del rival
	 * @return true: si la fila es valida
	 * 		   false: si la fila no es valida
	 * @see Tablero
	 */
	protected boolean confirmar_fila(Tablero tablero) {
		boolean fila_valida = false;
		
		if (!filaCreada && turno > 0) { 
			tablero.aniadir_combinacion(); 
			filaCreada = true;
		}
		
		if (turno == 0) { // Si estamos cifrando, se comprueba que no se repitan colores
			if (!tablero.getCifrado().comprobar_colores_repes()) {
				fila_valida = true;
			}
		} else { // Si no, solo que este llena, con que colores no importa
				fila_valida = true;
		}
		if (fila_valida) {
			turno++;
		}
		filaCreada = false;

		return fila_valida;

	}
	
	/**
	 * Devuelve el tablero del jugador actual.
	 * @return Tablero del jugador 
	 */
	public Tablero getTablero() {
		return tablero;
	}
	
	/**
	 * Devuelve el turno en el que esta el jugador escogido.
	 * @return Numero que representa el turno 
	 */
	public int getTurno() {
		return turno;
	}

}
