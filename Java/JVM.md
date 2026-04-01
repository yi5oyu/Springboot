<div align="center">

# **JVM(Java Virtual Machine)**

`Java로 개발한 프로그램을 컴파일하여 만들어지는 바이트코드를 실행시키기 위한 가상머신`    

| [클래스 로더 서브시스템](#클래스-로더-서브시스템-class-loader-subsystem) • [런타임 데이터 영역](#런타임-데이터-영역-runtime-data-areas) • [실행 엔진](#실행-엔진-execution-engine) |

<img width="641" height="1021" alt="image" src="https://github.com/user-attachments/assets/d6da4f74-8d34-4f87-8a1a-bfcef556e58d" />

</div>

---

`자바 소스 코드(.java)를 컴파일러(javac)가 바이트코드(.class)로 변환`

## 클래스 로더 서브시스템 (Class Loader Subsystem)

동적 로딩으로 컴파일된 바이트코드를 런타임에 필요한 클래스만 OS가 할당한 메모리 영역으로 적재

### Loading (로딩)

부트스트랩, 확장(Extension), 시스템(Application) 클래스 로더가 위임 모델을 통해 .class 파일을 찾아 메모리에 로드

### Linking (링킹)

Verify (검증): 읽어 들인 바이트코드가 자바 언어 명세 및 JVM 규약에 맞는지 유효성을 검사
Prepare (준비): 클래스 변수(static 변수)를 위한 메모리를 할당하고 기본값으로 초기화
Resolve (분석): 심볼릭 메모리 레퍼런스를 실제 메모리 주소인 다이렉트 레퍼런스로 변환

### Initialization (초기화)

클래스 변수들을 적절한 값(개발자가 지정한 값)으로 초기화하고 static 블록을 실행

---

## 런타임 데이터 영역 (Runtime Data Areas)

`OS로부터 할당받아 관리하는 메모리 영역`

### Method Area (Metaspace)

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
 - 클래스 이름(Example), 부모 클래스 이름(java.lang.Object), 클래스 종류(interface/class/enum), 접근 제어자(public, private ...)

2. 메서드 정보
 - 메서드 이름(exam), 리턴 타입(void), 매개변수(int a), 바이트코드
 - 바이트코드: int value = a * 10000;/long currentTime = System.currentTimeMillis();를 번역한 기계어 명령어
   
3. 필드 정보
 - 클래스에 선언된 모든 변수의 이름, 타입, 접근 제어자
 - NAME 필드: 이름(NAME), 타입(String), 접근 제어자(public static final)
 - example 필드: 이름은(example), 타입(String), 접근 제어자(private)

4. Static 변수
 - 클래스 레벨에서 관리되는 정적 변수(static 키워드가 붙은 변수들)
 - NAME 변수 안: 힙 영역의 메모리 주소(참조값) 저장("Example" 데이터는 힙 영역의 String Pool에 있음)

5. Runtime Constant Pool
 - 컴파일 타임에 알려진 숫자 리터럴, 문자열 리터럴, 클래스/메서드/필드의 심볼릭 레퍼런스(이름을 통한 참조)가 저장
 - 리터럴 상수: a * 10000을 계산하기 위해서 10000이라는 숫자 값을 저장
 - 심볼릭 레퍼런스: 진짜 메모리 주소대신 런타임 실행될때 도움 주기 위한 참조 (DNS와 비슷)

### Heap Area (힙 영역)

![0_rzQ6-DyP-2gjiua7](https://github.com/user-attachments/assets/4c33a65e-9ebb-4267-b8b4-b55d4bf86dca)

```
Example example = new Example();

1. 객체 생성
 - 힙 영역의 Eden에 객체 생성

2. Minor GC 동작
 - Eden 메모리가 가득찼을때 참조되지않은 객체 정리

3. Survivor 이동
 - 살아남은 객체는 Survivor 0로 이동
 - example 객체 count 1 추가(1)

4. Minor GC 재동작
 - Eden 메모리 정리중 살아남은 객체 + Survivor 0 객체 Survivor 1로 이동
 - Survivor 0 비어있음
 - Survivor 1: example 객체 count 1 추가(2)

5. Minor GC 재동작
 - Eden 메모리 정리중 살아남은 객체 + Survivor 1 객체 Survivor 0으로 이동
 - Survivor 1 비어있음
 - Survivor 0: example 객체 count 1 추가(3)

6. Old 이동
 - JVM 설정에 따라 count이상 객체 Old 영역으로 이동

7. Major GC
 - Old 메모리가 가득찼을때 

> 메모리 단편화: 두 Survivor 방 중 하나는 반드시 비어 있어야 함
> OutOfMemoryError: 큰 메모리 적재시 여유 공간 있어도 빈 공간 사이가 충분하지 않으면 에러 발생할 수 있음
> 탐색 비용: 새로운 객체 생성할 때마다 빈자리 찾아 넣어야해서 속도 저하 발생
```

#### 1. Young Generation

`GC(Minor GC)가 자주, 빠르게 발생`

- Eden: new 키워드로 객체를 생성했을때 여기에 만들어짐
- Survivor 0/1: Eden 방이 꽉 차서 청소를 한 번 당했을 때 살아남은 객체들이 잠시 대피하는 공간

> 두 Survivor 방 중 하나는 반드시 비어 있어야 함

#### 2. Old Generation

`꽉 차면 GC(Major GC or Full GC) 발생  `

- Old: 오래 살아남은 객체들이 넘어오는 넓은 공간
  
> 프로그램 전체가 잠깐 멈추는 현상(STW, Stop-The-World) 발생   

### Stack Area (스택 영역)

`메서드의 실행 흐름을 기억하고 임시 데이터를 계산`

#### 스택 프레임

`메서드 호출될 때마다 생성`

```
1. 프로그램 시작                                                                     ┌───────Stack Area───────┐
 - main 메서드 실행                                                                  │                        │
 - main 프레임 생성                                                                  │ ┌───────exam(5)──────┐ │
                                                                                    │ │                    │ │
2. exam 메서드 프레임 생성                                                           │ └────────────────────┘ │ 
 - exam(5); 호출                                                                    │ ┌────────main────────┐ │
 - main 메서드 프레임 실행 멈추고 대기                                               │ │                    │ │
 - exam 프레임 생성                                                                 │ └────────────────────┘ │ 
  > 프레임 데이터: 메서드 종료 후 돌아갈 main 메서드 주소 기록                        └────────────────────────┘
  > 지역 변수 배열: [0]: this(자기 객체), [1]: 5(파라미터 값) 저장

3. 연산 준비
 - 지역 변수 배열 [1] 값 5 복사해 피연산자 스택에 넣음
 - 메타스페이스(Runtime Constant Pool) 리터럴 상수 10000을 피연산자 스택에 넣음
  > 피연산자 스택: 10000, 5

4. 연산 수행
 - 피연산자 스택에서 값 꺼냄(POP)(10000, 5)
 - CPU가 연산 수행(5 * 10000)한 결과값을 다시 피연산자 스택에 저장
  > 피연산자 스택: 50000

5. 결과 저장
 - 피연산자 스택에서 값 꺼내(POP) 지역 변수 배열에 저장
 > 피연산자 스택: (비어있음)
 > 지역 변수 배열: [0]: this(자기 객체), [1]: 5(파라미터 값), [2]: 50000(결과값)

6. 메서드 종료/소멸
 - exam 메서드 종료 후 main 프레임 주소로 복귀(exam 프레임을 스택 영역에서 제거)
 - main 프레임 실행
```

1. Frame Data (프레임 데이터)

 - 메서드가 정상 종료되었을 때 돌아갈 이전 메서드 주소 저장
 - 예외 처리 정보, 런타임 상수 풀의 데이터를 가져오기 위한 참조 링크 저장

2. Local Variable Array (지역 변수 배열)

 - 메서드 안에서 선언된 지역 변수, 파라미터, this 객체 참조값 저장
 - 인덱스로 데이터 값만 저장됨

3. Operand Stack (피연산자 스택)

 - LIFO 구조로 연산을 위한 데이터 값 임시 저장

#### PC Register (PC 레지스터)

`각 스레드가 현재 실행하고 있는 JVM 바이트코드의 메모리 주소 저장하는 작은 공간`

스레드가 생성될 때마다 1개씩 1:1로 함께 생성

##### 컨텍스트 스위칭

- 상태 저장: 현재 위치를 실시간으로 업데이트하며 기억
- 하나의 CPU가 여러개의 스레드가 동시에 처리하는 것처럼 빠르게 처리

> Native Method(C/C++) 실행 시 PC 레지스터 값: Undefined

#### Native Method Stack

`자바 코드가 아닌 다른 언어(C/C++, 어셈블리어 등)로 작성된 네이티브 코드를 실행할 때 사용하는 메모리`

> JNI(Java Native Interface)를 통해 호출될 때 사용

---

## 실행 엔진 (Execution Engine)

### Interpreter (인터프리터)

`코드를 위에서부터 한 줄씩 읽고 CPU가 아는 기계어로 번역/실행`

### JIT Compiler (Just-In-Time 컴파일러)

`자주 쓰이는 코드(Hot Spot)를 전체 블록을 통째로 기계어로 번역해 캐시에 저장`

### Garbage Collector (GC, 가비지 컬렉터)

`참조하지 않는 더 이상 필요 없어진 메모리를 백그라운드에서 정리`

#### STW(Stop The World)

`GC가 메모리를 정리 작업을 수행하기 위해 JVM에서 실행 중인 애플리케이션의 모든 작업 스레드(Thread)를 강제로 일시 정지`

- 데이터 안정성
  - GC가 지우려는 객체를 정리하려고 할때 애플리케이션 스레드가 실행 중이라면 NullPointerException 발생
  - 객체가 빈 공간으로 복사될 때 다른 주소값을 읽거나 쓰게 될 수 있음

- 응답 지연
  - STW가 발생하는 동안 모든 어플리케이션 동작 멈춤
 
- 타임아웃
  - STW으로 인해 멈춰있는 시간이 길어지면 다른 서버 요청에 대한 응답 늦어져 연결 끊어질 수 있음 

#### 메모리 단편화(Memory Fragmentation)

##### Mark & Sweep

- Mark (식별): 사용 중인 객체들에 표시(Mark)를 남김
- Sweep (쓸기): 전체 메모리를 보면서 표시(Mark)가 남지 않은 객체 정리    

```
Mark-Sweep-Compact

1. 메모리에 데이터 쌓임
🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩

2. Mark
 - 정리해야할 객체 표시
🟩🟩🟥🟩🟥🟥🟩🟩🟥🟩

3. Sweep
 - 객체 정리
🟩🟩⬜🟩⬜⬜🟩🟩⬜🟩

4. 단편환 문제
 - 새로운 객체 생성 시도: 🟦🟦🟦
 - 연속된 공간이 없음

5. Compaction
 - 객체 압축
🟩🟩🟩🟩🟩🟩⬜⬜⬜⬜

6. 객체 생성/삽입
🟩🟩🟩🟩🟩🟩🟦🟦🟦⬜
```

##### Mark & Evacuate

`힙 영역을 물리적으로 동일한 크기의 방(Region)으로 쪼개고 논리적인 역할(Eden, Survivor, Old)을 동적으로 부여`

- Mark (식별): 사용 중인 객체들에 표시(Mark)를 남김
- Targeting (타겟 선정): 전체 리전 중 쓰레기(Garbage)가 가장 많이 쌓인 리전을 우선 청소 타겟으로 선정 
- Evacuate (대피): 타겟 리전에서 살아남은 객체들만 새로운 빈 방(Free Region)으로 복사(이동)
- Reclaim (회수): 객체가 모두 대피하여 쓰레기만 남은 기존 리전들을 통째로 비우고 다시 빈 방(Free Region)으로 반환

```
초기 리전
[O]	[E]	[F]	[S]
[F]	[O]	[E]	[F]
[S] [F] [E] [S]

1. Old 영역 메모리 가득 참
리전[O] 1: 🟩🟩🟩🟩
리전[O] 6: 🟩🟩🟩🟩

2. Mark
리전[O] 1: 🟩🟥🟥🟩
리전[O] 6: 🟥🟩🟥🟥
리전[F] 3: ⬜⬜⬜⬜

3. Evacuate
 - 사용중인 객체를 빈 방(Free) 리전으로 이동(복사)
리전[O] 1: 🟩🟥🟥🟩
리전[O] 6: 🟥🟩🟥🟥
리전[F] 3: 🟩🟩🟩⬜

4. Reclaim
 - 기존 리전 모두 정리
리전[F] 1: ⬜⬜⬜⬜
리전[F] 6: ⬜⬜⬜⬜
리전[O] 3: 🟩🟩🟩⬜

리전
 - 1, 6번 방은 비워지고 3번 방이 Old가 됨
[F]	[E]	[O]	[S]
[F]	[F]	[E]	[F]
[S] [F] [E] [S]

> Free 리전은 유동적으로 Eden, Survivor, Old로 바뀜
> 만일 Free 리전이 존재하지 않는다면 STW, Full GC 실행
```

#### 발전 과정

1. Serial GC

- 단일 스레드
- 애플리케이션의 모든 작업이 완전히 정지(STW), 힙 메모리가 커질수록 정지 시간 증가

2. Parallel GC

- 멀티 스레드
- STW 시간이 단축, 애플리케이션 처리량(Throughput) 증가
- 메모리가 수십 GB 단위로 커지면 한 번 멈출 때 수 초 이상 지연 밣생

3. CMS(Concurrent Mark Sweep) GC

- 애플리케이션 스레드 + GC 스레드
- 쓰레기 객체 찾는 Mark, 지우는 Sweep을 동시(Concurrent) 처리
- STW 짧아짐
- 메모리 단편화 발생, 메모리 정리하는 STW 발생

4. G1(Garbage-First) GC

- 리전 단위 분리
- 쓰레기 객체 많은 리전 우선 정리, 살아남은 객체 빈 리전으로 복사(STW 발생)
- STW 시간 예측 가능

5. ZGC

- 백그라운드에서 살아있는 객체를 다른 곳으로 옮기고 메모리를 압축하는 작업까지 동시 수행
- 매우 짧은 STW(1ms 이하)
- CPU 사용량 높음

> 컬러 포인터 (Colored Pointers): 객체에 메모리 주소(포인터) + 객체 상태 비트값         
> 로드 배리어 (Load Barrier): 메모리에서 객체 읽을 때마다 확인       


<!-- 
```
1. GC 스레드
 - 백그라운드에서 
```

<!-- 

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

3. 실행 엔진 (Execution Engine)

메모리(Runtime Data Areas)에 적재된 바이트코드를 기계어로 변경하여 명령어 단위로 실행하는 역할을 합니다. 성능에 가장 직접적인 영향을 미치는 곳입니다.
인터프리터 (Interpreter): 바이트코드를 한 줄씩 읽고 기계어로 번역하여 실행합니다. 속도가 느리다는 단점이 있습니다.
JIT (Just-In-Time) 컴파일러: 인터프리터의 단점을 보완하기 위해 도입되었습니다.
인터프리터 방식으로 실행하다가, 적절한 빈도 이상으로 반복 실행되는 코드(Hot Spot)를 발견하면 JIT 컴파일러가 해당 바이트코드 전체를 네이티브 머신 코드로 컴파일해 버립니다.

이후에는 번역 과정 없이 캐싱된 네이티브 코드를 직접 실행하므로 극적인 성능 향상을 가져옵니다. (C1, C2 컴파일러를 통한 단계별 최적화 수행)

가비지 컬렉터 (Garbage Collector, GC): 힙 영역을 모니터링하며 더 이상 참조되지 않는 객체(Unreachable Object)를 찾아 메모리에서 해제합니다.

Stop-The-World (GC를 수행하기 위해 애플리케이션 스레드가 멈추는 현상) 시간을 줄이는 것이 백엔드 성능 최적화의 핵심입니다.

G1 GC, ZGC, Shenandoah GC 등 서비스의 특성(지연 시간 최소화 vs 처리량 극대화)에 맞춰 다양한 알고리즘을 선택하여 사용할 수 있습니다.
