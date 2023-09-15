package com.projetGL.restaurant.controllers;

import com.projetGL.restaurant.entities.PaymentMethod;
import com.projetGL.restaurant.controllers.services.FactureNotFoundException;
import com.projetGL.restaurant.controllers.services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/leCafe/facture")
public class FactureController {
    @Autowired
    private FactureService FactureService;

    @PostMapping(value = "/")
    public ResponseEntity<Facture> addFacture(@RequestBody @Valid Facture facture) {
        return ResponseEntity.ok(FactureService.addFacture(facture));
    }

    @GetMapping(value = "/accountId/{accountId}")
    public ResponseEntity<List<Facture>> getAllFacturesByAccountId(@PathVariable String accountId) {
        return ResponseEntity.ok(FactureService.findAllByAccountId(accountId));
    }

    @GetMapping(value = "/paymentMethod/{paymentMethod}")
    public ResponseEntity<List<Facture>> getAllFacturesByPaymentMethod(@PathVariable PaymentMethod paymentMethod) {
        return ResponseEntity.ok(FactureService.findAllByPaymentMethod(paymentMethod));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Facture>> getAllFactures() {
        return ResponseEntity.ok(FactureService.findAllFactures());
    }

    @ExceptionHandler(value = FactureNotFoundException.class)
    public ResponseEntity<?> onFactureNotFound(){
        return ResponseEntity.notFound().build();
    }
}
