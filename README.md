# 📱 UnPlug
### MSA 기반 AI 스마트폰 디톡스 서비스

> 스마트폰 과의존 문제를 완화하고,  
> 사용자가 스스로 건강한 스마트폰 사용 습관을 형성하도록 돕는  
> **AI 기반 디지털 웰빙 서비스**

---

## 🔗 Project Overview

- **Project Name**: UnPlug  
- **Type**: Team Project (BridgeON)  
- **Topic**: AI-based Smartphone Detox Service  
- **Architecture**: Microservices Architecture (MSA)  
- **Competition**: K-PaaS 활용 공모전 – 서비스 개발 부문  

---

## 🎯 Service Goal

- 스마트폰 과의존 및 중독 문제 완화  
- 단순 사용 시간 제한이 아닌 **자기 통제 중심 디톡스 제공**  
- 사용자가 자신의 이용 패턴을 인식하고 조절할 수 있는 환경 구축  
- 장기적으로 건강한 디지털 웰빙 문화 확산

---

## 🧩 Key Features

- **AI Chatbot**
  - OpenAI API 기반
  - 사용자 사용 습관 및 설문 결과를 기반으로 맞춤형 디톡스 코칭 제공
- **Smartphone Usage Diagnosis**
  - 설문을 통한 스마트폰 사용 유형 분석
- **Detox Challenge**
  - To-do 기반 목표 설정 및 수행 관리
- **Detox Group**
  - 사용자 간 공동 목표 설정 및 참여

---

## 🏗 System Architecture (MSA)

- **Gateway Service**
  - 인증 및 요청 진입점
- **User Service**
  - 사용자 정보 관리
- **Chatbot Service**
  - AI 챗봇 및 OpenAI API 연동
- **Challenge Service**
  - 디톡스 챌린지 관리
- **Restrict Service**
  - 스마트폰 사용 제한 및 제어 로직

> 기능 단위로 서비스를 분리하고,  
> 각 서비스는 **독립적인 배포 및 확장**이 가능하도록 설계

---

## 🛠 Tech Stack

### Frontend
- React Native

### Backend (MSA)
- Spring Boot
- API Gateway
- OpenAI API (Chatbot)

### Database
- MySQL (Service-based DB separation)

### Cloud / Infra
- Naver Cloud Platform
- Ncloud Kubernetes Service (NKS)
- NGINX (Ingress / Reverse Proxy)
- Container Registry
- CI/CD Pipeline  
  - SourceCommit → SourceBuild → Container Registry

---

## 👤 My Role (Backend / Chatbot Service)

- Chatbot Service 설계 및 구현  
- OpenAI API 연동 및 AI 응답 흐름 설계  
- 사용자 상태 기반 디톡스 코칭 로직 구현  
- MSA 환경에서 독립적인 챗봇 서비스 운영  
- Gateway 인증 구조 이해 및 서비스 연동

---

## 🚀 Expected Effect

- 개인 맞춤형 디톡스 방향 제공
- 자기 통제 기반 스마트폰 사용 습관 형성
- AI 코칭을 통한 사용자 행동 변화 유도
- 챌린지를 통한 사회적 동기 부여

---

## 🔮 Future Work

- 디톡스 미션 인증 기능 고도화 (지도·센서 활용)
- 위치 기반 디톡스 모임 추천 (Map API)
- 소셜 로그인 (Naver / Kakao / Google)
- 커뮤니티 자동 필터링 및 모니터링 기능

---

## 📫 Contact

- **Email**: longvaca0213@gmail.com  
- **Team**: BridgeON
