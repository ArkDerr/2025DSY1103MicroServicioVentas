package cl.duoc.MiMicroServicio.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import cl.duoc.MiMicroServicio.controller.ventaController;
import cl.duoc.MiMicroServicio.model.venta;

import org.springframework.hateoas.server.RepresentationModelAssembler;



@Component
public class VentaModelAssembler implements RepresentationModelAssembler<venta, EntityModel<venta>>{

    @Override
    public EntityModel<venta> toModel(venta v){
        return EntityModel.of(
            v,
            linkTo(methodOn(ventaController.class).BuscarVenta(v.getIdventa())).withSelfRel(),
            linkTo(methodOn(ventaController.class).ListarVentas()).withRel("Todas-las-ventas"),
            linkTo(methodOn(ventaController.class).EliminarVenta(v.getIdventa())).withRel("Elimina-una-venta"),
            linkTo(methodOn(ventaController.class).ActualizarVenta(v.getIdventa(), v)).withRel("Actualiza-una-venta")
        );
    }
}
