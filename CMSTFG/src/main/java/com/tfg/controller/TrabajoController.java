package com.tfg.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tfg.dao.ITrabajadorDao;
import com.tfg.entity.*;
import com.tfg.service.CustomUserDetails;
import com.tfg.service.ITrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping(value = "/trabajo")
public class TrabajoController {

    @Autowired
    private ITrabajoService trabajoService;

    @Autowired
    private ITrabajadorDao trabajadorService;

    @GetMapping
    public ResponseEntity<List<Trabajo>> findPedidoByUsername(@RequestParam(required = false) String username,
                                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
                                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFin,
                                                             @RequestParam(required = false) String localizacion,
                                                             @RequestParam(required = false) String tipo_trabajo
    ) {

        ResponseEntity<List<Trabajo>> responseEntity = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal().equals("anonymousUser")){
            responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
            return responseEntity;
        }
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
        User usuario = currentPrincipalName.getUser();






        LocalDateTime fechaIni2 = null;
        LocalDateTime fechaFin2 = null;


        if (fechaInicio != null){
            fechaIni2 = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            if (fechaFin == null){
                fechaFin2 = LocalDateTime.now();
            }else {
                fechaFin2= fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
        }

        List<Trabajo> trabajo= null;

        if ((username != null) && (fechaInicio == null) && (localizacion == null) && (tipo_trabajo == null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo= trabajoService.findTrabajoByUsername(username);
        }else if ((username == null) && (fechaInicio != null) && (localizacion == null) && (tipo_trabajo == null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByFechas(fechaIni2, fechaFin2);
        }else if ((username == null) && (fechaInicio == null) && (localizacion != null) && (tipo_trabajo == null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByLocalizacion(localizacion);
        }else if ((username == null) && (fechaInicio == null) && (localizacion == null) && (tipo_trabajo != null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByTipoTrabajo(tipo_trabajo);
        }else if ((username != null) && (fechaInicio != null) && (localizacion == null) && (tipo_trabajo == null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByUsuarioyFechas(username, fechaIni2, fechaFin2);
        }else if ((username != null) && (fechaInicio == null) && (localizacion != null) && (tipo_trabajo == null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByUsuarioyLocalizacion(username, localizacion);
        }else if ((username != null) && (fechaInicio == null) && (localizacion == null) && (tipo_trabajo != null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByUsuarioyTipo_trabajo(username, tipo_trabajo);
        }else if ((username == null) && (fechaInicio != null) && (localizacion != null) && (tipo_trabajo == null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByFechasYLocalizacion(fechaIni2, fechaFin2, localizacion);
        }else if ((username == null) && (fechaInicio != null) && (localizacion == null) && (tipo_trabajo != null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByFechasYTipo_trabajo(fechaIni2, fechaFin2, tipo_trabajo);
        }else if ((username == null) && (fechaInicio == null) && (localizacion != null) && (tipo_trabajo != null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByLocalizacionYTipo_trabajo(localizacion, tipo_trabajo);
        }else if ((username != null) && (fechaInicio != null) && (localizacion != null) && (tipo_trabajo == null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByUsuarioFechasyLocalizacion(username, fechaIni2, fechaFin2, localizacion);
        }else if ((username != null) && (fechaInicio == null) && (localizacion != null) && (tipo_trabajo != null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByUsuarioLocalizacionYTipo_trabajo(username, localizacion, tipo_trabajo);
        }else if ((username != null) && (fechaInicio != null) && (localizacion == null) && (tipo_trabajo != null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByUsuarioFechasyTipo_trabajo(username, fechaIni2, fechaFin2, tipo_trabajo);
        }else if ((username == null) && (fechaInicio != null) && (localizacion != null) && (tipo_trabajo != null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByFechasLocalizacionyTipo_trabajo(fechaIni2, fechaFin2, localizacion, tipo_trabajo);
        }else if ((username != null) && (fechaInicio != null) && (localizacion != null) && (tipo_trabajo != null)){
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            trabajo=trabajoService.findTrabajoByUsuarioFechasLocalizacionyTipo_trabajo(username, fechaIni2, fechaFin2, localizacion, tipo_trabajo);
        }else{
            if((!usuario.getUsername().equals(username) && (usuario.getRol() != User.Rol.ROLE_ADMIN))){
                responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            responseEntity = new ResponseEntity<List<Trabajo>>((List<Trabajo>) null, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }



        if (trabajo != null) {
            responseEntity = new ResponseEntity<List<Trabajo>>(trabajo, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<List<Trabajo>>(trabajo, HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> addTrabajo(@RequestBody ObjectNode objectNode){

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        String username = objectNode.get("username").asText();
        String localizacion =  objectNode.get("localizacion").asText();
        String tipo_trabajo = objectNode.get("tipo_trabajo").asText();
        LocalDateTime fechaInicio = LocalDateTime.now();


        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication.getPrincipal().equals("anonymousUser")){
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
            User usuario =currentPrincipalName.getUser();

            if (usuario.getRol() == User.Rol.ROLE_USER){
                responseAsMap.put("mensaje",
                        "Los usuarios normales no pueden registrar un horario de trabajo.");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }

            Trabajo trabajoaentregar = new Trabajo();
            trabajoaentregar.setInicioTrabajo(fechaInicio);

            Trabajador trabajador = trabajadorService.findByUsername(username);
            if (trabajador == null){
                responseAsMap.put("mensaje",
                        "Trabajador no existente");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
                return responseEntity;
            }

            if((trabajador.getId() != usuario.getId())&&(usuario.getRol()!= User.Rol.ROLE_ADMIN)){
                responseAsMap.put("mensaje",
                        "se está intentando registrar un final de trabajo que no se corresponde a usted.");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }

            Trabajo trabajosinfinalizar = trabajoService.findTrabajoSinFinalizarDeUsuario(username);

            if (trabajosinfinalizar != null){
                responseAsMap.put("mensaje",
                        "Se está intentando iniciar un trabajo sin finalizar previamente el otro.");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
                return responseEntity;
            }

            trabajoaentregar.setTrabajador(trabajador);
            trabajoaentregar.setLocalizacion(localizacion);
            trabajoaentregar.setTipo_trabajo(tipo_trabajo);

            Trabajo trabajoentregado = trabajoService.saveTrabajo(trabajoaentregar);


            responseAsMap.put("Trabajo", trabajoentregado);
            responseAsMap.put("mensaje", "el trabajo " + trabajoentregado.getTipo_trabajo() + " por parte de "
                    + trabajoentregado.getTrabajador().getUsername() + "en localizacion" + trabajoentregado.getLocalizacion()
                    +" se ha registrado correctamente a la hora "+ trabajoentregado.getInicioTrabajo());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
        }catch (Exception e) {
            responseAsMap.put("mensaje",
                    "el trabajo no se ha podido registrar correctamente: " + e.getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> finalizarTrabajo(@RequestBody ObjectNode objectNode,
    @PathVariable long id){

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        String diaInicio = objectNode.get("diaInicio").asText();
        String horaInicio = objectNode.get("horaInicio").asText();
        String diaFin = objectNode.get("diaFin").asText();
        String horaFin = objectNode.get("horaFin").asText();
        String observaciones = objectNode.get("observaciones").asText();
        String localizacion= objectNode.get("localizacion").asText();
        String tipo_trabajo= objectNode.get("tipo_trabajo").asText();


        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
            User usuario =currentPrincipalName.getUser();

            if (usuario.getRol() != User.Rol.ROLE_ADMIN){
                responseAsMap.put("mensaje",
                        "Los usuarios normales no pueden registrar un horario de trabajo.");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }


            Trabajo trabajoaentregar= null;
            trabajoaentregar=trabajoService.findTrabajobyId(id);

            if (trabajoaentregar == null){
                responseAsMap.put("mensaje",
                        "El trabajador no tiene ningún trabajo iniciado");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CONFLICT);
                return responseEntity;
            }

            if (((diaInicio != null)&&(!diaInicio.equals(""))) && ((horaInicio != null)&&(!horaInicio.equals("")))){
                String str = diaInicio + " "+ horaInicio;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
                trabajoaentregar.setInicioTrabajo(dateTime);
            }

            if (((diaFin != null)&&(!diaFin.equals(""))) && ((horaFin != null)&&(!horaFin.equals("")))){
                String str = diaFin + " "+ horaFin;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
                trabajoaentregar.setFinalTrabajo(dateTime);
            }

            if ((observaciones != null)&&(!observaciones.equals(""))){
                trabajoaentregar.setObservaciones(observaciones);
            }

            if ((localizacion != null)&&(!localizacion.equals(""))){
                trabajoaentregar.setLocalizacion(localizacion);
            }

            if ((tipo_trabajo != null)&&(!tipo_trabajo.equals(""))){
                trabajoaentregar.setTipo_trabajo(tipo_trabajo);
            }



            Trabajo trabajoentregado = trabajoService.saveTrabajo(trabajoaentregar);


            responseAsMap.put("Trabajo", trabajoentregado);
            responseAsMap.put("mensaje", "el trabajo " + trabajoentregado.getTipo_trabajo() + " por parte de "
                    + trabajoentregado.getTrabajador().getUsername()
                    +" se ha registrado la hora de fin correctamente "+ trabajoentregado.getFinalTrabajo()+"con observaciones" +
                    trabajoentregado.getObservaciones());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
        }catch (Exception e) {
            responseAsMap.put("mensaje",
                    "el trabajo no se ha podido registrar correctamente: " + e.getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> finalizarTrabajoPorTrabajador(@RequestBody ObjectNode objectNode
                                                                ){

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        String username = objectNode.get("username").asText();
        LocalDateTime fechaFin = LocalDateTime.now();
        String observaciones = objectNode.get("observaciones").asText();

        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
            User usuario =currentPrincipalName.getUser();

            if (usuario.getRol() == User.Rol.ROLE_USER){
                responseAsMap.put("mensaje",
                        "Los usuarios normales no pueden registrar un horario de trabajo.");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }

            Trabajador trabajador = trabajadorService.findByUsername(username);
            if (trabajador == null){
                responseAsMap.put("mensaje",
                        "Trabajador no existente");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
                return responseEntity;
            }

            if((trabajador.getId() != usuario.getId())&&(usuario.getRol()!= User.Rol.ROLE_ADMIN)){
                responseAsMap.put("mensaje",
                        "se está intentando registrar un final de trabajo que no se corresponde a usted.");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            Trabajo trabajoaentregar= null;
            trabajoaentregar=trabajoService.findTrabajoSinFinalizarDeUsuario(username);

            if ((trabajoaentregar == null)|| (trabajoaentregar.getFinalTrabajo()!= null)){
                responseAsMap.put("mensaje",
                        "El trabajador no tiene ningún trabajo iniciado");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CONFLICT);
                return responseEntity;
            }

            trabajoaentregar.setObservaciones(observaciones);
            trabajoaentregar.setFinalTrabajo(fechaFin);


            Trabajo trabajoentregado = trabajoService.saveTrabajo(trabajoaentregar);


            responseAsMap.put("Trabajo", trabajoentregado);
            responseAsMap.put("mensaje", "el trabajo " + trabajoentregado.getTipo_trabajo() + " por parte de "
                    + trabajoentregado.getTrabajador().getUsername()
                    +" se ha registrado la hora de fin correctamente "+ trabajoentregado.getFinalTrabajo()+"con observaciones" +
                    trabajoentregado.getObservaciones());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
        }catch (Exception e) {
            responseAsMap.put("mensaje",
                    "el trabajo no se ha podido registrar correctamente: " + e.getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

}
