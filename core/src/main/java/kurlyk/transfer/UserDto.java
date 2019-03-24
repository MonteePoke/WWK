package kurlyk.transfer;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String login;
    private String password;
    private Role role;
    private State state;

    private String firstName;
    private String middleName;
    private String secondName;
    private String studyGroup;

    public String getFullName() {
        return middleName + " " + firstName + " " + secondName;
    }
}
