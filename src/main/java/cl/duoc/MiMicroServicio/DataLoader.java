package cl.duoc.MiMicroServicio;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cl.duoc.MiMicroServicio.model.venta;
import cl.duoc.MiMicroServicio.service.ventaService;
import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner {

    private final Faker faker = new Faker(new Locale("es", "cl"));
    private final Random random = new Random();

    @Autowired
    private ventaService ventaservice;

    @Override
    public void run(String... args) throws Exception{
        for (int i=0; i < 10; i++){
            venta nuevaventa = new venta();
            //nuevaventa.setRutusuario(faker.name().firstName());
            nuevaventa.setRutusuario(generarRutFalso());
            nuevaventa.setFechaventa(LocalDate.now().minusDays(random.nextInt(60)));

            ventaservice.GuardarVenta(nuevaventa);
            System.out.println("Venta guardada: " + nuevaventa.getRutusuario());
        }
    }

    private String generarRutFalso() {
        int cuerpo = 10000000 + random.nextInt(8999999);
        String dv = calculardv(cuerpo);
        return cuerpo + "-" + dv;
    }

    private String calculardv(int cuerpo) {
        int m = 0, s = 1;
        while (cuerpo != 0) {
            s = (s + cuerpo % 10 * (9 - m++ % 6)) % 11;
            cuerpo /= 10;
        }

        if (s == 0) return "K";
        if (s == 1) return "0";
        return String.valueOf(11 - s);
    }

}
