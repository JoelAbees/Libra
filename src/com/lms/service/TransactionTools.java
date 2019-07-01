//All interaction with Database with regards to Table Transaction;

package com.lms.service;

import com.lms.db.util.SQLConnection;
import com.lms.model.Transaction;

public class TransactionTools {
	public boolean addTransaction(Transaction transactionObj) {return true;}
	public boolean updateTransaction(int bookID, int userID) {return true;}
	public int checkActiveBookCount(int userID) {return 0;}
}
