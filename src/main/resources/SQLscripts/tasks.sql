CREATE TABLE TASKS (
                       id UUID PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description VARCHAR(1000),
                       due_date TIMESTAMP NOT NULL,
                       completed BOOLEAN NOT NULL,
                       priority VARCHAR(255),
                       status VARCHAR(255),
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP NOT NULL,
                       user_id UUID NOT NULL,
                       FOREIGN KEY (user_id) REFERENCES USERS(id)
);