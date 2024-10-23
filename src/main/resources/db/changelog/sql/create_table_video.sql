CREATE TABLE video(
  ID BIGSERIAL PRIMARY KEY,
  width INT,
  height INT,
  status INT NOT NULL,
  error_message TEXT,
  creation_date TIMESTAMP
);