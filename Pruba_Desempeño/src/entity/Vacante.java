package entity;

public class Vacante {

    private int id;
    private int empresa_id;
    private String titulo;
    private String tecnologia;
    private String descripcion;
    private String duracion;
    private String estado;

    private Empresa objEmpresa;

    // Constructores
    public Vacante() {
    }

    public Vacante(int empresa_id, String titulo, String tecnologia, String descripcion, String duracion, String estado, Empresa objEmpresa) {
        this.empresa_id = empresa_id;
        this.titulo = titulo;
        this.tecnologia = tecnologia;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.estado = estado;
        this.objEmpresa = objEmpresa;
    }

    // Metodos getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(int empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empresa getObjEmpresa() {
        return objEmpresa;
    }

    public void setObjEmpresa(Empresa objEmpresa) {
        this.objEmpresa = objEmpresa;
    }

    // toString
    @Override
    public String toString() {
        return "\nVacante " +
                "Empresa=" + objEmpresa +
                "Titulo=" + titulo + '\'' +
                "Tecnologia='" + tecnologia + '\'' +
                "Descripcion='" + descripcion + '\'' +
                "Duracion='" + duracion + '\'' +
                "Estado='" + estado;
    }


}
