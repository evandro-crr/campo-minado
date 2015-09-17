package campoMinado;

import javax.swing.JOptionPane;

public class Jogada {

	private static Window win;

	public static Window getWin() {
		return win;
	}

	public static void setWin(Window win) {
		Jogada.win = win;
	}

	public static void jogar(int i, int j, boolean movimento) {

		if (movimento) {
			win.getCampo().setValor(i, j, 11);

		} else {
			if (win.getCampo().getCampor()[i][j] == 9) {
				win.setAcao(false);
				win.pararTimer();
				JOptionPane.showMessageDialog(null, "Voçe perdeu");
				win.getCampo().setCampo(win.getCampo().getCampor());

			} else if (win.getCampo().getCampor()[i][j] == 0) {

				verificarCamapoLivre(i, j);

				for (int k = 0; k < win.getCampo().getCampo().length; k++) {
					for (int l = 0; l < win.getCampo().getCampo()[k].length; l++) {
						if (win.getCampo().getCampo()[k][l] == 0) {
							for (int i2 = 0; i2 < 3; i2++) {
								for (int j2 = 0; j2 < 3; j2++) {
									if (Mapa.verificaValidade(win.getCampo().getCampo(), k + i2 - 1, l + j2 - 1))
										win.getCampo().setValor(k + i2 - 1, l + j2 - 1,
												win.getCampo().getCampor()[k + i2 - 1][l + j2 - 1]);
								}

							}
						}
					}
				}
				verificarVitoria();

			} else if (win.getCampo().getCampo()[i][j] == 10) {
				win.getCampo().setValor(i, j, win.getCampo().getCampor()[i][j]);
				verificarVitoria();
			} else {
				abrir(i, j);
				verificarVitoria();

			}
		}

	}

	private static void abrir(int i, int j) {
		int parte[][] = Mapa.criarParte(win.getCampo().getCampo(), i, j);
		int parter[][] = Mapa.criarParte(win.getCampo().getCampor(), i, j);
		int referencia = 0;

		for (int k = 0; k < parte.length; k++) {
			for (int k2 = 0; k2 < parte.length; k2++) {
				if (parte[k][k2] == 11 && parter[k][k2] == 9)
					referencia++;
			}
		}

		if (referencia == parte[1][1]) {
			for (int k = 0; k < parter.length; k++) {
				for (int k2 = 0; k2 < parter.length; k2++) {
					if (parte[k][k2] == 10) {
						win.getCampo().setValor(i + k - 1, j + k2 - 1, parter[k][k2]);
					}
				}
			}
		}
	}

	static void mostrar(int[][] matriz) {
		String referencia = "   ";
		for (int i = 0; i < matriz[0].length; i++)

		{
			if (i < 10)
				referencia += i + "  ";
			else
				referencia += i + " ";
		}

		referencia += "\n";

		for (

		int i = 0; i < matriz.length; i++)

		{
			if (i < 10)
				referencia += i + " |";
			else
				referencia += i + "|";
			for (int j = 0; j < matriz[i].length; j++) {
				if (matriz[i][j] == 10)
					referencia += "x  ";
				else if (matriz[i][j] == 11 || matriz[i][j] == 9)
					referencia += "m  ";
				else
					referencia += matriz[i][j] + "  ";

			}
			referencia += "\n";
		}

		System.out.println(referencia);

	}

	private static void verificarCamapoLivre(int x, int y) {
		int[][] parte = Mapa.criarParte(win.getCampo().getCampo(), x, y);
		int[][] parteMapa = Mapa.criarParte(win.getCampo().getCampor(), x, y);

		for (int i = 0; i < parte.length; i++) {
			for (int j = 0; j < parte.length; j++) {
				if (parteMapa[i][j] == 0 && parte[i][j] == 10) {
					win.getCampo().setValor(x + i - 1, y + j - 1, win.getCampo().getCampor()[x + i - 1][y + j - 1]);
					verificarCamapoLivre(x + i - 1, y + j - 1);
				}
			}
		}

	}

	private static void verificarVitoria() {
		int referencia = 0;
		int area = 0;

		for (int i = 0; i < win.getCampo().getCampor().length; i++) {
			for (int j = 0; j < win.getCampo().getCampor()[i].length; j++) {
				if (win.getCampo().getCampo()[i][j] == 11 && win.getCampo().getCampor()[i][j] == 9)
					referencia++;
				if (win.getCampo().getCampo()[i][j] != 10 && win.getCampo().getCampor()[i][j] != 9)
					area++;
			}
		}

		if (referencia == win.getCampo().getNminas()
				|| (win.getCampo().getCampo().length * win.getCampo().getCampo()[0].length
						- win.getCampo().getNminas() == area)) {

			if (referencia == win.getCampo().getNminas())
				System.out.println("referencia");
			if ((win.getCampo().getCampo().length * win.getCampo().getCampo()[0].length
					- win.getCampo().getNminas() == area))
				System.out.println("area");

			win.getCampo().setCampo(win.getCampo().getCampor());
			win.setAcao(false);
			win.pararTimer();
			JOptionPane.showMessageDialog(null,
					"Voce ganhou      Time: " + win.getContador() / 60 + ":" + win.getContador() % 60);
		}
	}

}
