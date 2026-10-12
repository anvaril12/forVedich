CREATE TABLE tax_rate (
                          id INTEGER PRIMARY KEY,
                          rate NUMERIC(5, 2) NOT NULL,
                          CONSTRAINT tax_rate_range CHECK (rate >= 1 AND rate <= 100)
);

INSERT INTO tax_rate (id, rate)
VALUES (1, 13.00);