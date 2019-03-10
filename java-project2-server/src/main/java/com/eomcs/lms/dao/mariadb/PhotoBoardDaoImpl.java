package com.eomcs.lms.dao.mariadb;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;

public class PhotoBoardDaoImpl implements PhotoBoardDao {

  //Mybatis 의존 객체 선언
  SqlSessionFactory sqlSessionFactory;

  public PhotoBoardDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }
  
  @Override 
  // findAll에 Map을 받게끔 바꾼이유는 LessonDelete에서 
  // photoBoard와 PhotoFile을 삭제하기 위해서 이다.
  // 사진파일이 수업 데이터의 PK(수업번호)값을 공유하기에
  // 수업데이터를 삭제하려면 종속된 사진게시판과 사진파일을 지워야한다.
  public List<PhotoBoard> findAll(Map<String,Object> params) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("PhotoBoardMapper.findAll", params);
    }
  }
  

  @Override
  public void insert(PhotoBoard photoBoard) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.insert("PhotoBoardMapper.insert", photoBoard);
      sqlSession.commit();
    }
  }

  @Override
  public PhotoBoard findByNo(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      PhotoBoard photoBoard = sqlSession.selectOne("PhotoBoardMapper.findByNo", no); 
      // PhotoBoard 객체에 세션의 정보를 넘겨받는다
      if (photoBoard != null) {
        // 만약 정보가 null이 아니라면
        sqlSession.update("PhotoBoardMapper.increaseCount", no);
        // 조회수를 늘려야 한다
        sqlSession.commit();
      }
      return photoBoard;
    }
  }
  
  @Override
  public PhotoBoard findByNoWithFile(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      PhotoBoard photoBoard = sqlSession.selectOne("PhotoBoardMapper.findByNoWithFile", no);
      // PhotoBoard 객체에 세션 정보 넘겨받기
      if (photoBoard != null) {
        // 만약 정보가 null이 아니라면
        sqlSession.update("PhotoBoardMapper.increaseCount", no);
        // 조회수 늘리기
        sqlSession.commit();
      }
      return photoBoard;
    }
  }
  
  @Override
  public int update(PhotoBoard photoBoard) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("PhotoBoardMapper.update", photoBoard);
      sqlSession.commit();
      return count;
    }
  }
  
  @Override
  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("PhotoBoardMapper.delete", no);
      sqlSession.commit();
      return count;
    }
  }
}
