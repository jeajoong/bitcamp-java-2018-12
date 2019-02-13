// 11단계: 서비스 클래스의 일반화(상속)를 수행한다.
package com.eomcs.lms.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardService extends AbstractService<Board> {

  List<Board> lists;

  ObjectInputStream in;
  ObjectOutputStream out;
  String filepath;

  public void init(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }
  
  @SuppressWarnings("unchecked")
  public void loadData(String filepath) {
    this.filepath = filepath;
    
    try (ObjectInputStream in = new ObjectInputStream(
        new BufferedInputStream(
            new FileInputStream(this.filepath)))) {
      
      lists = (List<Board>) in.readObject();
      
    } catch (Exception e) {
      lists = new ArrayList<Board>();
      throw new RuntimeException("게시글 파일 로딩 오류!", e);
    }
  }
  
  public void saveData() throws Exception {
    try (ObjectOutputStream out = new ObjectOutputStream(
        new BufferedOutputStream(
            new FileOutputStream(this.filepath)))) {
    
      out.writeObject(lists);
      
    } catch (Exception e) {
      throw new Exception("게시글 파일 저장 오류!", e);
    }
  }
  
  public void execute(String request) throws Exception {

    switch (request) {
      case "/list/add":
        add();
        break;
      case "/list/list":
        list();
        break;
      case "/list/detail":
        detail();
        break;
      case "/list/update":
        update();
        break;
      case "/list/delete":
        delete();
        break;  
      default:
        out.writeUTF("FAIL");
    }
    out.flush();
  }

  private void add() throws Exception {
    out.writeUTF("OK");
    out.flush();
    lists.add((Board)in.readObject());
    out.writeUTF("OK");
  }

  private void list() throws Exception {
    out.writeUTF("OK");
    out.flush();
    out.writeUTF("OK");
    out.writeObject(lists);
  }

  private void detail() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int no = in.readInt();

    for (Board b : lists) {
      if (b.getNo() == no) {
        out.writeUTF("OK");
        out.writeObject(b);
        return;
      }
    }

    out.writeUTF("FAIL");
  }

  private void update() throws Exception {
    out.writeUTF("OK");
    out.flush();
    Board list = (Board) in.readObject();

    int index = 0;
    for (Board b : lists) {
      if (b.getNo() == list.getNo()) {
        lists.set(index, list);
        out.writeUTF("OK");
        return;
      }
      index++;
    }

    out.writeUTF("FAIL");
  }

  private void delete() throws Exception {
    out.writeUTF("OK");
    out.flush();
    int no = in.readInt();

    int index = 0;
    for (Board b : lists) {
      if (b.getNo() == no) {
        lists.remove(index);
        out.writeUTF("OK");
        return;
      }
      index++;
    }

    out.writeUTF("FAIL");    
  }

}







