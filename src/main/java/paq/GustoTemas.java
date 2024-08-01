package paq;

import java.util.List;

public class GustoTemas {
    
    private String id;
    private String idUsuario;
    private String idtema;
    private String tema;

    public GustoTemas(String id, String idUsuario, String idtema, String tema) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idtema = idtema;
        this.tema = tema;
    }
    
     public GustoTemas( String idUsuario, String idtema, String tema) {  
        this.idUsuario = idUsuario;
        this.idtema = idtema;
        this.tema = tema;
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

    public String getIdtema() {
        return idtema;
    }

    public void setIdtema(String idtema) {
        this.idtema = idtema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public String toString() {
        return "Gustotemas{" + "id=" + id + ", idUsuario=" + idUsuario + ", idtema=" + idtema + ", tema=" + tema + '}';
    }
}
