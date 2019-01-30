package design_pattern.decorator.composite;

public class Test01 {
  
  public static void main(String[] args) {
    
    //Composite 디자인 패턴:
    // => tree 구조로 전체-부분 관계의 객체를 표현할 때 사용한다.
    // => 보통 조직도를 표현할 때 적합한 설계 기법이다.
    // => OS의 파일 시스템도 이 설계 기법으로 구현할 수 있다.
    // 
    // 다음은 OS의 파일과 디렉토리 관계를 Composite tree구조로 표현한 것이다.
    //
  Directory root = new Directory("/");
  
  root.add(new File("a.gif", "image/gif", 3456));
  root.add(new File("b.gif", "image/gif", 34530));
  root.add(new File("test.txt", "text/plain", 8700));
  
  Directory src = new Directory("/src");
  src.add(new File("Hello.java", "text/java", 320));
  src.add(new File("Hello2.java", "text/java", 400));
  root.add(src);
  
  Directory bitcamp = new Directory("/bitcamp");
  bitcamp.add(new File("A.java", "text/java", 200));
  src.add(bitcamp);
  
  display(root, "");
  }
  
  static void display(Directory dir, String path) {
    System.out.println(path); // 디렉토리가 들어오면 현재 이름을 출력하고
    
    for (Node node : dir.childs) {// 폴더나 파일을 꺼내고
      if (node instanceof File) {
        System.out.printf("%s/%s\n", path, node.getTitle());// 출력
        
      }else if (node instanceof Directory) { // 꺼낸게 자식 디렉토리라면 
        display((Directory)node, path + node.getTitle()); // 즉시 다시 호출
      }
    }
  }
  
}
