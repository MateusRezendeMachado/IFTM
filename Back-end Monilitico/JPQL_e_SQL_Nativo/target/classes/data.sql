-- categorias
INSERT INTO categorias (nome, descricao) VALUES ('Bebidas', 'Bebidas alcoólicas e não alcoólicas');
INSERT INTO categorias (nome, descricao) VALUES ('Alimentos', 'Alimentos em geral');

-- clientes
INSERT INTO clientes (nome, email) VALUES ('João Silva', 'joao@email.com');

-- produtos
INSERT INTO produtos (produtonome, preco, unidadesemestoque, imagem, categoriaid) 
VALUES ('Cerveja IPA', 12.50, 100, 'cerveja.jpg', 1);
INSERT INTO produtos (produtonome, preco, unidadesemestoque, imagem, categoriaid) 
VALUES ('Queijo Minas', 25.00, 50, 'queijo.jpg', 2);

-- pedidos
INSERT INTO pedidos (datapedido, clienteid) VALUES ('2024-06-01', 1);
INSERT INTO pedidos (datapedido, clienteid) VALUES ('2024-06-15', 1);

-- detalhes_pedido
INSERT INTO detalhes_pedido (pedidoid, produtoid, quantidade, precounitario, desconto) 
VALUES (1, 1, 2, 12.50, 0.00);
INSERT INTO detalhes_pedido (pedidoid, produtoid, quantidade, precounitario, desconto) 
VALUES (1, 2, 1, 25.00, 2.50);
INSERT INTO detalhes_pedido (pedidoid, produtoid, quantidade, precounitario, desconto) 
VALUES (2, 1, 5, 12.50, 1.00);