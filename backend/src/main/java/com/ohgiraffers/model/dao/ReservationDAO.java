package com.ohgiraffers.model.dao;

import com.mysql.cj.xdevapi.Table;
import com.ohgiraffers.model.dto.CustomerDTO;
import com.ohgiraffers.model.dto.EmployeeDTO;
import com.ohgiraffers.model.dto.ReservationDTO;
import com.ohgiraffers.model.dto.TableDTO;

import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;


public class ReservationDAO {

    private Properties prop = new Properties();
    EmployeeDTO employee = null;
    CustomerDTO customer = null;
    TableDTO table = null;
    ReservationDTO reservation = null;

    public ReservationDAO(){

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/reservation-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 전체 손님 조회
    public void allCustomerInfo(Connection con){
        Statement stmt = null;
        ResultSet rset = null;
        List<CustomerDTO> allCustomer = null;

        String query = prop.getProperty("selectAllCustomer");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            allCustomer = new ArrayList<>();
            while(rset.next()){
                CustomerDTO customerDTO = new CustomerDTO();

                customerDTO.setCustomerId(rset.getInt("CUSTOMER_ID"));
                customerDTO.setName(rset.getString("NAME"));
                customerDTO.setPhone(rset.getString("PHONE"));
                customerDTO.setAge(rset.getInt("AGE"));
                customerDTO.setEmail(rset.getString("EMAIL"));

                allCustomer.add(customerDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
        }
        for(CustomerDTO allCustomerInfo : allCustomer){

            System.out.println("allCustomerInfo = " + allCustomerInfo);
        }
    }

    // 마지막 예약번호 조회
    public int selectLastReservationId(Connection con){

        Statement stmt = null;
        ResultSet rset = null;

        int maxReservationId = 0;

        String query = prop.getProperty("selectLastReservationId");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()){
                maxReservationId = rset.getInt("MAX(A.RESERVATION_ID)");
//                System.out.println(maxReservationId);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }
        return maxReservationId;
    }

    // 전체 직원 조회
    public void selectAllEmployee(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;

        EmployeeDTO employee =null;
        List<EmployeeDTO> employeeList = null;

        String query = prop.getProperty("selectAllEmployeeList");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            employeeList = new ArrayList<>();

            while (rset.next()) {
                employee = new EmployeeDTO();
                employee.setEmployeeId((rset.getInt("EMPLOYEE_ID")));
                employee.setName((rset.getString("NAME")));
                employee.setPhone((rset.getString("PHONE")));

                employeeList.add(employee);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        } for (EmployeeDTO  employeeDTO : employeeList) {
            System.out.println(employeeDTO);
        }
    }

    // 전체 테이블 정보 확인
    public void  allTableInfo(Connection con){
        Statement stmt = null;
        ResultSet rset = null;

        List<TableDTO> allTable = null;
        String query = prop.getProperty("AllTableInfo");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            allTable = new ArrayList<>();

            while(rset.next()){
                TableDTO tableDTO = new TableDTO();

                tableDTO.setTableId(rset.getInt("TABLE_ID"));
                tableDTO.setCapacity(rset.getInt("CAPACITY"));
                tableDTO.setLocation(rset.getString("LOCATION"));

                allTable.add(tableDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
        }
        for(TableDTO allTableInfo : allTable){
            System.out.println("allTableInfo = " + allTableInfo);
        }


    }

    // 전체 예약 조회
    public void allReservationInfo(Connection con){
        Statement stmt = null;
        ResultSet rset = null;

        List<ReservationDTO> allReservation = null;

        String query = prop.getProperty("selectAllReservation");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            allReservation = new ArrayList<>();

            while(rset.next()){
                ReservationDTO reservationDTO = new ReservationDTO();

                reservationDTO.setReservationId(rset.getInt("RESERVATION_ID"));
                reservationDTO.setReservationDate(rset.getString("RESERVATION_DATE"));
                reservationDTO.setReservationTime(rset.getTime("RESERVATION_TIME"));
                reservationDTO.setGuestCount(rset.getInt("GUEST_COUNT"));
                reservationDTO.setGuestName(rset.getString("GUEST_NAME"));
                reservationDTO.setNote(rset.getString("NOTE"));
                reservationDTO.setTableId(rset.getInt(("TABLE_ID")));
                reservationDTO.setEmployeeId(rset.getInt("EMPLOYEE_ID"));

                allReservation.add(reservationDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
        }
        for(ReservationDTO allReservationInfo : allReservation){
            System.out.println("allReservationInfo = " + allReservationInfo);
        }
    }
    // 예약 추가

    public int addReservation(Connection con) {
        ReservationDAO reservationDAO = new ReservationDAO();
        int maxReservationId = reservationDAO.selectLastReservationId(con);

        PreparedStatement pstmt = null;
        int result = 0;

        ReservationDTO reservationDTO = new ReservationDTO();


        Scanner sc = new Scanner(System.in);
        System.out.print("예약할 날짜를 입력해주세요 : ");
        String reservationDate = sc.nextLine();
        System.out.print("예약할 시간을 입력해주세요 : ");
        Time reservationTime = Time.valueOf(sc.next());
        System.out.print("예약 인원을 입력해주세요 : ");
        int guestCount = sc.nextInt();
        System.out.print("예약하는 손님의 이름을 입력해주세요 : ");
        String guestName = sc.next();
        System.out.print("메모 사항을 입력하세요 : ");
        String note = sc.next();
        System.out.print("테이블 번호를 입력하세요 : ");
        int tableId = sc.nextInt();
        System.out.print("직원 번호를 입력하세요(1,2,3,4,5) : ");
        int employeeId = sc.nextInt();

        int reservationId = maxReservationId + 1;

        try {
            String query = prop.getProperty("addReservation");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, reservationDate);
            pstmt.setTime(2,reservationTime );
            pstmt.setInt(3,guestCount);
            pstmt.setString(4,guestName);
            pstmt.setString(5,note );
            pstmt.setInt(6,tableId);
            pstmt.setInt(7,employeeId );


            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        if(result > 0){
            System.out.println("새로운 예약 추가 성공");
            System.out.println("예약 내용을 확인하시겠습니까?");
            System.out.println("1. 예, 2. 아니오");
            int num1 = sc.nextInt();
            if(num1 == 1){
                allReservationInfo(con);
            }
        } else {
            System.out.println("새로운 예약 추가 실패");
        }
        return result;
    }
    // --------------------------------------------------------------------------
    // 마지막 고객
    public int selectLastCustomerId(Connection con){
        Statement stmt = null;
        ResultSet rset = null;

        int maxCustomerId = 0;

        String query = prop.getProperty("selectLastCustomerId");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()){
                maxCustomerId = rset.getInt("MAX(B.CUSTOMER_ID)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
        }
        return maxCustomerId;
    }
    // 고객 정보 추가
    public int addCustomer(Connection con){
        ReservationDAO reservationDAO = new ReservationDAO();
        int maxCustomerId = reservationDAO.selectLastCustomerId(con);

        PreparedStatement pstmt = null;
        int result = 0;
        CustomerDTO customerDTO = new CustomerDTO();

        Scanner sc = new Scanner(System.in);
        System.out.println("고객의 이름을 입력해주세요 : ");
        String customerName = sc.nextLine();
        System.out.println("고객의 번호를 입력해주세요 : ");
        sc.next();
        String customerPhone = sc.nextLine();
        System.out.println("고객의 나이를 입력해주세요 : ");
        int customerAge = sc.nextInt();
        System.out.println("고객의 이메일을 입력해주세요 : ");
        sc.next();
        String customerEmail = sc.nextLine();

        int customerId = maxCustomerId + 1 ;

        try {
            String query = prop.getProperty("addCustomer");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, customerName);
            pstmt.setString(2,customerPhone );
            pstmt.setInt(3,customerAge);
            pstmt.setString(4,customerEmail);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        if(result > 0){
            System.out.println("새로운 고객 정보 추가 성공");
            System.out.println("고객 정보를 확인하시겠습니까?");
            System.out.println("1. 예, 2. 아니오");
            int num2 = sc.nextInt();
            if(num2 == 1){
                allCustomerInfo(con);
            }
        } else {
            System.out.println("고객 정보 추가 실패");
        }
        return result;
    }

    public CustomerDTO selectedCustomerName(Connection con, String selectedCustomerName){
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        CustomerDTO customerDTO = null;

        String query = prop.getProperty("selectCustomerByName");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,selectedCustomerName);

            rset = pstmt.executeQuery();
            if(rset.next()){
                customerDTO = new CustomerDTO();

                customerDTO.setCustomerId(rset.getInt("CUSTOMER_ID"));
                customerDTO.setName(rset.getString("NAME"));
                customerDTO.setPhone(rset.getString("PHONE"));
                customerDTO.setAge(rset.getInt("AGE"));
                customerDTO.setEmail(rset.getString("EMAIL"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerDTO;
    }

    // 예약 취소
    public void cancelReservation(Connection con){
        Scanner sc = new Scanner(System.in);
        PreparedStatement pstmt = null;
        int result = 0;

        CustomerDTO customerDTO = new CustomerDTO();
        allReservationInfo(con);

        System.out.println("예약 취소할 손님의 이름을 입력해주세요 : ");
        String selectedName = sc.nextLine();
//        CustomerDTO customerDTO = selectedCustomerName(con, selectedName);
        customerDTO.setName(selectedName);

        String query = prop.getProperty("cancelReservation");
        try {
            pstmt = con.prepareStatement(query);
//            customerDTO.setName(selectedName);
            pstmt.setString(1, customerDTO.getName());


            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        if(result > 0 ){
            System.out.println("예약 취소를 성공했습니다.");
        } else {
            System.out.println("예약 취소를 실패했습니다.");
        }

    }


}
