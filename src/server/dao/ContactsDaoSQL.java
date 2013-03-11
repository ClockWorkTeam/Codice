/**
* Nome: ContactsDaoSQL
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import server.shared.User;

public class ContactsDaoSQL {
	private Vector<User> users= new Vector<User>();
	private JavaConnectionSQLite connection;
	
	public ContactsDaoSQL(JavaConnectionSQLite connection){
		this.connection=connection;
	}
	/**Metodo che registra l'utente al database
	* @param username
	* @param password
	* @param name
	* @param surname
	* @param IP
	* @return lo User appena creato, null se lo User esiste già
	*/
	public User createContact(String username, String password, String name, String surname, String IP){
		if(getContact(username)!=null){return null;}
		User user = new User(username, password, name, surname, IP);

		ResultSet rs = connection.select("UserDataSQL","*", "","");
		try{
			rs.getString("username");
		}catch(SQLException e){ 
			boolean done= connection.executeUpdate("INSERT INTO UserDataSQL VALUES ('"+user.getUsername()+"','"+user.getPassword()+"','"+user.getName()+"','"+user.getSurname()+"','"+user.getIP()+"');");
			if(!done){
				return null;
			}
			users.add(user);
			return user;
		}
		return null;
	}
	
	/**Metodo che restituisce l'utente relativo ad un dato username
	  * @param username
	  * @return User corrispondente al dato username, restituisce null se non è presente
	 */
	public User getContact(String username){
		for(int i=0; i< users.size(); i++){
			if(username.equals((users.get(i)).getUsername()))
				return users.get(i);
		}
		return null;
	}

		
	/**Metodo che restituisce tutti gli username presenti nel DB
	 * @return Vector<String> username presenti 
	 */
	public Vector<String> getContactsUsername(){
		Vector<String> usernames= new Vector<String>();
		for(int i=0; i< users.size(); i++){
			usernames.add((users.get(i)).getUsername());
		}
		return usernames;
	}
	

	/**Metodo che registra l'utente al database
	* @param User da eliminare 
	* @return boolean se l'op ha avuto successo o no
	*/
	public boolean removeContact(User user){
		user= getContact(user.getUsername());
		if(user==null){return false;}
		users.remove(user);
		boolean done = connection.executeUpdate("DELETE FROM UserDataSQL WHERE username='"+user.getUsername()+"';");
		if(!done){
			return false;
		}
		return true;
	}

}
