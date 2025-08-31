# GreenGenie - Intelligent Farming Assistant 🌱🤖

A microservices-based AI-powered farming assistant built with Spring Boot, featuring a custom LLM for agricultural insights, crop management, and farm operation optimization.

## 🚀 Overview

GreenGenie is a comprehensive agricultural intelligence platform built using microservices architecture.The system combines custom Large Language Models with modern Spring Boot microservices to provide farmers with personalized advice, real-time insights, and data-driven recommendations.

## ✨ Architecture Overview

### Microservices Design
- **AI Service**: Custom LLM integration and natural language processing
- **Farm Service**: Farm registration, crop management, and field operations  
- **Weather Service**: Weather data integration and farming recommendations
- **Market Service**: Market price tracking and selling recommendations
- **Notification Service**: SMS/Email alerts and farming reminders
- **Auth Service**: User authentication and authorization with JWT
- **API Gateway**: Single entry point with routing and security

### Communication Patterns
- **gRPC**: High-performance inter-service communication
- **Apache Kafka**: Event streaming for real-time data processing
- **REST APIs**: External client communication
- **WebSocket**: Real-time chat interface with AI

## 🛠️ Tech Stack

### Backend Microservices
- **Framework**: Spring Boot 3.x with Java 17+
- **Database**: PostgreSQL per service with pgvector extension
- **Message Broker**: Apache Kafka for event streaming
- **Service Communication**: gRPC with Protocol Buffers
- **Authentication**: Spring Security with JWT tokens
- **API Documentation**: OpenAPI 3.0 with Swagger UI
- **Caching**: Redis for response caching and sessions

### Frontend
- **Framework**: React.js 18+ with TypeScript
- **State Management**: Redux Toolkit
- **UI Library**: Tailwind CSS with agricultural theme
- **Real-time Communication**: WebSocket for AI chat
- **Maps**: Mapbox for field visualization
- **Charts**: Chart.js for analytics dashboards

### AI/ML Integration
- **LLM Hosting**: Ollama for local model serving
- **Vector Database**: pgvector for semantic search
- **Model Framework**: Hugging Face Transformers
- **Training Pipeline**: Python with PyTorch
- **Spring AI**: Integration framework for AI services

### DevOps & Infrastructure
- **Containerization**: Docker & Docker Compose
- **Service Discovery**: Spring Cloud Netflix Eureka (optional)
- **Monitoring**: Spring Boot Actuator + Micrometer
- **Database Migration**: Flyway/Liquibase
- **Testing**: JUnit 5, TestContainers for integration tests

## 📁 Project Structure

```
farm-ai-microservices/
├── ai-service/
│   ├── src/main/java/com/farmai/ai/
│   │   ├── controller/
│   │   │   └── AIController.java
│   │   ├── service/
│   │   │   ├── LLMService.java
│   │   │   └── EmbeddingService.java
│   │   ├── config/
│   │   │   ├── OllamaConfig.java
│   │   │   └── VectorStoreConfig.java
│   │   ├── dto/
│   │   ├── entity/
│   │   └── AiServiceApplication.java
│   ├── src/main/resources/
│   │   ├── application.properties
│   │   └── data.sql
│   ├── src/main/proto/
│   │   └── ai-service.proto
│   ├── Dockerfile
│   └── pom.xml
├── farm-service/
│   ├── src/main/java/com/farmai/farm/
│   │   ├── controller/
│   │   │   ├── FarmController.java
│   │   │   └── CropController.java
│   │   ├── service/
│   │   │   ├── FarmService.java
│   │   │   ├── CropService.java
│   │   │   └── FieldService.java
│   │   ├── repository/
│   │   ├── entity/
│   │   │   ├── Farm.java
│   │   │   ├── Crop.java
│   │   │   └── Field.java
│   │   ├── grpc/
│   │   │   └── FarmGrpcService.java
│   │   └── FarmServiceApplication.java
│   ├── src/main/proto/
│   │   └── farm-service.proto
│   ├── Dockerfile
│   └── pom.xml
├── weather-service/
│   ├── src/main/java/com/farmai/weather/
│   │   ├── controller/
│   │   ├── service/
│   │   │   ├── WeatherService.java
│   │   │   └── ForecastService.java
│   │   ├── client/
│   │   │   └── ExternalWeatherClient.java
│   │   ├── kafka/
│   │   │   └── WeatherEventProducer.java
│   │   └── WeatherServiceApplication.java
│   ├── Dockerfile
│   └── pom.xml
├── market-service/
│   ├── src/main/java/com/farmai/market/
│   │   ├── controller/
│   │   ├── service/
│   │   │   ├── MarketPriceService.java
│   │   │   └── PriceAnalysisService.java
│   │   ├── kafka/
│   │   │   └── MarketEventConsumer.java
│   │   └── MarketServiceApplication.java
│   ├── Dockerfile
│   └── pom.xml
├── notification-service/
│   ├── src/main/java/com/farmai/notification/
│   │   ├── service/
│   │   │   ├── EmailService.java
│   │   │   └── SMSService.java
│   │   ├── kafka/
│   │   │   └── NotificationEventConsumer.java
│   │   └── NotificationServiceApplication.java
│   ├── Dockerfile
│   └── pom.xml
├── auth-service/
│   ├── src/main/java/com/farmai/auth/
│   │   ├── controller/
│   │   │   └── AuthController.java
│   │   ├── service/
│   │   │   ├── AuthService.java
│   │   │   └── JwtService.java
│   │   ├── entity/
│   │   │   └── User.java
│   │   ├── config/
│   │   │   └── SecurityConfig.java
│   │   └── AuthServiceApplication.java
│   ├── src/main/resources/
│   │   ├── application.properties
│   │   └── data.sql
│   ├── Dockerfile
│   └── pom.xml
├── api-gateway/
│   ├── src/main/java/com/farmai/gateway/
│   │   ├── filter/
│   │   ├── config/
│   │   └── GatewayApplication.java
│   ├── src/main/resources/
│   │   └── application.yml
│   ├── Dockerfile
│   └── pom.xml
├── frontend/
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   │   ├── Chat/
│   │   │   │   ├── ChatInterface.tsx
│   │   │   │   └── MessageBubble.tsx
│   │   │   ├── Dashboard/
│   │   │   │   ├── FarmDashboard.tsx
│   │   │   │   └── WeatherWidget.tsx
│   │   │   ├── Farm/
│   │   │   │   ├── FarmManagement.tsx
│   │   │   │   └── CropTracker.tsx
│   │   │   └── Auth/
│   │   │       ├── Login.tsx
│   │   │       └── Register.tsx
│   │   ├── services/
│   │   │   ├── api.ts
│   │   │   ├── websocket.ts
│   │   │   └── auth.ts
│   │   ├── store/
│   │   │   ├── authSlice.ts
│   │   │   ├── farmSlice.ts
│   │   │   └── store.ts
│   │   ├── types/
│   │   ├── utils/
│   │   ├── App.tsx
│   │   └── index.tsx
│   ├── package.json
│   ├── tailwind.config.js
│   └── Dockerfile
├── proto/
│   ├── ai-service.proto
│   ├── farm-service.proto
│   ├── weather-service.proto
│   ├── market-service.proto
│   └── common.proto
├── ml-pipeline/
│   ├── data/
│   │   ├── raw/
│   │   ├── processed/
│   │   └── models/
│   ├── scripts/
│   │   ├── train_model.py
│   │   ├── process_data.py
│   │   └── deploy_model.py
│   ├── notebooks/
│   └── requirements.txt
├── docker-compose.yml
├── docker-compose.dev.yml
├── docker-compose.prod.yml
├── .env.example
├── README.md
└── docs/
    ├── api/
    ├── architecture/
    └── deployment/
```

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.8+
- Node.js 18+
- Docker & Docker Compose
- PostgreSQL 15+
- Redis 7+

### Environment Setup

1. **Clone the repository**
```bash
git clone https://github.com/yourusername/farm-ai-microservices.git
cd farm-ai-microservices
```

2. **Set up environment variables**
```bash
cp .env.example .env
# Edit .env with your configuration
```

3. **Start infrastructure services**
```bash
docker-compose up -d postgres redis kafka zookeeper
```

4. **Run all services (Development)**
```bash
# Start all microservices
docker-compose -f docker-compose.dev.yml up -d

# Or run individually for development
cd auth-service && mvn spring-boot:run
cd farm-service && mvn spring-boot:run
cd ai-service && mvn spring-boot:run
# ... other services
```

5. **Start the frontend**
```bash
cd frontend
npm install
npm start
```

## 🔧 Service Configuration

### Auth Service Environment Variables
```env
SPRING_DATASOURCE_URL=jdbc:postgresql://auth-service-db:5432/db
SPRING_DATASOURCE_USERNAME=admin_user
SPRING_DATASOURCE_PASSWORD=password
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_SQL_INIT_MODE=always

JWT_SECRET=your-jwt-secret-key
JWT_EXPIRATION=86400000
```

### Farm Service Environment Variables
```env
SPRING_DATASOURCE_URL=jdbc:postgresql://farm-service-db:5432/db
SPRING_DATASOURCE_USERNAME=admin_user
SPRING_DATASOURCE_PASSWORD=password
SPRING_JPA_HIBERNATE_DDL_AUTO=update

AI_SERVICE_GRPC_HOST=ai-service
AI_SERVICE_GRPC_PORT=9001
WEATHER_SERVICE_GRPC_HOST=weather-service
WEATHER_SERVICE_GRPC_PORT=9002

SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092
```

### AI Service Environment Variables
```env
SPRING_DATASOURCE_URL=jdbc:postgresql://ai-service-db:5432/db
SPRING_DATASOURCE_USERNAME=admin_user
SPRING_DATASOURCE_PASSWORD=password

OLLAMA_BASE_URL=http://localhost:11434
OLLAMA_MODEL=farm-llm-v1
VECTOR_STORE_URL=jdbc:postgresql://ai-service-db:5432/db

HUGGING_FACE_API_KEY=your-hf-api-key
SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092
```

## 📋 Service Dependencies (pom.xml)

### Common Dependencies for All Services
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.6.0</version>
    </dependency>
    
    <!-- gRPC Dependencies -->
    <dependency>
        <groupId>io.grpc</groupId>
        <artifactId>grpc-netty-shaded</artifactId>
        <version>1.69.0</version>
    </dependency>
    <dependency>
        <groupId>io.grpc</groupId>
        <artifactId>grpc-protobuf</artifactId>
        <version>1.69.0</version>
    </dependency>
    <dependency>
        <groupId>io.grpc</groupId>
        <artifactId>grpc-stub</artifactId>
        <version>1.69.0</version>
    </dependency>
    <dependency>
        <groupId>net.devh</groupId>
        <artifactId>grpc-spring-boot-starter</artifactId>
        <version>3.1.0.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>com.google.protobuf</groupId>
        <artifactId>protobuf-java</artifactId>
        <version>4.29.1</version>
    </dependency>
    
    <!-- Kafka Dependencies -->
    <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
        <version>3.3.0</version>
    </dependency>
</dependencies>
```

### Build Configuration (All Services)
```xml
<build>
    <extensions>
        <extension>
            <groupId>kr.motd.maven</groupId>
            <artifactId>os-maven-plugin</artifactId>
            <version>1.7.0</version>
        </extension>
    </extensions>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
        
        <!-- Protobuf Maven Plugin -->
        <plugin>
            <groupId>org.xolstice.maven.plugins</groupId>
            <artifactId>protobuf-maven-plugin</artifactId>
            <version>0.6.1</version>
            <configuration>
                <protocArtifact>com.google.protobuf:protoc:3.25.5:exe:${os.detected.classifier}</protocArtifact>
                <pluginId>grpc-java</pluginId>
                <pluginArtifact>io.grpc:protoc-gen-grpc-java:1.68.1:exe:${os.detected.classifier}</pluginArtifact>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>compile</goal>
                        <goal>compile-custom</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

### Auth Service Additional Dependencies
```xml
<!-- JWT & Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.6</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
```

### AI Service Additional Dependencies
```xml
<!-- Spring AI -->
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-ollama-spring-boot-starter</artifactId>
    <version>1.0.0-M4</version>
</dependency>
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-pgvector-store-spring-boot-starter</artifactId>
    <version>1.0.0-M4</version>
</dependency>

<!-- Vector Database -->
<dependency>
    <groupId>com.pgvector</groupId>
    <artifactId>pgvector</artifactId>
    <version>0.1.4</version>
</dependency>
```

## 🌐 API Gateway Configuration

```yaml
# api-gateway/src/main/resources/application.yml
server:
  port: 8080

spring:
  cloud:
    gateway:
      routes:
        - id: auth-service
          uri: http://auth-service:8001
          predicates:
            - Path=/api/auth/**
            
        - id: farm-service
          uri: http://farm-service:8002
          predicates:
            - Path=/api/farms/**
            
        - id: ai-service
          uri: http://ai-service:8003
          predicates:
            - Path=/api/ai/**
            
        - id: weather-service
          uri: http://weather-service:8004
          predicates:
            - Path=/api/weather/**
            
        - id: market-service
          uri: http://market-service:8005
          predicates:
            - Path=/api/market/**

      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins: "http://localhost:3000"
            allowedMethods:
              - GET
              - POST
              - PUT
              - DELETE
            allowedHeaders: "*"
```

## 🐋 Docker Configuration

### Main Docker Compose
```yaml
# docker-compose.yml
version: '3.8'

services:
  # Infrastructure
  postgres:
    image: pgvector/pgvector:pg16
    ports:
      - "5432:5432"
    environment:
      POSTGRES_DB: farmai
      POSTGRES_USER: admin_user
      POSTGRES_PASSWORD: password
    volumes:
      - postgres_data:/var/lib/postgresql/data

  redis:
    image: redis:7-alpine
    ports:
      - "6379:6379"

  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000

  kafka:
    image: confluentinc/cp-kafka:latest
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
      - "9094:9094"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_LISTENERS: PLAINTEXT://:9092,EXTERNAL://:9094
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,EXTERNAL://localhost:9094
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,EXTERNAL:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1

  ollama:
    image: ollama/ollama:latest
    ports:
      - "11434:11434"
    volumes:
      - ollama_data:/root/.ollama

  # Microservices
  auth-service:
    build: ./auth-service
    ports:
      - "8001:8001"
    depends_on:
      - postgres
      - redis
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/farmai
      - SPRING_DATASOURCE_USERNAME=admin_user
      - SPRING_DATASOURCE_PASSWORD=password
      - SPRING_DATA_REDIS_HOST=redis
      - SPRING_DATA_REDIS_PORT=6379

  farm-service:
    build: ./farm-service
    ports:
      - "8002:8002"
    depends_on:
      - postgres
      - kafka
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/farmai
      - SPRING_DATASOURCE_USERNAME=admin_user
      - SPRING_DATASOURCE_PASSWORD=password
      - SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092

  ai-service:
    build: ./ai-service
    ports:
      - "8003:8003"
    depends_on:
      - postgres
      - ollama
      - kafka
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/farmai
      - SPRING_DATASOURCE_USERNAME=admin_user
      - SPRING_DATASOURCE_PASSWORD=password
      - SPRING_AI_OLLAMA_BASE_URL=http://ollama:11434
      - SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092

  api-gateway:
    build: ./api-gateway
    ports:
      - "8080:8080"
    depends_on:
      - auth-service
      - farm-service
      - ai-service

  frontend:
    build: ./frontend
    ports:
      - "3000:3000"
    depends_on:
      - api-gateway
    environment:
      - REACT_APP_API_URL=http://localhost:8080

volumes:
  postgres_data:
  ollama_data:
```

## 🧪 Testing

### Unit Tests
```bash
# Run tests for a specific service
cd auth-service
mvn test

# Run tests for all services
for service in auth-service farm-service ai-service weather-service market-service; do
  cd $service && mvn test && cd ..
done
```

### Integration Tests with TestContainers
```java
@SpringBootTest
@Testcontainers
class FarmServiceIntegrationTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");
    
    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));
    
    @Test
    void shouldCreateFarm() {
        // Integration test logic
    }
}
```

## 🚀 Deployment

### Development
```bash
docker-compose -f docker-compose.dev.yml up -d
```

### Production
```bash
docker-compose -f docker-compose.prod.yml up -d
```

### Kubernetes (Optional)
```bash
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/configmaps/
kubectl apply -f k8s/services/
kubectl apply -f k8s/deployments/
```

## 📊 Monitoring & Health Checks

### Health Check Endpoints
- Auth Service: `http://localhost:8001/actuator/health`
- Farm Service: `http://localhost:8002/actuator/health`
- AI Service: `http://localhost:8003/actuator/health`
- API Gateway: `http://localhost:8080/actuator/health`

### Swagger UI
- Auth Service: `http://localhost:8001/swagger-ui.html`
- Farm Service: `http://localhost:8002/swagger-ui.html`
- AI Service: `http://localhost:8003/swagger-ui.html`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Chris Blakely for the microservices architecture inspiration
- Spring Boot and Spring AI communities
- Ollama team for local LLM hosting
- Agricultural research institutions for domain expertise

## 📞 Support

- 📧 Email: support@farmai.com
- 💬 Discord: [FarmAI Community](https://discord.gg/farmai)
- 📖 Documentation: [docs.farmai.com](https://docs.farmai.com)
- 🐛 Issues: [GitHub Issues](https://github.com/yourusername/farm-ai-microservices/issues)

---

Built with ❤️ for farmers worldwid
