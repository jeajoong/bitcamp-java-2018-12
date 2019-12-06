//배열 - 인스턴스와 래퍼런스
package study.A03_parameters_scanner_array;

public class Test14 {

  public static void main(String[] args) {
    
    int[] arr1 = new int[] {101, 102, 103, 104, 105};
    int[] arr2 = {81,82,83,84,85};
    
    arr2 = arr1;// arr2의 배열주소를 잃어버리게 됨(버려짐) 더이상 사용불가
    
    System.out.printf("%d, %d\n" , arr1[0], arr2[0]);
    
    arr1[1] = 500;
    System.out.printf("%d, %d\n", arr1[1], arr2[2-1]);
    
    System.gc(); //메모리를 빠르게 재사용 할 수있게 독촉
  
  }

}
