package br.com.lanchonete.repository.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_billing")
@EntityListeners(AuditingEntityListener.class)
public class BillingEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    @Column(length = 14, scale = 2)
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    private StatusPaymentType status;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_billing_form_id")
    private BillingFormEntity billingForm;

}
