document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('loginForm');
    const loginInput = document.getElementById('login');
    const senhaInput = document.getElementById('senha');
    const confirmarSenhaInput = document.getElementById('confirmarSenha');
    const limparBtn = document.getElementById('limpar');
    const mensagemDiv = document.getElementById('mensagem');
    
    
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        validarFormulario();
    });
    
   
    limparBtn.addEventListener('click', function() {
        loginInput.value = '';
        senhaInput.value = '';
        confirmarSenhaInput.value = '';
        mensagemDiv.textContent = '';
        mensagemDiv.style.color = '';
    });
    
    function validarFormulario() {
       
        if (loginInput.value.trim() === '') {
            mensagemDiv.textContent = 'Erro: O campo Login deve ser preenchido.';
            mensagemDiv.style.color = 'red';
            return;
        }
        
        
        if (senhaInput.value !== confirmarSenhaInput.value) {
            mensagemDiv.textContent = 'Erro: As senhas não coincidem.';
            mensagemDiv.style.color = 'red';
            senhaInput.value = '';
            confirmarSenhaInput.value = '';
            return;
        }
        
        
        mensagemDiv.textContent = 'Todos os campos foram digitados corretamente.';
        mensagemDiv.style.color = 'green';
    }
});