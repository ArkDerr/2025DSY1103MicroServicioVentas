package cl.duoc.MiMicroServicio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.MiMicroServicio.DTO.UsuarioDTO;
import cl.duoc.MiMicroServicio.DTO.VentaUsuarioDTO;
import cl.duoc.MiMicroServicio.model.venta;
import cl.duoc.MiMicroServicio.service.ventaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/Ventas")
@Tag(name = "Ventas", description = "Endpoints para trabajar las ventas")
public class ventaController {


    @Autowired
    private ventaService ventaservice;

    //Endpoint para buscar todas las ventas
    @GetMapping
    @Operation(summary = "Ventas", description = "Operación que lista todas las ventas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente las ventas",
            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = venta.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro ninguna venta",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "no se encuentran Datos")))
    })
    public ResponseEntity<?> ListarVentas(){
        List<venta> ventas = ventaservice.BuscarTodaVenta();
        if (ventas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran Datos");
        } else {
            return ResponseEntity.ok(ventas);
        }    
    }

    //Endpoint para buscar Una venta
    @GetMapping("/{idventa}")
    @Operation(summary = "Endpoint que busca una Venta", description = "Operación que busca y lista una venta")
    @Parameters(value = {
        @Parameter(name = "idventa", description = "Id de la venta que se va a buscar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente la venta",
            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = venta.class))),
        @ApiResponse(responseCode = "404", description = "No se encuentran Venta",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "No se encuentran Venta")))
    })
    public ResponseEntity<?> BuscarVenta(@PathVariable int idventa){
        try {
            venta ventabuscada = ventaservice.BuscarUnaVenta(idventa);
            return ResponseEntity.ok(ventabuscada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran Venta");
        }
    }

    @GetMapping("/VentaUsuario/{idventa}")
    public ResponseEntity<?> DatosVentaUsuario(@PathVariable int idventa){
        try {
            venta ventabuscada = ventaservice.BuscarUnaVenta(idventa);
            UsuarioDTO usuarioVenta = ventaservice.BuscarUsuario(ventabuscada.getRutusuario());
            VentaUsuarioDTO ventausuario = new VentaUsuarioDTO();
            ventausuario.setFechaventa(ventabuscada.getFechaventa());
            ventausuario.setIdventa(ventabuscada.getIdventa());
            ventausuario.setRutusuario(ventabuscada.getRutusuario());
            ventausuario.setNombre(usuarioVenta.getNombre());
            ventausuario.setMail(usuarioVenta.getMail());
            return ResponseEntity.ok(ventausuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran Venta o Usuario no esta registrado");
        }
    }

    //Endpoint para guardar ventas
    @PostMapping
    @Operation(summary = "Endpoint que registra una Venta", description = "Endpoint que registra una Venta", 
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto venta que se va a registrar", required = true,
    content = @Content(schema = @Schema(implementation = venta.class))
    ))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Indica que se registro correctamente la venta", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = venta.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar la venta",
        content = @Content(schema = @Schema(type = "String", example = "No se puede registar la venta")))
    })
    public ResponseEntity<?> GuardarVenta(@RequestBody venta ventaguardar){
        try{
            venta ventaregistrar = ventaservice.GuardarVenta(ventaguardar);
            return ResponseEntity.ok(ventaregistrar);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar la venta");
        }
    }

    //Endpoint para buscar Una venta
    @DeleteMapping("/{idventa}")
    @Operation(summary = "Endpoint que busca y elimina una Venta", description = "Operación que busca y elimina una venta")
    @Parameters(value = {
        @Parameter(name = "idventa", description = "Id de la venta que se va a eliminar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se elimina venta",
        content = @Content(mediaType = "application/json",
        schema = @Schema(type = "string", example = "Se elimina venta"))),
        @ApiResponse(responseCode = "404", description = "Venta no esta registrada",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "Venta no esta registrada")))
    })
    public ResponseEntity<String> EliminarVenta(@PathVariable int idventa){
        try {
            venta ventabuscada = ventaservice.BuscarUnaVenta(idventa);
            ventaservice.EliminarVenta(idventa);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimina venta");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no esta registrada");
        }
    }

    //Enpoint que edita una venta
    @PutMapping("/{idventa}")
    @Operation(summary = "Endpoint que edita una Venta", description = "Endpoint que edita una Venta", 
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto venta que se va a editar", required = true,
    content = @Content(schema = @Schema(implementation = venta.class))
    ))
    @Parameters(value = {
        @Parameter(name = "idventa", description = "Id de la venta que se va a editar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Indica que se registro correctamente la venta", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = venta.class))),
        @ApiResponse(responseCode = "500", description = "Venta no esta registrada",
        content = @Content(schema = @Schema(type = "String", example = "Venta no esta registrada")))
    })
    public ResponseEntity<?> ActualizarVenta(@PathVariable int idventa, @RequestBody venta ventaactualizar){
        try {
            venta ventaactualizada = ventaservice.BuscarUnaVenta(idventa);
            ventaactualizada.setRutusuario(ventaactualizar.getRutusuario());
            ventaactualizada.setFechaventa(ventaactualizar.getFechaventa());
            ventaservice.GuardarVenta(ventaactualizada);
            return ResponseEntity.ok(ventaactualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no esta registrada");
        }
    }


}
