package com.bridgelabz.addressbook;

import org.junit.*;
import java.util.*;

public class AddressBookTestCase {
    private static final String SQL_READ = "SELECT * FROM PERSONDATA";
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
}
