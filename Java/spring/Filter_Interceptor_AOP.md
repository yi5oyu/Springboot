<div align="center">

# **Filter vs Interceptor vs AOP**

`공통 관심사 처리`    

</div>

<img width="2330" height="844" alt="image" src="https://github.com/user-attachments/assets/ea68a78c-e8c9-4ff6-a411-b2b7b43886fe" />

## Filter

`WAS의 Servlet Container 영역(DispatcherServlet 전/후)`

- **모든 웹 요청/응답 필터링**
  - 보안 검증(XSS, CSRF 방어)
  - 인코딩 변환(UTF-8)
  - CORS 설정
  - 이미지/데이터 압축 및 로깅

> Spring Security가 Filter 계층에서 여러 개의 시큐리티 필터들을 체인 형태로 연결하여 인증/인가를 처리(보안은 Spring MVC에 진입하기 전 가장 앞단에서 처리)

## Interceptor 

`Spring Container 영역(Controller 전/후)`

- **Spring MVC 공통 처리**
  - 인증/인가(로그인 체크, 권한 확인)
  - API 호출 처리(기록 로깅, 접근 제한)
  - 컨트롤러로 가는 공통 데이터(파라미터) 추가/가공

## AOP(Aspect Oriented Programming, 관점 지향 프로그래밍)

`Spring Container 메서드 영역(주로 Service 전/후)`

- **Proxy 패턴으로 공통 처리**
  - 트랜잭션 관리 (`@Transactional`)
  - 로깅 및 성능 측정 (Execution Time)
  - 캐싱(Caching) 및 예외 복구
