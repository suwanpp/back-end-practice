package com.ohgiraffers.model.dto;

import java.sql.Time;

public class ReservationDTO {

    private int reservationId;
    private String reservationDate;
    private Time reservationTime;
    private int guestCount;
    private String guestName;
    private String note;
    private int tableId;
    private int employeeId;

    public ReservationDTO(){}

    public ReservationDTO(int reservationId, String reservationDate, Time reservationTime, int guestCount, String guestName, String note, int tableId, int employeeId) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.guestCount = guestCount;
        this.guestName = guestName;
        this.note = note;
        this.tableId = tableId;
        this.employeeId = employeeId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Time getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Time reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "예약 번호=" + reservationId +
                ", 예약 날짜 = " + reservationDate + '\'' +
                ", 예약 시간 = " + reservationTime +
                ", 예약 손님 인원 = " + guestCount +
                ", 예약 손님 = " + guestName + '\'' +
                ", 메모 = " + note + '\'' +
                ", 테이블 번호 = " + tableId +
                ", 직원 번호 = " + employeeId +
                '}';
    }
}
