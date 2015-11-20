package campoMinado;
import java.util.Arrays;
import java.util.Random;

public class Mapa {

	private Random random = new Random();

	private int[][] campoJogador;
	private int[][] campoReferencia;
	private int nMinas;

	Mapa(int nMinas, int x, int y) {
		this.campoJogador = new int[x][y];
		this.campoReferencia = new int[x][y];
		this.nMinas = nMinas;
		colocaMinas();
		criaCampoReferencia();

		for (int i = 0; i < campoJogador.length; i++) {
			Arrays.fill(campoJogador[i], 10);
		}
	}

	public void setValor(int i, int j, int valor) {
		campoJogador[i][j] = valor;
	}

	public int getNMinas() {
		return nMinas;
	}

	public int[][] getCampoJogador() {
		return campoJogador;
	}

	public void setCampoJogador(int[][] campo) {
		this.campoJogador = campo;
	}

	public int[][] getCampoReferencia() {
		return campoReferencia;
	}

	// Cria o campo de referencia a partir do campo do jogador 
	// depois de ser colocado as minas 
	private void criaCampoReferencia() {
		for (int i = 0; i < campoJogador.length; i++) {
			for (int j = 0; j < campoJogador[i].length; j++) {
				campoReferencia[i][j] = verificaMina(criaParte(campoJogador, i, j));
			}
		}

	}

	// Verifica a quantidade de minas na região 
	// retorna o numero de minas, ou 9 se a posição verificada for uma mina
	private int verificaMina(int[][] parte) {
		int referencia = 0;

		if (parte[1][1] == 1)
			return 9;
		else {
			for (int i = 0; i < parte.length; i++) {
				for (int j = 0; j < parte.length; j++) {
					if (parte[i][j] == 1) {
						referencia++;
					}
				}
			}
		}
		return referencia;
	}

	// a partir de uma posição em um campo cria
	// uma matriz 3x3 com os valores em torno da posição dada
	static int[][] criaParte(int[][] campo, int i, int j) {

		int[][] parte = new int[3][3];

		for (int k = 0; k < parte.length; k++) {
			for (int k2 = 0; k2 < parte.length; k2++) {
				if (isValido(campo, i + k - 1, j + k2 - 1))
					parte[k][k2] = campo[i + k - 1][j + k2 - 1];
				else
					parte[k][k2] = 0;
			}
		}
		return parte;
	}

	// Verifica se o ponto dado não é nulo na matriz 
	static boolean isValido(int[][] campo, int i, int j) {
		return i >= 0 && i < campo.length && j >= 0 && j < campo[i].length;
	}
	
	// Distribue as minas aleatoriamente no campo do jogador
	private void colocaMinas() {
		//TODO Criar Enum para minas (1 = mina)
		for (int i = 0; i < nMinas; i++) {
			int ranx = random.nextInt(campoJogador.length);
			int rany = random.nextInt(campoJogador[0].length);
			if (campoJogador[ranx][rany] == 1)
				i--;
			else
				campoJogador[ranx][rany] = 1;
		}
	}
}