# UnPlug
### MSA ベース AI スマートフォン・デジタルデトックスサービス

> スマートフォンの過度な依存を緩和し、  
> ユーザーが自ら健全なスマートフォン利用習慣を形成できるよう支援する  
> **AI ベースのデジタルウェルビーイングサービス**

---

## Project Overview

- **Project Name**: UnPlug  
- **Type**: チームプロジェクト（BridgeON）  
- **Topic**: AI ベース・スマートフォンデトックスサービス  
- **Architecture**: マイクロサービスアーキテクチャ（MSA）  
- **Competition**: K-PaaS 活用コンテスト – サービス開発部門  

---

## Service Goal

- スマートフォンの過度な利用・依存問題の緩和  
- 単なる使用時間制限ではなく、**自己制御を重視したデトックス体験の提供**  
- ユーザーが自身の利用パターンを認識し、調整できる環境の構築  
- 長期的には健全なデジタルウェルビーイング文化の定着を目指す  

---

## Key Features

- **AI Chatbot**
  - OpenAI API ベース  
  - ユーザーの利用習慣やアンケート結果に基づいた、個別最適化デトックスコーチング
- **Smartphone Usage Diagnosis**
  - アンケートによるスマートフォン利用タイプの分析
- **Detox Challenge**
  - To-do ベースの目標設定および実行管理
- **Detox Group**
  - ユーザー同士での共同目標設定・参加機能

---

## System Architecture (MSA)

- **Gateway Service**
  - 認証およびリクエストのエントリーポイント
- **User Service**
  - ユーザー情報管理
- **Chatbot Service**
  - AI チャットボットおよび OpenAI API 連携
- **Challenge Service**
  - デトックスチャレンジ管理
- **Restrict Service**
  - スマートフォン利用制限・制御ロジック

> 機能単位でサービスを分離し、  
> 各サービスは **独立したデプロイおよびスケーリング** が可能な構成で設計

---

## Tech Stack

### Frontend
- React Native

### Backend (MSA)
- Spring Boot  
- API Gateway  
- OpenAI API（Chatbot）

### Database
- MySQL（サービス単位での DB 分離）

### Cloud / Infrastructure
- Naver Cloud Platform  
- Ncloud Kubernetes Service（NKS）  
- NGINX（Ingress / Reverse Proxy）  
- Container Registry  
- CI/CD Pipeline  
  - SourceCommit → SourceBuild → Container Registry  

---

## My Role（Backend / Chatbot Service）

- Chatbot Service の設計・実装  
- OpenAI API 連携および AI 応答フロー設計  
- ユーザー状態に応じたデトックスコーチングロジック実装  
- MSA 環境における独立したチャットボットサービス運用  
- Gateway 認証構成の理解およびサービス間連携対応  

---

## Expected Effect

- 個人に最適化されたデトックス方針の提示  
- 自己制御に基づくスマートフォン利用習慣の形成  
- AI コーチングによるユーザー行動変容の促進  
- チャレンジ機能を通じた社会的モチベーションの創出  

---

## Future Work

- デトックスミッション認証機能の高度化（位置情報・センサー活用）  
- 位置情報ベースのデトックスグループ推薦（Map API）  
- ソーシャルログイン対応（Naver / Kakao / Google）  
- コミュニティ自動フィルタリング・モニタリング機能  

---

## Contact

- **Email**: longvaca0213@gmail.com  
- **Team**: BridgeON
