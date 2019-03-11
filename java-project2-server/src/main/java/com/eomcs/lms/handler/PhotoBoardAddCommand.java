package com.eomcs.lms.handler;
import java.util.ArrayList;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.mybatis.TransactionManager;

public class PhotoBoardAddCommand extends AbstractCommand {
  
  TransactionManager txManager;
  PhotoBoardDao photoBoardDao; 
  PhotoFileDao photoFileDao;
  
  public PhotoBoardAddCommand(
      PhotoBoardDao photoBoardDao,
      PhotoFileDao photoFileDao,
      TransactionManager txManager) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.txManager = txManager;
  }
  
  @Override
  public void execute(Response response) throws Exception {
    txManager.beginTransaction();
    // TranscationManager의 beginTransaction() 호출하고 
    // beginTransaction()는 SqlSessionFactoryProxy의 prepareForTransaction()를 호출한다.
    // 그 메서드에서 자동으로 commit 되지않는 sqlSession을 만들고 한 트랜잭션이 종료되기 전까지 close()를 하지 않도록
    // SqlSession를 재정의 한  SqlSessionProxy(SqlSession original, boolean useTransaction)에 
    // sqlSession을 넣었다 이 sqlSession을 스레드에 add.
    // 그러고 나서 명령어 처리를 할때 스레드에 있는 그 sqlSession을 get해서 openSession() 한다.
    
    try {
      PhotoBoard board = new PhotoBoard();
      board.setTitle(response.requestString("사진 제목?"));
      board.setLessonNo(response.requestInt("수업?"));
      photoBoardDao.insert(board);
      
      response.println("최소 한 개의 사진 파일을 등록해야 합니다.");
      response.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");
      
      ArrayList<PhotoFile> files = new ArrayList<>();
      while (true) {
        String filePath = response.requestString("사진 파일?");
        if (filePath.length() == 0) {
          if (files.size() == 0) {
            response.println("최소 한 개의 사진 파일을 등록해야 합니다.");
            continue;
          } else {
            break;
          }
        }
        PhotoFile file = new PhotoFile();
        file.setFilePath(filePath);
        file.setPhotoBoardNo(board.getNo());// 사진 게시물을 입력한 후 자동 생성된 PK 값을 꺼낸다.
        
        files.add(file);
      }
      
      photoFileDao.insert(files);
      //모든 작업 완료 후 파일을 insert 하고 
      // TransactionManager의 commit()을 통해  
      // 스레드에 있는 sqlSession을 얻어와서 
      // SqlSessionFactoryProxy에서 if문으로 구분해 commit한다
      response.println("저장하였습니다.");
      txManager.commit();
      
    } catch (Exception e) {
      e.printStackTrace();
      response.println("저장 중 오류가 발생.");
      txManager.rollback();
      
    }
  }
}



