let id;
let nombre;

window.onload = async function() {
    let queryString = window.location.search;
    let urlParams = new URLSearchParams(queryString);
    id = urlParams.get('q');
    nombre = urlParams.get('n');
    console.log(id); 
    document.getElementById('idUsuario').value = id;
    llenarSelect();
    llenarListaRecomendacion();
    
    let a = urlParams.get('a');
    document.getElementById('idLibroR').innerHTML = a;
    
    let b = urlParams.get('b');
    document.getElementById("tituloR").innerHTML = b;  
    
    let c = urlParams.get('c');
    document.getElementById("idAutorR").innerHTML = c;
    
    let d = urlParams.get('d');
    document.getElementById("nombreR").innerHTML = d;   
}

function llenarSelect() {
    let idUsuario = document.getElementById("idUsuario").value;
    const url = 'http://localhost:8080/AutorFavorito/' + idUsuario;
    
    fetch(url).then(function(response) {
        response.json().then(function(data) {
            console.log(data);          
            
            // Recorrer un array de JSON 
            for (let i = 0; i < data.length; i++) {
                let select = document.getElementsByName("cliente")[0];
                let option = document.createElement("option");
                option.text = data[i].nombreAutor;             
                option.value = data[i].idAutor;
                select.add(option);  
            }
        });
    }).catch(function(error) {
        console.log('Fetch Error:', error);
    });
}

function llenarListaRecomendacion() {
    let idUsuario = document.getElementById("idUsuario").value;
    const url = 'http://localhost:8080/AutorFavorito/lista/' + idUsuario;
    
    fetch(url).then(function(response) {
        response.json().then(function(data) {
            console.log(data);          
           
            let lista = document.getElementById("tabla"); 
            // Recorrer un array de JSON 
            for (let i = 0; i < data.length; i++) {
                let titulo = data[i].titulo;  
                let autor = data[i].nombreAutor;  
                let idLibro = data[i].idLibro;
                let idAutor = data[i].idAutor;
                let imagen = data[i].imagen;
                let fechaRecomendacion = data[i].fechaRecomendacion;
                
                // Llenamos la tabla
                let hilera = document.createElement("tr");
                
                let celdatitulo = document.createElement("td");
                celdatitulo.setAttribute("align", "center");
                let textoCeldatitulo = document.createTextNode(titulo);
                celdatitulo.appendChild(textoCeldatitulo);
                hilera.appendChild(celdatitulo);
                
                let celdaautor = document.createElement("td");
                celdaautor.setAttribute("align", "center");
                let textoCeldaautor = document.createTextNode(autor);
                celdaautor.appendChild(textoCeldaautor);
                hilera.appendChild(celdaautor);
                
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
                
                let celdafechaRecomendacion = document.createElement("td");
                celdafechaRecomendacion.setAttribute("align", "center");
                let textoCeldafechaRecomendacion = document.createTextNode(fechaRecomendacion);
                celdafechaRecomendacion.appendChild(textoCeldafechaRecomendacion);
                hilera.appendChild(celdafechaRecomendacion);
                
                // Cargar imagen
                let celdaIMG = document.createElement("img");
                celdaIMG.setAttribute("align", "right");
                celdaIMG.setAttribute("id", "img");
                celdaIMG.setAttribute("class", "box1");
                let img = 'https://covers.openlibrary.org/b/id/' + imagen + '-L.jpg';

                if (img === undefined) {
                    celdaIMG.setAttribute("src", document.getElementById("img1").src);
                } else {
                    celdaIMG.setAttribute("src", img);
                }
                
                hilera.appendChild(celdaIMG);
                
                lista.appendChild(hilera);
            }    
        });
    }).catch(function(error) {
        console.log('Fetch Error:', error);
    });
}

function recuperarImagen(idAutor) {
    const url = 'https://openlibrary.org/authors/' + idAutor + '.json';

    fetch(url)
    .then(response => response.json())
    .then(data => {
        let idIMG = data.photos[0];
        console.log(idIMG); 
        const img = 'https://covers.openlibrary.org/a/id/' + idIMG + '-M.jpg';
        document.getElementById("img").src = img;

        // Llenar el formulario con la información del autor
        document.getElementById("idAutor").value = data.key;
        document.getElementById("nombreAutor").value = data.name;

        // Buscar un libro del autor
        buscarLibro(data.key, data.name);
    })
    .catch(err => console.log(err));
}

function buscarLibro(idAutor, nombreAutor) {
    const url = 'https://openlibrary.org/search.json?author=' + nombreAutor + '&fields=key,title,author_name,editions';

    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.docs.length > 0) {
                let libro = data.docs[0];
                let edicion = libro.editions.docs[0];

                // Llenar el formulario con la información del libro
                document.getElementById("idLibroR").innerHTML = edicion.key;
                document.getElementById("tituloR").innerHTML = edicion.title;
                document.getElementById("idAutorR").innerHTML = idAutor; // Usar el mismo ID del autor con el que se hizo la búsqueda
                document.getElementById("nombreR").innerHTML = libro.author_name.join(', '); // Incluir todos los autores

                // Obtener el enlace de la portada del libro
                const libroUrl = 'https://openlibrary.org' + edicion.key;
                const xhr = new XMLHttpRequest();
                xhr.open('GET', '/busquedaAutor/obtenerPortadaLibro?libroUrl=' + libroUrl, true);
                xhr.onload = function() {
                    if (xhr.status === 200) {
                        const imgUrl = xhr.responseText;
                        document.getElementById("imgLibro").src = imgUrl;
                        console.log('Si se encontraron libros para este autor x1' + imgUrl);
                    }
                };
                console.log('Si se encontraron libros para este autor x2');
                xhr.send();
            } else {
                console.log('No se encontraron libros para este autor x2');
            }
        })
        .catch(err => console.log(err));
}

function cambioSelect() {
    let select = document.getElementById('cliente');
    let autor = select.options[select.selectedIndex].text;
    document.getElementById('nombreAutor').value = autor;
    
    let idAutor = document.getElementById('cliente').value;
    document.getElementById('idAutor').value = idAutor;  

    // Llamar a la función para recuperar la información del autor
    recuperarImagen(idAutor);
}

function redireccionarMenu() {
    let URL = 'http://localhost:8080/Nombre_Personalizado_De_Mi_Proyecto/menu.html?q=' + id + '&n=' + nombre;
    location.href = URL;
}
