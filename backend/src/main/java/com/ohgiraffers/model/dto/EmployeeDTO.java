package com.ohgiraffers.model.dto;

public class EmployeeDTO {


    private int employeeId;
    private String name;
    private String phone;

    public EmployeeDTO(){}
    public EmployeeDTO(int employeeId, String name, String phone) {
        this.employeeId = employeeId;
        this.name = name;
        this.phone = phone;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "직원번호 = " + employeeId +
                ", 직원이름 = " + name + '\'' +
                ", 직원 연락처 = " + phone + '\'' +
                '}';
    }
}


