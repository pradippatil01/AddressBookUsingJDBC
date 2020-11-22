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
}

