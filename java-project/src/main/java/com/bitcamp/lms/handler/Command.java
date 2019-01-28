package com.bitcamp.lms.handler;

// 명령어를 처리하는 객체의 사용 규칙을 정의한다.
public interface Command {
  
  // 명령어를 처리하고 싶을 때 호출하는 메서드이다.
  void execute(); 
  
}
// 나의 생각
// 커맨드에 들어온 prompt()를 활용할수 있는 인터페이스