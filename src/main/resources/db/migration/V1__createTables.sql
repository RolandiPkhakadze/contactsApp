CREATE TABLE users(
    id INT PRIMARY KEY,
    email VARCHAR (50) NOT NULL UNIQUE,
    username VARCHAR (50) NOT NULL UNIQUE ,
    password VARCHAR (300) NOT NULL
);

CREATE TABLE number_providers(
     id SERIAL PRIMARY KEY,
     name VARCHAR(20),
     is_georgian BOOLEAN,
     tariff_for_geo INT,
     tariff_for_non_geo INT,
     tariff_for_same INT
);

CREATE TABLE phones(
        id SERIAL PRIMARY KEY,
        phone_number VARCHAR(20),
        user_id INT,
        provider_id INT,
        balance INT,
        CONSTRAINT fk_user
            FOREIGN KEY(user_id)
                REFERENCES users(id),
        CONSTRAINT fk_provider
            FOREIGN KEY(provider_id)
                REFERENCES number_providers(id)
);


CREATE TABLE histories(
      id SERIAL PRIMARY KEY,
      phone_id INT,
      user_id INT,
      start_date date,
      end_date date,
      duration INT,
      CONSTRAINT fk_phone
          FOREIGN KEY(phone_id)
              REFERENCES phones(id),
      CONSTRAINT fk_user
          FOREIGN KEY(user_id)
              REFERENCES users(id)
);

CREATE TABLE contacts(
     id SERIAL PRIMARY KEY,
     FOREIGN KEY (id) REFERENCES phones(id),
     user_id INT,
     is_favorite BOOLEAN,
     first_name VARCHAR(50),
     last_name VARCHAR (50),
     CONSTRAINT fk_user
         FOREIGN KEY(user_id)
             REFERENCES users(id),
     CONSTRAINT fk_phone_number
         FOREIGN KEY(id)
             REFERENCES phones(id)
);






