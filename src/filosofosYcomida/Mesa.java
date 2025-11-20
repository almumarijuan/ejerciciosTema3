package filosofosYcomida;

public class Mesa {
	private final int numfilosofos = 5;
    private boolean[] tenedorLibre = new boolean[numfilosofos];

    public Mesa() {
        for (int i = 0; i < numfilosofos; i++) {
            tenedorLibre[i] = true; // todos libres al principio
        }
    }
	
    public synchronized void cogerTenedores(Filosofo filo) {
    	int posIzq= filo.getPosicion();
    	int posDcha= (posIzq +1) %numfilosofos;
    	
    	try {
    		while(!tenedorLibre[posIzq] || !tenedorLibre[posDcha]) {
    			System.out.println(filo.getPosicion() + " está esperando a coger el tenedor");
    			wait();
    		}
    		tenedorLibre[posIzq]= false;
    		tenedorLibre[posDcha]= false;//está ocupando los tenedores
    		System.out.println("El filosofo en: "+ posIzq + " ha cogido los 2 tenedores");
    	}catch (InterruptedException e) {
    		Thread.currentThread().interrupt();
    	}
    	
    }
    public synchronized void soltarTenedores(Filosofo filo) {
    	int posIzq= filo.getPosicion();
    	int posDcha= (posIzq +1) %numfilosofos;
    	
    	tenedorLibre[posIzq]=true;
    	tenedorLibre[posDcha]=true;
    	System.out.println("El filosofo en: "+ posIzq + " ha soltado los 2 tenedores");
    	notifyAll();
    }
    
    public static void main(String[] args) {
    	Mesa mesa = new Mesa();
    	Filosofo f0= new Filosofo(0,mesa);
    	Filosofo f1= new Filosofo(1,mesa);
    	Filosofo f2= new Filosofo(2,mesa);
    	Filosofo f3= new Filosofo(3,mesa);
    	Filosofo f4= new Filosofo(4,mesa);
    	
    	f0.start();
    	f1.start();
    	f2.start();
    	f3.start();
    	f4.start();
    }

}
