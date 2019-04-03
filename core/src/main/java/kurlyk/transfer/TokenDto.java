package kurlyk.transfer;

import kurlyk.models.Role;
import kurlyk.models.State;
import kurlyk.models.Token;
import kurlyk.models.User;
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

    private Long userId;
    private String userLogin;
    private Role userRole;
    private State userState;

    private String userFirstName;
    private String userMiddleName;
    private String userSecondName;
    private String userStudyGroup;

    public User toUser(){
        return User
                .builder()
                .id(userId)
                .login(userLogin)
                .role(userRole)
                .state(userState)
                .firstName(userFirstName)
                .middleName(userMiddleName)
                .secondName(userSecondName)
                .studyGroup(userStudyGroup)
                .build();
    }

    public static TokenDto fromToken(Token token){
        return TokenDto
                .builder()
                .value(token.getValue())
                .userId(token.getUser().getId())
                .userLogin(token.getUser().getLogin())
                .userRole(token.getUser().getRole())
                .userState(token.getUser().getState())
                .userFirstName(token.getUser().getFirstName())
                .userMiddleName(token.getUser().getMiddleName())
                .userSecondName(token.getUser().getSecondName())
                .userStudyGroup(token.getUser().getStudyGroup())
                .build();
    }
}
