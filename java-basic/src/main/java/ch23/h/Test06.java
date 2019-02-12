// Apache HttpComponent 사용 - HttpGet 클래스
package ch23.h;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test06 {

  public static void main(String[] args) throws Exception {
    // HttpGet 클래스
    // => www.apache.org 사이트에서 제공하는 HttpComponent 라이브러리에 들어 있는 클래스이다.
    // => 사용하려면 먼저 외부 라이브러리를 프로젝트로 가져와야 한다.
    //      1) mvnrepository.com에서 apache httpclient 키워드로 검색한다.
    //      2) 최신 라이브러리 정보를 bulid.gradle
    // 1) HTTP 요청을 수행할 객체를 준비한다.
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet get = new HttpGet("https://www.naver.com");
    CloseableHttpResponse response = httpClient.execute(get);

    HttpEntity entity = response.getEntity();
    
    if (entity != null) {
    System.out.printf("응답 데이터 크기 => %d\n", entity.getContentLength());
    System.out.printf("응답 데이터의 MIME 타입 => %s\n", entity.getContentType());
    System.out.println("-----------------------------------------------------------------");
    }
    // getContent()의 리턴 값은 InputStream 객체이다.
    // InputStream을 가지고 데이터를 읽으려면 입출력 코딩을 작성해야 한다.
    // => 이 부분을 대신 해주는 도우미 클래스가 있다.
    String content = EntityUtils.toString(entity);
    System.out.println(content);
  }
}