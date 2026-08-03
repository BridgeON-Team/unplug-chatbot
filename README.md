# UnPlug

## MSA 기반 AI 스마트폰 디톡스 서비스

> 스마트폰의 과도한 사용을 줄이고,  
> 사용자가 스스로 건강한 스마트폰 사용 습관을 형성할 수 있도록 지원하는  
> **AI 기반 디지털 웰빙 서비스**

---

## Project Overview

- **프로젝트명**: UnPlug
- **프로젝트 형태**: 팀 프로젝트
- **팀명**: BridgeON
- **주제**: AI 기반 스마트폰 디톡스 서비스
- **아키텍처**: Microservices Architecture
- **참가 대회**: K-PaaS 활용 공모전 서비스 개발 부문
- **담당 역할**: Chatbot Service 백엔드 개발

---

## Service Goal

- 스마트폰의 과도한 사용과 의존 완화
- 단순한 사용 시간 제한을 넘어, **사용자의 자기 관리를 중심으로 한 디톡스 경험 제공**
- 사용자가 자신의 스마트폰 이용 패턴을 파악하고 주도적으로 개선할 수 있는 환경 구축
- AI 코칭과 챌린지 기능을 통한 지속적인 행동 변화 지원
- 장기적으로 건강한 디지털 웰빙 문화 형성

---

## Key Features

### AI Chatbot

- OpenAI API를 활용한 AI 챗봇
- 사용자 메시지를 기반으로 한 실시간 자동 응답
- 이용 습관과 설문 결과를 반영한 맞춤형 디톡스 코칭
- 대화 스레드와 메시지 이력 저장 및 조회

### Smartphone Usage Diagnosis

- 설문을 통한 스마트폰 이용 유형 분석
- 사용자의 상태와 이용 패턴을 기반으로 한 디톡스 방향 제안

### Detox Challenge

- To-do 기반 디톡스 목표 설정
- 목표별 진행 상태 관리
- 사용자의 지속적인 참여를 위한 챌린지 기능

### Detox Group

- 사용자 간 공통 목표 설정
- 그룹 단위 디톡스 챌린지 참여
- 공동 목표를 통한 동기 부여와 지속적인 참여 지원

---

## System Architecture

UnPlug는 기능별 책임을 분리한 MSA 구조로 설계되었습니다.

### API Gateway

- 클라이언트 요청의 단일 진입점
- JWT 인증 처리
- 인증된 사용자 정보를 내부 서비스에 전달
- 공통 인증 로직 중앙화

### User Service

- 사용자 정보 관리
- 사용자 상태 및 프로필 정보 제공
- 다른 서비스의 사용자 정보 조회 요청 처리

### Chatbot Service

- AI 챗봇 대화 기능
- WebSocket 기반 실시간 메시징
- OpenAI API 연동
- 채팅방 및 메시지 저장
- 사용자별 대화 접근 권한 검증

### Challenge Service

- 디톡스 챌린지 생성 및 관리
- 사용자의 목표와 진행 상태 관리

### Restrict Service

- 스마트폰 사용 제한 기능
- 이용 시간 및 제한 정책 관리

> 각 기능을 독립적인 서비스로 분리하여  
> **서비스별 개발, 배포, 확장 및 장애 격리가 가능한 구조**를 지향했습니다.

---

## Request and Authentication Flow

1. 클라이언트가 JWT를 포함하여 API Gateway에 요청합니다.
2. Gateway에서 JWT를 검증합니다.
3. 검증된 사용자 정보를 요청 헤더에 포함합니다.
4. 각 마이크로서비스는 전달받은 사용자 정보를 기반으로 요청을 처리합니다.
5. Chatbot Service는 필요한 경우 OpenFeign을 통해 User Service와 통신합니다.

이 구조를 통해 인증 로직을 Gateway에 집중시키고,  
개별 서비스는 각자의 비즈니스 로직에 집중할 수 있도록 설계했습니다.

---

## Chat Message Flow

1. 사용자가 WebSocket 연결을 생성합니다.
2. STOMP 메시지를 통해 Chatbot Service로 메시지를 전송합니다.
3. 서버에서 채팅방과 사용자 접근 권한을 검증합니다.
4. 사용자 메시지를 MySQL에 저장합니다.
5. OpenAI API에 사용자 메시지와 대화 문맥을 전달합니다.
6. 생성된 AI 응답을 저장합니다.
7. STOMP 구독 경로를 통해 사용자에게 실시간으로 응답을 전달합니다.

---

## Tech Stack

### Frontend

- React Native

### Backend

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Cloud Gateway
- OpenFeign
- WebSocket
- STOMP
- SockJS
- OpenAI API

### Database / Cache

- MySQL
- Redis

### Cloud / Infrastructure

- Naver Cloud Platform
- Ncloud Kubernetes Service
- NGINX
- Container Registry
- Docker
- CI/CD Pipeline

### CI/CD

- SourceCommit
- SourceBuild
- Container Registry

---

## My Role

저는 UnPlug 프로젝트에서 **Chatbot Service 백엔드 개발**을 담당했습니다.

### Chatbot Service 설계

- MSA 환경에서 독립적으로 동작하는 Chatbot Service 설계
- 채팅방, 메시지, 사용자 간 관계를 고려한 데이터 모델 설계
- Controller, Service, Repository 계층 구성
- 다른 서비스와의 결합도를 낮추기 위한 DTO 기반 통신 구조 적용

### Real-time Messaging

- WebSocket 기반 실시간 채팅 기능 구현
- STOMP와 SockJS를 활용한 메시지 송수신 구조 구현
- 사용자별 채팅방 구독 경로 구성
- 실시간 사용자 메시지 및 AI 응답 전달

### OpenAI API Integration

- OpenAI API를 활용한 자동 응답 기능 구현
- 사용자 메시지와 대화 문맥을 기반으로 한 AI 응답 생성
- AI 응답 생성 과정과 메시지 저장 흐름 연결
- 사용자 상태를 반영한 디톡스 코칭 응답 구조 설계

### Message Persistence

- `ChatThread`, `ChatMessage` 엔티티 설계
- 사용자 메시지 및 AI 응답 MySQL 저장
- 채팅방별 메시지 내역 조회
- 메시지 순서와 생성 시간을 고려한 조회 기능 구현

### Authentication and Authorization

- Gateway에서 인증된 사용자 정보를 헤더로 전달받는 구조 적용
- `X-Auth-Username` 기반 사용자 식별
- 사용자별 채팅방 접근 권한 검증
- 채팅방 및 메시지 소유권 검증
- 다른 사용자의 대화 데이터에 접근하지 못하도록 인증 흐름 구성

### Service-to-Service Communication

- OpenFeign을 활용한 User Service 연동
- 사용자 정보 조회를 위한 서비스 간 API 통신
- 서비스 간 엔티티 직접 공유 대신 DTO 기반 통신 적용
- 서비스별 책임 분리를 고려한 의존성 최소화

### Collaboration

- 팀원과 API 명세 및 요청·응답 구조 협의
- Gateway, User Service 담당자와 인증 정보 전달 방식 조율
- 서비스 간 변경 사항을 공유하고 연동 오류 수정
- 기능 설계 및 데이터 흐름 논의 참여

---

## Technical Challenges

### 사용자 식별 방식 리팩터링

초기에는 서비스 내부에서 사용자를 `userId`로 식별했지만,  
Gateway에서 전달하는 인증 정보와 서비스 간 데이터 흐름을 일관되게 관리하기 위해  
`username` 기반 사용자 식별 구조로 리팩터링했습니다.

이 과정에서 다음 영역을 함께 수정했습니다.

- Gateway 인증 헤더
- WebSocket 연결 및 메시지 처리
- REST API 요청 처리
- 채팅방 소유권 검증
- 메시지 저장 및 조회
- User Service 연동
- 서비스 간 DTO 구조

작은 인증 정보 변경도 여러 서비스와 기능에 영향을 줄 수 있다는 점을 경험하며,  
MSA 환경에서는 사전 설계와 명확한 API 계약이 중요하다는 것을 배웠습니다.

### WebSocket 인증 정보 처리

일반적인 REST 요청과 달리 WebSocket은 연결 이후 지속적으로 통신하므로,  
사용자 인증 정보가 메시지 처리 과정에서도 유지되도록 설계해야 했습니다.

Gateway에서 전달된 사용자 정보와 WebSocket 메시지의 사용자 정보를 연결하고,  
각 메시지 처리 시 채팅방 소유권을 검증하도록 구현했습니다.

### 서비스 간 책임 분리

Chatbot Service가 사용자 데이터를 직접 관리하지 않도록 하고,  
필요한 사용자 정보는 User Service에서 조회하도록 구성했습니다.

이를 통해 서비스별 책임을 명확히 하고,  
특정 서비스의 데이터 모델이 다른 서비스에 직접 노출되지 않도록 설계했습니다.

---

## What I Learned

- MSA 환경에서의 서비스 책임 분리
- API Gateway 중심 인증 구조
- JWT 인증 정보의 서비스 간 전달 방식
- WebSocket 기반 실시간 메시징
- OpenAI API를 활용한 AI 응답 처리
- OpenFeign 기반 서비스 간 통신
- 사용자별 데이터 접근 권한과 소유권 검증
- 분산 환경에서 변경 사항이 미치는 영향
- API 명세와 팀 간 커뮤니케이션의 중요성
- 기능 구현뿐 아니라 전체 시스템 흐름을 고려하는 개발 방식

---

## Expected Benefits

- 사용자별 상태에 맞춘 디톡스 계획 제공
- 자기 관리를 유도하는 스마트폰 이용 습관 형성
- AI 코칭을 통한 행동 변화 지원
- 챌린지 기능을 통한 지속적인 동기 부여
- 그룹 활동을 통한 사용자 간 상호 참여
- 개인화된 디지털 웰빙 경험 제공

---

## Future Work

- 지도 및 센서를 활용한 디톡스 미션 인증 고도화
- 위치 정보를 활용한 디톡스 커뮤니티 추천
- 소셜 로그인 지원
  - Naver
  - Kakao
  - Google
- 커뮤니티 게시물 자동 필터링 및 모니터링
- AI 응답 품질 개선 및 개인화 고도화
- 대화 요약을 활용한 장기 사용자 상태 관리
- 메시지 비동기 처리 및 장애 복구 구조 강화
- 서비스 모니터링과 로그 추적 체계 개선

---

## Repository

- **Chatbot Service**  
  https://github.com/BridgeON-Team/unplug-chatbot

---

## Contact

- **Email**: [longvaca0213@gmail.com](mailto:longvaca0213@gmail.com)
- **GitHub**: https://github.com/tengo99
- **Team**: BridgeON

---

> 단순히 기능을 구현하는 데 그치지 않고,  
> 인증, 실시간 통신, 서비스 간 연동을 포함한 전체 시스템 흐름을 고려하며 개발했습니다.
