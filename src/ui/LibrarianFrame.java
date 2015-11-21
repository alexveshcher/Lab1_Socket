package ui;

import dao.DAO;
import derby.DerbyDAO;
import vo.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LibrarianFrame extends JFrame {
    JPanel panel1;
    //JPanel panel2;
    JTextField textField;
    JButton searchButton;
    JTable orderTable;

    int selectedBookRow = -1;

    DAO dao = new DerbyDAO();
    List<Order> orderList;

    public LibrarianFrame() {

        this.setLocation(100, 100);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        panel1 = new JPanel();

        textField = new JTextField(20);
        textField.setText("введіть будь-які дані про книгу");
        panel1.add(textField);

        searchButton = new JButton("searchBook");
        panel1.add(BorderLayout.NORTH, searchButton);


        //івенти
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText("");
            }
        });
        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                initializeTable();

            }
        });


        this.add(panel1);
        this.setVisible(true);
    }

    //initializing table that returns Orders after pressing SEARCH button
    private void initializeTable() {
        if (orderTable != null)
            panel1.remove(orderTable);
        if (orderList != null) {
            orderList = null;
        }
        String[] columnNames = {"Order id", "Book id", "Student id"};

        try {
            orderList = dao.getUncompleted();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String data[][] = new String[orderList.size()][columnNames.length];

        for (int i = 0; i < orderList.size(); i++) {
            data[i][0] = Integer.toString(orderList.get(i).getId());
            data[i][1] = Integer.toString(orderList.get(i).getBook().getId());
            data[i][2] = Integer.toString(orderList.get(i).getStudent());

        }
        orderTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        orderTable.setFillsViewportHeight(true);
        panel1.add(scrollPane);
        //panel1.add(orderTable);

        this.revalidate();
    }


    public static void main(String[] args) {
        new LibrarianFrame();

        // a.setVisible(true);
    }
}
