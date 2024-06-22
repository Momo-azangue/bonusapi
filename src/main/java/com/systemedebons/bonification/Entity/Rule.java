package com.systemedebons.bonification.Entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "Rules")
public class Rule {

    @Id
    private String id;
    private String description;
    private float montantMin;
    private String type_transaction;
    private int pointsParTranscations;
    private Date date_debut;
    private Date date_fin;
    private String createdBy;  // ID de l'utilisateur
    private String createdByName;  // Nom de l'utilisateur
}
