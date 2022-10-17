DROP DATABASE "contactsApp";

CREATE DATABASE  "contactsApp";



CREATE TABLE "user"(
    id serial PRIMARY KEY,
    email varchar (50) not null UNIQUE ,
    username varchar (50) not null UNIQUE ,
    password varchar (300) not null
);

CREATE SEQUENCE user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;


create table history(
     id serial PRIMARY KEY,
     phone_id int,
     start_date date,
     end_date date,
     duration int,
     CONSTRAINT fk_phone
         FOREIGN KEY(phone_id)
             REFERENCES phone(id)
);

CREATE SEQUENCE history_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

create table phone(
        id serial PRIMARY KEY,
        phone_number varchar(20),
        user_id int,
        provider_id int,
        balance int,
        CONSTRAINT fk_user
            FOREIGN KEY(user_id)
                REFERENCES "user"(id),
        CONSTRAINT fk_provider
            FOREIGN KEY(provider_id)
                REFERENCES number_provider(id)
);

CREATE SEQUENCE number_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

create table contact(
     id serial PRIMARY KEY,
     foreign key (id) references phone(id),
     user_id int,
     is_favorite BOOLEAN,
     first_name varchar(50),
     last_name varchar (50),
     CONSTRAINT fk_user
         FOREIGN KEY(user_id)
             REFERENCES "user"(id),
     CONSTRAINT fk_phone_number
         FOREIGN KEY(id)
             REFERENCES phone(id)
);

CREATE SEQUENCE contact_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;


create table number_provider(
    id serial PRIMARY KEY,
    name varchar(20),
    is_georgian BOOLEAN,
    tariff_for_geo int,
    tariff_for_non_geo int,
    tariff_for_same int
);

CREATE SEQUENCE number_provider_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;
