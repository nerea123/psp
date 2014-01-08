
public class Prueba2 implements Runnable {

	private static int numHilos=4;
	protected int numCuenta=10;
	private int h=0;
	int numHilo;
	
	Prueba2(int n){
		numHilo=n;
	}
	
	public static void main(String[] args) {

		for(int i=0;i<numHilos;i++){
		Prueba2 p=new Prueba2(i);
		Thread th=new Thread(p);
		th.start();
		}
	}

	@Override
	public void run() {
		
		for(int i=0;i<numCuenta;i++){
			System.out.println("Hilo "+numHilo +" cuenta "+i);
			
		}
	}
}
