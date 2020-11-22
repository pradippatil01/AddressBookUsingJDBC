package com.bridgelabz.addressbook;

import java.sql.*;
import java.time.LocalDate;
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

    public List<AddressBookData> readAddressBookDataForDateRange(LocalDate startDate, LocalDate endDate) throws InvalidException {
        String sql = String.format("SELECT * FROM PERSONDATA WHERE DATEADDED BETWEEN '%S' AND '%S';",
                Date.valueOf(startDate), Date.valueOf(endDate));
        return this.getAddressBookDataFromDB(sql);
    }

    public List<AddressBookData> readAddressBookDataForCity(String city) throws InvalidException {
        String sql = String.format("SELECT * FROM PERSONDATA WHERE CITY = '%S';", city);
        return this.getAddressBookDataFromDB(sql);
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
            throw new InvalidException("JDBC_UPDATE_ERROR",
                    InvalidException.ExceptionType.SQL_EXCEPTION);
        }
    }

    public void addNewAddressBookData(String firstName, String lastName, String city, String state,
                                      int zipCode, long phoneNumber, LocalDate date) throws InvalidException {
        int person_id = -1;
        AddressBookData addressBookData = null;
        String sql = String.format("Insert into personData(firstName,lastName,city,state,zipcode,phoneNumber,dateAdded)" +
                "values('%s','%s','%s','%s',%s,%s,'%s')", firstName, lastName, city, state, zipCode, phoneNumber, Date.valueOf(date));
        try (Connection connection = dbConnection.getConnection()) {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
            if (rowAffected == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) person_id = resultSet.getInt(1);
            }
            addressBookData = new AddressBookData(person_id, firstName, lastName, city, state, zipCode, phoneNumber, date);
        } catch (SQLException | InvalidException exception) {
            throw new InvalidException("DATA_NOT_ADDED",
                    InvalidException.ExceptionType.SQL_EXCEPTION);
        }
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

