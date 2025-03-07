package com.systemedebons.bonification.Service;

import com.systemedebons.bonification.Entity.Administrator;
import com.systemedebons.bonification.Repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdminService {


    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Administrator createAdministrator(Administrator administrator) {
        return  administratorRepository.save(administrator);
    }




  /**  public Administrator login(String username, String password) {
        Administrator administrator = administratorRepository.findByUsername(username);
        if (administrator != null && administrator.getPassword().equals(password)) {

            return administrator;
        }else {
            return null;
        }
    }**/

    public List<Administrator> getAdmins() {
        return administratorRepository.findAll();
    }

    public Optional<Administrator> getAdministrator(String administratorId) {
        return administratorRepository.findById(administratorId);
    }

    public Administrator saveAdministrator(Administrator administrator) {
        if(administrator.getMotDePasse() != null && !administrator.getMotDePasse().isEmpty()){
            administrator.setMotDePasse(passwordEncoder.encode(administrator.getMotDePasse()));
        }else{
            throw new IllegalArgumentException("MotDePasse cannot be empty");
        }
        return administratorRepository.save(administrator);
    }

    public void deleteAdministrator(String id) {
        administratorRepository.deleteById(id);
    }

}
