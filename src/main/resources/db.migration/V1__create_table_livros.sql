CREATE TABLE livros(
        id serial PRIMARY KEY,
        titulo VARCHAR(255),
        autor VARCHAR(255),
        capa VARCHAR(255),
        sinopse VARCHAR(255),
        created_at timestamp,
        update_at timestamp

);