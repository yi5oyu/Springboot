<div align="center">

# **Spring Boot**

Spring Boot를 이용한 어플리케이션 개발에 필요한 개념 정리, 테스팅, 연습을 목적으로한 레포지토리

| [Spring & Spring boot](#-spring--spring-boot) • [Framework & Library](#-framework--library) • [보안](#%EF%B8%8F-보안) • [AI](#-ai) • [OPEN API](#-open-api) • [애노테이션](#%EF%B8%8F-애노테이션annotation)  |

</div>

## [Java](https://github.com/yi5oyu/Springboot/tree/master/Java)

<!-- 
#### 개발 환경
<img src="https://img.shields.io/badge/Spring Boot 3-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/> <img src="https://img.shields.io/badge/java 17-%23ED8B00.svg?style=flat-square&logo=openjdk&logoColor=white"/> 
<img src="https://img.shields.io/badge/Gradle 8.8-02303A?style=flat-square&logo=gradle&logoColor=white"/>

<img src="https://img.shields.io/badge/IntelliJ_IDEA-000000?style=flat-square&logo=IntelliJ IDEA&logoColor=white"/> <img src="https://img.shields.io/badge/VS%20Code-0078d7.svg?style=flat-square&logo=visual-studio-code&logoColor=white"/>

<img src="https://img.shields.io/badge/React_Native-20232A?style=flat-square&logo=react&logoColor=61DAFB"/>

<img src="https://img.shields.io/badge/MyBatis-%23007ACC.svg?style=flat-square&logo=MyBatis&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=flat-square&logo=spring&logoColor=white"/>

![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=flat-square&logo=Thymeleaf&logoColor=white)
<img src="https://img.shields.io/badge/JSP-%230074C1.svg?style=flat-square&logo=java&logoColor=white"/>
<img src="https://img.shields.io/badge/Mustache-%23FFDD00.svg?style=flat-square&logo=Mustache&logoColor=black"/>



<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/> <img src="https://img.shields.io/badge/redis-%23DD0031.svg?style=flat-square&logo=redis&logoColor=white"/>

    보안 Spring Security, JWT, OAuth 2.0
    NoSQL Redis
-->

<details>
<summary>🛠️ Settings</summary>

### 🚀 프로젝트 생성
    - Spring initializr
    https://start.spring.io/

#### 기본 설정

`종속성 추가`
    
    Maven: pom.xml
    Gradle: build.gradle

`설정 파일`

    application.properties or application.yml

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

#### ⚙️ 환경변수
`설정:` `Run/Debug Configurations(상단 바)` `>` `Edit Configurations...` `>` `Environment variables`   
`사용:` `${변수명}`

##### dotenv
    implementation 'io.github.cdimascio:dotenv-java:3.0.0'

`.env`   

    root/.env

    DB_USER=admin
    DB_PASS=qwerty

`.gitignore`

    # .env 파일 제외
    .env

`DotenvConfig`

    @Configuration
    public class DotenvConfig {
        public DotenvConfig() {
            Dotenv dotenv = Dotenv.configure().load();
            System.setProperty("DB_USER", dotenv.get("DB_USER"));
            System.setProperty("DB_PASS", dotenv.get("DB_PASS"));
        }
    }

</details>

<details>
<summary>폴더 구조</summary>
    
    Springboottest/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── Springboottest/
    │   │   │           ├── config/    
    │   │   │           ├── controller/
    │   │   │           ├── dto/    
    │   │   │           ├── entity/     
    │   │   │           ├── mapper/
    │   │   │           ├── repository/
    │   │   │           └── service/
    │   │   ├── resources/
    │   │   │   ├── mapper/
    │   │   │   ├── static/
    │   │   │   ├── templates/
    │   │   │   └── application.yml
    │   └── test/
    │       └── java/
    ├── .gitignore
    ├── README.md
    └── build.gradle
    
</details>

## 🎯 Spring & Spring boot      
      
### 📖 관심사 분리(Separation of Concerns, SoC)
     프로그램을 각기 다른 기능적 측면으로 분리, 각 부분이 특정 역할만 수행하도록 하는 설계 원칙 
     모듈화, 유지보수성, 확장성, 재사용성, 코드의 복잡성 감소, 테스트 확장 용이

1. **MVC(Model-View-Controller)**    
    Model(데이터), View(UI), Controller(논리)로 분리    
 
2. **계층화된 아키텍처(Layered Architecture)**    
    애플리케이션을 각각 특정 작업을 담당하는 논리적 계층으로 나눔    
    `프레젠테이션 계층(웹 계층)`: HTTP 요청/응답 처리(Controller)    
    `서비스 레이어 계층`: 비즈니스 로직(Service)     
    `데이터 액세스 계층`: 데이터베이스 상호 작용을 관리(Repository)
   
3. **관점 지향 프로그래밍(AOP)**    
    핵심 비즈니스 로직에서 관심사를 분리할 수 있게 함(로깅, 보안, 트랜잭션 관리...)     
    비즈니스 로직과 보조 기능을 분리해 복잡하게 만들지 않고 애플리케이션 전체에 적용    
    `클린코드`: 반복적인 코드 제거     
    `유연성`: @Aspect을 사용한 추가/수정

4. **제어 역전(Inversion of Control, IoC)**     
    사용할 객체를 직접 생성하지 않고 객체의 생명주기 관리를 스프링 컨테이너 or IoC 컨테이너에 위임    
    제어 역전을 통해 의존성 주입, 관점 지향 프로그래밍 등이 가능     
    `@Component`: 클래스를 스프링 컨테이너가 자동으로 감지하고 빈으로 등록    
    `@Bean`: 메소드가 반환하는 객체를 스프링 컨테이너가 빈으로 등록     
 
5. **의존성 주입(Dependency Injection, DI)**      
    객체 간의 결합도를 낮춰 코드의 재사용성을 높이고 유지보수를 쉽게 만듬    
    스프링 컨테이너가 자동으로 의존성을 주입/타입을 기반으로 의존성을 찾아 주입
   
`생성자 주입`

```java
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    
    public UserService(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }
}
```

`@Autowired`

```java
// Setter 주입
private UserService userService;

@Autowired
public void setUserService(UserService userService) {
    this.userService = userService;
}

// 필드 주입
@Autowired
private UserService userService;

// 메서드 주입
private UserService userService;

@Autowired
public void init(UserService userService) {
    this.userService = userService;
}
```

`Lombok`

```java
// final, @NonNull로 선언된 필드 생성자 자동 생성
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}
/* Lombok이 생성자 자동 생성
public UserController(UserService userService) {
    this.userService = userService;
}
*/
```

6. **설정 관리**    
    설정을 외부화하여 다양한 환경에서 동일한 애플리케이션 코드를 사용할 수 있게 함    
    application.properties or application.yml    

### 💡 Spring 
    Java 애플리케이션 개발을 위한 포괄적인 인프라 제공
    외부 애플리케이션 서버에서 실행(Apache Tomcat, Jetty 등...)
    war 파일 생성

#### XML 설정

`새로운 서비스 추가마다 반복되는 설정`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop">

    <!-- 데이터소스 설정 -->
    <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/testdb"/>
        <property name="username" value="root"/>
        <property name="password" value="password"/>
        <property name="initialSize" value="5"/>
        <property name="maxTotal" value="20"/>
    </bean>

    <!-- JPA EntityManager 설정 -->
    <bean id="entityManagerFactory" 
          class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="packagesToScan" value="com.example.entity"/>
        <property name="jpaVendorAdapter">
            <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter"/>
        </property>
        <property name="jpaProperties">
            <props>
                <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
                <prop key="hibernate.show_sql">true</prop>
                <prop key="hibernate.hbm2ddl.auto">update</prop>
            </props>
        </property>
    </bean>

    <!-- 트랜잭션 매니저 -->
    <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
        <property name="entityManagerFactory" ref="entityManagerFactory"/>
    </bean>

    <!-- 서비스 빈들 -->
    <bean id="userService" class="com.example.service.UserService">
        <property name="userRepository" ref="userRepository"/>
	<!-- 의존성이 추가될 때마다 XML 수정 필요 -->
    </bean>
    
    <bean id="userRepository" class="com.example.repository.UserRepositoryImpl">
        <property name="entityManager" ref="entityManagerFactory"/>
    </bean>

    <!-- AOP 설정 -->
    <aop:config>
        <aop:aspect id="loggingAspect" ref="loggingService">
            <aop:pointcut id="serviceLayer" 
                         expression="execution(* com.example.service.*.*(..))"/>
            <aop:before pointcut-ref="serviceLayer" method="logBefore"/>
        </aop:aspect>
    </aop:config>

    <!-- 컴포넌트 스캔 -->
    <context:component-scan base-package="com.example"/>
    <tx:annotation-driven transaction-manager="transactionManager"/>
</beans>
```

#### 어노테이션 설정

`XML <bean> 태그 대신 어노테이션 사용`

```java
@Service
public class UserService {
    // XML <property> 태그 대신 @Autowired
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;
    
    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        emailService.sendWelcomeEmail(savedUser);
        return savedUser;
    }
}

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }
}
```

### 💡 Spring boot   
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

```yaml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/testdb
    username: root
    password: password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

```java
@SpringBootApplication  // @Configuration + @EnableAutoConfiguration + @ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

6. **모니터링**   
   Spring Boot Actuator : 서비스를 운영하는 시기에 해당 시스템이 사용하는 스레드, 메모리, 세션 등 주요 요소들 모니터링

[> Spring VS Spring boot](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EA%B8%B0%EB%B3%B8%20%EA%B0%9C%EB%85%90%20%EC%A0%95%EB%A6%AC/SpringBoot%20%ED%8A%B9%EC%A7%95)      
[> WAR VS JAR](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EB%B0%B0%ED%8F%AC%20%EB%B0%A9%EB%B2%95(WAR%2CJAR))

<hr>

## 📦 Framework & Library
    Framework: 애플리케이션 개발의 기본 구조를 제공하는 소프트웨어 플랫폼
    Library: 특정 기능을 수행하는 코드 묶음

### [Spring Web(Spring MVC)](https://github.com/yi5oyu/Study/blob/main/SpringBoot/Spring%20MVC)
    https://docs.spring.io/spring-framework/reference/web/webmvc.html
    
    웹 애플리케이션에서 HTTP 요청과 응답을 효율적으로 처리하기 위해 MVC 패턴을 사용하는 프레임워크

`Spring MVC 아키텍처 계층`

`View 계층`: 사용자 인터페이스(UI)        
`Model 계층`: 데이터 정의(DTO, Entity)       
`Controller 계층`: HTTP 요청 처리/응답 반환    
`Service 계층`: 비즈니스 로직 처리     
`Repository 계층`: 데이터 접근, DB 외부 데이터 소스 CRUD 작업 수행   

#### HTTP 프로토콜(HyperText Transfer Protocol)
    웹에서 클라이언트와 서버 간의 데이터 전송을 위한 애플리케이션 계층 프로토콜

- 무상태(stateless)     
  각 요청은 독립적이며 이전 요청의 정보를 저장하지 않음
  상태를 유지하기 위해 쿠키, 세션 등... 필요함

- HTTP 메서드     
  `GET`: 조회    
  `POST`: 생성   
  `PUT`: 업데이트(대체)   
  `PATCH`: 업데이트(일부 변경)      
  `DELETE`: 삭제     

- HTTP 상태 코드     
  `200`: OK(요청 처리)     
  `201`: Created(생성)     
  `400`: Bad Request(잘못된 요청)    
  `401`: Unauthorized(인증 필요)     
  `404`: Not Found(찾을 수 없음)     
  `500`: Internal Server Error(서버 내부 오류)    

> HTTPS(Hypertext Transfer Protocol Secure)       
> HTTP + 보안       
> SSL/TLS 프로토콜을 사용해 데이터 암호화        
> 포트: HTTP 80 / HTTPS 443        

[> HTTP vs HTTPS](https://github.com/yi5oyu/Study/blob/main/WS/Nginx/HTTPS/HTTP%20VS%20HTTPS)     
[> SSL/TLS](https://github.com/yi5oyu/Study/blob/main/WS/Nginx/HTTPS/SSL)    

#### [REST API(Representational State Transfer API)](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/%EC%A0%95%EC%9D%98)
    HTTP 프로토콜을 기반으로 클라이언트와 서버 간에 자원을 주고받기 위해 설계된 아키텍처

`REST`: 주고 받는 자원에 이름을 정하고 주소(URI)에 명시해 HTTP 메서드를 통해 해당 자원의 상태를 주고받는 것    
`API`: 애플리케이션에서 제공하는 인터페이스. API를 통해 서버 or 프로그램 사이를 연결할 수 있음     
`RESTful`: REST 아키텍처 원칙을 따르는 웹 서비스 인터페이스  

`REST API CURD구현`

```java
    @RestController
    @RequestMapping("/users")
    public class UserController {
        @Autowired
        private final UserService userService;
        // Read
        @GetMapping 
        public ResponseEntity<List<User>> getAllUsers() {
            List<User> users = userService.getAllUsers();
            // HTTP 200 응답
            return ResponseEntity.ok(users);
        }
        // Create
        @PostMapping 
        public ResponseEntity<User> createUser(@RequestBody User user) {
            User createdUser = userService.createUser(user);
            // HTTP 201 응답
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }
        // Update
        @PutMapping("/{id}") 
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
            User updatedUser = userService.updateUser(id, user);
            // HTTP 200 응답
            return ResponseEntity.ok(updatedUser);
        }
        // Delete
        @DeleteMapping("/{id}") 
        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            // HTTP 204 응답
            return ResponseEntity.noContent().build();
        }
    }
```

[> ResponseEntity](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/ResponseEntity)      
[> HttpStatus 상태코드](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/HttpStatus)       

[> Spring MVC](https://github.com/yi5oyu/Study/blob/main/SpringBoot/Spring%20MVC)     

<hr>

### Spring WebFlux

    비동기, 논블로킹 기반의 리액티브 웹 프레임워크
    높은 동시성과 적은 리소스로 대용량 트래픽 처리

    높은 동시성이 필요한 API 게이트웨이(API 요청을 받아 처리하고 백엔드 서비스에 전달하는 역할)
    마이크로서비스 간 비동기 통신
    실시간 스트리밍(채팅, 알림, 모니터링)
    I/O 집약적인 애플리케이션(외부 API 호출 많음)

#### [동기/비동기 vs 블로킹/논블로킹](https://github.com/yi5oyu/Study/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/%EB%8F%99%EA%B8%B0%EB%B9%84%EB%8F%99%EA%B8%B0_%EB%B8%94%EB%A1%9C%ED%82%B9%EB%85%BC%EB%B8%94%EB%A1%9C%ED%82%B9)

`동기(Synchronous)` vs `비동기(Asynchronous)`
- **동기**: 작업을 순차적으로 실행, 이전 작업 완료 후 다음 작업 시작
- **비동기**: 작업을 동시에 실행, 완료를 기다리지 않고 다음 작업 진행

`블로킹(Blocking)` vs `논블로킹(Non-blocking)`  
- **블로킹**: 작업이 완료될 때까지 쓰레드가 대기(멈춤)
- **논블로킹**: 작업 완료를 기다리지 않고 즉시 제어권 반환

```java
// Spring MVC(동기 + 블로킹)
@GetMapping("/users/{id}")
public User getUser(@PathVariable String id) {
    // DB 호출(블로킹 대기)
    User user = userRepository.findById(id);
    
    // 외부 API 호출(블로킹 대기)
    Profile profile = externalApiService.getProfile(id);
    
    user.setProfile(profile);
    return user;
}

// 쓰레드: [DB 호출 대기] -> [API 호출 대기] -> [응답]
// 순차적 실행, 각 단계에서 쓰레드 블로킹
```
    
#### [리액티브 프로그래밍](https://github.com/yi5oyu/Study/blob/main/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D/%EB%A6%AC%EC%95%A1%ED%8B%B0%EB%B8%8C%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D)

    데이터 스트림과 변화 전파에 기반한 프로그래밍 패러다임
    비동기, 논블로킹, 이벤트 기반 처리로 높은 처리량과 확장성 제공

```java
// Spring WebFlux(비동기 + 논블로킹)
@GetMapping("/users/{id}")
public Mono<User> getUser(@PathVariable String id) {
    Mono<User> userMono = userRepository.findById(id);
    Mono<Profile> profileMono = externalApiService.getProfile(id);
    
    // 두 작업을 동시에 실행
    return userMono.zipWith(profileMono)
            .map(tuple -> {
                User user = tuple.getT1();
                user.setProfile(tuple.getT2());
                return user;
            });
}

// Event-Loop: [DB 호출 시작] + [API 호출 시작] -> [두 결과 합성] -> [응답]
// 동시 실행, 쓰레드 논블로킹
```

#### Mono/Flux

`Mono<T>: 0개 또는 1개의 요소를 비동기적으로 처리`

```java
@GetMapping("/user/{id}")
public Mono<User> getUser(@PathVariable String id) {
    return userRepository.findById(id)
            .defaultIfEmpty(new User("guest"));
}
```

`Flux<T>: 0개 이상의 요소 스트림을 비동기적으로 처리`

```java
@GetMapping("/users")
public Flux<User> getAllUsers() {
    return userRepository.findAll()
            .filter(User::isActive)
            .take(100); // 최대 100개만
}

// 실시간 스트림 (Server-Sent Events)
@GetMapping(value = "/users/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Flux<User> streamUsers() {
    return Flux.interval(Duration.ofSeconds(1))
            .map(i -> new User("user" + i))
            .take(10);
}
```

#### WebClient

`리액티브 HTTP 클라이언트`

```java
@Service
public class ApiService {
    private final WebClient webClient;
    
    public ApiService() {
        this.webClient = WebClient.builder()
            .baseUrl("https://api.example.com")
            .build();
    }
    
    // GET 요청
    public Mono<User> getUser(String id) {
        return webClient
            .get()
            .uri("/users/{id}", id)
            .retrieve()
            .bodyToMono(User.class);
    }
    
    // POST 요청
    public Mono<User> createUser(User user) {
        return webClient
            .post()
            .uri("/users")
            .bodyValue(user)
            .retrieve()
            .bodyToMono(User.class);
    }
}
```

#### MVC vs WebFlux 

**Spring MVC**
- 전통적인 CRUD 웹 애플리케이션
- 블로킹 라이브러리 사용(JDBC, JPA)
- 간단한 REST API
- 복잡한 비즈니스 로직(디버깅 용이성)

**Spring WebFlux**  
- 높은 동시성 필요(1000+ 동시 사용자)
- I/O 집약적(외부 API 호출 많음)
- 실시간 스트리밍 필요
- 마이크로서비스 아키텍처
- API 게이트웨이
: 대용량 트래픽 API 서버, 실시간 알림 시스템

`하이브리드`

```yaml
# 동일한 애플리케이션에서 두 방식 모두 사용 가능
spring:
  profiles:
    active: hybrid

# MVC: 관리자 페이지(CRUD)
# WebFlux: API 서버(높은 동시성)
```

<hr>

### 🛢️ DB
    데이터 접근 기술
    DB에 보관하고 관리하는 리포지토리
    
`JDBC(Java Database Connectivity)`

    DB에 직접 연결, SQL 쿼리 실행하는 기본적인 방법
    * 세밀한 제어 가능
    
`SQL Mapper`

    MyBatis
    SQL 쿼리를 XML 파일/어노테이션으로 정의, SQL 쿼리와 자바 객체 매핑
    * SQL 최적화

`ORM(JPA/Hibernate) `

    Spring Data JPA or Querydsl
    객체와 DB 테이블을 매핑, SQL을 직접 작성하지 않고도 객체를 통해 DB 조작할 수 있음
    * 생산성, 유지보수

<!--

MySQL
Oracle
H2 Database
-->

#### [H2 Database](https://github.com/yi5oyu/Study/tree/main/SpringBoot/DB/H2)
    개발 및 테스트 환경에서 사용되는 경량 데이터베이스
    표준 SQL, 웹 콘솔, Spring Boot 통합

`인메모리 모드`: 데이터를 메모리에 저장, 빠른 속도 제공    
`임베디드 모드`: 애플리케이션에 내장되어 사용     
`서버 모드`: 데이터베이스 서버로 실행    

[> 모드](https://github.com/yi5oyu/Study/blob/main/SpringBoot/DB/H2/%EB%AA%A8%EB%93%9C)       

`application.yml`
```yaml
    spring:
      datasource:
        # 인메모리
        url: jdbc:h2:mem:testdb
        driver-class-name: org.h2.Driver
        username: sa
        password: password
      h2:
        console:
          enabled: true
```
`http://localhost:8080/h2-console`    
<details>
<summary>연결</summary>

`접속`    

<img width="480" alt="{CAEB2A3F-B771-454E-B03E-8BF5B362DA3E}" src="https://github.com/user-attachments/assets/4f912cbb-f609-45e3-ab38-7bd3853041b5"><br>

`테이블 생성`    

<img width="480" alt="{CDDE4D73-8498-472B-B72B-0B4CEADFFEC7}" src="https://github.com/user-attachments/assets/5102a416-7bdc-4085-b4e3-847699e1bcdb">
</details>


#### [Mybatis](https://github.com/yi5oyu/Study/tree/main/MyBatis)
    SQL 쿼리를 Java 코드에서 분리하여 XML 파일 or 어노테이션으로 관리

`application.yml`

    mybatis:
      # 패키지 경로 지정
      # 지정된 패키지, 하위 패키지에 있는 클래스들을 자동으로 alias(별칭)으로 등록 
      type-aliases-package: com.example.springboottest.entity
      # xml 있는 위치 지정
      mapper-locations: classpath:mapper/*.xml

`Entity`
```java
    @Data
    public class User {
        private Long id;
        private String name;
        private String email;
    }
```

`Mapper(Interface)/xml`
```java
    @Mapper
    public interface UserMapper {
        List<User> findAll();
        User findById(Long id);
        void insertUser(User user);
        void updateUser(User user);
        void deleteUser(Long id);
    }
```
`Mapper(Interface)/어노테이션`
```java
    @Mapper
    public interface UserMapper {
        @Select("SELECT * FROM users")
        List<User> findAll();
        
        @Select("SELECT * FROM users WHERE id = #{id}")
        User findById(@Param("id") Long id);
        
        @Insert("INSERT INTO users (name, email) VALUES (#{name}, #{email})")
        @Options(useGeneratedKeys = true, keyProperty = "id")
        void insertUser(User user);
        
        @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
        void updateUser(User user);
        
        @Delete("DELETE FROM users WHERE id = #{id}")
        void deleteUser(@Param("id") Long id);
    }
```
[> Interface](https://github.com/yi5oyu/Study/blob/main/MyBatis/Interface)

`Service`
```java
    @Service
    public class UserService {
        @Autowired
        private UserMapper userMapper;

        public List<User> getAllUsers() {
            return userMapper.findAll();
        }
        public User getUserById(Long id) {
            return userMapper.findById(id);
        }
        public void addUser(User user) {
            userMapper.insertUser(user);
        }
        public void updateUser(User user) {
            userMapper.updateUser(user);
        }
        public void deleteUser(Long id) {
            userMapper.deleteUser(id);
        }
    }
```
[> Service](https://github.com/yi5oyu/Study/blob/main/MyBatis/Service)    

`Controller`
```java
    @RestController
    @RequestMapping("/users")
    public class UserController {
        @Autowired
        private UserService userService;
    
        @GetMapping
        public List<User> getAllUsers() {
            return userService.getAllUsers();
        }
        @GetMapping("/{id}")
        public User getUserById(@PathVariable Long id) {
            return userService.getUserById(id);
        }
        @PostMapping
        public void addUser(@RequestBody User user) {
            userService.addUser(user);
        }
        @PutMapping("/{id}")
        public void updateUser(@PathVariable Long id, @RequestBody User user) {
            user.setId(id);
            userService.updateUser(user);
        }
        @DeleteMapping("/{id}")
        public void deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
        }
    }
```
[> Controller](https://github.com/yi5oyu/Study/blob/main/MyBatis/Controller)      

`Mapper.xml`
```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
      "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

    <!-- 
        namespace와 mapper 인터페이스 매핑
        id와 mapper 인터페이스 필드명 매핑
    -->
    <mapper namespace="com.example.springboottest.mapper.UserMapper">
      <resultMap id="UserResultMap" type="com.example.springboottest.entity.User">
        <id property="id" column="id"/>
        <result property="name" column="name"/>
        <result property="email" column="email"/>
      </resultMap>
    
      <select id="findAll" resultMap="UserResultMap">
        SELECT * FROM users
      </select>
      <select id="findById" resultMap="UserResultMap">
        SELECT * FROM users WHERE id = #{id}
      </select>
      <insert id="insertUser" parameterType="com.example.springboottest.entity.User" useGeneratedKeys="true" keyProperty="id">
        INSERT INTO users (name, email) VALUES (#{name}, #{email})
      </insert>
      <update id="updateUser" parameterType="com.example.springboottest.entity.User">
        UPDATE users
        SET name = #{name}, email = #{email}
        WHERE id = #{id}
      </update>
      <delete id="deleteUser">
        DELETE FROM users WHERE id = #{id}
      </delete>

      <!-- 동적 쿼리 -->
      <!-- xml에서 안전한 특수문자 사용 <![CDATA[]]> -->
      <select id="searchUsers" resultType="User" parameterType="hashmap">
        SELECT * FROM users
        <!-- 조건문  -->
        <where>
            <if test="name != null and name != ''">
                name LIKE CONCAT('%', #{name}, '%')
            </if>
            <if test="id != null">
                AND id <![CDATA[ <= ]]> #{id}
            </if>
            <if test="email != null and email != ''">
                email LIKE CONCAT('%', #{email}, '%')
            </if>
        </where>
        <!-- 스위치문 -->
        <choose>
            <when test="order == 0">
                ORDER BY <![CDATA[ id DESC ]]>
            </when>
            <when test="order == 1">
                ORDER BY <![CDATA[ email ASC ]]>
            </when>
            <otherwise>
                ORDER BY name ASC
            </otherwise>
        </choose>
      </select>
    </mapper>
```
[> xml](https://github.com/yi5oyu/Study/blob/main/MyBatis/Mapper.xml)     


[> MyBatis](https://github.com/yi5oyu/Study/tree/main/MyBatis)    


#### [Spring Data JPA](https://github.com/yi5oyu/Study/tree/main/JPA)    
    JPA를 더 쉽고 편리하게 사용할 수 있도록 도와주는 Spring 프레임워크의 모듈
    
    JPA(Java Persistence API): 자바 애플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스
    ORM(object-relational mapping): 객체와 관계형 데이터베이스 테이블 매핑(객체지향적으로 프로그래밍을 하면서 관계형 데이터베이스를 사용할 수 있음)

- 인터페이스 계층 구조   
    Repository > CrudRepository > PagingAndSortingRepository > JpaRepository    
    `Repository`: 최상위 기본 인터페이스(메소드 없음)     
    `CrudRepository`: CRUD(생성, 읽기, 업데이트, 삭제) 메서드    
    `PagingAndSortingRepository`:  페이징, 정렬 메서드    
    `JpaRepository`: 일괄 처리, 메서드 이름을 기반으로 한 쿼리 생성 기능 제공    
    [> JPA 인터페이스 계층 구조](https://github.com/yi5oyu/Study/blob/main/JPA/3.%20SpringDataJPA/JpaRepository.java)

- 사용(MySQL)

`application.yml`
```yaml    
    spring:
      datasource:
        // 환경변수 지정 (jdbc:mysql://localhost:3306/DB이름)
        url: ${DB_URL}
        username: ${DB_USERNAME}
        password: ${DB_PASSWORD}
        driver-class-name: com.mysql.cj.jdbc.Driver
      jpa:
        hibernate:
          // 엔티티 변경사항 데이터베이스 스키마에 자동 업데이트
          ddl-auto: update
        // SQL 쿼리 출력(콘솔/로그)
        show-sql: false
        properties:
          hibernate:
            // SQL 문법 Dialect 지정
            dialect: org.hibernate.dialect.MySQLDialect
```
`Entity`
```java
    @Entity
    // 테이블 이름 명시
    @Table(name = "users")
    public class User {
        // 키(고유 식별자)
        @Id
        // 값 생성 전략 설정(자동으로 생성(키값++)
        // MySQL기준 DB에 AUTO_INCREMENT 설정해야함
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        // 매핑(DB 칼럼과 매핑)
        @Column(name = "a_id")
        private Long id;

        @Column(name = "a_name")
        private String name;
    }
```
`Repository`
```java
    public interface UserRepository extends JpaRepository<User, Long> {
        // 이름으로 검색
        List<User> findByName(String name);
        // 값이 없는 경우(결과 없음) 
        // Null값으로 인한 NullPointerException 예외처리
        Optional<User> findByName(String name);
        // 페이징
        Page<User> findByName(String name, Pageable pageable);
    }
```
`Service`
```java
    @Service
    public class UserService {
        @Autowired
        private UserRepository userRepository;

        // 조건에 맞는 모든 데이터 처리
        public List<User> getUsersName(String name) {
            return userRepository.findByName(name);
        }

        // 하나의 결과값(UNIQUE) or null(결과 없음) 처리
        public Optional<User> getUserName(String name) {
            // isPresent(), isEmpty(), orElse(), orElseThrow()...
            return userRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("유저 찾을 수 없음"));
        }        
        
        public Page<User> getUsersNameWithPaging(String name, int page, int size) {
            // 페이지 번호, 사이즈 설정
            Pageable pageable = PageRequest.of(page, size); 
            return userRepository.findByName(name, pageable);
        }
    }
```
`Controller`
```java
    @RestController
    @RequestMapping("/users")
    public class UserController {
        @Autowired
        private UserService userService;

        @GetMapping("/names")
        public List<User> getAllUsersName(@RequestParam("username") String name) {
            // List<User>
            return userService.getUsersName(name);
        }

        @GetMapping("/name")
        public User getUserName(@RequestParam("username") String name) {
            // Optional<User>
            return userService.getUserName(name);
        }

        @GetMapping("/paged-names")
        public Page<User> getUsersByNameWithPaging(@RequestParam("username") String name, int page, int size) {
            // Page<User>
            return userService.getUsersNameWithPaging(name, page, size); 
        }
    }   
```
[> Optional](https://github.com/yi5oyu/Study/blob/main/JPA/Optional)     
[> 객체지향쿼리](https://github.com/yi5oyu/Study/blob/main/JPA/4.%20JPQL/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%EC%BF%BC%EB%A6%AC)     

##### QueryDSL

    자바 코드로 SQL 문을 작성할 수 있게 해주는 프레임워크
    컴파일 시 오류 발생 방지

`build.gradle`
```gradle
    // spring boot 3 이상 버전과 호환
    implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta'
	annotationProcessor "com.querydsl:querydsl-apt:5.0.0:jakarta"
	annotationProcessor "jakarta.annotation:jakarta.annotation-api"
	annotationProcessor "jakarta.persistence:jakarta.persistence-api"

    def querydslDir = 'src/main/generated'
    // 기존 파일 삭제
    clean {
        delete file(querydslDir)
    }
    // QueryDSL(QClass) 파일 생성
    tasks.withType(JavaCompile) {
        options.generatedSourceOutputDirectory = file(querydslDir)
    }
```
`./gradlew clean build`

`QueryDSLConfig`
```java
    // JPAQueryFactory 빈 등록
    @Configuration
    public class QueryDSLConfig {
    @Autowired
    private EntityManager entityManager;
        @Bean
        public JPAQueryFactory jpaQueryFactory() {
            return new JPAQueryFactory(entityManager);
        }
    }
```
`Repository`
```java
    // UserRepository
    // JPA, QueryDSL 같이 사용
    @Repository
    public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
        Optional<User> findByName(String name);
    }

    // UserRepositoryCustom
    // 작성할 쿼리 인터페이스 메소드 
    public interface UserRepositoryCustom {
        Optional<Long> findIdByName(String name);
    }

    // UserRepositoryCustomlmpl
    // 인터페이스 구현
    @Repository
    public class UserRepositoryCustomImpl implements UserRepositoryCustom  {
        @Autowired
        private JPAQueryFactory queryFactory;
        @Override
        public Optional<Long> findIdByName(String name) {
            QUser user = QUser.user;
            Long userId = queryFactory
                .select(user.id)
                .from(user)
                .where(user.name.eq(name))
                .fetchOne();
            return Optional.ofNullable(userId);
        }
    }
```

#### MyBatis vs JPA

| 구분 | MyBatis | JPA |
|--------------|---------|-----|
| **종류** | SQL Mapper 프레임워크 | ORM(Object-Relational Mapping) 프레임워크 |
| **방식** | SQL 직접 작성(Mapper XML, 어노테이션) | 객체와 테이블 매핑, SQL 자동 생성 |
| **성능 최적화** | 매우 높음 | 보통 |
| **개발 속도** | 보통 | 매우 높음 |
| **복잡한 쿼리** | 용이 | 어려움 |

**MyBatis**
- 복잡한 분석 쿼리가 많은 경우
- 레거시 DB 연동(기존 SQL 활용, 복잡한 테이블 구조 처리)
- 극한의 성능 최적화 필요
- SQL 실력이 높은 경우
: XML 관리 복잡도, 타입 안정성 부족

**JPA**
- 단순한 CRUD 위주 애플리케이션
- 빠른 개발 속도 필요(스타트업, MVP)
- 객체 지향적 설계
- SQL 작성 경험이 부족
: N+1 문제, 복잡한 쿼리 작성 어려움

`하이브리드`

```java
// 트랜잭션 일관성 주의
@Service
public class UserService {
    private final UserRepository userRepository;        // JPA - 단순 CRUD
    private final UserAnalyticsMapper analyticsMapper; // MyBatis - 복잡한 분석
    
    // 일반적인 조회는 JPA
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    // 복잡한 분석은 MyBatis
    public List<SalesReport> getSalesAnalytics() {
        return analyticsMapper.getSalesReport();
    }
}
```

### NoSQL
    NoSQL(Not only SQL): SQL만을 사용하지 않는 데이터베이스 관리 시스템

    - 확장성
      여러 서버로 쉽게 분산시켜 확장

    - 유연성
      다양한 형식으로 데이터를 저장할 수 있음

    - 성능
      키-값 형태의 데이터 액세스 패턴에 최적화
      데이터를 여러 서버에 분산, 데이터를 요청하는 사용자/응용 프로그램에 더 가깝운 곳에 저장

#### [Redis](https://github.com/yi5oyu/Study/tree/main/DB/NoSQL/Redis)
    Redis(Remote Dictionary Server)
    키-값 저장소 (다양한 데이터 구조 지원)
    간단한 데이터 구조에 빠르게 액세스해야 하는 애플리케이션에 매우 빠르고 적합(캐시, 메시지 브로커, 세션 저장소)

<details>
<summary>Window</summary>
    
https://github.com/tporadowski/redis/releases    
Redis-x64-5.0.14.1.msi 설치    

redis-server.exe 실행   
redis-cli.exe 실행   

확인 ping 입력 > pong 출력   

[> 설치](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/%EC%84%A4%EC%B9%98)     
[> 명령어](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/%EB%AA%85%EB%A0%B9%EC%96%B4)    

</details>

`의존성`

    implementation 'org.springframework.boot:spring-boot-starter-data-redis'

`application.yml`
```yaml
    spring:
      redis:
        host: localhost
        port: 6379 # 기본 포트
        password: # 생략 가능
```
[> Redis application.yml](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/application.yml)     

`RedisConfig`
```java
    @Configuration
    public class RedisConfig {
        @Bean
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
            // RedisTemplate: Redis에 데이터를 저장하고 읽기 위한 클래스 (Redis 명령을 실행할 수 있는 메서드 제공)
            // 키(String), 값(Object - 저장하는 데이터 타입)
            RedisTemplate<String, Object> template = new RedisTemplate<>();
            // RedisConnectionFactory: Spring이 Redis 서버와 연결 관리
            template.setConnectionFactory(connectionFactory);
            // Redis에 저장되는 데이터 키를 직렬화
            template.setKeySerializer(new StringRedisSerializer());
            // 값을 문자열로 직렬화
            template.setValueSerializer(new StringRedisSerializer());
            return template;
        }
    }
```    
[> Redis Config](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/RedisConfig.java)    

`RedisService`
```java
    @Service
    public class RedisService {
        @Autowired
        private RedisTemplate<String, Object> redisTemplate;
        // 데이터 저장
        public void saveValue(String key, String value) {
            redisTemplate.opsForValue().set(key, value);
        }
        // 데이터 조회
        public String getValue(String key) {
            return (String) redisTemplate.opsForValue().get(key);
        }
        // 데이터 삭제
        public void deleteValue(String key) {
            redisTemplate.delete(key);
        }
    }
```
[> Redis Service](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/RedisService.java)     

`RedisController`
```java
    @RestController
    public class RedisController {
        @Autowired
        private RedisService redisService;
        // 저장 /save?key=이름&value=데이터
        @PostMapping("/save")
        public void save(String key, String value) {
            redisService.saveValue(key, value);
        }
        // 조회 /get?key=이름
        @GetMapping("/get")
        public String get(String key) {
            return redisService.getValue(key);
        }
        // 삭제 /delete?key=이름
        @DeleteMapping("/delete")
        public void delete(String key) {
            redisService.deleteValue(key);
        }
    }
```
[> Redis Controller](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/RedisController.java)     

<hr>

### 🛠️ TOOLS   

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


#### [Lombok](https://github.com/yi5oyu/Study/blob/main/SpringBoot/Lombok/Entitiy)
    반복적으로 작성해야 하는 코드를 줄여주는 라이브러리
    코드의 가독성을 높이고 개발 생산성을 향상시킴

`POJO`

    POJO(Plain Old Java Object)
    의존성 최소화한 단순한 자바 객체
```java
    // 필드와 기본 메서드(getter/setter), 오버라이드된 메소드 포함
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
```
`Lombok`

    반복적인 작업을 어노테이션 사용해 자동으로 처리
```java
    // Lombok 적용
    @Data // getters, setters, toString, equals, hashCode
    @NoArgsConstructor // 기본 생성자
    @AllArgsConstructor // 매개변수있는 생성자
    public class User {
        private Long id;
        private String name;
        private String email;
    }
```
[> Lombok 어노테이션](https://github.com/yi5oyu/Study/tree/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/Lombok)   

<hr>

### [View Template](https://github.com/yi5oyu/Study/tree/main/SpringBoot/View%20Template)

`렌더링`: 데이터를 화면에 시각적으로 표시하는 과정

- SSR(Server-Side Rendering)
  
      서버에서 HTML 생성하여 클라이언트로 전송
      초기 페이지 로드 빠름
      Thymeleaf
  
- CSR(Client-Side Rendering)

      클라이언트에서 JavaScript를 사용하여 HTML 생성
      초기 로드 상대적으로 느림
      React
    
#### [JSP(JavaServer Pages)](https://github.com/yi5oyu/Study/tree/main/SpringBoot/View%20Template/JSP)
    Java를 기반의 서버 사이드 스크립트 언어
    서블릿으로 변환되어 동적인 웹페이지(HTML) 생성

##### [application.yml](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/JSP/application.yml)

    spring:
      mvc:
        # 뷰 리졸버(View Resolver) 설정
        view:
          prefix: /WEB-INF/views/
          suffix: .jsp

    # 불러올 JSP 파일의 접두사(prefix), 접미사(suffix) 설정
    # /WEB-INF/views/[파일명].jsp
    
##### [build.gradle](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/JSP/build.gradle)
```gradle
    dependencies {
    	implementation 'org.springframework.boot:spring-boot-starter-web'
    
    	// JSP를 컴파일하여 실행 가능한 Java 서블릿 코드로 변환/서버에서 동적으로 HTML을 생성
    	// spring Boot 기본 설정에서는 JSP가 기본적으로 지원되지 않음, Tomcat이 JSP를 처리
    	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
        // JSTL
    	implementation 'org.glassfish.web:jakarta.servlet.jsp.jstl'
    	implementation 'jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api'
        // servlet API
    	implementation 'jakarta.servlet:jakarta.servlet-api'
    }
```
##### [스크립틀릿(Scriptlet)](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/JSP/Scriptlet)
    JSP 페이지 내에서 Java 코드를 직접 작성할 수 있음
    HTML이 클라이언트의 브라우저로 전송되기 전에 서버에서 실행
    <% Java 코드 %>

##### 태그 라이브러리

    스크립틀릿 사용하면 java코드와 HTML가 혼합되 복잡하고 코드 유지/관리 어려움
    
- [EL(Expression Language)](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/JSP/EL)

      JSP 페이지에서 데이터를 쉽게 출력할 수 있게 해주는 표현 언어
      간결한 문법(${} 표기법), 자동 형변환 및 null 처리, 객체 접근 용이

- [JSTL(JavaServer Pages Standard Tag Library)](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/JSP/JSTL)
  
      반복, 조건문, 국제화, SQL 처리 등 다양한 기능
      Java 코드를 없애고 태그 기반으로 작업을 수행할 수 있음


###### [jsp.jsp](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/JSP/jsp.jsp)
```html
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
      
      <body>
         <!-- JSTL -->
         <!-- Core -->
         <!-- 변수값 설정 -->
         <c:set var="name" value="lee" />
         <c:set var="age" value="20" />
         <h1>${name}, ${age}</h1>
        
         <!-- 조건문 -->
         <c:if test="${name == 'lee'}">
         <p>내이름은 lee</p>
        </c:if>
        <c:choose>
             <c:when test="${age >= 30}">A</c:when>
             <c:when test="${age >= 20}">B</c:when>
             <c:otherwise>C</c:otherwise>
        </c:choose>
        
        <!-- 반복문 -->
        <c:forEach var="user" items="${users}">
            <p>${user.name}: ${user.age}</p>
         </c:forEach>
        
        <!-- Formatting -->
         <!-- 숫자 -->
         <c:set var="num" value="1234567.89" /><br>
         <!-- 기본 숫자 -->
         <fmt:formatNumber value="${num}" /><br>
         <!-- 퍼센트 -->
         <fmt:formatNumber value="${num}" type="percent" /><br>
        <!-- 소수점 자릿수 지정 -->
         <fmt:formatNumber value="${num}" maxFractionDigits="2" /><br>
        
         <!-- 날짜 -->
        <c:set var="now" value="<%= new java.util.Date() %>" />
         <!-- 기본 날짜 -->
         <fmt:formatDate value="${now}" /><br>
         <!-- date -->
          <fmt:formatDate value="${now}" type="date" /><br>
         <!-- 시간 -->
         <fmt:formatDate value="${now}" type="time" /><br>
         <!-- 사용자 정의 형식 -->
         <fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" /><br>
      </body>
```
##### [Scope](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/JSP/Scope.java)
    데이터의 생명 주기
    page < request < session < application
    
`page`: JSP 페이지(JSP 페이지가 실행되는 동안에만 존재)    
`request`: HTTP 요청 기간 동안 존재(서블릿 -> JSP)     
`session`: 세션 시간 동안 존재      
`application`: 전체 어플리케이션 공유       

###### [redirect/forward](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/JSP/redirect%2Cforward)

`redirect`: 새로운 URL로 재요청(서버와 클라이언트간 요청 - 응답 - 요청 - 응답)      
`forward`: 서버 내부에서 요청을 다른 리소스로 전달(브라우저 URL 변경되지 않음)      

- Redirect에선 HTTP 요청이 새로 발생해 model과 request 데이터 사라짐

`JspController`
```java
    @Controller
    public class JspController {
        @Autowired
        private ServletContext context;
    
        @GetMapping("/jsp")
        public String jsp(HttpServletRequest request, HttpSession session, Model model){
            // Scope
            request.setAttribute("request", "리퀘스트");
            session.setAttribute("session", "세션");
            context.setAttribute("application", "어플리케이션");
            // HttpServletRequest 사용됨
            model.addAttribute("model", "모델");
            return "jsp";
        }
    
        @GetMapping("/page")
        public String page(){
            return "jsp";
        }
    
        @GetMapping("/redirect")
        public String redirect(HttpServletRequest request, HttpSession session, Model model) {
            request.setAttribute("request", "리퀘스트-리다이렉트");
            session.setAttribute("session", "세션-리다이렉트");
            context.setAttribute("application", "어플리케이션-리다이렉트");
            model.addAttribute("model", "모델-리다이렉트");
            return "redirect:/jsp";
        }
    
        @GetMapping("/forward")
        public String forward(HttpServletRequest request, HttpSession session, Model model) {
            request.setAttribute("request", "리퀘스트-포워드");
            session.setAttribute("session", "세션-포워드");
            context.setAttribute("application", "어플리케이션-포워드");
            model.addAttribute("model", "모델-포워드");
            return "forward:/jsp";
        }
    }
```
`jsp.jsp`
```html
    <body>
        <!-- 스코프 -->
        <!-- Page -->
        <% pageContext.setAttribute("pageScope", "페이지"); %>
        <div>페이지:</div>
        <p>${pageContext.getAttribute("pageScope")}</p>
    
        <!-- Request -->
        <div>리퀘스트:</div>
        <p>${requestScope.request}</p>
    
        <!-- Session -->
        <div>세션:</div>
        <p>${sessionScope.session}</p>
    
        <!-- Application -->
        <div>어플리케이션:</div>
        <p>${applicationScope.application}</p>
    
        <!-- Model -->
        <div>모델:</div>
        <p>${model}</p>
    </body>
```
> 한글 깨짐       
> Content-Type 추가: <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>      
> File > Settings > Editor > File Encodings > Global Encoding, Project Encoding, Properties Files > UTF-8로 변경       

#### [Thymeleaf](https://github.com/yi5oyu/Study/tree/main/SpringBoot/View%20Template/Thymeleaf)
    https://www.thymeleaf.org/index.html
    
    Spring Boot와 호환성이 좋고 HTML, JS, CSS 등... 처리할 수 있음
    th:* 속성 사용한 동적 콘텐츠 처리, 변수 표현식: ${...}

`build.gradle`
```gradle
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
```
`application.yml`

    # 캐시 false

`src/main/resources/templates`
```html
    <!DOCTYPE html>
    <!-- 네임스페이스 선언 -->
    <html xmlns:th="http://www.thymeleaf.org">
    <head>
      <meta charset="UTF-8">
      <title>타이틀</title>
    </head>
    <body>
```
##### [주석](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/Thymeleaf/%EC%A3%BC%EC%84%9D)

      <!-- 일반 주석 처리 -->
      <!--/*-->
        <div>
          템플릿이 정적으로 열려있을 때 표시하기 위해 사용
        </div>
      <!--*/-->

##### [변수/속성 값 설정](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/Thymeleaf/%EC%86%8D%EC%84%B1)

      <!-- thymeleaf 처리될 경우 th:text의 텍스트 보여짐. 아닐 경우 태그안 텍스트 보여짐 -->
      <!-- <h1>text</h1>가 값으로 출력됨 -->

###### [HTML escape](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EA%B8%B0%EB%B3%B8%20%EA%B0%9C%EB%85%90%20%EC%A0%95%EB%A6%AC/HTML%20escape)

      <h1 th:text="'text: ' + ${text}">일반 텍스트 반환</h1>
      <!-- <h1>text</h1> DOM 랜더링됨 -->
      <h1 th:utext="'utext: ' + ${text}">텍스트 HTML로 해석</h1>
      <!-- null일때 기본값 설정 -->
      <h1 th:text="${message ?: 'null일 경우 기본'}"></h1>
      <input th:value="${name}" value="이름" type="text" />
      <img th:src="@{/images/logo.png}" alt="로고" />
      <!-- 여러 속성 설정 -->
      <input th:attr="disabled=${judge}, placeholder=${name}" type="text" />
      <!-- /users/user?id=${productId}&name=${name} -->
      <a th:href="@{/users/user(id=${userId}, name=${name})}">유저 id, name</a>
      <!-- <div style="color:blue"> -->
      <div th:style="'color:' + ${color}">인라인 Style 적용</div>
      <!-- 클래스 지정 -->
      <div th:class="${judge ? 'font-css' : 'none'}">클래스 이름</div>
      <!-- 클래스 추가 -->
      <input th:classappend="${judge} ? 'font-css' : 'none'" type="text" />
      <!-- 클래스이름 앞/뒤에 추가 -->
      <input th:attrprepend="class='start-'" type="text" />
      <input th:attrappend="class='-end'" type="text" />

##### [조건문](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/Thymeleaf/%EC%A1%B0%EA%B1%B4%EB%AC%B8)

      <div th:if="${judge}">ture일때 랜더링</div>
      <div th:unless="${judge}">false일때 랜더링</div>
      <div th:switch="${name}">
        <p th:case="'lee'">lee</p>
        <p th:case="'a'">a</p>
        <p th:case="*">나머지</p>
      </div>

##### [반복문](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/Thymeleaf/Iteration)

      <div th:each="user, status : ${users}">
        인덱스: <span th:text="${status.index}"></span><br>
        카운트: <span th:text="${status.count}"></span><br>
        사이즈: <span th:text="${status.size}"></span><br>
        첫번째: <span th:text="${status.first}"></span><br>
        마지막: <span th:text="${status.last}"></span>
      </div>

##### [linline(none, text, js, css)](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/Thymeleaf/Javascript%2Ccss)

      <p th:inline="text">

###### [유틸리티 함수(OGNL)](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/Thymeleaf/OGNL)

        [[${name}]], [[${#dates.format(today, 'yyyy-MM-dd')}]]
      </p>
      <script th:inline="javascript">
        let name = [[${name}]]
        console.log(name)
      </script>
      <style th:inline="css">
        .font-css {
            color: [[${color}]];
            font-size: 20px;
        }
      </style>

##### [Fragment(layout)](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/Thymeleaf/layout)

      <div th:insert="fragments/header :: header"></div>
      <div th:replace="fragments/footer :: footer"></div>
      <div th:insert="fragments/main :: main(judge, name)"></div>
      
    </body>
    </html>

`src/main/resources/templates/fragments`

`header.html`

    <!-- insert: 해당 DOM이 삽입됨 -->
    <div th:fragment="header">
      <header>
        <div>header</div>
      </header>
    </div>
    <!--
    <div>
     <div>
      <header>
        <div>header</div>
      </header>
     </div>
    </div>
    -->

`footer.html`

    <!-- replace: 해당 DOM으로 대체됨 -->
    <div th:fragment="footer">
      <footer>
        <div>footer</div>
      </footer>
    </div>
    <!--
     <div>
      <header>
        <div>header</div>
      </header>
     </div>
    -->
    
`main.html`

    <!-- 프래그먼트에 매개변수 전달 -->
    <div th:fragment="main (judge, name)">
      <div th:if="${judge}" th:text="${name}"></div>
    </div>

#### [Mustache](https://github.com/yi5oyu/Study/blob/main/SpringBoot/View%20Template/Mustache) 
    가볍고 논리가 없는 템플릿 언어
    JSON 데이터를 HTML이나 다른 형식으로 쉽게 변환할 수 있음
    Asciidoctor(adoc) 문서에서 사용됨    

`build.gradle`
```gradle
compileOnly 'org.springframework.boot:spring-boot-starter-mustache'
```
`src/main/resources/templates/must.mustache'`

###### [HTML escape](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EA%B8%B0%EB%B3%B8%20%EA%B0%9C%EB%85%90%20%EC%A0%95%EB%A6%AC/HTML%20escape)
    {{데이터}}
    {{{데이터}}}
    
    {{! 반복문 참(#)/거짓(^) }}
    {{#users}}
     {{name}}
    {{/users}}
    {{^users}}
     {{name}}
    {{/users}}

    {{! 레이아웃 적용 }}
    {{>폴더명/파일명}}

<hr>

### Testing/문서화

#### [Spring REST Docs](https://github.com/yi5oyu/Study/tree/main/SpringBoot/REST%20API/Testing/Spring%20REST%20Docs)
    실제 API 테스트를 기반으로 정확한 API 문서 자동 생성

- [의존성 설정(build.gradle)](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/Testing/Spring%20REST%20Docs/build.gradle)
```gradle
      plugins {
          // asciidoctor 플러그인
          id 'org.asciidoctor.jvm.convert' version '3.3.2'
      }
      ext {
          // 스니펫 경로 설정(프로젝트 전역에서 사용할 변수 정의)
          set('snippetsDir', file("build/generated-snippets"))
      }
      asciidoctor {
          attributes(
              "snippets": snippetsDir
    	  )
          // 이전 문서 삭제
          doFirst {
              delete file('build/docs/asciidoc')
          }
      }
      dependencies {
          // mockmvc(API 테스트 도구) 추가
          testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'
      }
      // ./gradlew test 명령어
      tasks.named('test') {
    	  outputs.dir snippetsDir
    	  useJUnitPlatform()
      }
      // ./gradlew asciidoctor 명령어
      tasks.named('asciidoctor') {
    	  inputs.dir snippetsDir
    	  dependsOn test
      }
```
- [MockMvc](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/Testing/Spring%20REST%20Docs/mockMvc)

      Spring Framework 테스트 도구
      서버 실행없이 HTTP 요청 시뮬레이션, 단위 테스트(Controller 테스트)

**[UserControllerTest](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/Testing/Spring%20REST%20Docs/UserControllerTest.java)**
```java
    // UserController 테스트
    @WebMvcTest(UserController.class)
    // spring security 보안 필터 적용하지 않음
    @AutoConfigureMockMvc(addFilters = false)
    // REST Docs 설정 자동 구성, 테스트 결과 문서화 수행
    @AutoConfigureRestDocs
    public class UserControllerTest {
        @Autowired
        private MockMvc mockMvc;
        @MockBean
        private UserService userService;
        @Test
        public void testGetAllUsers() throws Exception {
            // 가상(mock)의 userService에서 결과를 가져옴(DB없이 테스트 가능)
            when(userService.getAllUsers()).thenReturn(Arrays.asList(
                new User(1L, "lee", "lee@google.com"),
                new User(2L, "aaaa", "bbbb@naver.com")
            ));
            // 엔드포인트 users GET 요청
            mockMvc.perform(get("/users"))
                // 상태코드 200 검증
                .andExpect(status().isOk())
                // get-all-users snippet 생성
                .andDo(document("get-all-users",
                    responseFields(
                        // JSON 배열([]) 필드 타입, 설명 문서화
                        // 배열 안에 있는 각 객체의 필드
                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("ID"),
                        fieldWithPath("[].name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("[].email").type(JsonFieldType.STRING).description("이메일")
                    )
                ));
       }    
       ...
    }
```    
- [Snippets](https://github.com/yi5oyu/Study/edit/main/SpringBoot/REST%20API/Testing/Spring%20REST%20Docs/snippets)

      Spring REST Docs에서 API 문서화를 위해 생성되는 재사용 가능한 작은 정보 조각
      document("")에 지정된 이름으로 폴더 생성
      경로: root(프로젝트명)/build/generated-snippets/

<details>
<summary>생성된 스니펫</summary>

    build/generated-snippets
    ./gradlew test

<img width="480" alt="{2AA35FC1-94A2-4376-9F4F-7F13C92391A1}" src="https://github.com/user-attachments/assets/da54517a-c9de-4753-a0f4-14025ac1b9fe" />

</details>

- [Asciidoc](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/Testing/Spring%20REST%20Docs/AsciiDoc)

      Asciidoctor: AsciiDoc 문서를 HTML, PDF등의 형식으로 변환하는 도구
      Asciidoc 파일(.adoc)에 Snippets 포함시켜 사용
      https://asciidoc.org/#try

      intellj Asciidoc 플러그인 설치
      html로 랜더링되기 때문에 css, js, config 파일로 커스터마이징이 가능함

`index.adoc`

    진입점, 문서 구조 정의
    root/src/docs/asciidoc/index.adoc

    = UserController API 문서
    홍길동, <hong@google.com>
    v1.0, 2024-10-10
    :author: 홍길동
    :revdate: 2024-10-10
    :revnumber: 1.0
    :email: hong@google.com
    :doctype: book
    :icons: font
    :source-highlighter: coderay
    :toc: left
    :toc-title: 목차
    :toclevels: 3
    :sectlinks:
    :sectnums:

    == 개요
    이 문서는 UserController 클래스에서 생성된 API 문서 제공
    API 엔드포인트, 요청/응답 사용 예제 설명
    
    == API 엔드포인트
    
    === 모든 사용자 조회
    include::{snippets}/get-all-users/curl-request.adoc[]
    include::{snippets}/get-all-users/http-request.adoc[]
    include::{snippets}/get-all-users/http-response.adoc[]
    include::{snippets}/get-all-users/httpie-request.adoc[]
    include::{snippets}/get-all-users/request-body.adoc[]
    include::{snippets}/get-all-users/response-body.adoc[]
    include::{snippets}/get-all-users/response-fields.adoc[]
    ...

<details>
<summary>Asciidoctor로 생성된 index.html 결과</summary>

    build/docs/asciidoc/index.html
    ./gradlew asciidoctor

<img width="960" alt="{AB307493-2994-4FB8-B39B-0B0C7826AC59}" src="https://github.com/user-attachments/assets/8618202c-c103-4364-a731-d39a7a790d5a" />

</details>


#### [Swagger](https://github.com/yi5oyu/Study/tree/main/SpringBoot/REST%20API/Testing/Swagger)
    Open API 문서 자동화/테스트 도구

`의존성`
    
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0'

`Swagger UI`

    http://localhost:8080/swagger-ui.html

`entity or dto`

    @Data
    @Schema(description = "User Entity")
    public class User {
        @Schema(description = "유니크 ID", example = "1")
        private Long id;
    
        @Schema(description = "이름", example = "lee")
        private String name;
    
        @Schema(description = "이메일", example = "lee@google.com")
        private String email;
    }

`Controller`
```java
    @OpenAPIDefinition(
        info = @Info(
            title = "Spring Test API",
            version = "1.0",
            description = "Spring boot Test API 문서"
        )
    )
    @Tag(name = "User", description = "유저 관리")
    @RestController
    @RequestMapping("/users")
    public class UserController {
        @Operation(summary = "ID로 유저 찾기", description = "유니크 ID로 특정 유저 찾음")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "유저 발견",
                content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "유저 없음")
        })
        @GetMapping("/{id}")
        public User getUserById(
            @Parameter(description = "유저의 유니크 ID", required = true, example = "1")
            @PathVariable Long id
        ){
            return userService.getUserById(id);
        }
    }
```
<details>
<summary>자세히 살펴보기</summary>

- 적용 전   
  
<img width="960" alt="{04F20F65-CB97-4A9A-BD10-9811120C84D4}" src="https://github.com/user-attachments/assets/da1b2674-8d2a-472c-bcf4-1d4b45b339b9">

- 적용 후      
  
<img width="960" alt="{72259830-76E5-455D-A39D-EDE6E6F36092}" src="https://github.com/user-attachments/assets/131f7355-6840-4a20-9202-8cd13e4a7841">

</details>

[> Swagger 어노테이션](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/Testing/Swagger/API%20%EB%AC%B8%EC%84%9C%ED%99%94)    

#### [PostMan](https://github.com/yi5oyu/Study/tree/main/SpringBoot/REST%20API/Testing/Postman)
    API 테스트, 개발/관리 도구
    CI/CD 통합(자동화된 API 테스트 가능)
    
    https://www.postman.com

<details>
<summary>자세히 살펴보기</summary>

<img width="960" alt="{781C878B-FFDA-47A5-A476-90BFE798767A}" src="https://github.com/user-attachments/assets/9beb14d3-2b61-413e-b1cb-2531294f9dca">

</details>

#### [Spring Boot Actuator](https://github.com/yi5oyu/Study/tree/main/SpringBoot/Spring%20Actuator)

<hr>

## 🛡️ 보안

### Spring Security(6.3.3)
    implementation 'org.springframework.boot:spring-boot-starter-security'

    /config/SecurityConfig
    WebSecurityCustomizer: Web Security 예외 설정
    CorsConfigurationSource: CORS 관련 설정
    SecurityFilterChain: 보안 필터 설정
[**> Security Config**](https://github.com/yi5oyu/Study/blob/main/SpringBoot/Spring%20Security/SecurityConfig.java)


### OAuth 2.0
    implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'

#### Git Hub
`OAuth 앱 등록`: https://github.com/settings/developers   

<img width="480" alt="{1A0A9306-5233-40FF-ABB6-31C0508357A1}" src="https://github.com/user-attachments/assets/6269a91e-1e7a-4937-af93-5fb326dd55e9" />

`Authorization callback URL`: Spring Security를 사용하는 경우 '{baseUrl}/login/oauth2/code/{registrationId}' 이 형식을 따라야함    


`.env`: 환경변수 등록   
`.gitignore`: .env 파일 제외    
`DotenvConfig`: config 클래스   
[application.yml](https://github.com/yi5oyu/Study/blob/main/SpringBoot/OAuth/application.yml)    

`SecurityConfig`

    Spring Security 설정
    
`접근 권한`

    // requestMatchers에 정의된 경로로 들어오는 요청 모든 사용자에게 허용 (인증 없이 접근 가능)
    .requestMatchers("/", "/home").permitAll()
    // 인증된 사용자 접근 가능
    .requestMatchers("/h2-console/**").authenticated()	
    // 그 외 모든 요청은 인증된 사용자만 접근 가능   
    .anyRequest().authenticated()
    
`OAuth 2 로그인`

    .oauth2Login(oauth2 -> oauth2
        // 로그인 시작점
        .loginPage("/oauth2/authorization/github")
        // 성공 후 리다이렉트될 URL
        .defaultSuccessUrl("http://localhost:8080/thymeleaf", true)
    )

`http://localhost:8080/login/oauth2/code/github` `>` `https://github.com/login/oauth/authorize`  

`세션 로그아웃`

    .logout(logout -> logout
        // 로그아웃 URL 경로
        .logoutUrl("/logout")
        // 성공 후 리다이렉트될 URL
        .logoutSuccessUrl("http://localhost:8080")
        // 로그아웃 HTTP 세션 무효화(제거)
        .invalidateHttpSession(true)
        // Spring Security 인증 객체, 관련된 모든 데이터 제거
        .clearAuthentication(true)
    )

[SecurityConfig](https://github.com/yi5oyu/Study/edit/main/SpringBoot/Spring%20Security/SecurityConfig.java)    

`사용자 정보`

    @AuthenticationPrincipal
    id, 이름, 이메일, 엑세스토큰 등..

[OAuthController](https://github.com/yi5oyu/Study/blob/main/SpringBoot/OAuth/OAuthController.java)    

[//]: # (### JWT)

<hr>

## 🧠 AI

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

<hr>

## 📡 [OPEN API](https://github.com/yi5oyu/Study/tree/main/API/OPEN%20API)
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

<hr>

## 🏷️ [@애노테이션(Annotation)](https://github.com/yi5oyu/Study/tree/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98)

    Java 소스 코드에 추가하는 메타데이터(Metadata)
    컴파일러나 런타임(스프링 컨테이너 등) 환경에서 특정 행동을 수행하도록 정보를 제공하는 역할


`표준 애노테이션`


    Java에서 기본적으로 제공하는 애노테이션

`@Override`: 

`메타 애노테이션`

    다른 애노테이션을 정의할 때 사용되는 애노테이션

`@Target`: 애노테이션 적용될 수 있는 대상 지정      
`@Retention`: 애노테이션 유지 기간 설정       
`@Documented`: Javadoc 생성 시 애노테이션이 문서화되도록 지정      
`@Inherited`: 애노테이션 타입이 자동으로 상속되도록 지정     
`@Repeatable`: 동일한 선언에 애노테이션을 두 번 이상 적용할 수 있음    


### 스테레오타입 애노테이션(Stereotype Annotations)

    스프링 컨테이너가 컴포넌트 스캔(Component Scan)을 할 때 검색 대상이 되는 애노테이션
    모든 스테레오타입 애노테이션은 내부적으로 @Component를 가지고 있음

#### [@Component](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Component)

```
최상위 기본 애노테이션
특화된 역할(Controller, Service, Repository)에 해당하지 않는 일반적인 유틸리티 클래스 등을 빈으로 등록할 때 사용
```

| 애노테이션 | 계층(Layer) | 핵심 역할 및 특징 |
| :--- | :--- | :--- |
| **[@Controller](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Controller)** | Presentation | 웹 요청(URL)을 받아 처리 |
| **[@Service](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Service)** | Business | 핵심 비즈니스 로직(명시적 역할) |
| **[@Repository](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Repository)** | Persistence | DB 접근 **DB 관련 예외를 스프링의 데이터 접근 예외로 자동 변환** |
| **[@Configuration](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Configuration)** | Config | 설정 정보 클래스(@Bean 등록 시 싱글톤을 보장하는 CGLIB 프록시 생성) |


### 의존성 주입 애노테이션(Dependency Injection Annotations)

```
스프링 컨테이너에 등록된 빈을 필요한 클래스에 주입하는 역할
```

#### [@Autowired](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Autowired)

```
타입(Type)에 맞춰서 빈을 찾아 자동으로 주입
생성자가 하나만 있을 경우 생략 가능
```

#### 빈 충돌 해결 애노테이션

[@Primary](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Primary)

```
같은 타입의 빈이 여러 개 있을 때 우선순위를 지정
@Autowired와 함께 자동 사용됨(디폴트 값)
클래스/@Bean 메서드에 적용 가능
```

[@Qualifier](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Qualifier)

```
같은 타입의 빈이 여러 개 있을 때 명시적으로 특정 빈을 지정
@Autowired와 함께 사용, 빈 이름으로 주입할 빈을 명확히 지정(@Primary 보다 높은 우선순위)
필드, 메서드, 파라미터, 클래스, 애노테이션에 적용 가능
```

#### 값 주입 애노테이션

[@Value](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Value)

```
설정 파일의 값을 가져와 필드에 주입
```

### 웹 요청 처리(Web Request Processing Annotations)

```
스프링 MVC에서 클라이언트의 HTTP 요청(URL, 메서드)을 컨트롤러의 메서드와 매핑하고 전송된 데이터를 바인딩
```

[RestController](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40RestController)

```
@Controller + @ResponseBody
RESTful API 요청 처리, View(HTML) 반환이 아닌 데이터(JSON, XML 등)를 직접 반환할 때 사용
```

[@RequestMapping("/url")](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40RequestMapping)

```
특정 URL 패턴과 HTTP 메서드에 대한 요청을 메서드나 클래스에 매핑
클래스 레벨에 선언 시 공통 URL 경로를 설정
```

```java
// GET 요청
@RequestMapping(path = "/users", method = RequestMethod.GET)
public List getUsers() { }

// POST 요청
@RequestMapping(path = "/users", method = RequestMethod.POST)
public User createUser(@RequestBody UserDto dto) { }

// 여러 메서드 허용
@RequestMapping(path = "/users", method = {RequestMethod.GET, RequestMethod.HEAD})
public List getUsers() { }

// 메서드 지정 안 하면 모든 HTTP 메서드 허용
@RequestMapping("/users")  // GET, POST, PUT, DELETE 등 모두 가능
public List handleUsers() { }
```

**HTTP 메서드별 애노테이션**

| 애노테이션 | HTTP Method | 역할 및 용도 |
| :--- | :---: | :--- |
| **[@GetMapping](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40GetMapping)** | GET @RequestMapping(method = RequestMethod.GET) | **데이터 조회**(Read) |
| **[@PostMapping](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40PostMapping)** | POST @RequestMapping(method = RequestMethod.POST) | **데이터 등록/생성**(Create) |
| **[@PutMapping](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40PutMapping)** | PUT @RequestMapping(method = RequestMethod.PUT) | **데이터 전체 수정**(Update) |
| **[@PatchMapping](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40PatchMapping)** | PATCH @RequestMapping(method = RequestMethod.PATCH) | **데이터 일부 수정**(Partial Update) |
| **[@DeleteMapping](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40DeleteMapping)** | DELETE @RequestMapping(method = RequestMethod.DELETE) | **데이터 삭제**(Delete) |

#### 파라미터 매핑 애노테이션

[@RequestBody](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40RequestBody)

```
HTTP 요청 본문(Body)에 담긴 데이터(JSON, XML 등)를 자바 객체로 변환(HttpMessageConverter가 동작하여 JSON을 객체로 매핑)
주로 REST API에서 POST/PUT 요청의 데이터를 받을 때 사용
```

[@RequestParam](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40RequestParam)

```
URL 쿼리 파라미터(?key=value) 또는 HTML Form 데이터를 파라미터로 바인딩
/search?keyword=abc  ->  @RequestParam("abc") String word
```

[@PathVariable](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40PathVariable)

```
URL 경로(Path) 자체에 포함된 변수 값을 파라미터로 바인딩
/users/{id}  ->  @PathVariable("id") Long userId
```

[@RequestHeader](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40RequestHeader)

```
HTTP 요청 헤더(Header)의 값을 가져올 때 사용
```
<!--

### 기능 확장 & 데이터 애노테이션(Functional Extension & Data Annotations)

 ```
프록시(AOP) 패턴을 이용해 부가 기능을 적용하거나, 데이터베이스 테이블과 자바 객체를 매핑(ORM)하는 역할
```

#### 트랜잭션 관리(Transaction Management)

[@Transactional](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Transactional)

```
해당 메서드나 클래스의 모든 작업을 하나의 트랜잭션으로 묶음
작업 도중 예외(RuntimeException) 발생 시 자동 롤백(Rollback), 성공 시 커밋(Commit)
```

*   **주요 옵션:**
    *   `readOnly = true`: 읽기 전용 트랜잭션 (조회 성능 최적화 시 사용)
    *   `rollbackFor = Exception.class`: 체크 예외를 포함한 모든 예외에 대해 롤백 처리
    *   `propagation`: 트랜잭션 전파 수준 설정 (기본값: REQUIRED)

<br>

#### JPA 객체 매핑(JPA Object Mapping)

```
JPA(Hibernate) 표준 명세를 사용하여 DB 테이블과 자바 클래스를 1:1로 연결
```

**엔티티 정의 및 기본 키**

| 애노테이션 | 설명 |
| :--- | :--- |
| **[@Entity](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Entity)** | 이 클래스가 **DB 테이블과 매핑되는 엔티티**임을 명시 (필수) |
| **[@Table](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Table)** | 엔티티와 매핑할 **테이블 이름을 지정** (생략 시 클래스 이름 사용)<br>예: `@Table(name = "users")` |
| **[@Id](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Id)** | 해당 필드를 테이블의 **기본 키(Primary Key)**로 지정 |
| **[@GeneratedValue](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40GeneratedValue)** | 기본 키의 **생성 전략(Auto Increment 등)** 설정<br>옵션: `IDENTITY`(DB 위임/MySQL), `SEQUENCE`(오라클), `AUTO` 등 |

**필드 및 컬럼 매핑**

| 애노테이션 | 설명 |
| :--- | :--- |
| **[@Column](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Column)** | 필드와 매핑할 **테이블 컬럼의 세부 사항** 설정<br>옵션: `name`(컬럼명), `nullable`(null 허용여부), `length`(길이) 등 |
| **[@Enumerated](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Enumerated)** | 자바의 **Enum 타입**을 DB에 저장할 때 사용<br>**주의:** `@Enumerated(EnumType.STRING)` 사용 권장 (순서 변경 방지) |
| **[@Transient](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40Transient)** | DB 컬럼과 매핑하지 않고 **객체 내부에서만 사용할 필드**에 지정 |

**연관관계 매핑 (참고용)**

| 애노테이션 | 설명 |
| :--- | :--- |
| **[@ManyToOne](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40ManyToOne)** | **다대일(N:1)** 관계 매핑 (예: 게시글 -> 작성자) |
| **[@OneToMany](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40OneToMany)** | **일대다(1:N)** 관계 매핑 (예: 작성자 -> 게시글 리스트) |
| **[@JoinColumn](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40JoinColumn)** | 외래 키(Foreign Key)를 가질 컬럼 이름 지정 |

--?

