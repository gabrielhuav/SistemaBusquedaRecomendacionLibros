let id ;
let nombre ;
function llenarSelectTemas() {
    const url = 'http://localhost:8080/Proyecto_Libros/webresources/Temas';

    fetch(url).then(function(response){
        response.json().then(function(data) {
            console.log(data);          
            
            //Recorrer un array de JSON 
            for(let i = 0 ; i < data.length ; i++ ){
                let select = document.getElementsByName("cliente")[0];
                let option = document.createElement("option");
                option.text = data[i].traduccion;             
                option.value = data[i].clave;
                select.add(option);  
            }
            
        });
    }).catch(function(error) {
        console.log('Fetch Error:', error);
    });
}

function llenarSelectGustoTemas() {
   
    const url = 'http://localhost:8080/Proyecto_Libros/webresources/Temas/' + id ;
    
   
    fetch(url).then(function(response){
        response.json().then(function(tema) {
            console.log(tema);          
            
            //Recorrer un array de JSON 
            for(let i = 0 ; i < tema.length ; i++ ){
                let select = document.getElementsByName("selectGusto")[0];
                let option = document.createElement("option");
                option.text = tema[i].tema;             
                option.value = tema[i].idtema;
                select.add(option);  
            }
            
        });
    }).catch(function(error) {
        console.log('Fetch Error:', error);
    });
}

function llenarListaRecomendacion(){  
    const url = 'http://localhost:8080/Proyecto_Libros/webresources/Temas/lista/' + id ;
    
    fetch(url).then(function(response){
        response.json().then(function(data) {
            console.log(data);          
            
            let lista = document.getElementById("tabla"); 
            //Recorrer un array de JSON 
            for(let i = 0 ; i < data.length ; i++ ){
                //let select = document.getElementsByName("cliente")[0];
                //let option = document.createElement("option");
                let titulo  = data[i].titulo;
                let nombreAutor  = data[i].nombreAutor;
                let tema  = data[i].tema;
                let idLibro = data[i].idLibro;
                let idAutor = data[i].idAutor;
                let imagen = data[i].imagen;
                let fechaRecomendacion = data[i].fechaRecomendacion;
               
                
                //Llenamos la tabla
                let hilera = document.createElement("tr");
                
                let celdatitulo = document.createElement("td");
                celdatitulo.setAttribute("align","center");
                let textoCeldatitulo = document.createTextNode(titulo);
                celdatitulo.appendChild(textoCeldatitulo);
                hilera.appendChild(celdatitulo);
                
                let celdanombreAutor = document.createElement("td");
                celdanombreAutor.setAttribute("align","center");
                let textoCeldanombreAutor = document.createTextNode(nombreAutor);
                celdanombreAutor.appendChild(textoCeldanombreAutor);
                hilera.appendChild(celdanombreAutor);
                
                let celdatema  = document.createElement("td");
                celdatema .setAttribute("align","center");
                let textoCeldatema  = document.createTextNode(tema );
                celdatema .appendChild(textoCeldatema );
                hilera.appendChild(celdatema );
                
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
                
                let celdafechaRecomendacion = document.createElement("td");
                celdafechaRecomendacion.setAttribute("align","center");
                let textoCeldafechaRecomendacion = document.createTextNode(fechaRecomendacion);
                celdafechaRecomendacion.appendChild(textoCeldafechaRecomendacion);
                hilera.appendChild(celdafechaRecomendacion);
                
                ///////////////////Cargar imagen
                
                let celdaIMG = document.createElement("img");
                celdaIMG.setAttribute("align","right");
                celdaIMG.setAttribute("id","img");
                celdaIMG.setAttribute("class","box1");
                let img = 'https://covers.openlibrary.org/b/id/' + imagen + '-L.jpg' ;

                if (img === undefined){
                    celdaIMG.setAttribute("src",document.getElementById("img").src);
                }else{
                    celdaIMG.setAttribute("src",img);
                }
                
                hilera.appendChild(celdaIMG);
                
                ////////////////////
                
                tabla.appendChild(hilera);
                

            }    
            
            let tr = document.createElement("tr");
            let td = document.createElement("td");
            let contenido = document.createTextNode("Hola Mundo!");
            td.appendChild(contenido);
            tr.appendeChild(td);
            lista.appendeChild(tr);
        });
    }).catch(function(error) {
        console.log('Fetch Error:', error);
    });
    
}

window.onload = async function( ) {
    let queryString = window.location.search;
    let urlParams = new URLSearchParams(queryString);
    id = urlParams.get('q');
    nombre = urlParams.get('n');
    console.log(id); 
    document.getElementById('idUsuario').value = id;
    document.getElementById('idUsuario1').value = id;
    llenarSelectTemas()
    llenarSelectGustoTemas()
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
    
 
    
}

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
    let select = document.getElementById('cliente');
    let tema = select.options[select.selectedIndex].text;
    document.getElementById('tema').value = tema;
    
    let idtema = document.getElementById('cliente').value;
    document.getElementById('idTema').value = idtema;   
}


function cambioSelectGusto() {
    let select1 = document.getElementById('selectGusto');
    let tema1 = select1.options[select1.selectedIndex].text;
     document.getElementById('temaUsuario').value = tema1;
    
    let idtema1 = document.getElementById('selectGusto').value;
    document.getElementById('claveTema').value = idtema1;   
}

function redireccionarMenu(){
    let URL = 'http://localhost:8080/Proyecto_Libros/menu.html?q=' + id + '&n=' + nombre ;
    location.href= URL;
}


