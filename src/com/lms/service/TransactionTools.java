//All interaction with Database with regards to Table Transaction;

package sqlTools;

import database.SQLConnection;
import model.Transaction;

public class TransactionTools {
	public boolean addTransaction(Transaction transactionObj) {return true;}
	public boolean updateTransaction(int bookID, int userID) {return true;}
	public int checkActiveBookCount(int userID) {return 0;}
}
