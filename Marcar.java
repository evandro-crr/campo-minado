import javax.swing.JOptionPane;

public class Marcar implements Jogadas {

	public Marcar(int i, int j, JanelaCampoMinado window) {
		jogar(i, j, window);

		verificarVitoria(window.getCampo(), window);
	}

	@Override
	public void jogar(int i, int j, JanelaCampoMinado window) {
		window.getCampo().setValor(i, j, 11);
	}

	private void verificarVitoria(Mapa mapa, JanelaCampoMinado window) {
		int referencia = 0;
		int area = 0;

		for (int i = 0; i < mapa.getCampoReferencia().length; i++) {
			for (int j = 0; j < mapa.getCampoReferencia()[i].length; j++) {
				if (mapa.getCampoJogador()[i][j] == 11 && mapa.getCampoReferencia()[i][j] == 9)
					referencia++;
				if (mapa.getCampoJogador()[i][j] != 10 && mapa.getCampoReferencia()[i][j] != 9)
					area++;
			}
		}

		if (referencia == mapa.getNMinas()
				|| (mapa.getCampoJogador().length * mapa.getCampoJogador()[0].length - mapa.getNMinas() == area)) {

			if (referencia == mapa.getNMinas())
				System.out.println("referencia");
			if ((mapa.getCampoJogador().length * mapa.getCampoJogador()[0].length - mapa.getNMinas() == area))
				System.out.println("area");

			mapa.setCampoJogador(mapa.getCampoReferencia());
			window.setAcao(false);
			window.pararTimer();
			JOptionPane.showMessageDialog(null,
					"Voce ganhou      Time: " + window.getContador() / 60 + ":" + window.getContador() % 60);
		}
	}

}
