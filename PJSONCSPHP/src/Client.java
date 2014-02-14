import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class Client {

	static InputStream is = null;
	static Gson gson=new Gson();
	static String json = "";
	
	
	public static void main(String[] args) {
		
		
		Categoria categoria;
		Articulo articulo;
		
		String id="1";
		String modelo="articulo";
		
		String ip="192.168.24.197";
		String url="http://"+ip+"/PHP/categoria-articulo.php";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("modelo", modelo));
		
		JsonObject json=makeHttpRequest(url,params);
		
		if(json.get("precio") == null){
			categoria=new Categoria(json.get("nombre").getAsString(),json.get("id").getAsInt());
			System.out.println("Categoria: "+categoria.getCategoria()+" con id "+categoria.getId());
		}else{
			articulo=new Articulo(json.get("nombre").getAsString(),json.get("precio").getAsFloat(),json.get("id").getAsInt());
			System.out.println("Articulo: "+articulo.getNombre()+" con precio "+articulo.getPrecio()+" e id "+articulo.getId());
		}
		
		
	}
	/**/
	public static JsonObject makeHttpRequest(String url, 
			List<? extends org.apache.http.NameValuePair> params) {

		// Making HTTP request
		try {
			
			
				DefaultHttpClient httpClient = new DefaultHttpClient();
				
				String paramString = URLEncodedUtils.format(params, "utf-8");
		
				url += "?" + paramString;
				HttpGet httpGet = new HttpGet(url);
			

				HttpResponse httpResponse = httpClient.execute(httpGet);
	
				HttpEntity httpEntity = httpResponse.getEntity();
			
				
				is = httpEntity.getContent();
				
			}			
			

		 catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
	
			StringBuilder sb = new StringBuilder();
			String line = null;
	
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n ");
				
			}
			
			is.close();
			json = sb.toString();
		
		} catch (Exception e) {
			//Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		JsonObject jObj=null;
		// try parse the string to a JSON object
		try {
			jObj =gson.fromJson(json, JsonObject.class);
		} catch (Exception e) {
			//Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}
}
