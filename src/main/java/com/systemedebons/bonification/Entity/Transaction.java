package com.systemedebons.bonification.Entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Data
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;
    private double montantTransaction;
    private String typeTransaction;
    private Statuts statut;
    private LocalDate date;
    @DBRef
    private User user;
    private  int pointsBonifies;
    private  int pointsUtilises;



}
