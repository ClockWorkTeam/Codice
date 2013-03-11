package server.dao;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Vector;
import server.shared.User;

public class ContactsDaoSQLTest {
	ContactsDaoSQL contacts;
	JavaConnectionSQLite connection;
	
	public void init(){
		connection= new JavaConnectionSQLite();
		contacts= new ContactsDaoSQL(connection);
	}
	
	@Test
	public void testStatic() {
		init();
		Vector<String> usernames=contacts.getContactsUsername();
		assertTrue("Non dovrebbero essere presenti utenti",usernames.isEmpty());
	}

	@Test
	public void testRegistration() {
		init();
		User user =contacts.createContact("Leo", "par2409", "Pardis", "Zohouri", "0.0.0.1");
		assertTrue("La registrazione non è stata eseguita", user!=null);
		User userP =contacts.createContact("Leo", "par2409", "Pardis", "Zohouri", "0.0.0.1");
		assertTrue("sono stati registrati due utenti con lo stesso username", userP==null);
		assertTrue("Il numero di utenti registrati è errato",(contacts.getContactsUsername()).size()==1);		
		contacts.removeContact(user);
	}

}
