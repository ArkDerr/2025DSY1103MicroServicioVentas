package cl.duoc.MiMicroServicio.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="VENTA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDVENTA")
    private Long idventa;

    @Column(name = "RUTUSUARIO", nullable = false, length = 15)
    private String rutusuario;

    @Column(name = "FECHAVENTA", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaventa;
    
}
