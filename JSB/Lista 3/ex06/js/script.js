let n1 =  document.getElementById("n1");
let n2 =  document.getElementById("n2");
let result = document.getElementById("result");


function verificaNum(){

    let v1 = Number(n1.value);
    let v2 = Number(n2.value);

    if(n1.value.trim() == "" || n2.value.trim() == ""){
        alert("Preencha ambos os campos!")
        return false;
    }
    
    if(isNaN(v1) || isNaN (v2)){
        alert("Digite valores válidos!");
        return false;
    }

    return true;
}

document.getElementById("exibeSoma").addEventListener("click", ()=>{
    if(verificaNum()){
        let v1  = Number(n1.value);
        let v2 =  Number(n2.value); 

        result.value = v1 + v2;
    }
});

document.getElementById("exibeSub").addEventListener("click", ()=>{
    if(verificaNum()){
        let v1  = Number(n1.value);
        let v2 =  Number(n2.value); 

        result.value = v1 - v2;
    }
});

document.getElementById("exibeMult").addEventListener("click", ()=>{
    if(verificaNum()){
        let v1  = Number(n1.value);
        let v2 =  Number(n2.value); 

        result.value = v1 * v2;
    }
});

document.getElementById("exibeDiv").addEventListener("click", ()=>{
    if(verificaNum()){
        let v1  = Number(n1.value);
        let v2 =  Number(n2.value); 

        if(v2 === 0){
            alert("Não é possível dividir por zero!");
        }
        else{
        result.value = v1 / v2;
        }
    }
});
