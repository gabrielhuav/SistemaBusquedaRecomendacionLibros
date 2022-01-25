package paq;

/**
 * Atributos para los usuarios/
 * @author xido_
 */
public class Usuario {
    
    private int id ;
    private String nombre ;
    private String apellidoP ;
    private String apellidoM ;
    private int edad ;
    private String genero ;
    private String correo ;
    private String userU ;
    private String passwordU ;
    
    
    public Usuario(int id, String nombre, String apellidoP, String apellidoM, int edad, String genero, String correo, String userU, String passwordU) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.edad = edad;
        this.genero = genero;
        this.correo = correo;
        this.userU = userU;
        this.passwordU = passwordU;
    }
    
    public Usuario(String nombre, String apellidoP, String apellidoM, int edad, String genero, String correo, String userU, String passwordU) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.edad = edad;
        this.genero = genero;
        this.correo = correo;
        this.correo = correo;
        this.userU = userU;
        this.passwordU = passwordU; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUserU() {
        return userU;
    }

    public void setUserU(String userU) {
        this.userU = userU;
    }

    public String getPaswordU() {
        return passwordU;
    }

    public void setPaswordU(String paswordU) {
        this.passwordU = paswordU;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", edad=" + edad + ", genero=" + genero + ", correo=" + correo + ", userU=" + userU + ", paswordU=" + passwordU + '}';
    }

   
      
}
