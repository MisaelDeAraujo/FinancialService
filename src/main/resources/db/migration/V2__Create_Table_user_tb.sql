CREATE TABLE IF NOT EXISTS user_tb(
    id SERIAL PRIMARY KEY,
    complete_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    cnpj VARCHAR(14) UNIQUE NOT NULL,
    wallet DOUBLE PRECISION NOT NULL,
    user_type user_type
);