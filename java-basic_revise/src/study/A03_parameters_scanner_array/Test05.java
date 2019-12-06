//래퍼런스 변수
package study.A03_parameters_scanner_array;

public class Test05 {
  public static void main(String[] args) {
    java.util.Date d1 = new java.util.Date();
    java.util.Date d2 = d1;
    //d1은 getDate 주소값을 포함하고 있음.
    //d2도 getDate 주소값을 포함하고 있음.
    
    System.out.printf("%d, %d\n", d1.getDate(), d2.getDate());
    
    d1.setDate(22);// d1을 22로 바꾸면 d2도 22로 위치값이 바뀐다
    
    System.out.printf("%d, %d\n", d1.getDate(), d2.getDate());
    //d1에 저장된 일자 값을 변경한 후
    //d2에 저장된 일자 값을 출력해 보면 d1과 똑같이 변경되어 있다.
    //이유는?
    //d1과 d2에 저장되는 것은 값이 아니라 (날짜 정보가 저장되어 있는 메모리의)주소이다.
    //이렇게 값을 저장하지 않고 값이 저장된 위치(주소)를 저장하는 변수를
    //"래퍼런스(변수)" 라 부른다.
    // 자바 기본 타입을 제외(byte,short,int,double,long,float,boolean,char ...)한 모든 변수가 래퍼런스이다.
    
  }

}
