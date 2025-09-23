btn = document.getElementById("btnEnviar"); 
textValor = document.getElementById("textVl");

btn.addEventListener("click", exibirClick);

function exibirClick(){
     if(textValor.value.trim() == ""){
        alert("Preencha alguma coisa.");
    }
    

    else{
    alert(textValor.value);
    }
}