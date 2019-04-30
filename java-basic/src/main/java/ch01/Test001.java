package ch01;

public class Test001 {

  public static void main(String[] args) {
    String str = "abc,def";
    char[] arr = str.toCharArray();
    
    for (char c : arr) {
      if (c >= 'a' && c <= 'z')
        System.out.println((int)(c - ('a' - 'A')));
      else if (c == ',')
      System.out.println("#");
      else
        System.out.println(c);
    }
  }
}
