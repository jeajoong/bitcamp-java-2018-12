package com.bitcamp.lms.Handler;

import java.sql.Date;
import java.util.Scanner;
import com.bitcamp.lms.App;
import com.bitcamp.lms.domain.*;

public class MemberHandler {
  
   Member[] members = new Member[App.LENGTH];
   int memberIdx = 0;
  
   Scanner keyboard;
   public MemberHandler (Scanner keyboard) { 
     this.keyboard = keyboard;
   }
   
   
  public void addmember() {
    
    Member member = new Member();
    
    System.out.print("번호? ");
    member.setNo(Integer.parseInt(this.keyboard.nextLine()));
    
    System.out.print("이름? ");
    member.setName(this.keyboard.nextLine());
    
    System.out.print("이메일? ");
    member.setEmail(this.keyboard.nextLine());
    
    System.out.print("암호? ");
    member.setPassword(this.keyboard.nextLine());

    System.out.print("사진? ");
    member.setPhoto(this.keyboard.nextLine());

    System.out.print("전화? ");
    member.setTel(this.keyboard.nextLine());

    member.setRegisteredDate(new Date(System.currentTimeMillis())); 
    
    members[memberIdx] = member;
    memberIdx++;
    
    System.out.println("저장하였습니다.");
    
  }
    public  void listmamber() {
  for (int j = 0; j < memberIdx; j++) {
    System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
        members[j].getNo(), members[j].getName(), members[j].getEmail(), 
        members[j].getTel(), members[j].getRegisteredDate());
}
  }
  }
//static에는 this를 사용할수 없다
