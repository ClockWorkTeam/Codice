/**
* Nome: LoginDao
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
import server.shared.*;
/**
 * Interfaccia che contiene i prototipi dei metodi per gestire il login
 * 
 */

public interface LoginDao{
 /**
   * Metodo che prova il login
   * @param user Oggetto User da cui si prendono username e password necessari per il login
   * @return un boolean che indica se il login e` avvenuto con successo o no
   */    
  public boolean login(User user);

 /** Metodo che effettua il logout
   * 
   * @param user Oggetto User dell'utente che vuole disconnettersi
   * @return un boolean che indica se il logout e` avvenuto con successo o no
   */    
  public boolean logout(User user);

}