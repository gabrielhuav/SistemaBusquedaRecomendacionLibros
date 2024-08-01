let select = document.getElementById('listaDinamicaTemas');

// Function to populate the select options dynamically
function cargaSelect() {
    fetch('/Temas')
        .then(response => response.json())
        .then(data => {
            const select = document.getElementById('cliente');
            select.innerHTML = ''; // clear the select options
            data.forEach(tema => {
                const option = document.createElement('option');
                option.value = tema.id;
                option.text = tema.traduccion;
                select.appendChild(option);
            });
        })
        .catch(error => console.error('Error:', error));
}

// Function to handle the change event of the select element
function cambioSelect() {
    var select = document.getElementById('cliente');
    var idTema = select.value;
    var tema = select.options[select.selectedIndex].text;
    document.getElementById('idTema').value = idTema;
    document.getElementById('tema').value = tema;
}

//// Function to handle the change event of the select element for gusta tema
//function cambioSelectGusto() {
//    var select = document.getElementById('selectGusto');
//    var idTema = select.value;
//    var tema = select.options[select.selectedIndex].text;
//    document.getElementById('claveTema').value = idTema;
//    document.getElementById('temaUsuario').value = tema;
//}

function recuperarImagen(idLibro) {
    const url = 'https://openlibrary.org' + idLibro + '.json' ;

    fetch(url)
   .then(response => response.json() )
   .then(data => {
        let idIMG = data.covers[0] ;
        console.log(idIMG); 
        const img = 'https://covers.openlibrary.org/b/id/' + idIMG + '-L.jpg' ;
        document.getElementById("img").src = img ;
    })
   .catch(err=>console.log(err))
}

function cambioSelect() {
    let select = document.getElementById('listaDinamicaTemas');
    let tema = select.options[select.selectedIndex].text;
    document.getElementById('tema').value = tema;
    
    let idtema = select.value;
    document.getElementById('idTema').value = idtema;   
}

function cargaTemas() {
    fetch('/Temas')
   .then(response => response.json())
   .then(data => {
        const select = document.getElementById('listaDinamicaTemas');
        select.innerHTML = '<option>Selecciona un tema</option>';
        data.forEach(tema => {
            const option = document.createElement('option');
            option.value = tema.clave;
            option.text = tema.traduccion;
            select.appendChild(option);
        });
    })
   .catch(error => console.error('Error:', error));
}

function redireccionarMenu() {
    window.location.href = "Menu.html";
}

document.addEventListener("DOMContentLoaded", function() {
    cargaTemas();
});

window.onload = async function() {
    let queryString = window.location.search;
    let urlParams = new URLSearchParams(queryString);
    id = urlParams.get('q');
    console.log("El ID es:", id);
    nombre = urlParams.get('n');
    console.log(id); 
    document.getElementById('idUsuario').value = id;
    document.getElementById('idUsuario1').value = id;
    cargaSelect;
    llenarListaRecomendacion();
    
    let a = urlParams.get('a');
    document.getElementById("tituloR").innerHTML = a; 
    
    let b = urlParams.get('b');
    document.getElementById("nombreR").innerHTML = b;  
    
    let c = urlParams.get('c');
    document.getElementById('idLibroR').innerHTML = c;
   
    let d = urlParams.get('d');
    document.getElementById("idAutorR").innerHTML= d;
    
    let e = urlParams.get('e');
    document.getElementById("temaR").innerHTML= e;
    recuperarImagen(c);
    console.log("Ya cargo")
}