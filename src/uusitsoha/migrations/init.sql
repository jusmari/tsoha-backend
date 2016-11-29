



CREATE TABLE IF NOT EXISTS questions (
  id varchar NOT NULL,
  body varchar NOT NULL,
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(id)
);

--;;


CREATE TABLE IF NOT EXISTS users (
  id varchar NOT NULL PRIMARY KEY,
  name varchar NOT NULL,
  password varchar NOT NULL
);

--;;

CREATE TABLE IF NOT EXISTS organizations (
  id varchar NOT NULL,
  name varchar NOT NULL
);

--;;

CREATE TABLE IF NOT EXISTS membership (
  user_id varchar NOT NULL,
  organization_id varchar NOT NULL
);

--;;

CREATE TABLE IF NOT EXISTS answers (
  question_id varchar NOT NULL,
  user_id varchar NOT NULL,
  correct boolean
);
