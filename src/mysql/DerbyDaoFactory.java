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

	public Connection getConnection()  {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(jdbcURL, "app", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public BookDao getBookDao() {
		return new DerbyBookDao(getConnection());
	}

	@Override
	public OrderDao getOrderDao() {
		return new DerbyOrderDao(getConnection());
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
			BookDao dao = daoFactory.getBookDao();
			list = dao.getAll();
			System.out.println(dao.search("o").toString());
		}
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
	}
}
