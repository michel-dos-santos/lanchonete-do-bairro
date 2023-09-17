package br.com.lanchonete.repository.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "request_item")
@EntityListeners(AuditingEntityListener.class)
public class RequestItemEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    @Column(length = 255, nullable = false)
    private String observation;
    @OneToOne
    @JoinColumn(name = "fk_product_id")
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name="fk_request_id")
    private RequestEntity request;


}
