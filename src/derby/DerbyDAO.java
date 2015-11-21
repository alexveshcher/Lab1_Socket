package derby;

import dao.DAO;
import vo.Book;
import vo.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DerbyDAO implements DAO {

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
    public Book readBook(int key) throws SQLException {
        Book b = new Book();
        String sql = "SELECT * FROM APP.BOOKS\n"
                + "WHERE ID = "+key+"\n";
        //+ "OR PUBLISHINGYEAR LIKE %"+Integer.parseInt(searchWord)+"%";

        PreparedStatement stm = getConnection().prepareStatement(sql);
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
    public List<Book> getAllBooks() throws SQLException {
        String sql = "SELECT * FROM APP.BOOKS";
        PreparedStatement stm = getConnection().prepareStatement(sql);
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
*/
    public List<Book> searchBook(String searchWord) throws SQLException {
        List<Book> list = null;
        String sql = "SELECT * FROM APP.BOOKS\n"
                + "WHERE AUTHORS LIKE '%"+searchWord+"%'\n"
                + "OR TITLE LIKE '%"+searchWord+"%'\n";
        //+ "OR PUBLISHINGYEAR LIKE %"+Integer.parseInt(searchWord)+"%";

        PreparedStatement stm = getConnection().prepareStatement(sql);
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





    @Override
    public void makeOrder(Order ord) throws SQLException {
        String sql = "INSERT INTO APP.ORDERS (BOOK_ID, STUDENT_ID, COMPLETED) VALUES ("
                +ord.getBook().getId()+","+
                +ord.getStudent()+","
                +ord.isCompleted()+")";
        PreparedStatement stm = getConnection().prepareStatement(sql);
        stm.executeUpdate();
        stm.close();
    }





    @Override
    public List<Order> getUncompleted() throws SQLException {
        String sql = "SELECT * FROM orders WHERE COMPLETED=FALSE ";
        PreparedStatement stm = getConnection().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        List<Order> list = new ArrayList<Order>();
        Book b;

        while (rs.next()) {
            Order ord = new Order();
            ord.setId(rs.getInt("id"));
            b = readBook(rs.getInt("BOOK_ID"));
            ord.setBook(b);
            ord.setStudent(rs.getInt("STUDENT_ID"));
            ord.setCompleted(rs.getBoolean("completed"));
            list.add(ord);
        }
        return list;
    }

    public List<Order> search(String searchWord) throws SQLException {
        List<Order> list = null;
        String sql = "SELECT * FROM APP.ORDERS\n" + "WHERE ID LIKE '%"
                + searchWord + "%'\n" + "OR BOOK_ID LIKE '%" + searchWord
                + "%'\n";

        PreparedStatement stm = getConnection().prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        list = new ArrayList<Order>();
        while (rs.next()) {
            Order ord = new Order();
            ord.setId(rs.getInt("id"));
            list.add(ord);
        }
        return list;
    }
}
