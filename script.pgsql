CREATE TABLE roles (
    id uuid PRIMARY KEY NOT NULL,
    name VARCHAR(40) UNIQUE NOT NULL
);

CREATE TABLE users (
    id uuid PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role_id uuid NOT NULL,
    birth_date DATE NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE authors (
    id uuid PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    biography VARCHAR(255) NOT NULL,
    image_link VARCHAR(255)
);

CREATE TABLE books (
    id uuid PRIMARY KEY NOT NULL,
    title VARCHAR(80) NOT NULL,
    short_description VARCHAR(255),
    author_id uuid NOT NULL,
    cost DECIMAL NOT NULL,
    download_link VARCHAR(255),
    image_link VARCHAR(255),
    FOREIGN KEY (author_id) REFERENCES authors (id)
);

CREATE TABLE reviews (
    id uuid PRIMARY KEY NOT NULL,
    text VARCHAR(255),
    mark INTEGER NOT NULL,
    user_id uuid NOT NULL,
    book_id uuid NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (book_id) REFERENCES books (id)
);

CREATE TABLE orders (
    id uuid PRIMARY KEY NOT NULL,
    adress VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL,
    user_id uuid NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
);

CREATE TABLE genres (
    id uuid PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE books_genres (
    book_id uuid NOT NULL,
    genre_id uuid NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE CASCADE
);
ALTER TABLE books_genres ADD PRIMARY KEY (book_id, genre_id);

CREATE TABLE books_orders (
    book_id uuid NOT NULL,
    order_id uuid NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);
ALTER TABLE books_orders ADD PRIMARY KEY (book_id, order_id);