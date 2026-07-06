# UnPlug

### MSAベース AIスマートフォン・デトックスサービス

> スマートフォンの過度な利用を軽減し、
> ユーザー自身が健康的なスマートフォン利用習慣を身につけられるよう支援する
> **AIを活用したデジタルウェルビーイングサービス**

---

## Project Overview

* **Project Name**: UnPlug
* **Type**: Team Project (BridgeON)
* **Topic**: AI-based Smartphone Detox Service
* **Architecture**: Microservices Architecture (MSA)
* **Competition**: K-PaaS活用コンテスト（サービス開発部門）

---

## Service Goal

* スマートフォンの過度な利用・依存の軽減
* 利用時間を制限するだけではなく、**自己管理を重視したデトックス体験**を提供
* ユーザー自身が利用パターンを把握し、主体的に改善できる環境を構築
* 長期的に健全なデジタルウェルビーイング文化の普及を目指す

---

## Key Features

### AI Chatbot

* OpenAI APIを活用
* 利用習慣やアンケート結果をもとに、ユーザーごとのデトックスコーチングを提供

### Smartphone Usage Diagnosis

* アンケートによるスマートフォン利用タイプの分析

### Detox Challenge

* To-doベースの目標設定・進捗管理

### Detox Group

* ユーザー同士で共通目標を設定し、共同でチャレンジに参加

---

## System Architecture (MSA)

### Gateway Service

* 認証およびリクエストのエントリーポイント

### User Service

* ユーザー情報の管理

### Chatbot Service

* AIチャットボットおよびOpenAI APIとの連携

### Challenge Service

* デトックスチャレンジの管理

### Restrict Service

* スマートフォン利用制限・制御ロジック

> 機能ごとにサービスを分離し、
> **各サービスが独立してデプロイ・スケールできる構成**を採用しています。

---

## Tech Stack

### Frontend

* React Native

### Backend (MSA)

* Spring Boot
* API Gateway
* OpenAI API (Chatbot)

### Database

* MySQL（サービス単位でデータベースを分離）

### Cloud / Infrastructure

* Naver Cloud Platform
* Ncloud Kubernetes Service (NKS)
* NGINX（Ingress / Reverse Proxy）
* Container Registry
* CI/CD Pipeline

  * SourceCommit → SourceBuild → Container Registry

---

## My Role (Backend / Chatbot Service)

* Chatbot Serviceの設計・実装
* WebSocket（STOMP + SockJS）を用いたリアルタイムチャット機能の実装
* OpenAI APIとの連携およびAI応答フローの設計・実装
* ChatThread・ChatMessageエンティティの設計およびMySQLへのメッセージ保存機能の実装
* JWT認証を利用したGateway認証アーキテクチャとの連携
* OpenFeignを用いたサービス間通信の実装
* ユーザー状態に応じたデトックスコーチングロジックの実装
* MSA環境における独立したチャットボットサービスの開発・運用
* チームメンバーと連携し、API仕様の調整や機能設計に参加

---

## Expected Benefits

* ユーザーごとに最適化されたデトックスプランの提供
* 自己管理を促進するスマートフォン利用習慣の形成
* AIコーチングによる行動変容の支援
* チャレンジ機能を通じた継続的なモチベーションの向上

---

## Future Work

* 地図・センサーを活用したデトックスミッション認証機能の高度化
* 位置情報を活用したデトックスコミュニティのレコメンド
* ソーシャルログイン対応（Naver / Kakao / Google）
* コミュニティ投稿の自動フィルタリング・モニタリング機能

---

## Contact

* **Email**: [longvaca0213@gmail.com](mailto:longvaca0213@gmail.com)
* **Team**: BridgeON
