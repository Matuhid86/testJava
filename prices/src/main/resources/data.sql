INSERT INTO brand (description) VALUES ('Zara');
INSERT INTO currency (code, description) VALUES ('EUR', 'Euros');
INSERT INTO product (code, description) VALUES ('35455', 'Generic Product');
INSERT INTO price (brand_id, start_date, end_date, price_list, product_id, priority, price, currency_id) VALUES (1, '2020-06-14T00:00:00', '2020-12-31T23:59:59', 1, 1, 0, 35.50, 1);
INSERT INTO price (brand_id, start_date, end_date, price_list, product_id, priority, price, currency_id) VALUES (1, '2020-06-14T15:00:00', '2020-06-14T18:30:00', 2, 1, 1, 25.45, 1);
INSERT INTO price (brand_id, start_date, end_date, price_list, product_id, priority, price, currency_id) VALUES (1, '2020-06-15T00:00:00', '2020-06-15T11:00:00', 3, 1, 1, 30.50, 1);
INSERT INTO price (brand_id, start_date, end_date, price_list, product_id, priority, price, currency_id) VALUES (1, '2020-06-15T16:00:00', '2020-12-31T23:59:59', 4, 1, 1, 38.95, 1);
