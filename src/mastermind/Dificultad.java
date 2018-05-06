package mastermind;

/**
 * Esta clase guarda las dificultades disponibles para las partidas junto a los intentos, el numero de colroes disponibles, si se pueden repetir y el numero de intentos.
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */

public enum Dificultad {
	
	/** Facil. */
	FACIL(4,8,false,10),
	
	/** Medio. */
	MEDIO(5,8,false,15),
	
	/** Dificil. */
	DIFICIL(8,10,true,9999); //9999 Se traducirá a intentos infinitos
	
	/**
  * Crea un nuevo objeto dificultad con los parametros establecidos.
  *
  * @param casillas numero de casillas para las combinaciones
  * @param colores cantidad de colores disponibles
  * @param repeticion si se pueden repetir o no los colores
  * @param intentos numero maximo de intentos en una partida
  */
 private Dificultad(int casillas,int colores,boolean repeticion,int intentos) {
		this.casillas = casillas;
		this.colores = colores;
		this.repeticion = repeticion;
		this.intentos = intentos;
	}
	
	
	/** Numero de casillas para las combinaciones. */
	int casillas;
	/** Numero que determina los tipos de colores que pueden usar las combinaciones. */
	int colores;
	/** Numero maximo de intentos en una partida. */
	int intentos;
	
	/**
	 * Almacena si se pueden repetir colores o no en la dificultad escoginda.
	 */
	boolean repeticion;
	
	/**
	 * Devuelve un numero correspondiente al numero de casillas que tendran las combinaciones de la dificultad correspondiente.
	 * @return Numero de casillas para las combinaciones.
	 */
	public int getCasillas() {
		return casillas;
	}
	
	/**
	 * Devuelve un numero correspondiente al numero de casillas que tendran las combinaciones de la dificultad correspondiente.
	 * @return Numero de casillas para las combinaciones.
	 */
	public int getColores() {
		return colores;
	}
	
	/**
	 * Devuelve un numero correspondiente al numero de casillas que tendran las combinaciones de la dificultad correspondiente.
	 * @return Numero el máxmimo numero de intentos en una partida.
	 */
	public int getIntentos() {
		return intentos;
	}
	/**
	 * Devuelve si la partida contará con repeticion de colores en el cifrado o no.
	 * @return true: se pueden repetir colores.
	 * 		   false: no se pueden repetir colores.
	 */
	public boolean getRepeticion() {
		return repeticion;
	}

}
