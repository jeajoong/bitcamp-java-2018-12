package com.bitcamp.lms.Handler;

import java.sql.Date;
import java.util.Scanner;
import com.bitcamp.lms.domain.*;

public class MemberHandler {
  
  Scanner keyboard;
  ArrayList arraylist = new ArrayList();
  
   public MemberHandler (Scanner keyboard) { 
     this.keyboard = keyboard;
   }
   
   
  public void addMember() {
    
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
    
    arraylist.add(member);
    
    System.out.println("저장하였습니다.");
    
  }
    public void listMember() {
      Object[] objs = arraylist.toArray();
      
  for (Object obj : objs) {
    Member member = (Member) obj;
    System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
        member.getNo(), member.getName(), member.getEmail(), 
        member.getTel(), member.getRegisteredDate());
}
  }
  }
//static에는 this를 사용할수 없다
