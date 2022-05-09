create table cliente
(
  id bigint not null
    primary key,
  celular varchar,
  data_hora_atualizacao timestamp,
  data_hora_cadastro timestamp not null,
  data_nascimento timestamp not null,
  email varchar not null,
  nome varchar,
  situacao integer not null,
  sobrenome varchar
);

INSERT INTO cliente (id, celular, data_hora_atualizacao, data_hora_cadastro, data_nascimento, email, nome, situacao, sobrenome)
VALUES (1, '(99) 9999-9999', '2022-05-09 00:00:00', '2022-05-09 00:00:00', '1973-05-10', 'um@email.com', 'Um', 1, 'Silva');

INSERT INTO cliente (id, celular, data_hora_atualizacao, data_hora_cadastro, data_nascimento, email, nome, situacao, sobrenome)
VALUES (2, '(99) 9999-9999', '2022-05-09 00:00:00', '2022-05-09 00:00:00', '1981-11-18', 'dois@email.com', 'Dois', 1, 'Carvalho');

INSERT INTO cliente (id, celular, data_hora_atualizacao, data_hora_cadastro, data_nascimento, email, nome, situacao, sobrenome)
VALUES (3, '(99) 9999-9999', '2022-05-09 00:00:00', '2022-05-09 00:00:00', '1951-11-09', 'tres@email.com', 'Três', 1, 'Mendonça');

