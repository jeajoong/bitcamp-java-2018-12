package service.impl;

import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import service.PhotoBoardService;

public class PhotoBoardServiceImpl implements PhotoBoardService{

  PhotoBoardDao boardDao;
  PhotoFileDao fileDao;
  PlatformTransactionManager txManager;
  

  public PhotoBoardServiceImpl(
      PhotoBoardDao boardDao,
      PhotoFileDao fileDao,
      PlatformTransactionManager txManager) {
    
    this.boardDao = boardDao;
    this.fileDao = fileDao;
    this.txManager = txManager;
  }

  @Override
  public List<PhotoBoard> list() {
    return boardDao.findAll(null);
  } 
// 일단 검색어는 스킵
  
  
  @Override
  public int add(PhotoBoard board) {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    
    TransactionStatus status = txManager.getTransaction(def);
    
    try {
      int count = boardDao.insert(board);
      // 파일 저장전에! 사진파일을 저장할때 게시판 번호를 넘겨주고 Dao로 저장해야함
      List<PhotoFile> files = board.getFiles();
      for(PhotoFile f : files) {
        f.setPhotoBoardNo(board.getNo());
      }
      fileDao.insert(board.getFiles());

      txManager.commit(status);
      return count;
      
    } catch (Exception e) {
      txManager.rollback(status);
      throw e;
    } 
  }

  
  
  @Override
  public PhotoBoard get(int no) {
    PhotoBoard photoBoard = boardDao.findByNo(no);
    if(photoBoard != null) {
      boardDao.increaseCount(no);
    }
    return photoBoard;
  }

// command는 update까지 완료
// Service는 update 미완료.. 할것
  @Override
  public int update(PhotoBoard board) {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    
    TransactionStatus status = txManager.getTransaction(def);
    
    try {
      boardDao.findByNo(board.getNo());
      
      
      
      photoFileDao.deleteByPhotoBoardNo(board.getNo());
    }catch (Exception e) {
    }
    
    
    
    
    return boardDao.update(board);
  }

  
  
  
  
  
  
  
  @Override
  public int delete(int no) {
    return boardDao.delete(no);
  }

}
