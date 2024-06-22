package com.systemedebons.bonification.Controller;

import com.systemedebons.bonification.Entity.ParametreGlobal;
import com.systemedebons.bonification.Service.ParametreGlobalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/api/parametres")
public class ParametreGlobalController {

    @Autowired
    private ParametreGlobalService parametreGlobalService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ParametreGlobal> getAllParametres() {
        return parametreGlobalService.getAllParametres();
    }

    @GetMapping("/{cle}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParametreGlobal> getParametreByCle(@PathVariable String cle) {
        ParametreGlobal parametre = parametreGlobalService.getParametreByCle(cle);
        return ResponseEntity.ok(parametre);
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParametreGlobal> createParametre(@RequestBody ParametreGlobal parametre) {
        ParametreGlobal nouveauParametre = parametreGlobalService.saveParametre(parametre);
        return ResponseEntity.ok(nouveauParametre);
    }


}
