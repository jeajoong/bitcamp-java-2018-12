package study.A28_annotation.e;

public @interface MyAnnotation5 {
  String value();
  String name() default "홍길동";
  int age() default 20;
  boolean working() default true;
}
