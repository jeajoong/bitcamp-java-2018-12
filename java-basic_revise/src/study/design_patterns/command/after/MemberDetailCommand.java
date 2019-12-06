package study.design_patterns.command.after;

// Command 규칙에 따라 동작하는 클래스를 정의한다.
// 각 명령어를 처리하는 메서드를 클래스로 분리한다.
public class MemberDetailCommand implements Command {

  @Override
  public void execute() {
    System.out.println("회원 상세조회 처리!");
    
  }

}
