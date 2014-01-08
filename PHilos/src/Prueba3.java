
public class Prueba3 extends Thread {

	private static int numHilos=4;
	protected int numCuenta=10;
	protected static int h=0;
	
	
	public static void main(String[] args) {
		
		for(int i=0;i<numHilos;i++){
			Prueba3 p=new Prueba3();
			//System.out.print(i);
			new Thread(p).start();
			
		}
		
	}
	
	public void run(){
		for(int i=0;i<numCuenta;i++){
			System.out.println("Hilo "+h +" cuenta "+i);
		}
		h++;
	}
}
