package com.eomcs.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.eomcs.lms.domain.Board;

// 서버쪽에 있는 BoardDaoImpl 객체를 대행할 클라이언트측 대행자 클래스 정의 
// 집에서 해볼건 각 커맨드에서 데이터를 출력해줘야 되는부분
// 데이터 조회는 됨.,.,.,., 왜 add 안되지...
public class BoardDaoImpl implements BoardDao {
  
  public void findAll() {
    
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
    
    try (PreparedStatement stmt = con.prepareStatement(
        "select * from board_server")) {
      
      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          System.out.printf("%d,  %s, %s, %d\n", 
              rs.getInt("no"),      
              rs.getString("contents"), 
              rs.getDate("createdDate"),
              rs.getInt("viewCount"));
        }
        
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  } catch (Exception e) {
    System.out.println("서버와 연결이 되지 않음" + e );
  }
  }
  
  public void insert(Board board) {
    int no = board.getNo();
    String contents = board.getContents();
    
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
    
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into board_server(no, contents) values( ? , ? )")) {
        
        stmt.setInt(1, no);
        stmt.setString(2, contents);
      }
    } catch (Exception e) {
      e.printStackTrace();
  }
  }
  
  public void findByNo(int no) {
    
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      
      try (PreparedStatement stmt = con.prepareStatement(
          "select * from board_server where board_id =  ? ")) {
        
        stmt.setInt(1, no);
        
        try (ResultSet rs = stmt.executeQuery()) {
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void update(Board board) {
    int no = 0;
    String contents = null;
    
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      
      try (PreparedStatement stmt = con.prepareStatement(
          "update board_server set contents = ? where no = ?")) {
        
        stmt.setString(1, contents);
        stmt.setInt(2, no);
    }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void delete(int no) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      
      try (PreparedStatement stmt = con.prepareStatement(
          "delete from x_board where board_id = ? ")) {
        
        stmt.setInt(1, no);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}



