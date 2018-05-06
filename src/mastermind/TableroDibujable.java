package mastermind;

/**
 * Interfaz que indica modos de dibujo especificos de Tablero.
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */

public interface TableroDibujable extends Dibujable {
	
	/**
	 * Dibuja en pantalla la combinacion correspondiente al turno de la partida en el que se esta.
	 */
	public void dibujar_comb_actual();
	
	/**
	 * Dibuja los tableros de los dos jugadores uno al lado del otro.
	 * @param tablero Tablero del rival.
	 */
	public void dibujar_tableros_lado_a_lado(Tablero tablero);
	
	/**
	 * Dibuja el tablero con el cifrado ocultado por casillas con el color negro.
	 */
	public void dibujar_con_cifr_oculto();
	
	/**
	 * Dibuja el cifrado del tablero.
	 */
	public void dibujar_cifrado();
}
