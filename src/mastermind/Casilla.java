package mastermind;


public class Casilla implements Dibujable,Cloneable {

	protected Color color;

	// Constructor
	public Casilla() {
		color = Color.NEGRO;
	}

	// Getter y Setter
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
	//Metodo para dibujar el acierto
	@Override
	public void dibujar_elemento() {
		System.out.printf("%s  %s ",color.getCod_Color(),Color.RESETEAR.getCod_Color());
	}

	// Equals
	public boolean equals(Object obj) {
		boolean resultado = false;
		if (obj instanceof Casilla && color == (((Casilla) obj).color)) {
			resultado = true;
		}
		return resultado;
	}
	
	//Clone
		protected Object clone(){
			Casilla clonado;
			try {
				clonado=(Casilla) super.clone();
			} catch (CloneNotSupportedException e) {
				clonado=null;
			}
			return clonado;		
		}

}
