package mysql;

import java.sql.*;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import vo.Book;
import dao.BookDao;
import dao.DaoFactory;
import dao.OrderDao;

public class DerbyDaoFactory implements DaoFactory {

	final String derbyProtocol = "jdbc:derby://localhost:1527/";
	final String driver = "org.apache.derby.jdbc.ClientDriver";
	final String dbName = "sample";
	final String jdbcURL = derbyProtocol + dbName;

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(jdbcURL, "app", "root");
	}

	@Override
	public BookDao getBookDao(Connection connection) {
		return new DerbyBookDao(connection);
	}

	@Override
	public OrderDao getOrderDao(Connection connection) {
		return new DerbyOrderDao(connection);
	}

	public DerbyDaoFactory() {
		System.setProperty("jdbc.drivers", driver);
	}

	@Test
	public void testGetAll() throws Exception {
		DaoFactory daoFactory = new DerbyDaoFactory();
		List<Book> list;
		try (Connection conn = DriverManager.getConnection(jdbcURL, "app",
				"root")) {
			BookDao dao = daoFactory.getBookDao(conn);
			list = dao.getAll();
			System.out.println(dao.search("o").toString());
		}
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
	}
}
