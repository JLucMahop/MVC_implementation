package com.projetGL.restaurant.controllers.services;

import com.projetGL.restaurant.entities.PaymentMethod;
import com.projetGL.restaurant.controllers.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository PaymentMethodRepository;

    public PaymentMethod addPaymentMethod(PaymentMethod paymentRequest) {
        PaymentMethod paymentMethod = PaymentMethod.builder()
                .paymentId(UUID.randomUUID())
                .paymentName(paymentRequest.getPaymentName())
                .tag(paymentRequest.getTag())
                .build();

        paymentRequest.getFactures().forEach(paymentMethod::addFacture);

        return PaymentMethodRepository.save(paymentMethod);
    }

    public Boolean deletePaymentMethod(String id) {
        try {
            Optional<PaymentMethod> PaymentMethod = PaymentMethodRepository.findById(UUID.fromString(id));
            if (PaymentMethod.isPresent()){
                PaymentMethodRepository.delete(PaymentMethod.get());
                return true;
            } else
                return false;
        } catch (Exception e) {
            return false;
        }

    }

    public PaymentMethod modifyPaymentMethod(PaymentMethod PaymentMethod) throws PaymentMethodNotFoundException {
        Optional<PaymentMethod> PaymentMethod1 = PaymentMethodRepository.findById(PaymentMethod.getPaymentId());
        if (PaymentMethod1.isPresent())
            return PaymentMethodRepository.save(PaymentMethod);
        else
            throw new PaymentMethodNotFoundException();
    }

    public List<PaymentMethod> findAllPaymentMethods() {
        return (List<PaymentMethod>) PaymentMethodRepository.findAll();
    }

    public List<PaymentMethod> findAllByTag(String tag) {
        return PaymentMethodRepository.findByTag(tag);
    }

    public List<PaymentMethod> findAllByPaymentName(String paymentName) {
        return PaymentMethodRepository.findByPaymentName(paymentName);
    }

}
