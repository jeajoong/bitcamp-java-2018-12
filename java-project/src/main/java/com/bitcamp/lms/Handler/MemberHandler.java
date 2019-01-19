package com.bitcamp.lms.Handler;
import java.sql.Date;
import java.util.Scanner;
import com.bitcamp.lms.domain.Member;

public class MemberHandler {
  
  Scanner keyboard;
  ArrayList<Member> list;
  
  public MemberHandler(Scanner keyboard) {
    this.keyboard = keyboard;
    this.list = new ArrayList<>();
  }
  
  public void listMember() {
    Member[] members = list.toArray(new Member[0]);
    
    for (Member member : members) {
      System.out.printf("%3d, %-4s, %-20s, %-15s, %s\n", 
          member.getNo(), member.getName(), 
          member.getEmail(), member.getTel(), member.getRegisteredDate());
    }
  }

  public void addMember() {
    Member member = new Member();
    
    System.out.print("번호? ");
    member.setNo(Integer.parseInt(keyboard.nextLine()));
    
    System.out.print("이름? ");
    member.setName(keyboard.nextLine());
    
    System.out.print("이메일? ");
    member.setEmail(keyboard.nextLine());
    
    System.out.print("암호? ");
    member.setPassword(keyboard.nextLine());
  
    System.out.print("사진? ");
    member.setPhoto(keyboard.nextLine());
  
    System.out.print("전화? ");
    member.setTel(keyboard.nextLine());
  
    member.setRegisteredDate(new Date(System.currentTimeMillis())); 
    
    list.add(member);
    
    System.out.println("저장하였습니다.");
  }

  public void detailMember() {
    System.out.println("번호? ");
    int no = keyboard.nextInt();
    // no를 입력하고 no에 맞는 배열 주소 값 한개를 Member변수에 가져와서 프린트해야한다
    
    int index = indexM(no);
    if (index == -1) {
      System.out.println("회원을 찾을 수 없습니다.");
      return;
    }
    Member member = list.get(index);
    
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getTel());
    System.out.printf("가입일: %s\n", member.getRegisteredDate());
    
  }
  
  public void updateMember() {
    System.out.println("번호? ");
    int no = keyboard.nextInt();
  
    int index = indexM(no);
    if (index == -1) {
      System.out.println("회원을 찾을수 없습니다.");
    }
    
    Member member = list.get(index);
    
    try {
      Member temp = member.clone();
      
      System.out.printf("이름(%s)? ", member.getName());
      String input = keyboard.next();
      if (input.length() > 0)
        temp.setName(input);
      
      System.out.printf("이메일(%s)? ", member.getEmail());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setEmail(input);
      
      System.out.printf("암호(********)? ");
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setPassword(input);
      
      System.out.printf("사진(%s)? ", member.getPhoto());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setPhoto(input);
      
      System.out.printf("전화(%s)? ", member.getTel());
      if ((input = keyboard.nextLine()).length() > 0)
        temp.setTel(input);
      
      list.set(index, temp);
      
    } catch (Exception e) {
      System.out.println("변경 중 오류 발생!");
    }
    
  }
  
  
  public void deleteMember() {
    
  }
  
  
  public int indexM(int no) {
    for (int i = 0; i < list.size; i++) {
      Member m = list.get(i);
      if(m.getNo() == no)
        return i;
    }
    return -1;
    
  }
  
}









