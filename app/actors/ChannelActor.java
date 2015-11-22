
package actors;

import akka.actor.*;
import play.libs.Akka;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import play.mvc.Http;
import play.mvc.Http.Session;


public class ChannelActor extends UntypedActor {

    public static Props props(ActorRef out) {
        return Props.create(ChannelActor.class, out);
    }

    private final ActorRef out;
    private String channel;
    private static Map<String, ArrayList<ActorRef>> map1 = new HashMap<String, ArrayList<ActorRef>>();

    public ChannelActor(ActorRef out) {
        String channel = "10";
        this.out = out;
        if(channel != null) {
            System.out.println("channel :: "+channel);
            this.channel = channel;
        } else {
            this.channel = "0";
        }
        this.addSocketCon(channel,out);
    }

    public synchronized void addSocketCon(String id, ActorRef con){
        //String ss=session("channel");
        String ss="10";
        System.out.println("Session GET :: "+ss);
        if(ss == null){
            ss="0";
        }
        id=ss;

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
            ArrayList<ActorRef> itemsList = this.map1.get(this.channel);
            for (int i = 0; i < itemsList.size(); i++) {
				ActorRef out1=itemsList.get(i);
				out1.tell( message, self());
			}
        }
    }
}