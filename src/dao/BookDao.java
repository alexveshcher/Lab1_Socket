package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import vo.Book;


public interface BookDao extends Remote {
	public Book create() throws RemoteException;
	public Book read(int key) throws SQLException,RemoteException;
	public void update(Book book)throws RemoteException;
	public void delete(Book book)throws RemoteException;
	public List<Book> getAllBooks() throws SQLException,RemoteException;
	public List<Book> search(String searchWord) throws SQLException,RemoteException;
}
