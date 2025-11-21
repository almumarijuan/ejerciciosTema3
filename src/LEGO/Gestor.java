package LEGO;

public class Gestor extends Thread{
	private Fabrica fabrica;
	private int numRojas;
	private int numAzules;
	
	public Gestor(Fabrica fabrica,int numRojas, int numAzules) {
		this.fabrica=fabrica;
		this.numRojas=numRojas;
		this.numAzules=numAzules;
	}
	
	@Override
	public void run() {
		fabrica.sacarPiezas(numRojas, numAzules);
		
	}

}
