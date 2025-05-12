package cl.duoc.MiMicroServicio.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    
    private Long rut;
    private String nombre;
    private String mail;
    private Integer idcurso;
}
