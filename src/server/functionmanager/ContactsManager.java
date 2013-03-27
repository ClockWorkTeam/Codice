package server.functionmanager;

import java.util.Vector;

import server.shared.User;

public class ContactsManager {
	public String getAllContacts(Vector<User> users){
		String contacts="{ size :"+users.size()+",";
		for(int i=0; i<users.size(); i++){
			contacts+=" username"+i+": "+users.get(i).getUsername()+",";
			contacts+=" name"+i+": "+users.get(i).getName()+",";
			contacts+=" surname"+i+": "+users.get(i).getSurname()+",";
			contacts+=" IP"+i+": "+users.get(i).getIP()+",";
		}
		contacts+="}";
		return contacts;
	}
	
}
