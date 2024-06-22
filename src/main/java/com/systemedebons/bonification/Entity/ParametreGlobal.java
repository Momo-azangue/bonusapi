package com.systemedebons.bonification.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Parametres_globaux")
public class ParametreGlobal {

    @Id
    private String id;
    private String cle;
    private String valeur;
    private Date dateMiseAJour;
}
