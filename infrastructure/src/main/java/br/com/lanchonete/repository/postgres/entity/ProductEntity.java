package br.com.lanchonete.repository.postgres.entity;

import br.com.lanchonete.model.StatusActiveType;
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
@Table(name = "tb_product")
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 255, nullable = false)
    private String description;
    @Column(length = 1024, nullable = false)
    private String image;
    @Column(length = 14, scale = 2)
    private BigDecimal unitPrice;
    @ManyToOne
    @JoinColumn(name = "fk_category_id")
    private CategoryEntity category;
    @Enumerated(EnumType.STRING)
    private StatusActiveType status;

}
