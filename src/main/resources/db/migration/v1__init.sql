CREATE TABLE user_cep
(
    id           VARCHAR(255) PRIMARY KEY,
    `created_at` datetime DEFAULT NULL,
    `updated_at` datetime DEFAULT NULL,
    name         VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE log_cep
(
    id           VARCHAR(255) PRIMARY KEY,
    `created_at` datetime DEFAULT NULL,
    `updated_at` datetime DEFAULT NULL,
    user_cep_id  VARCHAR(255) NOT NULL,
    cep          CHAR(8)      NOT NULL,
    request_type VARCHAR(50)  NOT NULL,
    FOREIGN KEY (user_cep_id) REFERENCES user_cep (id) ON DELETE CASCADE
);

CREATE TABLE service_cep
(
    id           VARCHAR(255) PRIMARY KEY,
    `created_at` datetime DEFAULT NULL,
    `updated_at` datetime DEFAULT NULL,
    service_type VARCHAR(50)  NOT NULL,
    name         VARCHAR(100) NOT NULL,
    description  TEXT
);

CREATE TABLE service_availability
(
    id                  VARCHAR(255) PRIMARY KEY,
    `created_at`        datetime DEFAULT NULL,
    `updated_at`        datetime DEFAULT NULL,
    service_cep_id      VARCHAR(255) NOT NULL,
    cep                 CHAR(8)      NOT NULL,
    availability_status BOOLEAN      NOT NULL,
    FOREIGN KEY (service_cep_id) REFERENCES service_cep (id) ON DELETE CASCADE
);