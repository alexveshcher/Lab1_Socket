package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import vo.Order;

public interface OrderDao {
	public void create(Order ord) throws SQLException;
	public Order read(int key) throws SQLException;
	public void update(Order Order);
	public void delete(Order Order);
	public List<Order> getAll() throws SQLException;
	public List<Order> search(String searchWord) throws SQLException;
    public List<Order> getUncompleted() throws SQLException;
}
