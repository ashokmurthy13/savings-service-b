-- create savings account table --

DROP TABLE IF EXISTS savings_account_tbl;
CREATE TABLE savings_account_tbl(
	id bigserial PRIMARY KEY,
	account_no bigint not null,
	account_type varchar(255) not null,
	transaction_type varchar(255) not null,
	description varchar(255) not null,
	deposit bigint,
	withdraw bigint,
	balance bigint not null,
	updated_at timestamp not null default now()
);

DROP TABLE IF EXISTS customer_tbl;
CREATE TABLE customer_tbl(
	account_no bigserial PRIMARY KEY,
	account_type varchar(255) not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	address varchar(255) not null,
	created_at timestamp not null default now()
);

ALTER SEQUENCE customer_tbl_account_no_seq RESTART WITH 4910115;
