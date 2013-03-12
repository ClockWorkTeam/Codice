package server.dao;

import static org.junit.Assert.*;
import org.junit.Test;
import server.shared.*;
import server.dao.UserDaoSQL;

public class UserDaoSQLTest {
	
	private JavaConnectionSQLite connection;
	private UserList users;
	private UserDaoSQL userDao;
	
	private void init(){
		connection =new JavaConnectionSQLite();
		users=new UserList();
		userDao=new UserDaoSQL(connection, users);
	}
	
	@Test
	public void test() {
		init();
		User user=userDao.recordUser("Leo", "par1368", "Pardis", "Zohouri", "0.0.0.1");
		assertTrue("Creazione utente fallito",(user.getUsername()).equals("Leo"));
		assertTrue("Utente non presente", (users.getUser(user.getUsername()))!=null);
		
		assertTrue("Rimozione utente fallita",userDao.removeUser(user.getUsername()));
	}

}
