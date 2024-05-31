package com.systemedebons.bonification.Repository;

import com.systemedebons.bonification.Entity.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RuleRepository extends MongoRepository<Rule, String> {

}
