package paq;

/**
 * Atributos para los usuarios/
 * @author xido_
 */
public class Autor {
    
    private String id  ;
    private String nombre ;
    private String mejorlibro ;
    private String fechaNacimiento ;
    private String fechaFallecimiento ;
    private String biografia ;

    public Autor(String id, String nombre, String mejorlibro, String fechaNacimiento, String fechaFallecimiento, String biografia) {
        this.id = id;
        this.nombre = nombre;
        this.mejorlibro = mejorlibro;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
        this.biografia = biografia;
    }    

    Autor(Autor buscarAutor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Autor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMejorlibro() {
        return mejorlibro;
    }

    public void setMejorlibro(String mejorlibro) {
        this.mejorlibro = mejorlibro;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(String fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nombre=" + nombre + ", mejorlibro=" + mejorlibro + ", fechaNacimiento=" + fechaNacimiento + ", fechaFallecimiento=" + fechaFallecimiento + ", biografia=" + biografia + '}';
    }
    
    
   
}
