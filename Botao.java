package campoMinado;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

class Botao extends JButton implements MouseListener {

	int i, j;

	Window win;

	public Botao(Mapa campo, int i, int j, Window win) {
		this.win = win;
		this.i = i;
		this.j = j;
		setSize(50, 50);
		setLocation(i * 50 + 10, j * 50 + 50);
		setBackground(new Color(22, 57, 88));
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (win.isAcao() == true) {
			if (arg0.getButton() == MouseEvent.BUTTON1 && win.getCampo().getCampo()[i][j] != 11) {
				Jogada.jogar(i, j, false);
				win.atualizar();
			} else if (arg0.getButton() == MouseEvent.BUTTON3 && win.getCampo().getCampo()[i][j] == 10
					&& win.getMarcacoes() < win.getCampo().getNminas()) {
				Jogada.jogar(i, j, true);
				win.setMarcacoes(1);
				win.atualizar();
			} else if (arg0.getButton() == MouseEvent.BUTTON3 && win.getCampo().getCampo()[i][j] == 11) {
				win.getCampo().setValor(i, j, 10);
				win.setMarcacoes(-1);
				win.atualizar();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
