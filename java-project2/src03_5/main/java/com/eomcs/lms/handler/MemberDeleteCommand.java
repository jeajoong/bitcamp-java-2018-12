package com.eomcs.lms.handler;
import com.eomcs.lms.dao.MemberDao;

public class MemberDeleteCommand extends AbstractCommand {
  
  MemberDao memberDao;
  
  public MemberDeleteCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
    int no = response.requestInt("번호? ");
    
      if (memberDao.delete(no) == 0) {
        response.println("해당 번호의 회원이 없습니다.");
        return;
      }
      response.println("삭제했습니다.");
      
    } catch (Exception e) {
      response.println(String.format("실행 오류! : %s\n", e.getMessage()));
    }
  }
}
