package LEGO;

public class LineaRoja extends Thread{
	private Fabrica fabrica;
	
	public LineaRoja (Fabrica fabrica) {
		this.fabrica=fabrica;
	}

	@Override
	public void run() {
		while(true) {
			fabrica.crearPiezasRojas();
			try {
				Thread.sleep(500);
			}catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	

}
