package server.functionmanager;

import java.util.Vector;

import server.shared.User;

public class ContactsManager {
	/**genera la stringa dei contatti seguendo questo schema:
	*  { size: N,
	*    contact1: { username: x, name: y, surname: w, IP: N.N.N.N },
	*    contact2: { ... },
	*    contact3: { ... }
	*  } 
	*/
	public String getAllContacts(Vector<User> users){
		String contacts="{\"size\": \""+users.size()+"\"";
		for(int i=0; i<users.size(); i++){
			contacts+=", \"contact"+i+
				"\": \"{ \"username\": \""+users.get(i).getUsername()+
				"\", \"name\": \""+users.get(i).getName()+
				"\", \"surname\": \""+users.get(i).getSurname()+
				"\", \"IP\": \""+users.get(i).getIP()+"\" }\"";
		}
		contacts+="}";
		return contacts;
	}
	
}
