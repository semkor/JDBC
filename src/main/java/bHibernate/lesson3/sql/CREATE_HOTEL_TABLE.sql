USE database_name;

CREATE TABLE HOTELS(
    ID INT AUTO_INCREMENT,
    CONSTRAINT HOTEL_ID PRIMARY KEY (ID),
    NAME VARCHAR(40) NOT NULL,
    COUNTRY VARCHAR(40),
    CITY VARCHAR(40),
    STREET VARCHAR(40),
    ROOM_ID INT NOT NULL,
    CONSTRAINT HOTELS_FK FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ID)
);

