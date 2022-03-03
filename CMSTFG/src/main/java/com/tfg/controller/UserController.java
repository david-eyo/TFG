package com.tfg.controller;

import com.tfg.dao.IAdminDao;
import com.tfg.dao.ITrabajadorDao;
import com.tfg.dao.IUser_generalDao;
import com.tfg.entity.Admin;
import com.tfg.entity.Trabajador;
import com.tfg.entity.User_general;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/secure/auth")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IAdminDao adminRepository;

    @Autowired
    private ITrabajadorDao trabajadorRepository;

    @Autowired
    private IUser_generalDao user_generalRepository;

    @PostMapping("/admin/add")
    public ResponseEntity<Map<String, Object>> addAdmin(@RequestBody Admin admin){

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        try{
            String pwd = admin.getPassword();
            String encryptedPwd = passwordEncoder.encode(pwd);
            admin.setPassword(encryptedPwd);
            Admin admin1 = adminRepository.save(admin);
            if (admin1 != null) {
                responseAsMap.put("administrador", admin);
                responseAsMap.put("mensaje", "el admin con id " + admin.getId() + " se ha creado con exito");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
            } else {
                responseAsMap.put("mensaje", "el admin no se ha creado correctamente");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
        responseAsMap.put("mensaje",
                "el administrador no se ha creado correctamente: " + e.getMessage());
        responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
    }

        return responseEntity;
    }

    @PostMapping("/trabajador/add")
    public ResponseEntity<Map<String, Object>> addTrabajador(@RequestBody Trabajador trabajador){

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        try{
            String pwd = trabajador.getPassword();
            String encryptedPwd = passwordEncoder.encode(pwd);
            trabajador.setPassword(encryptedPwd);
            Trabajador trabajador1=trabajadorRepository.save(trabajador);
            if (trabajador1 != null) {
                responseAsMap.put("trabajador", trabajador);
                responseAsMap.put("mensaje", "el trabajador con id " + trabajador.getId() + " se ha creado con exito");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
            } else {
                responseAsMap.put("mensaje", "el trabajador no se ha creado correctamente");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e) {
            responseAsMap.put("mensaje",
                    "el trabajador no se ha creado correctamente: " + e.getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping("/user/add")
    public ResponseEntity<Map<String, Object>> addUser_general(@RequestBody User_general user_general){

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        try{
            String pwd = user_general.getPassword();
            String encryptedPwd = passwordEncoder.encode(pwd);
            user_general.setPassword(encryptedPwd);
            User_general user=user_generalRepository.save(user_general);

            if (user != null) {
                responseAsMap.put("Usuario", user_general);
                responseAsMap.put("mensaje", "el usuario con id " + user_general.getId() + " se ha creado con exito");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CREATED);
            } else {
                responseAsMap.put("mensaje", "el usuario no se ha creado correctamente");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e) {
            responseAsMap.put("mensaje",
                    "el usuario no se ha creado correctamente: " + e.getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
