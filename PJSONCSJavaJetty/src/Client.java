import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


public class Client {

	private static final  String CONTENT_LENGTH="Content-Length ";
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Categoria categoria;
		Articulo articulo;
		Gson gson=new Gson();
		String peticion=peticion();
		JsonObject json=gson.fromJson(peticion, JsonObject.class);
		if(json.get("precio") == null){
			categoria=new Categoria(json.get("categoria").getAsString(),json.get("id").getAsInt());
			System.out.println("Categoria: "+categoria.getCategoria()+" con id "+categoria.getId());
		}else{
			articulo=new Articulo(json.get("nombre").getAsString(),json.get("precio").getAsFloat(),json.get("id").getAsInt());
			System.out.println("Articulo: "+articulo.getNombre()+" con precio "+articulo.getPrecio()+" e id "+articulo.getId());
		}
		
		
	}
	
	public static String peticion() {
	  	
	  	HttpClient comunicacion = new DefaultHttpClient();
	  	
	  	String url = "http://localhost:8080?modelo=articulo&id=1";
	  		
	  	HttpGet peticion = null;

			peticion = new HttpGet(url);
		
	  	peticion.setHeader("content-type", "application/json");
	  	String respuestaCad="";
	  	try {
	  		
	  		HttpResponse respuesta = comunicacion.execute(peticion);
	
	  		respuestaCad = EntityUtils.toString(respuesta.getEntity());
	  		
	  	} catch(Exception e) {
	  		//Log.e("Error ies REST", "ERROR!!", e);
	  	}
	  	
	  	return respuestaCad;
	  }

}
