package client;

import server.Server;
import vo.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.stream.Stream;


public class Client {


    public static final String GET_ALL_BOOKS = "getAllBooks";
    public static final String GET_ALL_ORDERS = "getAllOrders";
    public static final String FORM_AN_ORDER = "formAnOrder";
    public static final String UPDATE_ORDER = "updateOrder";
    public static final String LOGIN = "login";
    public static final String LOCALHOST = "localhost";
    public static final String GET_USERS_ORDERS = "getUser'sOrders";
    public static final String REGISTRATION = "REGISTRATION";
    public static final String SET_BOOK = "SETBOOK";
    public static final String CHECK_LOGIN = "CHECK_LOGIN";

    public static final String MAKE_ORDER = "makeOrder";


    public static TreeSet<Book> getBooks() {
        TreeSet<Book> books = null;
        try {
            try (Socket socket = new Socket(LOCALHOST, Server.PORT);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ) {  //STARTING REQUEST
                objectOutputStream.writeObject(new Object[]{GET_ALL_BOOKS});
                objectOutputStream.flush();
                //TRYING RECEIVE response
                books = (TreeSet<Book>) objectInputStream.readObject();
                return books;
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }


    public static void formAnOrder(final Order order) throws IOException {

        try (Socket socket = new Socket(LOCALHOST, Server.PORT);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ) {
            objectOutputStream.writeObject(new Object[]{FORM_AN_ORDER, order});
            objectOutputStream.flush();

        }
    }

    public static void makeOrder(final Order order) throws IOException {

        try (Socket socket = new Socket(LOCALHOST, Server.PORT);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ) {
            objectOutputStream.writeObject(new Object[]{MAKE_ORDER, order});
            objectOutputStream.flush();
        }
    }



    public static void setBook(final Book book) throws IOException {
        try (Socket socket = new Socket(LOCALHOST, Server.PORT);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ) {
            objectOutputStream.writeObject(new Object[]{SET_BOOK, book});
            objectOutputStream.flush();

        }
    }


}


