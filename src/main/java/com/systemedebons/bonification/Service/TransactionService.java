package com.systemedebons.bonification.Service;

import com.systemedebons.bonification.Entity.Historique;
import com.systemedebons.bonification.Entity.Point;
import com.systemedebons.bonification.Entity.Transaction;
import com.systemedebons.bonification.Entity.User;
import com.systemedebons.bonification.Repository.TransactionRepository;
import com.systemedebons.bonification.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PointService pointService;

    @Autowired
    private RuleService ruleService;

    @Autowired
    private HistoriqueService historiqueService;

    @Autowired
    private UserRepository userRepository;


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

    public Transaction saveTransaction(Transaction transaction) {
        // Get the current authenticated user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            transaction.setUser(userOptional.get());
        } else {
            throw new RuntimeException("User not found");
        }

        transaction.setDate(LocalDate.now()); // Set current date
        Transaction savedTransaction = transactionRepository.save(transaction);
        int points = 0;

        if (ruleService.estUneTransactionEligible(savedTransaction)) {
            points = ruleService.calculerPoints(savedTransaction);

            if (points > 0) {
                Point point = new Point();
                point.setUser(savedTransaction.getUser());
                point.setNombre(points);
                point.setId(savedTransaction.getId());
                point.setDate(LocalDate.now());
                pointService.savePoint(point);
            }
        }
        Historique historique = new Historique();
        historique.setUser(savedTransaction.getUser());
        historique.setDate(LocalDate.now());
        historique.setTypeOperation(savedTransaction.getTypeTransaction());
        historique.setPointsAvant(points);
        historique.setMontantTransaction(savedTransaction.getMontantTransaction());
        historique.setDescription("Transaction " + (points > 0 ? "éligible" : "non éligible") + " pour des points.");

        historiqueService.saveHistorique(historique);

        return savedTransaction;
    }

    public void deleteTransaction(String id) {
        transactionRepository.deleteById(id);
    }






}
