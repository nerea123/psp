import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
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
      /*calcular la ruta desde Chicago, IL a Los Ángeles, CA que atraviese dos hitos en Joplin, MO y en Oklahoma City*/
     
      String jsonString2 = callURL("http://maps.googleapis.com/maps/api/directions/json?origin=Chicago,IL&destination=Los+Angeles,CA&waypoints=Joplin,MO|Oklahoma+City,OK&sensor=false");
      try {  
          JSONObject jsonArray = new JSONObject(jsonString2);
          System.out.println("\n\njsonArray: " + jsonArray);
      } catch (JSONException e) {
          e.printStackTrace();
      }
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