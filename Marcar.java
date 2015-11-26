
public class Marcar implements Jogadas {
	
	public Marcar(int i, int j, Mapa mapa) {
		jogar(i, j, mapa);
	}
	
	@Override
	public void jogar(int i, int j, Mapa mapa) {
		mapa.setValor(i, j, 11);		
	}

}
