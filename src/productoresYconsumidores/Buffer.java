package productoresYconsumidores;

public class Buffer {
	
	private int[] buffer;
	private int in;
	private int out;
	private int count;
	
	public Buffer() {
		this.buffer = new int[10];
		for(int i=0; i<10;i++) {
			buffer[i]=-1;//-1 indica vacio
		}
		this.in=0;
		this.out=0;
		this.count=0;
	}
	
	public synchronized void meter(int entero) {
		try {
			while(count==10) {//esta lleno
				System.out.println("Está esprando a que se vacie");
				wait();
			}
			buffer[in]=entero;
			System.out.println("Ha metido: "+ entero+" en pos "+ in);
			if(in==9) {
				in=0;
			}else {
				in++;
			}
			count++;
			notifyAll();
		}catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
		
	}
	public synchronized void sacar() {
		try {
			while(count==0) {
				System.out.println("Esta esperando a que se llene");
				wait();
			}
			
	        System.out.println("Ha sacado: " + buffer[out] + " de pos " + out);
			buffer[out]=-1;
			if(out==9) {
				out=0;
			}else {
				out++;
			}
			count--;
			notifyAll();
		}catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
	}

	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		Productor p1= new Productor(buffer);
		Productor p2= new Productor(buffer);
		Productor p3= new Productor(buffer);
		Consumidor c1 = new Consumidor(buffer);
		Consumidor c2 = new Consumidor(buffer);
		Consumidor c3 = new Consumidor(buffer);
		
		p1.start();
		p2.start();
		p3.start();
		c1.start();
		c2.start();
		c3.start();

	}

}
