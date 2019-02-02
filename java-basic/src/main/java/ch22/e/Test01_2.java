// 인스턴스 읽기
package ch22.e;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class Test01_2 {

  public static void main(String[] args) {
    
    // score.data 파일에서 세 학생의 데이터를 읽어 Score 인스턴스로 생성하라.
    // => java.io.BufferedInputStream 클래스를 사용하라.
    // => java.io.DataInputStream 클래스를 사용하라.
    //
    Score s1 = null;
    Score s2 = null;
    Score s3 = null;
    
    try (FileInputStream in = new FileInputStream("score.data");
        BufferedInputStream in1 = new BufferedInputStream(in);
        DataInputStream in2 = new DataInputStream(in1)) {
    
      s1.setName(in2.readUTF());
      s1.setKor(in2.readInt());
      s1.setEng(in2.readInt());
      s1.setMath(in2.readInt());
      
      s2.setName(in2.readUTF());
      s2.setKor(in2.readInt());
      s2.setEng(in2.readInt());
      s2.setMath(in2.readInt());
      
      s3.setName(in2.readUTF());
      s3.setKor(in2.readInt());
      s3.setEng(in2.readInt());
      s3.setMath(in2.readInt());
      
    } catch (Exception e) {
      e.printStackTrace();
    
      }
    // 그리고 세 학생의 정보를 다음과 같은 형식으로 출력하라.
    // =>   홍길동, 100, 100, 100, 300, 100 
    // 

    
  }

}
