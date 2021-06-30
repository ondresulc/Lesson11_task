package com.company;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseFunctions implements GoodsMethods  {
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabaseforlesson11task";
    static final String USER = "root";
    static final String PASS = "Test_Pwd234";
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Override
    public Item loadItemById(Integer id) {
        Item tmpItem = null;

        try{
            getConnectionToDatabase();
            System.out.println();

            System.out.println("Getting records with condition ...");
            System.out.println("==================================");
            String sql = "SELECT id, name, partNo, price FROM item" +
                    " WHERE id =" + id;
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            Item item = new Item(resultSet.getInt("id"), resultSet.getString("partNo"), resultSet.getString("name"), resultSet.getBigDecimal("price"));
            tmpItem = item;
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tmpItem;
    }

    private void getConnectionToDatabase() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        connection.setAutoCommit(false);
        statement = connection.createStatement();
    }

    @Override
    public void deleteAllOutOfStockItems() {
        try{
            getConnectionToDatabase();
            String sql = "DELETE FROM item WHERE numberInStock = 0";
            PreparedStatement myStatement = connection.prepareStatement(sql);
            myStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Item> loadAllAvailableItems() {
        List<Item> allAvailableItems = new ArrayList<>();
        System.out.println("\n" + "All available items are these: ...");
        System.out.println("=========================================");

        try {
            getConnectionToDatabase();
            resultSet = statement.executeQuery("SELECT * FROM item WHERE numberInStock <> 0");

            while (resultSet.next()) {
                int itemId = resultSet.getInt("id");
                String itemPartNo = resultSet.getString("partNo");
                String itemSerialNo = resultSet.getString("serialNo");
                String itemName = resultSet.getString("name");
                String itemDescription = resultSet.getString("description");
                int itemNumberInStock = resultSet.getInt("numberInStock");
                BigDecimal itemPrice = resultSet.getBigDecimal("price");

                Item item = new Item();
                item.setId(itemId);
                item.setPartNo(itemPartNo);
                item.setSerialNo(itemSerialNo);
                item.setName(itemName);
                item.setDescription(itemDescription);
                item.setNumberInStock(itemNumberInStock);
                item.setPrice(itemPrice);

                allAvailableItems.add(item);
                System.out.println("id: " +itemId +" | "
                        +itemPartNo +" | "
                        +itemSerialNo +" | "
                        +itemName+" | " +  itemDescription +" | "
                        +itemNumberInStock + " pcs on stock | "
                        +itemPrice +" CZK");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void saveItem(Item item) {
        try {
            getConnectionToDatabase();

            String sql = "INSERT INTO item (partNo, serialNo, name, description, numberInStock, price) VALUES (?,?,?,?,?,?)";
            PreparedStatement myStatement = connection.prepareStatement(sql);
            myStatement.setString(1, item.getPartNo());
            myStatement.setString(2, item.getSerialNo());
            myStatement.setString(3, item.getName());
            myStatement.setString(4, item.getDescription());
            myStatement.setInt(5, item.getNumberInStock());
            myStatement.setBigDecimal(6, item.getPrice());

            myStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            }
    }

    @Override
    public void updatePrice(Integer id, BigDecimal newPrice) {

        try {
            getConnectionToDatabase();
            String sql = "UPDATE item SET price = ? WHERE id = ?";
            PreparedStatement myStatement = connection.prepareStatement(sql);
            myStatement.setBigDecimal(1, newPrice);
            myStatement.setInt(2, id);
            myStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
