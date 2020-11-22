package com.bridgelabz.addressbook;


import java.time.LocalDate;
import java.util.Objects;

public class AddressBookData {
    public int personId;
    public String firstName;
    public String lastName;
    public String city;
    public String state;
    int zipCode;
    long phoneNUmber;
    public LocalDate startDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressBookData that = (AddressBookData) o;
        return personId == that.personId &&
                zipCode == that.zipCode &&
                phoneNUmber == that.phoneNUmber &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(city, that.city) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName, city, state, zipCode, phoneNUmber);
    }



    @Override
    public String toString() {
        return "AddressBookData{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", phoneNUmber=" + phoneNUmber +
                '}';
    }


    public AddressBookData(int personId, String firstName, String lastName, String city, String state, int zipCode, long phoneNUmber) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNUmber = phoneNUmber;
    }

    public AddressBookData(int personId, String firstName, String lastName, String city, String state, int zipCode, long phoneNUmber, LocalDate startDate) {
        this(personId, firstName, lastName, city, state, zipCode, phoneNUmber);
        this.startDate=startDate;
    }
}