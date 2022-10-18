CREATE TABLE users(
    id int PRIMARY KEY,
    email varchar (50) not null UNIQUE ,
    username varchar (50) not null UNIQUE ,
    password varchar (300) not null
);

create table number_providers(
     id serial PRIMARY KEY,
     name varchar(20),
     is_georgian BOOLEAN,
     tariff_for_geo int,
     tariff_for_non_geo int,
     tariff_for_same int
);

create table phones(
        id serial PRIMARY KEY,
        phone_number varchar(20),
        user_id int,
        provider_id int,
        balance int,
        CONSTRAINT fk_user
            FOREIGN KEY(user_id)
                REFERENCES users(id),
        CONSTRAINT fk_provider
            FOREIGN KEY(provider_id)
                REFERENCES number_providers(id)
);


create table histories(
      id serial PRIMARY KEY,
      phone_id int,
      start_date date,
      end_date date,
      duration int,
      CONSTRAINT fk_phone
          FOREIGN KEY(phone_id)
              REFERENCES phones(id)
);

create table contacts(
     id serial PRIMARY KEY,
     foreign key (id) references phones(id),
     user_id int,
     is_favorite BOOLEAN,
     first_name varchar(50),
     last_name varchar (50),
     CONSTRAINT fk_user
         FOREIGN KEY(user_id)
             REFERENCES users(id),
     CONSTRAINT fk_phone_number
         FOREIGN KEY(id)
             REFERENCES phones(id)
);



