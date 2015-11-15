package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Book;
import vo.Order;
import dao.OrderDao;

public class DerbyOrderDao implements OrderDao {
	private Connection connection;

	public DerbyOrderDao(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void create(Order ord) throws SQLException {
		String sql = "INSERT INTO APP.ORDERS (BOOK_ID, STUDENT_ID, COMPLETED) VALUES ("
				//+ord.getId()+","
				+ord.getBook().getId()+","+
				+ord.getStudent()+","
				+ord.isCompleted()+")";//"2, 1, 4851548, false);";
		PreparedStatement stm = connection.prepareStatement(sql);
		stm.executeUpdate();
		stm.close();
	}
	

	@Override
	public Order read(int key) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Order Order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Order Order) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Order> getAll() throws SQLException {
		String sql = "SELECT * FROM orders";
		PreparedStatement stm = connection.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		List<Order> list = new ArrayList<Order>();
		Book b;
		while (rs.next()) {
			Order ord = new Order();
			ord.setId(rs.getInt("id"));
			ord.setStudent(rs.getInt("student"));
			ord.setCompleted(rs.getBoolean("completed"));
			list.add(ord);
		}
		return list;
	}

	@Override
	public List<Order> getUncompleted() throws SQLException {
		String sql = "SELECT * FROM orders WHERE COMPLETED=FALSE ";
		PreparedStatement stm = connection.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		List<Order> list = new ArrayList<Order>();
		Book b;
		while (rs.next()) {
			Order ord = new Order();
			ord.setId(rs.getInt("id"));
			//ord.setBook(rs.getInt("ID"));
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
		// + "OR PUBLISHINGYEAR LIKE %"+Integer.parseInt(searchWord)+"%";

		PreparedStatement stm = connection.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		list = new ArrayList<Order>();
		while (rs.next()) {
			Order ord = new Order();
			ord.setId(rs.getInt("id"));
			// b.setTitle(rs.getString("title"));
			// b.setAuthors(rs.getString("authors"));
			// b.setYear(rs.getInt("publishingyear"));

			list.add(ord);
		}
		return list;
	}

}
