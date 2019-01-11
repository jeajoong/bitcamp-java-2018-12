package com.bitcamp.lms.handler;

import java.sql.Date;
import com.bitcamp.lms.App;

public class MemberHandler {
  int no;
  String name;
  String email;
  String password;
  String photo;
  String tel;
  Date registeredDate;
  
  static MemberHandler[] members = new MemberHandler[App.LENGTH];
  static int memberIdx = 0;
  
  public static void addMember() {
    MemberHandler member = new MemberHandler();
    
    System.out.print("번호? ");
    member.no = App.keyboard.nextInt();
    
    System.out.print("이름? ");
    member.name = App.keyboard.next();
    
    System.out.print("이메일? ");
    member.email = App.keyboard.next();
    
    System.out.print("암호? ");
    member.password = App.keyboard.next();

    System.out.print("사진? ");
    member.photo = App.keyboard.next();

    System.out.print("전화? ");
    member.tel = App.keyboard.next();

    member.registeredDate = new Date(System.currentTimeMillis()); 
    
    System.out.println("저장 되었습니다");
    members[memberIdx] = member;
    memberIdx++;
  }
 public static void listMember() {
    for (int j = 0; j < memberIdx; j++) {
      System.out.printf("%d, %s, %s, %s, %s\n", 
          members[j].no, members[j].name, members[j].email, 
          members[j].tel, members[j].registeredDate);
  }
}
  
}
