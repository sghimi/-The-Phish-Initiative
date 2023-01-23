package phishinitiative;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.java_websocket.WebSocket;

/**
 *
 * @author dog
 */
public class Database {
	//Stores information about senders and recipients, entries will be
	//removed by main method after a certain amount of time
	public static HashMap<String,ArrayList<String>> map = new HashMap<>();
	//Key: Sender who is sending scam emails
	//Value index 0: time before this entry is removed by main thread
	//Values after 0: emails who are the recipients of the scam
	
	
	//Stores information about a given session on a scam website
	public static HashMap<WebSocket,ArrayList<String>> scamResults = new HashMap<>();
	//Key: Websocket to identify scammed person's session
	//Value index 0: their email address
	//Value after 0: All entries they made into the login page
	
	
	
	public static void newEntry(String sender, String[] recipients){ //adds entry to map
		if(map.containsKey(sender)){ //if sender is already in map
			ArrayList<String> list = map.get(sender);
			list.set(0,"24"); //sets total time to be 2400 seconds
			for(String str : recipients){
				if(!list.contains(str)){ //if the sender doesn't already have this recipient
					list.add(str);
					map.put(sender, list);
				}
			}
			System.out.println("put <"+sender+","+list.toString()+">");
			map.put(sender, list);
			return;
		}
		ArrayList<String> list = new ArrayList<>();
		list.add(0,"24"); //sets total time to be 2400 seconds
		list.addAll(Arrays.asList(recipients));
		map.put(sender,list);
		System.out.println("put <"+sender+","+list.toString()+">");
	}
	
	
	public static String findSender(String recipient){ //find sender given recipient's email
		
		for(Map.Entry<String,ArrayList<String>> entry : map.entrySet()){
			for(String str: entry.getValue()){
				System.out.println("comparing "+str+" to "+recipient);
				if(str.equals(recipient))
					return entry.getKey();
			}
		}
		return null; //return null if not found
	}
	
	
	
	public static String generateKey(String email){ //generate website params based on recipient's email
		String key = "?";
		for(int i = 0; i < email.length(); i++){
			char c = email.charAt(i);
			c +=1; //shifts each character forward by 1
			key = key + c;
		}
		return key;
	}
	
	public static void newVictim(String key, WebSocket ws){ //adds websocket session to scamResults
		if(scamResults.containsKey(ws)) //if session is already in scamResults
			return;
		String email = "";
		for(int i = 1; i < key.length(); i++){
			char c = key.charAt(i);
			c-=1; //shifts each char backward by 1
			email = email + c;
		}
		ArrayList<String> list = new ArrayList<>();
		list.add(email);
		scamResults.put(ws,list);
	}
	
	public static void newScamEntry(String message, WebSocket ws){ //adds data to session of whatever was inputted
		if(!scamResults.containsKey(ws)){ //if session isn't in scamResults
			return;
		}
		ArrayList<String> list = scamResults.get(ws);
		list.add(message);
		scamResults.put(ws,list);
	}
	
	public static String generateResultTitle(WebSocket ws){ //generates title of result email given session
		if(!scamResults.containsKey(ws)){ //if session isn't in scamResults
			return null;
		}
		String title = scamResults.get(ws).get(0);
		title = title + " got scammed!";
		return title;
	}
	
	public static String generateResultBody(WebSocket ws){//generates body of result email given session
		if(!scamResults.containsKey(ws)){ //if session isn't in scamResults
			return null;
		}
		
		ArrayList<String> list = scamResults.get(ws);
		String body = list.get(0) + " opened the website.\n";
		for(int i = 1; i < list.size(); i++){
			body = body + "\n Entered: "+list.get(i) + "\n";
		}
		body = body + list.get(0) + " closed the website. \n";
		scamResults.remove(ws); //removes the session from scamResults
		return body;
	}
	
}
