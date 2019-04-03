package kurlyk.models;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String middleName;
    private String secondName;

    private String studyGroup;

    public String getFullName() {
        return middleName + " " + firstName + " " + secondName;
    }
}
