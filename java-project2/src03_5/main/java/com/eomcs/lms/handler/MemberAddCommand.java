package com.eomcs.lms.handler;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

public class MemberAddCommand extends AbstractCommand {
  
  MemberDao memberDao;
  
  public MemberAddCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  @Override
  public void execute(Response response) {
    try {
    Member member = new Member();
    member.setName(response.requestString("이름? "));
    member.setEmail(response.requestString("이메일? "));
    member.setPassword(response.requestString("패스워드? "));
    member.setPhoto(response.requestString("사진? "));
    member.setTel(response.requestString("전화번호? "));
  
      memberDao.insert(member);
      response.println("저장하였습니다.");
      
    } catch (Exception e) {
      response.println(String.format("실행 오류! : %s\n", e.getMessage()));
    }
  }
}
