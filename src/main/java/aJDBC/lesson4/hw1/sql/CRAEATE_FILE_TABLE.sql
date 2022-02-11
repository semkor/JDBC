USE database_name;

CREATE TABLE FILE(
    ID INT,
    CONSTRAINT FILES_PK PRIMARY KEY (ID),
    NAME VARCHAR(50) NOT NULL,
    FORMAT VARCHAR(50) NOT NULL,
    SIZE INT,
    STORAGE_ID INT,
        CONSTRAINT STORAGE_ID_FK FOREIGN KEY (STORAGE_ID) REFERENCES STORAGE(ID)
);