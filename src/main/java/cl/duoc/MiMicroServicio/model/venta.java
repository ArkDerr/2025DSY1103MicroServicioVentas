package cl.duoc.MiMicroServicio.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidad que representa una venta")
public class venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDVENTA")
    @Schema(description = "Codigo de la venta autogenerado", accessMode = Schema.AccessMode.READ_ONLY)
    private int idventa;

    @Column(name = "RUTUSUARIO", nullable = false, length = 15)
    @Schema(description = "Rut de la persona que realiza la compra", example = "12.234.567-2")
    private String rutusuario;

    @Column(name = "FECHAVENTA", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Schema(description = "Fecha de la venta", example = "2025-06-05")
    private LocalDate fechaventa;
    
}
