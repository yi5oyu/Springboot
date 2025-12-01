<div align="center">

# **Java**



<!-- | [Spring & Spring boot](#-spring--spring-boot) • [Framework & Library](#-framework--library) • [보안](#%EF%B8%8F-보안) • [AI](#-ai) • [OPEN API](#-open-api) • [애노테이션](#%EF%B8%8F-애노테이션annotation)  | -->

</div>


## 객체 지향 프로그래밍(Object-Oriented Programming)

`데이터를 객체(object) 단위로 묶어 코드를 구성하는 프로그래밍 방법론`

### OOP의 4가지 특성

#### 1. 추상화(Abstraction)

`복잡한 내부 구현을 숨기고 필요한 기능만 외부에 제공`

#### 2. 캡슐화(Encapsulation)

`데이터와 메서드를 하나의 클래스로 묶어 관리`

#### 3. 상속(Inheritance)

`기존 클래스의 속성과 메서드를 새로운 클래스가 물려받음`

#### 4. 다형성(Polymorphism)

`같은 타입이지만 실행 결과가 다양한 객체를 이용할 수 있음`


### OOP의 5대 원칙(SOLID)

`객체지향 프로그래밍을 할 때 유지보수하기 쉽고 유연하고 확장이 쉬운 소프트웨어를 만들기 위한 5가지 규칙`

#### 1. 단일 책임 원칙(Single Responsibility Principle) 

`하나의 클래스는 하나의 책임(역할)만 가져야 함(클래스를 수정해야 할 이유는 단 하나)`

```
Controller: 요청 받는 일만 함
Service: 비즈니스 로직만 처리함
Repository: DB랑 대화하는 일만 함
```

#### 2. 개방-폐쇄 원칙(Open/Closed Principle)

`확장에는 열려 있어야 하고(Open) 변경에는 닫혀 있어야 함(Closed)`

#### 3. 리스코프 치환 원칙(Liskov Substitution Principle)

`자식 클래스는 언제나 부모 클래스를 대체할 수 있어야 함`

#### 4. 인터페이스 분리 원칙(Interface Segregation Principle)

`범용적인 인터페이스 하나보다 구체적인 인터페이스 여러 개가 나음`

#### 5. 의존관계 역전 원칙(Dependency Inversion Principle)

`구체적인 것(구현체)에 의존하지 말고 추상적인 것(인터페이스)에 의존`


```java
// 추상화
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
// 캡슐화(하나의 클래스로 관리)
// 기존 클래스를 상속받음
public class AService$$EnhancerBySpringCGLIB extends AService {

    // 은닉화(private으로 접근 차단)
    // 실제 객체
    private AService target;
    // 트랜잭션 매니저 호출
    private PlatformTransactionManager transactionManager;

    public AService$$EnhancerBySpringCGLIB(AService target, PlatformTransactionManager txManager) {
        this.target = target;
        this.transactionManager = txManager;
    }
    
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
