let nombre;
let id;
let a;

const contextPath = '/Nombre_Personalizado_De_Mi_Proyecto';

function recoger() {
    let codigo = document.getElementById("Codigo").value;
    const url = 'http://localhost:8080/busquedaAutor/' + codigo;

    fetch(url)
    .then(response => response.json() )
    .then(data => {
        console.log(data);

        document.getElementById('idAutor').value = data.id;
        document.getElementById('nombreAutor').value = data.nombre;
        document.getElementById('idAutorL').innerHTML = data.id;
        document.getElementById("nombreL").innerHTML = data.nombre;
        document.getElementById("mejorlibroL").innerHTML = data.mejorlibro;
        document.getElementById("fechaNacimientoL").innerHTML = data.fechaNacimiento;
        document.getElementById("fechaFallecimientoL").innerHTML = data.fechaFallecimiento;
        document.getElementById("biografiaL").innerHTML = data.biografia;

        recuperarImagenURLJSON(data.id);
    })
    .catch(err => console.log(err));
}

function recuperarImagen() {
    const url = 'https://openlibrary.org/authors/' + a + '.json' ;

    fetch(url)
    .then(response => response.json() )
    .then(data => {
        let idIMG = data.photos[0] ;
        console.log(idIMG); //7127409
        const img = 'https://covers.openlibrary.org/a/id/' + idIMG + '-M.jpg' ;
        document.getElementById("img").src= img;
    })
    .catch(err=>console.log(err))
}

function recuperarImagenURLJSON(idImagenBase) {
    const url = 'https://openlibrary.org/authors/' + idImagenBase + '.json' ;

    fetch(url)
    .then(response => response.json() )
    .then(data => {
        let idIMG = data.photos[0] ;
        console.log(idIMG); //7127409
        const img = 'https://covers.openlibrary.org/a/id/' + idIMG + '-M.jpg' ;
        document.getElementById("img1").src= img;
    })
    .catch(err=>console.log(err))
}

window.onload = async function( ) {
    let queryString = window.location.search;
    let urlParams = new URLSearchParams(queryString);
    id = urlParams.get('q');
    nombre = urlParams.get('n');
    console.log(id); 
    document.getElementById('idUsuario').value = id;
    document.getElementById('nombre').value = nombre;
    document.getElementById('idUsuarioBucar').value = id;
    
    a = urlParams.get('a');
    document.getElementById('idAutorB').innerHTML = a;
    
    let b = urlParams.get('b');
    document.getElementById("nombreB").innerHTML = b;  
    
    let c = urlParams.get('c');
    document.getElementById("mejorlibroB").innerHTML= c;
    
    let d = urlParams.get('d');
    document.getElementById("fechaNacimientoB").innerHTML = d;
    
    let e = urlParams.get('e');
    document.getElementById("fechaFallecimientoB").innerHTML = e;
    
    let f = urlParams.get('f');
    document.getElementById("biografiaB").innerHTML = f;
    
    recuperarImagen()
    
}

function redireccionarMenu(){
    let URL = 'http://localhost:8080/Nombre_Personalizado_De_Mi_Proyecto/menu.html?q=' + id + '&n=' + nombre ;
    location.href= URL;
}