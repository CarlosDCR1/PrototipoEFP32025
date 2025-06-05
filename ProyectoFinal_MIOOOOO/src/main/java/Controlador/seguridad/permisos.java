package Controlador.seguridad;

public class permisos {
    private int idUsuario; // <- agregar este atributo
    private boolean puedeMantenimiento;
    private boolean puedeProcesos;
    private boolean puedeEliminar;
    private boolean puedeRegistrar;
    private boolean puedeModificar;

    // Getters y setters para idUsuario
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Getters y setters para los permisos
    public boolean isPuedeMantenimiento() {
        return puedeMantenimiento;
    }

    public void setPuedeMantenimiento(boolean puedeMantenimiento) {
        this.puedeMantenimiento = puedeMantenimiento;
    }

    public boolean isPuedeProcesos() {
        return puedeProcesos;
    }

    public void setPuedeProcesos(boolean puedeProcesos) {
        this.puedeProcesos = puedeProcesos;
    }

    public boolean isPuedeEliminar() {
        return puedeEliminar;
    }

    public void setPuedeEliminar(boolean puedeEliminar) {
        this.puedeEliminar = puedeEliminar;
    }

    public boolean isPuedeRegistrar() {
        return puedeRegistrar;
    }

    public void setPuedeRegistrar(boolean puedeRegistrar) {
        this.puedeRegistrar = puedeRegistrar;
    }

    public boolean isPuedeModificar() {
        return puedeModificar;
    }

    public void setPuedeModificar(boolean puedeModificar) {
        this.puedeModificar = puedeModificar;
    }
}
