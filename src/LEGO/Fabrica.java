package LEGO;

public class Fabrica {//esta es el monitor q tendrá las 2 lineas (hebras) y los gestores de pedidos (hebras)
	private int piezasRojas;
	private int piezasAzules;
	
	public Fabrica() {
		this.piezasAzules=0;
		this.piezasRojas=0;
	}
	
	public synchronized void crearPiezasRojas() {// si hay sitio en la cesta de piezas rojas, produce una pieza roja y la deposita enla cesta de piezas rojas
		while(piezasRojas == 50) {
			try {
				System.out.println("Hay muchas piezas rojas");
				wait(); //hasta que alguien saque piezas
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
		}
		piezasRojas++;
		System.out.println("Se ha creado 1 pieza roja más, ahora hay: "+ piezasRojas);
		notifyAll();
	}
	
	public synchronized void crearPiezasAzules() {
		while(piezasAzules == 50) {
			try {
				System.out.println("Hay muchas piezas azules");
				wait(); //hasta que alguien saque piezas
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
		}
		piezasAzules++;
		System.out.println("Se ha creado 1 pieza azul más, ahora hay: "+ piezasAzules);
		notifyAll();
		
	}
	
	public synchronized void sacarPiezas(int numRojas,int numAzules) {
		try {
			while(piezasRojas ==0 || numRojas > piezasRojas || piezasAzules ==0 || numAzules >piezasAzules) {
				System.out.println("No hay piezas suficientes para sacar");
				wait(); //hasta crear piezas suficientes
			}
			piezasRojas -=numRojas;
			piezasAzules -=numAzules;
			System.out.println("Se han sacado: "+ numRojas + " rojas, "+numAzules+" azules.");
			notifyAll();
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}
		
	}
	
	public static void main(String[] args) {
		
		Fabrica fabrica = new Fabrica();
		LineaRoja lineaRoja = new LineaRoja(fabrica);
		LineaAzul lineaAzul = new LineaAzul(fabrica);
		Gestor g1= new Gestor(fabrica,12,15);
		Gestor g2 = new Gestor(fabrica,23,31);
		
		lineaRoja.start();
		lineaAzul.start();
		g1.start();
		g2.start();
	}

}
