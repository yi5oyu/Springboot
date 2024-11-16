# Spring Boot (3.3.3)
    FE React-Native
    보안 Spring Security, JWT, OAuth 2.0
    NoSQL Redis

Gradle 8.8 | Java 17

## 목차


### Spring 
    Java 애플리케이션 개발을 위한 포괄적인 인프라 제공
    외부 애플리케이션 서버에서 실행(Apache Tomcat)
    WAR 파일 생성

1. **제어 역전(Inversion of Control)**
    사용할 객체를 직접 생성하지 않고 객체의 생명주기 관리를 스프링 컨테이너 or IoC 컨테이너에 위임
    제어 역전을 통해 의존성 주입, 관점 지향 프로그래밍 등이 가능

2. **의존성 주입(Dependency Injection)**
    객체 간의 결합도를 낮춰 코드의 재사용성을 높이고 유지보수를 쉽게 만듬   
    스프링 컨테이너가 자동으로 의존성을 주입/타입을 기반으로 의존성을 찾아 주입

**생성자 주입**
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
**필드 주입**

    @Autowired
    private UserService userService;

**Setter 주입**

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

`@Autowired`

    빈(Bean) 객체를 자동으로 주입

    구성요소
    // 어노테이션이 적용될 수 있는 위치를 지정(생성자, 메서드, 매개변수, 필드, 어노테이션에 사용할 수 있음)
    @Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
    // 어노테이션의 지속 기간을 지정(런타임 시점까지 유지)
    @Retention(RetentionPolicy.RUNTIME)
    // 어노테이션을 사용하는 요소가 Javadoc 같은 문서화 도구에 의해 문서화되도록 함(자동으로 문서화에 포함되어 해당 의존성 주입이 코드 문서에 잘 표시됨)
    @Documented
    public @interface Autowired {
        // required=true: 스프링은 반드시 해당 빈을 주입해야 함(빈이 존재하지 않으면 NoSuchBeanDefinitionException 발생)
        // required=false:  주입할 수 있는 빈이 없더라도 오류가 발생하지 않음(null 상태로 유지)
        boolean required() default true;
    }

2. **관점 지향 프로그래밍(Aspect Oriented Programming)**
    로깅, 보안, 트랜잭션 관리등의 관심사 분리
3.     
    
### Spring boot 
    


[> Spring VS Spring boot](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EA%B8%B0%EB%B3%B8%20%EA%B0%9C%EB%85%90%20%EC%A0%95%EB%A6%AC/SpringBoot%20%ED%8A%B9%EC%A7%95)

<details>
<summary>etc</summary>

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

## NoSQL
    NoSQL(Not only SQL): SQL만을 사용하지 않는 데이터베이스 관리 시스템

    - 확장성
      여러 서버로 쉽게 분산시켜 확장

    - 유연성
      다양한 형식으로 데이터를 저장할 수 있음

    - 성능
      키-값 형태의 데이터 액세스 패턴에 최적화
      데이터를 여러 서버에 분산, 데이터를 요청하는 사용자/응용 프로그램에 더 가깝운 곳에 저장

### Redis
    Redis(Remote Dictionary Server)
    키-값 저장소 (다양한 데이터 구조 지원)
    간단한 데이터 구조에 빠르게 액세스해야 하는 애플리케이션에 매우 빠르고 적합(캐시, 메시지 브로커, 세션 저장소)

[**> Redis Config**](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/RedisConfig.java)   
[**> Example**](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/RedisService.java)

## 보안
### Spring Security (6.3.3)
    implementation 'org.springframework.boot:spring-boot-starter-security'

    /config/SecurityConfig
    WebSecurityCustomizer: Web Security 예외 설정
    CorsConfigurationSource: CORS 관련 설정
    SecurityFilterChain: 보안 필터 설정
[**> Security Config**](https://github.com/yi5oyu/Study/blob/main/SpringBoot/Spring%20Security/SecurityConfig.java)

[//]: # (### JWT)

## AI

### Spring AI
    Spring Boot 애플리케이션에 AI 기능을 쉽게 통합할 수 있도록 지원하는 라이브러리
    생성형 AI를 쉽게 구현할 수 있도록 추상화/구현체 제공
    OpenAI, Vertex AI, Azure 등...

#### ollama
    https://ollama.com
    로컬 컴퓨터에서 AI 모델을 실행하고 사용할 수 있도록 지원하는 툴
    다양한 AI 모델, 오프라인 실행, 빠른 속도

    ollama run llama3.2

[**> application.yml**](https://github.com/yi5oyu/Study/blob/main/SpringBoot/Spring%20AI/ollama/application.yml)   
[**> build.gradle**](https://github.com/yi5oyu/Study/blob/main/SpringBoot/Spring%20AI/ollama/build.gradle)   
[**> Example(Controller)**](https://github.com/yi5oyu/Study/blob/main/SpringBoot/Spring%20AI/ollama/ChatController.java)

### LM Studio
    https://lmstudio.ai
    로컬 컴퓨터에서 대규모 언어 모델(LLM)을 쉽게 실행
    오픈 소스 LLM(Hugging Face)을 로컬에서 다운로드하고 실행 가능
    
    https://huggingface.co
    Llama, MPT, StarCoder... (ggml/gguf 형식의 모델과 호환됨)
    

[**> Example**](https://github.com/yi5oyu/Study/blob/main/AI/LM%20STUDIO/llamaAPIService.java)

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

[**> Example(Controller)**](https://github.com/yi5oyu/Study/blob/main/API/OPEN%20API/NAVER/SearchController.java)

### KaKao
    https://developers.kakao.com
`내 애플리케이션(헤더)` `>` `애플리케이션 추가` `>` `플랫폼(앱 설정)` `>` `플랫폼 등록` `>` `앱 키(앱 설정)`

    - applicatim.yml
    kakao:
      api: 
        key: ${Kakao_API}

[**> Example(Controller)**](https://github.com/yi5oyu/Study/blob/main/API/OPEN%20API/KAKAO/KakaoSearchController.java)

[//]: # (### Google)
[//]: # (    https://developers.google.com/?hl=ko)
[//]: # (https://console.cloud.google.com/)
[//]: # (    https://developers.google.com/maps?hl=ko)

### SK openAPI (Tmap)
    https://openapi.sk.com
`대시보드` `>` `앱` `>` `앱 만들기` `>` `API 선택` `>` `API 사용 요금` `>` `사용하기` `>` `사용 신청하기` `>` `대시보드` `>` `생선한 앱 선택` `>` `앱키`
