package server;

import client.Client;
import dao.BookDao;
import dao.DaoFactory;
import dao.OrderDao;
import mysql.DerbyDaoFactory;
import vo.Book;
import vo.Order;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;


public class ServerThread implements Runnable {

    private final Socket socket;
    DaoFactory daoFactory = new DerbyDaoFactory();

    OrderDao orderDao = daoFactory.getOrderDao();
    BookDao bookDao = daoFactory.getBookDao();

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {

            //get request
            final Object[] reObject = (Object[]) objectInputStream.readObject();
            final String request = (String) reObject[0];
            /*
            if (request.equals(Client.GET_ALL_BOOKS)) {

                try {
                    objectOutputStream.writeObject(orderDao.getAllBooks());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                objectOutputStream.flush();

            }
            if (request.equals(Client.GET_ALL_ORDERS)) {

                HashMap<Book, User> orders = orderDao.getAllOrders();
                objectOutputStream.writeObject(orders);
                objectOutputStream.flush();

            }
            if (request.equals(Client.FORM_AN_ORDER)) {
                final Order order = (Order) reObject[1];
                orderDao.formAnOrder(order);
            }*/
            if (request.equals(Client.MAKE_ORDER)) {
                final Order order = (Order) reObject[1];
                orderDao.makeOrder(order);
            }



            //close socket
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
