INSERT INTO genre (name)
SELECT 'Science Fiction'
WHERE NOT EXISTS (SELECT 1 FROM genre);
INSERT INTO genre (name)
SELECT 'Fantasy';
INSERT INTO genre (name)
SELECT 'Romance';
INSERT INTO genre (name)
SELECT 'Mystery';
INSERT INTO genre (name)
SELECT 'History';


INSERT INTO persons (first_name, middle_name, last_name, address, telephone_number, birth_date)
SELECT 'John', 'A', 'Doe', '123 Main St', '111-222-333', '1980-01-01'
WHERE NOT EXISTS (SELECT 1 FROM persons);

INSERT INTO persons (first_name, middle_name, last_name, address, telephone_number, birth_date)
SELECT 'Jane', NULL, 'Smith', '456 Oak Ave', '444-555-666', '1990-02-02';

INSERT INTO persons (first_name, middle_name, last_name, address, telephone_number, birth_date)
SELECT 'Robert', 'B', 'Brown', '789 Pine Rd', '777-888-999', '1975-03-03';



INSERT INTO users (status, email, registration_date, person_id, username, password)
SELECT 'ACTIVE', 'john@example.com', CURRENT_DATE, p.id, 'john_doe', 'Password1!'
FROM persons p
WHERE p.first_name = 'John'
  AND NOT EXISTS (SELECT 1 FROM users);

INSERT INTO users (status, email, registration_date, person_id, username, password)
SELECT 'ACTIVE', 'jane@example.com', CURRENT_DATE, p.id, 'jane_smith', 'Password1!'
FROM persons p
WHERE p.first_name = 'Jane';



INSERT INTO book (title, genre_id, price, currency, language, page_number, availability, average_rating, author_id,
                  release_date)
SELECT 'The Future of Us',
       g.id,
       19.99,
       'USD',
       'ENGLISH',
       320,
       'AVAILABLE',
       4.5,
       p.id,
       '2020-05-01'
FROM genre g,
     persons p
WHERE g.name = 'Science Fiction'
  AND p.first_name = 'John'
  AND NOT EXISTS (SELECT 1 FROM book);

INSERT INTO book (title, genre_id, price, currency, language, page_number, availability, average_rating, author_id,
                  release_date)
SELECT 'Mystic Tales',
       g.id,
       14.99,
       'EUR',
       'ROMANIAN',
       250,
       'PREORDER',
       4.0,
       p.id,
       '2022-08-15'
FROM genre g,
     persons p
WHERE g.name = 'Fantasy'
  AND p.first_name = 'Jane';


INSERT INTO orders (ship_address, order_date, order_status, user_id)
SELECT '123 Main St', CURRENT_DATE, 'NEW', u.id
FROM users u
WHERE u.username = 'john_doe'
  AND NOT EXISTS (SELECT 1 FROM orders);

INSERT INTO orders (ship_address, order_date, order_status, user_id)
SELECT '456 Oak Ave', CURRENT_DATE, 'DELIVERED', u.id
FROM users u
WHERE u.username = 'jane_smith';



INSERT INTO order_items (order_id, book_id, quantity, unit_price)
SELECT o.id, b.id, 2, b.price
FROM orders o,
     book b
WHERE o.user_id = (SELECT id FROM users WHERE username = 'john_doe')
  AND b.title = 'The Future of Us'
  AND NOT EXISTS (SELECT 1 FROM order_items);

INSERT INTO order_items (order_id, book_id, quantity, unit_price)
SELECT o.id, b.id, 1, b.price
FROM orders o,
     book b
WHERE o.user_id = (SELECT id FROM users WHERE username = 'jane_smith')
  AND b.title = 'Mystic Tales';



INSERT INTO reviews (user_id, book_id, review_message, review_rating)
SELECT u.id, b.id, 'Great read!', 5
FROM users u,
     book b
WHERE u.username = 'john_doe'
  AND b.title = 'The Future of Us'
  AND NOT EXISTS (SELECT 1 FROM reviews);

INSERT INTO reviews (user_id, book_id, review_message, review_rating)
SELECT u.id, b.id, 'Enjoyed it!', 4
FROM users u,
     book b
WHERE u.username = 'jane_smith'
  AND b.title = 'Mystic Tales';