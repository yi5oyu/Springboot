<div align="center">

# [EAR WAR JAR](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EB%B0%B0%ED%8F%AC%20%EB%B0%A9%EB%B2%95(WAR%2CJAR))

`Java 어플리케이션 패키징 포맷`    

</div>

Java 기반의 백엔드 애플리케이션을 개발하고 배포할 때 컴파일된 코드와 자원들을 묶는 압축 방식

---

## EAR(Enterprise ARchive)

`대규모 엔터프라이즈 환경을 위해 여러 개의 JAR와 WAR 파일을 하나로 묶은 통합 패키지`

WebLogic, Jeus, JBoss 같은 거대한 Full Java EE(Jakarta EE) 애플리케이션 서버가 필요  

- 구조: META-INF/application.xml, 다수의 JAR 파일(비즈니스 로직), 다수의 WAR 파일(웹 모듈)

## WAR(Web application ARchive)

`JSP, Servlet 등 웹 서비스를 제공하기 위한 규격에 맞춰진 웹 어플리케이션 패키지`

Tomcat, Jetty 같은 웹 애플리케이션 서버(WAS)에 배포되어함  

- 구조: WEB-INF/, web.xml, JSP, HTML, CSS, JavaScript, 정적 이미지 등...

## JAR(Java ARchive)

JRE(Java Runtime Environment)만 있으면 JVM 위에서 독립적으로 바로 실행 가능  

- 구조: .class 파일, 속성 파일, 메타데이터, 기타 라이브러리.

> 가벼운 JAR 파일을 Docker Image로 만들어 배포하는 것이 CI/CD 파이프라인 구성에 유리
