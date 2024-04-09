package entity;

public class Empresa {

    private int id;
    private String nombre;
    private String sector;
    private String ubicacion;
    private String contacto;

    // Constructores
    public Empresa() {
    }

    public Empresa(String nombre, String sector, String ubicacion, String contacto) {
        this.nombre = nombre;
        this.sector = sector;
        this.ubicacion = ubicacion;
        this.contacto = contacto;
    }

    // Metodos getters and setters

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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String estado) {
        this.contacto = estado;
    }

    // toString
    @Override
    public String toString() {
        return "\nEmpresa " +
                " Nombre= " + nombre + '\'' +
                " Sector= " + sector + '\'' +
                " Ubicacion= " + ubicacion + '\'' +
                " Contacto= " + contacto;
    }
}
