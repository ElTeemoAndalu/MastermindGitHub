package mastermind;

public class Mastermind {

	private Partida partida;

	public Mastermind() {
		menu_principal();
	}

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

	public void creditos() {
		mostrar_Mastermind();
		System.out.println(
				"Director: Nicolas Navas Gomez\n\nProgramador: Nicolas Navas Gomez\n\nAnalista: Nicolas Navas Gomez");
	}

	

	

}
