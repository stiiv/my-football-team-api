CREATE TABLE IF NOT EXISTS members (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

INSERT INTO members (name) VALUES
    ('Marko'),
    ('Ivan'),
    ('Mate');
