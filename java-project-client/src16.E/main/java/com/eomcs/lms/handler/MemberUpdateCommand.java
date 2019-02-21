package com.eomcs.lms.handler;
import java.util.Scanner;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberUpdateCommand implements Command {
  
  Scanner keyboard;
  MemberDao memberDao;
  
  public MemberUpdateCommand(Scanner keyboard, MemberDao memberDao) {
    this.keyboard = keyboard;
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute() {

    try {
      Member member = new Member();
      System.out.print("번호? ");
      member.setNo(Integer.parseInt(keyboard.nextLine()));
    
      System.out.printf("이름? ");
      member.setName(keyboard.nextLine());
      
      System.out.printf("이메일? ");
      member.setEmail(keyboard.nextLine());
      
      System.out.printf("암호(********)? ");
      member.setPassword(keyboard.nextLine());
      
      System.out.printf("사진? ");
      member.setPhoto(keyboard.nextLine());
      
      System.out.printf("전화? ");
      member.setTel(keyboard.nextLine());
      
      if (memberDao.update(member) == 0) {
      System.out.println("해당 번호의 회원 데이터가 없습니다.");
      return;
      }
      
      System.out.println("변경했습니다.");
      
    } catch (Exception e) {
      System.out.printf("실행 오류! : %s\n", e.getMessage());
    }
  }
}
