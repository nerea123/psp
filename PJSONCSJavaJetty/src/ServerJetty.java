import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

public class ServerJetty {

	
		
		public static void main(String[] args) throws Exception {
			Server server = new Server(8080);
			

		    server.setHandler(new Handler());
		    
		    SelectChannelConnector connector0 = new SelectChannelConnector();
	        connector0.setPort(8080);
	        connector0.setHost("localhost");
	        connector0.setMaxIdleTime(30000);
	        connector0.setRequestHeaderSize(8192);
	        server.setConnectors(new Connector[]{ connector0 });
		    server.start();
		    server.join();
		}
}
