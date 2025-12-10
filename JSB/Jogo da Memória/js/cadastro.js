import { cadastraUser } from './storage.js';

window.addEventListener("DOMContentLoaded", () => {
    const CHAVE = "usuarios";

    let btnCadastrar = document.getElementById("btnCadastrar");

    btnCadastrar.addEventListener("click", function () {
        let user = document.getElementById("usr").value;
        let pwd = document.getElementById("pwd").value;
        let pwdconfirm = document.getElementById("pwdconfirm").value;

        if (user === "" || pwd === "" || pwdconfirm === "") {
            alert("Preencha todos os campos!");
            return;
        }

        if (pwd !== pwdconfirm) {
            alert("As senhas não coincidem!");
            return;
        }

        let novoUsuario = { usuario: user, senha: pwd };
        cadastraUser(CHAVE, novoUsuario);

        alert("Cadastro realizado com sucesso! Agora faça login.");
        window.location.href = "login.html";
    });
});
