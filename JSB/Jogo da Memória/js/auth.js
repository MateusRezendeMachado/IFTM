window.addEventListener("DOMContentLoaded", () => {
    const usuarioLogado = localStorage.getItem("logado");
    const audioAmbiente = document.getElementById("audioAmbiente");
    if (!usuarioLogado) {
        alert("Você precisa fazer login para acessar o jogo!");
        window.location.href = "login.html";
    }
});
