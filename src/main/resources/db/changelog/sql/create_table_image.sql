CREATE TABLE image (
  ID BIGSERIAL PRIMARY KEY,
  video_id BIGSERIAL NOT NULL,
  start_frame INT NOT NULL,
  end_frame INT NOT NULL,
  zoom INT NOT NULL,
  file_path TEXT NOT NULL
);

ALTER TABLE image ADD FOREIGN KEY (video_id) REFERENCES video(ID);