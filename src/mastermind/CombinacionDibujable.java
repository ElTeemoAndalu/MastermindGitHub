package mastermind;

/**
 * Interfaz que indica modos de dibujo especificos de Combinacion.
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */

public interface CombinacionDibujable extends Dibujable {
	/**
	 * Dibuja en pantalla la combinacion con los colores reales de esta ocultados por el color negro.
	 */
	public void dibujar_elemento_oculto();

}
