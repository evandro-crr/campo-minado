import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;

/*
 * codigos: 0-8 quantidade de minas em volta
 * 			9 é uma mina
 * 			10 posição não explorada
 * 			11 posição marcada como mina
 */

class Botao extends JButton implements MouseListener {
	private static final long serialVersionUID = 1L;
	int i, j;
	// EstadoBotao estado =
	final int largura = 50;
	JanelaCampoMinado window;

	public Botao(Mapa campo, int i, int j, JanelaCampoMinado window) {
		this.window = window;
		this.i = i;
		this.j = j;
		setSize(largura, largura);
		setLocation(i * largura + 10, j * largura + largura);
		setBackground(new Color(22, 57, 88));
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (window.isAcao() == true) {
			// Ativa ponto no campo do jogador
			if (e.getButton() == MouseEvent.BUTTON1 && window.getCampo().getCampoJogador()[i][j] != 11) {
				new Abrir(i, j, window);
				window.atualiza();
			}
			// Marca posival mina
			else if (e.getButton() == MouseEvent.BUTTON3 && window.getCampo().getCampoJogador()[i][j] == 10
					&& window.getMarcacoes() < window.getCampo().getNMinas()) {
				new Marcar(i, j, window.getCampo());
				window.setMarcacoes(1);
				window.atualiza();
			}
			// Desmarca povivel mina
			else if (e.getButton() == MouseEvent.BUTTON3 && window.getCampo().getCampoJogador()[i][j] == 11) {

				window.getCampo().setValor(i, j, 10);
				window.setMarcacoes(-1);
				window.atualiza();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
