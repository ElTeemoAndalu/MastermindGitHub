package mastermind;

public enum Color {
	
	NEGRO("\u001B[40m"),
	ROJO("\u001B[101m"),
	VERDE("\u001B[42m"),
	AMARILLO("\u001B[103m"),
	AZUL("\u001B[104m"),
	MORADO("\u001B[105m"),
	CELESTE("\u001B[106m"),
	BLANCO("\u001B[47m"),
	GRIS("\u001B[1;40m"),
	VERDE_CLARO("\u001B[1;42m"),
	RESETEAR("\u001B[0m");
	
	private String cod_color;
	
	private Color(String cod_color) {
		this.cod_color = cod_color;
	}
	
	public String getCod_Color() {
		return cod_color;
	}
}
