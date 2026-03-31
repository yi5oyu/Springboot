<div align="center">

# **REST API**

`문서화/설계 가이드`    

</div>

```
HTTP 상태 코드 사용

200 OK: 정상 처리
201 Created: 리소스 생성 성공 (POST)
400 Bad Request: 클라이언트의 잘못된 요청
401 Unauthorized: 인증 실패
403 Forbidden: 권한 없음
404 Not Found: 리소스 없음
500 Internal Server Error: 서버 내부 오류
```

### URI 작성 규칙

1. 계층관계는 슬래시(/) 사용

```
O: /users/1/orders

마지막엔 슬래시(/) 포함하지 않음
X: /users/1/orders/

소문자로 작성
X: /USERS/1/ORDERS
```

2. 하이픈(-) 사용

```
O: /user-profiles

X: /user_profiles
```

> 웹 브라우저나 문서에서 URI에 밑줄(링크)이 쳐질 경우 언더바(_)가 가려질 수 있음

3. 행위를 포함하지 않는 명사형으로 작성

```
HTTP Method(GET, POST, PUT, DELETE)로 표현

O: POST /users

X: POST /create-user, GET /getUsers
```

4. 엔드포인트 이름을 복수형으로 작성

```
O: /users, /products

X: /user, /product
```

5. 파일 확장자 포함하지 않음

```
응답 형식은 URI가 아닌 HTTP Header의 Accept를 통해 결정

O: GET /users/1 (Header: Accept: application/json)

X: GET /users/1.json

경로 변수와 쿼리 파라미터 역할을 구분

특정 리소스를 식별: /users/123
정렬, 필터링, 페이징 처리가 필요: /users?role=admin&page=1
```

## Tools

### Spring REST Docs

테스트 코드를 통과해야만 문서가 생성됨. 실제 코드와 문서의 불일치 방지      
초기 설정과 테스트 코드 작성 필수   

### Swagger

어노테이션 기반으로 적용. 생성된 웹 UI에서 즉각적인 API 테스트 가능     
코드에 문서화를 위한 어노테이션이 추가, 코드 가독성 저하

<!--
### Postman

API 개발 후 요청/응답을 컬렉션으로 묶어 팀원들과 워크스페이스에서 쉽게 공유
코드가 변경될 때마다 수동으로 문서 업데이트 필요
