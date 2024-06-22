package com.systemedebons.bonification.Repository;

import com.systemedebons.bonification.Entity.ParametreGlobal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParametreGlobalRespository extends MongoRepository<ParametreGlobal,String> {

    ParametreGlobal findByCle(String cle);
}
