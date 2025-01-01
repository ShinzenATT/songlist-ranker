CREATE TABLE IF NOT EXISTS melodies (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    url TEXT
);

CREATE TABLE IF NOT EXISTS tags (
    id Serial PRIMARY KEY,
    name varchar(32) UNIQUE
);

CREATE TABLE IF NOT EXISTS songs (
   id SERIAL PRIMARY KEY,
   title TEXT NOT NULL,
   melody INT REFERENCES melodies,
   contents TEXT NOT NULL,
   ranking INT DEFAULT 1000 NOT NULL,
   chapter INT REFERENCES tags,
   search_vectors tsvector GENERATED ALWAYS AS (to_tsvector('swedish', title || ' ' || contents)) STORED
);

CREATE INDEX IF NOT EXISTS textsearch_songs ON songs USING GIN (search_vectors)

-- TODO: Prio 9: Intended to allow for 'custom' explanatory tags to find 'related'/themed songs.
-- Givet att vi vill ha tillbaka det då.
--CREATE TABLE song_tags(
--  song INT references songs,
--  song_tag varchar(32) references tags,
--  PRIMARY KEY (song, song_tag)
--);