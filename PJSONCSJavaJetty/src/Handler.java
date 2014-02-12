import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.handler.AbstractHandler;

import com.google.gson.Gson;


public class Handler extends AbstractHandler
	{
	    

		Categoria arrayCat[]=new Categoria[]{new Categoria("cat1",1),new Categoria("cat2",2)};
		Articulo arrayArt[]=new Articulo[]{new Articulo("art1",2,1),new Articulo("art2",1,2)};
		Gson gson=new Gson();
		
		Object result=null;
		String json="";
		@Override
		public void handle(String arg0, org.eclipse.jetty.server.Request baseRequest,
				HttpServletRequest arg2, HttpServletResponse response)
				throws IOException, ServletException {

	        response.setContentType("text/html;charset=utf-8");
	        response.setStatus(HttpServletResponse.SC_OK);
	        baseRequest.setHandled(true);
	        
	        String modelo=baseRequest.getParameter("modelo");
	        String id=baseRequest.getParameter("id");
	       
	        
	        if(modelo.equalsIgnoreCase("categoria")){
	        	
	        	 for(int i=0;i<arrayCat.length;i++){
	 	        	if(arrayCat[i].getId()== Integer.parseInt(id))
	 	        		result=arrayCat[i];
	 	        }
	        	 
	        }else if(modelo.equalsIgnoreCase("articulo")){
	        	
	        	 for(int i=0;i<arrayArt.length;i++){
	 	        	if(arrayArt[i].getId()== Integer.parseInt(id))
	 	        		result=arrayArt[i];
	 	        }
	        }
	        
	       
	        if(result == null)
	        	json="no hay reistros para esa peticion";
	        else
	        	json=gson.toJson(result);
	        response.getWriter().println(json);
			
		}

		
	}
