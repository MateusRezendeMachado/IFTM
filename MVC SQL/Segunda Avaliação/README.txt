Exercício Bootstrap

Descrição do Projeto
Este projeto consiste na criação de uma loja virtual responsiva utilizando Bootstrap 5, com página inicial e páginas individuais para produtos. O foco foi implementar um layout responsivo que se adapta a diferentes tamanhos de tela, seguindo as práticas de desenvolvimento mobile-first.

O que foi implementado

1. Estrutura Base Responsiva
- **Layout principal**: Utilização de `min-vh-100` e `flex-column` para garantir que o footer sempre fique no fundo da página
- **Container fluido**: Implementação de containers responsivos que se adaptam ao viewport
- **Sistema de grid**: Uso do grid system do Bootstrap para criar layouts responsivos

2. Navegação
- **Navbar responsiva**: Menu que colapsa em dispositivos móveis e expande em desktop
- **Breadcrumbs**: Navegação hierárquica nas páginas de produto
- **Links de navegação**: Estrutura semântica com estados ativos

3. Página Inicial
- **Alert promocional**: Banner de promoção visível em todas as larguras de tela
- **Sidebar responsiva**: Coluna lateral que ocupa 100% em mobile e 25% em desktop
- **Grid de produtos**: 
  - 1 coluna em mobile
  - 2 colunas em tablet  
  - 3 colunas em desktop
- **Cards padronizados**: Uso de `ratio ratio-4x3` para imagens e `h-100` para altura uniforme

4. Páginas de Produto
- **Layout de duas colunas**: Imagem e informações lado a lado em desktop, empilhadas em mobile
- **Informações organizadas**: Preço, badges, descrição e especificações
- **Call-to-action**: Botões de compra com espaçamento adequado
- **Problema resolvido**: Espaçamento entre botões e footer corrigido com classes responsivas de padding

5. Componentes Utilizados
- **Cards**: Para exibição de produtos
- **Badges**: Para indicadores de status
- **Buttons**: Com variações de estilo e tamanho
- **Breadcrumb**: Para navegação contextual
- **Alert**: Para mensagens promocionais

Decisões de Design Descartadas

1. Grid Complexo
Inicialmente considerei um grid mais complexo com diferentes quantidades de colunas por breakpoint, mas optei pela simplicidade do `row-cols-1 row-cols-sm-2 row-cols-lg-3` para melhor legibilidade do código.

2. Carousel de Imagens
Pensei em implementar um carousel para as imagens dos produtos, mas descartei para manter o foco na responsividade básica e evitar complexidade desnecessária.

3. Modal de Compra
A ideia de abrir um modal ao clicar em "Comprar" foi descartada em favor de páginas dedicadas para cada produto, seguindo o padrão convencional de e-commerce.

4. Menu Dropdown
Considerou-se usar dropdowns no menu principal, mas manteve-se a simplicidade com links diretos para manter a navegação clara e acessível.

Técnicas de Responsividade Implementadas

Breakpoints Utilizados
- **Mobile**: < 576px (col-12)
- **Tablet**: ≥ 768px (col-md-*)
- **Desktop**: ≥ 992px (col-lg-*)

Abordagem Mobile-First
- Desenvolvimento iniciado por dispositivos móveis
- Progressive enhancement para telas maiores
- Uso de min-width nos media queries do Bootstrap

Conclusão
O projeto demonstra a aplicação prática do Bootstrap 5 para criar interfaces responsivas, com foco na adaptabilidade a diferentes dispositivos e na manutenção da usabilidade em todos os tamanhos de tela. A solução final equilibra funcionalidade, performance e experiência do usuário.