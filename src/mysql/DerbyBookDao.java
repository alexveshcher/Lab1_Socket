package mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vo.Book;
import dao.BookDao;

public class DerbyBookDao implements BookDao {
	private Connection connection;

	@Override
	public Book create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book read(int key) throws SQLException {
		Book b = new Book();
		String sql = "SELECT * FROM APP.BOOKS\n"
				+ "WHERE ID = "+key+"\n";
		//+ "OR PUBLISHINGYEAR LIKE %"+Integer.parseInt(searchWord)+"%";

		PreparedStatement stm = connection.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();

		while (rs.next()) {
			b.setId(rs.getInt("id"));
			b.setTitle(rs.getString("title"));
			b.setAuthors(rs.getString("authors"));
			b.setYear(rs.getInt("publishingyear"));

		}
		return b;
	}
	/*
	@Override
	public Book read(int key) throws SQLException {
		String sql = "SELECT * FROM Books WHERE ID LIKE '%"+key+"%'";
		Book b = new Book();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, key);
			ResultSet rs = stm.executeQuery();
			rs.next();
			b.setId(rs.getInt("id"));
			// g.setDepartment(rs.getString("department"));
		}
		return b;
	}
	*/

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> getAllBooks() throws SQLException {
		String sql = "SELECT * FROM APP.BOOKS";
		PreparedStatement stm = connection.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		List<Book> list = new ArrayList<Book>();
		while (rs.next()) {
			Book b = new Book();
			b.setId(rs.getInt("id"));
			b.setTitle(rs.getString("title"));
			b.setAuthors(rs.getString("authors"));
			b.setYear(rs.getInt("publishingyear"));

			list.add(b);
		}
		return list;
	}

	public DerbyBookDao(Connection connection) {
		this.connection = connection;
	}

	public List<Book> search(String searchWord) throws SQLException {
		List<Book> list = null;
		String sql = "SELECT * FROM APP.BOOKS\n" 
				+ "WHERE AUTHORS LIKE '%"+searchWord+"%'\n"
				+ "OR TITLE LIKE '%"+searchWord+"%'\n";
				//+ "OR PUBLISHINGYEAR LIKE %"+Integer.parseInt(searchWord)+"%";

		PreparedStatement stm = connection.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		list = new ArrayList<Book>();
		while (rs.next()) {
			Book b = new Book();
			b.setId(rs.getInt("id"));
			b.setTitle(rs.getString("title"));
			b.setAuthors(rs.getString("authors"));
			b.setYear(rs.getInt("publishingyear"));

			list.add(b);
		}
		return list;
	}

}
