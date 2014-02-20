import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*INSTRUCCIONES
 * Para probar su funcionamiento solo tienes que abrir el navegador y escribir http://localhost:8080/?modelo=articulo&id=2 
 * pudiendo cambiar el modelo por categoria y el id por 1 o 2*/

public class Server {

	private static final int SERVER_PORT = 8080;
	private static String json="";
	private static Categoria categoria=null;
	private static Articulo articulo=null;
	

		public static void main(String[] args) throws IOException {

			Socket socket = null;

			ServerSocket serverSocket;

			serverSocket = new ServerSocket(SERVER_PORT);

			

			

			while (true){

				try{

					System.out.println("Iniciada escucha...");

					socket = serverSocket.accept();

					

					System.out.println("Recibida conexion");

					PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), 
	                         true);   
					
					BufferedReader in = new BufferedReader(
	                           new InputStreamReader(socket.getInputStream())); 

				
					 getParameters(in.readLine());
					 String s;
				        while ((s = in.readLine()) != null) {
				            System.out.println(s);
				        	
				            if (s.isEmpty()) {
				                break;
				            }
				        }
					
					
					out.write("HTTP/1.0 200 OK\r\n");
				      
				       out.write("Server: Apache/0.8.4\r\n");
				       out.write("Content-Type: application/json\r\n");
				       out.write("Content-Length: "+json.length()+"\r\n");
				      
				       out.write("\r\n");
				       //out.write("<TITLE>JSONSERVER</TITLE>");
				       //out.write("<h1>Obteniendo JSON</h1>");
				       
				       
				    	out.write(json);
				       
				       out.flush();
				     

				}catch(Exception exception){

					exception.printStackTrace();

				}

			}

		}

		

		public static void getParameters(String mensaje){

			String modelo = mensaje.substring(mensaje.indexOf("?")+1,mensaje.indexOf("&"));
			
			System.out.println(modelo);
			
			String id = mensaje.substring(mensaje.indexOf("&")+1,mensaje.indexOf("HTTP/1.1"));
			
			id=id.substring(id.indexOf("=")+1,id.length());
			System.out.println(id);
			
			Categoria arrayCat[]=new Categoria[]{new Categoria("cat1",1),new Categoria("cat2",2)};
			Articulo arrayArt[]=new Articulo[]{new Articulo("art1",2,1),new Articulo("art2",1,2)};
			
			if(modelo.equals("modelo=categoria")){
				
				for(int i=0;i<arrayCat.length;i++)
					if(arrayCat[i].getId() == Integer.parseInt(id.trim()))
						categoria=arrayCat[i];
			}
			
			else{
				for(int i=0;i<arrayArt.length;i++)
					if(arrayArt[i].getId() == Integer.parseInt(id.trim()))
						articulo=arrayArt[i];
			}
			if(categoria == null && articulo == null)
		    	json="Ningún dato obtenido con ese modelo o id";
			else if(articulo == null){
				json="{ \"id\": \""+categoria.getId()+"\",\"categoria\": \""+categoria.getCategoria()+"\"}";
				categoria=null;
			}
			else{
				json="{ \"id\": \""+articulo.getId()+"\",\"articulo\": \""+articulo.getNombre()+"\",\"precio\": \""+articulo.getPrecio()+"\"}";
				articulo=null;
			}
			
		}
	

}
