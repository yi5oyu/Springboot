<div align="center">

# **Servlet**

`클라이언트의 HTTP 요청을 받아 처리하고 응답을 생성하는 Java 클래스`    

</div>

텍스트 형식의 HTTP 통신 신호를 자바 객체로 변환하여 애플리케이션 로직과 연결

- SSR(Server Side Rendering): 서버에서 HTML 생성
- REST API: JSON 데이터 통신

### [HttpServlet](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/HttpServlet)

`서버에 단 하나만 존재, 모든 요청(스레드)이 공유`

- **HTTP 메서드 분류**: GET, POST, PUT, DELETE 요청을 자동으로 구분
- **통신 규약 처리**: HTTP 헤더, 쿠키, 세션 등을 표준화된 방식으로 제공

> 개발자는 비즈니스 로직에만 집중할 수 있음

### HttpServletRequest/Response

**HttpServletRequest** - 클라이언트 요청 정보를 담은 객체

```java
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // 파라미터: /user?id=123&name=A
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        
        // 헤더
        String userAgent = request.getHeader("User-Agent");
        
        // 요청 정보
        String method = request.getMethod(); // GET
        String uri = request.getRequestURI(); // /user
        
        // 세션
        HttpSession session = request.getSession();
    }
}
```

**HttpServletResponse** - 클라이언트에게 보낼 응답을 작성하는 객체

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // 응답 타입 설정
    response.setContentType("text/html;charset=UTF-8");
    
    // 상태 코드 설정
    response.setStatus(200); // HttpServletResponse.SC_OK
    
    // 헤더
    response.setHeader("Custom-Header", "value");
    
    // 응답
    PrintWriter out = response.getWriter();
    out.println("Hello!");
    
    // 리다이렉트
    // response.sendRedirect("/login");
}
```

## WAS(Web Application Server)

동적 처리가 필요한 요청만 WAS(서블릿 컨테이너)로 전달

`서블릿 컨테이너`

- Servlet 싱글톤 객체 생성/관리(JVM Heap 메모리)
- 생명주기 관리(init() -> service() -> destroy())
- HTTP 요청/응답 처리(HttpServletRequest/Response 변환)
- 멀티스레드 관리(스레드 풀)
- URL 매핑
- 보안 괸리(인증, 권한, HTTPS)

모든 서블릿 객체는 JVM의 Heap 메모리 영역에 생성되어 관리
서블릿 컨테이너(Tomcat)가 서블릿의 생명주기를 관리

## 서블릿 처리 흐름

클라이언트(브라우저)[HTTP 요청] -> WS(Nginx)[동적 요청만 WAS 전달] -> WAS(서블릿 컨테이너)[스레드 풀에서 스레드 할당, HttpServletRequest/Response 객체 생성, URL 매핑된 서블릿 찾음] -> 서블릿[service() 메서드 실행(비즈니스 로직 처리)] -> 응답[HTML/JSON]


> 스레드풀  
> 미리 일정 수의 스레드를 만들어 관리

> 스레드  
> CPU의 명령어 포인터(PC 레지스터)가 코드를 한 줄씩 가리키며 이동하는 실행의 흐름
