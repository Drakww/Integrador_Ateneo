package avance.integrador.modelo;

import lombok.Data;

@Data
public class SolicitudMatriculaDTO {
    private apoderado apoderado;
    private alumno alumno;
    private matricula matricula;
    private Solicitud solicitud;
    private pagomatricula pago;
}
