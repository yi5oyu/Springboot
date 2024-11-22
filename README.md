# Spring Boot (3.3.3)
    FE React-Native
    보안 Spring Security, JWT, OAuth 2.0
    NoSQL Redis

Gradle 8.8 | Java 17

## 목차


### Spring 
    Java 애플리케이션 개발을 위한 포괄적인 인프라 제공
    외부 애플리케이션 서버에서 실행(Apache Tomcat, Jetty 등...)
    war 파일 생성

1. **제어 역전(Inversion of Control, IoC)**   
    사용할 객체를 직접 생성하지 않고 객체의 생명주기 관리를 스프링 컨테이너 or IoC 컨테이너에 위임    
    제어 역전을 통해 의존성 주입, 관점 지향 프로그래밍 등이 가능

`@Component`

    클래스를 빈(Bean)으로 지정
    
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Indexed
    public @interface Component {
        String value() default "";
    }

`@Target`

    

2. **의존성 주입(Dependency Injection, DI)**   
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

3. **관점 지향 프로그래밍(Aspect Oriented Programming, AOP)**   
    로깅, 보안, 트랜잭션 관리등의 관심사 분리를 가능하게 하여 모듈화를 개선함

### Spring boot   
    스프링 부트는 spring framework 개선
    개발 환경 설정 간소화(미리 설정된 스타터 프로젝트로 외부 라이브러리를 최적화해 제공)
    WAS 내장(Tomcat) jar 파일 생성

1. **의존성 관리 간소화**    
    `스타터`: 일반적인 개발 시나리오에 필요한 의존성들을 하나의 묶음으로 제공
    `버전 충돌`: 의존성 내의 여러 라이브러리 버전이 모든 의존성에 맞게 동기화
   
3. **배포 간소화**    
   스프링 부트 플러그인이 모든 의존성을 결과 JAR에 압축    
   
4. **내장 WAS**   
    특별한 설정 없이도 Tomcat 실행 가능

5. **자동 설정**   
   애플리케이션에 추가된 라이브러리를 실행하는데 필요한 환경 설정을 알아서 찾아줌(의존성을 추가하면 프레임워크가 자동으로 관리)

6. **모니터링**   
 Spring Boot Actuator : 서비스를 운영하는 시기에 해당 시스템이 사용하는 스레드, 메모리, 세션 등 주요 요소들 모니터링
   
#### Spring Web(Spring MVC)

1. **관심사 분리(Separation of Concerns)**    
    프로그램을 각기 다른 기능적 측면으로 분리, 각 부분이 특정 역할만 수행하도록 하는 설계 원칙   
    코드의 복잡성을 줄이고, 테스트 확장 용이   
   
    계층화된 아키텍처(Layered Architecture)
    애플리케이션을 각각 특정 작업을 담당하는 논리적 계층으로 나눔   

Controller 계층   

`@RestController`

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Controller
    @ResponseBody
    public @interface RestController {
        @AliasFor(
            annotation = Controller.class
        )
        String value() default "";
    }

<details>
<summary>Controller + ResponseBody</summary>

`@Controller`

    // RestController: RESTful 웹 서비스를 위한 컨트롤러 정의
    @Controller or @RestController(@Controller + @ResponseBody)
    // URL 패턴 지정 (value = "/hello", method = RequestMethod.GET)
    @RequestMapping("/api/users")
    // HTTP 요청 매핑 (@RequestMapping, @GetMapping, @PostMapping, @PutMapping...)
    public class UserController {
        @GetMapping("/id")
        
    }

`@ResponseBody`



</details>

    https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller.html
    
    

[> Spring VS Spring boot](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EA%B8%B0%EB%B3%B8%20%EA%B0%9C%EB%85%90%20%EC%A0%95%EB%A6%AC/SpringBoot%20%ED%8A%B9%EC%A7%95)

#### Spring Boot DevTools
    생산성을 높이기 위한 도구 모음

1. 자동 재시작(Automatic Restart)     
    소스 코드나 설정 파일에 변경이 발생하면 자동으로 애플리케이션 다시 시작     
    개발 중 코드 변경을 바로 반영할 수 있고 클래스 로더(classloader)를 활용하여 애플리케이션 빠르게 재시작함      
   
2. 라이브 리로드(Live Reload)      
    정적 리소스(HTML, CSS, JavaScript) 변경을 브라우저에 즉시 반영      
    브라우저 플러그인(LiveReload)을 사용하여 변경된 내용을 자동으로 로드     
    
3. 속성 기본값 재정의(Property Defaults)    
    캐시 설정 최적화: 템플릿 엔진(Thymeleaf, FreeMarker 등)의 캐시를 비활성화하여 변경 사항을 바로 확인할 수 있음    
    H2 콘솔 자동 활성화: H2 데이터베이스를 사용할 경우 자동으로 H2 콘솔 활성화    

4. 개발 및 프로덕션 설정 분리    
   application.properties or application.yml 파일에 spring.profiles.active=dev 설정을 추가하여 개발 환경과 프로덕션 환경을 쉽게 분리할 수 있음    
   개발 환경에서만 활성화, 배포 환경에서는 비활성화됨      


#### Lombok
    반복적으로 작성해야 하는 코드를 줄여주는 라이브러리
    코드의 가독성을 높이고 개발 생산성을 향상시킴

    // Lombok 없는 코드
    public class User {
    
        private Long id;
        private String name;
        private String email;
    
        // 기본 생성자
        public User() {}
    
        // 매개변수있는 생성자
        public User(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
    
        // Getters, Setters
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getEmail() {
            return email;
        }
    
        public void setEmail(String email) {
            this.email = email;
        }
    
        // toString 재정의
        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    
        // equal, hashCode 재정의
        @Override
        public boolean equals() {
            ...
        }
    
        @Override
        public int hashCode() {
            ...
        }
    }

    // Lombok 코드
    @Data // getters, setters, toString, equals, hashCode
    @NoArgsConstructor // 기본 생성자
    @AllArgsConstructor // 매개변수있는 생성자
    public class User {
        private Long id;
        private String name;
        private String email;
    }

[> 그 외 어노테이션](https://github.com/yi5oyu/Study/tree/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/Lombok)   

#### Spring Data JPA   
    JPA를 더 쉽고 편리하게 사용할 수 있도록 도와주는 Spring 프레임워크의 모듈
    
    JPA(Java Persistence API): 자바 애플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스
    ORM(object-relational mapping): 객체와 관계형 데이터베이스 테이블 매핑(객체 지향적으로 프로그래밍을 하면서 관계형 데이터베이스를 사용할 수 있음)
    

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
