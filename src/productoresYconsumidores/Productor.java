package productoresYconsumidores;

import java.util.Random;

public class Productor extends Thread {
	private Buffer buffer;
	
	public Productor(Buffer buffer) {
		this.buffer=buffer;
	}

	@Override
	public void run() {
		while(true) {
			Random rand = new Random();
			this.buffer.meter(rand.nextInt(10001));
		}
	}
	
	

}
