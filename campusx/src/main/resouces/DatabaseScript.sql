DROP SEQUENCE IF EXISTS vendor_sequence;
DROP SEQUENCE IF EXISTS shop_sequence;
DROP SEQUENCE IF EXISTS address_sequence;
DROP SEQUENCE IF EXISTS kyc_sequence;
DROP SEQUENCE IF EXISTS item_sequence;
DROP SEQUENCE IF EXISTS user_sequence;
DROP SEQUENCE IF EXISTS otp_sequence;

DROP TABLE IF EXISTS vendor CASCADE;
DROP TABLE IF EXISTS shop CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS kyc CASCADE;
DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS otp CASCADE;

CREATE SEQUENCE vendor_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE shop_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE address_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE kyc_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE item_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE user_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE otp_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE vendor(
	vendor_id BIGINT PRIMARY KEY,
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
	phone_number BIGINT UNIQUE NOT NULL,
	secret_pin INT NOT NULL,
	password VARCHAR NOT NULL,
	add_timestamp TIMESTAMP,
	secret_pin_last_update_timestamp TIMESTAMP,
	password_last_update_timestamp TIMESTAMP,
	account_status BOOLEAN DEFAULT TRUE,
	is_account_closed BOOLEAN DEFAULT FALSE
);

CREATE TABLE address(
	address_id BIGINT PRIMARY KEY,
	street VARCHAR NOT NULL,
	city VARCHAR NOT NULL,
	state VARCHAR NOT NULL,
	country VARCHAR NOT NULL,
	zip_code INT NOT NULL,
	geo_lat INT,
	geo_lang INT
);

CREATE TABLE shop(
	shop_id BIGINT PRIMARY KEY,
	name VARCHAR NOT NULL,
	official_phone_number BIGINT UNIQUE NOT NULL,
	official_email_id VARCHAR UNIQUE NULL,
	shop_picture VARCHAR,
	number_of_items INT NULL,
	average_price INT NULL,
	shop_rating INT NULL,
	add_timestamp TIMESTAMP,
	last_update_timestamp TIMESTAMP,
	status BOOLEAN DEFAULT FALSE,
	address_id BIGINT REFERENCES address(address_id),
	vendor_id BIGINT REFERENCES vendor(vendor_id)
);

CREATE TABLE kyc(
	kyc_id BIGINT PRIMARY KEY,
	gst_number VARCHAR UNIQUE NULL,
	kyc_issue_timestamp TIMESTAMP,
	shop_id BIGINT REFERENCES shop(shop_id)
);

CREATE TABLE item(
	item_id BIGINT PRIMARY KEY,
	name VARCHAR NOT NULL,
	description VARCHAR NOT NULL,
	price INT NOT NULL DEFAULT 1,
	food_type VARCHAR NOT NULL, 
	picture VARCHAR,
	item_rating INT,
	add_timestamp TIMESTAMP,
	last_update_timestamp TIMESTAMP,
	shop_id BIGINT REFERENCES shop(shop_id)
);

CREATE TABLE users(
	user_id BIGINT PRIMARY KEY,
	first_name VARCHAR NOT NULL,
	last_name VARCHAR NOT NULL,
	date_of_birth DATE NOT NULL,
	phone_number BIGINT UNIQUE NOT NULL,
	password VARCHAR NOT NULL,
	profile_picture VARCHAR,
	add_timestamp TIMESTAMP,
	last_update_timestamp TIMESTAMP,
	account_status BOOLEAN DEFAULT TRUE,
	is_account_closed BOOLEAN DEFAULT FALSE
);

CREATE TABLE otp(
	otp_id BIGINT PRIMARY KEY,
	otp INT UNIQUE NOT NULL,
	add_timestamp TIMESTAMP,
	expiry_timestamp TIMESTAMP,
	use_timestamp TIMESTAMP,
	generated_for BIGINT NOT NULL,
	status BOOLEAN DEFAULT FALSE
);

SELECT * FROM users;