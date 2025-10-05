# Green Genie
## Agricultural Supply Chain Platform - Enterprise Solution Architecture Document

---

## 📋 Executive Summary

### Project Overview
**Green Genie** is an enterprise-grade microservices-based system designed to digitally transform the agricultural supply chain from farm to market. This platform addresses critical inefficiencies in the $7 trillion global agricultural industry by providing end-to-end visibility, traceability, and automation across the entire value chain.

### Business Problem
The current agricultural supply chain faces several critical challenges:
- **Post-harvest losses**: 30-40% of produce is wasted due to poor tracking and coordination
- **Price volatility**: Farmers lack real-time market data for optimal pricing decisions
- **Quality inconsistency**: No standardized quality assessment across the supply chain
- **Logistics inefficiency**: Poor cold chain management leads to spoilage
- **Market access**: Small-scale farmers struggle to connect with buyers directly
- **Payment delays**: Manual processes cause 45-60 day payment cycles
- **Lack of traceability**: Food safety incidents cannot be traced to origin

### Solution Value Proposition
Our platform delivers:
- **30% reduction** in post-harvest losses through optimized logistics
- **25% increase** in farmer income through direct market access and fair pricing
- **Real-time traceability** from farm to consumer
- **AI-powered predictions** for yield, pricing, and demand forecasting
- **Automated quality grading** reducing human error by 85%
- **Cold chain monitoring** ensuring 99.5% compliance
- **Instant payment settlements** through integrated payment gateways

### Target Market
- **Primary**: Agricultural cooperatives, food processors, distributors
- **Secondary**: Individual farmers, wholesale buyers, retail chains
- **Geographic Focus**: Initially India, expanding to Southeast Asia and Africa
- **Market Size**: $450B addressable market in target regions

---

## 🎯 Business Goals & Objectives

### Strategic Goals
1. **Operational Excellence**
   - Reduce supply chain cycle time by 40%
   - Achieve 99.9% system uptime
   - Process 10,000+ transactions per day

2. **Market Expansion**
   - Onboard 5,000 farms in Year 1
   - Scale to 50,000 farms by Year 3
   - Expand to 3 countries within 18 months

3. **Revenue Generation**
   - Transaction fee model: 2-3% per marketplace order
   - SaaS subscriptions for premium features
   - Data analytics services for agribusinesses

4. **Sustainability Impact**
   - Reduce food waste by 30%
   - Enable carbon footprint tracking
   - Support 100,000 small-scale farmers

### Key Performance Indicators (KPIs)
| Metric | Current Baseline | Year 1 Target | Year 3 Target |
|--------|------------------|---------------|---------------|
| Active Farms | 0 | 5,000 | 50,000 |
| Monthly Transactions | 0 | 50,000 | 1,000,000 |
| Platform Uptime | N/A | 99.5% | 99.9% |
| Average Harvest-to-Market Time | 7 days | 4 days | 2 days |
| Farmer Satisfaction Score | N/A | 4.0/5 | 4.5/5 |
| Revenue (Annual) | $0 | $5M | $50M |

---

## 👥 Stakeholder Analysis

### Primary Stakeholders

#### 1. Farmers
**Needs**: 
- Easy farm registration and profile management
- Simple harvest recording with mobile app
- Real-time market prices
- Direct access to buyers
- Fast payment processing

**Pain Points**:
- Limited technical literacy
- Poor internet connectivity in rural areas
- Lack of market information
- Dependency on middlemen

**Success Criteria**:
- 20% increase in income
- Payments within 7 days
- Access to 100+ buyers

#### 2. Buyers (Wholesalers/Retailers)
**Needs**:
- Quality-assured produce
- Reliable supply
- Competitive pricing
- Traceability for compliance
- Flexible ordering

**Pain Points**:
- Inconsistent quality
- Supply unreliability
- Food safety compliance risks
- Lack of origin transparency

**Success Criteria**:
- 95% order fulfillment rate
- 15% cost reduction
- Complete traceability

#### 3. Logistics Providers
**Needs**:
- Optimized route planning
- Real-time shipment tracking
- Cold chain monitoring
- Proof of delivery

**Pain Points**:
- Manual coordination
- Spoilage liability
- Inefficient routing
- Paper-based documentation

**Success Criteria**:
- 25% reduction in fuel costs
- 40% faster deliveries
- 95% on-time delivery rate

#### 4. Platform Administrators
**Needs**:
- System monitoring dashboards
- User management
- Dispute resolution tools
- Analytics and reporting

**Success Criteria**:
- <2 hour issue resolution time
- Complete audit trails
- Real-time operational visibility

---

## 📊 Market Analysis

### Industry Trends
1. **Digital Transformation**: 78% of agribusinesses investing in digital solutions
2. **AI/ML Adoption**: $2.3B invested in AgTech AI in 2024
3. **Blockchain Traceability**: 45% growth in supply chain blockchain adoption
4. **IoT Sensors**: 60M+ agricultural IoT devices deployed globally
5. **Direct-to-Consumer**: 35% growth in farm-to-table models

### Competitive Landscape

| Competitor | Strengths | Weaknesses | Our Advantage |
|------------|-----------|------------|---------------|
| **Ninjacart** | Large buyer network | Limited farmer tools | Better AI predictions |
| **Agrostar** | Strong input supply | No marketplace | End-to-end supply chain |
| **DeHaat** | Rural reach | Basic tech | Advanced microservices |
| **Crofarm** | Quality focus | Limited geography | Multi-region scalability |

### Regulatory Environment
- **FSSAI Compliance**: Food safety standards tracking
- **APMC Regulations**: Market yard integration requirements
- **Data Privacy**: GDPR/local data protection compliance
- **Payment Regulations**: RBI guidelines for digital payments

---

## 🏗️ System Architecture

### Architecture Principles
1. **Microservices Architecture**: Independent, scalable services
2. **Event-Driven Design**: Asynchronous communication via Kafka
3. **Cloud-Native**: Containerized deployment with Docker/Kubernetes
4. **API-First**: RESTful APIs with OpenAPI documentation
5. **Security by Design**: Zero-trust architecture, JWT authentication
6. **Data Sovereignty**: Regional data storage compliance

### High-Level Architecture Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                        Load Balancer / API Gateway              │
└─────────────────────────────────────────────────────────────────┘
                                  │
        ┌─────────────────────────┼─────────────────────────┐
        │                         │                         │
┌───────▼────────┐       ┌───────▼────────┐       ┌───────▼────────┐
│  Auth Service  │       │  Farm Service  │       │ Harvest Service│
│   (Port 8085)  │       │   (Port 8081)  │       │   (Port 8082)  │
│                │       │                │       │                │
│  JWT + Spring  │       │  REST + gRPC   │◄──────┤  REST + gRPC   │
│   Security     │       │  (Server)      │ gRPC  │  (Client)      │
└────────┬───────┘       └────────┬───────┘       └────────┬───────┘
         │                        │                        │
         │                        │                        │
    ┌────▼────┐              ┌────▼────┐              ┌────▼────┐
    │Auth DB  │              │Farm DB  │              │Harvest  │
    │(PG)     │              │(PG)     │              │DB (PG)  │
    └─────────┘              └─────────┘              └─────────┘
                                  │                        │
                                  ▼                        ▼
                        ┌─────────────────────────────────────┐
                        │         Apache Kafka Cluster        │
                        │  Topics: farm-events, harvest-      │
                        │  events, logistics-events,          │
                        │  marketplace-events                 │
                        └─────────────────────────────────────┘
                                  │
        ┌─────────────────────────┼─────────────────────────┐
        │                         │                         │
┌───────▼────────┐       ┌───────▼────────┐       ┌───────▼────────┐
│   Logistics    │       │  Marketplace   │       │ Notification   │
│    Service     │       │    Service     │       │    Service     │
│  (Port 8083)   │       │  (Port 8084)   │       │  (Port 8086)   │
│                │       │                │       │                │
│  Kafka Consumer│       │ Kafka Consumer │       │ Kafka Consumer │
└────────┬───────┘       └────────┬───────┘       └────────────────┘
         │                        │
    ┌────▼────┐              ┌────▼────┐
    │Logistics│              │Market   │
    │DB (PG)  │              │DB (PG)  │
    └─────────┘              └─────────┘

                        ┌─────────────────────┐
                        │    AI Service       │
                        │   (Port 8087)       │
                        │                     │
                        │ - Yield Prediction  │
                        │ - Price Forecasting │
                        │ - Quality Detection │
                        │ - Demand Analysis   │
                        └─────────────────────┘

Legend:
PG = PostgreSQL Database
──► = Synchronous Communication (REST/gRPC)
- -> = Asynchronous Communication (Kafka)
```

---

## 🎨 Microservices Design

### Service Catalog

#### 1. Farm Service (Core Domain Service)
**Responsibility**: Manage farm master data

**Port**: 8081 (REST), 9091 (gRPC)

**Database**: PostgreSQL (`farmdb`)

**Key Entities**:
- Farm (id, farmerId, name, location, sizeInAcres, cropTypes, soilType, status)

**API Endpoints**:
```
POST   /api/v1/farms              - Register new farm
GET    /api/v1/farms/{id}         - Get farm details
PUT    /api/v1/farms/{id}         - Update farm
GET    /api/v1/farms/farmer/{id}  - Get all farms for a farmer
GET    /api/v1/farms/search       - Search farms by location/crop
```

**gRPC Services**:
```protobuf
service FarmService {
    rpc GetFarm(GetFarmRequest) returns (GetFarmResponse);
    rpc GetFarmsByCropType(GetFarmsByCropTypeRequest) returns (GetFarmsResponse);
}
```

**Events Produced**:
- `FarmRegistered` → farm-events topic
- `FarmStatusChanged` → farm-events topic
- `HarvestSeasonStarted` → farm-events topic

**Non-Functional Requirements**:
- Response time: <200ms (P95)
- Availability: 99.9%
- Concurrent users: 10,000
- Data retention: 7 years

---

#### 2. Harvest Service (Business Logic Service)
**Responsibility**: Track harvests, quality grading, pricing

**Port**: 8082

**Database**: PostgreSQL (`harvestdb`)

**Dependencies**: 
- gRPC client to farm-service
- REST client to ai-service (quality detection)

**Key Entities**:
- Harvest (id, farmId, cropType, quantityInKg, qualityGrade, harvestDate, pricePerKg, totalValue, status)

**API Endpoints**:
```
POST   /api/v1/harvests           - Record new harvest
GET    /api/v1/harvests/{id}      - Get harvest details
PUT    /api/v1/harvests/{id}      - Update harvest
GET    /api/v1/harvests/farm/{id} - Get all harvests for a farm
POST   /api/v1/harvests/{id}/grade - Submit quality grading
GET    /api/v1/harvests/available - List available inventory
```

**Business Rules**:
1. Quality grading: A (90-100%), B (70-89%), C (<70%)
2. Price calculation: `basePrice * qualityMultiplier * seasonalFactor`
3. Auto-listing on marketplace after quality assessment
4. Expiry tracking for perishables

**Events Produced**:
- `HarvestRecorded` → harvest-events topic
- `QualityAssessed` → harvest-events topic
- `HarvestListed` → marketplace-events topic

**Events Consumed**:
- `OrderPlaced` → Update harvest status to SOLD

**Non-Functional Requirements**:
- Response time: <300ms (P95)
- Data consistency: Strong consistency with farm-service
- Transaction volume: 50,000/day
- Image processing: <5s for quality detection

---

#### 3. Logistics Service (Support Service)
**Responsibility**: Shipment tracking, route optimization, cold chain monitoring

**Port**: 8083

**Database**: PostgreSQL (`logisticsdb`)

**Key Entities**:
- Shipment (id, harvestId, vehicleNumber, originLocation, destinationLocation, status, currentGPSLocation, currentTemperature, estimatedDelivery)

**API Endpoints**:
```
POST   /api/v1/shipments          - Create shipment
GET    /api/v1/shipments/{id}     - Get shipment details
PUT    /api/v1/shipments/{id}/location - Update GPS location
PUT    /api/v1/shipments/{id}/temperature - Update temperature
POST   /api/v1/shipments/{id}/complete - Mark delivered
GET    /api/v1/shipments/track/{id} - Public tracking link
```

**Business Rules**:
1. Cold chain alert if temperature exceeds threshold (2-8°C for vegetables)
2. ETA calculation based on distance and traffic data
3. Proof of delivery with digital signature
4. Automatic invoice generation on delivery

**Events Produced**:
- `ShipmentDispatched` → logistics-events topic
- `DeliveryCompleted` → logistics-events topic
- `TemperatureAlert` → logistics-events topic
- `DelayAlert` → logistics-events topic

**Events Consumed**:
- `OrderConfirmed` → Create shipment automatically

**Non-Functional Requirements**:
- GPS update frequency: Every 5 minutes
- Temperature monitoring: Every 2 minutes
- Route optimization: <10s calculation time
- 24/7 monitoring capability

---

#### 4. Marketplace Service (Business Service)
**Responsibility**: B2B trading platform, order management

**Port**: 8084

**Database**: PostgreSQL (`marketplacedb`)

**Key Entities**:
- Order (id, buyerId, harvestId, quantityInKg, pricePerKg, totalAmount, status, paymentStatus, deliveryAddress)
- Bid (id, harvestId, buyerId, bidAmount, status) - for auction feature

**API Endpoints**:
```
GET    /api/v1/marketplace/listings - Browse available harvests
POST   /api/v1/orders              - Place order
GET    /api/v1/orders/{id}         - Get order details
PUT    /api/v1/orders/{id}/cancel  - Cancel order
GET    /api/v1/orders/buyer/{id}   - Get buyer's orders
GET    /api/v1/orders/seller/{id}  - Get seller's orders
POST   /api/v1/orders/{id}/payment - Process payment
```

**Business Rules**:
1. Minimum order quantity validation
2. Price negotiation window (24 hours)
3. Automatic cancellation if payment not received in 48 hours
4. Buyer rating system (1-5 stars)
5. Escrow payment model

**Events Produced**:
- `OrderPlaced` → marketplace-events topic
- `PaymentReceived` → marketplace-events topic
- `OrderConfirmed` → marketplace-events topic
- `OrderCancelled` → marketplace-events topic

**Events Consumed**:
- `HarvestListed` → Update marketplace catalog
- `DeliveryCompleted` → Trigger payment release

**Non-Functional Requirements**:
- Search response time: <100ms
- Payment processing: <3s
- Order throughput: 1,000 orders/minute
- Payment gateway uptime: 99.99%

---

#### 5. Auth Service (Platform Service)
**Responsibility**: Authentication, authorization, user management

**Port**: 8085

**Database**: PostgreSQL (`authdb`)

**Key Entities**:
- User (id, email, password, role, phoneNumber, status, lastLogin)

**Roles**:
- `FARMER`: Can register farms, record harvests
- `BUYER`: Can browse marketplace, place orders
- `LOGISTICS_PROVIDER`: Can manage shipments
- `ADMIN`: Full system access
- `SUPPORT`: Read-only access for customer support

**API Endpoints**:
```
POST   /api/v1/auth/register      - User registration
POST   /api/v1/auth/login         - Login (returns JWT)
POST   /api/v1/auth/refresh       - Refresh JWT token
POST   /api/v1/auth/logout        - Logout
GET    /api/v1/auth/me            - Get current user
PUT    /api/v1/auth/password      - Change password
POST   /api/v1/auth/forgot        - Forgot password flow
```

**Security Features**:
- JWT tokens with 1-hour expiry
- Refresh tokens with 7-day expiry
- Bcrypt password hashing (cost factor 12)
- Rate limiting: 5 failed attempts = 15-minute lockout
- 2FA support (SMS OTP)

**Non-Functional Requirements**:
- Authentication latency: <100ms
- Token generation: <50ms
- Horizontal scalability: Stateless design
- Security compliance: OWASP Top 10

---

#### 6. Notification Service (Event Processor)
**Responsibility**: Multi-channel notifications (email, SMS, push)

**Port**: 8086

**Database**: None (stateless) or optional PostgreSQL for notification history

**Notification Channels**:
1. **Email**: Transactional emails via SendGrid/AWS SES
2. **SMS**: Via Twilio/AWS SNS
3. **Push Notifications**: Via Firebase Cloud Messaging
4. **In-App Notifications**: Via WebSocket

**Events Consumed**:
```
harvest-events:
  - HarvestRecorded → Notify farmer: "Harvest recorded successfully"
  - QualityAssessed → Notify farmer: "Quality grade: A, Listed on marketplace"

marketplace-events:
  - OrderPlaced → Notify seller: "New order received"
  - OrderPlaced → Notify buyer: "Order confirmed"
  - PaymentReceived → Notify seller: "Payment received for order #123"

logistics-events:
  - ShipmentDispatched → Notify buyer: "Your order is on the way"
  - DeliveryCompleted → Notify all: "Order delivered successfully"
  - TemperatureAlert → Notify logistics: "URGENT: Cold chain breach"
```

**Notification Templates**:
- Configurable templates with variable substitution
- Multi-language support (English, Hindi, Tamil, Telugu)
- Unsubscribe management

**Non-Functional Requirements**:
- Delivery latency: <5s for critical alerts
- SMS delivery rate: >95%
- Email delivery rate: >98%
- Throughput: 10,000 notifications/minute

---

#### 7. AI Service (Intelligence Layer)
**Responsibility**: Machine learning models, predictions, analytics

**Port**: 8087

**Technology Stack**: Python (FastAPI) or Spring Boot with DL4J

**AI Capabilities**:

##### 7.1 Yield Prediction Model
- **Input**: Farm location, soil type, crop type, weather data, historical yields
- **Output**: Expected yield in kg, confidence interval
- **Algorithm**: Random Forest / Gradient Boosting
- **Accuracy Target**: 85% within 10% error margin

##### 7.2 Price Forecasting Model
- **Input**: Crop type, quality grade, season, market trends
- **Output**: Recommended price per kg, 7-day price forecast
- **Algorithm**: LSTM Time Series model
- **Update Frequency**: Daily retraining

##### 7.3 Quality Detection (Computer Vision)
- **Input**: Images of produce
- **Output**: Quality grade (A/B/C), defect detection
- **Algorithm**: CNN (ResNet50 transfer learning)
- **Accuracy Target**: 90% agreement with human graders

##### 7.4 Demand Forecasting
- **Input**: Historical orders, seasonal patterns, market events
- **Output**: Expected demand by crop type and region
- **Algorithm**: Prophet (time series) + XGBoost
- **Horizon**: 30-day forecast

##### 7.5 Anomaly Detection
- **Input**: Transaction patterns, price data
- **Output**: Fraud alerts, price manipulation detection
- **Algorithm**: Isolation Forest
- **Real-time**: <1s detection latency

**API Endpoints**:
```
POST   /ai/v1/predict-yield       - Yield prediction
POST   /ai/v1/predict-price       - Price forecasting
POST   /ai/v1/detect-quality      - Image-based quality grading
POST   /ai/v1/forecast-demand     - Demand forecasting
POST   /ai/v1/analyze-anomaly     - Fraud detection
GET    /ai/v1/models/status       - Model health check
```

**Model Performance Monitoring**:
- A/B testing for model improvements
- Drift detection and auto-retraining
- Explainable AI (SHAP values) for predictions

**Non-Functional Requirements**:
- Inference latency: <500ms
- Model accuracy: >85% for critical models
- Uptime: 99.5%
- GPU support for image processing

---

## 📡 Integration Architecture

### Event-Driven Communication (Kafka)

**Kafka Cluster Configuration**:
- 3 Brokers (minimum for production)
- Replication factor: 3
- Min in-sync replicas: 2
- Retention: 7 days (configurable per topic)

**Topic Design**:

```
farm-events (3 partitions)
├── FarmRegistered
├── FarmStatusChanged
└── HarvestSeasonStarted

harvest-events (5 partitions)
├── HarvestRecorded
├── QualityAssessed
└── HarvestListed

logistics-events (3 partitions)
├── ShipmentDispatched
├── DeliveryCompleted
├── TemperatureAlert
└── DelayAlert

marketplace-events (5 partitions)
├── OrderPlaced
├── OrderConfirmed
├── PaymentReceived
└── OrderCancelled
```

**Event Schema (Example)**:
```json
{
  "eventId": "uuid",
  "eventType": "HarvestRecorded",
  "timestamp": "2025-10-05T10:30:00Z",
  "version": "1.0",
  "payload": {
    "harvestId": "uuid",
    "farmId": "uuid",
    "cropType": "wheat",
    "quantityInKg": 5000,
    "qualityGrade": "A",
    "pricePerKg": 25.50
  },
  "metadata": {
    "source": "harvest-service",
    "userId": "uuid",
    "correlationId": "uuid"
  }
}
```

**Consumer Groups**:
- `logistics-consumer-group`: Processes harvest events for shipment creation
- `notification-consumer-group`: Sends notifications for all events
- `analytics-consumer-group`: Streams data to data warehouse

---

### Synchronous Communication (gRPC)

**Why gRPC for harvest-service → farm-service?**
- Low latency requirement (<50ms)
- Strongly typed contracts (protobuf)
- Efficient binary serialization
- Bi-directional streaming (future use)

**Proto Definition** (`proto/farm.proto`):
```protobuf
syntax = "proto3";

package com.agrichain.proto;

service FarmService {
  rpc GetFarm(GetFarmRequest) returns (GetFarmResponse);
  rpc GetFarmsByCropType(GetFarmsByCropTypeRequest) returns (GetFarmsResponse);
  rpc ValidateFarmOwnership(ValidateFarmOwnershipRequest) returns (ValidateFarmOwnershipResponse);
}

message GetFarmRequest {
  string farm_id = 1;
}

message GetFarmResponse {
  FarmDetails farm = 1;
  string error = 2;
}

message FarmDetails {
  string id = 1;
  string farmer_id = 2;
  string name = 3;
  string location = 4;
  double size_in_acres = 5;
  repeated string crop_types = 6;
  string status = 7;
  string soil_type = 8;
}

message GetFarmsByCropTypeRequest {
  string crop_type = 1;
  string region = 2;
}

message GetFarmsResponse {
  repeated FarmDetails farms = 1;
}

message ValidateFarmOwnershipRequest {
  string farm_id = 1;
  string farmer_id = 2;
}

message ValidateFarmOwnershipResponse {
  bool is_valid = 1;
  string message = 2;
}
```

**Service Discovery**:
- Docker Compose: Service name resolution (`farm-service:9091`)
- Kubernetes: Service DNS (`farm-service.default.svc.cluster.local:9091`)
- Production: Consul/Eureka for service registry

---

### API Gateway Pattern

**Responsibilities**:
- Single entry point for all clients
- Request routing to microservices
- JWT validation
- Rate limiting
- API versioning
- Response caching
- Request/response logging

**Technology Options**:
- Spring Cloud Gateway
- Kong API Gateway
- AWS API Gateway

**Routing Rules**:
```
/api/v1/farms/**      → farm-service:8081
/api/v1/harvests/**   → harvest-service:8082
/api/v1/shipments/**  → logistics-service:8083
/api/v1/marketplace/** → marketplace-service:8084
/api/v1/orders/**     → marketplace-service:8084
/api/v1/auth/**       → auth-service:8085
/ai/v1/**             → ai-service:8087
```

**Rate Limiting**:
- Authenticated users: 1000 requests/hour
- Anonymous users: 100 requests/hour
- Admin users: Unlimited

---

## 👤 User Stories & Requirements

### Epic 1: Farmer Onboarding & Management

#### US-001: Farmer Registration
**As a** farmer  
**I want to** register on the platform with my mobile number  
**So that** I can start using the system to manage my farm

**Acceptance Criteria**:
- User provides: name, mobile number, email (optional), password
- Mobile number verification via OTP
- Email verification via link (if provided)
- Default role assigned: FARMER
- Welcome email/SMS sent
- User redirected to profile completion

**Technical Notes**:
- Service: auth-service
- API: `POST /api/v1/auth/register`
- Validation: Unique mobile number, strong password (min 8 chars)

---

#### US-002: Farm Profile Creation
**As a** registered farmer  
**I want to** create a profile for my farm  
**So that** I can track my agricultural activities

**Acceptance Criteria**:
- User provides: farm name, location (GPS or address), size in acres, crop types, soil type
- System validates location coordinates
- Farm assigned unique ID
- Farm status set to ACTIVE
- Event published: `FarmRegistered`
- Success confirmation displayed

**Technical Notes**:
- Service: farm-service
- API: `POST /api/v1/farms`
- Validation: Size > 0, valid coordinates
- Database: Stores in `farms` table

---

#### US-003: View Farm Dashboard
**As a** farmer  
**I want to** view a dashboard of all my farms  
**So that** I can see an overview of my agricultural operations

**Acceptance Criteria**:
- List displays: farm name, location, size, active crops, total harvests
- Filters: by crop type, season
- Search by farm name
- Pagination (20 per page)
- Click to view farm details

**Technical Notes**:
- Service: farm-service
- API: `GET /api/v1/farms/farmer/{farmerId}`
- Response time: <200ms

---

### Epic 2: Harvest Management

#### US-004: Record Harvest
**As a** farmer  
**I want to** record a new harvest from my farm  
**So that** I can list it for sale

**Acceptance Criteria**:
- User selects farm from dropdown
- Provides: crop type, quantity (kg), harvest date
- Optional: upload images for quality assessment
- System validates farm ownership via gRPC
- System calculates base price from market data
- Harvest saved with status PENDING_QUALITY_CHECK
- Event published: `HarvestRecorded`
- Notification sent to farmer

**Technical Notes**:
- Service: harvest-service
- API: `POST /api/v1/harvests`
- gRPC call: `ValidateFarmOwnership(farmId, farmerId)`
- Image upload: S3/CloudStorage

---

#### US-005: AI Quality Assessment
**As a** farmer  
**I want** the system to automatically assess my harvest quality  
**So that** I get a fair grade and price

**Acceptance Criteria**:
- System analyzes uploaded images via AI service
- Quality grade assigned: A (premium), B (standard), C (basic)
- Defects detected and reported (e.g., pest damage, discoloration)
- Price adjusted based on quality: A (+20%), B (base), C (-15%)
- Quality report generated
- Event published: `QualityAssessed`
- Farmer receives quality report via notification

**Technical Notes**:
- Service: harvest-service (caller), ai-service (processor)
- API: `POST /ai/v1/detect-quality`
- Processing time: <5s
- Model: CNN (ResNet50)

---

#### US-006: View Harvest History
**As a** farmer  
**I want to** view all my past harvests  
**So that** I can track my production over time

**Acceptance Criteria**:
- List displays: crop type, quantity, date, quality grade, price, status
- Filters: by crop type, date range, quality grade
- Export to CSV
- Visual charts: yield trends, price trends
- Total revenue calculation

**Technical Notes**:
- Service: harvest-service
- API: `GET /api/v1/harvests/farm/{farmId}`
- Caching: Redis (5 minutes)

---

### Epic 3: Marketplace & Trading

#### US-007: Browse Marketplace
**As a** buyer  
**I want to** browse available harvests  
**So that** I can find produce to purchase

**Acceptance Criteria**:
- List displays: crop type, quantity available, quality grade, price per kg, farm location
- Filters: crop type, quality grade, price range, location radius
- Search by keyword
- Sort: by price (low/high), quality, distance
- Pagination
- Click to view harvest details

**Technical Notes**:
- Service: marketplace-service
- API: `GET /api/v1/marketplace/listings`
- Search: Elasticsearch (optional)
- Response time: <100ms

---

#### US-008: Place Order
**As a** buyer  
**I want to** place an order for a harvest  
**So that** I can purchase produce

**Acceptance Criteria**:
-
