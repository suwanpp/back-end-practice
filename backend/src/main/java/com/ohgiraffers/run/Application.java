package com.ohgiraffers.run;

import com.ohgiraffers.model.dao.ReservationDAO;
import com.ohgiraffers.model.dto.ReservationDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {

        Connection con = getConnection();
        ReservationDAO registDAO = new ReservationDAO();
        PreparedStatement psmtmt = null;
        int result = 0;
        Scanner sc = new Scanner(System.in);
        boolean a = true;

        int maxReservationId = registDAO.selectLastReservationId(con);

        System.out.println("식당 예약 프로그램");
        while(a){

            System.out.println("번호를 골라주세요");
            System.out.println("1. 관리자");
            System.out.println("2. 손님");
            int code = sc.nextInt();

            if( code == 1){
                boolean adminMenu = true;
                while(adminMenu){
                    System.out.println("*** 관리자 ***");
                    System.out.println("사용할 번호 입력");
                    System.out.println("1. 전체 예약 조회");
                    System.out.println("2. 전체 직원 조회");
                    System.out.println("3. 고객 정보 추가");
                    System.out.println("4. 처음으로 ");
                    System.out.println("0. 프로그램 종료");
                    int select1 = sc.nextInt();

                    switch (select1) {
                        case 1 : registDAO.allReservationInfo(con); break;
                        case 2 : registDAO.selectAllEmployee(con);break;
                        case 3 : registDAO.addCustomer(con); break;
                        case 4 : adminMenu = false;
                    }
                    if(select1 == 0){
                        System.out.println("프로그램을 종료합니다.");
                        a = false;
                        break;
                    }

                }
            } else if(code == 2){
                boolean guestMenu = true;
                while (guestMenu){

                    System.out.println("*** 손님 ***");
                    System.out.println("사용할 번호 입력");
                    System.out.println("1. 예약하기");
                    System.out.println("2. 테이블 정보 확인");
                    System.out.println("3. 예약 취소");
                    System.out.println("4. 처음으로");
                    System.out.println("0. 프로그램 종료");
                    int select2 = sc.nextInt();

                    switch (select2) {
                        case 1:
                            registDAO.addReservation(con);
                            break;
                        case 2:
                            registDAO.allTableInfo(con);
                            break;
                        case 3:
                            registDAO.cancelReservation(con);
                            break;
                        case 4:
                            guestMenu = false;
                    }

                    if (select2 == 0) {
                        System.out.println("프로그램을 종료합니다.");
                        a = false;
                        break;
                    }
                }



            }
        }

    }

}
