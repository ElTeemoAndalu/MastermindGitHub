package mastermind;

public enum Dificultad {
	
	FACIL(4,8,false,10),
	MEDIO(5,8,false,15),
	DIFICIL(8,10,true,999); //999 Se traducirá a intentos infinitos
	
	private Dificultad(int casillas,int colores,boolean repeticion,int intentos) {
		this.casillas = casillas;
		this.colores = colores;
		this.repeticion = repeticion;
		this.intentos = intentos;
	}
	
	int casillas,colores,intentos;
	boolean repeticion;
	
	public int getCasillas() {
		return casillas;
	}
	public int getColores() {
		return colores;
	}
	public int getIntentos() {
		return intentos;
	}
	public boolean getRepeticion() {
		return repeticion;
	}

}
