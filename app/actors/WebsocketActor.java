
package actors;
import akka.actor.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class WebsocketActor extends UntypedActor {

    public static Props props(ActorRef out) {
        return Props.create(WebsocketActor.class, out);
    }

    private final ActorRef out;

    private static Map<String, ArrayList<ActorRef>> map1 = new HashMap<String, ArrayList<ActorRef>>();

    public WebsocketActor(ActorRef out) {
        this.out = out;
        this.addSocketCon("10",out);
    }

    public synchronized void addSocketCon(String id, ActorRef con){

    	ArrayList<ActorRef> itemsList = this.map1.get(id);

    	if(itemsList == null) {
	         itemsList = new ArrayList<ActorRef>();
	         itemsList.add(con);
	         this.map1.put(id, itemsList);
	    } else {
	        // add if item is not already in list
	        if(!itemsList.contains(con)) itemsList.add(con);
	    }

    }

    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {

            //out.tell("I received your message: " + message, self());
            ArrayList<ActorRef> itemsList = this.map1.get("10");
            for (int i = 0; i < itemsList.size(); i++) {
				ActorRef out1=itemsList.get(i);
				out1.tell( message, self());
			}
        }
    }
}