package phishinitiative;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;



/**
 *
 * @author dog
 */


// Implementation derived from Java Websocket Api's example files at 
// https://github.com/TooTallNate/Java-WebSocket/tree/master/src/main/example

// WebsocketServer (not to be confused with object WebSocketServer) is a runnable
// class which handles websocket connections given a port
public class WebsocketServer extends WebSocketServer{
	private int TCP_PORT;//port this thread is listening to
	
	private Set<WebSocket> connections;//derived from example code
	
	public WebsocketServer(){ //default constructor (dont actually use)
		super(new InetSocketAddress(6312));
		TCP_PORT = 6312;
		connections = new HashSet<>();
	}
	
	public WebsocketServer(int port){ //constructor with port (use)
		//ports <= 6312 are for main website, ports > 6312 are for scam websites
		super(new InetSocketAddress(port));
		TCP_PORT = port;
		connections = new HashSet<>();
	}

	@Override
	public void onOpen(WebSocket ws, ClientHandshake ch) { //adds websocket to thread's set
		connections.add(ws);
		System.out.println("New connection from "+ws.getRemoteSocketAddress().getPort());
	}

	@Override
	public void onClose(WebSocket ws, int i, String string, boolean bln) { //removes websocket from thread's set
		if(Database.scamResults.containsKey(ws)){
			String victim = Database.scamResults.get(ws).get(0);
			String recipient = Database.findSender(victim); //recipient in this case is the original sender
			if(recipient == null){
				System.out.println("find Sender failed");
				connections.remove(ws);
				System.out.println("Closed connection to "+ws.getRemoteSocketAddress().getPort());
				return;
			}
			try{
				EmailServer.sendMessage(recipient, -1, ws);
			}
			catch(Exception e){
				System.out.println("failed to send result email to "+recipient);
			}
		}
		connections.remove(ws);
		System.out.println("Closed connection to "+ws.getRemoteSocketAddress().getPort());
	}

	@Override
	public void onMessage(WebSocket ws, String message) { //on recieving message
		System.out.println("Message from client: "+message);
		
		if(TCP_PORT <= 6312){ //messages from main site in form {"recieveEmail":"email@example.com","phishEmail":"example@email.com","type":#}
			int type = Integer.parseInt(message.substring(message.length()-2,message.length()-1)) - 1;
			message = message.substring(2,message.length()-2);
			String[] strs = message.split("\":\"|\",\"");
			if(strs.length < 5){
				System.out.println("invalid message: "+message);
				System.out.println(Arrays.toString(strs));
				return;
			}
			String sender = strs[1];
			String[] recipients = new String[1];
			recipients[0] = strs[3];
			Database.newEntry(sender, recipients);
			try{
				EmailServer.sendMessage(recipients[0],type,null);
			}
			catch(Exception e){
				System.out.println("failed to send email to "+recipients[0]);
			}
		}
		else{ //message sent from scam victim
			if(message.length() == 0){
				return;
			}
			char c = message.charAt(0); //char c: first character
			if(c == '?'){ //if packet leads with ?, message is url params
				Database.newVictim(message, ws);
			}
			else{ //if not, it is the website recording an entry into username password section
				Database.newScamEntry(message, ws);
			}
		}
	}

	@Override
	public void onError(WebSocket ws, Exception excptn) { //required implemented abstract method
		if(ws != null){
			System.out.println("ERROR from "+ws.getRemoteSocketAddress().getPort());
			excptn.printStackTrace();
			connections.remove(ws);
		}
		else{
			System.out.println("A null socket had an error?");
		}
	}

	@Override
	public void onStart() { //starts the thread
		System.out.println("Server started at port:"+TCP_PORT);
		setConnectionLostTimeout(10000); //sets the idle timeout time to 10000 seconds
	}

}

