import { consultarUser } from './storage.js';

window.addEventListener("DOMContentLoaded", () => {
    const CHAVE = "usuarios";

    let btnEntrar = document.getElementById("btnEntrar");

    btnEntrar.addEventListener("click", function () {
        let user = document.getElementById("usr").value;
        let pwd = document.getElementById("pwd").value;

        if (user === "" || pwd === "") {
            alert("Preencha todos os campos!");
            return;
        }

        let credenciais = { usuario: user, senha: pwd };
        let valido = consultarUser(CHAVE, credenciais);

        if (valido) {
            alert("Bem-vindo, " + user + "!");
            localStorage.setItem("logado", user);

            window.location.href = "index.html";

        } else {
            alert("Usuário ou senha incorretos!");
        }
    });
});
