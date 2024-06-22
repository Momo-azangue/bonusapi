package com.systemedebons.bonification.Service;

import com.systemedebons.bonification.Entity.ParametreGlobal;
import com.systemedebons.bonification.Repository.ParametreGlobalRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParametreGlobalService {

    @Autowired
    private ParametreGlobalRespository parametreGlobalRespository;



    public List<ParametreGlobal> getAllParametres() {
        return parametreGlobalRespository.findAll();
    }

    public ParametreGlobal getParametreByCle(String cle) {
        return parametreGlobalRespository.findByCle(cle);
    }

    public ParametreGlobal saveParametre(ParametreGlobal parametre) {
        parametre.setDateMiseAJour(new Date());
        return parametreGlobalRespository.save(parametre);
    }

}
