package com.phonebook.apllication;

public class PhoneUserData {
    // 멤버 변수
    private String name;
    private String number;
    private String email;
    private String registrationDate;
    private String modificationDate;

    // default constructor
    public PhoneUserData() {}

    public PhoneUserData(String name, String number, String email, String registrationDate, String modificationDate) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.registrationDate = registrationDate;
        this.modificationDate = modificationDate;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getNumber() {
        return number;
    }
    public String getRegistrationDate() {
        return registrationDate;
    }
    public String getModificationDate() {
        return modificationDate;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setModifyTime(String modificationDate) {
        this.modificationDate = modificationDate;
    }
}


