package cl.duoc.MiMicroServicio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cl.duoc.MiMicroServicio.model.venta;

@SpringBootTest
public class testBuscarVentaBD {

    @Autowired
    private ventaService ventaservice;


    @Test
    public void testBuscarVenta(){

        venta ventaBuscada = ventaservice.BuscarUnaVenta(179);
        assertEquals("10026478-9", ventaBuscada.getRutusuario());
    }

    @Test
    public void testRegistrarVenta(){

        venta v = new venta();
        v.setFechaventa(LocalDate.now());
        v.setRutusuario("99999999-9");

        venta ventaRegistrada = ventaservice.GuardarVenta(v);

        assertEquals(240, ventaRegistrada.getIdventa());

    }



    

    

}
