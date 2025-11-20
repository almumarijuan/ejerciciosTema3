package productoresYconsumidores;

public class Consumidor extends Thread{
	private Buffer buffer;
	
	public Consumidor(Buffer buffer) {
		this.buffer=buffer;
	}

	@Override
	public void run() {
		while(true) {
			buffer.sacar();
		}
		
	}
	
	
}
