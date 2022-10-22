
ALTER TABLE contacts
    DROP CONSTRAINT contacts_id_fkey;

ALTER TABLE contacts
    DROP CONSTRAINT fk_phone_number;

ALTER TABLE histories
    DROP CONSTRAINT fk_phone;

ALTER TABLE phones
 DROP COLUMN id;

ALTER TABLE  phones
 ADD PRIMARY KEY (phone_number);

ALTER TABLE  contacts
    ALTER COLUMN id TYPE VARCHAR(50);


ALTER TABLE contacts
 ADD CONSTRAINT fk_phone_number FOREIGN KEY (id)
            REFERENCES phones(phone_number);
