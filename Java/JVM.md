<div align="center">

# **JVM(Java Virtual Machine)**

`Java로 개발한 프로그램을 컴파일하여 만들어지는 바이트코드를 실행시키기 위한 가상머신`    

<!-- | [Spring & Spring boot](#-spring--spring-boot) • [Framework & Library](#-framework--library) • [보안](#%EF%B8%8F-보안) • [AI](#-ai) • [OPEN API](#-open-api) • [애노테이션](#%EF%B8%8F-애노테이션annotation)  | -->

</div>

## 구조

<img width="641" height="1021" alt="image" src="https://github.com/user-attachments/assets/d6da4f74-8d34-4f87-8a1a-bfcef556e58d" />

## 

자바 소스 코드(.java)를 컴파일러(javac)가 바이트코드(.class)로 변환

## 클래스 로더 서브시스템 (Class Loader Subsystem)

동적 로딩으로 컴파일된 바이트코드를 런타임에 필요한 클래스만 OS가 할당한 메모리 영역으로 적재

### Loading (로딩)

부트스트랩, 확장(Extension), 시스템(Application) 클래스 로더가 위임 모델을 통해 .class 파일을 찾아 메모리에 로드

### Linking (링킹)

Verify(검증): 읽어 들인 바이트코드가 자바 언어 명세 및 JVM 규약에 맞는지 유효성을 검사합니다.
Prepare(준비): 클래스 변수(static 변수)를 위한 메모리를 할당하고 기본값으로 초기화합니다.
Resolve(분석): 심볼릭 메모리 레퍼런스를 실제 메모리 주소인 다이렉트 레퍼런스로 변환합니다.

### Initialization (초기화)

클래스 변수들을 적절한 값(개발자가 지정한 값)으로 초기화하고, static 블록을 실행

## 런타임 데이터 영역 (Runtime Data Areas)

`OS로부터 할당받아 관리하는 메모리 영역`

### Method Area(Metaspace)

```java
public class Example {
    // 클래스 변수 (Static 변수)
    public static final String NAME = "Example"; 
    // 인스턴스 변수
    private String example;
    // 일반 메서드
    public void exam(int a) {
        // 지역 변수
        int value = a * 10000; 
        // 네이티브 메서드
        long currentTime = System.currentTimeMillis();
    }
}
```

1. 클래스 정보
 - 클래스 이름(Example), 부모 클래스 이름(java.lang.Object), 클래스 종류(interface/enum/class), 접근 제어자(public, private)

2. 메서드 정보
 - 메서드 이름, 리턴 타입, 매개변수, 바이트코드 그 자체.
   
3. 필드 정보
 - 클래스에 선언된 모든 변수의 이름, 타입, 접근 제어자

4. Static 변수
 - static 키워드가 붙은 변수들(객체 생성 전)

5. Runtime Constant Pool
 - 컴파일 타임에 알려진 숫자 리터럴, 문자열 리터럴, 클래스/메서드/필드의 심볼릭 레퍼런스(이름을 통한 참조)가 저장

### 스레드 공유 영역

Metaspace, Method Area

클래스 수준의 정보(클래스 이름, 부모 클래스 이름, 메서드, 변수)와 static 변수가 저장

Heap Area (힙 영역)

### Thread-safe 영역

Stack Area
PC (Program Counter) Register
Native Method Stack

## 실행 엔진 (Execution Engine)

Gemini의 응답
JVM(Java Virtual Machine)은 단순히 자바 코드를 실행하는 환경을 넘어, 운영체제(OS)와 애플리케이션 사이에서 메모리를 관리하고 코드를 최적화하는 매우 정교한 가상 머신입니다. "Write Once, Run Anywhere"라는 자바의 철학을 완성하는 핵심 엔진이기도 하죠.

개발자의 관점에서 성능 최적화와 트러블슈팅을 위해 반드시 알아야 할 JVM의 내부 구조를 크게 3가지 핵심 영역으로 나누어 자세히 설명해 드리겠습니다.

1. 클래스 로더 서브시스템 (Class Loader Subsystem)
자바 소스 코드(.java)를 컴파일러(javac)가 바이트코드(.class)로 변환하면, JVM은 이 바이트코드를 OS가 할당한 메모리 영역으로 적재해야 합니다. 이 역할을 클래스 로더가 수행하며, 동적 로딩(Dynamic Loading) 방식을 사용해 런타임에 필요한 클래스만 메모리에 올립니다.

클래스 로더는 다음 세 단계를 거칩니다.

Loading (로딩): 부트스트랩, 확장(Extension), 시스템(Application) 클래스 로더가 위임 모델을 통해 .class 파일을 찾아 메모리에 로드합니다.

Linking (링크):

Verify (검증): 읽어 들인 바이트코드가 자바 언어 명세 및 JVM 규약에 맞는지 유효성을 검사합니다.

Prepare (준비): 클래스 변수(static 변수)를 위한 메모리를 할당하고 기본값으로 초기화합니다.

Resolve (분석): 심볼릭 메모리 레퍼런스를 실제 메모리 주소인 다이렉트 레퍼런스로 변환합니다.

Initialization (초기화): 클래스 변수들을 적절한 값(개발자가 지정한 값)으로 초기화하고, static 블록을 실행합니다.

2. 런타임 데이터 영역 (Runtime Data Areas)
JVM이 OS로부터 할당받아 관리하는 메모리 영역입니다. 스레드(Thread) 공유 여부에 따라 나눌 수 있습니다.

모든 스레드가 공유하는 영역 (동기화 문제 발생 가능)

Method Area (메서드 영역): 클래스 수준의 정보(클래스 이름, 부모 클래스 이름, 메서드, 변수)와 static 변수가 저장됩니다. Java 8 이후에는 이 영역의 일부였던 Permanent Generation(PermGen)이 Metaspace로 대체되어 네이티브 메모리를 사용하게 되었습니다.

Heap Area (힙 영역): new 키워드로 생성된 모든 객체와 배열이 저장되는 곳입니다. 애플리케이션 생명주기 동안 동적으로 할당되며, 가비지 컬렉터(GC)의 주요 타겟이 되는 영역입니다.

각 스레드마다 개별적으로 생성되는 영역 (Thread-safe)

Stack Area (스택 영역): 메서드 호출 시마다 각각의 스택 프레임(Stack Frame)이 생성되어 쌓입니다. 지역 변수, 매개 변수, 리턴 값, 연산 중 발생하는 임시 데이터 등이 저장되며 메서드 종료 시 프레임이 삭제됩니다.

PC (Program Counter) Register: 현재 스레드가 실행 중인 JVM 명령어의 주소를 가리킵니다. 컨텍스트 스위칭 시 스레드가 어디까지 실행했는지 기억하는 역할을 합니다.

Native Method Stack: 자바 외의 언어(C, C++ 등)로 작성된 네이티브 코드를 실행하기 위한 메모리 영역입니다. JNI(Java Native Interface)를 통해 호출될 때 사용됩니다.

3. 실행 엔진 (Execution Engine)

메모리(Runtime Data Areas)에 적재된 바이트코드를 기계어로 변경하여 명령어 단위로 실행하는 역할을 합니다. 성능에 가장 직접적인 영향을 미치는 곳입니다.
인터프리터 (Interpreter): 바이트코드를 한 줄씩 읽고 기계어로 번역하여 실행합니다. 속도가 느리다는 단점이 있습니다.
JIT (Just-In-Time) 컴파일러: 인터프리터의 단점을 보완하기 위해 도입되었습니다.
인터프리터 방식으로 실행하다가, 적절한 빈도 이상으로 반복 실행되는 코드(Hot Spot)를 발견하면 JIT 컴파일러가 해당 바이트코드 전체를 네이티브 머신 코드로 컴파일해 버립니다.

이후에는 번역 과정 없이 캐싱된 네이티브 코드를 직접 실행하므로 극적인 성능 향상을 가져옵니다. (C1, C2 컴파일러를 통한 단계별 최적화 수행)

가비지 컬렉터 (Garbage Collector, GC): 힙 영역을 모니터링하며 더 이상 참조되지 않는 객체(Unreachable Object)를 찾아 메모리에서 해제합니다.

Stop-The-World (GC를 수행하기 위해 애플리케이션 스레드가 멈추는 현상) 시간을 줄이는 것이 백엔드 성능 최적화의 핵심입니다.

G1 GC, ZGC, Shenandoah GC 등 서비스의 특성(지연 시간 최소화 vs 처리량 극대화)에 맞춰 다양한 알고리즘을 선택하여 사용할 수 있습니다.
