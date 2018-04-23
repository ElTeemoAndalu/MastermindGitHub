package mastermind;

public class Indicador extends Casilla {
	

	// Constructor
	public Indicador() {
		super();
	}

	// Metodo para dibujar el indicador
	public void dibujar_elemento() {
		System.out.printf("%s %s ",color.getCod_Color(),Color.RESETEAR.getCod_Color());
	}

}
