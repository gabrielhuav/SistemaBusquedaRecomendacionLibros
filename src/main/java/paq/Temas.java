package paq;

public class Temas {
    
    private String id;
    private String clave;
    private String traduccion;

    public Temas(String id, String clave, String traduccion) {
        this.id = id;
        this.clave = clave;
        this.traduccion = traduccion;
    }
    
    public Temas(String clave, String traduccion) {
        this.clave = clave;
        this.traduccion = traduccion;
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
