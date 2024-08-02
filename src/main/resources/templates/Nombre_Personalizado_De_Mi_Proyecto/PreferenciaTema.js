let select = document.getElementById('listaDinamicaTemas');

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

// Function to handle the change event of the select element for gusta tema
function cargaSelectGusto() {
    const idUsuarioInput = document.getElementById('idUsuario');
    const idUsuario = idUsuarioInput.value;
    console.log('idUsuario:', idUsuario); // Check if idUsuario is being populated correctly
    if (!idUsuario) {
        console.error('No idUsuario provided');
        return;
    }
    fetch(`/Temas/historial/${idUsuario}`)
       .then(response => response.text())
       .then(data => {
            const jsonData = JSON.parse(data);
            const select = document.getElementById('selectGusto');
            select.innerHTML = ''; // clear the select options
            jsonData.forEach(gusto => {
                const option = document.createElement('option');
                option.value = gusto.tema;
                option.text = gusto.tema;
                select.appendChild(option);
            });
        })
       .catch(error => console.error('Error:', error));
}

let currentSelection = null;

function cambioSelectGusto() {
    const idUsuarioInput = document.getElementById('idUsuario');
    const idUsuario = idUsuarioInput.value;
    console.log('idUsuario:', idUsuario); // Check if idUsuario is being populated correctly
    if (!idUsuario) {
        console.error('No idUsuario provided');
        return;
    }
    const select = document.getElementById('selectGusto');
    currentSelection = select.options[select.selectedIndex].text;
    fetch(`/Temas/historial/${idUsuario}`)
       .then(response => response.text())
       .then(data => {
            const jsonData = JSON.parse(data);
            select.innerHTML = ''; // clear the select options
            jsonData.forEach(gusto => {
                const option = document.createElement('option');
                option.value = gusto.tema;
                option.text = gusto.tema;
                select.appendChild(option);
            });
            // Restaurar la selección actual
            select.value = currentSelection;
        })
       .catch(error => console.error('Error:', error));
}

function registrarGustoFavoritoUsuario() {
    let idUsuario = document.getElementById("idUsuario").value;
    let idTema = document.getElementById("idTema").value;
    let tema = document.getElementById("tema").value;

    fetch("/Temas", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: new URLSearchParams({
            idUsuario: idUsuario,
            idTema: idTema,
            tema: tema
        })
    })
    .then(response => response.text())
    .then(data => {
        alert("Tema agregado correctamente!");
        cargaSelectGusto();
    })
    .catch(error => console.error("Error en la petición:", error));
}

function redireccionarMenu() {
    window.location.href = "Menu.html";
}

document.addEventListener("DOMContentLoaded", function() {
    cargaTemas();
    cargaSelectGusto();
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
    cargaSelectGusto();
    console.log("Ya cargo la página...");
}