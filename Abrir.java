import javax.swing.JOptionPane;

public class Abrir implements Jogadas {

	private JanelaCampoMinado window;

	public Abrir(int i, int j, JanelaCampoMinado window) {
		this.window = window;
		jogar(i, j, window);
	}

	// Faz a ligca de quando clicado em um ponto
	@Override
	public void jogar(int i, int j, JanelaCampoMinado window) {

		if (window.getCampo().getCampoReferencia()[i][j] == 9) {
			window.setAcao(false);
			window.pararTimer();
			JOptionPane.showMessageDialog(null, "Você perdeu");
			window.getCampo().setCampoJogador(window.getCampo().getCampoReferencia());
			return;

		} else if (window.getCampo().getCampoReferencia()[i][j] == 0) {

			verificarCamapoLivre(i, j);

			for (int k = 0; k < window.getCampo().getCampoJogador().length; k++) {
				for (int l = 0; l < window.getCampo().getCampoJogador()[k].length; l++) {
					if (window.getCampo().getCampoJogador()[k][l] == 0) {
						for (int i2 = 0; i2 < 3; i2++) {
							for (int j2 = 0; j2 < 3; j2++) {
								if (Mapa.isValido(window.getCampo().getCampoJogador(), k + i2 - 1, l + j2 - 1))
									window.getCampo().setValor(k + i2 - 1, l + j2 - 1,
											window.getCampo().getCampoReferencia()[k + i2 - 1][l + j2 - 1]);
							}

						}
					}
				}
			}

		} else if (window.getCampo().getCampoJogador()[i][j] == 10) {
			window.getCampo().setValor(i, j, window.getCampo().getCampoReferencia()[i][j]);
		} else{
			new Aberto(i,j, window);
		}
		verificarVitoria();
	}

	// Abri o campo quando aclidado em um ponto com 0 minas
	private void verificarCamapoLivre(int x, int y) {
		int[][] parte = Mapa.criaParte(window.getCampo().getCampoJogador(), x, y);
		int[][] parteMapa = Mapa.criaParte(window.getCampo().getCampoReferencia(), x, y);

		for (int i = 0; i < parte.length; i++) {
			for (int j = 0; j < parte.length; j++) {
				if (parteMapa[i][j] == 0 && parte[i][j] == 10) {
					window.getCampo().setValor(x + i - 1, y + j - 1,
							window.getCampo().getCampoReferencia()[x + i - 1][y + j - 1]);
					verificarCamapoLivre(x + i - 1, y + j - 1);
				}
			}
		}

	}

	private void verificarVitoria() {
		int referencia = 0;
		int area = 0;

		for (int i = 0; i < window.getCampo().getCampoReferencia().length; i++) {
			for (int j = 0; j < window.getCampo().getCampoReferencia()[i].length; j++) {
				if (window.getCampo().getCampoJogador()[i][j] == 11
						&& window.getCampo().getCampoReferencia()[i][j] == 9)
					referencia++;
				if (window.getCampo().getCampoJogador()[i][j] != 10
						&& window.getCampo().getCampoReferencia()[i][j] != 9)
					area++;
			}
		}

		if (referencia == window.getCampo().getNMinas()
				|| (window.getCampo().getCampoJogador().length * window.getCampo().getCampoJogador()[0].length
						- window.getCampo().getNMinas() == area)) {

			if (referencia == window.getCampo().getNMinas())
				System.out.println("referencia");
			if ((window.getCampo().getCampoJogador().length * window.getCampo().getCampoJogador()[0].length
					- window.getCampo().getNMinas() == area))
				System.out.println("area");

			window.getCampo().setCampoJogador(window.getCampo().getCampoReferencia());
			window.setAcao(false);
			window.pararTimer();
			JOptionPane.showMessageDialog(null,
					"Voce ganhou      Time: " + window.getContador() / 60 + ":" + window.getContador() % 60);
		}
	}

}
