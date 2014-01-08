package serpis.psp;

public class PruebaRunnable {

	public static void main(String[] args){
		Thread thread = new Thread(new ContadorEC());
		thread.start();
	}
}

