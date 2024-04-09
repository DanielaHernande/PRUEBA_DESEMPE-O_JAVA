package entity;

public class Coder {
    private int id;
    private String nombre;
    private String apellidos;
    private String documento;
    private int cohorte;
    private String clan;
    private String cv;

    // Contructores
    public Coder() {
    }

    public Coder( String nombre, String apellidos, String documento, int cohorte, String clan, String cv) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.documento = documento;
        this.cohorte = cohorte;
        this.clan = clan;
        this.cv = cv;
    }

    // Metodos getters ans setters

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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getCohorte() {
        return cohorte;
    }

    public void setCohorte(int cohorte) {
        this.cohorte = cohorte;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    // toString
    @Override
    public String toString() {
        return "Coder{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", documento='" + documento + '\'' +
                ", cohorte=" + cohorte +
                ", clan='" + clan + '\'' +
                ", cv='" + cv + '\'' +
                '}';
    }
}
