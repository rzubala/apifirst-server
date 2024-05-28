package guru.springframework.apifirst.apifirstserver.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "char(36)", updatable = false, nullable = false)
    private UUID id;

    @OneToOne
    private Address shipToAddress;

    @OneToOne
    private Address billToAddress;

    @OneToMany(mappedBy = "customer")
    private List<PaymentMethod> paymentMethods;

    @Embedded
    private Name name;

    private String email;
    private String phone;

    @CreationTimestamp
    private OffsetDateTime dateCreated;
    @UpdateTimestamp
    private OffsetDateTime dateUpdated;
}
