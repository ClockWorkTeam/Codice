package server.transfer;


import java.util.regex.Pattern;

import org.jwebsocket.api.WebSocketPacket;
import org.jwebsocket.kit.RawPacket;
import org.jwebsocket.kit.WebSocketServerEvent;
import org.jwebsocket.listener.WebSocketServerTokenEvent;
import org.jwebsocket.listener.WebSocketServerTokenListener;
import org.jwebsocket.server.TokenServer;
import org.jwebsocket.token.Token;

import server.ServerMyTalk;
import server.usermanager.*;
import server.functionmanager.ContactsManager;
import server.shared.User;

public class AuthenticationListener implements WebSocketServerTokenListener {
	TokenServer tokenServer;
	private AuthenticationManager authenticationManager;
	private UserManager userManager;
	
	public AuthenticationListener(AuthenticationManager authenticationManager, UserManager userManager){
		this.authenticationManager=authenticationManager;
		this.userManager=userManager;
	}
	
    public void setTokenServer(ServerMyTalk server) {
        tokenServer=server.getTokenServer();
    }

    public void processToken(WebSocketServerTokenEvent event, Token token) {
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
       Pattern pattern =Pattern.compile("[\\:\\,\\}\\{\"]");
       String[] dati=packet.getString().split(pattern.toString());
       WebSocketPacket wspacket=null;
       if(dati[0].equals("Login")){
    	   User user=authenticationManager.login(dati[5],dati[11],event.getConnector().getRemoteHost().toString());
    		if(user==null){
    				wspacket = new RawPacket("{\"risposta\":\"false\",\"name\":\"\",\"surname\":\"\"}");
    		}else{
    				wspacket = new RawPacket("{\"risposta\":\"true\", \"name\":\""+user.getName()+"\", \"surname\":\""+user.getSurname()+"\"}");		
            }
       }
       else if(dati[0].equals("getContacts")){
    	  ContactsManager contacts= new ContactsManager();
		  wspacket = new RawPacket(contacts.getAllContacts(userManager.getAllContacts(userManager.getUser(dati[5]))));
       }
       else if(dati[0].equals("SignUp")){
    	   User user = authenticationManager.createUser(dati[5], dati[11], dati[17], dati[23], event.getConnector().getRemoteHost().toString());
    	   if(user==null){
				wspacket = new RawPacket("{\"risposta\":\"false\"}");
    	   }else{
				wspacket = new RawPacket("{\"risposta\":\"true\"}");
    	   }   		   
       }
	   sendPacket(wspacket, event);
    }
}