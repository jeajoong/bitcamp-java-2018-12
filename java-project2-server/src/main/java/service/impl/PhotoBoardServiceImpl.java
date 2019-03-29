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
  
  PlatformTransactionManager txManager;
  PhotoBoardDao boardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardServiceImpl(
  PhotoBoardDao boardDao,
  PhotoFileDao photoFileDao,
  PlatformTransactionManager txManager) {
    this.boardDao = boardDao;
    this.photoFileDao = photoFileDao;
    this.txManager = txManager;
  }

  @Override
  public List<PhotoBoard> list() {
    return boardDao.findAll(null);
  }

  @Override
  public int add(PhotoBoard board) {
    // 트랜잭션 동작 방식을 설정한다.
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    
    // 트랜잭션을 준비한다.
    TransactionStatus status = txManager.getTransaction(def);
    
    try {
       boardDao.insert(board);
      
      List<PhotoFile> files = board.getFiles();
      for (PhotoFile file : files) {
        file.setPhotoBoardNo(board.getNo());
      }
      photoFileDao.insert(board.getFiles());
    
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return boardDao.insert(board);
  }

  @Override
  public PhotoBoard get(int no) {
    return null;
  }

  @Override
  public int update(PhotoBoard board) {
    return boardDao.update(board);
  }

  @Override
  public int delete(int no) {
    return boardDao.delete(no);
  }

}
