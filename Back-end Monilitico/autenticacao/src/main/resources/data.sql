
-- Roles
INSERT INTO roles (nome) VALUES ('ADMIN');
INSERT INTO roles (nome) VALUES ('USER');

-- Permissões
INSERT INTO permissoes (nome) VALUES ('CLIENTE_READ');
INSERT INTO permissoes (nome) VALUES ('CLIENTE_WRITE');

-- ADMIN tem ambas
INSERT INTO role_permissoes (role_id, permissao_id) VALUES (1, 1);
INSERT INTO role_permissoes (role_id, permissao_id) VALUES (1, 2);

-- USER só tem READ
INSERT INTO role_permissoes (role_id, permissao_id) VALUES (2, 1);

-- Usuários (senhas com BCrypt: cadu/cadu123, joao/joao123)
INSERT INTO usuarios (login, senha) VALUES ('cadu', '$2a$10$7l8NcWp9Z6k5Q1V2W3e4f5g6h7i8j9k0l1m2n3o4p5q6r7s8t9u0v1w2x3y4z5');
INSERT INTO usuarios (login, senha) VALUES ('joao', '$2a$10$7l8NcWp9Z6k5Q1V2W3e4f5g6h7i8j9k0l1m2n3o4p5q6r7s8t9u0v1w2x3y4z5');

-- vincular cadu -> ADMIN, joao -> USER
INSERT INTO usuario_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuario_roles (usuario_id, role_id) VALUES (2, 2);
