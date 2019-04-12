package com.eomcs.lms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(basePackages="com.eomcs.lms")

// 트랜잭션을 설정한 파일을 로딩한다.
// => Spring IoC 컨테이너는 @ImportResource 애노테이션에 지정된 설정 파일에 따라 객체를 준비한다.
@ImportResource("classpath:/com/eomcs/lms/conf/tx-context.xml")
@EnableWebMvc // 이게 없다면 매핑이 불가능.
public class AppConfig {

  final static Logger logger = LogManager.getLogger(AppConfig.class);

  public AppConfig() {
    logger.debug("AppConfig 객체 생성...");
  }
}

// Web MVC와 관련된 애노테이션을 처리하는 객체를 추가한다.
// Gradle에 추가해 프로젝트의 애노테이션을 처리를 대신 받는다.

// @Controller, @RequestMapping, @RequestParam, @RequestHeader
// 애노테이션을 처리할 객체를 추가해야 한다.
// 자바 클래스를 설정할 때 AppConfig에 @EnableWebMvc를 붙인다
// XML로 설정하려면 <mvc:annotation-driven/> 태그를 추가


