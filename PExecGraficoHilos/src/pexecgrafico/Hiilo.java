/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pexecgrafico;

/**
 *
 * @author Nerea
 */
public class Hiilo implements Runnable {

    private String comando;
  
    
    public Hiilo(String comando){
    
        this.comando=comando;
        System.out.println(comando+"hola");
        
    }
    @Override
    public void run() {
        ejecutar(comando);
        System.out.println("Se cerro comando "+comando);
    }
    
    public static void ejecutar(String comando){
		
		try
		{
                    Process p=new ProcessBuilder(comando ).start();
                    p.waitFor();
	
		}
		catch (Exception e)
		{
		  
		  System.out.println("No se encuentra el programa o comando");
		}
	}
}
