package serpis.psp;

import java.util.Scanner;

public class PExec {

	static String respuesta;
	static String comando;
	
	public static void main(String[] args) {
		Scanner scaner=new Scanner(System.in);
		System.out.println("Introduce el programa a ejecutar: ");
		comando=scaner.nextLine();
		System.out.println("¿Desea esperar a que termine o se cierre? S/N ");
		respuesta=scaner.nextLine();
		ejecutar(comando, respuesta);
		System.out.println("Fin");

	}
	
	public static void ejecutar(String comando, String respuesta){
		try
		{
			if(respuesta.equalsIgnoreCase("N") || respuesta.equalsIgnoreCase("NO")){
				Process p = Runtime.getRuntime().exec (comando);
			}
			else{
				Process p=new ProcessBuilder(comando ).start();
				p.waitFor();
			}
		}
		catch (Exception e)
		{
		   /* Se lanza una excepción si no se encuentra en ejecutable o el fichero no es ejecutable. */
		}
	}

}
