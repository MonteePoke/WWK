package kurlyk.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
    private String value;

    private String userLogin;
    private Role userRole;
    private State userState;

    private String userFirstName;
    private String userMiddleName;
    private String userSecondName;
    private String userStudyGroup;
}
