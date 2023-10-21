package br.com.lanchonete.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_order")
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    @Column(length = 10)
    private Integer number;
    @Enumerated(EnumType.STRING)
    private StatusType status;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_billing_id")
    private BillingEntity billing;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_client_id")
    private ClientEntity client;
    @OneToMany(mappedBy="order", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItems;

}
