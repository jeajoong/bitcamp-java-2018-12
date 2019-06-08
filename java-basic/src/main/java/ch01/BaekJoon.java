package ch01;

import java.util.Arrays;
import java.util.Scanner;

// 문제 11399번
// ATM이 한대인 은행에서 사람들이 줄을 서서 인출한다고 했을때
// 사람마다 인출시간이 다르다.
// 한줄에 서서 기다려야 하는데
// 각 개인이 기다리는 시간의 합을 최소로 해서 궁극적으로 모두가 기다리는 시간을 줄여야 하는 규칙이다.
// 

//-첫째 줄에 사람의 수 N(1 ≤ N ≤ 1,000)이 주어진다. 둘째 줄에는 각 사람이 돈을 인출하는데 걸리는 시간 Pi가 주어진다. (1 ≤ Pi ≤ 1,000)

//-첫째 줄에 각 사람이 돈을 인출하는데 필요한 시간의 합의 최솟값을 출력한다
public class BaekJoon {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] Pi = new int[N];
    
      if(1<= N && N <= 1000) {
    for (int i = 0; i <N; i++) {
      Pi[i] = sc.nextInt();
    }
    
    Arrays.sort(Pi); // !!! Arrays.sort에 배열을 전달하면 담긴 값을 오름차순으로 정렬해준다. 
    
    int wait = 0;
    int total = 0;
    
    for (int e : Pi) {
      wait += e;
      total += wait;
    }
    System.out.println(total);
    
    }
      sc.close();
  }
}
