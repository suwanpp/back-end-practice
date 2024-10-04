package com.ohgiraffers.model.dto;

public class TableDTO {

    private int tableId;
    private int capacity;
    private String location;

    public TableDTO(int tableId, int capacity, String location) {
        this.tableId = tableId;
        this.capacity = capacity;
        this.location = location;
    }

    public TableDTO() {

    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "TableDTO{" +
                "테이블 번호 = " + tableId +
                ", 수용 가능 인원 =" + capacity +
                ", 테이블 위치 ='" + location + '\'' +
                '}';
    }
}
