package com.eomcs.lms.handler;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;

public class LessonDetailCommand implements Command {

  Scanner keyboard;

  public LessonDetailCommand(Scanner keyboard) {
    this.keyboard = keyboard;
  }

  @Override
  public void execute(ObjectInputStream in, ObjectOutputStream out) {
    System.out.print("번호? ");
    int no = Integer.parseInt(keyboard.nextLine());

    try {
      out.writeUTF("/lesson/detail");
      out.flush();
      if (!in.readUTF().equals("OK"))
        throw new Exception("서버에서 해당 명령어를 처리하지 못합니다.");
      
      out.writeInt(no);
      out.flush();
      
      String status = in.readUTF();
      
      if (!status.equals("OK")) 
        throw new Exception("서버에서 수업데이터 가져오기 실패!");
      
      Lesson lesson = (Lesson) in.readObject();
      System.out.println(lesson);

    } catch (Exception e) {
      System.out.printf("게시글 상세 정보 출력 오류! : %s\n", e.getMessage());
    }
  }
}
