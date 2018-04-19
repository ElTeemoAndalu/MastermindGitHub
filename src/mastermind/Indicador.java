package mastermind;

public class Indicador extends Casilla {
	
	private Color color;

	// Constructor
	public Indicador() {
		super();
	}

	// Metodo para dibujar el acierto
	@Override
	public void dibujar_elemento() {
		System.out.printf("%s %s ", color.getCod_Color(), Color.RESETEAR.getCod_Color());
	}

}
