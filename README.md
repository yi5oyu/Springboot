# Spring Boot (3.3.3)
    react-native 연동 | 보안 (Spring Security, JWT, OAuth 2.0) | NoSQL(Redis)

Gradle 8.8 | Java 17

<details>
<summary>Tips</summary>

### 프로젝트 생성
    - Spring initializr
    https://start.spring.io/

### Intellj

#### 가독성/편의성
    application.properties을 application.yml로 변경

    root > src > main > resources > application.yml
    
    --계층 구조로 그룹화--
    > application.properties
    server.port=8080 

    > application.yml
    server:
      port: 8080 
    
    --속성 중첩--
    > application.properties
    spring.application.name=springboot
    spring.profiles.active=default

    > application.yml
    spring:
      application:
        name: springboot
      profiles:
        active: default

#### 환경변수
`설정:` `Run/Debug Configurations(상단 바)` `>` `Edit Configurations...` `>` `Environment variables`   
`사용:` `${변수명}`

</details>

## 보안
### Spring Security (6.3.3)
    implementation 'org.springframework.boot:spring-boot-starter-security'
[**> Security Config**](https://github.com/yi5oyu/Study/blob/main/SpringBoot/Spring%20Security/SecurityConfig.java)

### JWT


## NoSQL

### Redis

## AI

### Spring AI

#### ollama

### LM Studio

## OPEN API
    API에 접근하기 위한 API key 필요
### Naver
    https://developers.naver.com
`Application(헤더)` `>` `내 애플리케이션` `>` `Application 등록` `>` `애플리케이션 정보` `>` `Client ID, Client Secret`   

    - applicatim.yml
    naver:
      client: 
        id: ${Client_ID}
          secret: ${Client_Sercet}

[> Example(Controller)](https://github.com/yi5oyu/Study/blob/main/API/OPEN%20API/NAVER/SearchController.java)

### KaKao
    https://developers.kakao.com
`내 애플리케이션(헤더)` `>` `애플리케이션 추가` `>` `플랫폼(앱 설정)` `>` `플랫폼 등록` `>` `앱 키(앱 설정)`

    - applicatim.yml
    kakao:
      api: 
        key: ${Kakao_API}

[> Example(Controller)](https://github.com/yi5oyu/Study/blob/main/API/OPEN%20API/KAKAO/KakaoSearchController.java)

[//]: # (### Google)
[//]: # (    https://developers.google.com/?hl=ko)
[//]: # (https://console.cloud.google.com/)
[//]: # (    https://developers.google.com/maps?hl=ko)

### SK openAPI (Tmap)
    https://openapi.sk.com/
`대시보드` `>` `앱` `>` `앱 만들기` `>` `API 선택` `>` `API 사용 요금` `>` `사용하기` `>` `사용 신청하기` `>` `대시보드` `>` `생선한 앱 선택` `>` `앱키`