package hacienda;

import java.util.LinkedList;
import java.util.Queue;


public class Oficina {
	
	private boolean ventana1=false; //true es q está siendo atandida
	private boolean ventana2=false;
	private Queue<Ciudadano> cola1;
	private Queue<Ciudadano> cola2;
	

	public Oficina() {
		this.cola1= new LinkedList<>();
		this.cola2=new LinkedList<>();
	}

	
	public synchronized void esperarVentanilla1(Ciudadano ciudadano) {//lo invoca un ciudadano que quiere entrar a esta ventanilla
		try {
			//si no hay nadie en las colas hay que despertar a Andres
			if(cola1.isEmpty() && cola2.isEmpty()) {
				notifyAll();
			}
			cola1.add(ciudadano);
			while(!ventana1 || cola1.peek()!=ciudadano) {//esperas a q se libere la ventanilla1 y seas el primero en la cola
				System.out.println("Ciudadano está esperando en ventanilla1");
				wait();
			}
			cola1.remove();
			System.out.println("Ciudadano se ha ido de ventanilla1");
			ventana1=false;
			notifyAll();
			
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	
	}
	public synchronized void esperarVentanilla2(Ciudadano ciudadano) {
		try {
			//si no hay nadie en las colas hay que despertar a Andres
			if(cola1.isEmpty() && cola2.isEmpty()) {
				notifyAll();
			}
			cola2.add(ciudadano);
			while(!ventana2 || cola2.peek()!=ciudadano) {
				System.out.println("Ciudadano está esperando en ventanilla2");
				wait();
			}
			cola2.remove();
			System.out.println("Ciudadano se ha ido de ventanilla1");
			ventana2=false;
			notifyAll();
		}catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	public synchronized void atenderCiudadano() {//esto lo hace andres
		while(cola1.isEmpty()&& cola2.isEmpty()) {//si no hay nadie, se duerme
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		//si hay más cola en la ventana 1 atiende ahi, sino a la 2
		if(cola1.size()>=cola2.size()) {//atiende en ventana 1
			ventana1=true;
			ventana2=false;
		}else {//ateinde en ventana 2
			ventana2=true;
			ventana1=false;
		}
		notifyAll();
	}
	
	public static void main(String[] args) {
		Oficina oficina = new Oficina();
		Andres andres = new Andres(oficina);
		andres.start();
		for(int i=0; i<5;i++) {
			int ventanilla = (i%2) +1;
			new Ciudadano(ventanilla,oficina).start();
		}

	}

}
