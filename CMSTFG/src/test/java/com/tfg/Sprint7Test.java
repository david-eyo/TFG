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
public class Sprint7Test {

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
    void testCreaYEliminaTrabajador() {
        creaLineas();

        log.info("Creando Trabajador" +
                ":");

        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);
        assertEquals(trabajador, trabajadorDB);
        trabajadorRepository.delete(trabajador);
    }

    @Test
    void testAñadeTrabajoyElimina(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);

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

        assertEquals(trabajo, trabajoEntregado);

        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajadorRepository.deleteById(trabajadorDB.getId());

    }


    @Test
    void testAñadeTrabajoyBuscaPorUsername(){
        creaLineas();
        Trabajador trabajador = creaTrabajadorNormal();
        Trabajador trabajadorDB=trabajadorRepository.save(trabajador);

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

        List<Trabajo> trabajoEntregado2 = trabajoRepository.findTrabajoByUsername(trabajoEntregado.getTrabajador().getUsername());

        assertEquals(trabajoEntregado, trabajoEntregado2.get(0));

        trabajoRepository.deleteById(trabajoEntregado.getId());
        trabajadorRepository.deleteById(trabajadorDB.getId());

    }

    @Test
    void testAñadeTrabajoyBuscaPorId(){
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

        Trabajo trabajoBuscado2 = trabajoRepository.findTrabajobyId(trabajoEntregado.getTrabajador().getId());

        assertEquals(trabajoEntregado, trabajoBuscado2);

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
    void testAñadeTrabajoyBuscaPorFechas(){
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
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByFechas(fechaInicioBusca, fechaFinBusca);

        assertEquals(trabajoEntregado, trabajoBuscado2.get(0));
        assertEquals(trabajoEntregado2, trabajoBuscado2.get(1));
        assertEquals(trabajoEntregado3, trabajoBuscado2.get(2));
        assertEquals(trabajoEntregado4, trabajoBuscado2.get(3));
        assertEquals(trabajoEntregado5, trabajoBuscado2.get(4));

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
    void testAñadeTrabajoyBuscaPorFechasSelectivas(){
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
        String fechaInicioStr4 = "2020-07-31 18:00";
        String fechaFinStr4 = "2020-07-31 10:00";
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
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByFechas(fechaInicioBusca, fechaFinBusca);

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
    void testAñadeTrabajoyBuscaPorLocalizacion(){
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

        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByLocalizacion("A Barca");

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
    void testAñadeTrabajoyBuscaPorTipoTrabajo(){
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

        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByTipoTrabajo("Clasificar");

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
    void testAñadeTrabajoyBuscaPorUsuarioyFechas(){
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
        List<Trabajo> trabajoBuscado2 = trabajoRepository.findTrabajoByUsuarioyFechas(trabajador.getUsername(),fechaInicioBusca, fechaFinBusca);

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



}
