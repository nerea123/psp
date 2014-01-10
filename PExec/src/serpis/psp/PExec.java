package serpis.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PExec {

	static String respuesta;
	static String comando;
	static Process p;
	static boolean correcto;
	
	public static void main(String[] args) {
		
		do{
		
			Scanner scaner=new Scanner(System.in);
			System.out.println("Introduce el programa a ejecutar: ");
			comando=scaner.nextLine();
			System.out.println("¿Desea esperar a que termine o se cierre? S/N ");
			respuesta=scaner.nextLine();
			ejecutar(comando, respuesta);
			System.out.println(correcto);
		}while(!correcto);

	}
	
	public static void mostrar() throws IOException{
		// Se obtiene el stream de salida del programa
        InputStream is = p.getInputStream();
        
        /* Se prepara un bufferedReader para poder leer la salida más comodamente. */
        BufferedReader br = new BufferedReader (new InputStreamReader (is));
        
        // Se lee la primera linea
        String aux = br.readLine();
        
        // Mientras se haya leido alguna linea
        while (aux!=null)
        {
            // Se escribe la linea en pantalla
            System.out.println (aux);
            
            // y se lee la siguiente.
            aux = br.readLine();
        } 
	}
	
	public static void ejecutar(String comando, String respuesta){
		
		String comandosConSalidaPorPantalla[];
		
		 if( System.getProperty("os.name").indexOf("Windows")!=-1)
		 	comandosConSalidaPorPantalla=new String[]{"netstat","dir","ipconfig"};
		 else
			 comandosConSalidaPorPantalla=new String[]{"ls","ifconfig","netstat"};
		try
		{
			if(respuesta.equalsIgnoreCase("N") || respuesta.equalsIgnoreCase("NO")){
				p = Runtime.getRuntime().exec (comando);
				correcto=true;	
				
			}
			else{
				Process p=new ProcessBuilder(comando ).start();
				p.waitFor();
				correcto=true;	
				
			}
			
				    
			
			for(String i : comandosConSalidaPorPantalla){
				if(comando.indexOf(i)!=-1){
					mostrar();
					break;
				}
			}
			
			
			
		}
		catch (Exception e)
		{
		  correcto=false;
		  System.out.println("error");
		}
	}

}
