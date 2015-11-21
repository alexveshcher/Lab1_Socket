package dao;
import vo.Book;
import vo.Order;

import java.sql.*;
import java.util.List;


public interface DAO {
    public Connection getConnection() throws SQLException;

    public Book readBook(int key) throws SQLException;
    //public List<Book> getAllBooks() throws SQLException;
    public List<Book> searchBook(String searchWord) throws SQLException;

    public void makeOrder(Order ord) throws SQLException;
    public List<Order> getUncompleted() throws SQLException;
}
