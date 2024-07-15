let id ;
let nombre ;

window.onload = async function( ) {

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
    document.getElementById("idAutorR").innerHTML= c;
    
    let d = urlParams.get('d');
    document.getElementById("nombreR").innerHTML = d;   
    recuperarImagen(a);
    
}
 
function llenarSelect(){
    let idUsuario = document.getElementById("idUsuario").value;
    const url = 'http://localhost:8080/Nombre_Personalizado_De_Mi_Proyecto/webresources/AutorFavorito/' + idUsuario ;
    
    fetch(url).then(function(response){
        response.json().then(function(data) {
            console.log(data);          
            
            //Recorrer un array de JSON 
            for(let i = 0 ; i < data.length ; i++ ){
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

function llenarListaRecomendacion(){
    let idUsuario = document.getElementById("idUsuario").value;
    const url = 'http://localhost:8080/Nombre_Personalizado_De_Mi_Proyecto/webresources/AutorFavorito/lista/' + idUsuario ;
    
    fetch(url).then(function(response){
        response.json().then(function(data) {
            console.log(data);          
           
            let lista = document.getElementById("tabla"); 
            //Recorrer un array de JSON 
            for(let i = 0 ; i < data.length ; i++ ){
                //let select = document.getElementsByName("cliente")[0];
                //let option = document.createElement("option");
                let titulo  = data[i].titulo;  
                let autor  = data[i].nombreAutor;  
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
    let autor = select.options[select.selectedIndex].text;
    document.getElementById('nombreAutor').value = autor;
    
    let idAutor = document.getElementById('cliente').value;
    document.getElementById('idAutor').value = idAutor;  
}

function redireccionarMenu(){
    let URL = 'http://localhost:8080/Nombre_Personalizado_De_Mi_Proyecto/menu.html?q=' + id + '&n=' + nombre ;
    location.href= URL;
}







        
