package campoMinado;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Window extends JFrame {

	private Botao[][] bnt;
	private JLabel lblmarc, lblbcont;
	private JMenuBar menuBar;
	private JMenu mJogo;
	private JMenuItem mInovoJogo;
	private Timer time;
	private int contador;
	private boolean acao = true;
	private Mapa campo;
	private int marcacoes;

	public Window(int nminas, int x, int y) {
		this.campo = new Mapa(nminas, x, y);
		bnt = new Botao[campo.getCampo().length][campo.getCampo()[0].length];

		setSize((campo.getCampo().length) * 50 + 25, (campo.getCampo()[0].length) * 50 + 90);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Campo Minado");
		setResizable(false);

		lblmarc = new JLabel("Minas: 0/" + campo.getNminas());
		lblmarc.setSize(200, 50);
		lblmarc.setLocation((int) (this.getSize().width - lblmarc.getSize().getWidth()), 0);
		lblmarc.setFont(lblmarc.getFont().deriveFont(24.f));
		getContentPane().add(lblmarc);

		lblbcont = new JLabel("Timer: 0:00");
		lblbcont.setSize(300, 50);
		lblbcont.setLocation(10, 0);
		lblbcont.setFont(lblmarc.getFont());
		getContentPane().add(lblbcont);

		for (int i = 0; i < bnt.length; i++) {
			for (int j = 0; j < bnt[0].length; j++) {
				bnt[i][j] = new Botao(campo, i, j, this);
				getContentPane().add(bnt[i][j]);
			}
		}

		Jogada.setWin(this);

		mJogo = new JMenu("Jogo");
		menuBar = new JMenuBar();
		mInovoJogo = new JMenuItem("Novo Jogo");

		mInovoJogo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Selecao novo = new Selecao();
				dispose();
			}
		});

		mJogo.add(mInovoJogo);
		menuBar.add(mJogo);
		setJMenuBar(menuBar);

		time = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contador++;
				lblbcont.setText("Time: " + contador / 60 + ":" + contador % 60);
			}
		});

		time.start();

		setVisible(true);
	}

	void atualizar() {
		for (int i = 0; i < campo.getCampo().length; i++) {
			for (int j = 0; j < campo.getCampo()[0].length; j++) {
				if (campo.getCampo()[i][j] == 0) {
					bnt[i][j].setText("");
					bnt[i][j].setBackground(new Color(96, 164, 223));
				} else if (campo.getCampo()[i][j] < 9) {
					bnt[i][j].setText("" + campo.getCampo()[i][j]);
					bnt[i][j].setBackground(new Color(96, 164, 223));
				} else if (campo.getCampo()[i][j] == 9) {
					bnt[i][j].setText("M");
					bnt[i][j].setBackground(new Color(255, 0, 0));
				} else if (campo.getCampo()[i][j] == 10) {
					bnt[i][j].setText("");
					bnt[i][j].setBackground(new Color(22, 57, 88));
				} else if (campo.getCampo()[i][j] == 11) {
					bnt[i][j].setText("M");
					bnt[i][j].setBackground(new Color(255, 255, 0));
				}
			}

		}

		lblmarc.setText("Minas:" + marcacoes + "/" + campo.getNminas());
	}

	public int getContador() {
		return contador;
	}

	public boolean isAcao() {
		return acao;
	}

	public void setAcao(boolean acao) {
		this.acao = acao;
	}

	public Mapa getCampo() {
		return campo;
	}

	public void setCampo(Mapa campo) {
		this.campo = campo;
	}

	public int getMarcacoes() {
		return marcacoes;
	}

	public void setMarcacoes(int marcacoes) {
		this.marcacoes += marcacoes;
	}

	public void pararTimer() {
		time.stop();
	}

}
