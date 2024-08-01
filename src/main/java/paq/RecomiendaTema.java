package paq;


public class RecomiendaTema {
    
    private String id;
    private String idUsuario;
    private String idLibro;
    private String titulo;
    private String idAutor;
    private String nombreAutor;
    private String tema;
    private String imagen;
    private String FechaRecomendacion;

    public RecomiendaTema(String id, String idUsuario, String idLibro, String titulo, String idAutor, String nombreAutor, String tema, String imagen ,String FechaRecomendacion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.tema = tema;
        this.imagen = imagen;
        this.FechaRecomendacion = FechaRecomendacion;
    }

    public RecomiendaTema(String idUsuario, String idLibro, String titulo, String idAutor, String nombreAutor, String tema,String imagen, String FechaRecomendacion) {
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.tema = tema;
        this.imagen = imagen;
        this.FechaRecomendacion = FechaRecomendacion;
    }

    public RecomiendaTema(String idUsuario, String idLibro, String titulo, String idAutor, String nombreAutor, String tema, String imagen) {
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.tema = tema;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getFechaRecomendacion() {
        return FechaRecomendacion;
    }

    public void setFechaRecomendacion(String FechaRecomendacion) {
        this.FechaRecomendacion = FechaRecomendacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    @Override
    public String toString() {
        return "RecomiendaTema{" + "id=" + id + ", idUsuario=" + idUsuario + ", idLibro=" + idLibro + ", titulo=" + titulo + ", idAutor=" + idAutor + ", nombreAutor=" + nombreAutor + ", tema=" + tema + ", FechaRecomendacion=" + FechaRecomendacion + '}';
    }
}
