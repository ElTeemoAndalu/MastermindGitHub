package mastermind;



public abstract class Jugador {

	protected Tablero tablero;
	protected int turno;
	protected boolean filaCreada;

	
	protected abstract void introducir_aciertos(Tablero tablero);
	protected abstract void introducir_cifrado(Tablero tablero);
	
	protected void introducir_bola() {
		
	}
	
	protected boolean confirmar_fila(Tablero tablero) {
		boolean fila_valida = false;
		if (turno == 0) { // Si estamos cifrando, se comprueba que no se repitan colores
			if (!tablero.getCifrado().comprobar_colores_repes() && tablero.getCifrado().es_comb_llena()) {
				fila_valida = true;
			}
		}else{ // Si no, solo que este llena, con que colores no importa
			if(tablero.getCifrado().es_comb_llena()) {
				fila_valida = true;
			}
		}
		turno++;
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
