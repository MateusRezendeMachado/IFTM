btn = document.getElementById("exibeDobro"); 
textValor = document.getElementById("textVl");
textDobro = document.getElementById("textDobro");

btn.addEventListener("click", exibirClick);

function exibirClick(){
     if(textValor.value.trim() == ""){
        alert("Entre com um valor.");
    }
    
    else if(isNaN(textValor.value)){
        alert("Por favor, entre com um número. ")
    }
    

    else{
    textDobro.value = textValor.value * 2;
    }
}