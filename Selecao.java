package campoMinado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Selecao {

	private int x, y, minas;

	private JLabel lbl[] = new JLabel[3];
	private JTextField txt[] = new JTextField[3];

	private JFrame win = new JFrame("Seleção de nivel");

	public Selecao() {
		win.getContentPane().setLayout(null);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(245, 130);
		win.setResizable(false);

		for (int i = 0; i < lbl.length; i++) {
			lbl[i] = new JLabel();
			txt[i] = new JTextField();
			lbl[i].setSize(70, 25);
			lbl[i].setLocation(i * 75 + 10, 10);
			txt[i].setSize(70, 25);
			txt[i].setLocation(i * 75 + 10, 25 + 10);
			win.getContentPane().add(txt[i]);
			win.getContentPane().add(lbl[i]);

		}

		lbl[0].setText("Altura");
		lbl[1].setText("Largura");
		lbl[2].setText("Minas");

		JButton confirma = new JButton("Comfirmar");
		confirma.setSize(110, 25);
		confirma.setLocation(10, 50 + 15);
		win.getContentPane().add(confirma);

		win.setVisible(true);
		confirma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				y = Integer.parseInt(txt[0].getText());
				x = Integer.parseInt(txt[1].getText());
				minas = Integer.parseInt(txt[2].getText());
				Window jogo = new Window(minas, x, y);
				win.dispose();
			}
		});

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getMinas() {
		return minas;
	}

}
