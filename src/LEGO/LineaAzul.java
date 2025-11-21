package LEGO;

public class LineaAzul extends Thread{
	private Fabrica fabrica;
	
	public LineaAzul (Fabrica fabrica) {
		this.fabrica=fabrica;
	}

	@Override
	public void run() {
		while(true) {
			fabrica.crearPiezasAzules();
			try {
				Thread.sleep(500);
			}catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

}
