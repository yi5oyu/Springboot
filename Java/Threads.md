<div align="center">

# **스레드**

`JVM 레벨에서 관리되는 경량 스레드`    

</div>

OS 스레드와 직접 매핑되지 않고, 여러 개의 가상 스레드가 소수의 플랫폼 스레드 위에서 실행됨

JAVA 스래드 객체
new Thread() 호출 -> 힙 메모리 영역에 객체 생성(스레드 이름, 우선순위, 상태값) -> JVM의 JNI(Java Native Interface)를 통해 OS 커널에 진짜 스레드 요청 -> 리소스 커널 스레드 생성
JVM 스택 영역이 곧 OS가 할당한 Native 메모리 영역 (스택)

Java 힙에 있는 스레드 객체는 내부적으로 Native ID나 포인터(주소) 정보를 가지고 있음. 이 주소를 통해 Java 객체와 실제 OS 커널 스레드가 1:1로 매핑
