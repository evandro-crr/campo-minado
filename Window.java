package campoMinadoG;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window {

	static JFrame win = new JFrame();
	static Botao[][] bnt;
	static JLabel lblmarc, lblbcont;
	static Timer time;
	static int contador;
	public static boolean acao = true;
	public static Mapa campo;
	public static int marcacoes;

	public static void criar(int nminas, int x, int y) {
		campo = new Mapa(nminas, x, y);
		bnt = new Botao[campo.getCampo().length][campo.getCampo()[0].length];
	}

	public static void mostar() {

		time = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contador++;
				lblbcont.setText("Time: "+contador / 60 + ":" + contador % 60);
			}
		});

		win.setSize((campo.getCampo().length) * 50 + 25, (campo.getCampo()[0].length) * 50 + 90);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.getContentPane().setLayout(null);
		win.setTitle("Campo Minado");

		lblmarc = new JLabel("Minas: 0/" + campo.getNminas());
		lblmarc.setSize(200, 50);
		lblmarc.setLocation((int) (win.getSize().width - lblmarc.getSize().getWidth()), 0);
		lblmarc.setFont(lblmarc.getFont().deriveFont(24.f));
		win.getContentPane().add(lblmarc);

		lblbcont = new JLabel("Timer: 0:00");
		lblbcont.setSize(300, 50);
		lblbcont.setLocation(10, 0);
		lblbcont.setFont(lblmarc.getFont());
		win.getContentPane().add(lblbcont);

		for (int i = 0; i < bnt.length; i++) {
			for (int j = 0; j < bnt[0].length; j++) {
				bnt[i][j] = new Botao(campo, i, j);
				bnt[i][j].setSize(50, 50);
				bnt[i][j].setLocation(i * 50 + 10, j * 50 + 50);
				bnt[i][j].setBackground(new Color(22, 57, 88));
				win.getContentPane().add(bnt[i][j]);
			}
		}

		time.start();
		win.setVisible(true);

	}

	static void atualizar(int[][] campo) {
		for (int i = 0; i < campo.length; i++) {
			for (int j = 0; j < campo[0].length; j++) {
				if (campo[i][j] == 0) {
					bnt[i][j].setText("");
					bnt[i][j].setBackground(new Color(96, 164, 223));
				} else if (campo[i][j] < 9) {
					bnt[i][j].setText("" + campo[i][j]);
					bnt[i][j].setBackground(new Color(96, 164, 223));
				} else if (campo[i][j] == 9) {
					bnt[i][j].setText("M");
					bnt[i][j].setBackground(new Color(255, 0, 0));
				} else if (campo[i][j] == 10) {
					bnt[i][j].setText("");
					bnt[i][j].setBackground(new Color(22, 57, 88));
				} else if (campo[i][j] == 11) {
					bnt[i][j].setText("M");
					bnt[i][j].setBackground(new Color(255, 255, 0));
				}
			}

		}

		lblmarc.setText("Minas:"+marcacoes + "/" + Window.campo.getNminas());

	}
}
