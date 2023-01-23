package phishinitiative;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author dog
 */


//Main thread starts the websocket server threads for main and scam sites, then this thread is allocated
//to polling any map entries who stick around too long
public class PhishInitiative extends WebsocketServer{
	public static void main(String[] args) throws Exception{
		WebsocketServer wss = new WebsocketServer(6312); //thread which listens to trafic from main website
		wss.start();	
		WebsocketServer phisher = new WebsocketServer(6313); //thread which listens to trafic from scam websites
		phisher.start();
		while(true){
			Thread.sleep(100000); //wait for 100 secs
			for(Map.Entry<String,ArrayList<String>> entry : Database.map.entrySet()){
				Integer time = Integer.parseInt(entry.getValue().get(0)); //grab first value of given key
				time --; //reduce time by 1
				System.out.println("reducing "+entry.getKey() + " to "+time);
				if(time <= 0){ //if time is 0
					Database.map.remove(entry.getKey()); //remove key value pair from map
				}
				else{ //if time isn't 0
					ArrayList<String> list = entry.getValue();
					list.set(0, time.toString()); //set time value to be one less
					Database.map.put(entry.getKey(), list); //put changed entry back into map
				}
			}
		}
	}	
} 