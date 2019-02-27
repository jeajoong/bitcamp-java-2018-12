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
    commandMap.put("/lesson/add", new LessonAddCommand(keyboard, lessonDao));
    commandMap.put("/lesson/list", new LessonListCommand(keyboard, lessonDao));
    commandMap.put("/lesson/detail", new LessonDetailCommand(keyboard, lessonDao));
    commandMap.put("/lesson/update", new LessonUpdateCommand(keyboard, lessonDao));
    commandMap.put("/lesson/delete", new LessonDeleteCommand(keyboard, lessonDao));

    MemberDaoImpl memberDao = new MemberDaoImpl(con);
    commandMap.put("/member/add", new MemberAddCommand(keyboard, memberDao));
    commandMap.put("/member/list", new MemberListCommand(keyboard, memberDao));
    commandMap.put("/member/detail", new MemberDetailCommand(keyboard, memberDao));
    commandMap.put("/member/update", new MemberUpdateCommand(keyboard, memberDao));
    commandMap.put("/member/delete", new MemberDeleteCommand(keyboard, memberDao));

    BoardDaoImpl boardDao = new BoardDaoImpl(con);
    commandMap.put("/board/add", new BoardAddCommand(keyboard, boardDao));
    commandMap.put("/board/list", new BoardListCommand(keyboard, boardDao));
    commandMap.put("/board/detail", new BoardDetailCommand(keyboard, boardDao));
    commandMap.put("/board/update", new BoardUpdateCommand(keyboard, boardDao));
    commandMap.put("/board/delete", new BoardDeleteCommand(keyboard, boardDao));
    
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
