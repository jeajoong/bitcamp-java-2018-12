//변수 선언과 값 저장(할당=배정=대입)
package study.A03_parameters_scanner_array;

public class Test02 {
  public static void main(String[] args) {
    //변수 선언 후 값 저장
    int a;
    a = 100; // 값을 저장할 때 = 연산자(assignment operator)를 사용한다
    
    // 변수 선언과 동시에 값 저장 = 변수 초기화
    int b = 100;
    
    // 변수 여러개 선언
    int c;
    int d;
    int e;
    
    // 변수를 한번에 여러개 선언
    int x, y, z;
    //여러개의 변수 선언과 동시에 값 저장
    int n=100, m=200, o=300;
    
    //변수에 값을 저장하지 않고 그 변수의 값을 사용하려 하면 컴파일 오류!
    /* int f;
    System.out.println(f); // 컴파일 오류!
    */
  }
}
