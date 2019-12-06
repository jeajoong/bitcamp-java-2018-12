package study.design_patterns.command.after;

public class HelloCommand implements Command {

  @Override
  public void execute() {
    System.out.println("안녕하세요!");
    
  }

}
