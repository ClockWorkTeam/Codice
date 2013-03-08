/**
* Nome: UserTest
* Package: server.shared
* Autore: Zohouri Haghian Pardis
* Data: 2013/03/07
* Versione: 1.0
*
* Modifiche:
* +---------+---------------+--------------------------+
* | Data    | Programmatore |         Modifiche        |
* +---------+---------------+--------------------------+
* |  130307 |     ZHP       | + creazione documento	   |
* |         |               |     TEST "UNITA'" 	   |
* |			|				| (senza RecordMessage)	   |
* +---------+---------------+--------------------------+
* |  130308 |     ZHP       |     TEST INTEGRAZIONE    |
* |			|				|	(con RecordMessage)	   |
* +---------+---------------+--------------------------+
*
*/ 

package server.shared;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import java.util.Vector;

public class UserTest {
	User user;

	private void initLogin(){
		user= new User("Leo","par2409","0.0.10.0");	
	}
	
	private void initTot(){
		user= new User("Liquid90","ciao","Giacomo","Bain","0.0.10.10");
	}
	
	@Test
	public void testCostruttoreLogin() {
		initLogin();
		assertTrue("Username non è quello atteso", user.getUsername() == "Leo");
		assertTrue("Password non è quello atteso", user.getPassword() == "par2409");
		assertTrue("IP non è quello atteso", user.getIP() == "0.0.10.0");
	}

	@Test
	public void testCostruttoreTot() {
		initTot();
		assertTrue("Username non è quello atteso", user.getUsername() == "Liquid90");
		assertTrue("Password non è quello atteso", user.getPassword() == "ciao");
		assertTrue("IP non è quello atteso", user.getIP() == "0.0.10.10");
	}
/**
 * Test sui messaggi
 */
	RecordMessage rm;
	RecordMessage rm2;
	Vector<RecordMessage> rms;
	
	
	private void initOne(){
		rm= new RecordMessage("Liquid90","Pardis","ciao", new Date(2013-01-01));
	}
	
	private void initMore(){
		rms= new Vector<RecordMessage>();
		rm= new RecordMessage("Liquid90","Pardis","ciao", new Date(2013-01-01));
		rms.add(rm);
		rm2= new RecordMessage("Ago","Pardis","ciao2", new Date(2013-01-01));
		rms.add(rm2);		
	}

	@Test
	public void testMessage(){
		initTot(); //inizializzo user
		initOne(); //inizializzo messaggio
		user.setMessage(rm);
		assertTrue("Il messaggio non è stato inserito", user.isUserMessage(rm));
	}
	
	@Test
	public void testMessages(){
		initTot();
		initMore();
		user.setMessages(rms);
		assertTrue("Il messaggio non è stato inserito", user.isUserMessage(rm));
		user.removeMessage(rm);
		assertFalse("Il messaggio non è stato eliminato", user.isUserMessage(rm));
	}
	
}
