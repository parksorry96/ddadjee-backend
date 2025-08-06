# 개발 작업 일지

## 🏗️ 프로젝트 초기 설정

### 프로젝트 개요
- **프로젝트명**: ddadjee (따제)
- **목적**: 취준생 자격증 관리 플랫폼
- **아키텍처**: Modular Monolith with Spring Modulith
- **기술 스택**: 
  - Backend: Spring Boot 3.5.4, Java 21
  - Database: PostgreSQL + Redis
  - Frontend: React 19 + TypeScript + Vite

### 완료된 작업 (2025-08-06)

#### 1. Common 모듈 구성

##### 1.1 Constants (상수)
- `SystemConstants`: 시스템 전역 상수
- `ValidationConstants`: 검증 관련 상수
- `RedisConstants`: Redis 캐시 키 및 TTL
- `SecurityConstants`: 보안 관련 상수
- `PaginationConstants`: 페이지네이션 기본값

##### 1.2 Enums (열거형)
- `Status`: 엔티티 상태 (ACTIVE, INACTIVE, PENDING 등)
- `ErrorLevel`: 에러 심각도 (CRITICAL, ERROR, WARNING 등)
- `UserRole`: 사용자 역할 (USER, PREMIUM_USER, ADMIN)
  - Permission 기반 세부 권한 관리

##### 1.3 Exception (예외 처리)
- `ErrorCode`: 표준화된 에러 코드 (MessageSource 연동)
- `BaseException`: 모든 커스텀 예외의 추상 클래스
- `BusinessException`: 비즈니스 로직 예외
- `ValidationException`: 검증 예외
- `ResourceNotFoundException`: 리소스 미발견 예외
- `AuthenticationException`: 인증 관련 예외
- `GlobalExceptionHandler`: 전역 예외 처리 (@RestControllerAdvice)
- `ErrorResponse`: 에러 응답 Record (표준화된 에러 응답 형식)

##### 1.4 Message Properties (메시지 관리)
- `MessageConfig`: MessageSource 설정
- `MessageUtils`: 메시지 조회 유틸리티
- `messages.properties`: 한글 메시지
- `messages_en.properties`: 영문 메시지

##### 1.5 Entity Base Classes (JPA 엔티티)
- `BaseEntity`: ID만 포함 (Long, IDENTITY 전략)
- `BaseTimeEntity`: 생성/수정 시간 포함
- `BaseAuditEntity`: 생성자/수정자 정보 포함
- JPA Auditing 설정 완료

##### 1.6 Common DTOs (공통 DTO)
- `ApiResponse<T>`: 표준 API 성공 응답 (Record)
- `PageRequest`: 페이지네이션 요청 (Record)
- `PageResponse<T>`: 페이지네이션 응답 (Record)
- `SliceResponse<T>`: 무한 스크롤용 응답 (Record)
- `PageResponseUtils`: 빈 페이지 생성 유틸리티

##### 1.7 Common Annotations (커스텀 어노테이션)
- `@CurrentUser`: 현재 로그인 사용자 주입
- `@ValidEnum`: Enum 유효성 검증
- `@ValidPassword`: 비밀번호 형식 검증
- 각 어노테이션별 Validator 구현 완료

### 설계 결정 사항

1. **ID 전략**: UUID 대신 Long + IDENTITY 선택
   - PostgreSQL과 최적 호환성
   - 성능 및 인덱싱 효율성
   - 디버깅 용이성

2. **Message 관리**: Properties 파일 기반
   - 하드코딩된 메시지 제거
   - 다국어 지원 준비
   - 중앙 집중식 메시지 관리

3. **DTO Strategy**: Record 사용
   - 불변성 보장
   - 보일러플레이트 코드 감소
   - Java 21 기능 활용

4. **Exception Handling**: 계층적 예외 구조
   - BaseException 상속 구조
   - ErrorCode enum으로 표준화
   - GlobalExceptionHandler로 일관된 처리

## 📋 다음 작업 계획

### Phase 1: User 도메인 구현
- [ ] User 엔티티 설계
- [ ] 회원가입/로그인 기능
- [ ] JWT 토큰 인증
- [ ] 소셜 로그인 (OAuth2)

### Phase 2: Certificate 도메인 구현
- [ ] Certificate 엔티티 설계
- [ ] 자격증 CRUD
- [ ] 자격증 만료 알림
- [ ] 자격증 검증

### Phase 3: Community 도메인 구현
- [ ] 게시판 기능
- [ ] 댓글 시스템
- [ ] 좋아요/북마크

### Phase 4: Notification 도메인 구현
- [ ] 알림 시스템
- [ ] 이메일 발송
- [ ] 푸시 알림

## 🔗 참고 문서
- [Spring Modulith 공식 문서](https://spring.io/projects/spring-modulith)
- [프로젝트 CLAUDE.md](../CLAUDE.md)