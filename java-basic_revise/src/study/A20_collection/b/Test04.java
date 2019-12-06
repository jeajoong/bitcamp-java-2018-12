// Stack 클래스 사용법 - search()
package study.A20_collection.b;

import java.util.Stack;

public class Test04 {

  public static void main(String[] args) {
    Stack<String> stack = new Stack<>();
    
    //push() - 스택의 맨 마지막에 값을 추가한다.
    stack.push("aaa");
    stack.push("bbb");
    stack.push("ccc");
    
    //peek() - 스택의 맨 위에서 부터 해당 값을 찾는다. 위치는 맨 위가 1이다.
    System.out.println(stack.search("ccc")); // 1
    System.out.println(stack.search("bbb")); // 2
    System.out.println(stack.search("aaa")); // 3
    System.out.println(stack.search("xxx")); // 못찾으면 -1
  }

}
