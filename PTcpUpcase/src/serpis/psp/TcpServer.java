package serpis.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpServer {

	static String host = "";static int port=80;
	
	public static String encuentraHost(String mensaje,String host){
		String cad1=mensaje.substring(4,mensaje.length()-1);
		int indiceFinHost=cad1.indexOf(" ");
		return host=cad1.substring(0,indiceFinHost);
	
	}
	
	public static String encuentraHostHTTP1(String mensaje,String host){
		int indiceHost=mensaje.indexOf("Host:");
		String cad1=mensaje.substring(indiceHost+5,mensaje.length()-4);
		return cad1;
	
	}
	
	public static void main(String[] args) {
		
		ServerSocket s; //Socket servidor
		Socket sc; //Socket cliente
		
		Socket socket;//socket para peticion pedida por cliente
		PrintStream print;//Canal de escritura para peticion pedida por cliente
		PrintStream p; //Canal de escritura
		Scanner b; //Canal de Lectura
		Scanner scanner;//Canal de Lectura para peticion pedida por cliente
		String mensaje;
		String mssg[]=new String[2];
		boolean http1=false;

		try {
		//Creo el socket server
		s = new ServerSocket(8888);

		//Invoco el metodo accept del socket servidor, me devuelve una referencia al socket cliente
		sc = s.accept();

		//Obtengo una referencia a los canales de escritura y lectura del socket cliente
		b = new Scanner( new InputStreamReader ( sc.getInputStream() ) );
		p = new PrintStream ( sc.getOutputStream() );

		while ( true ) {
		//Leo lo que escribio el socket cliente en el canal de lectura
			
		mensaje = b.nextLine();
		System.out.println("Mensaje del cliente: "+mensaje);

		//Escribo en canal de escritura el mismo mensaje recibido
		if(mensaje.indexOf("GET")==0){
			
			if(mensaje.substring(4,5).equals("/") && mensaje.indexOf("Host")==-1){
				
				host="localhost";
				mensaje=mensaje.substring(0,mensaje.length()-8);
				System.out.println("Estableciendo conexion via HTTP/1.0");
				
			}else if(mensaje.indexOf("Host")==-1){
				
				host=encuentraHost(mensaje,host);
				mensaje.substring(0,mensaje.length()-8);
				System.out.println("Estableciendo conexion via HTTP/1.0");
			}else if(mensaje.indexOf("Host")!=-1){
				http1=true;
				host=encuentraHostHTTP1(mensaje, host);
				mssg=mensaje.split("\r\n");
				System.out.println(mssg[0]);
				System.out.println("Estableciendo conexion via HTTP/1.1");
			}
			socket=new Socket(host,port);
			scanner=new Scanner( new InputStreamReader ( socket.getInputStream() ) );
			print=new PrintStream ( socket.getOutputStream() );
			if(http1){
				http1=false;
				print.println(mssg[0]);
				System.out.println(mssg[0]);
				print.println("\r\n");
				//System.out.println(mssg[0]);
				//print.println(mssg[1]);
				print.println("\r\n");
			}else{
				
				print.println(mensaje);
				print.println("\r\n");
				print.println("\r\n");
			}
			System.out.println("Enviando peticion a "+host+"...");
			int fin=mensaje.indexOf("</html>");
			while(scanner.hasNextLine()){
				String linea=scanner.nextLine();
				if(linea.indexOf("</html>")==-1)
					p.println(linea);
				else{
					p.println(linea);
					break;
				}
			}
		
			System.out.println("Peticion enviada");
			scanner.close();
			print.close();
			socket.close();
	
			p.println("o");
			
		}
		else{
			p.println(mensaje.toUpperCase());
			p.println("o");
			
		}

		if ( mensaje.equals("cerrar")) {
		break;
		}
		
		
		}
		
		p.close();
		b.close();

		sc.close();
		s.close();
		System.out.println("TcpServer end \n");
		} catch (IOException e) {
		System.out.println("No puedo crear el socket");
		}
		}

	}


