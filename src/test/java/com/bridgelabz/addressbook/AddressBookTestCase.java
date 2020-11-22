package com.bridgelabz.addressbook;

import org.junit.*;

import java.util.*;

public class AddressBookTestCase {
    private static final String SQL_READ = "SELECT * FROM PERSONDATA";
    private static final String SQL_READ_WRONG_QUERY = "SELECT * FROM PERSONDATA2";
    private static final String SQL_UPDATE = "UPDATE PERSONDATA " +
                                             "SET CITY=? ,STATE=?, ZIPCODE=?, WHERE FIRSTNAME=? ";

    AddressBookDB addressBookDB;

    @Before
    public void setUp() {
        addressBookDB = new AddressBookDB();
    }

    @Test
    public void givenAddressBookDB_whenRetrievedData_MatchCountWithDB() throws InvalidException {
        List<AddressBookData> addressBookData = addressBookDB.readAddressBookData(SQL_READ);
        Assert.assertEquals(4, addressBookData.size());
    }

    @Test
    public void givenAddressBookDB_whenPassWrongTableNameAtRetrieve_ShouldThrowException() {
        try {
            List<AddressBookData> addressBookData = addressBookDB.readAddressBookData(SQL_READ_WRONG_QUERY);
        } catch (InvalidException invalidException) {
            System.out.println(invalidException.getMessage());
            Assert.assertEquals("JDBC_TABLE_NAME_WRONG", invalidException.getMessage());
        }
    }

    @Test
    public void givenAddressBookDB_whenUpdated_syncWithDB() throws InvalidException {
        int i = addressBookDB.updateAddressBookData(SQL_UPDATE);
        boolean result = addressBookDB.checkAddressBookDataSyncWithDB("pradip");
        Assert.assertTrue(result);

    }
}
