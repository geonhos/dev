-- src/main/resources/initialize.sql
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE test_table (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL
);

INSERT INTO test_table (name) VALUES ('example');