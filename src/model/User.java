package model;

import javafx.beans.property.SimpleStringProperty;

public class User {
    SimpleStringProperty Name;
    SimpleStringProperty Lastname;
    SimpleStringProperty Age;
    SimpleStringProperty Address;
    SimpleStringProperty Phone;
    SimpleStringProperty Action;

    public User(){}

    public User(String name, String lastname, String age, String address, String phone, String action) {
        Name = new SimpleStringProperty(name);
        Lastname = new SimpleStringProperty(lastname);
        Age = new SimpleStringProperty(age);
        Address = new SimpleStringProperty(address);
        Phone = new SimpleStringProperty(phone);
        Action = new SimpleStringProperty(action);
    }

    public String getName() {
        return Name.get();
    }


    public void setName(String name) {
        this.Name.set(name);
    }

    public String getLastname() {
        return Lastname.get();
    }



    public void setLastname(String lastname) {
        this.Lastname.set(lastname);
    }

    public String getAge() {
        return Age.get();
    }




    public void setAge(String age) {
        this.Age.set(age);
    }

    public String getAddress() {
        return Address.get();
    }



    public void setAddress(String address) {
        this.Address.set(address);
    }

    public String getPhone() {
        return Phone.get();
    }



    public void setPhone(String phone) {
        this.Phone.set(phone);
    }

    public String getAction() {
        return Action.get();
    }



    public void setAction(String action) {
        this.Action.set(action);
    }
}

