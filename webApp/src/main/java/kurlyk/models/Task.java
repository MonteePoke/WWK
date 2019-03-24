package kurlyk.models;

import kurlyk.LabType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer labNumber;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private LabType labType;

    @Lob
    @Column(columnDefinition="CLOB")
    private String question;

    @Lob
    @Column(columnDefinition="CLOB")
    private String answer;
}
