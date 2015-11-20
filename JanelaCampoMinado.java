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

public class JanelaCampoMinado extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Celula[][] btn;
	private JLabel lblMarcacoes, lblTimer;
	private JMenuBar menuBar;
	private JMenu jogo;
	private JMenuItem novoJogo;
	
	private Timer timer;
	private Mapa campo;
	private int contador; // Contador para o timer
	private boolean jogoAcontecendo = true; // bloqueia os botoes quando o jogo termina
	private int nMarcacoes; // Controla quantos pontos podem ser marcado como posivel mina

	public JanelaCampoMinado(int nMinas, int x, int y) {
		this.campo = new Mapa(nMinas, x, y);
		btn = new Celula[campo.getCampoJogador().length][campo.getCampoJogador()[0].length];

		setSize((campo.getCampoJogador().length) * 50 + 25, (campo.getCampoJogador()[0].length) * 50 + 110);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Campo Minado");
		setResizable(false);

		lblMarcacoes = new JLabel("Minas: 0/" + campo.getNMinas());
		lblMarcacoes.setSize(170, 50);
		lblMarcacoes.setLocation((int) (this.getSize().width - lblMarcacoes.getSize().getWidth()), 0);
		lblMarcacoes.setFont(lblMarcacoes.getFont().deriveFont(24.f));
		getContentPane().add(lblMarcacoes);

		lblTimer = new JLabel("Tempo: 0:00");
		lblTimer.setSize(300, 50);
		lblTimer.setLocation(10, 0);
		lblTimer.setFont(lblMarcacoes.getFont());
		getContentPane().add(lblTimer);

		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn[0].length; j++) {
				btn[i][j] = new Celula(i, j, this);
				getContentPane().add(btn[i][j]);
			}
		}

		Jogada.setWin(this);

		jogo = new JMenu("Jogo");
		menuBar = new JMenuBar();
		novoJogo = new JMenuItem("Novo Jogo");
		novoJogo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Selecao();
				dispose();
			}
		});
		jogo.add(novoJogo);
		
		menuBar.add(jogo);
		setJMenuBar(menuBar);

		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contador++;
				lblTimer.setText("Tempo: " + contador / 60 + ":" + contador % 60);
			}
		});
		timer.start();

		setVisible(true);
	}

	// Atualiza os botoes comforme o campo do jogador
	public void atualiza() {
		for (int i = 0; i < campo.getCampoJogador().length; i++) {
			for (int j = 0; j < campo.getCampoJogador()[0].length; j++) {
				if (campo.getCampoJogador()[i][j] == 0) {
					btn[i][j].setText("");
					btn[i][j].setBackground(new Color(96, 164, 223));
				} else if (campo.getCampoJogador()[i][j] < 9) {
					btn[i][j].setText("" + campo.getCampoJogador()[i][j]);
					btn[i][j].setBackground(new Color(96, 164, 223));
				} else if (campo.getCampoJogador()[i][j] == 9) {
					btn[i][j].setText("M");
					btn[i][j].setBackground(new Color(255, 0, 0));
				} else if (campo.getCampoJogador()[i][j] == 10) {
					btn[i][j].setText("");
					btn[i][j].setBackground(new Color(22, 57, 88));
				} else if (campo.getCampoJogador()[i][j] == 11) {
					btn[i][j].setText("M");
					btn[i][j].setBackground(new Color(255, 255, 0));
				}
			}

		}

		lblMarcacoes.setText("Minas:" + nMarcacoes + "/" + campo.getNMinas());
	}

	public int getContador() {
		return contador;
	}

	public boolean isJogoAcontecendo() {
		return jogoAcontecendo;
	}

	public void setAcao(boolean acao) {
		this.jogoAcontecendo = acao;
	}

	public Mapa getCampo() {
		return campo;
	}

	public void setCampo(Mapa campo) {
		this.campo = campo;
	}

	public int getMarcacoes() {
		return nMarcacoes;
	}

	public void setMarcacoes(int marcacoes) {
		this.nMarcacoes += marcacoes;
	}

	public void pararTimer() {
		timer.stop();
	}

}
