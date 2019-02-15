//6까지 완료
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import com.eomcs.lms.domain.Lesson;

public class LessonTest {
  
  static ObjectInputStream in;
  static ObjectOutputStream out;
  
  public LessonTest(ObjectInputStream in, ObjectOutputStream out) {
   this.out = out;
   this.in = in;
  }
     
  public void test() throws Exception {
      add(new Lesson(1, "자바 프로그래밍"));
      add(new Lesson(2, "노드 프로그래밍"));
      
      detail(1);
      
      update(new Lesson(1, "자바 프로그래밍2222"));
      
      detail(1);
      
      delete(2);
      
      list();
  }


   static void add(Lesson lesson) throws Exception {
    out.writeUTF("/lesson/add");
    out.flush();
    if(! in.readUTF().equals("OK"))
      return;
 
    out.writeObject(lesson);
    out.flush();
    
    String status = in.readUTF();
    
    if(status.equals("OK"))
      System.out.println("데이터 추가 성공");
    else
      System.out.println("데이터 추가 실패");
  }

   static void list() throws Exception {
    out.writeUTF("/lesson/list"); 
    out.flush();
    if( ! in.readUTF().equals("OK"))
      return;
    
    String status = in.readUTF();
    
    if(!status.equals("OK")) {
      System.out.println("데이터 목록 가져오기 실패!");
      return;
    }
    
    List<Lesson> lessons = (List<Lesson>) in.readObject();
    for(Lesson m : lessons) {
      System.out.println(m);
    }
  }
  
   static void detail(int no) throws Exception {
    out.writeUTF("/lesson/detail");
    out.flush();
    if( ! in.readUTF().equals("OK"))
      return;
    
    out.writeInt(no);
    out.flush();
    
    String status = in.readUTF();
    
    if(! status.equals("OK")) {
      System.out.println("데이터 가져오기 실패!");
      return;
    }
    
    Lesson lesson = (Lesson) in.readObject();
    System.out.println(lesson);
  }
  
  static void update(Lesson lesson) throws Exception {
    out.writeUTF("/lesson/update");
    out.flush();
    if (! in.readUTF().equals("OK"))
      return;
    
    out.writeObject(lesson);
    out.flush();
    
    String status = in.readUTF();
    
    if(status.equals("OK"))
      System.out.println("데이터 변경 성공!");
    else
      System.out.println("데이터 변경 실패!");
  }
   static void delete(int no) throws Exception {
    out.writeUTF("/lesson/delete");
    out.flush();
    if( ! in.readUTF().equals("OK"))
      return;
    
    out.writeInt(no);
    out.flush();
    
    String status = in.readUTF();
    
    if(status.equals("OK"))
      System.out.println("데이터 삭제 성공!");
    else
      System.out.println("데이터 삭제 실패!");
  }
  
}
