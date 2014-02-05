import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class JsonReader {

  public static void main(String[] args) {
	  
      String jsonString = callURL("http://cdn.crunchify.com/wp-content/uploads/code/jsonArray.txt");
      //System.out.println("\n\njsonString: " + jsonString);


      try {  
          JSONArray jsonArray = new JSONArray(jsonString);
          System.out.println("\n\njsonArray: " + jsonArray);
      } catch (JSONException e) {
          e.printStackTrace();
      }
      
      try {
          JSONArray jsonArray = new JSONArray(jsonString);

          int count = jsonArray.length(); // get totalCount of all jsonObjects
          for(int i=0 ; i< count; i++){   // iterate through jsonArray
              JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position
              System.out.println("jsonObject " + i + ": " + jsonObject);
          }
      } catch (JSONException e) {
          e.printStackTrace();
      }
      /***********************************************************************************/
     
      System.out.println("************************************************");
      String jsonString2 = callURL("http://maps.googleapis.com/maps/api/geocode/json?latlng=60,30&sensor=false");
      try {  
          JSONArray jsonArray = new JSONObject(jsonString2).getJSONArray("results");
          if (jsonArray.length()>0){
    			String direccion = jsonArray.getJSONObject(0).getString("formatted_address");
    			System.out.println(direccion);
    		}
          //System.out.println("\n\njsonArray: " + jsonArray);
      } catch (JSONException e) {
          e.printStackTrace();
      }
      
    //Método que conectará con el WebService de Google que nos permite obtener
      //los datos de una localización dadas sus coordenadas
      System.out.println("************************************************");
      System.out.println("Ejemplo coordenadas 60,30 ");
      System.out.println(busquedaGoogle("38.15", "-0.89"));
      
  }
  
  public static String busquedaGoogle(String longitud, String latitud) {
  	String devuelve = "";
	
  	//Creamos un nuevo objeto HttpClient que será el encargado de realizar la
  	//comunicación HTTP con el servidor a partir de los datos que le damos.
  	HttpClient comunicacion = new DefaultHttpClient();
  	//Creamos una peticion GET indicando la URL de llamada al servicio.
  	String url = "http://maps.googleapis.com/maps/api/geocode/json?" +
				"latlng=" + latitud + "," + longitud + "&sensor=false";
  			//"latlng=38.15,-0.89&sensor=false");
  	HttpGet peticion = null;
	peticion = new HttpGet(url);
  	//Modificamos mediante setHeader el atributo http content-type para indicar
  	//que el formato de los datos que utilizaremos en la comunicación será JSON.
  	peticion.setHeader("content-type", "application/json");
  	
  	try {
  		//Ejecutamos la petición y obtenemos la respuesta en forma de cadena
  		HttpResponse respuesta = comunicacion.execute(peticion);
  		String respuestaCad = EntityUtils.toString(respuesta.getEntity());
  		//Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
  		JSONObject respuestaJSON = new JSONObject(respuestaCad);
  		//Accedemos al vector de resultados
  		JSONArray resultJSON = respuestaJSON.getJSONArray("results");
  		//Vamos obteniendo todos los campos que nos interesen.
  		//En este caso obtenemos la primera dirección de los resultados.
  		String direccion="SIN DATOS PARA ESA LONGITUD Y LATITUD";
  		if (resultJSON.length()>0){
  			direccion = resultJSON.getJSONObject(0).getString("formatted_address");
  		}
  		devuelve = "Dirección: " + direccion;
  	} catch(Exception e) {
  		//Log.e("Error ies REST", "ERROR!!", e);
  	}
  	
  	return devuelve;
  }

  public static String callURL(String myURL) {
      System.out.println("Requested URL:" + myURL);
      StringBuilder sb = new StringBuilder();
      URLConnection urlConn = null;
      InputStreamReader in = null;
      try {
          URL url = new URL(myURL);
          urlConn = url.openConnection();
          if (urlConn != null)
              urlConn.setReadTimeout(60 * 1000);
          if (urlConn != null && urlConn.getInputStream() != null) {
              in = new InputStreamReader(urlConn.getInputStream(),
                      Charset.defaultCharset());
              BufferedReader bufferedReader = new BufferedReader(in);
              if (bufferedReader != null) {
                  int cp;
                  while ((cp = bufferedReader.read()) != -1) {
                	  //al convertir el int a char aparecen los caracteres
                      sb.append((char) cp);
            
                  }
                  bufferedReader.close();
              }
          }
      in.close();
      } catch (Exception e) {
          throw new RuntimeException("Exception while calling URL:"+ myURL, e);
      }

      return sb.toString();
  }

}