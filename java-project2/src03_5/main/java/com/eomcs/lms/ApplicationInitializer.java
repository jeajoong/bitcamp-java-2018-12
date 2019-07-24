package com.eomcs.lms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.mariadb.BoardDaoImpl;
import com.eomcs.lms.dao.mariadb.LessonDaoImpl;
import com.eomcs.lms.dao.mariadb.MemberDaoImpl;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;

public class ApplicationInitializer implements ApplicationContextListener{

  Connection con;
  @Override
  public void contextInitialized(Map<String, Object> context) {
    
    try {
      con = DriverManager.getConnection(
          "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111");
    
    Map<String,Command> commandMap = new HashMap<>();
    
    Scanner keyboard = (Scanner) context.get("keyboard");
    
    LessonDaoImpl lessonDao = new LessonDaoImpl(con);
    commandMap.put("/lesson/add", new LessonAddCommand(lessonDao));
    commandMap.put("/lesson/list", new LessonListCommand(lessonDao));
    commandMap.put("/lesson/detail", new LessonDetailCommand(lessonDao));
    commandMap.put("/lesson/update", new LessonUpdateCommand(lessonDao));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(lessonDao));

    MemberDaoImpl memberDao = new MemberDaoImpl(con);
    commandMap.put("/member/add", new MemberAddCommand(memberDao));
    commandMap.put("/member/list", new MemberListCommand(memberDao));
    commandMap.put("/member/detail", new MemberDetailCommand(memberDao));
    commandMap.put("/member/update", new MemberUpdateCommand(memberDao));
    commandMap.put("/member/delete", new MemberDeleteCommand(memberDao));

    BoardDaoImpl boardDao = new BoardDaoImpl(con);
    commandMap.put("/board/add", new BoardAddCommand(boardDao));
    commandMap.put("/board/list", new BoardListCommand(boardDao));
    commandMap.put("/board/detail", new BoardDetailCommand(boardDao));
    commandMap.put("/board/update", new BoardUpdateCommand(boardDao));
    commandMap.put("/board/delete", new BoardDeleteCommand(boardDao));
    
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
  }
  @Override
  public void contextDestroyed(Map<String, Object> context) {
    try {
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
  }

}
