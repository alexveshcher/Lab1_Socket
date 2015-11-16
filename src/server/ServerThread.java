package server;

import client.Client;
import dao.DaoFactory;
import dao.OrderDao;
import mysql.DerbyDaoFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;


public class ServerThread implements Runnable {
    @Override
    public void run() {

    }
/*
    private final Socket socket;
    DaoFactory daoFactory = new DerbyDaoFactory();

    OrderDao dao = daoFactory.getOrderDao();

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

            if (request.equals(Client.GET_ALL_BOOKS)) {

                try {
                    objectOutputStream.writeObject(dao.getAllBooks());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                objectOutputStream.flush();

            }
            if (request.equals(Client.GET_ALL_ORDERS)) {

                HashMap<Book, User> orders = dao.getAllOrders();
                objectOutputStream.writeObject(orders);
                objectOutputStream.flush();

            }
            if (request.equals(Client.FORM_AN_ORDER)) {
                final Order order = (Order) reObject[1];
                dao.formAnOrder(order);
            }
            if (request.equals(Client.LOGIN)) {
                final User user = (User) reObject[1];
                User result = dao.login(user);
                objectOutputStream.writeObject(result);
                objectOutputStream.flush();
            }
            if (request.equals(Client.UPDATE_ORDER)) {
                final Order order = (Order) reObject[1];
                dao.updateOrderStatus(order);
            }
            if (request.equals(Client.GET_USERS_ORDERS)) {
                final User user = (User) reObject[1];
                HashMap<Book, Boolean> orders = dao.getUserOrders(user);
                objectOutputStream.writeObject(orders);
                objectOutputStream.flush();
            }
            if (request.equals(Client.REGISTRATION)) {
                final User user = (User) reObject[1];
                dao.setRegistration(user);
            }
            if (request.equals(Client.SET_BOOK)) {
                final Book book = (Book) reObject[1];
                dao.setBook(book);
            }
            if (request.equals(Client.CHECK_LOGIN)) {
                final User user = (User) reObject[1];
                objectOutputStream.writeBoolean(dao.checkLogin(user));
                objectOutputStream.flush();
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
    }*/
}
