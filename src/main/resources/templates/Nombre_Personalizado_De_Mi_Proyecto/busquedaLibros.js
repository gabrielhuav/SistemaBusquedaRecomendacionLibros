let id;
let nombre;
let info;

window.onload = async function() {
    let queryString = window.location.search;
    let urlParams = new URLSearchParams(queryString);
    id = urlParams.get('q');
    nombre = urlParams.get('n');
    document.getElementById('idUsuario').value = id;
    document.getElementById('nombre').value = nombre;
    listaHistorial();
}

function BuscarPorTitulo() {
    const titulo = document.getElementById("tituloAbuscar").value;

    fetch("/api/libros/buscarPorTitulo", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            titulo: titulo
        })
    })
    .then(response => response.json())
    .then(data => {
        const tabla = document.getElementById("tabla");
        if (tabla) {
            while (tabla.rows.length > 1) {
                tabla.deleteRow(1);
            }

            data.forEach((libro, i) => {
                const fila = tabla.insertRow();

                const celdaImagen = fila.insertCell();
                celdaImagen.setAttribute("align", "center");
                const img = document.createElement("img");
                img.src = libro.imagen ? `https://covers.openlibrary.org/b/id/${libro.imagen}-M.jpg` : "default_image.jpg";
                img.className = "box";
                celdaImagen.appendChild(img);

                const celdaTitulo = fila.insertCell();
                celdaTitulo.setAttribute("align", "center");
                celdaTitulo.textContent = libro.titulo;

                const celdaAutor = fila.insertCell();
                celdaAutor.setAttribute("align", "center");
                celdaAutor.textContent = libro.nombreAutor ? libro.nombreAutor : "N/A";

                const celdaClaveLibro = fila.insertCell();
                celdaClaveLibro.setAttribute("align", "center");
                celdaClaveLibro.textContent = libro.idLibro ? libro.idLibro : "N/A";

                const celdaClaveAutor = fila.insertCell();
                celdaClaveAutor.setAttribute("align", "center");
                celdaClaveAutor.textContent = libro.idAutor ? libro.idAutor : "N/A";

                const celdaPrimerapublicacion = fila.insertCell();
                celdaPrimerapublicacion.setAttribute("align", "center");
                celdaPrimerapublicacion.textContent = libro.yearP ? libro.yearP : "N/A";

                const celdaBoton = fila.insertCell();
                const boton = document.createElement("button");
                boton.setAttribute("align", "justify");
                boton.setAttribute("onclick", `agregarFavorito(${i})`);
                boton.setAttribute("class", "buttonMIO");
                boton.textContent = "Agregar a favorito";
                celdaBoton.appendChild(boton);
            });
        } else {
            console.error("Elemento con id 'tabla' no encontrado.");
        }
    })
    .catch(error => console.error("Error en la petición:", error));
}

function agregarFavorito(index) {
    const tabla = document.getElementById("tabla");
    const fila = tabla.rows[index + 1]; // +1 porque la primera fila es el encabezado

    const idUsuario = document.getElementById("idUsuario").value;
    const idLibro = fila.cells[3].textContent;
    const titulo = fila.cells[1].textContent;
    const idAutor = fila.cells[4].textContent;
    const nombreAutor = fila.cells[2].textContent;
    const yearP = fila.cells[5].textContent;
    const imagen = fila.cells[0].querySelector("img").src.split('/').pop().split('-')[0];

    fetch("/LibroFavorito", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            idUsuario: idUsuario,
            idLibro: idLibro,
            titulo: titulo,
            idAutor: idAutor,
            nombreAutor: nombreAutor,
            yearP: yearP,
            imagen: imagen
        })
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
    })
    .catch(error => console.error("Error en la petición:", error));
}


function BuscarPorAutor() {
    const autor = document.getElementById("tituloAbuscar").value;

    fetch("/api/libros/buscarPorAutor", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            autor: autor
        })
    })
    .then(response => response.json())
    .then(data => {
        const tabla = document.getElementById("tabla");
        if (tabla) {
            while (tabla.rows.length > 1) {
                tabla.deleteRow(1);
            }

            data.forEach((libro, i) => {
                const fila = tabla.insertRow();

                const celdaImagen = fila.insertCell();
                celdaImagen.setAttribute("align", "center");
                const img = document.createElement("img");
                img.src = libro.imagen ? `https://covers.openlibrary.org/b/id/${libro.imagen}-M.jpg` : "default_image.jpg";
                img.className = "box";
                celdaImagen.appendChild(img);

                const celdaTitulo = fila.insertCell();
                celdaTitulo.setAttribute("align", "center");
                celdaTitulo.textContent = libro.titulo;

                const celdaAutor = fila.insertCell();
                celdaAutor.setAttribute("align", "center");
                celdaAutor.textContent = libro.nombreAutor ? libro.nombreAutor : "N/A";

                const celdaClaveLibro = fila.insertCell();
                celdaClaveLibro.setAttribute("align", "center");
                celdaClaveLibro.textContent = libro.idLibro ? libro.idLibro : "N/A";

                const celdaClaveAutor = fila.insertCell();
                celdaClaveAutor.setAttribute("align", "center");
                celdaClaveAutor.textContent = libro.idAutor ? libro.idAutor : "N/A";

                const celdaPrimerapublicacion = fila.insertCell();
                celdaPrimerapublicacion.setAttribute("align", "center");
                celdaPrimerapublicacion.textContent = libro.yearP ? libro.yearP : "N/A";

                const celdaBoton = fila.insertCell();
                const boton = document.createElement("button");
                boton.setAttribute("align", "justify");
                boton.setAttribute("onclick", `prueba(${i})`);
                boton.setAttribute("class", "buttonMIO");
                boton.textContent = "Agregar a favorito";
                celdaBoton.appendChild(boton);
            });
        } else {
            console.error("Elemento con id 'tabla' no encontrado.");
        }
    })
    .catch(error => console.error("Error en la petición:", error));
}

function BuscarPorPalabra() {
    const palabra = document.getElementById("tituloAbuscar").value;

    fetch("/api/libros/buscarPorPalabra", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            palabra: palabra
        })
    })
    .then(response => response.json())
    .then(data => {
        const tabla = document.getElementById("tabla");
        if (tabla) {
            while (tabla.rows.length > 1) {
                tabla.deleteRow(1);
            }

            data.forEach((libro, i) => {
                const fila = tabla.insertRow();

                const celdaImagen = fila.insertCell();
                celdaImagen.setAttribute("align", "center");
                const img = document.createElement("img");
                img.src = libro.imagen ? `https://covers.openlibrary.org/b/id/${libro.imagen}-M.jpg` : "default_image.jpg";
                img.className = "box";
                celdaImagen.appendChild(img);

                const celdaTitulo = fila.insertCell();
                celdaTitulo.setAttribute("align", "center");
                celdaTitulo.textContent = libro.titulo;

                const celdaAutor = fila.insertCell();
                celdaAutor.setAttribute("align", "center");
                celdaAutor.textContent = libro.nombreAutor ? libro.nombreAutor : "N/A";

                const celdaClaveLibro = fila.insertCell();
                celdaClaveLibro.setAttribute("align", "center");
                celdaClaveLibro.textContent = libro.idLibro ? libro.idLibro : "N/A";

                const celdaClaveAutor = fila.insertCell();
                celdaClaveAutor.setAttribute("align", "center");
                celdaClaveAutor.textContent = libro.idAutor ? libro.idAutor : "N/A";

                const celdaPrimerapublicacion = fila.insertCell();
                celdaPrimerapublicacion.setAttribute("align", "center");
                celdaPrimerapublicacion.textContent = libro.yearP ? libro.yearP : "N/A";

                const celdaBoton = fila.insertCell();
                const boton = document.createElement("button");
                boton.setAttribute("align", "justify");
                boton.setAttribute("onclick", `prueba(${i})`);
                boton.setAttribute("class", "buttonMIO");
                boton.textContent = "Agregar a favorito";
                celdaBoton.appendChild(boton);
            });
        } else {
            console.error("Elemento con id 'tabla' no encontrado.");
        }
    })
    .catch(error => console.error("Error en la petición:", error));
}

function prueba(i) {
    document.getElementById("Titulo").value = info.docs[i].title;
    document.getElementById("Autor").value = info.docs[i].author_name[0];
    document.getElementById("ClaveLibro").value = info.docs[i].key;
    document.getElementById("ClaveAutor").value = info.docs[i].author_key[0];
    document.getElementById("Primerapublicacion").value = info.docs[i].first_publish_year;
    document.getElementById("imagen").value = info.docs[i].cover_i;
}

function listaHistorial() {
    fetch("/api/libros/favoritos/" + id)
    .then(response => response.json())
    .then(data => {
        let tabla = document.getElementById("tablaH");
        // ...
    })
    .catch(error => console.error("Error en la petición:", error));
}

function redireccionarMenu() {
    let URL = '/Nombre_Personalizado_De_Mi_Proyecto/menu.html?q=' + id + '&n=' + nombre;
    location.href = URL;
}