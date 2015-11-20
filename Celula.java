package campoMinado;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
/*
 * codigos: 0-8 quantidade de minas em volta
 * 			9 é uma mina
 * 			10 posição não explorada
 * 			11 posição marcada como mina
 */

class Celula extends JButton implements MouseListener {
	private static final long serialVersionUID = 1L;
	int i, j;
	//int minasAoRedor;
	//EstadoCelula estado = EstadoCelula.INEXPLORADO;
	final int largura = 50;
	JanelaCampoMinado janelaJogo;

	public Celula(int i, int j, JanelaCampoMinado janelaJogo) {
		this.janelaJogo = janelaJogo;
		this.i = i;
		this.j = j;
		setSize(largura, largura);
		setLocation(i * largura + 10, j * largura + largura);
		setBackground(new Color(22, 57, 88));
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (janelaJogo.isJogoAcontecendo() == true) {
			// Ativa ponto no campo do jogador
			// Se clicou com o botão esquerdo E não é uma posição marcada como mina
			if (e.getButton() == MouseEvent.BUTTON1 && janelaJogo.getCampo().getCampoJogador()[i][j] != 11) {
				Jogada.jogar(i, j, false);
				janelaJogo.atualiza();
			}
			// Marca possivel mina
			// Se clicou com o botão direito E é uma célula inexplorada E o número de marcações não foi excedido
			else if (e.getButton() == MouseEvent.BUTTON3 && janelaJogo.getCampo().getCampoJogador()[i][j] == 10
					&& janelaJogo.getMarcacoes() < janelaJogo.getCampo().getNMinas()) {
				Jogada.jogar(i, j, true);
				janelaJogo.setMarcacoes(1);
				janelaJogo.atualiza();
			}
			// Desmarca possivel mina
			// Se clicou com o botão direito E é uma célula marcada
			else if (e.getButton() == MouseEvent.BUTTON3 && janelaJogo.getCampo().getCampoJogador()[i][j] == 11) {
				janelaJogo.getCampo().setValor(i, j, 10);
				janelaJogo.setMarcacoes(-1);
				janelaJogo.atualiza();
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
