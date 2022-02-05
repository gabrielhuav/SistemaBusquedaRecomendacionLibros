let id ;
let nombre ;
let info ;

window.onload = async function( ) {

    let queryString = window.location.search;
    let urlParams = new URLSearchParams(queryString);
    id = urlParams.get('q');
    nombre = urlParams.get('n'); 
    document.getElementById('idUsuario').value = id;
    document.getElementById('nombre').value = nombre;
    listaHistorial();

}
 
function BuscarPorTitulo() {
    
    //Eliminar elemento
    let tableHeaderRowCount = 1;
    let table = document.getElementById('tabla');
    let rowCount = table.rows.length;
    for (let i = tableHeaderRowCount; i < rowCount; i++) {
        table.deleteRow(tableHeaderRowCount);
    }
    
    
    
    let titulo = document.getElementById("tituloAbuscar").value;  
    let tituloBuscar = titulo.replaceAll(' ','+'); 
    
    const url = 'http://openlibrary.org/search.json?title=' + tituloBuscar ;
    
    fetch(url).then(function(response){
        response.json().then(function(data) {
        info = data;
        let tabla  = document.getElementById("tabla"); 
            
            for(let i = 0 ; i < 10 ; i++ ){
                
                let idLibro  = data.docs[i].key;
                let titulo   = data.docs[i].title; 
                let idAutor = data.docs[i].author_key[0]; 
                let autor = data.docs[i].author_name[0];
                let anio    = data.docs[i].first_publish_year; 
                
                //Llenamos la tabla
                let hilera = document.createElement("tr");
                
                let celdatitulo = document.createElement("td");
                celdatitulo.setAttribute("align","center");
                let textoCeldatitulo = document.createTextNode(titulo);
                celdatitulo.appendChild(textoCeldatitulo);
                hilera.appendChild(celdatitulo);
                
                let celdaautor = document.createElement("td");
                celdaautor.setAttribute("align","center");
                let textoCeldaautor = document.createTextNode(autor);
                celdaautor.appendChild(textoCeldaautor);
                hilera.appendChild(celdaautor);
                
                let celdaidLibro = document.createElement("td");
                celdaidLibro.setAttribute("align","center");
                let textoCeldaidLibro = document.createTextNode(idLibro);
                celdaidLibro.appendChild(textoCeldaidLibro);
                hilera.appendChild(celdaidLibro);
                
                let celdaidAutor = document.createElement("td");
                celdaidAutor.setAttribute("align","center");
                let textoCeldaidAutor = document.createTextNode(idAutor);
                celdaidAutor.appendChild(textoCeldaidAutor);
                hilera.appendChild(celdaidAutor);
                
                let celdaanio = document.createElement("td");
                celdaanio.setAttribute("align","center");
                let textoCeldaanio = document.createTextNode(anio);
                celdaanio.appendChild(textoCeldaanio);
                hilera.appendChild(celdaanio);
                
                 
                
                let celda = document.createElement("button");
                celda.setAttribute("align","justify");
                celda.setAttribute("onclick",'prueba(' + i + ')');
                celda.setAttribute("class","buttonMIO");
                let textoCelda = document.createTextNode("Agregar a favorito");
                celda.appendChild(textoCelda);
                hilera.appendChild(celda);
                
                ///////////////////Cargar imagen
                
                let celdaIMG = document.createElement("img");
                celdaIMG.setAttribute("align","right");
                celdaIMG.setAttribute("id","img");
                celdaIMG.setAttribute("class","box");
                let idIMG = data.docs[i].cover_i ;
                console.log(idIMG); 
                let img = 'https://covers.openlibrary.org/b/id/' + idIMG + '-L.jpg' ;

                if (idIMG === undefined){
                    celdaIMG.setAttribute("src",document.getElementById("img1").src);
                    
                }else{
                    celdaIMG.setAttribute("src",img);
                }
                
                hilera.appendChild(celdaIMG);
                
                ////////////////////
      

                tabla.appendChild(hilera);
            }       
 
        });
    }).catch(function(error) {
        console.log('Fetch Error:', error);
    });

}

function BuscarPorAutor() {
    
    //Eliminar elemento
    let tableHeaderRowCount = 1;
    let table = document.getElementById('tabla');
    let rowCount = table.rows.length;
    for (let i = tableHeaderRowCount; i < rowCount; i++) {
        table.deleteRow(tableHeaderRowCount);
    }
    
    
    
    let titulo = document.getElementById("tituloAbuscar").value;  
    let tituloBuscar = titulo.replaceAll(' ','+'); 
    
    const url = 'https://openlibrary.org/search.json?author=' + tituloBuscar ;
    
    fetch(url).then(function(response){
        response.json().then(function(data) {
        
        info = data;
        
        console.log(data.docs[0].key);
        console.log(data.docs[0].title); 
        console.log(data.docs[0].author_key[0]); 
        console.log(data.docs[0].author_name[0]);
        console.log(data.docs[0].first_publish_year); 

        
        let tabla  = document.getElementById("tabla"); 
            
            for(let i = 0 ; i < 10 ; i++ ){
                
                let idLibro  = data.docs[i].key;
                let titulo   = data.docs[i].title; 
                let idAutor = data.docs[i].author_key[0]; 
                let autor = data.docs[i].author_name[0];
                let anio    = data.docs[i].first_publish_year; 
                
                //Llenamos la tabla
                let hilera = document.createElement("tr");
                
                let celdatitulo = document.createElement("td");
                celdatitulo.setAttribute("align","center");
                let textoCeldatitulo = document.createTextNode(titulo);
                celdatitulo.appendChild(textoCeldatitulo);
                hilera.appendChild(celdatitulo);
                
                let celdaautor = document.createElement("td");
                celdaautor.setAttribute("align","center");
                let textoCeldaautor = document.createTextNode(autor);
                celdaautor.appendChild(textoCeldaautor);
                hilera.appendChild(celdaautor);
                
                let celdaidLibro = document.createElement("td");
                celdaidLibro.setAttribute("align","center");
                let textoCeldaidLibro = document.createTextNode(idLibro);
                celdaidLibro.appendChild(textoCeldaidLibro);
                hilera.appendChild(celdaidLibro);
                
                let celdaidAutor = document.createElement("td");
                celdaidAutor.setAttribute("align","center");
                let textoCeldaidAutor = document.createTextNode(idAutor);
                celdaidAutor.appendChild(textoCeldaidAutor);
                hilera.appendChild(celdaidAutor);
                
                let celdaanio = document.createElement("td");
                celdaanio.setAttribute("align","center");
                let textoCeldaanio = document.createTextNode(anio);
                celdaanio.appendChild(textoCeldaanio);
                hilera.appendChild(celdaanio);
                
                let celda = document.createElement("button");
                celda.setAttribute("align","justify");
                celda.setAttribute("onclick",'prueba(' + i + ')');
                celda.setAttribute("class","buttonMIO");
                let textoCelda = document.createTextNode("Agregar a favorito");
                celda.appendChild(textoCelda);
                hilera.appendChild(celda);
                
                ///////////////////Cargar imagen
                
                let celdaIMG = document.createElement("img");
                celdaIMG.setAttribute("align","right");
                celdaIMG.setAttribute("id","img");
                celdaIMG.setAttribute("class","box");
                let idIMG = data.docs[i].cover_i ;
                console.log(idIMG); 
                let img = 'https://covers.openlibrary.org/b/id/' + idIMG + '-L.jpg' ;

                if (idIMG === undefined){
                    celdaIMG.setAttribute("src",document.getElementById("img1").src);
                    
                }else{
                    celdaIMG.setAttribute("src",img);
                }
                
                hilera.appendChild(celdaIMG);
                
                ////////////////////
                

                tabla.appendChild(hilera);
            }       
            
            
        });
    }).catch(function(error) {
        console.log('Fetch Error:', error);
    });

}

function BuscarPorPalabra() {
    
    //Eliminar elemento
    let tableHeaderRowCount = 1;
    let table = document.getElementById('tabla');
    let rowCount = table.rows.length;
    for (let i = tableHeaderRowCount; i < rowCount; i++) {
        table.deleteRow(tableHeaderRowCount);
    }
    
    
    
    let titulo = document.getElementById("tituloAbuscar").value;  
    let tituloBuscar = titulo.replaceAll(' ','+'); 
    
    const url = 'http://openlibrary.org/search.json?q=' + tituloBuscar ;
    
    fetch(url).then(function(response){
        response.json().then(function(data) {
        
        info = data;
        
        console.log(data.docs[0].key);
        console.log(data.docs[0].title); 
        console.log(data.docs[0].author_key[0]); 
        console.log(data.docs[0].author_name[0]);
        console.log(data.docs[0].first_publish_year); 

        
        let tabla = document.getElementById("tabla"); 
            
            for(let i = 0 ; i < 10 ; i++ ){
                
                let idLibro  = data.docs[i].key;
                let titulo   = data.docs[i].title; 
                let idAutor = data.docs[i].author_key[0]; 
                let autor = data.docs[i].author_name[0];
                let anio    = data.docs[i].first_publish_year; 
                
                //Llenamos la tabla
                let hilera = document.createElement("tr");
                
                let celdatitulo = document.createElement("td");
                celdatitulo.setAttribute("align","center");
                let textoCeldatitulo = document.createTextNode(titulo);
                celdatitulo.appendChild(textoCeldatitulo);
                hilera.appendChild(celdatitulo);
                
                let celdaautor = document.createElement("td");
                celdaautor.setAttribute("align","center");
                let textoCeldaautor = document.createTextNode(autor);
                celdaautor.appendChild(textoCeldaautor);
                hilera.appendChild(celdaautor);
                
                let celdaidLibro = document.createElement("td");
                celdaidLibro.setAttribute("align","center");
                let textoCeldaidLibro = document.createTextNode(idLibro);
                celdaidLibro.appendChild(textoCeldaidLibro);
                hilera.appendChild(celdaidLibro);
                
                let celdaidAutor = document.createElement("td");
                celdaidAutor.setAttribute("align","center");
                let textoCeldaidAutor = document.createTextNode(idAutor);
                celdaidAutor.appendChild(textoCeldaidAutor);
                hilera.appendChild(celdaidAutor);
                
                let celdaanio = document.createElement("td");
                celdaanio.setAttribute("align","center");
                let textoCeldaanio = document.createTextNode(anio);
                celdaanio.appendChild(textoCeldaanio);
                hilera.appendChild(celdaanio);
                
                let celda = document.createElement("button");
                celda.setAttribute("align","justify");
                celda.setAttribute("onclick",'prueba(' + i + ')');
                celda.setAttribute("class","buttonMIO");
                let textoCelda = document.createTextNode("Agregar a favorito");
                celda.appendChild(textoCelda);
                hilera.appendChild(celda);
                
               ///////////////////Cargar imagen
                
                let celdaIMG = document.createElement("img");
                celdaIMG.setAttribute("align","right");
                celdaIMG.setAttribute("id","img");
                celdaIMG.setAttribute("class","box");
                let idIMG = data.docs[i].cover_i ;
                console.log(idIMG); 
                let img = 'https://covers.openlibrary.org/b/id/' + idIMG + '-L.jpg' ;

                if (idIMG === undefined){
                    celdaIMG.setAttribute("src",document.getElementById("img1").src);
                    
                }else{
                    celdaIMG.setAttribute("src",img);
                }
                
                hilera.appendChild(celdaIMG);
                
                ////////////////////

                tabla.appendChild(hilera);
            }       
            
            
        });
    }).catch(function(error) {
        console.log('Fetch Error:', error);
    });

}

function prueba(i){
    document.getElementById("Titulo").value = info.docs[i].title ;
    document.getElementById("Autor").value =   info.docs[i].author_name[0];
    document.getElementById("ClaveLibro").value = info.docs[i].key;;
    document.getElementById("ClaveAutor").value = info.docs[i].author_key[0] ;
    document.getElementById("Primerapublicacion").value = info.docs[i].first_publish_year;
    document.getElementById("imagen").value = info.docs[i].cover_i;
}

function listaHistorial() {

    const url = 'http://localhost:8080/Proyecto_Libros/webresources/LibroFavorito/lista/' + id ;
               
    fetch(url).then(function(response){
        response.json().then(function(dataH){

        let tabla = document.getElementById("tablaH"); 
            console.log(dataH);
            
            for(let i = 0 ; i < dataH.length ; i++ ){
                
                let idLibroH = dataH[i].idLibro;
                let tituloH  = dataH[i].titulo; 
                let idAutorH = dataH[i].idAutor; 
                let autorH   = dataH[i].nombreAutor;
                let anioH    = dataH[i].yearP; 
                let imagen   = dataH[i].imagen; 
                
                console.log(idLibroH);
                
                //Llenamos la tabla
                let hilera = document.createElement("tr");
                
                let celdatitulo = document.createElement("td");
                celdatitulo.setAttribute("align","center");
                let textoCeldatitulo = document.createTextNode(tituloH);
                celdatitulo.appendChild(textoCeldatitulo);
                hilera.appendChild(celdatitulo);
                
                let celdaautor = document.createElement("td");
                celdaautor.setAttribute("align","center");
                let textoCeldaautor = document.createTextNode(autorH);
                celdaautor.appendChild(textoCeldaautor);
                hilera.appendChild(celdaautor);
                
                let celdaidLibro = document.createElement("td");
                celdaidLibro.setAttribute("align","center");
                let textoCeldaidLibro = document.createTextNode(idLibroH);
                celdaidLibro.appendChild(textoCeldaidLibro);
                hilera.appendChild(celdaidLibro);
                
                let celdaidAutor = document.createElement("td");
                celdaidAutor.setAttribute("align","center");
                let textoCeldaidAutor = document.createTextNode(idAutorH);
                celdaidAutor.appendChild(textoCeldaidAutor);
                hilera.appendChild(celdaidAutor);
                
                let celdaanio = document.createElement("td");
                celdaanio.setAttribute("align","center");
                let textoCeldaanio = document.createTextNode(anioH);
                celdaanio.appendChild(textoCeldaanio);
                hilera.appendChild(celdaanio);
                
                ///////////////////Cargar imagen
                let celdaIMG = document.createElement("img");
                celdaIMG.setAttribute("align","right");
                celdaIMG.setAttribute("id","img");
                celdaIMG.setAttribute("class","box");
                              
                let img = 'https://covers.openlibrary.org/b/id/' + imagen + '-L.jpg' ;
                if (img === undefined){ 
                    celdaIMG.setAttribute("src",document.getElementById("img2").src);
                }else{
                    celdaIMG.setAttribute("src",img);
                }
                
                hilera.appendChild(celdaIMG);
            
                tablaH.appendChild(hilera);
            }       

        });
    }).catch(function(error) {
        console.log('Fetch Error:', error);
    });

}



function redireccionarMenu(){
    let URL = 'http://localhost:8080/Proyecto_Libros/menu.html?q=' + id + '&n=' + nombre ;
    location.href= URL;
}
