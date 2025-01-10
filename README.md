# ConsultEP

A aplicação tem o objetivo de gerenciar consultas de CEP e serviços associados, seguindo os princípios do SOLID. Contendo registros de logs, disponibilidade de serviços e geração de relatórios.

# Estrutura de pacotes

```java
com.luizromao.consultep
├── 📱 application
│   ├── usecase         # Casos de uso da aplicação
│   │   ├── 
│   │   └── 
│   └── dto            # Objetos de transferência de dados
│   │   ├── 
│   │   └── 
│	└── service        # Regras de negócio - orquestração dos use cases
│   │   ├──
│   │   └──
│	└── exeption       # Tratamento das exceções dos use cases
│   │   ├──
│   │   └──
│	└── validation     # Anotações responsáveis pela validação dos dados
│   │   ├──
│   │   └──
│	└── util           # Pacote de utilidades
│   │   ├──
│   │   └──
├── 🏛️ domain
│   ├── model          # Entidades e objetos de domínio
│   │   ├── 
│   │   └── 
│   ├── repository     # Interfaces dos repositórios
│   │   ├── 
│   │   └── 
├── ⚙️ infrastructure
│   └── config         # Configurações da aplicação
│       ├── 
│       └── 
└── 🖥️ presentation
    ├── controller     # Controladores da API
    │   ├── 
    │   └── 
    └── exception      # Tratamento de exceções
        ├── 
        └── 
```

# Clean Architecture

A **Clean Architecture** é baseada na separação de responsabilidades e dependências direcionais bem definidas. Analisando sua estrutura:

- **Camadas Definidas:**
    - `application`: Contém os **use cases** e serviços de orquestração, que são centrais para a regra de negócio. Nesta camada, os objetos (`DTO`) são transferidos de e para as camadas externas, respeitando a **dependência invertida**.
    - `domain`: Inclui os modelos e as interfaces dos repositórios. Essa camada é **independente** e representa o núcleo do sistema.
    - `infrastructure`: Trata implementações técnicas específicas, como repositórios e adaptadores para serviços externos, seguindo a ideia de que a infraestrutura é um detalhe que pode ser substituído sem impactar o restante do sistema.
    - `presentation`: Responsável pela interface com o usuário (APIs). Os controladores e o tratamento de exceções se concentram aqui, sem lógica de negócio.
- **Direção da Dependência:**
    - Se a camada de domínio não depende de outras camadas e todas as outras camadas dependem da `domain`, está de acordo com os princípios da Clean Architecture.

# SOLID

Os princípios de SOLID ajudam a criar um código mais modular, escalável e fácil de manter.

1. **Single Responsibility Principle (SRP):**
    - Cada pacote tem uma responsabilidade clara, como `usecase` para lógica de aplicação e `model` para entidades de domínio. Isso demonstra boa separação.
2. **Open/Closed Principle (OCP):**
    - O uso de `repository` como interfaces em `domain` permite que as implementações em `infrastructure` sejam substituíveis sem modificar a lógica de negócio. Isso segue o OCP.
3. **Liskov Substitution Principle (LSP):**
    - A prática de definir interfaces em `domain.repository` para repositórios sugere que classes que implementam essas interfaces devem ser intercambiáveis sem quebrar o sistema.
4. **Interface Segregation Principle (ISP):**
    - A estrutura incentiva interfaces menores e específicas (como repositórios individuais). Isso evita a criação de "interfaces gordas".
5. **Dependency Inversion Principle (DIP):**
    - Com a dependência sendo dirigida de fora para dentro (camadas externas dependendo da `domain` via interfaces), o DIP é respeitado.

# Docker

A aplicação utiliza Docker para configurar as imagens do Mockoon e MySQL. Para realizar a configuração local, siga estas etapas: 

- Acessar a pasta ./docker
- Executar o comando `docker-compose up --build`

# Fluxo da Aplicação

Relacionamento das Entidades

```mermaid
erDiagram
   BASE_ENTITY {
    string id PK
    datetime createdAt
    datetime updatedAt
  }

  LOG_CEP {
    string id PK
    string cep
    string requestType
    string jsonData
    string userCepId FK
  }

  SERVICE_AVAILABILITY {
    string id PK
    string cep
    boolean availabilityStatus
    string serviceCepId FK
    string createdByUserId FK
  }

  SERVICE_CEP {
    string id PK
    string serviceType
    string name
    string description
    string createdByUserId FK
  }

  USER_CEP {
    string id PK
    string name
    string email
  }

  BASE_ENTITY ||--o| LOG_CEP : has
  BASE_ENTITY ||--o| SERVICE_AVAILABILITY : has
  BASE_ENTITY ||--o| SERVICE_CEP : has
  BASE_ENTITY ||--o| USER_CEP : has
  USER_CEP ||--o| LOG_CEP : creates
  USER_CEP ||--o| SERVICE_CEP : creates
  SERVICE_CEP ||--o| SERVICE_AVAILABILITY : provides
  SERVICE_CEP ||--o| USER_CEP : created_by
  SERVICE_AVAILABILITY ||--o| USER_CEP : created_by

```

## 📋 User Cep

Este componente gerencia os usuários do sistema. Cada usuário tem nome, e-mail e pode acessar diferentes serviços de consulta de CEP.

- 🔑 Dados principais
    - `name` → Nome completo
    - `email` → Endereço de e-mail
- 🎯 Para que serve?
    - Guardar informações dos usuários
    - Registrar histórico de consultas

---

## 📝 Log Cep

Registra todo histórico de buscas de CEP, guardando quem pesquisou, quando e que tipo de informação foi solicitada.

- 🔑 Dados principais
    - `cep` → CEP pesquisado
    - `requestType` → Tipo da busca
    - `user` → Quem fez a consulta
    - `requestTime` → Quando aconteceu
    - jsonData → Informações da requisição em JSON
- 🎯 Para que serve?
    - Manter histórico completo
    - Facilitar consultas posteriores

---

## 🔍 Service Cep

Cuida dos diferentes serviços oferecidos em cada região, como saúde, educação e transporte. Controla quem pode acessar cada serviço.

- 🔑 Dados principais
    - `serviceType` → Categoria (ex: Transporte)
    - `name` → Nome do serviço
    - `description` → Detalhes
    - `serviceAvailabilities`→ Armazenar e gerenciar informações sobre os serviços nos CEPs. Permitindo verificar se um serviço está acessível em um determinado CEP
    - `createdBy` → Informar o usuário que criou o serviço
- 🎯 Para que serve?
    - Organizar serviços disponíveis
    - Controlar acesso
    - Gerenciar por região

---

## 📍 Service Availability

Mostra quais serviços funcionam em cada CEP, tornando fácil saber o que está disponível em cada região.

- 🔑 Dados principais
    - `cep` → Região atendida
    - `serviceCep` → Tipo de serviço
    - `availabilityStatus` → Disponível ou não
    - `createdBy` → Informar o usuário que criou o serviço
- 🎯 Para que serve?
    - Mostrar serviços por região
    - Informar disponibilidade

# Fluxo Operacional

## 1. Registro de Usuários

- Processo simples de cadastro com nome e e-mail
- Dados persistidos na tabela `user_cep`

## 2. Registro de Serviços

- Cadastro dos serviços que os ceps poderão ser associados.
- Dados persistidos na tabela `service_cep`

## 3. Registro de Serviços Disponíveis no CEP

- Associnar os serviços que irão conter nos ceps
- Dados persistidos na tabela `service_availability_cep`
- Possibilidade de resgatar informações completa do CEP através da api externa (Mockoon).

## 4. Sistema de Logs

- A cada ação na aplicação será registrado os logs.
- Dados persistidos na tabela `log_cep`

## 5. Gerar Relatórios

- Gerar Relatórios csv dos Serviços Disponíveis
- Gerar Relatórios csv dos Logs
