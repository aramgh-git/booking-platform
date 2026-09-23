CREATE TABLE listings (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    price_per_night DOUBLE PRECISION NOT NULL,
    max_guests INTEGER NOT NULL
)