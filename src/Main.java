import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import mysql.DerbyDaoFactory;
import dao.BookDao;
import dao.DaoFactory;
import ui.MyFrame;
import vo.Book;

public class Main {
	public static void main(String[] args) throws SQLException {
		new MyFrame();
		DaoFactory daoFactory = new DerbyDaoFactory();
		List<Book> list;
		try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app","root")) {
			BookDao dao = daoFactory.getBookDao(conn);
			list = dao.getAll();
		}
	}
}
