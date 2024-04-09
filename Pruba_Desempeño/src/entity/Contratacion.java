package entity;

import java.sql.Date;

public class Contratacion {

    private int id;
    private int vacante_id;
    private int coder_id;
    private Date fecha_aplicacion;
    private String estado;
    private Double salario;

    private Coder objCoder;
    private Vacante objVacante;

    // Constructores
    public Contratacion() {
    }

    public Contratacion( int vacante_id, int coder_id, Date fecha_aplicacion, String estado, Double salario, Coder objCoder, Vacante objVacante) {
        this.vacante_id = vacante_id;
        this.coder_id = coder_id;
        this.fecha_aplicacion = fecha_aplicacion;
        this.estado = estado;
        this.salario = salario;
        this.objCoder = objCoder;
        this.objVacante = objVacante;
    }

    // Metodos getters ans setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVacante_id() {
        return vacante_id;
    }

    public void setVacante_id(int vacante_id) {
        this.vacante_id = vacante_id;
    }

    public int getCoder_id() {
        return coder_id;
    }

    public void setCoder_id(int coder_id) {
        this.coder_id = coder_id;
    }

    public Date getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public void setFecha_aplicacion(Date fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Coder getObjCoder() {
        return objCoder;
    }

    public void setObjCoder(Coder objCoder) {
        this.objCoder = objCoder;
    }

    public Vacante getObjVacante() {
        return objVacante;
    }

    public void setObjVacante(Vacante objVacante) {
        this.objVacante = objVacante;
    }

    // toString
    @Override
    public String toString() {
        return "Contratacion{" +
                ", vacante_id=" + vacante_id +
                ", coder_id=" + coder_id +
                ", fecha_aplicacion=" + fecha_aplicacion +
                ", estado='" + estado + '\'' +
                ", salario=" + salario +
                ", objCoder=" + objCoder +
                ", objVacante=" + objVacante +
                '}';
    }
}
