# Feign API Client 사용 및 테스트 가이드

## 소개
Feign은 HTTP Client를 간편하게 구현하기 위한 Java 라이브러리로, Spring Cloud OpenFeign은 Spring Boot와 통합하여 REST API 호출을 손쉽게 처리할 수 있도록 도와줍니다.

---

## 주요 기능
- **선언적 REST Client**: 인터페이스 기반으로 API 호출 정의.
- **Spring Boot와 간편한 통합**: 간단한 설정으로 API 호출 가능.
- **Retry 및 에러 처리**: 내장된 오류 처리 및 재시도 기능 제공.
- **Request/Response 로깅**: 요청 및 응답 로깅 기능 지원.

---

## 사용 방법

1. **Feign Client 정의**:
    - `@FeignClient`와 함께 인터페이스로 API 호출을 선언적으로 정의합니다.
    - 예: `@FeignClient(name = "jsonPlaceholderClient", url = "https://jsonplaceholder.typicode.com")`

2. **DTO 생성**:
    - Feign Client가 반환하는 데이터를 매핑할 DTO 클래스를 생성합니다.

3. **Feign Client 호출**:
    - 정의된 Feign Client를 서비스 클래스에서 주입받아 사용합니다.

---

## 테스트

### 내부적으로 사용하는 테스트 방식

1. **WireMock 기반 Feign Client 테스트**:
    - **WireMock**을 사용하여 외부 API 호출을 Mocking합니다.
    - Feign Client가 외부 API를 올바르게 호출하고, 기대한 데이터를 반환하는지 검증합니다.

2. **Mockito를 활용한 서비스 계층 테스트**:
    - **Mock** 객체를 생성하여 Feign Client의 동작을 시뮬레이션합니다.
    - 특정 입력에 대해 정의된 결과를 반환하도록 설정하여, 다양한 시나리오(정상 동작, 예외 처리 등)를 테스트합니다.

3. **테스트 독립성 보장**:
    - Mocking을 통해 외부 환경(API 서버, 데이터베이스 등)에 의존하지 않고 독립적인 테스트를 수행합니다.

---

## 실행 방법

1. **Feign Client 설정**
    - `@FeignClient`를 사용하여 인터페이스 기반 API 호출 정의.
    - URL, 요청 매핑 등을 명확히 설정.

2. **테스트 실행**
    - 모든 테스트 실행:
      ```bash
      ./gradlew test
      ```
    - 특정 테스트 실행:
      ```bash
      ./gradlew test --tests "PostTest"
      ```
    - 통합 테스트 실행:
      ```bash
      ./gradlew integrationTest 
      ```

3. **WireMock 서버 사용**
    - WireMock 서버를 통해 외부 API 호출을 Mock하여 Feign Client의 동작 검증.

---

## **Spring Boot 주요 테스트 어노테이션**

| **어노테이션**         | **테스트 범위**                                          | **설명**                                                                 |
|------------------------|--------------------------------------------------------|--------------------------------------------------------------------------|
| `@SpringBootTest`      | 통합 테스트 (Integration Test)                         | 애플리케이션 컨텍스트를 로드하여 전체적으로 테스트.                       |
| `@WebMvcTest`          | Controller 테스트 (단위 테스트에 가까움)               | 특정 Controller와 관련된 Bean만 로드.                                    |
| `@DataJpaTest`         | Repository 테스트 (단위 테스트와 통합 테스트 중간)      | JPA와 관련된 Bean만 로드. (데이터베이스 연동 테스트)                      |
| `@MockBean`, `@SpyBean`| 단위 테스트 (Unit Test)                                | 특정 Bean의 Mock 또는 Spy 객체를 주입하여 독립적으로 테스트.              |
| `@TestConfiguration`   | 테스트 전용 Bean 설정                                  | 특정 테스트에만 필요한 Bean을 구성하여 제공.                              |

---

### **주요 어노테이션 설명**

#### **1. `@SpringBootTest`**
- **범위**: 통합 테스트 (Integration Test)
- **설명**: 전체 애플리케이션 컨텍스트를 로드하여 통합적으로 테스트합니다.
- **사용 사례**:
   - 서비스, 레포지토리, 컨트롤러 등 애플리케이션의 전체 계층을 포함한 테스트.
   - 데이터베이스, 외부 API 등의 의존성을 포함한 통합 환경 검증.

#### **2. `@WebMvcTest`**
- **범위**: Controller 테스트
- **설명**: 특정 컨트롤러와 관련된 Bean만 로드하여 HTTP 요청/응답을 검증합니다.
- **사용 사례**:
   - 컨트롤러 계층의 요청/응답 검증.
   - HTTP 상태 코드, JSON 응답 구조 검증.

#### **3. `@DataJpaTest`**
- **범위**: Repository 테스트
- **설명**: JPA와 관련된 Bean만 로드하여 데이터베이스 연동 동작을 검증합니다.
- **사용 사례**:
   - CRUD 동작 검증.
   - JPA 쿼리 메서드 검증.

#### **4. `@MockBean`, `@SpyBean`**
- **범위**: 단위 테스트
- **설명**: 테스트 대상 객체의 의존성을 Mock 또는 Spy 객체로 대체하여 독립적으로 테스트합니다.
- **사용 사례**:
   - 특정 Bean의 메서드 호출 횟수 및 파라미터 검증.
   - Mock 동작 정의로 테스트 시나리오 생성.

#### **5. `@TestConfiguration`**
- **범위**: 테스트 전용 Bean 설정
- **설명**: 테스트에만 필요한 특정 Bean을 구성하여 제공합니다.
- **사용 사례**:
   - 실제 애플리케이션에서는 필요하지 않은 Mock 또는 테스트 전용 Bean 설정.

---

## 참고

- **Spring Cloud OpenFeign 공식 문서**  
  [https://spring.io/projects/spring-cloud-openfeign](https://spring.io/projects/spring-cloud-openfeign)

- **WireMock 공식 문서**  
  [https://wiremock.org/](https://wiremock.org/)

이 문서를 통해 Feign Client 설정 및 테스트를 효율적으로 구현하고 관리할 수 있습니다. 🎉
