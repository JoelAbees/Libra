//All interaction with Database with regards to Table Books and Book_Details

package sqlTools;

import database.SQLConnection;
import model.Book;

public class BookTools {
	
	public boolean checkBook(String ISBN) {return true;}
	public boolean addBook(String ISBN, int quantity) {return true;}
	public boolean addBookDetail(Book bookObj) {return true;}
	public void searchBooks(String genre , String status, String ISBN) {} // return type not fixed
	
}
