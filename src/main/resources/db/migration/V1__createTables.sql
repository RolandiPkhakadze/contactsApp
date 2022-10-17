CREATE TABLE user(
    id int GENERATED ALWAYS AS IDENTITY,
    email varchar (50) not null UNIQUE ,
    username varchar (50) not null UNIQUE ,
    password varchar (300) not null,
    PRIMARY KEY (id),
);

CREATE SEQUENCE user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;


create table history(
     id int GENERATED ALWAYS AS IDENTITY,
     phone_id int,
     start_date date,
     end_date date,
     duration int,
     PRIMARY KEY (id),
     CONSTRAINT fk_phone
         FOREIGN KEY(phone_id)
             REFERENCES customers(phone_id)
);

CREATE SEQUENCE history_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

create table phone(
        id int GENERATED ALWAYS AS IDENTITY,
        phone_number varchar(20),
        user_id int,
        provider_id int,
        balance int,
        PRIMARY KEY (id),
        CONSTRAINT fk_user
            FOREIGN KEY(user_id)
                REFERENCES customers(user_id),
                PRIMARY KEY (id),
        CONSTRAINT fk_provider
            FOREIGN KEY(provider_id)
                REFERENCES customers(provider_id)
);

CREATE SEQUENCE number_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

create table contact(
                             id int GENERATED ALWAYS AS IDENTITY,
                             foreign key (phone_id) references phone(id),
                             user_id int,
                             is_favorite BOOLEAN,
                             first_name varchar(50),
                             last_name varchar (50),
                             PRIMARY KEY (id),
                             CONSTRAINT fk_user
                                 FOREIGN KEY(user_id)
                                     REFERENCES customers(user_id),
                             PRIMARY KEY (id),
                             CONSTRAINT fk_phone_number
                                 FOREIGN KEY(phone_id)
                                     REFERENCES customers(phone_id)
);

CREATE SEQUENCE contact_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;


create table number_provider(
                        id int GENERATED ALWAYS AS IDENTITY,
                        name varchar(20),
                        is_georgian BOOLEAN,
                        tariff_for_geo int,
                        tariff_for_non_geo int,
                        tariff_for_same int,
                        PRIMARY KEY (id),
);

CREATE SEQUENCE number_provider_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;
