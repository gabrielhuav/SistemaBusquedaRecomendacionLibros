let id;
let nombre;

function llenarSelectTemas() {
    const url = '/Temas';

    fetch(url)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log(data);

            //Recorrer un array de JSON 
            for (let i = 0; i < data.length; i++) {
                let select = document.getElementsByName("cliente")[0];
                let option = document.createElement("option");
                option.text = data[i].traduccion;             
                option.value = data[i].clave;
                select.add(option);  
            }
        })
        .catch(function (error) {
            console.log('Fetch Error:', error);
        });
}

function llenarSelectGustoTemas() {
    const url = '/Temas/' + id;

    fetch(url)
        .then(function (response) {
            return response.json();
        })
        .then(function (tema) {
            console.log(tema);          

            //Recorrer un array de JSON 
            for (let i = 0; i < tema.length; i++) {
                let select = document.getElementsByName("selectGusto")[0];
                let option = document.createElement("option");
                option.text = tema[i].tema;             
                option.value = tema[i].idtema;
                select.add(option);  
            }
        })
        .catch(function (error) {
            console.log('Fetch Error:', error);
        });
}

function llenarListaRecomendacion() {
    const url = '/Temas/lista/' + id;

    fetch(url)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log(data);          

            let lista = document.getElementById("tabla"); 
            //Recorrer un array de JSON 
            for (let i = 0; i < data.length; i++) {
                //let select = document.getElementsByName("cliente")[0];
                //let option = document.createElement("option");
                let titulo = data[i].titulo;
                let nombreAutor = data[i].nombreAutor;
                let tema = data[i].tema;
                let idLibro = data[i].idLibro;
                let idAutor = data[i].idAutor;
                let imagen = data[i].imagen;
                let fechaRecomendacion = data[i].fechaRecomendacion;

                //Llenamos la tabla
                let hilera = document.createElement("tr");

                let celdatitulo = document.createElement("td");
                celdatitulo.setAttribute("align", "center");
                let textoCeldatitulo = document.createTextNode(titulo);
                celdatitulo.appendChild(textoCeldatitulo);
                hilera.appendChild(celdatitulo);

                let celdanombreAutor = document.createElement("td");
                celdanombreAutor.setAttribute("align", "center");
                let textoCeldanombreAutor = document.createTextNode(nombreAutor);
                celdanombreAutor.appendChild(textoCeldanombreAutor);
                hilera.appendChild(celdanombreAutor);

                let celdatema = document.createElement("td");
                celdatema.setAttribute("align", "center");
                let textoCeldatema = document.createTextNode(tema);
                celdatema.appendChild(textoCeldatema);
                hilera.appendChild(celdatema);
                let celdaidLibro = document.createElement("td");
                celdaidLibro.setAttribute("align", "center");
                let textoCeldaidLibro = document.createTextNode(idLibro);
                celdaidLibro.appendChild(textoCeldaidLibro);
                hilera.appendChild(celdaidLibro);

                let celdaidAutor = document.createElement("td");
                celdaidAutor.setAttribute("align", "center");
                let textoCeldaidAutor = document.createTextNode(idAutor);
                celdaidAutor.appendChild(textoCeldaidAutor);
                hilera.appendChild(celdaidAutor);

                let celdaImagen = document.createElement("td");
                celdaImagen.setAttribute("align", "center");
                let textoCeldaImagen = document.createTextNode(imagen);
                celdaImagen.appendChild(textoCeldaImagen);
                hilera.appendChild(celdaImagen);

                let celdafechaRecomendacion = document.createElement("td");
                celdafechaRecomendacion.setAttribute("align", "center");
                let textoCeldafechaRecomendacion = document.createTextNode(fechaRecomendacion);
                celdafechaRecomendacion.appendChild(textoCeldafechaRecomendacion);
                hilera.appendChild(celdafechaRecomendacion);

                lista.appendChild(hilera);
            }
        })
        .catch(function (error) {
            console.log('Fetch Error:', error);
        });
}

function postNuevoCliente() {
    const tema = document.getElementsByName("cliente")[0].value;
    const idUsuario = id;

    const url = '/Temas';

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `tema=${tema}&idUsuario=${idUsuario}`
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log(data);
            window.location.href = `/Nombre_Personalizado_De_Mi_Proyecto/PreferenciaTema.html?q=${idUsuario}`;
        })
        .catch(function (error) {
            console.log('Fetch Error:', error);
        });
}

function postLogin() {
    const claveTema = document.getElementsByName("selectGusto")[0].value;
    const idUsuario = id;
    const temaUsuario = document.getElementsByName("cliente")[0].value;

    const url = '/Temas/registraGusto';

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `claveTema=${claveTema}&idUsuario=${idUsuario}&temaUsuario=${temaUsuario}`
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            console.log(data);
            const a = data.titulo.replaceAll(" ", "+");
            const b = data.nombreAutor.replaceAll(" ", "+");
            const c = data.idLibro.replaceAll(" ", "+");
            const d = data.idAutor.replaceAll(" ", "+");
            const e = temaUsuario.replaceAll(" ", "+");
            window.location.href = `/Nombre_Personalizado_De_Mi_Proyecto/PreferenciaTema.html?q=${idUsuario}&a=${a}&b=${b}&c=${c}&d=${d}&e=${e}`;
        })
        .catch(function (error) {
            console.log('Fetch Error:', error);
        });
}