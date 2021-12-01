## Spring AOP 개념

### AOP 적용방식

1. 컴파일 시점
2. 클래스 로딩 시점
3. 런타임 시점 - 프록시

- 실제 대상 코드 유지. 프록시를 통해 부가 기능 적용

### AOP 적용 위치

- 프록시 방식을 상요하는 스프링 AOP는 메서드 실행 지점에만 AOP 적용 가능
- 프록시는 메서드 overring 방식으로 동작하기 때문에 생성자나 static 메서드, 필드값 접근에는 프록시 개념 적용 불가
- **스프링 AOP 의 조인포인트는 메서드 실행으로 제한**

## Spring AOP 용어 정리

### 조인 포인트(Join Point)

- 어드바이스가 적용될 수 있는 위치, 메소드 실행, 생성자 호출, 필드 값 접근, static 메서드 접근 같은 프로그램 실행 중지점
- 조인 포인트는 추상적인 개념. AOP 를 적용 할 수 있는 모든 지점
- 스프링 AOP는 프록시 방식을 사용하므로 조인 포인트는 항상 메소드 실행 지점으로 제한

### 포인트컷(Pointcut)

- 조인 포인트 중에서도 어드바이스가 적용될 위치를 선별하는 기능
- 주로 AspectJ 표현식 사용
- 스프링 AOP 는 메서드 실행 지점만 Pointcut 으로 선별 가능

### 타겟(Target)

- 어드바이스를 받는 객체, 포인트컷으로 결정

### 어드바이스(Advice)

- 부가 기능
- 특정 조인 포인트에서 Aspect 에 의해 취해지는 조치
- Around, Before, After 과 같은 다양한 종류의 어드바이스 존재

### 애스펙트(Aspect)

- 어드바이스 + 포인트컷을 모듈화 한것
- `@Aspect`
- 여러 어드바이스와 포인트 컷이 함께 존재

### 어드바이저(Advisor)

- 하나의 어드바이스와 하나의 포인트 컷으로 구성
- 스프링 AOP 에서만 사용되는 특별한 용어

### 위빙(Weaving)

- 포인트컷으로 결정한 타겟의 조인 포인트에 어드바이스를 적용하는 것
- 위빙을 통해 핵심 기능 코드에 영햐을 주지 않고 부가 기능을 추가
- AOP 적용을 위해 애스펙트를 객체에 연결한 상태
  - 컴파일 타임(AspectJ compiler)
  - 로드 타임
  - 런타임 -> 스프링 AOP 는 런타임, 프록시 방식

### AOP 프록시

- AOP 기능을 구현하기 위해 만든 프록시 객체, 스프링에서 AOP 프록시는 JDK 동적 프록시 또는 CGLIB 프록시

## Spring AOP 구현

### @Pointcut

- 메서드명과 파라미터를 합쳐 포인트컷 시그니처(Signature)라 함
- 메서드의 반환 타입 -void
- 코드 내용은 비워둠

### @Order

- Aspect 단위로 순서 지정 가능

### Advice 종류

- `@Around`
  - 메서드의 주변에서 실행
  - 가장 강력한 advice
    - 조인 포인트 실행 여부 선택 `joinPoint.proceed() 호출 여부 선택`
    - 전달 값 변환: `joinPoint.proceed(args[])`
    - 반환 값 변환
    - 예외 변환
    - 트랜잭션 처럼 `try-catch-finally` 모두 들어가는 구문 처리 가능
  - 어드바이스의 첫번째 파라미터는 `ProceedingJoinPoint` 사용
  - `proceed()` 를 통해 대상 실행
  - `proceed()` 를 여러번 실행 가능(재시도)
- `@Before`
  ```java
  @Before("com.xx.xxx.xxx")
  public void doBefore(JoinPoint joinPoint) {
    log.info("[before] {}", joinPoint.getSignature());
  }
  ```  
  - 조인 포인트 실행 전에 수행
  - `@Around`와 다르게 작업 흐름 변경 불가
- `@AfterReturning`
  ```java
  @AfterReturning(value = "com.xx.xxx.xxx" returning = "result")
  public void doReturn(JoinPoint joinPoint, result) {
    log.info("[return] {} return={}", joinPoint.getSignature(), result);
  }
  ```  
  - 메서드 실행히 정상적으로 반환될 때 실행
  - `returning` 속성에 사용된 이름은 어드바이스 메서드의 매개변수 이름과 일치해야 함
  - `returning` 절에 지정된 타입의 값을 반환하는 메서드만 대상으로 실행
  - `@Around` 와 다르게 반환 객체를 변경 불가. 반환 객체 조작은 가능
- `@AfterThorwing`
  ```java
  @AfterThrowing(value = "com.xx.xxx.xxx" throwing = "ex")
  public void doReturn(JoinPoint joinPoint, Exception ex) {
    log.info("[exception] {} message={}", joinPoint.getSignature(), ex.getMessage);
  }
  ```  
  - 메서드 실행이 예외를 던져서 종료될 때 실행
  - `throwing` 속성에 사용된 이름은 어드바이스메서드의 매개변수 이름과 일치해야 함
  - `throwing` 절에 지정된 타입과 맞는 예외를 대상으로 실행
- `@After`
  - 메서드 실행이 종료되면 실행(finally)
  - 정상 및 예외 반환 조건 모두 처리
  - 일반적으로 리소스 해제 시 사용