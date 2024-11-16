CREATE TABLE problems (
    id INT,
    pid INT PRIMARY KEY,
    title VARCHAR(1000),
    acceptance VARCHAR(100),
    difficulty VARCHAR(100),
    frequency VARCHAR(100),
    devDojolink VARCHAR(1000)
);

CREATE TABLE firms(
    id INT PRIMARY KEY,
    cid INT,
    companyName VARCHAR(1000)
);
