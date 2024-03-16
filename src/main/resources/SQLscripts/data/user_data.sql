INSERT INTO USERS (id, first_name, last_name, birthday, hash_password, email, role, created_at, updated_at)
VALUES
    (UUID_GENERATE_V4(), 'John', 'Doe', '1980-01-01', encode(gen_random_bytes(32), 'hex'), 'john.doe@example.com', 'ROLE_USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (UUID_GENERATE_V4(), 'Jane', 'Doe', '1985-02-02', encode(gen_random_bytes(32), 'hex'), 'jane.doe@example.com', 'ROLE_USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (UUID_GENERATE_V4(), 'Alice', 'Smith', '1990-03-03', encode(gen_random_bytes(32), 'hex'), 'alice.smith@example.com', 'ROLE_ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (UUID_GENERATE_V4(), 'Bob', 'Johnson', '1995-04-04', encode(gen_random_bytes(32), 'hex'), 'bob.johnson@example.com', 'ROLE_USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (UUID_GENERATE_V4(), 'Charlie', 'Brown', '2000-05-05', encode(gen_random_bytes(32), 'hex'), 'charlie.brown@example.com', 'ROLE_USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);