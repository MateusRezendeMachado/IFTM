export function cadastraUser(key, novoUsuario) {
    let usuarios = localStorage.getItem(key);

    if (usuarios == null) {
        // cria um array com o primeiro usuário
        usuarios = [novoUsuario];
    } else {
        // transforma o que veio do localStorage em array
        usuarios = JSON.parse(usuarios);

        // impede duplicado
        let existe = usuarios.some(u => u.usuario === novoUsuario.usuario);
        if (existe) {
            alert("Usuário já cadastrado!");
            return;
        }

        usuarios.push(novoUsuario);
    }

    localStorage.setItem(key, JSON.stringify(usuarios));
    alert("Usuário cadastrado com sucesso!");
}

export function consultarUser(key, credenciais) {
    let usuarios = JSON.parse(localStorage.getItem(key));

    if (!usuarios) {
        alert("Nenhum usuário cadastrado!");
        return false;
    }

    for (let i = 0; i < usuarios.length; i++) {
        if (
            usuarios[i].usuario === credenciais.usuario &&
            usuarios[i].senha === credenciais.senha
        ) {
            return true;
        }
    }

    return false;
}
