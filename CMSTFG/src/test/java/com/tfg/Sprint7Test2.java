package com.tfg;

import com.tfg.dao.ITrabajadorDao;
import com.tfg.dao.ITrabajoDao;
import com.tfg.entity.Trabajador;
import com.tfg.entity.Trabajo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Sprint7Test2 {

    private Logger log = Logger.getLogger("Trabajadores y registro de trabajo");

    @Autowired
    private ITrabajadorDao trabajadorRepository;

    @Autowired
    private ITrabajoDao trabajoRepository;

    public void creaLineas() {
        log.info("-------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------"
                + "----------------------------------------------------------------------------------------");

    }

    public Trabajador creaTrabajadorNormal(){
        Date date = new GregorianCalendar(1940, Calendar.JANUARY, 30).getTime();
        Trabajador trabajador = new Trabajador();
        trabajador.setUsername("trabajadorUsername");
        trabajador.setPassword("trabajadorPassword");
        trabajador.setNombre("trabajadorNombre");
        trabajador.setApellidos("trabajadorApellidos");
        trabajador.setEmail("trabajadorEmail");
        trabajador.setCiudad("trabajadorCiudad");
        trabajador.setCp("15707");
        trabajador.setDireccion("trabajadorDireccion");
        trabajador.setFechaNacimiento(date);
        trabajador.setTlf("trabajadorTlf");
        return trabajador;
    }
    public Trabajador creaTrabajadorNormal2(){
        Date date = new GregorianCalendar(1940, Calendar.JANUARY, 30).getTime();
        Trabajador trabajador = new Trabajador();
        trabajador.setUsername("trabajadorUsername2");
        trabajador.setPassword("trabajadorPassword");
        trabajador.setNombre("trabajadorNombre");
        trabajador.setApellidos("trabajadorApellidos");
        trabajador.setEmail("trabajadorEmail");
        trabajador.setCiudad("trabajadorCiudad");
        trabajador.setCp("15707");
        trabajador.setDireccion("trabajadorDireccion");
        trabajador.setFechaNacimiento(date);
        trabajador.setTlf("trabajadorTlf");
        return trabajador;
    }
    public Trabajador creaTrabajadorNormal3(){
        Date date = new GregorianCalendar(1940, Calendar.JANUARY, 30).getTime();
        Trabajador trabajador = new Trabajador();
        trabajador.setUsername("trabajadorUsername3");
        trabajador.setPassword("trabajadorPassword");
        trabajador.setNombre("trabajadorNombre");
        trabajador.setApellidos("trabajadorApellidos");
        trabajador.setEmail("trabajadorEmail");
        trabajador.setCiudad("trabajadorCiudad");
        trabajador.setCp("15707");
        trabajador.setDireccion("trabajadorDireccion");
        trabajador.setFechaNacimiento(date);
        trabajador.setTlf("trabajadorTlf");
        return trabajador;
    }


    @Test
    void testAñadeTrabajoyBuscaPorUsuarioyLocalizacion(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajador2 = creaTrabajadorNormal2();
        Trabajador trabajador3 = creaTrabajadorNormal3();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        Trabajador trabajadorDB2=trabajadorRepository.save(trabajador2);
        Trabajador trabajadorDB3=trabajadorRepository.save(trabajador3);

        Trabajo trabajo = new Trabajo();
        trabajo.setTipo_trabajo("Clasificar");
        trabajo.setTrabajador(trabajadorDB);
        trabajo.setObservaciones("-");
        trabajo.setLocalizacion("A Barca");
        String fechaInicioStr = "2021-07-30 10:00";
        String fechaFinStr = "2021-07-30 18:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
        trabajo.setInicioTrabajo(fechaInicio);
        trabajo.setFinalTrabajo(fechaFin);
        Trabajo trabajoEntregado=trabajoRepository.save(trabajo);


        Trabajo trabajo2 = new Trabajo();
        trabajo2.setTipo_trabajo("Recolectar");
        trabajo2.setTrabajador(trabajadorDB);
        trabajo2.setObservaciones("-");
        trabajo2.setLocalizacion("A Barca");
        String fechaInicioStr2 = "2021-07-31 00:00";
        String fechaFinStr2 = "2021-07-31 08:00";
        LocalDateTime fechaInicio2 = LocalDateTime.parse(fechaInicioStr2, formatter);
        LocalDateTime fechaFin2 = LocalDateTime.parse(fechaFinStr2, formatter);
        trabajo2.setInicioTrabajo(fechaInicio2);
        trabajo2.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado2=trabajoRepository.save(trabajo2);

        Trabajo trabajo3 = new Trabajo();
        trabajo3.setTipo_trabajo("Recolectar");
        trabajo3.setTrabajador(trabajadorDB2);
        trabajo3.setObservaciones("-");
        trabajo3.setLocalizacion("A Barca");
        String fechaInicioStr3 = "2021-07-31 00:00";
        String fechaFinStr3 = "2021-07-31 08:00";
        LocalDateTime fechaInicio3 = LocalDateTime.parse(fechaInicioStr3, formatter);
        LocalDateTime fechaFin3 = LocalDateTime.parse(fechaFinStr3, formatter);
        trabajo3.setInicioTrabajo(fechaInicio3);
        trabajo3.setFinalTrabajo(fechaFin3);
        Trabajo trabajoEntregado3=trabajoRepository.save(trabajo3);

        Trabajo trabajo4 = new Trabajo();
        trabajo4.setTipo_trabajo("Recolectar");
        trabajo4.setTrabajador(trabajadorDB2);
        trabajo4.setObservaciones("-");
        trabajo4.setLocalizacion("Casa");
        String fechaInicioStr4 = "2021-07-31 18:00";
        String fechaFinStr4 = "2021-07-31 10:00";
        LocalDateTime fechaInicio4 = LocalDateTime.parse(fechaInicioStr4, formatter);
        LocalDateTime fechaFin4 = LocalDateTime.parse(fechaFinStr4, formatter);
        trabajo4.setInicioTrabajo(fechaInicio4);
        trabajo4.setFinalTrabajo(fechaFin4);
        Trabajo trabajoEntregado4=trabajoRepository.save(trabajo4);

        Trabajo trabajo5 = new Trabajo();
        trabajo5.setTipo_trabajo("Recolectar");
        trabajo5.setTrabajador(trabajadorDB3);
        trabajo5.setObservaciones("-");
        trabajo5.setLocalizacion("A Barca");
        trabajo5.setInicioTrabajo(fechaInicio2);
        trabajo5.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado5=trabajoRepository.save(trabajo5);



        String fechaInicioStrBusca = "2021-07-30 00:00";
        String fechaFinStrBusca = "2021-07-31 20:00";
        LocalDateTime fechaInicioBusca = LocalDateTime.parse(fechaInicioStrBusca, formatter);
        LocalDateTime fechaFinBusca = LocalDateTime.parse(fechaFinStrBusca, formatter);
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByUsuarioyLocalizacion(trabajador.getUsername(),"A Barca");

        assertEquals(2, trabajoBuscado2.size());
        assertEquals(trabajoEntregado, trabajoBuscado2.get(0));
        assertEquals(trabajoEntregado2, trabajoBuscado2.get(1));

        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajoRepository.deleteById(trabajoEntregado2.getId());
        trabajoRepository.deleteById(trabajoEntregado3.getId());
        trabajoRepository.deleteById(trabajoEntregado4.getId());
        trabajoRepository.deleteById(trabajoEntregado5.getId());

        trabajadorRepository.deleteById(trabajadorDB.getId());
        trabajadorRepository.deleteById(trabajadorDB2.getId());
        trabajadorRepository.deleteById(trabajadorDB3.getId());


    }

    @Test
    void testAñadeTrabajoyBuscaPorUsuarioytipoTrabajo(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajador2 = creaTrabajadorNormal2();
        Trabajador trabajador3 = creaTrabajadorNormal3();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        Trabajador trabajadorDB2=trabajadorRepository.save(trabajador2);
        Trabajador trabajadorDB3=trabajadorRepository.save(trabajador3);

        Trabajo trabajo = new Trabajo();
        trabajo.setTipo_trabajo("Clasificar");
        trabajo.setTrabajador(trabajadorDB);
        trabajo.setObservaciones("-");
        trabajo.setLocalizacion("A Barca");
        String fechaInicioStr = "2021-07-30 10:00";
        String fechaFinStr = "2021-07-30 18:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
        trabajo.setInicioTrabajo(fechaInicio);
        trabajo.setFinalTrabajo(fechaFin);
        Trabajo trabajoEntregado=trabajoRepository.save(trabajo);


        Trabajo trabajo2 = new Trabajo();
        trabajo2.setTipo_trabajo("Recolectar");
        trabajo2.setTrabajador(trabajadorDB);
        trabajo2.setObservaciones("-");
        trabajo2.setLocalizacion("A Barca");
        String fechaInicioStr2 = "2021-07-31 00:00";
        String fechaFinStr2 = "2021-07-31 08:00";
        LocalDateTime fechaInicio2 = LocalDateTime.parse(fechaInicioStr2, formatter);
        LocalDateTime fechaFin2 = LocalDateTime.parse(fechaFinStr2, formatter);
        trabajo2.setInicioTrabajo(fechaInicio2);
        trabajo2.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado2=trabajoRepository.save(trabajo2);

        Trabajo trabajo3 = new Trabajo();
        trabajo3.setTipo_trabajo("Recolectar");
        trabajo3.setTrabajador(trabajadorDB2);
        trabajo3.setObservaciones("-");
        trabajo3.setLocalizacion("A Barca");
        String fechaInicioStr3 = "2021-07-31 00:00";
        String fechaFinStr3 = "2021-07-31 08:00";
        LocalDateTime fechaInicio3 = LocalDateTime.parse(fechaInicioStr3, formatter);
        LocalDateTime fechaFin3 = LocalDateTime.parse(fechaFinStr3, formatter);
        trabajo3.setInicioTrabajo(fechaInicio3);
        trabajo3.setFinalTrabajo(fechaFin3);
        Trabajo trabajoEntregado3=trabajoRepository.save(trabajo3);

        Trabajo trabajo4 = new Trabajo();
        trabajo4.setTipo_trabajo("Recolectar");
        trabajo4.setTrabajador(trabajadorDB2);
        trabajo4.setObservaciones("-");
        trabajo4.setLocalizacion("Casa");
        String fechaInicioStr4 = "2021-07-31 18:00";
        String fechaFinStr4 = "2021-07-31 10:00";
        LocalDateTime fechaInicio4 = LocalDateTime.parse(fechaInicioStr4, formatter);
        LocalDateTime fechaFin4 = LocalDateTime.parse(fechaFinStr4, formatter);
        trabajo4.setInicioTrabajo(fechaInicio4);
        trabajo4.setFinalTrabajo(fechaFin4);
        Trabajo trabajoEntregado4=trabajoRepository.save(trabajo4);

        Trabajo trabajo5 = new Trabajo();
        trabajo5.setTipo_trabajo("Recolectar");
        trabajo5.setTrabajador(trabajadorDB3);
        trabajo5.setObservaciones("-");
        trabajo5.setLocalizacion("A Barca");
        trabajo5.setInicioTrabajo(fechaInicio2);
        trabajo5.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado5=trabajoRepository.save(trabajo5);



        String fechaInicioStrBusca = "2021-07-30 00:00";
        String fechaFinStrBusca = "2021-07-31 20:00";
        LocalDateTime fechaInicioBusca = LocalDateTime.parse(fechaInicioStrBusca, formatter);
        LocalDateTime fechaFinBusca = LocalDateTime.parse(fechaFinStrBusca, formatter);
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByUsuarioyTipo_trabajo(trabajador.getUsername(),"Clasificar");

        assertEquals(1, trabajoBuscado2.size());
        assertEquals(trabajoEntregado, trabajoBuscado2.get(0));

        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajoRepository.deleteById(trabajoEntregado2.getId());
        trabajoRepository.deleteById(trabajoEntregado3.getId());
        trabajoRepository.deleteById(trabajoEntregado4.getId());
        trabajoRepository.deleteById(trabajoEntregado5.getId());

        trabajadorRepository.deleteById(trabajadorDB.getId());
        trabajadorRepository.deleteById(trabajadorDB2.getId());
        trabajadorRepository.deleteById(trabajadorDB3.getId());


    }

    @Test
    void testAñadeTrabajoyBuscaPorFechasyLocalizacion(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajador2 = creaTrabajadorNormal2();
        Trabajador trabajador3 = creaTrabajadorNormal3();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        Trabajador trabajadorDB2=trabajadorRepository.save(trabajador2);
        Trabajador trabajadorDB3=trabajadorRepository.save(trabajador3);

        Trabajo trabajo = new Trabajo();
        trabajo.setTipo_trabajo("Clasificar");
        trabajo.setTrabajador(trabajadorDB);
        trabajo.setObservaciones("-");
        trabajo.setLocalizacion("A Barca");
        String fechaInicioStr = "2021-07-30 10:00";
        String fechaFinStr = "2021-07-30 18:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
        trabajo.setInicioTrabajo(fechaInicio);
        trabajo.setFinalTrabajo(fechaFin);
        Trabajo trabajoEntregado=trabajoRepository.save(trabajo);


        Trabajo trabajo2 = new Trabajo();
        trabajo2.setTipo_trabajo("Recolectar");
        trabajo2.setTrabajador(trabajadorDB);
        trabajo2.setObservaciones("-");
        trabajo2.setLocalizacion("A Barca");
        String fechaInicioStr2 = "2021-07-31 00:00";
        String fechaFinStr2 = "2021-07-31 08:00";
        LocalDateTime fechaInicio2 = LocalDateTime.parse(fechaInicioStr2, formatter);
        LocalDateTime fechaFin2 = LocalDateTime.parse(fechaFinStr2, formatter);
        trabajo2.setInicioTrabajo(fechaInicio2);
        trabajo2.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado2=trabajoRepository.save(trabajo2);

        Trabajo trabajo3 = new Trabajo();
        trabajo3.setTipo_trabajo("Recolectar");
        trabajo3.setTrabajador(trabajadorDB2);
        trabajo3.setObservaciones("-");
        trabajo3.setLocalizacion("A Barca");
        String fechaInicioStr3 = "2021-07-31 00:00";
        String fechaFinStr3 = "2021-07-31 08:00";
        LocalDateTime fechaInicio3 = LocalDateTime.parse(fechaInicioStr3, formatter);
        LocalDateTime fechaFin3 = LocalDateTime.parse(fechaFinStr3, formatter);
        trabajo3.setInicioTrabajo(fechaInicio3);
        trabajo3.setFinalTrabajo(fechaFin3);
        Trabajo trabajoEntregado3=trabajoRepository.save(trabajo3);

        Trabajo trabajo4 = new Trabajo();
        trabajo4.setTipo_trabajo("Recolectar");
        trabajo4.setTrabajador(trabajadorDB2);
        trabajo4.setObservaciones("-");
        trabajo4.setLocalizacion("Casa");
        String fechaInicioStr4 = "2021-07-31 18:00";
        String fechaFinStr4 = "2021-07-31 10:00";
        LocalDateTime fechaInicio4 = LocalDateTime.parse(fechaInicioStr4, formatter);
        LocalDateTime fechaFin4 = LocalDateTime.parse(fechaFinStr4, formatter);
        trabajo4.setInicioTrabajo(fechaInicio4);
        trabajo4.setFinalTrabajo(fechaFin4);
        Trabajo trabajoEntregado4=trabajoRepository.save(trabajo4);

        Trabajo trabajo5 = new Trabajo();
        trabajo5.setTipo_trabajo("Recolectar");
        trabajo5.setTrabajador(trabajadorDB3);
        trabajo5.setObservaciones("-");
        trabajo5.setLocalizacion("A Barca");
        trabajo5.setInicioTrabajo(fechaInicio2);
        trabajo5.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado5=trabajoRepository.save(trabajo5);



        String fechaInicioStrBusca = "2021-07-30 00:00";
        String fechaFinStrBusca = "2021-07-31 20:00";
        LocalDateTime fechaInicioBusca = LocalDateTime.parse(fechaInicioStrBusca, formatter);
        LocalDateTime fechaFinBusca = LocalDateTime.parse(fechaFinStrBusca, formatter);
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByFechasYLocalizacion(fechaInicioBusca, fechaFinBusca, "A Barca");

        assertEquals(4, trabajoBuscado2.size());
        assertEquals(trabajoEntregado, trabajoBuscado2.get(0));
        assertEquals(trabajoEntregado2, trabajoBuscado2.get(1));
        assertEquals(trabajoEntregado3, trabajoBuscado2.get(2));
        assertEquals(trabajoEntregado5, trabajoBuscado2.get(3));



        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajoRepository.deleteById(trabajoEntregado2.getId());
        trabajoRepository.deleteById(trabajoEntregado3.getId());
        trabajoRepository.deleteById(trabajoEntregado4.getId());
        trabajoRepository.deleteById(trabajoEntregado5.getId());

        trabajadorRepository.deleteById(trabajadorDB.getId());
        trabajadorRepository.deleteById(trabajadorDB2.getId());
        trabajadorRepository.deleteById(trabajadorDB3.getId());


    }


    @Test
    void testAñadeTrabajoyBuscaPorFechasyTipoTrabajo(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajador2 = creaTrabajadorNormal2();
        Trabajador trabajador3 = creaTrabajadorNormal3();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        Trabajador trabajadorDB2=trabajadorRepository.save(trabajador2);
        Trabajador trabajadorDB3=trabajadorRepository.save(trabajador3);

        Trabajo trabajo = new Trabajo();
        trabajo.setTipo_trabajo("Clasificar");
        trabajo.setTrabajador(trabajadorDB);
        trabajo.setObservaciones("-");
        trabajo.setLocalizacion("A Barca");
        String fechaInicioStr = "2021-07-30 10:00";
        String fechaFinStr = "2021-07-30 18:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
        trabajo.setInicioTrabajo(fechaInicio);
        trabajo.setFinalTrabajo(fechaFin);
        Trabajo trabajoEntregado=trabajoRepository.save(trabajo);


        Trabajo trabajo2 = new Trabajo();
        trabajo2.setTipo_trabajo("Recolectar");
        trabajo2.setTrabajador(trabajadorDB);
        trabajo2.setObservaciones("-");
        trabajo2.setLocalizacion("A Barca");
        String fechaInicioStr2 = "2021-07-31 00:00";
        String fechaFinStr2 = "2021-07-31 08:00";
        LocalDateTime fechaInicio2 = LocalDateTime.parse(fechaInicioStr2, formatter);
        LocalDateTime fechaFin2 = LocalDateTime.parse(fechaFinStr2, formatter);
        trabajo2.setInicioTrabajo(fechaInicio2);
        trabajo2.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado2=trabajoRepository.save(trabajo2);

        Trabajo trabajo3 = new Trabajo();
        trabajo3.setTipo_trabajo("Recolectar");
        trabajo3.setTrabajador(trabajadorDB2);
        trabajo3.setObservaciones("-");
        trabajo3.setLocalizacion("A Barca");
        String fechaInicioStr3 = "2021-07-31 00:00";
        String fechaFinStr3 = "2021-07-31 08:00";
        LocalDateTime fechaInicio3 = LocalDateTime.parse(fechaInicioStr3, formatter);
        LocalDateTime fechaFin3 = LocalDateTime.parse(fechaFinStr3, formatter);
        trabajo3.setInicioTrabajo(fechaInicio3);
        trabajo3.setFinalTrabajo(fechaFin3);
        Trabajo trabajoEntregado3=trabajoRepository.save(trabajo3);

        Trabajo trabajo4 = new Trabajo();
        trabajo4.setTipo_trabajo("Recolectar");
        trabajo4.setTrabajador(trabajadorDB2);
        trabajo4.setObservaciones("-");
        trabajo4.setLocalizacion("Casa");
        String fechaInicioStr4 = "2021-07-31 18:00";
        String fechaFinStr4 = "2021-07-31 10:00";
        LocalDateTime fechaInicio4 = LocalDateTime.parse(fechaInicioStr4, formatter);
        LocalDateTime fechaFin4 = LocalDateTime.parse(fechaFinStr4, formatter);
        trabajo4.setInicioTrabajo(fechaInicio4);
        trabajo4.setFinalTrabajo(fechaFin4);
        Trabajo trabajoEntregado4=trabajoRepository.save(trabajo4);

        Trabajo trabajo5 = new Trabajo();
        trabajo5.setTipo_trabajo("Recolectar");
        trabajo5.setTrabajador(trabajadorDB3);
        trabajo5.setObservaciones("-");
        trabajo5.setLocalizacion("A Barca");
        trabajo5.setInicioTrabajo(fechaInicio2);
        trabajo5.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado5=trabajoRepository.save(trabajo5);



        String fechaInicioStrBusca = "2021-07-30 00:00";
        String fechaFinStrBusca = "2021-07-31 20:00";
        LocalDateTime fechaInicioBusca = LocalDateTime.parse(fechaInicioStrBusca, formatter);
        LocalDateTime fechaFinBusca = LocalDateTime.parse(fechaFinStrBusca, formatter);
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByFechasYTipo_trabajo(fechaInicioBusca, fechaFinBusca, "Clasificar");

        assertEquals(1, trabajoBuscado2.size());

        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajoRepository.deleteById(trabajoEntregado2.getId());
        trabajoRepository.deleteById(trabajoEntregado3.getId());
        trabajoRepository.deleteById(trabajoEntregado4.getId());
        trabajoRepository.deleteById(trabajoEntregado5.getId());

        trabajadorRepository.deleteById(trabajadorDB.getId());
        trabajadorRepository.deleteById(trabajadorDB2.getId());
        trabajadorRepository.deleteById(trabajadorDB3.getId());
    }


    @Test
    void testAñadeTrabajoyBuscaPorLocalizacionYTipoTrabajo(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajador2 = creaTrabajadorNormal2();
        Trabajador trabajador3 = creaTrabajadorNormal3();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        Trabajador trabajadorDB2=trabajadorRepository.save(trabajador2);
        Trabajador trabajadorDB3=trabajadorRepository.save(trabajador3);

        Trabajo trabajo = new Trabajo();
        trabajo.setTipo_trabajo("Clasificar");
        trabajo.setTrabajador(trabajadorDB);
        trabajo.setObservaciones("-");
        trabajo.setLocalizacion("A Barca");
        String fechaInicioStr = "2021-07-30 10:00";
        String fechaFinStr = "2021-07-30 18:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
        trabajo.setInicioTrabajo(fechaInicio);
        trabajo.setFinalTrabajo(fechaFin);
        Trabajo trabajoEntregado=trabajoRepository.save(trabajo);


        Trabajo trabajo2 = new Trabajo();
        trabajo2.setTipo_trabajo("Recolectar");
        trabajo2.setTrabajador(trabajadorDB);
        trabajo2.setObservaciones("-");
        trabajo2.setLocalizacion("A Barca");
        String fechaInicioStr2 = "2021-07-31 00:00";
        String fechaFinStr2 = "2021-07-31 08:00";
        LocalDateTime fechaInicio2 = LocalDateTime.parse(fechaInicioStr2, formatter);
        LocalDateTime fechaFin2 = LocalDateTime.parse(fechaFinStr2, formatter);
        trabajo2.setInicioTrabajo(fechaInicio2);
        trabajo2.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado2=trabajoRepository.save(trabajo2);

        Trabajo trabajo3 = new Trabajo();
        trabajo3.setTipo_trabajo("Recolectar");
        trabajo3.setTrabajador(trabajadorDB2);
        trabajo3.setObservaciones("-");
        trabajo3.setLocalizacion("A Barca");
        String fechaInicioStr3 = "2021-07-31 00:00";
        String fechaFinStr3 = "2021-07-31 08:00";
        LocalDateTime fechaInicio3 = LocalDateTime.parse(fechaInicioStr3, formatter);
        LocalDateTime fechaFin3 = LocalDateTime.parse(fechaFinStr3, formatter);
        trabajo3.setInicioTrabajo(fechaInicio3);
        trabajo3.setFinalTrabajo(fechaFin3);
        Trabajo trabajoEntregado3=trabajoRepository.save(trabajo3);

        Trabajo trabajo4 = new Trabajo();
        trabajo4.setTipo_trabajo("Recolectar");
        trabajo4.setTrabajador(trabajadorDB2);
        trabajo4.setObservaciones("-");
        trabajo4.setLocalizacion("Casa");
        String fechaInicioStr4 = "2021-07-31 18:00";
        String fechaFinStr4 = "2021-07-31 10:00";
        LocalDateTime fechaInicio4 = LocalDateTime.parse(fechaInicioStr4, formatter);
        LocalDateTime fechaFin4 = LocalDateTime.parse(fechaFinStr4, formatter);
        trabajo4.setInicioTrabajo(fechaInicio4);
        trabajo4.setFinalTrabajo(fechaFin4);
        Trabajo trabajoEntregado4=trabajoRepository.save(trabajo4);

        Trabajo trabajo5 = new Trabajo();
        trabajo5.setTipo_trabajo("Recolectar");
        trabajo5.setTrabajador(trabajadorDB3);
        trabajo5.setObservaciones("-");
        trabajo5.setLocalizacion("A Barca");
        trabajo5.setInicioTrabajo(fechaInicio2);
        trabajo5.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado5=trabajoRepository.save(trabajo5);



        String fechaInicioStrBusca = "2021-07-30 00:00";
        String fechaFinStrBusca = "2021-07-31 20:00";
        LocalDateTime fechaInicioBusca = LocalDateTime.parse(fechaInicioStrBusca, formatter);
        LocalDateTime fechaFinBusca = LocalDateTime.parse(fechaFinStrBusca, formatter);
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByLocalizacionYTipo_trabajo("A Barca", "Clasificar");

        assertEquals(1, trabajoBuscado2.size());
        assertEquals(trabajoEntregado, trabajoBuscado2.get(0));

        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajoRepository.deleteById(trabajoEntregado2.getId());
        trabajoRepository.deleteById(trabajoEntregado3.getId());
        trabajoRepository.deleteById(trabajoEntregado4.getId());
        trabajoRepository.deleteById(trabajoEntregado5.getId());

        trabajadorRepository.deleteById(trabajadorDB.getId());
        trabajadorRepository.deleteById(trabajadorDB2.getId());
        trabajadorRepository.deleteById(trabajadorDB3.getId());


    }


    @Test
    void testAñadeTrabajoyBuscaPorUsuarioFechasYLocalizacion(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajador2 = creaTrabajadorNormal2();
        Trabajador trabajador3 = creaTrabajadorNormal3();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        Trabajador trabajadorDB2=trabajadorRepository.save(trabajador2);
        Trabajador trabajadorDB3=trabajadorRepository.save(trabajador3);

        Trabajo trabajo = new Trabajo();
        trabajo.setTipo_trabajo("Clasificar");
        trabajo.setTrabajador(trabajadorDB);
        trabajo.setObservaciones("-");
        trabajo.setLocalizacion("A Barca");
        String fechaInicioStr = "2021-07-30 10:00";
        String fechaFinStr = "2021-07-30 18:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
        trabajo.setInicioTrabajo(fechaInicio);
        trabajo.setFinalTrabajo(fechaFin);
        Trabajo trabajoEntregado=trabajoRepository.save(trabajo);


        Trabajo trabajo2 = new Trabajo();
        trabajo2.setTipo_trabajo("Recolectar");
        trabajo2.setTrabajador(trabajadorDB);
        trabajo2.setObservaciones("-");
        trabajo2.setLocalizacion("A Barca");
        String fechaInicioStr2 = "2021-07-31 00:00";
        String fechaFinStr2 = "2021-07-31 08:00";
        LocalDateTime fechaInicio2 = LocalDateTime.parse(fechaInicioStr2, formatter);
        LocalDateTime fechaFin2 = LocalDateTime.parse(fechaFinStr2, formatter);
        trabajo2.setInicioTrabajo(fechaInicio2);
        trabajo2.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado2=trabajoRepository.save(trabajo2);

        Trabajo trabajo3 = new Trabajo();
        trabajo3.setTipo_trabajo("Recolectar");
        trabajo3.setTrabajador(trabajadorDB2);
        trabajo3.setObservaciones("-");
        trabajo3.setLocalizacion("A Barca");
        String fechaInicioStr3 = "2021-07-31 00:00";
        String fechaFinStr3 = "2021-07-31 08:00";
        LocalDateTime fechaInicio3 = LocalDateTime.parse(fechaInicioStr3, formatter);
        LocalDateTime fechaFin3 = LocalDateTime.parse(fechaFinStr3, formatter);
        trabajo3.setInicioTrabajo(fechaInicio3);
        trabajo3.setFinalTrabajo(fechaFin3);
        Trabajo trabajoEntregado3=trabajoRepository.save(trabajo3);

        Trabajo trabajo4 = new Trabajo();
        trabajo4.setTipo_trabajo("Recolectar");
        trabajo4.setTrabajador(trabajadorDB2);
        trabajo4.setObservaciones("-");
        trabajo4.setLocalizacion("Casa");
        String fechaInicioStr4 = "2021-07-31 18:00";
        String fechaFinStr4 = "2021-07-31 10:00";
        LocalDateTime fechaInicio4 = LocalDateTime.parse(fechaInicioStr4, formatter);
        LocalDateTime fechaFin4 = LocalDateTime.parse(fechaFinStr4, formatter);
        trabajo4.setInicioTrabajo(fechaInicio4);
        trabajo4.setFinalTrabajo(fechaFin4);
        Trabajo trabajoEntregado4=trabajoRepository.save(trabajo4);

        Trabajo trabajo5 = new Trabajo();
        trabajo5.setTipo_trabajo("Recolectar");
        trabajo5.setTrabajador(trabajadorDB3);
        trabajo5.setObservaciones("-");
        trabajo5.setLocalizacion("A Barca");
        trabajo5.setInicioTrabajo(fechaInicio2);
        trabajo5.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado5=trabajoRepository.save(trabajo5);



        String fechaInicioStrBusca = "2021-07-30 00:00";
        String fechaFinStrBusca = "2021-07-31 20:00";
        LocalDateTime fechaInicioBusca = LocalDateTime.parse(fechaInicioStrBusca, formatter);
        LocalDateTime fechaFinBusca = LocalDateTime.parse(fechaFinStrBusca, formatter);
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByUsuarioFechasyLocalizacion(trabajador.getUsername(),
                fechaInicioBusca, fechaFinBusca,"A Barca");

        assertEquals(2, trabajoBuscado2.size());
        assertEquals(trabajoEntregado, trabajoBuscado2.get(0));
        assertEquals(trabajoEntregado2, trabajoBuscado2.get(1));

        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajoRepository.deleteById(trabajoEntregado2.getId());
        trabajoRepository.deleteById(trabajoEntregado3.getId());
        trabajoRepository.deleteById(trabajoEntregado4.getId());
        trabajoRepository.deleteById(trabajoEntregado5.getId());

        trabajadorRepository.deleteById(trabajadorDB.getId());
        trabajadorRepository.deleteById(trabajadorDB2.getId());
        trabajadorRepository.deleteById(trabajadorDB3.getId());


    }


    @Test
    void testAñadeTrabajoyBuscaPorUsuarioFechasYTipoTrabajo(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajador2 = creaTrabajadorNormal2();
        Trabajador trabajador3 = creaTrabajadorNormal3();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        Trabajador trabajadorDB2=trabajadorRepository.save(trabajador2);
        Trabajador trabajadorDB3=trabajadorRepository.save(trabajador3);

        Trabajo trabajo = new Trabajo();
        trabajo.setTipo_trabajo("Clasificar");
        trabajo.setTrabajador(trabajadorDB);
        trabajo.setObservaciones("-");
        trabajo.setLocalizacion("A Barca");
        String fechaInicioStr = "2021-07-30 10:00";
        String fechaFinStr = "2021-07-30 18:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
        trabajo.setInicioTrabajo(fechaInicio);
        trabajo.setFinalTrabajo(fechaFin);
        Trabajo trabajoEntregado=trabajoRepository.save(trabajo);


        Trabajo trabajo2 = new Trabajo();
        trabajo2.setTipo_trabajo("Recolectar");
        trabajo2.setTrabajador(trabajadorDB);
        trabajo2.setObservaciones("-");
        trabajo2.setLocalizacion("A Barca");
        String fechaInicioStr2 = "2021-07-31 00:00";
        String fechaFinStr2 = "2021-07-31 08:00";
        LocalDateTime fechaInicio2 = LocalDateTime.parse(fechaInicioStr2, formatter);
        LocalDateTime fechaFin2 = LocalDateTime.parse(fechaFinStr2, formatter);
        trabajo2.setInicioTrabajo(fechaInicio2);
        trabajo2.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado2=trabajoRepository.save(trabajo2);

        Trabajo trabajo3 = new Trabajo();
        trabajo3.setTipo_trabajo("Recolectar");
        trabajo3.setTrabajador(trabajadorDB2);
        trabajo3.setObservaciones("-");
        trabajo3.setLocalizacion("A Barca");
        String fechaInicioStr3 = "2021-07-31 00:00";
        String fechaFinStr3 = "2021-07-31 08:00";
        LocalDateTime fechaInicio3 = LocalDateTime.parse(fechaInicioStr3, formatter);
        LocalDateTime fechaFin3 = LocalDateTime.parse(fechaFinStr3, formatter);
        trabajo3.setInicioTrabajo(fechaInicio3);
        trabajo3.setFinalTrabajo(fechaFin3);
        Trabajo trabajoEntregado3=trabajoRepository.save(trabajo3);

        Trabajo trabajo4 = new Trabajo();
        trabajo4.setTipo_trabajo("Recolectar");
        trabajo4.setTrabajador(trabajadorDB2);
        trabajo4.setObservaciones("-");
        trabajo4.setLocalizacion("Casa");
        String fechaInicioStr4 = "2021-07-31 18:00";
        String fechaFinStr4 = "2021-07-31 10:00";
        LocalDateTime fechaInicio4 = LocalDateTime.parse(fechaInicioStr4, formatter);
        LocalDateTime fechaFin4 = LocalDateTime.parse(fechaFinStr4, formatter);
        trabajo4.setInicioTrabajo(fechaInicio4);
        trabajo4.setFinalTrabajo(fechaFin4);
        Trabajo trabajoEntregado4=trabajoRepository.save(trabajo4);

        Trabajo trabajo5 = new Trabajo();
        trabajo5.setTipo_trabajo("Recolectar");
        trabajo5.setTrabajador(trabajadorDB3);
        trabajo5.setObservaciones("-");
        trabajo5.setLocalizacion("A Barca");
        trabajo5.setInicioTrabajo(fechaInicio2);
        trabajo5.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado5=trabajoRepository.save(trabajo5);



        String fechaInicioStrBusca = "2021-07-30 00:00";
        String fechaFinStrBusca = "2021-07-31 20:00";
        LocalDateTime fechaInicioBusca = LocalDateTime.parse(fechaInicioStrBusca, formatter);
        LocalDateTime fechaFinBusca = LocalDateTime.parse(fechaFinStrBusca, formatter);
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByUsuarioFechasyTipo_trabajo(trabajador.getUsername(),
                fechaInicioBusca, fechaFinBusca,"Recolectar");

        assertEquals(1, trabajoBuscado2.size());
        assertEquals(trabajoEntregado2, trabajoBuscado2.get(0));

        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajoRepository.deleteById(trabajoEntregado2.getId());
        trabajoRepository.deleteById(trabajoEntregado3.getId());
        trabajoRepository.deleteById(trabajoEntregado4.getId());
        trabajoRepository.deleteById(trabajoEntregado5.getId());

        trabajadorRepository.deleteById(trabajadorDB.getId());
        trabajadorRepository.deleteById(trabajadorDB2.getId());
        trabajadorRepository.deleteById(trabajadorDB3.getId());


    }

    @Test
    void testAñadeTrabajoyBuscaPorUsuarioLocalizacionYTipoTrabajo(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajador2 = creaTrabajadorNormal2();
        Trabajador trabajador3 = creaTrabajadorNormal3();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        Trabajador trabajadorDB2=trabajadorRepository.save(trabajador2);
        Trabajador trabajadorDB3=trabajadorRepository.save(trabajador3);

        Trabajo trabajo = new Trabajo();
        trabajo.setTipo_trabajo("Clasificar");
        trabajo.setTrabajador(trabajadorDB);
        trabajo.setObservaciones("-");
        trabajo.setLocalizacion("A Barca");
        String fechaInicioStr = "2021-07-30 10:00";
        String fechaFinStr = "2021-07-30 18:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
        trabajo.setInicioTrabajo(fechaInicio);
        trabajo.setFinalTrabajo(fechaFin);
        Trabajo trabajoEntregado=trabajoRepository.save(trabajo);


        Trabajo trabajo2 = new Trabajo();
        trabajo2.setTipo_trabajo("Recolectar");
        trabajo2.setTrabajador(trabajadorDB);
        trabajo2.setObservaciones("-");
        trabajo2.setLocalizacion("A Barca");
        String fechaInicioStr2 = "2021-07-31 00:00";
        String fechaFinStr2 = "2021-07-31 08:00";
        LocalDateTime fechaInicio2 = LocalDateTime.parse(fechaInicioStr2, formatter);
        LocalDateTime fechaFin2 = LocalDateTime.parse(fechaFinStr2, formatter);
        trabajo2.setInicioTrabajo(fechaInicio2);
        trabajo2.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado2=trabajoRepository.save(trabajo2);

        Trabajo trabajo3 = new Trabajo();
        trabajo3.setTipo_trabajo("Recolectar");
        trabajo3.setTrabajador(trabajadorDB2);
        trabajo3.setObservaciones("-");
        trabajo3.setLocalizacion("A Barca");
        String fechaInicioStr3 = "2021-07-31 00:00";
        String fechaFinStr3 = "2021-07-31 08:00";
        LocalDateTime fechaInicio3 = LocalDateTime.parse(fechaInicioStr3, formatter);
        LocalDateTime fechaFin3 = LocalDateTime.parse(fechaFinStr3, formatter);
        trabajo3.setInicioTrabajo(fechaInicio3);
        trabajo3.setFinalTrabajo(fechaFin3);
        Trabajo trabajoEntregado3=trabajoRepository.save(trabajo3);

        Trabajo trabajo4 = new Trabajo();
        trabajo4.setTipo_trabajo("Recolectar");
        trabajo4.setTrabajador(trabajadorDB2);
        trabajo4.setObservaciones("-");
        trabajo4.setLocalizacion("Casa");
        String fechaInicioStr4 = "2021-07-31 18:00";
        String fechaFinStr4 = "2021-07-31 10:00";
        LocalDateTime fechaInicio4 = LocalDateTime.parse(fechaInicioStr4, formatter);
        LocalDateTime fechaFin4 = LocalDateTime.parse(fechaFinStr4, formatter);
        trabajo4.setInicioTrabajo(fechaInicio4);
        trabajo4.setFinalTrabajo(fechaFin4);
        Trabajo trabajoEntregado4=trabajoRepository.save(trabajo4);

        Trabajo trabajo5 = new Trabajo();
        trabajo5.setTipo_trabajo("Recolectar");
        trabajo5.setTrabajador(trabajadorDB3);
        trabajo5.setObservaciones("-");
        trabajo5.setLocalizacion("A Barca");
        trabajo5.setInicioTrabajo(fechaInicio2);
        trabajo5.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado5=trabajoRepository.save(trabajo5);



        String fechaInicioStrBusca = "2021-07-30 00:00";
        String fechaFinStrBusca = "2021-07-31 20:00";
        LocalDateTime fechaInicioBusca = LocalDateTime.parse(fechaInicioStrBusca, formatter);
        LocalDateTime fechaFinBusca = LocalDateTime.parse(fechaFinStrBusca, formatter);
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByUsuarioLocalizacionYTipo_trabajo(trabajador.getUsername(),
                "A barca","Recolectar");

        assertEquals(1, trabajoBuscado2.size());
        assertEquals(trabajoEntregado2, trabajoBuscado2.get(0));

        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajoRepository.deleteById(trabajoEntregado2.getId());
        trabajoRepository.deleteById(trabajoEntregado3.getId());
        trabajoRepository.deleteById(trabajoEntregado4.getId());
        trabajoRepository.deleteById(trabajoEntregado5.getId());

        trabajadorRepository.deleteById(trabajadorDB.getId());
        trabajadorRepository.deleteById(trabajadorDB2.getId());
        trabajadorRepository.deleteById(trabajadorDB3.getId());


    }

    @Test
    void testAñadeTrabajoyBuscaPorFechasLocalizacionYTipoTrabajo(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajador2 = creaTrabajadorNormal2();
        Trabajador trabajador3 = creaTrabajadorNormal3();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        Trabajador trabajadorDB2=trabajadorRepository.save(trabajador2);
        Trabajador trabajadorDB3=trabajadorRepository.save(trabajador3);

        Trabajo trabajo = new Trabajo();
        trabajo.setTipo_trabajo("Clasificar");
        trabajo.setTrabajador(trabajadorDB);
        trabajo.setObservaciones("-");
        trabajo.setLocalizacion("A Barca");
        String fechaInicioStr = "2021-07-30 10:00";
        String fechaFinStr = "2021-07-30 18:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
        trabajo.setInicioTrabajo(fechaInicio);
        trabajo.setFinalTrabajo(fechaFin);
        Trabajo trabajoEntregado=trabajoRepository.save(trabajo);


        Trabajo trabajo2 = new Trabajo();
        trabajo2.setTipo_trabajo("Recolectar");
        trabajo2.setTrabajador(trabajadorDB);
        trabajo2.setObservaciones("-");
        trabajo2.setLocalizacion("A Barca");
        String fechaInicioStr2 = "2021-07-31 00:00";
        String fechaFinStr2 = "2021-07-31 08:00";
        LocalDateTime fechaInicio2 = LocalDateTime.parse(fechaInicioStr2, formatter);
        LocalDateTime fechaFin2 = LocalDateTime.parse(fechaFinStr2, formatter);
        trabajo2.setInicioTrabajo(fechaInicio2);
        trabajo2.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado2=trabajoRepository.save(trabajo2);

        Trabajo trabajo3 = new Trabajo();
        trabajo3.setTipo_trabajo("Recolectar");
        trabajo3.setTrabajador(trabajadorDB2);
        trabajo3.setObservaciones("-");
        trabajo3.setLocalizacion("A Barca");
        String fechaInicioStr3 = "2021-07-31 00:00";
        String fechaFinStr3 = "2021-07-31 08:00";
        LocalDateTime fechaInicio3 = LocalDateTime.parse(fechaInicioStr3, formatter);
        LocalDateTime fechaFin3 = LocalDateTime.parse(fechaFinStr3, formatter);
        trabajo3.setInicioTrabajo(fechaInicio3);
        trabajo3.setFinalTrabajo(fechaFin3);
        Trabajo trabajoEntregado3=trabajoRepository.save(trabajo3);

        Trabajo trabajo4 = new Trabajo();
        trabajo4.setTipo_trabajo("Recolectar");
        trabajo4.setTrabajador(trabajadorDB2);
        trabajo4.setObservaciones("-");
        trabajo4.setLocalizacion("Casa");
        String fechaInicioStr4 = "2021-07-31 18:00";
        String fechaFinStr4 = "2021-07-31 10:00";
        LocalDateTime fechaInicio4 = LocalDateTime.parse(fechaInicioStr4, formatter);
        LocalDateTime fechaFin4 = LocalDateTime.parse(fechaFinStr4, formatter);
        trabajo4.setInicioTrabajo(fechaInicio4);
        trabajo4.setFinalTrabajo(fechaFin4);
        Trabajo trabajoEntregado4=trabajoRepository.save(trabajo4);

        Trabajo trabajo5 = new Trabajo();
        trabajo5.setTipo_trabajo("Recolectar");
        trabajo5.setTrabajador(trabajadorDB3);
        trabajo5.setObservaciones("-");
        trabajo5.setLocalizacion("A Barca");
        trabajo5.setInicioTrabajo(fechaInicio2);
        trabajo5.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado5=trabajoRepository.save(trabajo5);



        String fechaInicioStrBusca = "2021-07-30 00:00";
        String fechaFinStrBusca = "2021-07-31 20:00";
        LocalDateTime fechaInicioBusca = LocalDateTime.parse(fechaInicioStrBusca, formatter);
        LocalDateTime fechaFinBusca = LocalDateTime.parse(fechaFinStrBusca, formatter);
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByFechasLocalizacionyTipo_trabajo(fechaInicioBusca,fechaFinBusca,
                "A barca","Recolectar");

        assertEquals(3, trabajoBuscado2.size());
        assertEquals(trabajoEntregado2, trabajoBuscado2.get(0));
        assertEquals(trabajoEntregado3, trabajoBuscado2.get(1));
        assertEquals(trabajoEntregado5, trabajoBuscado2.get(2));


        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajoRepository.deleteById(trabajoEntregado2.getId());
        trabajoRepository.deleteById(trabajoEntregado3.getId());
        trabajoRepository.deleteById(trabajoEntregado4.getId());
        trabajoRepository.deleteById(trabajoEntregado5.getId());

        trabajadorRepository.deleteById(trabajadorDB.getId());
        trabajadorRepository.deleteById(trabajadorDB2.getId());
        trabajadorRepository.deleteById(trabajadorDB3.getId());


    }

    @Test
    void testAñadeTrabajoyBuscaPorTodo(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajador2 = creaTrabajadorNormal2();
        Trabajador trabajador3 = creaTrabajadorNormal3();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        Trabajador trabajadorDB2=trabajadorRepository.save(trabajador2);
        Trabajador trabajadorDB3=trabajadorRepository.save(trabajador3);

        Trabajo trabajo = new Trabajo();
        trabajo.setTipo_trabajo("Clasificar");
        trabajo.setTrabajador(trabajadorDB);
        trabajo.setObservaciones("-");
        trabajo.setLocalizacion("A Barca");
        String fechaInicioStr = "2021-07-30 10:00";
        String fechaFinStr = "2021-07-30 18:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
        trabajo.setInicioTrabajo(fechaInicio);
        trabajo.setFinalTrabajo(fechaFin);
        Trabajo trabajoEntregado=trabajoRepository.save(trabajo);


        Trabajo trabajo2 = new Trabajo();
        trabajo2.setTipo_trabajo("Recolectar");
        trabajo2.setTrabajador(trabajadorDB);
        trabajo2.setObservaciones("-");
        trabajo2.setLocalizacion("A Barca");
        String fechaInicioStr2 = "2021-07-31 00:00";
        String fechaFinStr2 = "2021-07-31 08:00";
        LocalDateTime fechaInicio2 = LocalDateTime.parse(fechaInicioStr2, formatter);
        LocalDateTime fechaFin2 = LocalDateTime.parse(fechaFinStr2, formatter);
        trabajo2.setInicioTrabajo(fechaInicio2);
        trabajo2.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado2=trabajoRepository.save(trabajo2);

        Trabajo trabajo3 = new Trabajo();
        trabajo3.setTipo_trabajo("Recolectar");
        trabajo3.setTrabajador(trabajadorDB2);
        trabajo3.setObservaciones("-");
        trabajo3.setLocalizacion("A Barca");
        String fechaInicioStr3 = "2021-07-31 00:00";
        String fechaFinStr3 = "2021-07-31 08:00";
        LocalDateTime fechaInicio3 = LocalDateTime.parse(fechaInicioStr3, formatter);
        LocalDateTime fechaFin3 = LocalDateTime.parse(fechaFinStr3, formatter);
        trabajo3.setInicioTrabajo(fechaInicio3);
        trabajo3.setFinalTrabajo(fechaFin3);
        Trabajo trabajoEntregado3=trabajoRepository.save(trabajo3);

        Trabajo trabajo4 = new Trabajo();
        trabajo4.setTipo_trabajo("Recolectar");
        trabajo4.setTrabajador(trabajadorDB2);
        trabajo4.setObservaciones("-");
        trabajo4.setLocalizacion("Casa");
        String fechaInicioStr4 = "2021-07-31 18:00";
        String fechaFinStr4 = "2021-07-31 10:00";
        LocalDateTime fechaInicio4 = LocalDateTime.parse(fechaInicioStr4, formatter);
        LocalDateTime fechaFin4 = LocalDateTime.parse(fechaFinStr4, formatter);
        trabajo4.setInicioTrabajo(fechaInicio4);
        trabajo4.setFinalTrabajo(fechaFin4);
        Trabajo trabajoEntregado4=trabajoRepository.save(trabajo4);

        Trabajo trabajo5 = new Trabajo();
        trabajo5.setTipo_trabajo("Recolectar");
        trabajo5.setTrabajador(trabajadorDB3);
        trabajo5.setObservaciones("-");
        trabajo5.setLocalizacion("A Barca");
        trabajo5.setInicioTrabajo(fechaInicio2);
        trabajo5.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado5=trabajoRepository.save(trabajo5);



        String fechaInicioStrBusca = "2021-07-30 00:00";
        String fechaFinStrBusca = "2021-07-31 20:00";
        LocalDateTime fechaInicioBusca = LocalDateTime.parse(fechaInicioStrBusca, formatter);
        LocalDateTime fechaFinBusca = LocalDateTime.parse(fechaFinStrBusca, formatter);
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByUsuarioFechasLocalizacionyTipo_trabajo(trabajador.getUsername(),
                fechaInicioBusca, fechaFinBusca, trabajo.getLocalizacion(), trabajo.getTipo_trabajo());

        assertEquals(1, trabajoBuscado2.size());
        assertEquals(trabajoEntregado, trabajoBuscado2.get(0));

        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajoRepository.deleteById(trabajoEntregado2.getId());
        trabajoRepository.deleteById(trabajoEntregado3.getId());
        trabajoRepository.deleteById(trabajoEntregado4.getId());
        trabajoRepository.deleteById(trabajoEntregado5.getId());

        trabajadorRepository.deleteById(trabajadorDB.getId());
        trabajadorRepository.deleteById(trabajadorDB2.getId());
        trabajadorRepository.deleteById(trabajadorDB3.getId());


    }


    @Test
    void testAñadeTrabajoyBuscatrabajoSinFinalizar(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajador2 = creaTrabajadorNormal2();
        Trabajador trabajador3 = creaTrabajadorNormal3();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        Trabajador trabajadorDB2=trabajadorRepository.save(trabajador2);
        Trabajador trabajadorDB3=trabajadorRepository.save(trabajador3);

        Trabajo trabajo = new Trabajo();
        trabajo.setTipo_trabajo("Clasificar");
        trabajo.setTrabajador(trabajadorDB);
        trabajo.setObservaciones("-");
        trabajo.setLocalizacion("A Barca");
        String fechaInicioStr = "2021-07-30 10:00";
        String fechaFinStr = "2021-07-30 18:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaInicio = LocalDateTime.parse(fechaInicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(fechaFinStr, formatter);
        trabajo.setInicioTrabajo(fechaInicio);
        trabajo.setFinalTrabajo(fechaFin);
        Trabajo trabajoEntregado=trabajoRepository.save(trabajo);


        Trabajo trabajo2 = new Trabajo();
        trabajo2.setTipo_trabajo("Recolectar");
        trabajo2.setTrabajador(trabajadorDB);
        trabajo2.setObservaciones("-");
        trabajo2.setLocalizacion("A Barca");
        String fechaInicioStr2 = "2021-07-31 00:00";
        String fechaFinStr2 = "2021-07-31 08:00";
        LocalDateTime fechaInicio2 = LocalDateTime.parse(fechaInicioStr2, formatter);
        LocalDateTime fechaFin2 = LocalDateTime.parse(fechaFinStr2, formatter);
        trabajo2.setInicioTrabajo(fechaInicio2);
        trabajo2.setFinalTrabajo(fechaFin2);
        Trabajo trabajoEntregado2=trabajoRepository.save(trabajo2);

        Trabajo trabajo3 = new Trabajo();
        trabajo3.setTipo_trabajo("Recolectar");
        trabajo3.setTrabajador(trabajadorDB2);
        trabajo3.setObservaciones("-");
        trabajo3.setLocalizacion("A Barca");
        String fechaInicioStr3 = "2021-07-31 00:00";
        String fechaFinStr3 = "2021-07-31 08:00";
        LocalDateTime fechaInicio3 = LocalDateTime.parse(fechaInicioStr3, formatter);
        LocalDateTime fechaFin3 = LocalDateTime.parse(fechaFinStr3, formatter);
        trabajo3.setInicioTrabajo(fechaInicio3);
        trabajo3.setFinalTrabajo(fechaFin3);
        Trabajo trabajoEntregado3=trabajoRepository.save(trabajo3);

        Trabajo trabajo4 = new Trabajo();
        trabajo4.setTipo_trabajo("Recolectar");
        trabajo4.setTrabajador(trabajadorDB2);
        trabajo4.setObservaciones("-");
        trabajo4.setLocalizacion("Casa");
        String fechaInicioStr4 = "2021-07-31 18:00";
        String fechaFinStr4 = "2021-07-31 10:00";
        LocalDateTime fechaInicio4 = LocalDateTime.parse(fechaInicioStr4, formatter);
        LocalDateTime fechaFin4 = LocalDateTime.parse(fechaFinStr4, formatter);
        trabajo4.setInicioTrabajo(fechaInicio4);
        trabajo4.setFinalTrabajo(fechaFin4);
        Trabajo trabajoEntregado4=trabajoRepository.save(trabajo4);

        Trabajo trabajo5 = new Trabajo();
        trabajo5.setTipo_trabajo("Recolectar");
        trabajo5.setTrabajador(trabajadorDB3);
        trabajo5.setObservaciones("-");
        trabajo5.setLocalizacion("A Barca");
        trabajo5.setInicioTrabajo(fechaInicio2);
        Trabajo trabajoEntregado5=trabajoRepository.save(trabajo5);



        String fechaInicioStrBusca = "2021-07-30 00:00";
        String fechaFinStrBusca = "2021-07-31 20:00";
        LocalDateTime fechaInicioBusca = LocalDateTime.parse(fechaInicioStrBusca, formatter);
        LocalDateTime fechaFinBusca = LocalDateTime.parse(fechaFinStrBusca, formatter);
        Trabajo trabajoBuscado2 = trabajoRepository.findTrabajoSinFinalizarDeUsuario(trabajador3.getUsername());


        assertEquals(trabajoEntregado5, trabajoBuscado2);

        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajoRepository.deleteById(trabajoEntregado2.getId());
        trabajoRepository.deleteById(trabajoEntregado3.getId());
        trabajoRepository.deleteById(trabajoEntregado4.getId());
        trabajoRepository.deleteById(trabajoEntregado5.getId());

        trabajadorRepository.deleteById(trabajadorDB.getId());
        trabajadorRepository.deleteById(trabajadorDB2.getId());
        trabajadorRepository.deleteById(trabajadorDB3.getId());


    }
}
