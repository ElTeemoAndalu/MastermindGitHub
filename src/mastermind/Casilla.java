package mastermind;

/**
 * Esta clase guarda un color y puede dibujarlo en formato casilla.
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */

public class Casilla implements Dibujable,Cloneable {
	/**
	 * Almacena un color del enum Color.
	 * @see Color
	 */
	protected Color color;

	// Constructor
	/**
	 * Construye un nuevo objeto con el color por defecto ( Negro ).
	 */
	public Casilla() {
		color = Color.NEGRO;
	}

	// Getter y Setter
	/**
	 * Devuelve el color almacenado.
	 * @return color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Establece como color almacenado el color que se le pasa.
	 * @param color El color por el que se le quiere cambiar.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	
	//Metodo para dibujar el acierto
	/**
	 * Dibuja con el color almacenado un cuadrado, que simboliza una casilla.
	 */
	@Override
	public void dibujar_elemento() {
		System.out.printf("%s  %s ",color.getCod_Color(),Color.RESETEAR.getCod_Color());
	}

	// Equals
	/**
	 * Compara este color con el objeto que se le pasa.
	 * @param El objeto con el que se va a comparar.
	 * @return true: si son iguales.
	 * 		   false: si son diferentes.
	 */
	public boolean equals(Object obj) {
		boolean resultado = false;
		if (obj instanceof Casilla && color == (((Casilla) obj).color)) {
			resultado = true;
		}
		return resultado;
	}
	

}
