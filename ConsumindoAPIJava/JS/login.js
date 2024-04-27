async function enviar(){
    var usuario = document.getElementById("usuario").value;
    var senha = document.getElementById("password").value;

    var update = {
        user: usuario,
        password: senha
    }

    const options = {
        method: "post",
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(update)
    }

    fetch(`http://localhost:8080/usuarios/login?user=${update.user}&password=${update.password}`, options)
    .then(response => response.json())
    .then(token => localStorage.setItem("token", token.token))
    .catch(error => console.error('Erro:', error));
}