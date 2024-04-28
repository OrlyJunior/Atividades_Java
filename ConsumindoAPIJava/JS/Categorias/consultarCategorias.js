window.onload = consultarCategorias;

async function consultarCategorias(){
    console.log(1)
    const options = {
        method: "get"
    }

    await fetch("http://localhost:8080/categorias", options)
    .then(data => data.json())
    .then(element => element.forEach(element => {
        document.getElementById("tableCategorias").insertAdjacentHTML("beforeend", `<td>${element.id}</td>
                                                                                     <td>${element.nome}</td>`)
    }))
}