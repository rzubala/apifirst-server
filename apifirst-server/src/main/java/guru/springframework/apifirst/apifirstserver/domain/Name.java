package guru.springframework.apifirst.apifirstserver.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Name {
    private String prefix;
    private String firstName;
    private String lastName;
    private String suffix;
}
