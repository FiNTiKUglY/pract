CREATE TABLE roles (
    id uuid PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    id uuid PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    role_id uuid NOT NULL,
    bith_date TIMESTAMP NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE authors (
    id uuid PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    biography VARCHAR(255) NOT NULL
);

CREATE TABLE books (
    id uuid PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL,
    short_description VARCHAR(255),
    author_id uuid NOT NULL,
    cost DECIMAL NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors (id)
);

CREATE TABLE reviews (
    id uuid PRIMARY KEY NOT NULL,
    text VARCHAR(255),
    mark INTEGER NOT NULL,
    user_id uuid NOT NULL,
    book_id uui NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);

CREATE TABLE orders (
    id uuid PRIMARY KEY NOT NULL,
    adress VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL,
    user_id uuid NOT NULL,
    book_id uui NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);

CREATE TABLE genres (
    id uuid PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE book_genre (
    book_id uuid PRIMARY KEY NOT NULL,
    genre_id uuid PRIMARY KEY NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books (id),
    FOREIGN KEY (genre_id) REFERENCES genres (id)
);