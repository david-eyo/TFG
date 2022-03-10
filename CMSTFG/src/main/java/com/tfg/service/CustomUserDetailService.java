package com.tfg.service;

import com.tfg.dao.IAdminDao;
import com.tfg.dao.ITrabajadorDao;
import com.tfg.dao.IUser_generalDao;
import com.tfg.dao.IUsuarioDao;
import com.tfg.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private IAdminDao adminRepository;

    @Autowired
    private ITrabajadorDao trabajadorRepository;

    @Autowired
    private IUser_generalDao user_generalRepository;

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userAdmin =adminRepository.findByUsername(username);
        User userTrabajador =trabajadorRepository.findByUsername(username);
        User userUser_general =user_generalRepository.findByUsername(username);

        CustomUserDetails userDetails = null;
        if (userAdmin != null){
            userDetails = new CustomUserDetails();
            userDetails.setUser(userAdmin);
        }else if(userTrabajador != null){
            userDetails = new CustomUserDetails();
            userDetails.setUser(userTrabajador);
        }else if(userUser_general != null) {
            userDetails = new CustomUserDetails();
            userDetails.setUser(userUser_general);
        }
        return userDetails;
    }


    public UserDetails loadUserById(long id){

        User user= usuarioDao.findUserById(id);
        CustomUserDetails userDetails = null;

        if (user != null){
            userDetails = new CustomUserDetails();
            userDetails.setUser(user);
        }

        return userDetails;

    }


    public void delete(Long id){
        usuarioDao.deleteById(id);
    }
}
