package cl.duoc.MiMicroServicio.service;


import cl.duoc.MiMicroServicio.model.venta;
import cl.duoc.MiMicroServicio.repository.ventaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ventaServiceTest {


    @Mock
    private ventaRepository ventarepository;

    @InjectMocks
    private ventaService ventaservice;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarVenta(){
        venta v = new venta();
        v.setFechaventa(LocalDate.now());
        v.setRutusuario("12345678-9");

        when(ventarepository.save(any())).thenReturn(v);

        venta ventaGuardada = ventaservice.GuardarVenta(v);

        assertEquals("12345678-9", ventaGuardada.getRutusuario());
        verify(ventarepository, times(1)).save(v);
    }

    @Test
    public void testBuscarUnaVenta(){
        venta v = new venta();
        v.setIdventa( 1);
        v.setFechaventa(LocalDate.now());
        v.setRutusuario("12.345678-8");

        Long id = (long) 1;

        when(ventarepository.findById(id)).thenReturn(Optional.of(v));

        venta ventaBuscada = ventaservice.BuscarUnaVenta(1);

        assertNotNull(ventaBuscada);
        assertEquals(1, ventaBuscada.getIdventa());
        verify(ventarepository).findById(id);
    }




}
