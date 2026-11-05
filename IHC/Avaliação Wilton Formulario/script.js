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
  { titulo: "Crimem e Castigo", autor: "Fiódor Dostoiévski", isbn: "9788573266467", ano: 1866 },
  { titulo: "Os Irmãos Karamázov", autor: "Fiódor Dostoiévski", isbn: "9788573265002", ano: 1880 },
  { titulo: "Metamorfose", autor: "Franz Kafka", isbn: "9788535900897", ano: 1915 },
  { titulo: "O Processo", autor: "Franz Kafka", isbn: "9788535912449", ano: 1925 },
  { titulo: "O Apanhador no Campo de Centeio", autor: "J.D. Salinger", isbn: "9788571236806", ano: 1951 },
  { titulo: "Ensaio sobre a Cegueira", autor: "José Saramago", isbn: "9788535900712", ano: 1995 },
  { titulo: "O Ano da Morte de Ricardo Reis", autor: "José Saramago", isbn: "9788535901122", ano: 1984 },
  { titulo: "Orgulho e Preconceito", autor: "Jane Austen", isbn: "9788525422897", ano: 1813 },
  { titulo: "O Conde de Monte Cristo", autor: "Alexandre Dumas", isbn: "9788537808931", ano: 1844 }
];

const $ = id => document.getElementById(id);
const form = $("form"), autor = $("autor"), titulo = $("titulo"), isbn = $("isbn");
const status = $("status"), btnPesquisar = $("btn-pesquisar");
let timer = null;

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
    isbn.focus();
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

function mostrarResultados(lista) {
  if (lista.length === 0) {
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

form.addEventListener("submit", e => {
  e.preventDefault();
  if (timer) return; // Impede submissão duplicada ativa
  if (!validar()) { status.innerHTML = ""; return; }

  const a = autor.value, t = titulo.value, i = soDigitos(isbn.value);
  btnPesquisar.disabled = true;
  mostrarCarregando();

  // Simula latência de requisição assíncrona
  timer = setTimeout(() => {
    timer = null;
    btnPesquisar.disabled = false;
    mostrarResultados(pesquisar(a, t, i));
  }, 1200);
});

$("btn-limpar").addEventListener("click", () => {
  if (timer) cancelar();
  novaConsulta();
});