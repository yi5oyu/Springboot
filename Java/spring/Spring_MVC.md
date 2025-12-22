<div align="center">

# [**Spring MVC**](https://github.com/yi5oyu/Study/blob/main/SpringBoot/Spring%20MVC)

`Model-View-Controller 패턴의 Spring 웹 프레임워크`    

</div>

Servlet의 복잡함을 추상화하여 개발자가 비즈니스 로직에만 집중할 수 있게 해주는 프레임워크

`반복되는 코드`

```java
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 파라미터 추출
        String id = request.getParameter("id");
        
        // 타입 변환
        int userId = Integer.parseInt(id);
        
        // 비즈니스 로직
        User user = userService.findById(userId);
        
        // 응답 타입 설정
        response.setContentType("application/json;charset=UTF-8");
        
        // 수동 JSON 변환
        String json = "{\"id\":" + user.getId() + ",\"name\":\"" + user.getName() + "\"}";
        
        // 응답 작성
        response.getWriter().println(json);
    }
}
```

`URL 매핑`

```java
// 각 URL마다 Servlet 클래스 필요
@WebServlet("/user/list")   // UserListServlet
@WebServlet("/user/detail") // UserDetailServlet
@WebServlet("/user/create") // UserCreateServlet
@WebServlet("/user/update") // UserUpdateServlet
@WebServlet("/user/delete") // UserDeleteServlet
```

## [DispatcherServlet](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/DispatcherServlet)

`각 요청(URL)마다 서블릿을 하나씩 만들고 관리가 아닌 하나의 서블릿으로 통합`

`모든 클라이언트 요청(HTTP 메서드)에 대한 단일 진입점 역할(적절한 Controller로 전달)`

상속: DispatcherServlet(Spring MVC) -> FrameworkServlet(Spring) -> HttpServletBean(Spring) -> HttpServlet(Servlet API)

```java
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 파라미터 자동 추출, 타입 자동 변환, JSON 자동 변환
    @GetMapping
    public UserDto getUser(@RequestParam int id) {
        return userService.findById(id);
    }
}
```

## MVC 패턴

`Model-View-Controller 디자인 패턴`

비즈니스 로직과 화면을 구분하는 아키텍처 패턴

### 역할

`Model(모델)`

- 데이터와 비즈니스 로직 관리
- 데이터베이스와 상호작용

`View(뷰)`

- 사용자 인터페이스 담당

`Controller(컨트롤러`

- 사용자 요청 처리

## 3-Tier 아키텍처

### 표현 계층(Presentation Layer)

`사용자 인터페이스, 데이터 검증`

- Controller, DTO

```java
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        UserDto dto = userService.findById(id);
        return ResponseEntity.ok(dto);
    }
}
```

### 비즈니스 계층(Business Logic Layer)

`핵심 비즈니스 로직, 트랜잭션 관리`

- Service

```java
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException());
        
        return UserDto.from(user);
    }
}
```

### 데이터 접근 계층(Data Access Layer)

`데이터베이스 CRUD 연산`

- Repository, Entity

```java
@Repository
public interface UserRepository extends JpaRepository {
    Optional findByEmail(String email);
}

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
}
```

> 엔티티(Entity)
> 데이터베이스 테이블과 1:1 매핑

> DTO(Data Transfer Object)
> 계층 간 데이터 전송용
> 클라이언트에 노출되는 정보(필요한 필드만 선택적으로 포함)

## Spring MVC 요청 처리 흐름

<img width="1196" height="798" alt="image" src="https://github.com/user-attachments/assets/3556b983-575f-43cd-a4c1-43a4f6cce069" />

### 1. Request

`GET http://localhost:8080/user?id=123`

- 클라이언트(브라우저)가 HTTP 요청 보냄

### 2. DispatcherServlet -> HandlerMapping

`HandlerMapping은 해당 URL에 매핑된 Controller 정보를 찾아 반환`

- URL(/user)을 처리할 컨트롤러 찾음

### 3. DispatcherServlet -> HandlerAdapter

`HandlerAdapter는 컨트롤러의 메서드 형식에 맞춰 요청 파라미터 등을 변환`

- 찾은 컨트롤러를 직접 실행할 수 없기 때문에 HandlerAdapter에게 처리를 위임

### 4. HandlerAdapter -> Controller

`Controller는 Service를 호출하고, Service는 Repository를 통해 Database에 접근하여 데이터를 가져옴`

- Controller를 호출하여 비즈니스 로직을 수행

### 5. Controller -> HandlerAdapter

`view name: "userDetail", model: {name: "A"}`

- 로직 처리가 끝나면 Controller는 결과 데이터(Model)와 화면 정보(View Name)를 반환

> REST API 방식에선 HTML 필요없기 때문에 6,7 단계 없음
> 대신 HttpMessageConverter 호출해 자바 객체를 JSON 문자열로 변환하여 HTTP 응답 바디에 넣음

### 6. DispatcherServlet -> ViewResolver

`ViewResolver는 실제 화면 파일의 경로를 찾아 반환(/WEB-INF/views/userDetail.jsp)`

- 뷰 이름("userDetail")을 ViewResolver에게 보냄

### 7. ViewResolver -> View

`View는 모델 데이터를 참조하여 최종적인 HTML 페이지를 생성`

- View(JSP, Thymeleaf 등)에게 모델 데이터를 전달해 화면을 그리라고 명령

### 8. Response

- 완성된 HTML/JSON 데이터를 DispatcherServlet을 통해 클라이언트에게 최종 응답으로 전송
