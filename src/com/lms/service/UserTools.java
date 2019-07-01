//All interaction with Database with regards to Tables User and User_Membership;

package sqlTools;

import database.SQLConnection;
import model.User;

public class UserTools {
	public boolean librarianLogin(String username, String password) {return true;}
	public boolean addLibrarian(User userObj) {return true;}
	public String checkMembership(int userID) {return "GOLD";}
	
}
