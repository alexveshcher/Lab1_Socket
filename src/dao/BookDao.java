package dao;

import java.sql.SQLException;
import java.util.List;

import vo.Book;

public interface BookDao {
	public Book create();
	public Book read(int key) throws SQLException;
	public void update(Book book);
	public void delete(Book book);
	public List<Book> getAllBooks() throws SQLException;
	public List<Book> search(String searchWord) throws SQLException;
}
