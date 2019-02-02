// 인스턴스 출력하기
package ch22.e;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Test01_1{
  
  static FileOutputStream out = null;
  
  public static void main(String[] args) {
    
    // 다음 세 학생의 성적 정보를 score.data 파일에 바이너리 형식으로 저장하라!
    // => java.io.BufferedOutputStream 클래스를 사용하라.
    // => java.io.DataOutputStream 클래스를 사용하라.
    //
    Score s1 = new Score("홍길동", 100, 100, 100);
    Score s2 = new Score("임꺽정", 90, 90, 90);
    Score s3 = new Score("유관순", 80, 80, 80);
    
    try (FileOutputStream out = new FileOutputStream("Score.data");
        BufferedOutputStream out1 = new BufferedOutputStream(out);
        DataOutputStream out2 = new DataOutputStream(out1)  ) {
      
      // try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
     //                                                                 new FileOutputStream("Score.data"))) ) {
      
      
      out2.writeUTF(s1.getName());
      out2.writeInt(s1.getKor());
      out2.writeInt(s1.getEng());
      out2.writeInt(s1.getMath());
      
      out2.writeUTF(s2.getName());
      out2.writeInt(s2.getKor());
      out2.writeInt(s2.getEng());
      out2.writeInt(s2.getMath());
      
      out2.writeUTF(s2.getName());
      out2.writeInt(s2.getKor());
      out2.writeInt(s2.getEng());
      out2.writeInt(s2.getMath());
      
    } catch (Exception e) {
      e.printStackTrace();
    
      }
  }
}
