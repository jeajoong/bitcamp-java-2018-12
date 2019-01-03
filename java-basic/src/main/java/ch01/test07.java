package ch01;

public class test07 {
  public static void main(String[] args) {
    //'가' 문자를 출력하라
    System.out.println((char)0xac00);
    //문자의 코드를 알지 못한다면 작은 따옴표('')를 이용할 수 있다.
    //'가'?
    //한글 '가'의 UTF-16 값을 리턴한다.
    //또한 이 값이 문자의 코드 값임을 알려준다.
    System.out.println('가'+2);
    System.out.println((char)('가'+2));
    
    //작은 따옴표 안에 직접 유니코드(UTF-16)를 표기할 수 있다.
    System.out.println('\uac00');
    //
  }

}
