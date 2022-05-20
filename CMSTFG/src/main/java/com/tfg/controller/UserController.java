package com.tfg.controller;

import com.tfg.config.JWTTokenHelper;
import com.tfg.dao.IAdminDao;
import com.tfg.dao.ITrabajadorDao;
import com.tfg.dao.IUser_generalDao;
import com.tfg.entity.*;
import com.tfg.requests.AuthenticationRequest;
import com.tfg.responses.LoginResponse;
import com.tfg.responses.UserInfo;
import com.tfg.service.CustomUserDetailService;
import com.tfg.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
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

    @Autowired
    private CustomUserDetailService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTTokenHelper jWTTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/admin/add")
    public ResponseEntity<Map<String, Object>> addAdmin(@RequestBody Admin admin){

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        try{
            String pwd = admin.getPassword();
            String encryptedPwd = passwordEncoder.encode(pwd);
            admin.setPassword(encryptedPwd);

            if ((admin.getNombre() == null)){
                responseAsMap.put("mensaje", "Nombre no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((admin.getApellidos() == null)){
                responseAsMap.put("mensaje", "Apellidos no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((admin.getCiudad() == null)){
                responseAsMap.put("mensaje", "Ciudad no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((admin.getCp() == null)){
                responseAsMap.put("mensaje", "CP no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((admin.getDireccion() == null)){
                responseAsMap.put("mensaje", "Direccion no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((admin.getEmail() == null)){
                responseAsMap.put("mensaje", "Email no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((admin.getFechaNacimiento() == null)){
                responseAsMap.put("mensaje", "Fecha Nacimiento no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

            if ((admin.getTlf() == null)){
                responseAsMap.put("mensaje", "Teléfono no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((admin.getUsername() == null)){
                responseAsMap.put("mensaje", "Username no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if (userService.loadUserByUsername(admin.getUsername()) != null){
                responseAsMap.put("mensaje", "El username ya existe");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if (admin.getPassword() == null){
                responseAsMap.put("mensaje", "Password no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }



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
    public ResponseEntity<Map<String, Object>> addTrabajador(@RequestBody Trabajador trabajador)
    {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        try{
            User usuario = null;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!authentication.getPrincipal().equals("anonymousUser")){
                CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
                usuario =currentPrincipalName.getUser();
            }
            if ((usuario == null)||((usuario.getRol()!= User.Rol.ROLE_ADMIN))){
                responseAsMap.put("mensaje",
                        "No está autenticado correctamente. Pa" +
                                "" +
                                "ra ver esta informacion debe estar autenticado con la cuenta " +
                                "que desea obtener los datos");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }

            if ((trabajador.getNombre() == null)){
                responseAsMap.put("mensaje", "Nombre no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((trabajador.getApellidos() == null)){
                responseAsMap.put("mensaje", "Apellidos no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((trabajador.getCiudad() == null)){
                responseAsMap.put("mensaje", "Ciudad no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((trabajador.getCp() == null)){
                responseAsMap.put("mensaje", "CP no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((trabajador.getDireccion() == null)){
                responseAsMap.put("mensaje", "Direccion no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((trabajador.getEmail() == null)){
                responseAsMap.put("mensaje", "Email no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((trabajador.getFechaNacimiento() == null)){
                responseAsMap.put("mensaje", "Fecha Nacimiento no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

            if ((trabajador.getTlf() == null)){
                responseAsMap.put("mensaje", "Teléfono no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((trabajador.getUsername() == null)){
                responseAsMap.put("mensaje", "Username no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if (userService.loadUserByUsername(trabajador.getUsername()) != null){
                responseAsMap.put("mensaje", "El username ya existe");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if (trabajador.getPassword() == null){
                responseAsMap.put("mensaje", "Password no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if (trabajador.getDni() == null){
                responseAsMap.put("mensaje", "DNI no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if (trabajador.getNss() == null){
                responseAsMap.put("mensaje", "NSS no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
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
            if ((user_general.getNombre() == null)){
                responseAsMap.put("mensaje", "Nombre no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((user_general.getApellidos() == null)){
                responseAsMap.put("mensaje", "Apellidos no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((user_general.getCiudad() == null)){
                responseAsMap.put("mensaje", "Ciudad no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((user_general.getCp() == null)){
                responseAsMap.put("mensaje", "CP no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((user_general.getDireccion() == null)){
                responseAsMap.put("mensaje", "Direccion no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((user_general.getEmail() == null)){
                responseAsMap.put("mensaje", "Email no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((user_general.getFechaNacimiento() == null)){
                responseAsMap.put("mensaje", "Fecha Nacimiento no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

            if ((user_general.getTlf() == null)){
                responseAsMap.put("mensaje", "Teléfono no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((user_general.getUsername() == null)){
                responseAsMap.put("mensaje", "Username no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if (userService.loadUserByUsername(user_general.getUsername()) != null){
                responseAsMap.put("mensaje", "El username ya existe");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.CONFLICT);
            }
            if (user_general.getPassword() == null){
                responseAsMap.put("mensaje", "Password no puede ser nulo");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }


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

    @GetMapping
    public ResponseEntity<UserDetails> findUserByUsername(@RequestParam(required = false) String username)
    {


        User usuario = null;
        ResponseEntity<UserDetails> responseEntity = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")){
            CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
            usuario =currentPrincipalName.getUser();
        }else {
            responseEntity = new ResponseEntity<UserDetails>((UserDetails) null, HttpStatus.UNAUTHORIZED);
            return responseEntity;
        }

        if (username.equals("")){
            username = usuario.getUsername();
        }


        if ((usuario == null)||(!(usuario.getUsername().equals(username) )&&(usuario.getRol() != User.Rol.ROLE_ADMIN))){
            responseEntity = new ResponseEntity<UserDetails>((UserDetails) null, HttpStatus.UNAUTHORIZED);
            return responseEntity;
        }

        UserDetails usuario1 =  userService.loadUserByUsername(username);


        if (usuario1 != null) {

            responseEntity = new ResponseEntity<UserDetails>(usuario1, HttpStatus.OK);


        } else {
            responseEntity = new ResponseEntity<UserDetails>((UserDetails)null, HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable long id) {


        UserDetails user = userService.loadUserById(id);
        ResponseEntity<Map<String, Object>> responseEntity = null;
        Map<String, Object> responseAsMap = new HashMap<String, Object>();

        User usuario = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")){
            CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
            usuario =currentPrincipalName.getUser();
        }


        if ((usuario == null)||((usuario.getId() != id)&&(usuario.getRol()!= User.Rol.ROLE_ADMIN))){
            responseAsMap.put("mensaje",
                    "No está autenticado correctamente. Pa" +
                            "" +
                            "ra ver esta informacion debe estar autenticado con la cuenta " +
                            "que desea obtener los datos");
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
            return responseEntity;
        }

        if (user != null) {
            responseAsMap.put("usuario", user);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;

        User usuario = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")){
            CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
            usuario =currentPrincipalName.getUser();
        }

        try {
            if ((usuario == null)||((usuario.getId() != id)&&(usuario.getRol()!= User.Rol.ROLE_ADMIN))){
                responseAsMap.put("mensaje",
                        "No está autenticado correctamente. Pa" +
                                "" +
                                "ra ver esta informacion debe estar autenticado con la cuenta " +
                                "que desea obtener los datos");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.UNAUTHORIZED);
                return responseEntity;
            }
            UserDetails user = userService.loadUserById(id);
            if (user == null) {
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NOT_FOUND);
                responseAsMap.put("mensaje", "el user con id " + id + " no existe");
            } else {
                userService.delete(id);
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NO_CONTENT);
            }

        } catch (DataAccessException e) {
            responseAsMap.put("mensaje",
                    "el user no se ha podido eliminar correctamente: " + e.getMostSpecificCause().getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @PutMapping(value = "/admin/{id}")
    public ResponseEntity<Map<String, Object>> updateAdmin(@PathVariable long id, @Valid @RequestBody Admin admin,
                                                                     BindingResult result) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
        User usuario =currentPrincipalName.getUser();

        if (result.hasErrors()) {
            errores = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
            responseAsMap.put("errors", errores);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        try {
            if (admin.getId() != id){
                responseAsMap.put("mensaje",
                        "el producto no se ha actualizado correctamente: No coinciden los ids");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if (usuario.getRol() != User.Rol.ROLE_ADMIN){
                responseAsMap.put("mensaje",
                        "el producto no se ha actualizado correctamente: Solo los admins pueden actualizarse entre sí" );
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            Admin adminaux = adminRepository.findByUsername(admin.getUsername());

            if (adminaux == null){
                responseAsMap.put("mensaje", "El username de un usuario no puede ser cambiado. Haberlo pensao' antes.");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((admin.getNombre() != null) && (!admin.getNombre().equals(adminaux.getNombre()))){
                adminaux.setNombre(admin.getNombre());
            }
            if ((admin.getApellidos() != null)&&(!admin.getApellidos().equals(adminaux.getApellidos())) ){
                adminaux.setApellidos(admin.getApellidos());
            }
            if ((admin.getCiudad() != null)&&(!admin.getCiudad().equals(adminaux.getCiudad()))){
                adminaux.setCiudad(admin.getCiudad());
            }
            if ((admin.getCp() != null)&&(!admin.getCp().equals(adminaux.getCp()))){
                adminaux.setCp(admin.getCp());
            }
            if ((admin.getDireccion() != null)&&(!admin.getDireccion().equals(adminaux.getDireccion()))){
                adminaux.setDireccion(admin.getDireccion());
            }
            if ((admin.getEmail() != null)&&(!admin.getEmail().equals(adminaux.getEmail()))){
                adminaux.setEmail(admin.getEmail());
            }
            if ((admin.getFechaNacimiento() != null)&&(admin.getFechaNacimiento() != adminaux.getFechaNacimiento())){
                adminaux.setFechaNacimiento(admin.getFechaNacimiento());
            }
            if ((admin.getRol() != adminaux.getRol())){
                responseAsMap.put("mensaje", "El rol de un usuario no puede ser cambiado");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((admin.getTlf() != null)&&(!admin.getTlf().equals(adminaux.getTlf()))){
                adminaux.setTlf(admin.getTlf());
            }
            if ((admin.getUsername() != null)&&(!admin.getUsername().equals(adminaux.getUsername()))){
                responseAsMap.put("mensaje", "El username de un usuario no puede ser cambiado. Haberlo pensao' antes.");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((admin.getPassword() != null)&&(!admin.getPassword().equals(adminaux.getPassword()))){
                responseAsMap.put("mensaje", "Password no puede ser cambiada. Haberlo pensao' antes.");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

            Admin adminDB =adminRepository.save(adminaux);
            if (adminDB != null) {
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NO_CONTENT);
            } else {
                responseAsMap.put("mensaje", "el admin no se ha actualizado correctamente");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException | ConstraintViolationException e) {
            responseAsMap.put("mensaje",
                    "el producto no se ha actualizado correctamente: " + e.getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @PutMapping(value = "/trabajador/{id}")
    public ResponseEntity<Map<String, Object>> updateTrabajador(@PathVariable long id, @Valid @RequestBody Trabajador trabajador,
                                                                     BindingResult result) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
        User usuario =currentPrincipalName.getUser();


        if (result.hasErrors()) {
            errores = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
            responseAsMap.put("errors", errores);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        try {
            if (trabajador.getId() != id){
                responseAsMap.put("mensaje",
                        "el trabajador no se ha actualizado correctamente: No coinciden los ids");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

            Trabajador trabajadoraux = trabajadorRepository.findByUsername(trabajador.getUsername());
            if (trabajadoraux == null){
                responseAsMap.put("mensaje", "El username de un usuario no puede ser cambiado. Haberlo pensao' antes.");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((usuario.getId() != trabajadoraux.getId())&&(usuario.getRol() != User.Rol.ROLE_ADMIN)){
                responseAsMap.put("mensaje",
                        "el trabajador no se ha actualizado correctamente: Solo el propio usuario puede actualizar sus datos" );
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((trabajador.getNombre() != null)&&(!trabajador.getNombre().equals(trabajadoraux.getNombre()) )){
                trabajadoraux.setNombre(trabajador.getNombre());
            }
            if ((trabajador.getApellidos() != null)&&(!trabajador.getApellidos().equals(trabajadoraux.getApellidos())) ){
                trabajadoraux.setApellidos(trabajador.getApellidos());
            }
            if ((trabajador.getCiudad() != null)&&(!trabajador.getCiudad().equals(trabajadoraux.getCiudad()))){
                trabajadoraux.setCiudad(trabajador.getCiudad());
            }
            if ((trabajador.getCp() != null)&&(!trabajador.getCp().equals(trabajadoraux.getCp()))){
                trabajadoraux.setCp(trabajador.getCp());
            }
            if ((trabajador.getDireccion() != null)&&(!trabajador.getDireccion().equals(trabajadoraux.getDireccion()))){
                trabajadoraux.setDireccion(trabajador.getDireccion());
            }
            if ((trabajador.getEmail() != null)&&(!trabajador.getEmail().equals(trabajadoraux.getEmail()))){
                trabajadoraux.setEmail(trabajador.getEmail());
            }
            if ((trabajador.getFechaNacimiento() != null)&&(trabajador.getFechaNacimiento() != trabajadoraux.getFechaNacimiento())){
                trabajadoraux.setFechaNacimiento(trabajador.getFechaNacimiento());
            }
            if ((trabajador.getNss() != null)&&(trabajador.getNss() != trabajadoraux.getNss())){
                trabajadoraux.setNss(trabajador.getNss());
            }
            if ((trabajador.getDni() != null)&&(trabajador.getDni() != trabajadoraux.getDni())){
                trabajadoraux.setDni(trabajador.getDni());
            }
            if ((trabajador.getRol() != trabajadoraux.getRol())){
                responseAsMap.put("mensaje", "El rol de un usuario no puede ser cambiado");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((trabajador.getTlf() != null)&&(!trabajador.getTlf().equals(trabajadoraux.getTlf()))){
                trabajadoraux.setTlf(trabajador.getTlf());
            }
            if ((trabajador.getUsername() != null)&&(!trabajador.getUsername().equals(trabajadoraux.getUsername()))){
                responseAsMap.put("mensaje", "El username de un usuario no puede ser cambiado. Haberlo pensao' antes.");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if (trabajador.getPassword() != null){
                responseAsMap.put("mensaje", "Password no puede ser cambiada. Haberlo pensao' antes.");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

            Trabajador trabajadorDB =trabajadorRepository.save(trabajadoraux);
            if (trabajadorDB != null) {
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NO_CONTENT);
            } else {
                responseAsMap.put("mensaje", "el trabajador no se ha actualizado correctamente");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException | ConstraintViolationException e) {
            responseAsMap.put("mensaje",
                    "el producto no se ha actualizado correctamente: " + e.getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<Map<String, Object>> updateUser_general(@PathVariable long id, @Valid @RequestBody User_general user,
                                                                  BindingResult result) {

        Map<String, Object> responseAsMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> responseEntity = null;
        List<String> errores = null;



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication.getPrincipal();
        User usuario =currentPrincipalName.getUser();




        if (result.hasErrors()) {
            errores = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errores.add(error.getDefaultMessage());
            }
            responseAsMap.put("errors", errores);
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        try {
            if (user.getId() != id){
                responseAsMap.put("mensaje",
                        "el producto no se ha actualizado correctamente: No coinciden los ids");
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((usuario.getId() != user.getId())&&(usuario.getRol() != User.Rol.ROLE_ADMIN)){
                responseAsMap.put("mensaje",
                        "el producto no se ha actualizado correctamente: Solo los users pueden actualizarse entre sí" );
                responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            User_general useraux = user_generalRepository.findByUsername(user.getUsername());

            if (useraux == null){
                responseAsMap.put("mensaje", "El username de un usuario no puede ser cambiado. Haberlo pensao' antes.");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((user.getNombre() != null) && (!user.getNombre().equals(useraux.getNombre()))){
                useraux.setNombre(user.getNombre());
            }
            if ((user.getApellidos() != null)&&(!user.getApellidos().equals(useraux.getApellidos())) ){
                useraux.setApellidos(user.getApellidos());
            }
            if ((user.getCiudad() != null)&&(!user.getCiudad().equals(useraux.getCiudad()))){
                useraux.setCiudad(user.getCiudad());
            }
            if ((user.getCp() != null)&&(!user.getCp().equals(useraux.getCp()))){
                useraux.setCp(user.getCp());
            }
            if ((user.getDireccion() != null)&&(!user.getDireccion().equals(useraux.getDireccion()))){
                useraux.setDireccion(user.getDireccion());
            }
            if ((user.getEmail() != null)&&(!user.getEmail().equals(useraux.getEmail()))){
                useraux.setEmail(user.getEmail());
            }
            if ((user.getFechaNacimiento() != null)&&(user.getFechaNacimiento() != useraux.getFechaNacimiento())){
                useraux.setFechaNacimiento(user.getFechaNacimiento());
            }
            if ((user.getRol() != useraux.getRol())){
                responseAsMap.put("mensaje", "El rol de un usuario no puede ser cambiado");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if ((user.getTlf() != null)&&(!user.getTlf().equals(useraux.getTlf()))){
                useraux.setTlf(user.getTlf());
            }
            if ((user.getUsername() != null)&&(!user.getUsername().equals(useraux.getUsername()))){
                responseAsMap.put("mensaje", "El username de un usuario no puede ser cambiado. Haberlo pensao' antes.");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
            if (user.getPassword() != null){
                responseAsMap.put("mensaje", "Password no puede ser cambiada. Haberlo pensao' antes.");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }

            User_general userDB =user_generalRepository.save(useraux);
            if (userDB != null) {
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.NO_CONTENT);
            } else {
                responseAsMap.put("mensaje", "el user no se ha actualizado correctamente");
                return responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException | ConstraintViolationException e) {
            responseAsMap.put("mensaje",
                    "el producto no se ha actualizado correctamente: " + e.getMessage());
            responseEntity = new ResponseEntity<Map<String, Object>>(responseAsMap, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    /*AUTENTICACION*/

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUserName(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentPrincipalName = (CustomUserDetails) authentication1.getPrincipal();
        User usuario =currentPrincipalName.getUser();

        //User user=(User)authentication.getPrincipal();
        String jwtToken=jWTTokenHelper.generateToken(usuario.getUsername());

        LoginResponse response=new LoginResponse();
        response.setToken(jwtToken);


        return ResponseEntity.ok(response);
    }









}
