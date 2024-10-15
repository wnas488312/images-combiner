CREATE TABLE video(
  ID BIGSERIAL PRIMARY KEY,
  width INT,
  height INT,
  zoom FLOAT,
  time_marks JSONB,
  status INT NOT NULL,
  error_message TEXT,
  creation_date TIMESTAMP
);