// 인스턴스 출력하기
package ch22.e;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Test01_1 {
  
  FileOutputStream out = null;

  public static void main(String[] args) {
    
    // 다음 세 학생의 성적 정보를 score.data 파일에 바이너리 형식으로 저장하라!
    // => java.io.BufferedOutputStream 클래스를 사용하라.
    // => java.io.DataOutputStream 클래스를 사용하라.
    //
    Score s1 = new Score("홍길동", 100, 100, 100);
    Score s2 = new Score("임꺽정", 90, 90, 90);
    Score s3 = new Score("유관순", 80, 80, 80);

    
    try (FileOutputStream out1 = new FileOutputStream("score.data");
        DataOutputStream out2 = new DataOutputStream(out1)
            ) {
      
        out1.writeUTF(s1.setName("홍길동"));
  
        
        
    } catch (Exception e) {
      e.printStackTrace();
    
      }
  }
  
  public void writeUTF(String value) throws IOException {
    byte[] bytes = value.getBytes("UTF-8");
    out.write(bytes.length >> 8);
    out.write(bytes.length);
    out.write(bytes);
}
}
