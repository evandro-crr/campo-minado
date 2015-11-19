import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Selecao extends JFrame {

	private int x, y, minas;

	private JLabel lbl[] = new JLabel[3];
	private JTextField txt[] = new JTextField[3];

	public Selecao() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(245, 215);
		setResizable(false);
		setTitle("Seleção de Nivel");

		for (int i = 0; i < lbl.length; i++) {
			lbl[i] = new JLabel();
			txt[i] = new JTextField();
			lbl[i].setSize(70, 25);
			lbl[i].setLocation(i * 75 + 10, 10 + 90);
			txt[i].setSize(70, 25);
			txt[i].setLocation(i * 75 + 10, 25 + 10 + 90);
			getContentPane().add(txt[i]);
			getContentPane().add(lbl[i]);

		}

		lbl[0].setText("Altura");
		lbl[1].setText("Largura");
		lbl[2].setText("Minas");

		JButton confirma = new JButton("Personalizado");
		confirma.setSize(150, 25);
		confirma.setLocation(10, 50 + 15 + 90);
		getContentPane().add(confirma);

		confirma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				y = Integer.parseInt(txt[0].getText());
				x = Integer.parseInt(txt[1].getText());
				minas = Integer.parseInt(txt[2].getText());
				JanelaCampoMinado jogo = new JanelaCampoMinado(minas, x, y);
				dispose();
			}
		});

		JButton iniciante = new JButton("Iniciante");
		iniciante.setSize(150, 25);
		iniciante.setLocation(getSize().width / 2 - 75, 5);
		iniciante.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JanelaCampoMinado jogo = new JanelaCampoMinado(10, 10, 10);
				dispose();
			}
		});
		getContentPane().add(iniciante);

		JButton intermediario = new JButton("Intermediario");
		intermediario.setSize(150, 25);
		intermediario.setLocation(getSize().width / 2 - 75, 35);
		intermediario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JanelaCampoMinado jogo = new JanelaCampoMinado(40, 16, 16);
				dispose();
			}
		});

		getContentPane().add(intermediario);

		JButton experiente = new JButton("Experiente");
		experiente.setSize(150, 25);
		experiente.setLocation(getSize().width / 2 - 75, 40 + 25);
		experiente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JanelaCampoMinado jogo = new JanelaCampoMinado(60, 18, 18);
				dispose();
			}
		});
		getContentPane().add(experiente);

		setVisible(true);

	}
}
