package server.transfer;


import java.util.Collection;
import java.util.Map;

import org.jwebsocket.api.WebSocketConnector;
import org.jwebsocket.api.WebSocketPacket;
import org.jwebsocket.config.JWebSocketConfig;
import org.jwebsocket.factory.JWebSocketFactory;
import org.jwebsocket.kit.RawPacket;
import org.jwebsocket.kit.WebSocketServerEvent;
import org.jwebsocket.listener.WebSocketServerTokenEvent;
import org.jwebsocket.listener.WebSocketServerTokenListener;
import org.jwebsocket.server.TokenServer;
import org.jwebsocket.token.Token;

import server.ServerMyTalk;


public class CallTransfer implements WebSocketServerTokenListener {
	TokenServer tokenServer;
	
    public void setTokenServer(ServerMyTalk server) {
        tokenServer=server.getTokenServer();
    }

    public void processToken(WebSocketServerTokenEvent event, Token token) {
   		String type= token.getString("type");
   		WebSocketPacket wspacket=null;
  		System.out.println("� arrivato un messaggio" + token.toString());
   		if(type.equals("call"))
   		{
   			wspacket=new RawPacket(token.toString());
   			sendPacket(wspacket, event);
   		}
  		
    }

    public void processClosed(WebSocketServerEvent arg0) {
    }

    public void processOpened(WebSocketServerEvent event) {
        System.out.println("***********Client '" + event.getSessionId()
                + "' connected.*********");
    }

    public void sendPacket(WebSocketPacket packet, WebSocketServerEvent event){
		event.sendPacket(packet); 
    }
    public void processPacket(WebSocketServerEvent event, WebSocketPacket packet) {      
    }
}