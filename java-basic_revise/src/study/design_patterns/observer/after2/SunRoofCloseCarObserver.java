package study.design_patterns.observer.after2;

public class SunRoofCloseCarObserver extends AbstractCarObserver {
  @Override
  public void carStopped() {
    System.out.println("썬루프를 닫는다.");
    
  }
}
