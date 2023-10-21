package br.com.lanchonete.postgres.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_client")
@EntityListeners(AuditingEntityListener.class)
public class ClientEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 14, nullable = false)
    private String cpf;
    @Column(length = 50, nullable = false)
    private String email;
    @OneToMany(mappedBy="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEntity> orders = new ArrayList<>();

}
