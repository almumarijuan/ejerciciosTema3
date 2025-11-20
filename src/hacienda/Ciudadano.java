package hacienda;

public class Ciudadano extends Thread {
	
	private int ventanilla;
	private Oficina oficina;
	
	public Ciudadano(int ventanilla, Oficina oficina) {
		this.ventanilla=ventanilla;
		this.oficina=oficina;
	}

	@Override
	public void run() {
		if(ventanilla==1) {
			oficina.esperarVentanilla1(this);
		}else {
			oficina.esperarVentanilla2(this);
		}
		
	}
	
	

}
