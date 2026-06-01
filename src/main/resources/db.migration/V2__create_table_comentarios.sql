CREATE TABLE comentarios(
    id SERIAL PRIMARY KEY,
    comentario VARCHAR(255),
    estrelas NUMERIC NOT NULL,
    created_at TIMESTAMP,
    update_at TIMESTAMP,
    livro_id INT NOT NULL,  -- 👈 chave estrangeira
    FOREIGN KEY (livro_id) REFERENCES livros(id)
)