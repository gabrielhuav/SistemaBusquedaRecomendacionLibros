package paq;


public class Libro {
    
    private String id  ;
    private String idUsuario  ;
    private String idLibro ;
    private String titulo  ;
    private String idAutor ;
    private String nombreAutor ;
    private String yearP ;
    private String imagen ;

    public Libro(String id, String idUsuario, String idLibro, String titulo, String idAutor, String nombreAutor, String yearP,String imagen) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.yearP = yearP;
        this.imagen = imagen;
    }
    
    public Libro( String idUsuario, String idLibro, String titulo, String idAutor, String nombreAutor, String yearP,String imagen) {
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.yearP = yearP;
        this.imagen = imagen;
    }


    Libro(Libro buscarAutor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Libro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getYearP() {
        return yearP;
    }

    public void setYearP(String yearP) {
        this.yearP = yearP;
    }
    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", idUsuario=" + idUsuario + ", idLibro=" + idLibro + ", titulo=" + titulo + ", idAutor=" + idAutor + ", nombreAutor=" + nombreAutor + ", yearP=" + yearP + '}';
    }

   

    
}
