package com.company;

public class plaza {
    private String id;
    private String matricula;
    private String propietario;
    private String telefono;
    private String vencimiento;

    public plaza(String id, String matricula, String propietario, String telefono, String vencimiento) {
        this.id=id;
        this.matricula=matricula;
        this.propietario=propietario;
        this.telefono=telefono;
        this.vencimiento=vencimiento;

    }

    @Override
    public String toString() {
        return id+ "," + matricula + "," + propietario + "," + telefono + "," + vencimiento;
    }
}

