package com.projetGL.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Payment_method")
public class PaymentMethod {
    @Id
    private UUID paymentId;
    @Indexed
    private String paymentName;
    @Indexed
    private String tag;
    private List<Facture> factures;

    public void addFacture(Facture facture) {
        factures.add(facture);
        facture.setPaymentMethod(this);
    }

    public void removeFacture(Facture facture) {
        factures.remove(facture);
        facture.setPaymentMethod(null);
    }

}
