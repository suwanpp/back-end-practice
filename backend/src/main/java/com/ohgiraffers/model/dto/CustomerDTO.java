package com.ohgiraffers.model.dto;

public class CustomerDTO {

    private int CustomerId;
    private String name;
    private String phone;
    private int age;
    private String email;

    public CustomerDTO(){}

    public CustomerDTO(int customerId, String name, String phone, int age, String email) {
        CustomerId = customerId;
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.email = email;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "CustomerId=" + CustomerId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
