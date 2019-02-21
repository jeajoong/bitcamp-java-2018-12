package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Member;

//서버쪽에 있는 MemberDaoImpl 객체를 대행할 클라이언트측 대행자 클래스 정의 
//
public class MemberDaoImpl implements MemberDao {
  public List<Member> findAll() {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      
      try (PreparedStatement stmt = con.prepareStatement(
          "select member_id, cdt, name, email, pwd, tel, photo from lms_member"
          + " order by member_id desc")) {
        
        try (ResultSet rs = stmt.executeQuery()) {
          
          ArrayList<Member> list = new ArrayList<>();
          while (rs.next()) {
            Member member = new Member();
            member.setNo(rs.getInt("member_id"));
            member.setRegisteredDate(rs.getDate("cdt"));
            member.setName(rs.getString("name"));
            member.setEmail(rs.getString("email"));
            member.setPassword(rs.getString("pwd"));
            member.setTel(rs.getString("tel"));
            member.setPhoto(rs.getString("photo"));
            
            list.add(member);
          }
          return list;
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void insert(Member member) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      
      try (PreparedStatement stmt = con.prepareStatement(
          "insert into lms_member(name, email, pwd, tel, photo) values( ? , ? , ? ,? , ? )")) {
        
        stmt.setString(1, member.getName());
        stmt.setString(2, member.getEmail());
        stmt.setString(3, member.getPassword());
        stmt.setString(4, member.getTel());
        stmt.setString(5, member.getPhoto());
        stmt.executeUpdate();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Member findByNo(int no) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      
      try (PreparedStatement stmt = con.prepareStatement(
          "select * from lms_member where member_id = ?")) {
        
        stmt.setInt(1, no);
        
        try (ResultSet rs = stmt.executeQuery()) {
        
          if (rs.next()) {
            Member member = new Member();
            member.setName(rs.getString("name"));
            member.setEmail(rs.getString("email"));
            member.setPassword(rs.getString("pwd"));
            member.setRegisteredDate(rs.getDate("cdt"));
            member.setTel(rs.getString("tel"));
            member.setPhoto(rs.getString("photo"));
            return member;
          } else {
            return null;
          }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int update(Member member) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      
      try (PreparedStatement stmt = con.prepareStatement(
          "update lms_member set name = ?, email = ?, pwd = ?, tel = ?, photo = ? where board_id = ?")) {
        // cdt는 수정대상 아님
        stmt.setString(1, member.getName());
        stmt.setString(2, member.getEmail());
        stmt.setString(3, member.getPassword());
        stmt.setString(4, member.getTel());
        stmt.setString(5, member.getPhoto());
        
        return stmt.executeUpdate();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public int delete(int no) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      
      try (PreparedStatement stmt = con.prepareStatement(
          "delete from lms_member where member_id = ?")) {
        
        stmt.setInt(1, no);
        
        return stmt.executeUpdate();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
}









