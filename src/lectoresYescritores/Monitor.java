package lectoresYescritores;

public class Monitor {

	//en esta clase estarán los atributos compartidos y los métodos 
	private int entero1;
	private int entero2;
	
	public Monitor(int entero1,int entero2) {
		this.entero1=entero1;
		this.entero2=entero2;
	}
	//método de lectura
	public synchronized int leer(int cual) {
		if(cual ==1) {
			return this.entero1;
		}else {
			return this.entero2;
		}
		
	}
	
	//método de escritura
	public synchronized void escribir(int cual,int entero) {
		if(cual ==1) {
			this.entero1=entero;
		}else {
			this.entero2=entero;
		}
	}
	
	public static void main(String args[]) {
		Monitor monitor = new Monitor(7,9);
		Lector l1 = new Lector(monitor);
		Escritor e = new Escritor(monitor);
		Lector l2 = new Lector(monitor);
		l1.setPriority(Thread.MAX_PRIORITY);
		e.setPriority(Thread.MIN_PRIORITY);
		l1.start();
		e.start();
		l2.start();
		
	}

}
