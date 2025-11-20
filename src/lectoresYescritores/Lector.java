package lectoresYescritores;

import java.util.Random;

public class Lector extends Thread{
	
	private Monitor monitor;

	public Lector(Monitor monitor) {
		this.monitor=monitor;
	}
	@Override
	public void run() {
		Random rand = new Random();
		int num = rand.nextInt(1, 3);
		if(num==1) {
			System.out.println(monitor.leer(num));
			System.out.println(monitor.leer(2));
		}else {
			System.out.println(monitor.leer(2));
			System.out.println(monitor.leer(num));
		}
		
	}
	

}
