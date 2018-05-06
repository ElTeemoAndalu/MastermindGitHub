package mastermind;


/**
 * Esta clase guarda un color y puede dibujarlo en formato indicador(un circulo pequeño).
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */

public class Indicador extends Casilla {
	

	// Constructor
		/**
		 * Construye un nuevo objeto con el color por defecto ( IND_INVI -> color blanco, invisible si esta en fondo blanco ).
		 */
	public Indicador() {
		color = Color.IND_INVI;
	}

	// Metodo para dibujar el indicador
	/**
	 * Dibuja con el color almacenado un circulo pequeño, que simboliza un indicador.
	 */
	public void dibujar_elemento() {
		System.out.printf("%s\u25CF%s ",color.getCod_Color(),Color.RESETEAR.getCod_Color());
	}

}
