<div align="center">

# **Java**

`Write Once, Run Anywhere`    

<!-- | [Spring & Spring boot](#-spring--spring-boot) • [Framework & Library](#-framework--library) • [보안](#%EF%B8%8F-보안) • [AI](#-ai) • [OPEN API](#-open-api) • [애노테이션](#%EF%B8%8F-애노테이션annotation)  | -->

</div>

## 구조

### JDK(Java Development Kit)

`자바 애플리케이션을 개발하고 실행하기 위해 필요한 모든 소프트웨어 패키지`    

```
JDK ⊃ JRE ⊃ JVM 의 포함 관계를 가짐

JDK = (JRE = JVM + 자바 표준 라이브러리) + 개발 도구

주요 개발 도구
 - 개발자가 소스 코드를 작성하고 관리하는 데 필요한 도구
 - javac(Java Compiler): 자바 소스 파일(.java)을 바이트코드(.class)로 컴파일하는 핵심 도구
 - java(Java Launcher): 컴파일된 .class 파일을 실행(JVM 구동)하는 도구
 - javadoc: 소스 코드의 주석을 읽어 API 문서(HTML)를 자동으로 생성해 주는 도구
 - jdb(Java Debugger): 프로그램의 오류를 찾고 수정하기 위한 디버깅 도구
 - jar(Java Archive): 여러 .class 파일과 리소스를 하나의 압축 파일(.jar)로 묶어주는 도구
```

> war(Web Application Archive)     
>  웹 애플리케이션 배포용 아카이브. jar와 유사하지만 WEB-INF/, web.xml 등... 웹 애플리케이션 구조를 포함    
>  Tomcat 등 서블릿 컨테이너에 배포 시 사용    

### JRE(Java Runtime Environment)

`컴파일된 자바 프로그램(.class, .jar)을 실행하는 데 필요한 소프트웨어 패키지`

```
자바 프로그램을 실행하기 위한 환경(자바 프로그램을 실행만 하는 일반 사용자에게 필요)

JRE = JVM + 자바 표준 라이브러리(Java API)

실행 환경 구성요소
 - JVM: 실제 프로그램을 실행하고 메모리를 관리하는 가상 머신
 - Java 클래스 라이브러리: 자바 프로그램이 실행될 때 반드시 필요한 기본 코드 모음(java.lang, java.util, java.io 등...)
 - 설정 파일, 프로퍼티 파일 등 실행을 돕는 파일들
```

### JVM(Java Virtual Machine)

`자바 프로그램이 운영체제(OS)에 관계없이 실행될 수 있도록 도와주는 가상의 컴퓨터`

`JVM 구조`

<img width="711" height="776" alt="image" src="https://github.com/user-attachments/assets/47909433-836e-4165-b459-4d394dc4c186" />

`JVM 동작 과정 및 코드 변환 흐름`    

```
.java 파일 작성 → javac 컴파일 → .class 파일 → JVM 실행 → 기계어

1. 소스 코드 작성: 자바 언어로 소스 코드를 작성
2. 컴파일: javac 컴파일러가 이 코드를 바이트코드로 변환해 .class 파일 생성
 - 구문 분석: 소스 코드를 읽어 문법적으로 올바른지 검사
 - 심볼 분석: 변수, 메서드, 클래스 등의 선언을 심볼 테이블에 등록
 - 타입 겸사: 변수 타입, 메서드 반환 타입 등 타입 일치 여부 검증
 - 바이트 코드 생성: 검증된 코드를 JVM이 이해하는 바이트코드로 변환
3. JVM 실행(프로그램이 실행되면 JVM 실행)
 > Class Loader Subsystem
  - 로딩(Loading): .class 파일들을 찾아서 JVM 메모리에 올림
  - 링킹(Linking): 검증(Verify), 클래스 변수(static 변수) 메모리 할당, 기본값 설정(Prepare), 논리적 주소를 실제 메모리 주소로 바꿈(Resolve)
  - 초기화(Initialization): static 코드를 실행하고 static 변수에 실제 값 할당
 > Runtime Data Area
  - 로딩된 데이터는 용도에 따라 메모리에 할당
  - 모든 스레드
    - Method Area: 로딩된 클래스 정보(멤버 변수 이름, 데이터 타입 등), static 변수, 상수, main() 바이트코드 저장
    - Heap: 문자열 객체, 프로그램 실행 중 new 생성자로 생성되는 객체들이 저장
  - 개별 스레드
    - Stack: 메서드가 호출될 때마다 프레임이 생성되어 지역 변수 등을 저장
    - PC Register: 현재 실행 중인 명령어의 주소를 가리킴
    - Native Method Stack: 자바 외의 언어(C/C++)로 작성된 네이티브 코드를 실행하기 위한 메모리 영역
 > Execution Engine
  - 메모리에 배치된 코드를 실행 엔진이 읽어서 실제 기계어(Native Code)로 변환 및 실행
  - Interpreter: 바이트코드를 한 줄씩 읽어서 OS가 이해하는 기계어로 변환해 즉시 실행
  - JIT Compiler(Just-In-Time): 인터프리터가 반복적으로 실행하는 코드를 발견하면 JIT가 해당 코드를 통째로 컴파일해 기계어로 바꾼 뒤 캐시에 저장
  - Garbage Collector(GC): Heap 영역을 감시하다가 더 이상 참조되지 않는 객체를 찾아 메모리에서 제거
 > JNI & Native Method Interface
  - 자바 코드만으로 해결할 수 없는 하드웨어 제어나 운영체제 기능이 필요할 때 사용
  - JNI(Java Native Interface): 실행 엔진이 C, C++ 등으로 작성된 외부 라이브러리를 호출할 수 있도록 연결
```

> 네이티브 코드(기계어): CPU가 직접 실행할 수 있는 명령어    
> JVM의 Interpreter/JIT가 바이트코드를 변환하여 생성

## 객체 지향 프로그래밍(Object-Oriented Programming)

`데이터를 객체(object) 단위로 묶어 코드를 구성하는 프로그래밍 방법론`

`OOP의 4가지 특성`    

캡슐화(Encapsulation): 데이터와 그 데이터를 처리하는 메서드를 하나로 묶고 외부에서 함부로 접근하지 못하도록 은닉(private)    
상속(Inheritance): 부모 클래스의 속성과 기능을 자식 클래스가 물려받아 재사용하고 확장      
다형성(Polymorphism): 하나의 객체나 메서드가 여러 가지 형태(역할)를 가질 수 있는 성질    
추상화(Abstraction): 복잡한 시스템으로부터 핵심적인 개념이나 기능만을 추출    

`OOP의 5대 원칙(SOLID)`

`객체지향 프로그래밍을 할 때 유지보수하기 쉽고 유연하고 확장이 쉬운 소프트웨어를 만들기 위한 5가지 규칙`

단일 책임 원칙(SRP): 하나의 클래스는 하나의 책임만 가져야 하며 클래스를 변경해야 하는 이유는 단 하나여야 함
개방-폐쇄 원칙(OCP): 확장에는 열려 있어야 하고(Open), 변경에는 닫혀 있어야 함(Closed)
리스코프 치환 원칙(LSP): 자식 클래스는 언제나 부모 클래스를 대체할 수 있어야 함
인터페이스 분리 원칙(ISP): 클라이언트는 자신이 사용하지 않는 메서드에 의존하면 안 됨
의존관계 역전 원칙(DIP): 구체적인 것(구현 클래스)에 의존하지 말고 추상적인 것(인터페이스)에 의존해야 함

```java
// 단일 책임 원칙(Single Responsibility Principle): 하나의 클래스는 하나의 책임(역할)만 가져야 함(클래스를 수정해야 할 이유는 단 하나)
Controller: 요청 받는 일만 함
Service: 비즈니스 로직만 처리함
Repository: DB랑 대화하는 일만 함
```

```java
// 추상화(Abstraction): 복잡한 내부 구현을 숨기고 필요한 기능만 외부에 제공
// Controller에서 Service 내부 로직 모름(바뀌어도 상관없음)

@Autowired
private AService aService; 

@Controller
public class AController(){
    public void a() {
        // 메서드 호출만으로 사용 가능
        aService.b();
    }
}
```

```java
@Service
public class AService { 
    @Transactional
    public void a() {
        // 로직
    }
}

// CGLIB 기반 프록시
/*
    캡슐화(Encapsulation): 데이터와 메서드를 하나의 클래스로 묶어 관리
    상속(Inheritance): 기존 클래스의 속성과 메서드를 새로운 클래스가 물려받음
    개방-폐쇄 원칙(Open/Closed Principle): 확장에는 열려 있어야 하고(Open) 변경에는 닫혀 있어야 함(Closed)
     - 원본 코드 변경 없이 프록시로 트랜잭션 기능 확장
*/
public class AService$$EnhancerBySpringCGLIB$$[랜덤값] extends AService {

    // 은닉화(private으로 접근 차단)
    // 실제 객체
    private AService target;
    // 트랜잭션 매니저 호출
    private PlatformTransactionManager transactionManager;

    public AService$$EnhancerBySpringCGLIB(AService target, PlatformTransactionManager txManager) {
        this.target = target;
        this.transactionManager = txManager;
    }

    // 다형성(Polymorphism): 같은 타입이지만 실행 결과가 다양한 객체를 이용할 수 있음
    // 부모 메서드를 재정의해 추가 동작 수행
    @Override
    public void a() {
        TransactionStatus status = null;

        try {
            DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
            status = transactionManager.getTransaction(definition);
            // 실제 메서드 호출
            target.a();
            // 커밋
            transactionManager.commit(status);
        } catch (Exception e) {
            // 롤백
            if (status != null) {
                transactionManager.rollback(status);
            }
            throw e;
        }
    }
}

```

<!-- 
// 빈 등록(JPA, MyBatis 같이 사용할때 등록)
@Configuration
public class DBConfig {
    // JPA 내부에 JDBC Connection 설정 구현되 있어서 JPA으로 통일(JPA/MyBatis 둘 다 사용 가능)
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
-->

`Spring Data JPA`

```java
/*
    인터페이스 분리 원칙(Interface Segregation Principle): 범용적인 인터페이스 하나보다 구체적인 인터페이스 여러 개가 나음`
     - 선택적 기능 확장, 역할 분리, 유지보수 좋음
      > 기본 CRUD만 필요한 Repository는 JpaRepository만 상속
      > 복잡한 쿼리가 필요한 경우에만 Custom 인터페이스 추가
*/

// UserRepositoryCustom: 프로젝트에 필요한 복잡한 쿼리 전용 인터페이스 분리
@Repository
public interface UserRepository extends JpaRepository<Users, Long>, UserRepositoryCustom {
    // 간단한 쿼리 메서드
    Optional<Users> findByGithubId(String githubId);
}

// QueryDSL 기반의 복잡한 쿼리만 정의
public interface UserRepositoryCustom {
    Optional<Long> findIdByGithubId(String githubId);
    Optional<String> findPrincipalNameByGithubId(String githubId);
}

// 구현체
@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    
    private final QUsers qUsers = QUsers.users;
    private final QNotes qNotes = QNotes.notes;
    private final QChats qChats = QChats.chats;
    
    @Override
    @Transactional
    public void deleteAllContentByUserId(Long userId) {
        queryFactory.delete(qChats)
            .where(qChats.conversations.notes.users.id.eq(userId))
            .execute();

        // ...
    }

    // ...
}

/*
    의존관계 역전 원칙(Dependency Inversion Principle): 구체적인 것(구현체)에 의존하지 말고 추상적인 것(인터페이스)에 의존
     - 구현체가 변경되어도 비즈니스 로직은 영향받지 않음
*/

@Service
@RequiredArgsConstructor
public class UserService {
    
    // 구현체(UserRepositoryImpl)가 아닌 인터페이스(UserRepository)에 의존
    private final UserRepository userRepository;
    
    @Transactional
    public void deleteUser(String githubId) {
        // UserRepository의 구현 방식(JPA, MyBatis 등)을 몰라도 됨
        Users user = userRepository.findByGithubId(githubId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Custom 메서드도 인터페이스를 통해 호출
        userRepository.deleteAllContentByUserId(user.getId());
    }
}
```

`@Transational`

```java
/*
    리스코프 치환 원칙(Liskov Substitution Principle): 자식 클래스는 언제나 부모 클래스를 대체할 수 있어야 함
    같은 PlatformTransactionManager 클래스를 상속받은 JPA(JpaTransactionManager), MyBatis(DataSourceTransactionManager)
*/
// Spring 트랜잭션 관리 인터페이스로 추상화 시킴
public interface PlatformTransactionManager {
    // 트랜잭션 시작
    TransactionStatus getTransaction(TransactionDefinition definition);
    // 커밋/롤백
    void commit(TransactionStatus status);
    void rollback(TransactionStatus status);
}

// JPA용 구현체
public class JpaTransactionManager implements PlatformTransactionManager {
    // JPA 방식으로 구현
    private EntityManagerFactory emf;  // JPA 설정 정보
    
    public JpaTransactionManager(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition) {
        // EntityManager 생성
        // JPA 트랜잭션 시작
        // 내부적으로 JDBC Connection도 가져옴(MyBatis와 공유 가능)
        // TransactionStatus 객체 생성해서 반환
    }
    
    @Override
    public void commit(TransactionStatus status) {
        // JPA 트랜잭션 커밋
        // JDBC Connection도 커밋(MyBatis 작업도 함께 커밋)
    }
    
    @Override
    public void rollback(TransactionStatus status) {
        // JPA 트랜잭션 롤백
        // JDBC Connection도 롤백(MyBatis 작업도 함께 롤백)
    }
}

// MyBatis용 구현체
public class DataSourceTransactionManager implements PlatformTransactionManager {
    // JDBC 방식으로 구현
    private DataSource dataSource;  // DB 연결 정보
    
    public DataSourceTransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition) {
        // DataSource에서 Connection 가져오기
        // 자동 커밋 끄기 (트랜잭션 시작)
        // TransactionStatus 객체 생성해서 반환
    }

    @Override
    public void commit(TransactionStatus status) {
        // Connection에서 커밋
    }
    
    @Override
    public void rollback(TransactionStatus status) {
        // Connection에서 롤백
    }
}

// Spring @Transactional 처리
public class TransactionInterceptor {
    
    // 부모 타입(PlatformTransactionManager)으로 선언(JPA <-> MyBatis 교체 가능)
    private PlatformTransactionManager txManager;
    
    // @Transactional이 붙은 메서드를 실행할 때 자동 호출
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TransactionStatus status = null;

        // 어떤 구현체든 부모에 정의된 메소드 호출 가능
        try {
            // 트랜잭션 시작
            status = txManager.getTransaction(new DefaultTransactionDefinition());
            
            // 실제 메서드 실행
            Object result = invocation.proceed();
            
            // 커밋
            txManager.commit(status);
            
            return result;
        } catch (Exception e) {
            // 롤백
            if (status != null) {
                txManager.rollback(status);
            }
            throw e;
        }
    }
}

@Service
@RequiredArgsConstructor
public class AService {
    private final ARepository aRepository;

    // JPA와 MyBatis가 서로 바뀌어도 동일하게 동작
    @Transactional
    public void a() {
        // 
    }
}

```

<!-- 
```java
/*
    Spring Boot 애플리케이션 실행 시 프록시 생성 과정

    1. Spring boot 애플리케이션을 실행하면 스프링 컨테이너가 빈에 등록된 클래스를 찾음
    2. 원본 객체를 힙 메모리에 만듬
    3. 스프링 컨테이너에 등록되기 전 빈 후처리(BeanPostProcessor) 처리
    4. AOP 어노테이션이 있으면 CGLIB 기술을 써서 프록시 객체를 만들어서 컨테이너에 저장
*/

System.out.println(aService.getClass().getName());
// AService$$EnhancerBySpringCGLIB$$[랜덤값] 출력

```
-->
