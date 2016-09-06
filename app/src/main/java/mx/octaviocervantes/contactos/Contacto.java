package mx.octaviocervantes.contactos;

/**
 * Created by Tavo on 04/09/2016.
 */
public class Contacto {

    private String nombreContacto;
    private String fechaContacto;
    private String telefonoContacto;
    private String correoContacto;
    private String descripcionContacto;

    public Contacto(String nombreContacto, String fechaContacto, String telefonoContacto, String correoContacto, String descripcionContacto) {
        this.nombreContacto = nombreContacto;
        this.fechaContacto = fechaContacto;
        this.telefonoContacto = telefonoContacto;
        this.correoContacto = correoContacto;
        this.descripcionContacto = descripcionContacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getFechaContacto() {
        return fechaContacto;
    }

    public void setFechaContacto(String fechaContacto) {
        this.fechaContacto = fechaContacto;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public String getDescripcionContacto() {
        return descripcionContacto;
    }

    public void setDescripcionContacto(String descripcionContacto) {
        this.descripcionContacto = descripcionContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }
}
