let id ;
let nombre ;
window.onload = async function( ) {
    let queryString = window.location.search;
    let urlParams = new URLSearchParams(queryString);
    id = urlParams.get('q');
    nombre = urlParams.get('n');
    
    document.getElementById('nombre').innerHTML = nombre;
}

function redireccionarAutores(){
    let URL = 'http://localhost:8080/Proyecto_Libros/autor.html?q=' + id + '&n=' + nombre ;
    location.href= URL;
}

function redireccionarRecomendacionAutor(){
    let URL = 'http://localhost:8080/Proyecto_Libros/RecomendacionAutor.html?q=' + id + '&n=' + nombre ;
    location.href= URL;
}

function redireccionarRecomendaciomTema(){
    let URL = 'http://localhost:8080/Proyecto_Libros/PreferenciaTema.html?q=' + id + '&n=' + nombre ;
    location.href= URL;
}

function redireccionarbusquedaLibros(){
    let URL = 'http://localhost:8080/Proyecto_Libros/busquedaLibros.html?q=' + id + '&n=' + nombre ;
    location.href= URL;
}




