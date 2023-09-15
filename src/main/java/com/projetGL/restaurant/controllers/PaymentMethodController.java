package com.projetGL.restaurant.controllers;

import com.projetGL.restaurant.controllers.services.PaymentMethodNotFoundException;
import com.projetGL.restaurant.controllers.services.PaymentMethodService;
import com.projetGL.restaurant.controllers.services.MenuItemNotFoundException;
import com.projetGL.restaurant.entities.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/leCafe/paymentMethod")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService PaymentMethodService;

    @PostMapping(value = "/")
    public ResponseEntity<PaymentMethod> addPaymentMethod(@RequestBody @Valid PaymentMethod PaymentMethod) {
        return ResponseEntity.ok(PaymentMethodService.addPaymentMethod(PaymentMethod));
    }

    @PostMapping(value = "/delete/{PaymentMethodId}")
    public ResponseEntity<Boolean> deletePaymentMethod(@PathVariable String PaymentMethodId) {
        return ResponseEntity.ok(PaymentMethodService.deletePaymentMethod(PaymentMethodId));
    }

    @PostMapping(value = "/modify/")
    public ResponseEntity<PaymentMethod> modifyPaymentMethod(@RequestBody @Valid PaymentMethod PaymentMethod) throws MenuItemNotFoundException, PaymentMethodNotFoundException {
        return ResponseEntity.ok(PaymentMethodService.modifyPaymentMethod(PaymentMethod));
    }

    @GetMapping(value = "/paymentName/{name}")
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethodsByName(@PathVariable String name) {
        return ResponseEntity.ok(PaymentMethodService.findAllByPaymentName(name));
    }

    @GetMapping(value = "/tag/{tag}")
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethodsByAvailability(@PathVariable String tag) {
        return ResponseEntity.ok(PaymentMethodService.findAllByTag(tag));
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethods() {
        return ResponseEntity.ok(PaymentMethodService.findAllPaymentMethods());
    }

    @ExceptionHandler(value = PaymentMethodNotFoundException.class)
    public ResponseEntity<?> onPaymentMethodNotFound(){
        return ResponseEntity.notFound().build();
    }
}
