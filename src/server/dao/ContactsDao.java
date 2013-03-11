/**
* Nome: ContactsDao
* Package: server.dao
* Autore: Zohouri Haghian Pardis
* Data: 2013/03/06
* Versione: 1.0
*
* Modifiche:
* +---------+---------------+--------------------------+
* | Data    | Programmatore |         Modifiche        |
* +---------+---------------+--------------------------+
* |  130306 |     ZHP       | + creazione documento	   |
* |         |               |                          |
* +---------+---------------+--------------------------+
*
*/ 

package server.dao;
import java.util.Vector;

import server.shared.User;
/**
 * Interfaccia che contiene i prototipi dei metodi per gestire la lista contatti presenti nel DB
 * 
 */
public interface ContactsDao {

	/**Metodo che registra l'utente al database
	* @param username
	* @param password
	* @param name
	* @param surname
	* @param IP
	* @return lo User appena creato, null se lo User esiste già
	*/
	public User createContact(String username, String password, String name, String surname, String IP);

	/**Metodo che restituisce l'utente relativo ad un dato username
	  * @param username
	  * @return User corrispondente al dato username, restituisce null se non è presente
	 */
	public User getContact(String username);
		
	/**Metodo che restituisce tutti gli username presenti nel DB
	 * @return Vector<String> username presenti 
	 */
	public Vector<String> getContactsUsername();
	
	/**Metodo che registra l'utente al database
	* @param User da eliminare 
	* @return boolean se l'op ha avuto successo o no
	*/
	public boolean removeContact(User user);
}
