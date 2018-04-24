package mastermind;

public class Indicador extends Casilla {
	

	// Constructor
	public Indicador() {
		color = Color.IND_INVI;
	}

	// Metodo para dibujar el indicador
	public void dibujar_elemento() {
		System.out.printf("%s\u25CF%s ",color.getCod_Color(),Color.RESETEAR.getCod_Color());
	}

}
