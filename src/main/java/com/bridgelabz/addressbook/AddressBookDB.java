package com.bridgelabz.addressbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDB {
    DBConnection dbConnection = new DBConnection();

    public List<AddressBookData> readAddressBookData(String sql) throws InvalidException {
        return this.getAddressBookDataFromDB(sql);
    }

    public List<AddressBookData> getAddressBookDataFromDB(String sql) throws InvalidException {
        List<AddressBookData> addressBookDataList = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int personId = resultSet.getInt("personId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                int zipCode = resultSet.getInt("zipCode");
                long phoneNUmber = resultSet.getLong("phoneNumber");
                addressBookDataList.add(new AddressBookData(personId, firstName, lastName, city, state, zipCode, phoneNUmber));
            }
            return addressBookDataList;
        } catch (SQLException exception) {
            throw new InvalidException("JDBC_TABLE_NAME_WRONG",
                    InvalidException.ExceptionType.SQL_EXCEPTION);
        }
    }

    public int updateAddressBookData(String sql) throws InvalidException {

        try (Connection connection = dbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            String city = "Surat";
            String state = "GJ";
            int zipCode = 564534;
            String firstName = "pradip";
            preparedStatement.setString(1, city);
            preparedStatement.setString(2, state);
            preparedStatement.setInt(3, zipCode);
            preparedStatement.setString(4, firstName);
            int rowAffected = preparedStatement.executeUpdate();
            System.out.printf("Row affected %d%n", rowAffected);
            return rowAffected;
        } catch (SQLException exception) {
          exception.printStackTrace();
        }
        return 0;
    }

    public boolean checkAddressBookDataSyncWithDB(String name) throws InvalidException {
        String sql = "SELECT * FROM PERSONDATA WHERE FIRSTNAME = ?";
        String fetchedName = null;
        try (Connection connection = dbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                fetchedName = rs.getString("firstName");
                return fetchedName.equalsIgnoreCase(name);
            }
            preparedStatement.close();
        } catch (SQLException | InvalidException exception) {
            throw new InvalidException("data not found",
                    InvalidException.ExceptionType.SQL_EXCEPTION);
        }
        return false;
    }
}

