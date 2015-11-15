package dao;

import java.sql.*;

public interface DaoFactory {

	public Connection getConnection() throws SQLException;
	
    public BookDao getBookDao(Connection connection);
    public OrderDao getOrderDao(Connection connection);
}
