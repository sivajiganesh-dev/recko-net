DROP TABLE IF EXISTS users;

CREATE TABLE users (
   id         BIGINT IDENTITY PRIMARY KEY,
   username   VARCHAR(50) UNIQUE NOT NULL,
   password   VARCHAR(60)        NOT NULL,
   email      VARCHAR(50) UNIQUE NOT NULL,
   phone      VARCHAR(15)
);

DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
   id         BIGINT IDENTITY PRIMARY KEY,
   user_id    BIGINT IDENTITY PRIMARY KEY,
   subject    VARCHAR(50)        NOT NULL,
   content    VARCHAR(60)        NOT NULL,
   created    DATETIME           NOT NULL,
   FOREIGN KEY (user_id) REFERENCES users (id)

);

DROP TABLE IF EXISTS friends;

CREATE TABLE IF NOT EXISTS friends (
   id         BIGINT IDENTITY PRIMARY KEY,
   user_id BIGINT NOT NULL,
   friend_id BIGINT NOT NULL,
   FOREIGN KEY (user_id) REFERENCES users (id),
   FOREIGN KEY (friend_id) REFERENCES users (id),
);