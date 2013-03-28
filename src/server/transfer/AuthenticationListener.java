package server.transfer;


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
   		String type= token.getString("type");
   		WebSocketPacket wspacket=null;	    
   		if(type.equals("Login")){
   			User user=authenticationManager.login(token.getString("username"),token.getString("password"),event.getConnector().getRemoteHost().toString());

   			if(user==null){
   				wspacket = new RawPacket("{\"risposta\":\"false\",\"name\":\"\",\"surname\":\"\"}");
   			}else{
   				wspacket = new RawPacket("{\"risposta\":\"true\", \"name\":\""+user.getName()+"\", \"surname\":\""+user.getSurname()+"\"}");		
   			}
   		}
   		else if(type.equals("SignUp")){
   			User user = authenticationManager.createUser(token.getString("username"),token.getString("password"), token.getString("name"), token.getString("surname"), event.getConnector().getRemoteHost().toString());

   			if(user==null){
   				wspacket = new RawPacket("{\"risposta\":\"false\"}");
   			}else{
   				wspacket = new RawPacket("{\"risposta\":\"true\"}");
   			}   		   
   		}
   		else if(type.equals("getContacts")){
   			ContactsManager contacts= new ContactsManager();
   			wspacket = new RawPacket(contacts.getAllContacts(userManager.getAllContacts(userManager.getUser(token.getString("username")))));
   			System.out.println(wspacket.getString());
   		}
  		sendPacket(wspacket, event);
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