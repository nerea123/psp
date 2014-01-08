
public class Prueba {

	private static int numHilos=4;
	protected int numCuenta=10;
	private int h=0;
	
	Prueba(){
		Hilo hilo;
		for(int i=0;i<numHilos;i++){
			hilo=new Hilo(i);
			hilo.start();
			}
	}

	class Hilo extends Thread{
		int numHilo=0;
		
		Hilo(int n){
			numHilo=n;
		}
		
		
		
		public void run(){
			
			
			for(int i=0;i<numCuenta;i++){
				System.out.println("Hilo "+numHilo +" cuenta "+i);
				
			}
			
		}
	}
	
	
	public static void main(String[] args) {

		Prueba p=new Prueba();
	}

	
}
