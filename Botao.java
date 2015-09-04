package campoMinadoG;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Botao extends JButton implements MouseListener {

	int i, j;

	public Botao(Mapa campo, int i, int j) {
		this.i = i;
		this.j = j;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (Window.acao == true) {
			if (arg0.getButton() == MouseEvent.BUTTON1 && Window.campo.getCampo()[i][j] != 11) {
				Jogada.jogar(Window.campo, i, j, false);
				Window.atualizar(Window.campo.getCampo());
			} else if (arg0.getButton() == MouseEvent.BUTTON3 && Window.campo.getCampo()[i][j] == 10
					&& Window.marcacoes < Window.campo.getNminas()) {
				Jogada.jogar(Window.campo, i, j, true);
				Window.marcacoes++;
				Window.atualizar(Window.campo.getCampo());
			} else if (arg0.getButton() == MouseEvent.BUTTON3 && Window.campo.getCampo()[i][j] == 11) {
				Window.campo.setValor(i, j, 10);
				Window.marcacoes--;
				Window.atualizar(Window.campo.getCampo());
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
