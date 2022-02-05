package paq;

import java.util.List;


public class temas {
    
    private String id  ;
    private String clave ;
    private String traduccion ;

    public temas(String id, String clave, String traduccion) {
        this.id = id;
        this.clave = clave;
        this.traduccion = traduccion;
    }
    
    public temas(String clave, String traduccion) {
        this.clave = clave;
        this.traduccion = traduccion;
    }
    
    temas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    temas(List<temas> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTraduccion() {
        return traduccion;
    }

    public void setTraduccion(String traduccion) {
        this.traduccion = traduccion;
    }

    @Override
    public String toString() {
        return "temas{" + "id=" + id + ", clave=" + clave + ", traduccion=" + traduccion + '}';
    }

}
