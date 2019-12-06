package study.A22_Serializable.f;

import java.io.Serializable;
 
// java.io.Serializable
// => serialize를 허락하고 싶다면 이 인터페이스를 구현한다.
// => 자바는 기본적으로 보안을 위해서 자동으로 serialize 하는 것을 허락하지 않는다.
// => 개발자가 직접 이 인터페이스를 붙인 클래스에 대해서만 serializa 할 수 있다.
// => 따라서 이 인터페이스는 serialize 기능을 활성화시키는 표시자로서 역할을 한다.
//    그래서 이 인터페이스에는 메서드가 선언되어 있지 않다.
//    이 인터페이스를 구현하는 클래스는 따로 메서드를 구현할 필요가 없다.
//
// 직렬화(Serialize)
// 자바 시스템 내부에서 사용되는 Object 또는 Data를 외부의 자바 시스템에서도
// 사용할 수 있도록 byte 형태로 데이터를 변환하는 기술.
// JVM(Java Virtual Machine 이하 JVM)의 메모리에 상주(힙 또는 스택)되어 있는
// 객체 데이터를 바이트 형태로 변환하는 기술

// 역직렬화(Deserialize)
// byte로 변환된 Data를 원래대로 Object나 Data로 변환하는 기술을 역직렬화(Deserialize)라고 부릅니다.
// 직렬화된 바이트 형태의 데이터를 객체로 변환해서 JVM으로 상주시키는 형태.
//
// Serializable 인터페이스를 상속받은 객체는 직렬화 할 수 있는 기본 조건

public class Score2 implements Serializable {
  
  private String name;
  private String tel; // Test2_1을 실행한 후 이 필드를 추가하라!
  private int kor;
  private int eng;
  private int math;
  private int sum;
  private float aver;
  
  public Score2() {
    System.out.println("Score2()");
  }
  
  public Score2(String name, int kor, int eng, int math) {
    this.name = name;
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    compute();
  }

  // Test2_1을 실행하여 Score2 값을 출력한 후 toString() 메서드를 다시 생성하라.
  @Override
  public String toString() {
    return "Score2 [name=" + name + ", tel=" + tel + ", kor=" + kor + ", eng=" + eng + ", math="
        + math + ", sum=" + sum + ", aver=" + aver + "]";
  }

  //Test2_1을 실행하여 Score2 값을 출력한 후 tel 필드의 getter/setter를 추가하라. 
  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getKor() {
    return kor;
  }

  public void setKor(int kor) {
    this.kor = kor;
    this.compute();
  }

  public int getEng() {
    return eng;
  }

  public void setEng(int eng) {
    this.eng = eng;
    this.compute();
  }

  public int getMath() {
    return math;
  }

  public void setMath(int math) {
    this.math = math;
    this.compute();
  }

  public int getSum() {
    return sum;
  }

  public float getAver() {
    return aver;
  }
  
  public void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.aver = this.sum / 3f;
  }
}




