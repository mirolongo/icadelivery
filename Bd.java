package com.icadelivery;

import java.sql.*;
import java.util.ArrayList;

class Bd {
    Kund kund = new Kund();
    int id;
    String namn;
    String adress;
    String telefonnumer;
    String username = "root";
    String pwd = "Brasil2020";
    String url = "jdbc:mysql://localhost:3306/ica?useTimezone=true&serverTimezone=UTC";
    String query = "select * from kunder;";
    ResultSet rs = null;
    ResultSet result = null;
    private void update(String query) throws SQLException {
        Statement myStatement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, pwd);
            myStatement = connection.createStatement();
            myStatement.executeUpdate(query);
        } finally {
            myStatement.close();
            connection.close();
        }
    }
    public void insert(Order order) throws Exception {
        try {
            String query = String.format("insert into `order` (kund_id,delivery_id) values (%s,%s);", order.kundId,
            order.deliveryId);
            if (order.deliveryId == DeliveryType.LEVERANS.id) {
                Object kund_id;
                Object delivery_id;
                Object budfirmor_id;
                query = String.format("insert into `order` (kund_id,delivery_id,budfirmor_id) values (%s,%s,%s);",
                order.kundId, order.deliveryId, order.budFirmorId);
            }
            int orderId = insertAndReturnId(query);
            order.id = orderId;
            String insertBytStateses = queryForByt(order);
            update(insertBytStateses);
            String orderItems = queryForOrderItems(order);
            update(orderItems);
        } finally {
        }
    }
    private String queryForByt(Order order) {
        Object orderstatus_id;
        Object order_id;
        StringBuilder stringBuilder = new StringBuilder("insert into bytstatus (order_id, orderstatus_id)Object values; values ");
        String value = "(" + order.id + ",%s)";
        ArrayList<String> values = new ArrayList<String>();
        for (OrderStatus a : order.statuses) {
            values.add(String.format(value, a.status.id()));
        }
        stringBuilder.append(String.join(",", values));
        stringBuilder.append(";");
        return stringBuilder.toString();
    }
    private String queryForOrderItems(Order order) {
        String value;
        StringBuilder stringBuilder = new StringBuilder("insert into beställningar (varor_id, antal, order_id) values ");
                value = "(%s,%s," + order.id + ")";
        ArrayList<String> values = new ArrayList<String>();
        for (BeställningsArtikel a : order.artiklar) {
            values.add(String.format(value, a.varorId, a.antal));
        }
        stringBuilder.append(String.join(",", values));
        stringBuilder.append(";");
        return stringBuilder.toString();
    }
    private int insertAndReturnId(String query) throws Exception {
        Statement myStatement = null;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, pwd);
            myStatement = connection.createStatement();
            myStatement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = myStatement.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } finally {
            myStatement.close();
            connection.close();
        }
    }
}
