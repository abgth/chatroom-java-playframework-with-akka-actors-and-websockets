package controllers;

import play.*;
import play.mvc.*;
import play.mvc.WebSocket;

import akka.actor.*;
import play.libs.F.*;
import play.libs.Akka;
import akka.actor.ActorSystem;

import actors.WebsocketActor;
import actors.ChannelActor;

import play.mvc.Http;
import play.mvc.Http.Session;

import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
    public Result ws() {
        return ok(socket.render());
    }
	
	public WebSocket<String> socket() {
	    return WebSocket.withActor(WebsocketActor::props);
	}
	
	public WebSocket<String> channel(String channel) {
		session("channel", channel);
		Session session = Http.Context.current().session();
        session.put("channel", channel);
       	//ActorRef channelizer = Akka.system().actorOf(Props.create(ChannelActor.class));
		//ActorRef channelizer = Akka.system().actorOf(Props.create(ChannelActor.class));
	    //return WebSocket.withActor(ChannelActor::props);
	    String ss=session("channel");
	    System.out.println("Session GET :: "+ss);
	    return WebSocket.withActor(ChannelActor::props);
	}
}
