package mastermind;

/**
 * Esta clase sirve para guiar al usuario por las pantallas principales del juego y para crear partidas.
 * 
 * 
 * 
 * @author Nicolas Navas Gomez
 * @version 1.0
 * @since 1.0
 *
 */

public class Mastermind {
	
	/** Almacena la partida que seleccione el usuario poseteriormente
	 * @see Partida*/
	private Partida partida;
	
	/**
	 * Al construirse el objeto Mastermind se lanza el metodo que enlazara con todos los demas, el menu principal
	 */
	public Mastermind() {
		menu_principal();
	}
	
	/**
	 * Metodo para mostrar la palabra mastermind en letras grandes formadas por diferentes simbolos
	 */
	public void mostrar_Mastermind() {
		System.out.println(
				" ____      ____         _____       _________   _______________   ___________   ___________   ____      ____   _______________   _____     ___   __________");
		System.out.println(
				"|    \\    /    |       /  _  \\     |         | |               | |           | |    ___    | |    \\    /    | |               | |     \\   |   | |          \\ ");
		System.out.println(
				"|     \\  /     |      /  / \\  \\    |  _______| |_____     _____| |    _______| |   |   |   | |     \\  /     | |_____     _____| |      \\  |   | |    ___    \\");
		System.out.println(
				"|      \\/      |     /  /___\\  \\   | |               |   |       |   |______   |   |___|   | |      \\/      |       |   |       |       \\ |   | |   |   \\    |");
		System.out.println(
				"|              |    /   _____   \\  | |_______        |   |       |    ______|  |    _     _| |              |       |   |       |    |\\  \\|   | |   |    |   |");
		System.out.println(
				"|    |\\__/|    |   /   /     \\   \\ |______   |       |   |       |   |_______  |   | \\    \\  |    |\\__/|    |  _____|   |_____  |    | \\      | |   |___/    |   ");
		System.out.println(
				"|    |    |    |  /   /       \\   \\ ______|  |       |   |       |           | |   |  \\    \\ |    |    |    | |               | |    |  \\     | |           /   ");
		System.out.println(
				"|____|    |____| /___/         \\___\\|________|       |___|       |___________| |___|   \\___ \\|____|    |____| |_______________| |____|   \\____| |__________/");
	}
	
	/**
	 * Metodo que muestra las opciones del menu principal del juego. A través de el se accede al reso de menus.
	 */
	public void menu_principal() {
		mostrar_Mastermind();
		switch (Teclado.lecturaconlimites(1, 3, Teclado.LimiteInfySup.INCLUIDOS,
				"\n\n1.- Jugar\n2.- Creditos\n3.- Salir del juego\n")) {
			case 1:
				dificultades();
				break;
			case 2:
				creditos();
				break;
			case 3:
				System.out.println("\n\nGracias por jugar Mastermind.");
				break;

			default:
				break;
		}
	}
	
	/**
	 * Metodo que enlaza al menu de modos de la dificultad facil en caso de selecciona la primera opcion, en el resto de casos crea un Partida con la dificultad escogida.
	 */
	public void dificultades() {
		mostrar_Mastermind();
		switch (Teclado.lecturaconlimites(1, 3, Teclado.LimiteInfySup.INCLUIDOS,
				"\n\nDificultad\n\n1.- Facil(Dos modos)\n2.- Medio\n3.- Dificil")) {
			case 1:
				modos_dific_facil();
				break;
			case 2:
				partida = new Partida(Dificultad.MEDIO);
				partida.partida_medio();
				break;
			case 3:
				partida = new Partida(Dificultad.DIFICIL);
				partida.partida_dificil();
				break;
			default:
				break;
		}
		menu_principal();
	}
	
	/**
	 * Crea un Partida dependiendo del modo escogido, cifrando o descifrando .
	 */
	public void modos_dific_facil() {
		mostrar_Mastermind();
		switch (Teclado.lecturaconlimites(1, 3, Teclado.LimiteInfySup.INCLUIDOS,
				"\n\nModos de la dificultad facil\n\n1.- Descifrando combinaciones\n2.- Cifrando e indicando aciertos\n\n")) {
			case 1:
				partida = new Partida(Dificultad.FACIL,false);
				partida.partida_facil_descifrando();
				break;
			case 2:
				partida = new Partida(Dificultad.FACIL,true);
				partida.partida_facil_cifrando();
				break;
			default:
				break;
		}
	}
	
	/**
	 * Muestra los creditos del proyecto
	 */
	public void creditos() {
		mostrar_Mastermind();
		System.out.println(
				"Director: Nicolás Navas Gómez\n\nProgramador: Nicolás Navas Gómez\n\nAnalista: Nicolás Navas Gómez");
	}

	

	

}
