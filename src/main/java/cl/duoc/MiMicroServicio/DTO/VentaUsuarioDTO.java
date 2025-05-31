package cl.duoc.MiMicroServicio.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaUsuarioDTO {

    private int idventa;
    private String rutusuario;
    private LocalDate fechaventa;
    private String nombre;
    private String mail;
}
