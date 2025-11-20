package hacienda;

public class Andres extends Thread{
	 
	private Oficina oficina;
	
	public Andres(Oficina oficina) {
		this.oficina=oficina;
	}

	@Override
	public void run() {
		while(true) {
			oficina.atenderCiudadano();
			System.out.println("Andres está atendiendo al ciudadano");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
