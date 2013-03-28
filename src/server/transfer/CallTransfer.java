package server.transfer;


import java.util.Collection;
import org.jwebsocket.api.WebSocketConnector;
import org.jwebsocket.api.WebSocketPacket;
import org.jwebsocket.kit.RawPacket;
import org.jwebsocket.kit.WebSocketServerEvent;
import org.jwebsocket.listener.WebSocketServerTokenEvent;
import org.jwebsocket.listener.WebSocketServerTokenListener;
import org.jwebsocket.server.TokenServer;
import org.jwebsocket.token.Token;

import server.ServerMyTalk;


public class CallTransfer implements WebSocketServerTokenListener {
	TokenServer tokenServer;
    private AuthenticationTransfer authentication;

    public CallTransfer(AuthenticationTransfer authentication){
    	this.authentication=authentication;
    }
    public void setTokenServer(ServerMyTalk server) {
        tokenServer=server.getTokenServer();
    }

    public void processToken(WebSocketServerTokenEvent event, Token token) {
   		String type= token.getString("type");
  		WebSocketPacket wspacket=null;
   		if(type.equals("call")){
   			Collection<WebSocketConnector> clients=authentication.getClients();
   		  	for (WebSocketConnector connector : clients) {
   	    		if(connector.getRemoteHost().toString().equals(token.getString("ip"))){
   	    			wspacket=new RawPacket("{\"type\":\"call\", \"ip\":\""+event.getConnector().getRemoteHost()+"\",\"typecall\":\""+token.getString("typecall")+"\"}");
   	    			System.out.println(wspacket.getString());
   	    			sendPacket(wspacket,connector);
   	    		}
   	    	}
   		}
  		
    }

    public void processClosed(WebSocketServerEvent arg0) {
    }

    public void processOpened(WebSocketServerEvent event) {
        System.out.println("***********Client '" + event.getSessionId()
                + "' connected.*********");
    }

    public void sendPacket(WebSocketPacket packet, WebSocketConnector connector){
    	tokenServer.sendPacket(connector, packet); 
    }
    public void processPacket(WebSocketServerEvent event, WebSocketPacket packet) {      
    }
}