package study.design_patterns.observer.after;

public class SunRoofCloseCarObserver implements CarObserver {

  @Override
  public void carStarted() {
  }

  @Override
  public void carStopped() {
    System.out.println("썬루프를 닫는다.");
    
  }
  
}
