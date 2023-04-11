package com.phoenix.qpproject;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:5400/qp_mayo?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "phoenixC1ub0101";

    @Test
    public void testConnection() throws Exception {
        // DBMS에게 DB 연결 드라이버의 위치를 알려주기 위한 메소드
        Class.forName(DRIVER);
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
