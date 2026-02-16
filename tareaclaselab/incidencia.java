
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class incidencia {

    private int id;
    private final String descripcion;
    private Date fechaRegistro;
    private String nivelPrioridad;

    public incidencia(int id, String descripcion, String fecha, String nivelPrioridad)
            throws DescripcionInvalidaException,
                   PrioridadInvalidaException,
                   FechaInvalidaException {

        this.id = id;

        validarDescripcion(descripcion);
        validarPrioridad(nivelPrioridad);
        this.fechaRegistro = validarFecha(fecha);

        this.descripcion = descripcion.trim();
        this.nivelPrioridad = nivelPrioridad.trim().toUpperCase();
    }

    public incidencia(String descripcion) {
        this.descripcion = descripcion;
    }

    private void validarDescripcion(String descripcion) throws DescripcionInvalidaException {

        if (descripcion == null) {
            throw new DescripcionInvalidaException("La descripción no puede ser null.");
        }

        if (descripcion.trim().isEmpty()) {
            throw new DescripcionInvalidaException("La descripción no puede estar vacía.");
        }

        if (descripcion.trim().length() < 10) {
            throw new DescripcionInvalidaException("La descripción debe tener mínimo 10 caracteres.");
        }
    }

    private void validarPrioridad(String prioridad) throws PrioridadInvalidaException {

        if (prioridad == null) {
            throw new PrioridadInvalidaException("La prioridad no puede ser null.");
        }

        String prioridadNormalizada = prioridad.trim().toUpperCase();

        if (!prioridadNormalizada.equalsIgnoreCase("ALTA") &&
            !prioridadNormalizada.equalsIgnoreCase("MEDIA") &&
            !prioridadNormalizada.equalsIgnoreCase("BAJA")) {

            throw new PrioridadInvalidaException("Prioridad inválida. Solo ALTA, MEDIA o BAJA.");
        }
    }

    private Date validarFecha(String fecha) throws FechaInvalidaException {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);

        try {
            Date fechaConvertida = formato.parse(fecha);

            if (fechaConvertida.after(new Date())) {
                throw new FechaInvalidaException("La fecha no puede ser futura.");
            }

            return fechaConvertida;

        } catch (ParseException e) {
            throw new FechaInvalidaException("Formato de fecha incorrecto. Use dd/MM/yyyy.");
        }
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public String getNivelPrioridad() {
        return nivelPrioridad;
    }

    @Override
    public String toString() {

        SimpleDateFormat formatoSalida = new SimpleDateFormat("dd/MM/yyyy");

        return "\n                                " +
               "\nID: " + id +
               "\nDescripción: " + descripcion +
               "\nFecha: " + formatoSalida.format(fechaRegistro) +
               "\nPrioridad: " + nivelPrioridad +
               "\n                                   ";
    }
}
