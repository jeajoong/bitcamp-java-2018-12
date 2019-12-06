// Wrapper 클래스와 오토박싱(auto-boxing)/오토언박싱(auto-unboxing)
package study.A11_string_autoBoxing;

public class Test11 {
  public static void main(String[] args) {
    
    Integer obj1 = new Integer(100);
    Integer obj2 = new Integer(100);
    if (obj1 == obj2)
      System.out.println("같다");
    else
      System.out.println("다르다");
    
    //정수 값을 제대로 비교하려면 equlas()를 호출해야 한다.
    //물론, wrapper 클래스들도 String 클래스처럼 object로부터 상속받은 
    // equlas()를 값을 비교하도록 변경하였다.
    if (obj1.equals(obj2))
      System.out.println("obj1.equlas(obj2)");
    else
      System.out.println("!obj1.equlas(obj2)");
  // new를 사용하여 객체를 만들면 인스턴스 주소가 다르다
  // 해결방안? 
  // ==> 같은 값을 갖는 인스턴스인 경우 중복 생성하지 않고 한 개만 생성한다면
  // 이런 번거로움을 피할 수 있다.
    Integer obj3 = Integer.valueOf(100);
    Integer obj4 = Integer.valueOf(100);
    
    if (obj3 == obj4)
      System.out.println("같다");
    else
      System.out.println("다르다");
    
    
  }
}
















