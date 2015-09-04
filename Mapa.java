package campoMinadoG;

import java.util.Arrays;
import java.util.Random;

public class Mapa {

	Random ran = new Random();

	private int[][] campo;
	private int[][] campor;
	private int nminas;

	Mapa(int nminas, int x, int y) {
		this.campo = new int[x][y];
		this.campor = new int[x][y];
		this.nminas = nminas;
		minas();
		referencia();

		for (int i = 0; i < campo.length; i++) {
			Arrays.fill(campo[i], 10);
		}
	}

	public void setValor(int i, int j, int valor) {
		campo[i][j] = valor;
	}

	public int getNminas() {
		return nminas;
	}

	public int[][] getCampo() {
		return campo;
	}

	public void setCampo(int[][] campo) {
		this.campo = campo;
	}

	public int[][] getCampor() {
		return campor;
	}

	private void referencia() {
		for (int i = 0; i < campo.length; i++) {
			for (int j = 0; j < campo[i].length; j++) {
				campor[i][j] = verificarMina(criarParte(campo, i, j));
			}
		}

	}

	int verificarMina(int[][] parte) {
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

	static int[][] criarParte(int[][] campo, int i, int j) {

		int[][] parte = new int[3][3];

		for (int k = 0; k < parte.length; k++) {
			for (int k2 = 0; k2 < parte.length; k2++) {
				if (verificaValidade(campo, i + k - 1, j + k2 - 1))
					parte[k][k2] = campo[i + k - 1][j + k2 - 1];
				else
					parte[k][k2] = 0;
			}
		}

		return parte;
	}

	static boolean verificaValidade(int[][] campo, int i, int j) {
		return i >= 0 && i < campo.length && j >= 0 && j < campo[i].length;
	}

	private void minas() {
		for (int i = 0; i < nminas; i++) {
			int ranx = ran.nextInt(campo.length);
			int rany = ran.nextInt(campo[0].length);
			if (campo[ranx][rany] == 1)
				i--;
			else
				campo[ranx][rany] = 1;
		}
	}
}
