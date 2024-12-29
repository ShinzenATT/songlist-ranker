CREATE TABLE melodies (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    url TEXT
);

CREATE TABLE tags (
    name varchar(32) PRIMARY KEY
);

CREATE TABLE songs (
   id INT PRIMARY KEY,
   title TEXT NOT NULL,
   melody INT REFERENCES melodies,
   contents TEXT NOT NULL,
   title_vectors tsvector GENERATED ALWAYS AS (to_tsvector('swedish', title)) STORED,
   content_vectors tsvector GENERATED ALWAYS AS (to_tsvector('swedish', contents)) STORED,
   ranking INT DEFAULT 1000 NOT NULL,
   chapter varchar(32) REFERENCES tags
);

-- TODO: Prio 9: Intended to allow for 'custom' explanatory tags to find 'related'/themed songs.
-- Givet att vi vill ha tillbaka det då.
--CREATE TABLE song_tags(
--  song INT references songs,
--  song_tag varchar(32) references tags,
--  PRIMARY KEY (song, song_tag)
--);