package lectoresYescritores;

import java.util.Random;

public class Escritor extends Thread{
	
	private Monitor monitor;

	public Escritor(Monitor monitor) {
		this.monitor=monitor;
	}
	@Override
	public void run() {
		Random rand = new Random();
		int num = rand.nextInt(1, 3);
		if(num==1) {
			monitor.escribir(1,rand.nextInt(10000));
		}else {
			monitor.escribir(2,rand.nextInt(10000));
		}
		
	}
	//esta clase define run() para modificar el atributo de monitor
	
}
