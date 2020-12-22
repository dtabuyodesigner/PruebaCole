package domain;

public class Alumno {

    // Variables de los alumnos
    private int codAlum;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String codCurso;
    private String observaciones;

    //Constructor vacio
    public Alumno() {

    }

    // Constructor de alumno
    public Alumno(String nombre, String apellido1, String apellido2, String codCurso, String observaciones) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.codCurso = codCurso;
        this.observaciones = observaciones;
    }

    //Constructor 2 del alumno
    public Alumno(int codAlum, String nombre, String apellido1, String getApellido2, String codCurso, String observaciones) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = getApellido2;
        this.codCurso = codCurso;
        this.observaciones = observaciones;
        this.codAlum = codAlum;
    }

    public int getCodAlum() {
        return codAlum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String getApellido2) {
        this.apellido2 = getApellido2;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido1 + " " + apellido2 + " " + codCurso;
    }


}
