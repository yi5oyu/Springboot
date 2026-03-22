<div align="center">

# API 통신

`웹 서비스 통신 방식`    

</div>

## [REST](https://github.com/yi5oyu/Study/blob/main/SpringBoot/REST%20API/%EC%A0%95%EC%9D%98)

`JSON/XML 데이터를 자원의 위치(URI)와 행위(HTTP Method)를 조합해 통신`

```
HTTP 메소드

GET 리소스 조회
POST 데이터 추가, 등록
PUT 리소스 수정(없으면 추가)
PATCH 리소스 부분 변경
DELETE 리소스 삭제
HEAD 리소스 조회(GET)(HTTP body 부분 제외하고 조회)
OPTIONS 통신 옵션 확인(메소드, 헤더 등...)
```

### RESTful

`REST 아키텍처 스타일의 설계 원칙`

## GraphQL

`하나의 엔드포인트(/graphql)만 열어두고 통신해 클라이언트가 원하는 데이터만 원하는 구조로 요청`

불필요한 데이터까지 받는 문제와 데이터가 부족해 여러 번 호출해야 하는 문제 해결

## SOAP

`XML 형태로 통신`

보안과 트랜잭션 처리가 매우 강력하지만 규칙이 까다롭고 느리고 복잡한 구조
