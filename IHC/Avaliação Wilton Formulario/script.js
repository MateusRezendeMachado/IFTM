const catalogo = [
  { titulo: "Dom Casmurro", autor: "Machado de Assis", isbn: "9788535911664", ano: 1899 },
  { titulo: "Memórias Póstumas de Brás Cubas", autor: "Machado de Assis", isbn: "9788535911671", ano: 1881 },
  { titulo: "Quincas Borba", autor: "Machado de Assis", isbn: "9788535911688", ano: 1891 },
  { titulo: "O Alienista", autor: "Machado de Assis", isbn: "9788535925340", ano: 1882 },
  { titulo: "Capitães da Areia", autor: "Jorge Amado", isbn: "9788535914061", ano: 1937 },
  { titulo: "Gabriela, Cravo e Canela", autor: "Jorge Amado", isbn: "9788535921786", ano: 1958 },
  { titulo: "Dona Flor e Seus Dois Maridos", autor: "Jorge Amado", isbn: "9788535920789", ano: 1966 },
  { titulo: "Vidas Secas", autor: "Graciliano Ramos", isbn: "9788501115102", ano: 1938 },
  { titulo: "São Bernardo", autor: "Graciliano Ramos", isbn: "9788501115591", ano: 1934 },
  { titulo: "A Hora da Estrela", autor: "Clarice Lispector", isbn: "9788532508126", ano: 1977 },
  { titulo: "Laços de Família", autor: "Clarice Lispector", isbn: "9788532508089", ano: 1960 },
  { titulo: "Perto do Coração Selvagem", autor: "Clarice Lispector", isbn: "9788532508140", ano: 1943 },
  { titulo: "Grande Sertão: Veredas", autor: "João Guimarães Rosa", isbn: "9788520925270", ano: 1956 },
  { titulo: "Primeiras Estórias", autor: "João Guimarães Rosa", isbn: "9788520931561", ano: 1962 },
  { titulo: "O Cortiço", autor: "Aluísio Azevedo", isbn: "9788508044733", ano: 1890 },
  { titulo: "Triste Fim de Policarpo Quaresma", autor: "Lima Barreto", isbn: "9788535919653", ano: 1915 },
  { titulo: "Macunaíma", autor: "Mário de Andrade", isbn: "9788535919011", ano: 1928 },
  { titulo: "O Quinze", autor: "Rachel de Queiroz", isbn: "9788501062635", ano: 1930 },
  { titulo: "Morte e Vida Severina", autor: "João Cabral de Melo Neto", isbn: "9788501077752", ano: 1955 },
  { titulo: "Auto da Compadecida", autor: "Ariano Suassuna", isbn: "9788520939505", ano: 1955 },
  { titulo: "Torto Arado", autor: "Itamar Vieira Junior", isbn: "9789896658403", ano: 2019 },
  { titulo: "1984", autor: "George Orwell", isbn: "9788535914849", ano: 1949 },
  { titulo: "A Revolução dos Bichos", autor: "George Orwell", isbn: "9788535909555", ano: 1945 },
  { titulo: "Cem Anos de Solidão", autor: "Gabriel García Márquez", isbn: "9788501012074", ano: 1967 },
  { titulo: "O Amor nos Tempos do Cólera", autor: "Gabriel García Márquez", isbn: "9788501078759", ano: 1985 },
  { titulo: "O Senhor dos Anéis: A Sociedade do Anel", autor: "J.R.R. Tolkien", isbn: "9788595084742", ano: 1954 },
  { titulo: "O Hobbit", autor: "J.R.R. Tolkien", isbn: "9788595084735", ano: 1937 },
  { titulo: "Admirável Mundo Novo", autor: "Aldous Huxley", isbn: "9788508045228", ano: 1932 },
  { titulo: "Fahrenheit 451", autor: "Ray Bradbury", isbn: "9788525425423", ano: 1953 },
  { titulo: "O Pequeno Príncipe", autor: "Antoine de Saint-Exupéry", isbn: "9788522031412", ano: 1943 },
  { titulo: "Crime e Castigo", autor: "Fiódor Dostoiévski", isbn: "9788573266467", ano: 1866 },
  { titulo: "Os Irmãos Karamázov", autor: "Fiódor Dostoiévski", isbn: "9788573265002", ano: 1880 },
  { titulo: "Metamorfose", autor: "Franz Kafka", isbn: "9788535900897", ano: 1915 },
  { titulo: "O Processo", autor: "Franz Kafka", isbn: "9788535912449", ano: 1925 },
  { titulo: "O Apanhador no Campo de Centeio", autor: "J.D. Salinger", isbn: "9788571236806", ano: 1951 },
  { titulo: "Ensaio sobre a Cegueira", autor: "José Saramago", isbn: "9788535900712", ano: 1995 },
  { titulo: "O Ano da Morte de Ricardo Reis", autor: "José Saramago", isbn: "9788535901122", ano: 1984 },
  { titulo: "Orgulho e Preconceito", autor: "Jane Austen", isbn: "9788525422897", ano: 1813 },
  { titulo: "O Conde de Monte Cristo", autor: "Alexandre Dumas", isbn: "9788537808931", ano: 1844 }
];

// Normaliza texto removendo acentos e espaços
const norm = s => s.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase().trim();
// Extrai apenas caracteres numéricos e X
const soDigitos = s => s.replace(/[^0-9xX]/g, "").toUpperCase();
// Escapa HTML contra XSS
const esc = s => s.replace(/[&<>"']/g, c => ({ "&": "&amp;", "<": "&lt;", ">": "&gt;", '"': "&quot;", "'": "&#39;" }[c]));

// Formata exibição do ISBN-13
function formatarIsbn(d) {
  if (d.length === 13) return `${d.slice(0,3)}-${d.slice(3,5)}-${d.slice(5,8)}-${d.slice(8,12)}-${d.slice(12)}`;
  return d;
}

// Valida dígito verificador ISBN 10/13
function validarIsbn(d) {
  if (d.length === 10) {
    let soma = 0;
    for (let i = 0; i < 10; i++) {
      const c = d[i];
      let v;
      if (c === "X") {
        if (i !== 9) return false;
        v = 10;
      } else if (c >= "0" && c <= "9") {
        v = c.charCodeAt(0) - 48;
      } else {
        return false;
      }
      soma += v * (10 - i);
    }
    return soma % 11 === 0;
  }
  if (d.length === 13) {
    let soma = 0;
    for (let i = 0; i < 13; i++) {
      const c = d[i];
      if (c < "0" || c > "9") return false;
      const v = c.charCodeAt(0) - 48;
      soma += v * (i % 2 === 0 ? 1 : 3);
    }
    return soma % 10 === 0;
  }
  return false;
}

/* =====================================================================
   ALGORITMO DE SUGESTÃO ("Você quis dizer")
   ---------------------------------------------------------------------
   1. Distância de Damerau-Levenshtein: número mínimo de inserções,
      remoções, trocas e transposições de letras vizinhas ("casmurro"
      -> "casmruro" conta 1) para transformar um texto em outro.
   2. Similaridade = 1 - distância / tamanho do maior texto  (0 a 1).
   3. Para cada campo preenchido, calcula-se a nota contra o valor
      correspondente de cada livro:
        - texto (autor/título): maior entre a similaridade do texto
          inteiro e a média, por palavra digitada, da melhor
          similaridade com as palavras do candidato (aceita digitação
          parcial e palavras fora de ordem). Palavras com menos de
          3 letras ("de", "da", "o") são ignoradas.
        - ISBN: similaridade direta dos dígitos.
   4. Um livro só vira sugestão se TODOS os campos preenchidos passarem
      do limiar. A nota final é a média das notas dos campos.
   5. A sugestão corrige apenas os campos que o usuário preencheu;
      sugestões iguais são agrupadas; mostram-se as 3 melhores.
   ===================================================================== */
const LIMIAR_TEXTO = 0.7;
const LIMIAR_ISBN = 0.8;
const MAX_SUGESTOES = 3;

function distancia(a, b) {
  const m = a.length, n = b.length;
  if (!m) return n;
  if (!n) return m;
  const d = Array.from({ length: m + 1 }, (_, i) => {
    const linha = new Array(n + 1).fill(0);
    linha[0] = i;
    return linha;
  });
  for (let j = 1; j <= n; j++) d[0][j] = j;
  for (let i = 1; i <= m; i++) {
    for (let j = 1; j <= n; j++) {
      const custo = a[i - 1] === b[j - 1] ? 0 : 1;
      d[i][j] = Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + custo);
      if (i > 1 && j > 1 && a[i - 1] === b[j - 2] && a[i - 2] === b[j - 1]) {
        d[i][j] = Math.min(d[i][j], d[i - 2][j - 2] + 1); // transposição
      }
    }
  }
  return d[m][n];
}

function similaridade(a, b) {
  const t = Math.max(a.length, b.length);
  return t ? 1 - distancia(a, b) / t : 1;
}

function pontuarTexto(consulta, candidato) {
  const q = norm(consulta), c = norm(candidato);
  if (c.includes(q)) return 1;
  const global = similaridade(q, c);
  const palavrasC = c.split(/\s+/);
  let palavrasQ = q.split(/\s+/).filter(p => p.length >= 3);
  if (!palavrasQ.length) palavrasQ = q.split(/\s+/);
  const porPalavra = palavrasQ.reduce((soma, p) => {
    const melhor = Math.max(...palavrasC.map(w =>
      Math.max(similaridade(p, w), similaridade(p, w.slice(0, p.length)))
    ));
    return soma + melhor;
  }, 0) / palavrasQ.length;
  return Math.max(global, porPalavra);
}

// Retorna até MAX_SUGESTOES consultas corrigidas: { autor, titulo, isbn, rotulo }
function sugerir(a, t, i) {
  const a1 = a.trim(), t1 = t.trim();
  const melhores = new Map();

  for (const l of catalogo) {
    const notas = [];
    if (a1) notas.push([pontuarTexto(a1, l.autor), LIMIAR_TEXTO]);
    if (t1) notas.push([pontuarTexto(t1, l.titulo), LIMIAR_TEXTO]);
    if (i)  notas.push([similaridade(i, l.isbn), LIMIAR_ISBN]);
    if (!notas.length || notas.some(([n, min]) => n < min)) continue;

    const nota = notas.reduce((s, [n]) => s + n, 0) / notas.length;
    const partes = [];
    if (t1) partes.push(l.titulo);
    if (a1) partes.push(l.autor);
    if (i && !t1) partes.unshift(l.titulo); // só ISBN digitado: mostra também o título
    if (i)  partes.push("ISBN " + formatarIsbn(l.isbn));
    const rotulo = partes.join(" · ");

    if (!melhores.has(rotulo) || melhores.get(rotulo).nota < nota) {
      melhores.set(rotulo, {
        nota, rotulo,
        autor: a1 ? l.autor : null,
        titulo: t1 ? l.titulo : null,
        isbn: i ? l.isbn : null
      });
    }
  }

  return [...melhores.values()]
    .sort((x, y) => y.nota - x.nota || x.rotulo.localeCompare(y.rotulo, "pt-BR"))
    .slice(0, MAX_SUGESTOES);
}

// ===== INTERFACE =====
const $ = id => document.getElementById(id);
const form = $("form"), autor = $("autor"), titulo = $("titulo"), isbn = $("isbn");
const status = $("status"), btnPesquisar = $("btn-pesquisar");
let timer = null;

// Restringe caracteres permitidos no campo
isbn.addEventListener("input", () => {
  isbn.value = isbn.value.replace(/[^0-9xX-]/g, "");
  limparErroIsbn();
});
[autor, titulo].forEach(el => el.addEventListener("input", limparErroGeral));

function limparErroIsbn() {
  $("isbn-erro").hidden = true;
  isbn.removeAttribute("aria-invalid");
}
function limparErroGeral() {
  $("erro-geral").hidden = true;
  [autor, titulo, isbn].forEach(el => el.removeAttribute("aria-invalid"));
}

// Valida preenchimento e regras do formulário
function validar() {
  limparErroGeral();
  limparErroIsbn();
  const a = autor.value.trim(), t = titulo.value.trim(), i = soDigitos(isbn.value);

  if (!a && !t && !i) {
    $("erro-geral").textContent = "Preencha pelo menos um campo (autor, título ou ISBN) para pesquisar.";
    $("erro-geral").hidden = false;
    [autor, titulo, isbn].forEach(el => el.setAttribute("aria-invalid", "true"));
    autor.focus();
    return false;
  }
  if (i && !(i.length === 10 || i.length === 13)) {
    $("isbn-erro").textContent = `O ISBN tem ${i.length} número(s), mas deve ter 10 ou 13. Confira e tente de novo.`;
    $("isbn-erro").hidden = false;
    isbn.setAttribute("aria-invalid", "true");
    isbn.focus();
    return false;
  }
  if (i && !validarIsbn(i)) {
    $("isbn-erro").textContent =
      "O ISBN digitado não é válido: o dígito verificador não confere. " +
      "Confira os números na contracapa do livro e tente novamente.";
    $("isbn-erro").hidden = false;
    isbn.setAttribute("aria-invalid", "true");
    // Um ISBN digitado errado quase sempre falha no dígito verificador:
    // aqui também oferecemos "Você quis dizer".
    const sugestoes = sugerir(a, t, i);
    if (sugestoes.length) mostrarSugestoes(sugestoes, false);
    else isbn.focus();
    return false;
  }
  return true;
}

function mostrarCarregando() {
  status.innerHTML = `
    <div class="panel info" role="status">
      <p><span class="spinner" aria-hidden="true"></span>Buscando obras…</p>
      <div class="actions"><button type="button" id="btn-cancelar">Cancelar busca</button></div>
    </div>`;
  $("btn-cancelar").addEventListener("click", cancelar);
}

function cancelar() {
  clearTimeout(timer);
  timer = null;
  btnPesquisar.disabled = false;
  status.innerHTML = `
    <div class="panel info" role="status">
      <p>Busca cancelada. Os dados que você digitou foram mantidos.</p>
    </div>`;
  autor.focus();
}

// Filtra livros no catálogo local
function pesquisar(a, t, i) {
  return catalogo.filter(l =>
    (!a || norm(l.autor).includes(norm(a))) &&
    (!t || norm(l.titulo).includes(norm(t))) &&
    (!i || l.isbn === i)
  );
}

// "Você quis dizer: ..." com opção de negação
function mostrarSugestoes(sugestoes, semResultado = true) {
  const itens = sugestoes.map((s, idx) => `
    <li><button type="button" class="sugestao" data-idx="${idx}">${esc(s.rotulo)}</button></li>`).join("");
  status.innerHTML = `
    <div class="panel" role="status">
      ${semResultado ? "<p><strong>Nenhuma obra encontrada.</strong></p>" : ""}
      <p>Você quis dizer:</p>
      <ul class="sugestoes">${itens}</ul>
      <div class="actions"><button type="button" id="btn-nenhuma">Nenhuma dessas</button></div>
    </div>`;

  status.querySelectorAll(".sugestao").forEach(btn =>
    btn.addEventListener("click", () => aceitarSugestao(sugestoes[Number(btn.dataset.idx)]))
  );
  $("btn-nenhuma").addEventListener("click", recusarSugestoes);
}

// Aplica a sugestão escolhida nos campos e refaz a busca
function aceitarSugestao(s) {
  if (s.autor !== null) autor.value = s.autor;
  if (s.titulo !== null) titulo.value = s.titulo;
  if (s.isbn !== null) isbn.value = formatarIsbn(s.isbn);
  limparErroGeral();
  limparErroIsbn();
  executarBusca();
}

// Nenhuma sugestão serve: limpa o formulário e orienta a digitar de novo
function recusarSugestoes() {
  novaConsulta();
  status.innerHTML = `
    <div class="panel info" role="status">
      <p>Tudo bem! Limpamos os campos. Digite novamente o autor, o título ou o ISBN da obra.</p>
    </div>`;
  autor.focus();
}

function mostrarResultados(lista, a, t, i) {
  if (lista.length === 0) {
    const sugestoes = sugerir(a, t, i);
    if (sugestoes.length) {
      mostrarSugestoes(sugestoes);
      return;
    }
    status.innerHTML = `
      <div class="panel" role="status">
        <p><strong>Nenhuma obra encontrada.</strong></p>
        <p class="hint">Verifique a grafia ou tente buscar com menos campos preenchidos.</p>
        <div class="actions">
          <button type="button" id="btn-ajustar">Ajustar busca</button>
          <button type="button" id="btn-nova">Nova consulta</button>
        </div>
      </div>`;
    $("btn-ajustar").addEventListener("click", () => autor.focus());
    $("btn-nova").addEventListener("click", novaConsulta);
    return;
  }
  const itens = lista.map(l => `
    <li>
      <div class="book-title">${esc(l.titulo)}</div>
      <div class="book-meta">${esc(l.autor)} · ${l.ano} · ISBN ${formatarIsbn(l.isbn)}</div>
    </li>`).join("");
  status.innerHTML = `
    <div class="panel" role="status">
      <p><strong>${lista.length} ${lista.length === 1 ? "obra encontrada" : "obras encontradas"}</strong></p>
      <ul class="results">${itens}</ul>
      <div class="actions"><button type="button" id="btn-nova">Nova consulta</button></div>
    </div>`;
  $("btn-nova").addEventListener("click", novaConsulta);
}

function novaConsulta() {
  form.reset();
  limparErroGeral();
  limparErroIsbn();
  status.innerHTML = "";
  autor.focus();
}

function executarBusca() {
  const a = autor.value, t = titulo.value, i = soDigitos(isbn.value);
  btnPesquisar.disabled = true;
  mostrarCarregando();

  // Simula latência de requisição assíncrona
  timer = setTimeout(() => {
    timer = null;
    btnPesquisar.disabled = false;
    mostrarResultados(pesquisar(a, t, i), a, t, i);
  }, 1200);
}

form.addEventListener("submit", e => {
  e.preventDefault();
  if (timer) return; // Impede submissão duplicada ativa
  status.innerHTML = "";
  if (!validar()) return;
  executarBusca();
});

$("btn-limpar").addEventListener("click", () => {
  if (timer) cancelar();
  novaConsulta();
});
