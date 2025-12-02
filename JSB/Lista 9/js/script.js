window.addEventListener("DOMContentLoaded", function(){
    let dataNasc = document.getElementById("dataNasc")
    let btnDataNasc = document.getElementById("btnDataNasc")
    let cpf = document.getElementById("cpf")
    let btnCpf = document.getElementById("btnCpf")
    let matricula = document.getElementById("matricula")
    let btnMatricula = document.getElementById("btnMatricula")
    let disciplina = document.getElementById("disciplina")
    let btnDisciplina = document.getElementById("btnDisciplina")
    let disciplinaV2 = document.getElementById("disciplinaV2")
    let btnDisciplinaV2 = document.getElementById("btnDisciplinaV2")
    let disciplinaV3 = document.getElementById("disciplinaV3")
    let btnDisciplinaV3 = document.getElementById("btnDisciplinaV3")
    let disciplinaV4 = document.getElementById("disciplinaV4")
    let btnDisciplinaV4 = document.getElementById("btnDisciplinaV4")
    let campus = document.getElementById("campus")
    let btnCampus = document.getElementById("btnCampus")
    let telefone = document.getElementById("telefone")
    let btnTelefone = document.getElementById("btnTelefone")
    let telefoneV2 = document.getElementById("telefoneV2")
    let btnTelefoneV2 = document.getElementById("btnTelefoneV2")
    let altura = document.getElementById("altura")
    let btnAltura = document.getElementById("btnAltura")
    let faturamento = document.getElementById("faturamento")
    let btnFaturamento = document.getElementById("btnFaturamento")
    let cronometro = document.getElementById("cronometro")
    let btnCronometro = document.getElementById("btnCronometro")
    let senha = document.getElementById("senha")
    let btnSenha = document.getElementById("btnSenha")




    btnDataNasc.addEventListener("click", function(){
        let padrao = /^(\d{2}\/\d{2}\/\d{4}|\d{2}\/\d{2}\/\d{2})$/;

        (padrao.test(dataNasc.value))?alert("Valido"):alert("Invalido")
    })

    btnCpf.addEventListener("click", function(){
        let padrao = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;

        (padrao.test(cpf.value))?alert("Valido"):alert("Invalido")
    })

    btnMatricula.addEventListener("click", function(){
        let padrao = /^IFTM-\d{3}\/\d{3}\-[a-zA-Z]{2}$/;

        (padrao.test(matricula.value))?alert("Valido"):alert("Invalido")
    })

    btnDisciplina.addEventListener("click", function(){
        let padrao = /^(M|m)(T|t)-\d{2}\.\d{3}-(I|i)(F|f)(T|t)(M|m)$/;

        (padrao.test(disciplina.value))?alert("Valido"):alert("Invalido")
    })

    btnDisciplinaV2.addEventListener("click", function(){
        let padrao = /^MT-\d{2}\.\d{3}-(I|i)(F|f)(T|t)(M|m)$/;

        (padrao.test(disciplinaV2.value))?alert("Valido"):alert("Invalido")
    })

    btnDisciplinaV3.addEventListener("click", function(){
        let padrao = /^(M|m)\s?(T|t)-\d{2}\.\d{3}-(I|i)\s?(F|f)\s?(T|t)\s?(M|m)$/;

        (padrao.test(disciplinaV3.value))?alert("Valido"):alert("Invalido")
    })

    btnDisciplinaV4.addEventListener("click", function(){
        let padrao = /^(M|m)\s?(T|t)-\d{2}\.\d{3}-(I|i)\s?(F|f)\s?(T|t)\s?(M|m)[A-Z]$/;

        (padrao.test(disciplinaV4.value))?alert("Valido"):alert("Invalido")
    })

    btnCampus.addEventListener("click", function(){
        let padrao = /^(IFTM campus Uberlândia|IFTM campus Uberlândia Centro)$/;

        (padrao.test(campus.value))?alert("Valido"):alert("Invalido")
    })

    btnTelefone.addEventListener("click", function(){
        let padrao = /^\+\d{2}\(\d{2}\)\d{5}-\d{4}$/;

        (padrao.test(telefone.value))?alert("Valido"):alert("Invalido")
    })

    btnTelefoneV2.addEventListener("click", function(){
        let padrao = /^\(\d{2,3}\)\d{5}-\d{4}$/;

        (padrao.test(telefoneV2.value))?alert("Valido"):alert("Invalido")
    })

    btnAltura.addEventListener("click", function(){
        let padrao = /^\d{1,2}([.,]\d{1,5})?$/;

        (padrao.test(altura.value))?alert("Valido"):alert("Invalido")
    })

    btnFaturamento.addEventListener("click", function(){
        let padrao = /^R\$\s?(?:\d{1,3}(?:\.\d{3})*|\d+),\d{1,2}$/;

        (padrao.test(faturamento.value))?alert("Valido"):alert("Invalido")
    })

    btnCronometro.addEventListener("click", function(){
        let padrao = /^(?:[01]\d|2[0-3]):[0-5]\d:[0-5]\d:\d{2}$/;

        (padrao.test(cronometro.value))?alert("Valido"):alert("Invalido")
    })

    btnSenha.addEventListener("click", function(){
        let padrao = /^(?:[A-Za-z0-9._-]{5,}&[A-Pa-p]+\.[aeiou]+(?:\.[A-Za-z0-5]+)?-[^0-9]+,[^A-Za-z0-9]{2}\.[^ab01]+)$/;

        (padrao.test(senha.value))?alert("Valido"):alert("Invalido")
    })
})