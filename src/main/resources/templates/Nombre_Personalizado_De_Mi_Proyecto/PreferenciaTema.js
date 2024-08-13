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
    
    // Set the value of claveTema
    let claveTema = select.options[select.selectedIndex].getAttribute('data-clave');
    document.getElementById('claveTema').value = claveTema;
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
            option.setAttribute('data-clave', tema.clave); // Set the data-clave attribute
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
                option.setAttribute('data-clave', gusto.claveTema); // Set the data-clave attribute
                select.appendChild(option);
            });
        })
       .catch(error => console.error('Error:', error));
}

async function getClaveFromTraduccion(traduccion) {
    try {
        const response = await fetch('/Temas/getClaveFromTraduccion?traduccion=' + traduccion);
        const data = await response.text();
        return data;
    } catch (error) {
        console.error('Error:', error);
    }
}

let currentSelection = null;

async function cambioSelectGusto() {
    const idUsuarioInput = document.getElementById('idUsuario');
    const idUsuario = idUsuarioInput.value;
    console.log('idUsuario:', idUsuario); // Check if idUsuario is being populated correctly
    if (!idUsuario) {
        console.error('No idUsuario provided');
        return;
    }
    const select = document.getElementById('selectGusto');
    currentSelection = select.options[select.selectedIndex].text;
    let temaSeleccionado = select.options[select.selectedIndex].text;
    let claveTema = await getClaveFromTraduccion(temaSeleccionado);
    document.getElementById('claveTema').value = claveTema;
    console.log("La clave es...: ", claveTema); // Check if claveTema is being populated correctly
    fetch(`/Temas/historial/${idUsuario}`)
       .then(response => response.text())
       .then(data => {
            const jsonData = JSON.parse(data);
            select.innerHTML = ''; // clear the select options
            jsonData.forEach(gusto => {
                const option = document.createElement('option');
                option.value = gusto.tema;
                option.text = gusto.tema;
                option.setAttribute('data-clave', gusto.clave); // Set the data-clave attribute
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

function recomendar() {
  let idUsuario = document.getElementById("idUsuario").value;
  let tema = document.getElementById("tema").value;
  let claveTema = document.getElementById("claveTema").value;

  fetch("/Temas/registraGusto", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body: new URLSearchParams({
      idUsuario: idUsuario,
      tema: tema,
      claveTema: claveTema
    })
  })
  .then(response => response.text())
  .then(data => {
    alert("Recomendacion por tema: ", claveTema);
    cargaSelectGusto();
  })
  .catch(error => console.error("Error en la petición:", error));
  console.log("La clave es...: ", claveTema);
  console.log(claveTema);
}

function redireccionarMenu() {
    window.location.href = "Menu.html";
}

function cargaHistorial() {
  console.log(document.getElementById('historial-table-body')); // Verifica si el elemento existe
  const historialTableBody = document.getElementById('historial-table-body');
  const idUsuarioInput = document.getElementById('idUsuario');
  const idUsuario = parseInt(idUsuarioInput.value, 10);
  if (isNaN(idUsuario)) {
    console.error('Invalid idUsuario value');
    return;
  }
  fetch(`/Temas/Recomendaciones/historial/${idUsuario}`)
    .then(response => response.json())
    .then(data => {
      console.log(data); // inspect the data
      if (!Array.isArray(data)) {
        console.error('Invalid data format');
        return;
      }
      historialTableBody.innerHTML = ''; // clear the table body
      data.forEach(recomendacion => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td><img src="https://covers.openlibrary.org/b/id/${recomendacion.imagen}-M.jpg" class="book-cover" /></td>
          <td>${recomendacion.titulo}</td>
          <td>${recomendacion.nombreAutor}</td>
          <td>${recomendacion.idLibro}</td>
          <td>${recomendacion.idAutor}</td>
          <td>${recomendacion.tema}</td>
          <td>${recomendacion.FechaRecomendacion}</td>
        `;
        historialTableBody.appendChild(row);
      });
    })
    .catch(error => {
      console.error('Error al cargar historial de recomendaciones:', error);
      alert('Error al cargar historial de recomendaciones. Intente nuevamente más tarde.');
    });
}

document.addEventListener("DOMContentLoaded", function() {
    cargaTemas();
    cargaSelectGusto();
    cargaHistorial();
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
    cargaHistorial();
    console.log("Ya cargo la página...");
}