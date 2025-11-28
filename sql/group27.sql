CREATE DATABASE IF NOT EXISTS contact_mgmt_db;
USE contact_mgmt_db;

-- Kullanıcılar Tablosu
DROP TABLE IF EXISTS users;
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password_hash VARCHAR(255) NOT NULL,
                       first_name VARCHAR(50) NOT NULL,
                       last_name VARCHAR(50) NOT NULL,
                       role ENUM('TESTER', 'JUNIOR_DEV', 'SENIOR_DEV', 'MANAGER') NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Kişiler (Contacts) Tablosu
DROP TABLE IF EXISTS contacts;
CREATE TABLE contacts (
                          contact_id INT AUTO_INCREMENT PRIMARY KEY,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          phone_primary VARCHAR(20) NOT NULL,
                          email VARCHAR(100),
                          birth_date DATE,
                          linkedin_url VARCHAR(255),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Zorunlu Kullanıcılar (Şifreler MD5 hashli)
-- tt/tt, jd/jd, sd/sd, man/man
INSERT INTO users (username, password_hash, first_name, last_name, role) VALUES
                                                                             ('tt', 'accc9105df5383111407fd5b41255e23', 'Test', 'User', 'TESTER'),
                                                                             ('jd', '6d2539f150495f32b8eb309e51c6b16d', 'Junior', 'Dev', 'JUNIOR_DEV'),
                                                                             ('sd', '0d45330c69d80c057b4943f77df270d4', 'Senior', 'Dev', 'SENIOR_DEV'),
                                                                             ('man', '39c63ddb96a31b9610cd976b896ad4f0', 'Manager', 'User', 'MANAGER');