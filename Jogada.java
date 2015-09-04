package campoMinadoG;

import javax.swing.JOptionPane;

public class Jogada {

	public static Mapa jogar(Mapa campo, int i, int j, boolean movimento) {

		if (movimento) {
			campo.setValor(i, j, 11);

		} else {
			if (campo.getCampor()[i][j] == 9) {
				Window.acao = false;
				Window.time.stop();
				JOptionPane.showMessageDialog(null, "Voçe perdeu");
				campo.setCampo(campo.getCampor());

			} else if (campo.getCampor()[i][j] == 0) {

				verificarCamapoLivre(i, j);

				for (int k = 0; k < campo.getCampo().length; k++) {
					for (int l = 0; l < campo.getCampo()[k].length; l++) {
						if (campo.getCampo()[k][l] == 0) {
							for (int i2 = 0; i2 < 3; i2++) {
								for (int j2 = 0; j2 < 3; j2++) {
									if (Mapa.verificaValidade(campo.getCampo(), k + i2 - 1, l + j2 - 1))
										campo.setValor(k + i2 - 1, l + j2 - 1,
												campo.getCampor()[k + i2 - 1][l + j2 - 1]);
								}

							}
						}
					}
				}
				verificarVitoria();
			} else if (Window.campo.getCampo()[i][j] == 10) {
				campo.setValor(i, j, campo.getCampor()[i][j]);
				verificarVitoria();
			} else {
				abrir(i, j);
				verificarVitoria();

			}
		}

		return campo;
	}

	static void abrir(int i, int j) {
		int parte[][] = Mapa.criarParte(Window.campo.getCampo(), i, j);
		int parter[][] = Mapa.criarParte(Window.campo.getCampor(), i, j);
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
						Window.campo.setValor(i + k - 1, j + k2 - 1, parter[k][k2]);
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

	static void verificarCamapoLivre(int x, int y) {
		int[][] parte = Mapa.criarParte(Window.campo.getCampo(), x, y);
		int[][] parteMapa = Mapa.criarParte(Window.campo.getCampor(), x, y);

		for (int i = 0; i < parte.length; i++) {
			for (int j = 0; j < parte.length; j++) {
				if (parteMapa[i][j] == 0 && parte[i][j] == 10) {
					Window.campo.setValor(x + i - 1, y + j - 1, Window.campo.getCampor()[x + i - 1][y + j - 1]);
					verificarCamapoLivre(x + i - 1, y + j - 1);
				}
			}
		}

	}

	static void verificarVitoria() {
		int referencia = 0;
		int area = 0;

		for (int i = 0; i < Window.campo.getCampor().length; i++) {
			for (int j = 0; j < Window.campo.getCampor()[i].length; j++) {
				if (Window.campo.getCampo()[i][j] == 11 && Window.campo.getCampor()[i][j] == 9)
					referencia++;
				if (Window.campo.getCampo()[i][j] != 10 && Window.campo.getCampor()[i][j] != 9)
					area++;
			}
		}

		if (referencia == Window.campo.getNminas()
				|| (Window.campo.getCampo().length * Window.campo.getCampo()[0].length
						- Window.campo.getNminas() == area)) {

			if (referencia == Window.campo.getNminas())
				System.out.println("referencia");
			if ((Window.campo.getCampo().length * Window.campo.getCampo()[0].length - Window.campo.getNminas() == area))
				System.out.println("area");

			Window.campo.setCampo(Window.campo.getCampor());
			Window.acao = false;
			Window.time.stop();
			JOptionPane.showMessageDialog(null, "Voce ganhou      Time: " + Window.contador / 60 + ":" + Window.contador % 60);
		}
	}

}
