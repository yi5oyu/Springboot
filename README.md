# 🌟 개요
    Spring Boot를 이용한 어플리케이션 개발에 필요한 개념 정리, 테스팅, 연습을 목적으로한 레포지토리
    
#### 개발 환경
<img src="https://img.shields.io/badge/Spring Boot 3-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/> <img src="https://img.shields.io/badge/java 17-%23ED8B00.svg?style=flat-square&logo=openjdk&logoColor=white"/> 
<img src="https://img.shields.io/badge/Gradle 8.8-02303A?style=flat-square&logo=gradle&logoColor=white"/>

<img src="https://img.shields.io/badge/IntelliJ_IDEA-000000?style=flat-square&logo=IntelliJ IDEA&logoColor=white"/> <img src="https://img.shields.io/badge/VS%20Code-0078d7.svg?style=flat-square&logo=visual-studio-code&logoColor=white"/>

<img src="https://img.shields.io/badge/React_Native-20232A?style=flat-square&logo=react&logoColor=61DAFB"/>

<img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=flat-square&logo=spring&logoColor=white"/>

<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/> <img src="https://img.shields.io/badge/redis-%23DD0031.svg?style=flat-square&logo=redis&logoColor=white"/>

    보안 Spring Security, JWT, OAuth 2.0
    NoSQL Redis

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

## 📋 목차

- [Spring & Spring boot](#-spring--spring-boot)
- [Framework & Library](#-framework--library)
- [보안](#%EF%B8%8F-보안)
- [AI](#-ai)
- [OPEN API](#-open-api)
- [etc...](#%EF%B8%8F-etc)
   
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
    `@Autowired`: 생성자 주입  

`생성자 주입`
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
`필드 주입`

    @Autowired
    private UserService userService;

`Setter 주입`

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

6. **설정 관리**    
    설정을 외부화하여 다양한 환경에서 동일한 애플리케이션 코드를 사용할 수 있게 함    
    application.properties or application.yml    

### 💡 Spring 
    Java 애플리케이션 개발을 위한 포괄적인 인프라 제공
    외부 애플리케이션 서버에서 실행(Apache Tomcat, Jetty 등...)
    war 파일 생성
    
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

6. **모니터링**   
   Spring Boot Actuator : 서비스를 운영하는 시기에 해당 시스템이 사용하는 스레드, 메모리, 세션 등 주요 요소들 모니터링

[> Spring VS Spring boot](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EA%B8%B0%EB%B3%B8%20%EA%B0%9C%EB%85%90%20%EC%A0%95%EB%A6%AC/SpringBoot%20%ED%8A%B9%EC%A7%95)      
[> WAR VS JAR](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EB%B0%B0%ED%8F%AC%20%EB%B0%A9%EB%B2%95(WAR%2CJAR))

## 📦 Framework & Library
    Framework: 애플리케이션 개발의 기본 구조를 제공하는 소프트웨어 플랫폼
    Library: 특정 기능을 수행하는 코드 묶음

### Spring Web(Spring MVC)
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

#### REST API(Representational State Transfer API)
    HTTP 프로토콜을 기반으로 클라이언트와 서버 간에 자원을 주고받기 위해 설계된 아키텍처

`REST`: 주고 받는 자원에 이름을 정하고 주소(URI)에 명시해 HTTP 메서드를 통해 해당 자원의 상태를 주고받는 것    
`API`: 애플리케이션에서 제공하는 인터페이스. API를 통해 서버 or 프로그램 사이를 연결할 수 있음     
`RESTful`: REST 아키텍처 원칙을 따르는 웹 서비스 인터페이스  

[> REST API](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/%EC%A0%95%EC%9D%98)

`REST API CURD구현`

    @RestController
    @RequestMapping("/users")
    public class UserController {
        @Autowired
        private final UserService userService;
        // Read
        @GetMapping 
        public ResponseEntity<List<User>> getAllUsers() {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        }
        // Create
        @PostMapping 
        public ResponseEntity<User> createUser(@RequestBody User user) {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }
        // Update
        @PutMapping("/{id}") 
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        }
        // Delete
        @DeleteMapping("/{id}") 
        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
    }

[> ResponseEntity](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/ResponseEntity)      
[> HttpStatus 상태코드](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/HttpStatus)       

[> Spring MVC](https://github.com/yi5oyu/Study/blob/main/SpringBoot/Spring%20MVC)     

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


#### TEMPLATE ENGINES
Thymeleaf Mustache 

MySQL
Oracle
H2 Database
-->

#### H2 Database
    개발 및 테스트 환경에서 사용되는 경량 데이터베이스
    표준 SQL, 웹 콘솔, Spring Boot 통합

`인메모리 모드`: 데이터를 메모리에 저장, 빠른 속도 제공    
`임베디드 모드`: 애플리케이션에 내장되어 사용     
`서버 모드`: 데이터베이스 서버로 실행    

[> 모드](https://github.com/yi5oyu/Study/blob/main/SpringBoot/DB/H2/%EB%AA%A8%EB%93%9C)       

`application.yml`

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

`http://localhost:8080/h2-console`    
<details>
<summary>연결</summary>

`접속`    

<img width="480" alt="{CAEB2A3F-B771-454E-B03E-8BF5B362DA3E}" src="https://github.com/user-attachments/assets/4f912cbb-f609-45e3-ab38-7bd3853041b5"><br>

`테이블 생성`    

<img width="480" alt="{CDDE4D73-8498-472B-B72B-0B4CEADFFEC7}" src="https://github.com/user-attachments/assets/5102a416-7bdc-4085-b4e3-847699e1bcdb">
</details>


#### Mybatis
    SQL 쿼리를 Java 코드에서 분리하여 XML 파일 or 어노테이션으로 관리

`application.yml`

    mybatis:
      # 패키지 경로 지정
      # 지정된 패키지, 하위 패키지에 있는 클래스들을 자동으로 alias(별칭)으로 등록 
      type-aliases-package: com.example.springboottest.entity
      # xml 있는 위치 지정
      mapper-locations: classpath:mapper/*.xml

`Entity`

    @Data
    public class User {
        private Long id;
        private String name;
        private String email;
    }

`Mapper(Interface)/xml`

    @Mapper
    public interface UserMapper {
        List<User> findAll();
        User findById(Long id);
        void insertUser(User user);
        void updateUser(User user);
        void deleteUser(Long id);
    }

`Mapper(Interface)/어노테이션`

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

[> Interface](https://github.com/yi5oyu/Study/blob/main/MyBatis/Interface)

`Service`

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

[> Service](https://github.com/yi5oyu/Study/blob/main/MyBatis/Service)    

`Controller`

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

[> Controller](https://github.com/yi5oyu/Study/blob/main/MyBatis/Controller)      

`Mapper.xml`

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

[> xml](https://github.com/yi5oyu/Study/blob/main/MyBatis/Mapper.xml)     


[> MyBatis](https://github.com/yi5oyu/Study/tree/main/MyBatis)    



#### Spring Data JPA   
    JPA를 더 쉽고 편리하게 사용할 수 있도록 도와주는 Spring 프레임워크의 모듈
    
    JPA(Java Persistence API): 자바 애플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스
    ORM(object-relational mapping): 객체와 관계형 데이터베이스 테이블 매핑(객체 지향적으로 프로그래밍을 하면서 관계형 데이터베이스를 사용할 수 있음)

- 인터페이스 계층 구조   
    Repository > CrudRepository > PagingAndSortingRepository > JpaRepository    
    `Repository`: 최상위 기본 인터페이스(메소드 없음)     
    `CrudRepository`: CRUD(생성, 읽기, 업데이트, 삭제) 메서드    
    `PagingAndSortingRepository`:  페이징, 정렬 메서드    
    `JpaRepository`: 일괄 처리, 메서드 이름을 기반으로 한 쿼리 생성 기능 제공    
    [> JPA 인터페이스 계층 구조](https://github.com/yi5oyu/Study/blob/main/JPA/3.%20SpringDataJPA/JpaRepository.java)

- 사용(MySQL)

`application.yml`
    
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

`Entity`

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

`Repository`

    public interface UserRepository extends JpaRepository<User, Long> {
        // 이름으로 검색
        List<User> findByName(String name);
        // 값이 없는 경우(결과 없음) 
        // Null값으로 인한 NullPointerException 예외처리
        Optional<User> findByName(String name);
        // 페이징
        Page<User> findByName(String name, Pageable pageable);
    }

`Service`

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

`Controller`

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
    
[> Optional](https://github.com/yi5oyu/Study/blob/main/JPA/Optional)     
[> 객체지향쿼리](https://github.com/yi5oyu/Study/blob/main/JPA/4.%20JPQL/%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%EC%BF%BC%EB%A6%AC)     

### NoSQL
    NoSQL(Not only SQL): SQL만을 사용하지 않는 데이터베이스 관리 시스템

    - 확장성
      여러 서버로 쉽게 분산시켜 확장

    - 유연성
      다양한 형식으로 데이터를 저장할 수 있음

    - 성능
      키-값 형태의 데이터 액세스 패턴에 최적화
      데이터를 여러 서버에 분산, 데이터를 요청하는 사용자/응용 프로그램에 더 가깝운 곳에 저장

#### Redis
    Redis(Remote Dictionary Server)
    키-값 저장소 (다양한 데이터 구조 지원)
    간단한 데이터 구조에 빠르게 액세스해야 하는 애플리케이션에 매우 빠르고 적합(캐시, 메시지 브로커, 세션 저장소)
    
[**> Redis**](https://github.com/yi5oyu/Study/tree/main/DB/NoSQL/Redis)     
[> 설치](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/%EC%84%A4%EC%B9%98)     

`의존성`

    implementation 'org.springframework.boot:spring-boot-starter-data-redis'

`application.yml`

    spring:
      redis:
        host: localhost
        port: 6379 # 기본 포트
        password: # 생략 가능
        
[> Redis application.yml](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/application.yml)     

`RedisConfig`

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
    
[> Redis Config](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/RedisConfig.java)    

`RedisService`

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

[> Redis Service](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/RedisService.java)     

`RedisController`

    @RestController
    public class RedisController {
        @Autowired
        private RedisService redisService;
        // 저장 /save?key=이름&value=데이터
        @PostMapping("/save")
        public void save(String key, String value) {
            redisService.saveData(key, value);
        }
        // 조회 /get?key=이름
        @GetMapping("/get")
        public String get(String key) {
            return redisService.getData(key);
        }
        // 삭제 /delete?key=이름
        @DeleteMapping("/delete")
        public void delete(String key) {
            redisService.deleteData(key);
        }
    }

[> Redis Controller](https://github.com/yi5oyu/Study/blob/main/DB/NoSQL/Redis/RedisController.java)     

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


#### Lombok
    반복적으로 작성해야 하는 코드를 줄여주는 라이브러리
    코드의 가독성을 높이고 개발 생산성을 향상시킴

`POJO`

    POJO(Plain Old Java Object)
    의존성 최소화한 단순한 자바 객체

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

`Lombok`

    반복적인 작업을 어노테이션 사용해 자동으로 처리

    // Lombok 적용
    @Data // getters, setters, toString, equals, hashCode
    @NoArgsConstructor // 기본 생성자
    @AllArgsConstructor // 매개변수있는 생성자
    public class User {
        private Long id;
        private String name;
        private String email;
    }

[> Lombok 어노테이션](https://github.com/yi5oyu/Study/tree/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/Lombok)   

### Templates

#### JSP
    
#### Thymeleaf

### Testing/Logging

#### Swagger

#### Spring REST Docs

#### PostMan

#### Spring Boot Actuator

## 🛡️ 보안

### Spring Security (6.3.3)
    implementation 'org.springframework.boot:spring-boot-starter-security'

    /config/SecurityConfig
    WebSecurityCustomizer: Web Security 예외 설정
    CorsConfigurationSource: CORS 관련 설정
    SecurityFilterChain: 보안 필터 설정
[**> Security Config**](https://github.com/yi5oyu/Study/blob/main/SpringBoot/Spring%20Security/SecurityConfig.java)

[//]: # (### JWT)

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

## 📡 OPEN API
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


## 🗂️ etc

### 🏷️ @어노테이션(Annotation)
    Java에서 코드에 메타데이터를 추가하는 방법
    컴파일러나 런타임 환경에서 특정 행동을 수행하도록 정보를 제공하는 역할


#### 스테레오 타입 어노테이션
    사용자 정의 어노테이션
    주로 애플리케이션의 계층 구조, 코드의 가독성을 높이는 데 사용

``

`@RestController`

    
<details>
<summary>Controller + ResponseBody</summary>

`@Controller`
`@ResponseBody`

</details>

[RestController](https://github.com/yi5oyu/Study/blob/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98/%40RestController)


<hr>

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

[> 어노테이션](https://github.com/yi5oyu/Study/tree/main/SpringBoot/%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98)

#### 표준 어노테이션
    Java에서 기본적으로 제공하는 어노테이션

`@Override`: 

#### 메타 어노테이션
    다른 어노테이션을 정의할 때 사용되는 어노테이션

`@Target`: 어노테이션 적용될 수 있는 대상 지정      
`@Retention`: 어노테이션 유지 기간 설정       
`@Documented`: Javadoc 생성 시 어노테이션이 문서화되도록 지정      
`@Inherited`: 어노테이션 타입이 자동으로 상속되도록 지정     
`@Repeatable`: 동일한 선언에 어노테이션을 두 번 이상 적용할 수 있음    


