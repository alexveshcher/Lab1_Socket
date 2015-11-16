package dao;

import java.sql.*;

public interface DaoFactory {

	public Connection getConnection() throws SQLException;
	
    public BookDao getBookDao();
    public OrderDao getOrderDao();
}
