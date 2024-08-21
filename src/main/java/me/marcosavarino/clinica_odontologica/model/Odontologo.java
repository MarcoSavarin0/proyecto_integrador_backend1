package me.marcosavarino.clinica_odontologica.model;

public class Odontologo {
    private  Integer Id;
    private String numero_De_Licencia;
    private String nombre;
    private String apellido;

    public Odontologo() {
    }

    public Odontologo(String numero_De_Licencia, String nombre, String apellido) {
        this.numero_De_Licencia = numero_De_Licencia;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(Integer id, String numero_De_Licencia, String nombre, String apellido) {
        Id = id;
        this.numero_De_Licencia = numero_De_Licencia;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "Id=" + Id +
                ", numero_De_Licencia='" + numero_De_Licencia + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNumero_De_Licencia() {
        return numero_De_Licencia;
    }

    public void setNumero_De_Licencia(String numero_De_Licencia) {
        this.numero_De_Licencia = numero_De_Licencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
