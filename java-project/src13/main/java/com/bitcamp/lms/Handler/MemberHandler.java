package com.bitcamp.lms.Handler;

import java.sql.Date;
import com.bitcamp.lms.App;
import com.bitcamp.lms.domain.*;

public class MemberHandler {
  
   Member[] members = new Member[App.LENGTH];
   int memberIdx = 0;
  
  public void addmember() {
    
    Member member = new Member();
    
    System.out.print("번호? ");
    member.no = Integer.parseInt(App.keyboard.nextLine());
    
    System.out.print("이름? ");
    member.name = App.keyboard.nextLine();
    
    System.out.print("이메일? ");
    member.email = App.keyboard.nextLine();
    
    System.out.print("암호? ");
    member.password = App.keyboard.nextLine();

    System.out.print("사진? ");
    member.photo = App.keyboard.nextLine();

    System.out.print("전화? ");
    member.tel = App.keyboard.nextLine();

    member.registeredDate = new Date(System.currentTimeMillis()); 
    
    members[memberIdx] = member;
    memberIdx++;
    
    System.out.println("저장하였습니다.");
    
  }
    public  void listmamber() {
  for (int j = 0; j < memberIdx; j++) {
    System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
        members[j].no, members[j].name, members[j].email, 
        members[j].tel, members[j].registeredDate);
}
  }
  }

