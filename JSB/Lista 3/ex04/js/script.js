btn = document.getElementById("exibeNota"); 
textNota1 = document.getElementById("n1");
textNota2 = document.getElementById("n2"); 


btn.addEventListener("click", exibirClick);

function exibirClick (){
    if(textNota1.value.trim() == ""){
        alert("Campo do primeiro bimestre vazio.");
    }
    else if(textNota2.value.trim() == ""){
        alert("Campo do segundo biimestre vazio.");
    }

    else if(isNaN(textNota1.value) || isNaN(textNota2.value)){
        alert("Entre com uma nota válida.");
    }

    else{
        let n1 = Number(textNota1.value);
        let n2 = Number(textNota2.value);
        let soma = n1 + n2; 
         
        if(soma >= 60){
            alert("Aprovado! Parabéns"); 
        }
        
        else{
            alert("Reprovado.");
        }
    }
}