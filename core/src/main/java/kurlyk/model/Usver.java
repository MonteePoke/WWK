package kurlyk.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Usver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Role> roles;

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
