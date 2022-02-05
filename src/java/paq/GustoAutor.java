package paq;

import java.util.List;


public class GustoAutor {
    
    private String id  ;
    private String idUsuario ;
    private String idAutor ;
    private String nombreAutor ;

    public GustoAutor(String id, String idUsuario, String idAutor, String nombreAutor) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
    }
    
    public GustoAutor(String idUsuario, String idAutor, String nombreAutor) {
        this.idUsuario = idUsuario;
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
    }
    
    GustoAutor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    GustoAutor(List<GustoAutor> lista) {
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

    @Override
    public String toString() {
        return "GustoAutor{" + "id=" + id + ", idUsuario=" + idUsuario + ", idAutor=" + idAutor + ", nombreAutor=" + nombreAutor + '}';
    }
    
  
}
