package com.projetGL.restaurant.controllers.services;

import com.projetGL.restaurant.entities.Facture;
import com.projetGL.restaurant.entities.Order;
import com.projetGL.restaurant.entities.PaymentMethod;
import com.projetGL.restaurant.controllers.repositories.FactureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class FactureService {

    @Autowired
    private FactureRepository FactureRepository;

    public Facture addFacture(Facture factureRequest) {
        Facture facture = Facture.builder()
                .factureId(UUID.randomUUID())
                .accountId(factureRequest.getAccountId())
                .postDateTime(LocalDateTime.now())
                .build();
        factureRequest.getOrders().forEach(facture::addOrder);
        factureRequest.setPaymentMethod(factureRequest.getPaymentMethod());
        double total = 0;
        for (Order order : facture.getOrders()){
            total += order.getCost();
        }
        facture.setTotalCost(total);
        return FactureRepository.save(facture);
    }

    public List<Facture> findAllFactures() {
        return (List<Facture>) FactureRepository.findAll();
    }

    public List<Facture> findAllByAccountId(String accountId) {
        return FactureRepository.findByAccountId(accountId);
    }

    public List<Facture> findAllByPaymentMethod(PaymentMethod paymentMethod) {
        return FactureRepository.findByPaymentMethod(paymentMethod);
    }

}
