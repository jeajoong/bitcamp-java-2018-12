// 데이터 처리 관련 코드를 별도의 클래스로 분리
package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Member;

public interface MemberDao {
  public void insert(Member member);
  public List<Member> findAll();
  public Member findByNo(int no);
  public int update(Member member);
  public int delete(int no);
}







