package serpis.psp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpClient {

	private static final  String CONTENT_LENGTH="Content-Length ";
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		/*String serverName=args[0];
		String respuesta;
		
		int port =Integer.parseInt(args[1]);
		System.out.printf("TcpClient serverName=%1$s port=%2$s \n", serverName,port);
		InetAddress serverInetAddress = InetAddress.getByName(serverName);
		Socket socket=new Socket(serverName,port);*/
		
		//printWriter.printf("%s\r\n","GET / HTTP/1.1");
		//printWriter.printf("%s\r\n","GET / HTTP/1.0");
		//printWriter.printf("%s\r\n","");
		//printWriter.printf("%s\r\n","Host:"+serverName);
		//printWriter.printf("\r\n");
		//printWriter.flush();
		
		
		//Scanner scanner=new Scanner(socket.getInputStream());
		
		/*while(scanner.hasNextLine()){
			String line=scanner.nextLine();
			System.out.printf("[%s]\n",line);
		}*/
		Socket s;//socket cliente
		
		PrintWriter p;
		Scanner b;
		//GET / HTTP/1.0 \r\n\r\n
		//GET / HTTP/1.1 \r\n Host:www.google.es\r\n
		//String host = "www.google.es";
		//int port = 80;
		String host = "localhost";
		int port = 8888;
		String respuesta = null;
		String mensaje;
		

		//Referencia a la entrada por consola (System.in)
		Scanner in = new Scanner(new InputStreamReader(System.in));

		try {

			//Creo una conexion al socket servidor
			s = new Socket(host,port);
	
			//Creo las referencias al canal de escritura y lectura del socket
			p = new PrintWriter(s.getOutputStream(), true);
			b = new Scanner ( new InputStreamReader ( s.getInputStream() ) );
	
			while ( true ) {
			//Ingreso un String por consola
			System.out.print("Mensaje a enviar: ");
	
			
			//Escribo en el canal de escritura del socket
			
			String f=in.nextLine();
			
			p.println( f );
			
			if ( f.equals("cerrar")) {
				break;
				}
			//Espero la respuesta por el canal de lectura
			
			//respuesta = b.nextLine();
			while(b.hasNextLine()){
				respuesta = b.nextLine();
				if(respuesta.equals("o"))
					break;
				System.out.println(respuesta);
			}
			
		}
		p.close();
		b.close();
		s.close();
		System.out.println("TcpClient end \n");
		} catch (UnknownHostException e) {
		System.out.println("No puedo conectarme a " + host + ":" + port);
		} catch (IOException e) {
		System.out.println("Error de E/S en " + host + ":" + port);
		}
	}
	
		//socket.close();
		/*hacer conexion tcp http1.0:
		 * telnet localhost 80
		 * GET / HTTP/1.0 (donde la / es la pagina)
		 * y hacemos otro return 
		 */
		
		/*hacer conexion tcp http1.1:
		 * telnet localhost 80
		 * GET / HTTP/1.1 
		 * Host: 127.0.1.1
		 */

	}


