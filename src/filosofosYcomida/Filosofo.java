package filosofosYcomida;

import java.util.Random;

public class Filosofo extends Thread{
	private int posicion;
	
	public int getPosicion() {
		return this.posicion;
	}

	private Mesa mesa;
	
	public Filosofo(int posicion, Mesa mesa) {
		this.posicion=posicion;
		this.mesa=mesa;
	}

	private void pensar() {
		System.out.println("Filósofo " + posicion + " está pensando");
		Random rand = new Random();
        try {
            Thread.sleep(500 + rand.nextInt(500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
	}
	
	private void comer() {
		System.out.println("Filósofo " + posicion + " está comiendo");
		Random rand = new Random();
        try {
            Thread.sleep(500 + rand.nextInt(500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
	}
	
	@Override
    public void run() {
        while (true) {
            pensar();
            mesa.cogerTenedores(this);
            comer();
            mesa.soltarTenedores(this);
        }
    }
}
