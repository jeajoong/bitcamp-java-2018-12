// 9단계 : Service 패키지로 옮기기
package com.eomcs.lms.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.domain.Member;

public class MemberService {

  static ArrayList<Member> members = new ArrayList<>();
  
  static ObjectInputStream in;
  static ObjectOutputStream out;
  
  public MemberService(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }
  
    public void execute(String request) throws Exception {
          
          switch (request) {
            case "/member/add":
              add();
              break;
            case "/member/list":
              list();
              break;
            case "/member/detail":
              detail();
              break;
            case "/member/update":
              update();
              break;
            case "/member/delete":
              delete();
              break;
              default:
                out.writeUTF("FAIL");
          }
          out.flush();
        }
  
  static void add() throws Exception {
    out.writeUTF("OK");
    out.flush();
    members.add((Member) in.readObject()); // add => List의 add임
    out.writeUTF("OK");
  }
  
   static void list() throws Exception {
   out.writeUTF("OK"); 
   out.flush();
   out.writeUTF("OK");
    out.writeObject(members);
  }
  
   static void detail() throws IOException {
    out.writeUTF("OK");
    out.flush();
    int no = in.readInt();
    
    for (Member m : members) {
      if(m.getNo() == no) {
        out.writeUTF("OK");
        out.writeObject(m);
        return;
      }
    }
    out.writeUTF("fail");
  }
  
  static void update() throws Exception {
    out.writeUTF("OK");
    out.flush();
    
    Member member = (Member) in.readObject();
    
    int index = 0;
    for (Member m : members) {
      if(m.getNo() == member.getNo()) { 
        members.set(index, member); 
        out.writeUTF("OK");
        return;
      }
      index++; // 0번째 부터 훑음.. 조건식이 맞다면 결국엔 index의 값도 리스트.getno 값이다
    }
    out.writeUTF("fail");
  }
  
  static void delete() throws Exception {
    out.writeUTF("OK");
    out.flush();
    
    int no = in.readInt(); 
    
    int index = 0; 
    for(Member m : members) {
      if(m.getNo() == no) {
        members.remove(index); 
        out.writeUTF("OK");
        return;
      }
      index++;
    }
    out.writeUTF("Fail");
  }
}
