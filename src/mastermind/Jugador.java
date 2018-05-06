package mastermind;

public abstract class Jugador {

	protected Tablero tablero;
	protected int turno;
	protected boolean filaCreada;

	protected abstract void introducir_aciertos(Tablero tablero);

	protected boolean confirmar_fila(Tablero tablero) {
		boolean fila_valida = false;
		
		if (!filaCreada && turno > 0) { 
			tablero.aniadir_combinacion(); 
			filaCreada = true;
		}
		
		if (turno == 0) { // Si estamos cifrando, se comprueba que no se repitan colores
			if (!tablero.getCifrado().comprobar_colores_repes()) {
				fila_valida = true;
			}
		} else { // Si no, solo que este llena, con que colores no importa
				fila_valida = true;
		}
		if (fila_valida) {
			turno++;
		}
		filaCreada = false;

		return fila_valida;

	}

	public Tablero getTablero() {
		return tablero;
	}

	public int getTurno() {
		return turno;
	}

}
