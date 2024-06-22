package com.systemedebons.bonification.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@Document(collection = "historiques")
public class Historique {
    @Id
    private String id;
    @DBRef
    private User user;
    private LocalDate date;
    private String typeOperation;
    private int pointsAvant;
    private int pointsApres;
    private  double montantTransaction;
    private String description;
}
